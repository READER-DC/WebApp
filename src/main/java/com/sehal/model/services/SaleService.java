package com.sehal.model.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.sehal.model.Sale;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class SaleService {

	@Resource(lookup = "jdbc/PostgressSQL")
	DataSource dataSource;

	public List<Sale> getSales(String dateStart, String dateEnd) {
		Sale.sales.clear();
		System.out.println(
				"dateStart = " + dateStart + "  " + "dateEnd = " + dateEnd);
		String sql = "SELECT ts.fk_k_id, tg.mgr_name, tg.g_name, ts.fk_g_id, sum(ts.qty) as sumSale "
				+ "FROM tbl_sales ts "
				+ "LEFT JOIN tbl_kontragents tk ON tk.k_id = ts.fk_k_id "
				+ "LEFT JOIN tbl_goods tg ON ts.fk_g_id = tg.g_id "
				+ "WHERE (ts.date_doc BETWEEN ? AND ? ) AND tk.bg_ch_name LIKE ('Розница') "
				+ "AND ts.is_pickup = FALSE  AND ts.is_wholesale = FALSE "
				+ "GROUP BY ts.fk_k_id, tg.mgr_name, tg.g_name, ts.fk_g_id";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			Date dateStart1 = Date.valueOf(dateStart);
			Date dateEnd1 = Date.valueOf(dateEnd);
			
			preparedStatement.setDate(1, dateStart1);
			preparedStatement.setDate(2, dateEnd1);
			System.out.println(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Sale sale = new Sale();
				sale.setFK_K_ID(rs.getInt("fk_k_id"));
				sale.setFK_G_ID(rs.getInt("fk_g_id"));
				sale.setQTY(rs.getInt("sumSale"));
				sale.add();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Sale.sales;
	}

	public void insert(List<String> sales) {
		int counter = 0;
		sales.remove(0);
		String sql = "INSERT INTO TBL_SALES(DATE_DOC, FK_K_ID, FK_G_ID,	QTY,"
				+ "IS_PICKUP, IS_WHOLESALE) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			for (String ln : sales) {
				String[] sale = ln.split(";", 6);

				preparedStatement.setDate(1,
						Date.valueOf(sale[0].substring(0, 10)));
				preparedStatement.setInt(2, Integer.valueOf(sale[1]));
				preparedStatement.setInt(3, Integer.valueOf(sale[2]));
				preparedStatement.setInt(4, Integer.valueOf(sale[3]));
				preparedStatement.setBoolean(5, Boolean.valueOf(sale[4]));
				preparedStatement.setBoolean(6, Boolean.valueOf(sale[5]));

				preparedStatement.executeUpdate();

				counter++;
				if (counter % 100000 == 0) {
					System.out.println(counter + " sales inserted!");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(counter + " - " + "INSERT INTO TBL_SALES SUCCESS");
	}
}

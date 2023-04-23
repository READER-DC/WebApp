package com.sehal.model.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class StockService {

	@Resource(lookup = "jdbc/PostgressSQL")
	DataSource dataSource;

	public void insert(List<String> stocks) {

		int counter = 0;
		stocks.remove(0);
		String sql = "INSERT INTO TBL_CURR_STOCKS(DATE_STOCK"
				+ ", FK_CFO_ID, FK_G_ID, FK_WH_DOG_ID, FK_WH_K_ID, FK_TYPE_DOG_ID, STATUS_GOODS_ID"
				+ ", FK_LOT_ID, DATE_LOT, QTY, QTY_REZERV, S_COST, S_COST_NONDS, S_COST_VAL"
				+ ", S_COST_NONDS_VAL, S_COSTIN_VAL, S_COSTIN_NONDS_VAL)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql)) {
			for (String ln : stocks) {
				String[] stock = ln.split(";", 17);

				if (stock[9].equals("0.01")) {
					stock[9] = "0";
				}

				preparedStatement.setDate(1,
						Date.valueOf(stock[0].substring(0, 10)));
				preparedStatement.setInt(2, Integer.valueOf(stock[1]));
				preparedStatement.setInt(3, Integer.valueOf(stock[2]));
				preparedStatement.setInt(4, Integer.valueOf(stock[3]));
				preparedStatement.setInt(5, Integer.valueOf(stock[4]));
				preparedStatement.setInt(6, Integer.valueOf(stock[5]));
				preparedStatement.setInt(7, Integer.valueOf(stock[6]));
				preparedStatement.setInt(8, Integer.valueOf(stock[7]));
				preparedStatement.setDate(9,
						Date.valueOf(stock[8].substring(0, 10)));
				preparedStatement.setInt(10, Integer.valueOf(stock[9]));
				preparedStatement.setInt(11, Integer.valueOf(stock[10]));
				preparedStatement.setDouble(12, Double.valueOf(stock[11]));
				preparedStatement.setDouble(13, Double.valueOf(stock[12]));
				preparedStatement.setDouble(14, Double.valueOf(stock[13]));
				preparedStatement.setDouble(15, Double.valueOf(stock[14]));
				preparedStatement.setDouble(16, Double.valueOf(stock[15]));
				preparedStatement.setDouble(17, Double.valueOf(stock[16]));

				preparedStatement.addBatch();
				counter++;
				if (counter % 1000 == 0) {
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}

				if (counter % 100000 == 0) {
					System.out.println(counter + "stoks inserted!");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(
				counter + " - " + "INSERT INTO TBL_CURR_STOCKS SUCCESS");
	}

}

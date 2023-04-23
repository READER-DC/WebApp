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
public class SaleService {

	@Resource(lookup = "jdbc/PostgressSQL")
	DataSource dataSource;

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

				preparedStatement.setDate(1, Date.valueOf(sale[0].substring(0, 10)));
				preparedStatement.setInt(2, Integer.valueOf(sale[1]));
				preparedStatement.setInt(3, Integer.valueOf(sale[2]));
				preparedStatement.setInt(4, Integer.valueOf(sale[3]));
				preparedStatement.setBoolean(5, Boolean.valueOf(sale[4]));
				preparedStatement.setBoolean(6, Boolean.valueOf(sale[5]));
				
				preparedStatement.executeUpdate();
				
				counter++;
				if(counter%100000 == 0) {
					System.out.println(counter + " sales inserted!");
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(counter + " - " + "INSERT INTO TBL_SALES SUCCESS");
	}
}

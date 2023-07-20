package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class GoodsFamilyService {

	@Resource(lookup = "jdbc/PostgreSQL")
	DataSource dataSource;

	public void insert(List<String> goodsFamilies) {
		int counter = 0;
		goodsFamilies.remove(0);
		String sql = "INSERT INTO TBL_GOODS_FAMILY (G_ID, G_ID_MAIN, KS_ID_FAMILY,"
				+ "REG_ID_FAMILY, TYPE_ID_FAMILY) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {

			for (String ln : goodsFamilies) {
				String[] goodFamily = ln.split(";", 5);

				preparedStatement.setInt(1, Integer.valueOf(goodFamily[0]));
				preparedStatement.setInt(2, Integer.valueOf(goodFamily[1]));
				preparedStatement.setInt(3, Integer.valueOf(goodFamily[2]));
				preparedStatement.setInt(4, Integer.valueOf(goodFamily[3]));
				preparedStatement.setInt(5, Integer.valueOf(goodFamily[4]));

				preparedStatement.executeUpdate();
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(
				counter + " - " + "INSERT INTO TBL_GOODS_FAMILY SUCCESS");
	}
}

package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class MatrixService {

	@Resource(lookup = "jdbc/PostgreSQL")
	DataSource dataSource;

	public void insert(List<String> matrixes) {
		int i = 0;
		int counter = 0;
		matrixes.remove(0);
		String sql = "INSERT INTO TBL_CURR_MATRIX(K_ID, G_ID, FORMATS, MINS, MAXS, "
				+ "ADR, BZ, PROMO, BZH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			for (String ln : matrixes) {
				String[] matrix = ln.split(";", 10);
				for (i = 0; i < 9; i++) {
					if (matrix[i].isEmpty())
						matrix[i] = "0";
					// System.out.print(matrix[i]);
				}

				preparedStatement.setInt(1, Integer.valueOf(matrix[0]));
				preparedStatement.setInt(2, Integer.valueOf(matrix[1]));
				preparedStatement.setString(3, matrix[2]);
				preparedStatement.setInt(4, Integer.valueOf(matrix[3]));
				preparedStatement.setDouble(5, Double.valueOf(matrix[4]));
				preparedStatement.setInt(6, Integer.valueOf(matrix[5]));
				preparedStatement.setInt(7, Integer.valueOf(matrix[6]));
				preparedStatement.setInt(8, Integer.valueOf(matrix[7]));
				preparedStatement.setInt(9, Integer.valueOf(matrix[8]));

				preparedStatement.executeUpdate();

				counter++;
				if (counter % 100000 == 0) {
					System.out.println(counter + " inserted!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(
				counter + " - " + "INSERT INTO TBL_CURR_MATRIX SUCCESS");
	}
}

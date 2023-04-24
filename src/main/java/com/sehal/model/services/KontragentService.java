package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.sehal.model.Kontragent;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class KontragentService {

	@Resource(lookup = "jdbc/PostgressSQL")
	DataSource dataSource;

	public void insert(List<String> kontragents) {
		int counter = 0;
		kontragents.remove(0);
		String sql = "INSERT INTO TBL_KONTRAGENTS(K_ID, K_NAME, CH_ID, BG_CH_ID,"
				+ "BG_CH_NAME, BG_FMT_ID, BG_FMT_NAME, BG_REG_ID, BG_REG_NAME"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			for (String ln : kontragents) {
				String[] kontragent = ln.split(";", 9);

				preparedStatement.setInt(1, Integer.valueOf(kontragent[0]));
				preparedStatement.setString(2, kontragent[1]);
				preparedStatement.setInt(3, Integer.valueOf(kontragent[2]));
				preparedStatement.setInt(4, Integer.valueOf(kontragent[3]));
				preparedStatement.setString(5, kontragent[4]);
				preparedStatement.setInt(6, Integer.valueOf(kontragent[5]));
				preparedStatement.setString(7, kontragent[6]);
				preparedStatement.setInt(8, Integer.valueOf(kontragent[7]));
				preparedStatement.setString(9, kontragent[8]);

				preparedStatement.addBatch();

				if (counter % 1000 == 0) {
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(
				counter + " - " + "INSERT INTO TBL_KONTRAGENTS SUCCESS");
	}

	public void getAll() {
		String sql = "SELECT DISTINCT (tcm.k_id), tk.k_name FROM tbl_curr_matrix tcm "
				+ "LEFT JOIN tbl_kontragents tk ON tcm.k_id = tk.k_id "
				+ "ORDER BY tk.k_name";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {				
				Kontragent kontragent = new Kontragent();
				kontragent.setK_NAME(resultSet.getString(2));
				kontragent.setK_ID(resultSet.getInt(1));
				kontragent.addKontragent();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

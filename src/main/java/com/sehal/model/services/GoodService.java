package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class GoodService {
	@Resource(lookup = "jdbc/PostgressSQL")
	DataSource dataSource;

	public void deleteFromTBL() {
		String sqlString = "DELETE FROM TBL_GOODS";
		try (Connection connection = dataSource.getConnection();
				Statement stm = connection.createStatement()) {
			stm.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(List<String> goods) {
		int i = 0;
		int counter = 0;
		deleteFromTBL();
		goods.remove(0);
		String sql = "INSERT INTO TBL_GOODS(G_ID, G_NAME, MNF_ID, MNF_NAME, GRT22_ID, GRT22_NAME,"
				+ "MGRT22_ID, MGRT22_NAME, GR_ID, GR_NAME, MGR_ID, MGR_NAME, SCAT_ID, SCAT_NAME, CAT_ID, "
				+ "CAT_NAME, CAT22_ID, CAT22, SCAT22_ID, SCAT22, UNIT, CLEAN_WEIGHT, VOLUME, PER_PKG)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {

			for (String ln : goods) {
				String[] item = ln.split(";", 25);

				for (i = 0; i < 24; i++) {
					if (item[i].isEmpty())
						item[i] = "0";
				}
				preparedStatement.setInt(1, Integer.valueOf(item[0]));
				preparedStatement.setString(2, String.valueOf(item[1]));
				preparedStatement.setInt(3, Integer.valueOf(item[2]));
				preparedStatement.setString(4, String.valueOf(item[3]));
				preparedStatement.setInt(5, Integer.valueOf(item[4]));
				preparedStatement.setString(6, String.valueOf(item[5]));
				preparedStatement.setInt(7, Integer.valueOf(item[6]));
				preparedStatement.setString(8, String.valueOf(item[7]));
				preparedStatement.setInt(9, Integer.valueOf(item[8]));
				preparedStatement.setString(10, String.valueOf(item[9]));
				preparedStatement.setInt(11, Integer.valueOf(item[10]));
				preparedStatement.setString(12, String.valueOf(item[11]));
				preparedStatement.setInt(13, Integer.valueOf(item[12]));
				preparedStatement.setString(14, String.valueOf(item[13]));
				preparedStatement.setInt(15, Integer.valueOf(item[14]));
				preparedStatement.setString(16, String.valueOf(item[15]));
				preparedStatement.setInt(17, Integer.valueOf(item[16]));
				preparedStatement.setString(18, String.valueOf(item[17]));
				preparedStatement.setInt(19, Integer.valueOf(item[18]));
				preparedStatement.setString(20, String.valueOf(item[19]));
				preparedStatement.setString(21, String.valueOf(item[20]));
				preparedStatement.setDouble(22, Double.valueOf(item[21]));
				preparedStatement.setDouble(23, Double.valueOf(item[22]));
				preparedStatement.setDouble(24, Double.valueOf(item[23]));

				preparedStatement.executeUpdate();
				counter++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(counter + " - " + "INSERT INTO TBL_GOODS SUCCESS");

	}

}

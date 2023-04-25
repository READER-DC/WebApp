package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class UserService {

	@Resource(lookup = "jdbc/PostgressSQL")
	private DataSource dataSource;
	
	public boolean authorization(String userName, String psw) {
		System.out.println("authorization started!");
		boolean auth = false;
		long id = 0l;
		String sql = "SELECT id_user FROM TBL_USERS WHERE first_name LIKE(?) and pass LIKE (?)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, psw);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getLong(1);
				if (id > 0)
					auth = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auth;
	}

}

package com.sehal.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sehal.model.DistribytionReport;
import com.sehal.model.Kontragent;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class KontragentService {

	@Resource(lookup = "jdbc/PostgreSQL")
	DataSource dataSource;
	
	public List<String> makeReport1byKontragent(int k_id) {
		List<String> list = new ArrayList<>();
		String sql = "SELECT "
				+ "	t1.k_id,"
				+ "	t1.g_id,"
				+ "	CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END g_id_second ,"
				+ "	t1.formats,"
				+ "	CASE WHEN ( "
				+ "			(COALESCE(t1.mins, 0) + COALESCE(t1.adr, 0) + COALESCE(t1.bzh, 0)) > 0 \n"
				+ "			AND\n"
				+ "			t1.g_id = ( CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id END) \n"
				+ "		) THEN 1 ELSE 0 END nd_plan,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END AND  t4.qty1 > 0 ) THEN 1 ELSE 0 END nd_fact,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.mins ELSE 0 END mins,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.maxs ELSE 0 END maxs,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.adr ELSE 0 END adr,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.bzh ELSE 0 END bzh,"
				+ "	COALESCE (t4.qty1, 0) qty_stock"
				+ " FROM tbl_curr_matrix t1"
				+ " LEFT JOIN tbl_goods_family t2 ON t1.g_id = t2.g_id_main"
				+ " LEFT JOIN (\n"
				+ "			SELECT fk_wh_k_id, fk_g_id, sum(qty) AS qty1"
				+ "			FROM tbl_curr_stocks"
				+ "			GROUP BY fk_wh_k_id, fk_g_id) t4"
				+ "			ON fk_wh_k_id = t1.k_id AND fk_g_id = t1.g_id"
				+ " WHERE t1.k_id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			preparedStatement.setInt(1, k_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DistribytionReport dr = new DistribytionReport();
				dr.setK_id(rs.getInt("k_id"));
				dr.setG_id(rs.getInt("g_id"));
				dr.setG_id_second(rs.getInt("g_id_second"));
				dr.setFormat(rs.getString("formats"));
				dr.setNd_plan(rs.getInt("nd_plan"));
				dr.setNd_fact(rs.getInt("nd_fact"));
				dr.setMaxs(rs.getDouble("maxs"));
				dr.setQty_stock(rs.getInt("qty_stock"));
				dr.add();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;	
	}
	
	public List<String> makeReport1() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT "
				+ "	t1.k_id,"
				+ "	t1.g_id,"
				+ "	CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END g_id_second ,"
				+ "	t1.formats,"
				+ "	CASE WHEN ( "
				+ "			(COALESCE(t1.mins, 0) + COALESCE(t1.adr, 0) + COALESCE(t1.bzh, 0)) > 0 \n"
				+ "			AND\n"
				+ "			t1.g_id = ( CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id END) \n"
				+ "		) THEN 1 ELSE 0 END nd_plan,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END AND  t4.qty1 > 0 ) THEN 1 ELSE 0 END nd_fact,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.mins ELSE 0 END mins,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.maxs ELSE 0 END maxs,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.adr ELSE 0 END adr,"
				+ "	CASE WHEN (t1.g_id = CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END) THEN t1.bzh ELSE 0 END bzh,"
				+ "	COALESCE (t4.qty1, 0) qty_stock"
				+ " FROM tbl_curr_matrix t1"
				+ " LEFT JOIN tbl_goods_family t2 ON t1.g_id = t2.g_id_main"
				+ " LEFT JOIN (\n"
				+ "			SELECT fk_wh_k_id, fk_g_id, sum(qty) AS qty1"
				+ "			FROM tbl_curr_stocks"
				+ "			GROUP BY fk_wh_k_id, fk_g_id) t4"
				+ "			ON fk_wh_k_id = t1.k_id AND fk_g_id = (CASE WHEN (t2.g_id IS NULL) THEN t1.g_id ELSE t2.g_id  END)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DistribytionReport dr = new DistribytionReport();
					dr.setK_id(rs.getInt("k_id"));
					dr.setG_id(rs.getInt("g_id"));
					dr.setG_id_second(rs.getInt("g_id_second"));
					dr.setFormat(rs.getString("formats"));
					dr.setNd_plan(rs.getInt("nd_plan"));
					dr.setNd_fact(rs.getInt("nd_fact"));
					dr.setMaxs(rs.getDouble("maxs"));
					dr.setQty_stock(rs.getInt("qty_stock"));
					dr.add();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;	
	}
	

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

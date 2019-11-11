package com.example.demo.generate.db;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;


public class MysqlConnection {

	static Connection conn = null;

	/**
	 * 创建数据库连接
	 * @return
	 */
	public static Connection newConnection() {


		try {
			if(conn == null){
				String url = null;
				String username = null;
				String password = null;
				InputStream stream = MysqlConnection.class.getClassLoader().getResourceAsStream("application.yml");
				InputStreamReader in = new InputStreamReader(stream);
				BufferedReader reader = new BufferedReader(in);
				String line;
				int index = 0;
				while ((line = reader.readLine()) != null) {
					if (Objects.equals(line.trim(), "spring:")) {
						index++;
					} else if (Objects.equals(line.trim(), "datasource:")) {
						index++;
					} else if (url == null && index == 2 && line.trim().startsWith("url:")) {
						url = line.trim().replace("url:", "").trim();
					} else if (username == null && index == 2 && line.trim().startsWith("username:")) {
						username = line.trim().replace("username:", "").trim();
					} else if (password == null && index == 2 && line.trim().startsWith("password:")) {
						password = line.trim().replace("password:", "").trim();
					}
				}
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection() {
		if(conn != null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public static boolean closeConnection(Connection conn) {

		return closeConnection(conn, null, null);
	}

	public static boolean closeConnection(Connection conn, Statement pamStatement,
			ResultSet rs) {
		boolean flag = true;
		try {

			if (rs != null)
				rs.close();
			if (pamStatement != null)
				pamStatement.close();

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} 
		return flag;
	}
}

package com.org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignInDao {
	public static int saveSignIn(int id,String name, String email, String password, String username, int age) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infrotrix", "root",
					"binod");
			String sql = "INSERT INTO user values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, username);
			ps.setInt(6, age);

			int res = ps.executeUpdate();
			con.close();
			return res;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public static int updateUser( String name, String email, String password, String username,int age,int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infrotrix", "root",
					"binod");
			String sql = "UPDATE user SET name=?,email=?,password=?,username=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, username);
			ps.setInt(5, age);
			ps.setInt(6, id);

			int res = ps.executeUpdate();
			con.close();
			return res;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public static int deleteUser(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_employee", "root",
					"binod");
			String sql = "DELETE  FROM user WHERE  id="+id;
			PreparedStatement ps = con.prepareStatement(sql);
			int res = ps.executeUpdate();
			con.close();
			return res;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}
}


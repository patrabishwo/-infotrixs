package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewemp")
public class Display extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/infrotrix?user=root&password=binod");
			String sql = "SELECT *FROM user";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			PrintWriter pw = resp.getWriter();
			pw.write("<html><body>");
			pw.write("<table border=3px>");
			pw.write("<tr>");
			pw.write("<th>ID</th>");
			pw.write("<th>NAME</th>");
			pw.write("<th>EMAIL</th>");
			pw.write("<th>PASSWORD</th>");
			pw.write("<th>USERNAME</th>");
			pw.write("<th>AGE</th>");
			pw.write("<th>EDIT</th>");
			pw.write("<th>DELETE</th>");
			pw.write("</tr>");

			while (rs.next()) {
				pw.write("<tr>");
				pw.write("<td>" + rs.getInt(1) + "</td>");
				pw.write("<td>" + rs.getString(2) + "</td>");
				pw.write("<td>" + rs.getString(3) + "</td>");
				pw.write("<td>" + rs.getString(4) + "</td>");
				pw.write("<td>" + rs.getString(5) + "</td>");
				pw.write("<td>" + rs.getInt(6) + "</td>");
				pw.write("<td><a href=edit?id=" + rs.getInt(1) + ">edit</a></td>");
				pw.write("<td><a href=delete?id=" + rs.getInt(1) + ">delete</a></td>");
				pw.write("</tr>");
			}
			pw.write("</table></body></html>");
			con.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}

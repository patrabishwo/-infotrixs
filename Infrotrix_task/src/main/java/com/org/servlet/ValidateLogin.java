package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class ValidateLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int validate = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infrotrix", "root",
					"binod");
			String sql = "SELECT *FROM user WHERE username=? AND email=? AND password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				validate = 1;
			}
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (validate != 0) {
			PrintWriter pw = resp.getWriter();
			pw.write("<html><body><h1>LOGIN SUCESSFULLY</h1></body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("welcome.html");
			rd.include(req, resp);

		} else {
			PrintWriter pw = resp.getWriter();
			pw.write("<html><body><h1>INVALID USERNAME/EMAIL/PASSWORD</h1></body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("login1.html");
			rd.include(req, resp);

		}

	}

}
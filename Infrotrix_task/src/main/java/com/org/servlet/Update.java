package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.SignInDao;

@WebServlet("/update")
public class Update extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		int age = Integer.parseInt(req.getParameter("deptno"));

		int res = SignInDao.updateUser(name, email, password, username, age, id);

		RequestDispatcher rd = req.getRequestDispatcher("viewemp");
		PrintWriter pw = resp.getWriter();
		if (res != 0) {
			pw.write("<html><body><h1>" + name + "DATA UPDATED SUCCESFULLY</h1></body></html>");
			rd.include(req, resp);
		} else {
			pw.write("<html><body><h1>Something went wrong try again !!!!</h1></body></html>");
			rd.include(req, resp);

		}
	}

}

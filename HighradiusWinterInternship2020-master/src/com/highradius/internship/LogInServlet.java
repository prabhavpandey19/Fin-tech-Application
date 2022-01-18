package com.highradius.internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			DatabaseConnection.initializeDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "select * from user_details";
		ResultSet res = DatabaseConnection.execQuery(sql);
		int flag = 0;
		RequestDispatcher dispatcher = null;
		try {
			while(res.next()) {
				String uname = res.getString("username");
				String pass = res.getString("password");
				if (uname.equals(userName)) {
					if (pass.equals(password)) {
						dispatcher = request.getRequestDispatcher("UserDashboard.jsp");
						request.setAttribute("userLevel", res.getString("user_level"));
						request.setAttribute("pkuserID", "" + res.getInt("pk_user_id"));
						sql = "select count(*) as totalCount from order_details";
						if (res.getString("user_level").equals("Level 1")) {
							sql += " where Customer_Name is not NULL";
						}
						else {
							sql += " where Order_Amount";
							if (res.getString("user_level").equals("Level 2")) {
								sql += " > 10000 and Order_Amount <= 50000 and Customer_Name is not NULL";
							}
							else {
								sql += " > 50000 and Customer_Name is not NULL";
							}
						}
						res.close();
						res = DatabaseConnection.execQuery(sql);
						res.next();
						request.setAttribute("rowCount", res.getString("totalCount"));							
						//dispatcher.forward(request, response);
						this.getServletContext().getRequestDispatcher("/TableServlet.do").forward(request, response);
						break;
					}
					else {
						flag = 1;						
					}
				}
				else {
					flag = 2;					
				}
			}		
			PrintWriter out = response.getWriter();
            dispatcher = request.getRequestDispatcher("index.html");
			if (flag == 1|| flag== 2) {
				dispatcher.include(request, response);				
				String errorMessage = "Invalid Username or Password !";
				/*out.println("<script type='text/javascript'>");
				out.println("alert(" + "'" + errorMessage + "'" + ");</script>");*/
				out.println("<script type=\'text/javascript\'>" + 
						"document.getElementById(\"errorMessageDisplay\").innerHTML = \"" + 
						errorMessage+"\";" + "</script>");
			}
//			else if (flag == 2) {
//				dispatcher.include(request, response);
//				String errorMessage = "Invalid Username !";
//				/*out.println("<script type='text/javascript'>");
//				out.println("alert(" + "'" + errorMessage + "'" + ");</script>");*/
//				out.println("<script type=\'text/javascript\'>" + 
//						"document.getElementById(\"errorMessageDisplay\").innerHTML = \"" + 
//						errorMessage+"\";" + "</script>");
//			}
//			
				
	}
	catch (SQLException e) {
		e.printStackTrace();
	}		
		finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

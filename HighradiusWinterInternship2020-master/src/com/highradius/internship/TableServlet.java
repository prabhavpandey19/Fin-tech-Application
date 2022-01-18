package com.highradius.internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("UserDashboard.jsp").include(request, response);		
		String userLevel = (String)request.getAttribute("userLevel");
		String sql = null;
		
        if (userLevel.equals("Level 1")) {
          sql = "select * from order_details where Customer_Name is not NULL";
        }
        else if (userLevel.equals("Level 2")) {
          sql = "select * from order_details where Order_Amount > 10000 and Order_Amount <= 50000 and Customer_Name is not NULL";
        }
        else {
          sql = "select * from order_details where Order_Amount > 50000 and Customer_Name is not NULL";
        }
        if((String)request.getAttribute("flag") == "1") {
        	
        		sql = sql + (String)request.getAttribute("sql1");
        		request.setAttribute("search", request.getParameter("search"));
        	
        }
        ResultSet res = DatabaseConnection.execQuery(sql); 
        request.setAttribute("res", res);
//        try {
//        while(res.next()) {
//        	out.print("<tr style=\"height:2.5vw;\" class=\"data\"  id=\"rowN" + res.getInt("Order_ID") + ">");
//        	out.print("<td ><input type=\"checkbox\" class=\"checkbox\" onclick=\"highlightRow("+ res.getInt("Order_ID") + " id=\"chkbox\"></td>");
//        	out.print("<td>" + res.getDate("Order_Date")+ "</td>");
//        	out.print("<td>"+ res.getInt("Order_ID")+"</td>");
//        	out.print("<td>"+ res.getString("Customer_Name")+ "</td>");
//        	out.print("<td>"+ res.getInt("Customer_ID") +"</td>");
//        	out.print("<td>"+ res.getInt("Order_Amount") +"</td>");
//        	out.print("<td>"+ res.getString("Approval_Status") +"</td>");
//        	String approvedBy = res.getString("Approved_By");
//            if (approvedBy == null || approvedBy.isEmpty()) {
//            	out.print("<td></td>");
//            }
//            else
//            {
//            	out.print("<td>"+ approvedBy+"</td>");
//            }
//            String notes = res.getString("Notes");
//            if (notes == null || notes.isEmpty()) {
//            	out.print("<td></td>");
//            }
//            else {
//            	out.print("<td>"+ notes +"</td>");
//            }
//        }
//        }
//	
//	catch (SQLException e) {
//		e.printStackTrace();
//	}		
//		finally {
//			try {
//				res.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
        request.getRequestDispatcher("UserDashboard.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

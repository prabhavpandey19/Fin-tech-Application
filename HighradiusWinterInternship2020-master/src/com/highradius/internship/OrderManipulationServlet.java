package com.highradius.internship;

import java.io.IOException;
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
 * Servlet implementation class OrderManipulationServlet
 */
@WebServlet("/OrderManipulationServlet")
public class OrderManipulationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManipulationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected int setCustomerID(String name) {
    	ResultSet res = DatabaseConnection.execQuery("select Customer_Name, Customer_ID from order_details where Customer_Name is not NULL");
    	try {
			while(res.next()) {
				if (res.getString("Customer_Name").equals(name))
					return res.getInt("Customer_ID");
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return GetterClass.getNewCustomerID();
    }
        

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String actionSelected = request.getParameter("button");
		String orderDate, customerName, notes, sql, approvedBy, approvalStatus;
		int customerID, orderID, orderAmount;		
		if (actionSelected.equals("ADD")) {						
			try {				
				orderID = GetterClass.getNewOrderID();				
				orderDate = GetterClass.getDate();
				customerName = request.getParameter("customerName");				
				customerID = setCustomerID(customerName);
				orderAmount = Integer.parseInt(request.getParameter("orderAmount"));
				notes = request.getParameter("notes");
				sql = "insert into order_details values(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmt = DatabaseConnection.con.prepareStatement(sql);
				stmt.setInt(1, orderID);
				stmt.setString(2, customerName);
				stmt.setInt(3, customerID);
				stmt.setInt(4, orderAmount);				
				if (orderAmount <= 10000) {
					approvalStatus = "Approved";
					approvedBy = "David Lee";
				}
				else {
					approvalStatus = "Awaiting Approval";
					approvedBy = null;
				}
				stmt.setString(5, approvalStatus);
				stmt.setString(6, approvedBy);
				stmt.setString(7, notes);
				stmt.setString(8, orderDate);
				int updateStatus = stmt.executeUpdate();
				if (updateStatus > 0) {
					System.out.println("Order added successfully");
				}
				else {
					System.out.println("Order insertion failed");
				}				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end-of-if-ADD
		
		else if (actionSelected.equals("SUBMIT")) {			
			try {				
				orderID = Integer.parseInt(request.getParameter("orderID"));
				orderAmount = Integer.parseInt(request.getParameter("orderAmount"));
				notes = request.getParameter("notes");			
				if (orderAmount <= 10000) {
					approvalStatus = "Approved";
					approvedBy = "David Lee";
				}
				else {
					approvalStatus = "Awaiting Approval";
					approvedBy = null;
				}
				sql = "update order_details set Order_Amount=?, Notes=?, Approved_By=?, Approval_Status=?" +
				"where Order_ID=?";
				PreparedStatement stmt = DatabaseConnection.con.prepareStatement(sql);
				stmt.setInt(1, orderAmount);
				stmt.setString(2, notes);
				stmt.setString(3, approvedBy);
				stmt.setString(4, approvalStatus);
				stmt.setInt(5, orderID);
				int result = stmt.executeUpdate();
				if (result > 0) {
					System.out.println("Update Successfull");
				}
				else {
					System.out.println("Update Failure");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end-of-else-if-SUBMIT/EDIT
		
		else if(actionSelected.equals("APPROVE")) {
			/*System.out.println("Approve button pressed");
			System.out.println("Order ID: " + request.getParameter("orderIdSelected"));
			System.out.println("User Level: " + request.getParameter("userLevel"));*/
			try {
				
				orderID = Integer.parseInt(request.getParameter("orderIdSelected"));
				
				ResultSet res = DatabaseConnection.execQuery("select username from user_details where pk_user_id=" +
				Integer.parseInt(request.getParameter("userID")));
				res.next();
				approvedBy = res.getString("username");
				sql = "update order_details set Approval_Status=\"Approved\", Approved_By=\"" +
				approvedBy + "\", Notes=\"Approved on " + GetterClass.getDate() + "\" where order_ID=" + orderID;
				PreparedStatement stmt = DatabaseConnection.con.prepareStatement(sql);
				int x = stmt.executeUpdate();
				if(x > 0) {
					System.out.println("Order approved");
				}
				else {
					System.out.println("Order approaval failed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end-of-else-if-APPROVE
		
		else {
			//System.out.println("Reject button pressed");
			try {
				orderID = Integer.parseInt(request.getParameter("orderIdSelected"));
				ResultSet res = DatabaseConnection.execQuery("select username from user_details where pk_user_id=" +
				Integer.parseInt(request.getParameter("userID")));
				res.next();
				approvedBy = res.getString("username");
				sql = "update order_details set Approval_Status=\"Rejected\", Approved_By=\"" +
				approvedBy + "\", Notes=\"Rejected on " + GetterClass.getDate() + "\" where order_ID=" + orderID;
				PreparedStatement stmt = DatabaseConnection.con.prepareStatement(sql);
				int x = stmt.executeUpdate();
				if(x > 0) {
					System.out.println("Order rejected");
				}
				else {
					System.out.println("Order rejection failed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end-of-else-if-REJECT
				
//res.next();			
		request.setAttribute("userLevel", request.getParameter("userLevel"));
		request.setAttribute("pkuserID", request.getParameter("userID"));
		//request.setAttribute("rowCount", res.getString("totalCount"));	
		//dispatcher.forward(request, response);
		this.getServletContext().getRequestDispatcher("/TableServlet.do").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

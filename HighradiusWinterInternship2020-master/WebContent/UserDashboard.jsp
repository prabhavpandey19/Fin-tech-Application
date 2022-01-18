<%@page import="com.highradius.internship.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.highradius.internship.GetterClass" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>User DashBoard</title>
    <link rel="stylesheet" href="css/type.css" />
    <link rel="stylesheet" href="css/pagination.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="js/pagination.js"></script>
   
   <%
      String userLevel = (String)request.getAttribute("userLevel");
      String userID = (String)request.getAttribute("pkuserID");
      //int count = Integer.parseInt((String)request.getAttribute("rowCount"));
      int rowcount=0;
    %>
    
   <style>
   
      body {
        font-family: "Roboto";
      	}
     .hRow{
			background-color: #f5c093 !important;
		}	
	.inForm {
			  float: right;
			  padding-right: 17%;
			  border: none;
			  border-bottom: 2px solid rgb(216, 216, 216);
		}

	</style>
		
	
	
<script type="text/javascript">
   		function callsearchservlet(){
   			var input, filter;
   	        input = document.getElementById("sbox");
   	        
   	        filter = input.value.toUpperCase();
   	     	//var xhttp;
   	    	//xhttp = new XMLHttpRequest();
   	        /* document.forms[0].action = "Search";
            document.forms[0].submit(); */
   	        if(filter.length>2){
   	        	/* xhttp.open("POST", 'Search', true);
   	        	xhttp.send();  */
   	        	
   	        	
   	        	
   	        	document.getElementById("sform").action="Search";
   	        	document.getElementById("sform").method="POST";
   	        	document.getElementById("sform").submit(); 
   	        }
   	        
   			
   		}

</script>

</head>




  <body>
 	 
    <div class="header-logo-div">
      
      <a href="index.html"><img src="images/logout.png" alt="logout-logo" class="logout"/></a> 
      
      <img src="images/hrc-logo.svg" alt="highradius-logo" class="hrc-logo" />
      <img src="images/abc-logo.png" alt="highradius-logo" class="abc-logo" />
    </div>
	


<br /><br />
<div id="outborder">
        <div id="bod">
          <div id="addpopup" class="modalDialog">
            <div>
              <a href="#close" title="Close" class="close">X</a>
              <form action="OrderManipulationServlet" method="post">
                <p id="addtxt">
                  <span style="border-bottom: 4px solid rgb(252, 153, 87)">ADD ORDER</span>
                </p>
                <br />
                <div id="poprow">
                  <label id="label">Order ID </label>
                  <input type="text" name="oderID" id="inForm" value="<%=GetterClass.getNewOrderID()%>" readonly/><br /><br />
                  <label id="label">Order Date </label>
                  <input type="text" name="orderDate" id="inForm" value="<%=GetterClass.getDate()%>"readonly/><br /><br />
                  <label id="label"> Customer Name </label>
                  <input type="text" name="customerName" id="inForm" required/><br /><br />
                  <label id="label"> Customer ID</label>
                  <input type="text" name="customerID" id="inForm" value="<%=GetterClass.getNewCustomerID()%>"readonly/><br /><br />
                  <label id="label"> Order Amount </label>
                  <input type="text" name="orderAmount" id="inForm"  required/><br /><br />
                  <label id="label"> Notes</label>
                  <input type="text" name="notes" id="inForm" /><br /><br />
                  <input type="hidden" name="userLevel" value="<%= userLevel %>"/>
                </div><br>
                <center>
                  <input id="informbox" type="submit" name="button" value="ADD" />
                </center><br>
              </form>
            </div>
          </div>

          <div id="editpopup" class="modalDialog">
            <div>
              <a href="#close" title="Close" class="close">X</a>
              <form action="OrderManipulationServlet" method="post">
                <p id="addtxt">
                  <span style="border-bottom: 4px solid rgb(252, 153, 87)" >EDIT ORDER</span>
                </p>
                <br />
                <div id="poprow">
                <label id="label"> Order ID </label>
                <input type="text" name="orderID" class="inForm" id="inFormOrderID" readonly/><br /><br />
                <label id="label"> Order Amount</label>
                <input type="text" name="orderAmount" class="inForm" id="inFormOrderAmount" onchange="approvalByCheck()" /><br /><br />
                <label id="label"> Notes</label>
                <input type="text" name="notes" class="inForm" id="inFormNotes" /><br /><br />
                <label id="label"> Approval By</label>
                <input type="text" name="approvalBy" class="inForm" id="inFormApprovalBy" readonly /><br /><br />
                <input type="hidden" name="userLevel" value="<%= userLevel %>"/>                
                </div><br>
               <center> <input id="informbox" type="submit" name="button" value="SUBMIT" /></center>
               <br><br>
              </form>
            </div>
          </div>
        
<div>    
            <%        
                if (userLevel.equals("Level 1")) { 
            %>  
            <script>const formType= 1;</script>          
                         
               <button id="add" name="add" onclick="location.href='#addpopup'">ADD</button>            
              <input type="button" id="edit" name="edit" onclick="location.href='#editpopup'" value="EDIT" disabled >              
                          
            
            <%  }
                else { 
            %>
            <script>const formType= 2;</script>    
            <form action="OrderManipulationServlet" method="post" id="type2UserData">
              <input type="hidden" name="userLevel" value="<%= userLevel %>" />
              <input type="hidden" name="orderIdSelected" />  
              <input type="hidden" name="userID" value="<%= userID %>" />   
            </form>
            <input type="submit" id="approve" name="button" value="APPROVE" form="type2UserData" disabled>            
            <input type="submit" id="reject" name="button" value="REJECT" form="type2UserData" disabled>
                        
            
            <%  }
            %>
           <!--  <input type="text" id="sbox" name="search" placeholder="search" onkeyup="searchFun()" /> -->
            <!-- <form   id="sform"> -->
             
            <form  action="Search" method="post" id="sform">
            <%
            String val=null;
            if((String)request.getAttribute("search") != null){
            	val=(String)request.getAttribute("search");
            }
            else
            	val="";
            %>
            <input type="text" id="sbox" name="search" placeholder="search" autocomplete="off" value="<%=val %>" onkeyup="callsearchservlet()" autofocus onfocus="this.value = this.value;" />
             <input type="hidden" name="userLevel" value="<%= userLevel %>" /> 
            </form>  
            <br /><br />
          </div>

 <div class="table">
    <table id="dataTable">
      <tr >
        <th></th>
        <th>Order Date</th>
        <th>Order ID</th>
        <th>Customer Name</th>
        <th>Customer ID</th>
        <th>Order Amount</th>
        <th>Approval Status</th>
        <th>Approved By</th>
        <th>Notes</th>
      </tr>
     
     <%
     	ResultSet res = (ResultSet)request.getAttribute("res");
     %>
     
      
      <%      
                 
        while(res.next()) {
        	rowcount++;
        	
      %>
   
        <tr style="height:2.5vw;" class="data"  id="rowN<%= res.getInt("Order_ID") %>">
          <td ><input type="checkbox" class="checkbox" onclick="highlightRow(<%= res.getInt("Order_ID") %>)" id="chkbox"></td>
          <td><%= res.getDate("Order_Date") %></td>
          <td><%= res.getInt("Order_ID") %></td>
          <td><%= res.getString("Customer_Name") %></td>
          <td><%= res.getInt("Customer_ID") %></td>
          <td><%= res.getInt("Order_Amount") %></td>
          <td><%= res.getString("Approval_Status") %></td>
      <%
        String approvedBy = res.getString("Approved_By");
        if (approvedBy == null || approvedBy.isEmpty()) {            
      %>
          <td></td>
      <%
        }
        else {          
      %>
          <td><%= approvedBy %></td>
      <%
        }
        String notes = res.getString("Notes");
        if (notes == null || notes.isEmpty()) {
      %>
          <td></td>
      <%
        }
        else {
      %>
          <td><%= notes %></td>
      <%
        }
        }//end-of-while
      %>
        </tr> 

</table>
 
 
 
<div id="data-record" >
	
	<p>Customers <input id="s_page" readonly>-<input id="e_page" readonly> of <%=rowcount %></p>
	
</div>
	  

<div class="paging-container" id="tablePaging">
	  
	  <div class="pagination">
	    <button data-page="-3" class="disabled">
	<!--      <a href="javascipt:void(0)"><span aria-hidden="true"></span></a>
	    </button>
	    <li data-page="-1" class="disabled">
	      <a href="javascipt:void(0)"><span aria-hidden="true"></span></a>
	    </li>
	    
	    
	    <li data-page="1" class="active"><a href="javascipt:void(0)">1</a></li>
	    
	    
	    
	    <li data-page="-2">	    
	      <a href="javascipt:void(0)"><span aria-hidden="true"></span></a>
	    </li>
	    
	    <li data-page="-4">
	      <a href="javascipt:void(0)"><span aria-hidden="true"></span></a>
	    </li> --> 
	  </div>
	 
	 
	
	  
	   
</div>
    

    
			</div>
        </div>
      
 </div>

	<!-- Search function for searching both orderID and customerName -->
    <script>
	$(function () {
		load = function () {
			
			window.tp = new Pagination('#tablePaging', {
				itemsCount:<%=rowcount%>,
				pageRange: [10, 20, 30, -1],//-1 (All),
				//pageSize: pageRange[0],
				onPageChange: function (paging) {
					//custom paging logic here
					console.log(paging);
					var start = paging.pageSize * (paging.currentPage - 1),
						end = start + paging.pageSize,
						$rows = $('#dataTable').find('.data');

					$rows.hide();

					for (var i = start; i < end; i++) {
						$rows.eq(i).show();
						
					}
					document.getElementById("s_page").value = start+1;
					if(end><%=rowcount%>){
						document.getElementById("e_page").value = <%= rowcount%>;
					}
					else
						document.getElementById("e_page").value = end;
					
				}
			});
		}

		load();
	});
	
    function searchFun() {
        // Declare variables
        var input, filter, table, tr, td, i;
        input = document.getElementById("sbox");
        
        filter = input.value.toUpperCase();
        
        if(filter.length>2){
        table = document.getElementById("dataTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
          td_1 = tr[i].getElementsByTagName("td")[2];
          td_2 = tr[i].getElementsByTagName("td")[3];
          if (td_1 || td_2) {
            if (td_1.innerHTML.toUpperCase().indexOf(filter) > -1 || td_2.innerHTML.toUpperCase().indexOf(filter)> -1) {
              tr[i].style.display = "";
            } else {
              tr[i].style.display = "none";
            }
          }          
        }
        }
        }
    $(document).ready(function() {
    	  
        var input = $("#sbox");
        var len = input.val().length;
        input[0].focus();
        input[0].setSelectionRange(len, len);

    });
    </script>
	
	<!-- For fetching data of the selected row for David Lee -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
    $(function () {
        //Assign Click event to Button.
        $("#edit").click(function () {
            //var message = "";            
 
            //Loop through all checked CheckBoxes in GridView.
            $("#dataTable input[type=checkbox]:checked").each(function () {
                var row = $(this).closest("tr")[0];
                /*message += row.cells[2].innerHTML;
                message += "   " + row.cells[5].innerHTML;
                message += "   " + row.cells[7].innerHTML;
                message += "   " + row.cells[8].innerHTML;
                message += "\n";*/
                document.getElementById('inFormOrderID').value = row.cells[2].innerText;
                document.getElementById('inFormOrderAmount').value = row.cells[5].innerText;
                document.getElementById('inFormNotes').value = row.cells[8].innerText;                
                //document.getElementById('inFormApprovalBy').value = row.cells[7].innerText;
                if (document.getElementById('inFormOrderAmount').value <= 10000) {
    			//document.getElementById('inFormApprovalBy').setAttribute('value', 'David Lee');
    			document.getElementById('inFormApprovalBy').value = 'David Lee';
	    		}
	    		else {
	    			if (document.getElementById('inFormOrderAmount').value <= 50000) {
	    				//document.getElementById('inFormApprovalBy').setAttribute('value', 'Laura Smith');
	    				document.getElementById('inFormApprovalBy').value = 'Laura Smith';
	    			}
	    			else {
	    				//document.getEementById('inFormApprovalBy').setAttribute('value', 'Matthew Vance');
	    				document.getElementById('inFormApprovalBy').value = 'Matthew Vance';
	    			}
	    		}
            });
                        
 
            //Display selected Row data in Alert Box.
            //alert(message);
            //return false;
            
            
        });
    });
    
    
    
    </script>

	<!-- For fetching data of the selected row for Laura & Matthew -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
/* 	$(document).ready(function() {
		var search = (String)request.getAttribute("search");
		if (search != null || search != "") {
			document.getElementById('sbox').value = search;
		}
	}) */
	
    $(function () {
        //Assign Click event to Button.
        $("#approve").click(function () {
                        
 
            //Loop through all checked CheckBoxes in GridView.
            $("#dataTable input[type=checkbox]:checked").each(function () {
                var row = $(this).closest("tr")[0];                
                document.getElementsByName('orderIdSelected')[0].value = row.cells[2].innerText;
            });
        });
    });
    
    $(function () {
        //Assign Click event to Button.
        $("#reject").click(function () {
                        
 
            //Loop through all checked CheckBoxes in GridView.
            $("#dataTable input[type=checkbox]:checked").each(function () {
                var row = $(this).closest("tr")[0];
                document.getElementsByName('orderIdSelected')[0].value = row.cells[2].innerText;                
            });
        });
    });
    </script>
	
    
    <script>
    	function approvalByCheck() {
    		var amount;
    		amount = document.getElementById('inFormOrderAmount').value;
    		if (amount <= 10000) {
    			//document.getElementById('inFormApprovalBy').setAttribute('value', 'David Lee');
    			document.getElementById('inFormApprovalBy').value = 'David Lee';
    		}
    		else {
    			if (amount <= 50000) {
    				//document.getElementById('inFormApprovalBy').setAttribute('value', 'Laura Smith');
    				document.getElementById('inFormApprovalBy').value = 'Laura Smith';
    			}
    			else {
    				//document.getEementById('inFormApprovalBy').setAttribute('value', 'Matthew Vance');
    				document.getElementById('inFormApprovalBy').value = 'Matthew Vance';
    			}
    		}    		
    	}
    </script>
	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="js/type.js"></script>
  </body>
</html>

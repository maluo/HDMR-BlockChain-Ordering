<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders Manager</title>
</head>
<body>
<div align="center">
	<h2>Orders Manager</h2>
	<form method="get" action="search">
		<input type="text" name="keyword" /> &nbsp;
		<input type="submit" value="Search" />
	</form>
	<h3><a href="new">New Order</a></h3>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Order Number</th>
			<th>Item Number</th>
			<th>Unit Price</th>
			<th>Quantity</th>
			<th>Balance</th>
			<th>Shipping</th>
			<th>Sales Price</th>
			<th>Sub Total</th>
			<th>Gross Income</th>
			<th>Transaction Date</th>
			<th>System Date</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${listOrders}" var="order">
		<tr>
			<td>${order.id}</td>
			<td>${order.orderNum}</td>
			<td>${order.unitPrice}</td>
			<td>${order.quantity}</td>
			<td>${order.balance}</td>
			<td>${order.shipping}</td>
			<td>${order.salesPrice}</td>
			<td>${order.subtotal}</td>
			<td>${order.grossIncome}</td>
			<td>${order.transactionDate}</td>
			<td>${order.sysDate}</td>
			<td>
				<a href="edit?id=${order.id}">Edit</a>
				&nbsp;&nbsp;&nbsp;
				<a href="delete?id=${order.id}">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>	
</body>
</html>
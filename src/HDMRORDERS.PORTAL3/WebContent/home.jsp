<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servlet, JSP and Hibernate CRUD Operations</title>
 
<style type="text/css">
    body{
        text-align: center;
    }
    table {
        margin-left: 15%;
        min-width: 70%;
        border: 1px solid #CCC;
        border-collapse: collapse;
        margin: 0 auto;
    }
    table tr{line-height: 30px;}
    table tr th { background: #000033; color: #FFF; padding: 20px;}
    table tr td { border:1px solid #CCC; margin: 5px;}
    input[type=text], input[type=email], input[type=tel]{
        min-width: 60%;
    }
    input[type=submit], a{
        background: green;
        padding: 5px;
        margin: 5px;
        color: #FFF;
    }
    a{
        text-decoration: none;
    }
</style>
</head>
<body>
    <h1>Servlet, JSP and Hibernate CRUD Operations</h1>
    <!--  
    <c:url value="/customer/register" var="registerUrl" />
    <form action="${'${'}registerUrl}" method="post">
        <table>
            <c:if test="${'${'}customer.id ne null}">
                <tr>
                <td>Customer ID:</td>
                    <td><input type="text" name="id" value="${'${'}customer.id}" readonly="readonly"></td>
                </tr>
            </c:if>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" value="${'${'}customer.firstName}" required></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName" value="${'${'}customer.lastName}" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" value="${'${'}customer.email}" required></td>
            </tr>
            <tr>
                <td>Mobile:</td>
                <td><input type="tel" pattern="[789][0-9]{9}" name="mobile" value="${'${'}customer.mobile}" required></td>
            </tr>
 
            <c:if test="${'${'}customer.id ne null}">
                <tr>
                    <td colspan="2"><input type="submit" value="Update"></td>
                </tr>
            </c:if>
            <c:if test="${'${'}customer.id eq null}">
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>
            </c:if>
        </table>
    </form>
    <br>
    -->
    
    <h1>List of Orders</h1>
    
    <table>
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
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>${order.getId()}</td>
				<td>${order.getOrderNum()}</td>
				<td>${order.getItems().getProductId()}</td>
				<td>${order.getUnitPrice()}</td>
				<td>${order.getQuantity()}</td>
				<td>${order.getBalance()}</td>
				<td>${order.getShipping()}</td>
				<td>${order.getSalesPrice()}</td>
				<td>${order.getSubtotal()}</td>
				<td>${order.getGrossIncome()}</td>
				<td>${order.getTransactionDate()}</td>
				<td>${order.getSysDate()}</td>
                 
                <td>
                    <form action="<c:url value="/customer/update"/>" method="post">
                        <input type="hidden" name="custId" value="${'${'}customer.id}">
                        <input type="submit" value="Update">
                    </form>
                <td>
                    <form action="<c:url value="/customer/delete"/>" method="post">
                        <input type="hidden" name="custId" value="${'${'}customer.id}">
                        <input style="background: #F00;" type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
     
</body>
</html>
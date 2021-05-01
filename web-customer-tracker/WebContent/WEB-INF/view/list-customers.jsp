<%@page import="com.sandbox.webcustomertracker.SortBy"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.sandbox.webcustomertracker.SortBy" %>
<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	<link type="text/css"
		rel="stylesheet"
		href="${ pageContext.request.contextPath }/resources/css/style.css" />
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Management</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- add customer -->
			<input type="button" value="Add Customer"
				   onclick="window.location.href='show-form-for-add'; return false;"
				   class="add-button"
			/>
			
			<!-- search customer -->
			<form:form action="search" method="GET">
               	<input type="text" name="theSearchName" placeholder="serach customers" />
                <input type="submit" value="Search" class="add-button" />
            </form:form>
            
            <!-- list customers -->
			<table>
				<!-- construct a sort link for first name -->
				<c:url var="sortByFirstName" value="/customer/list">
					<c:param name="sort" value="${ SortBy.firstName.ordinal() }" />
				</c:url>
				<!-- construct a sort link for first name -->
				<c:url var="sortByLastName" value="/customer/list">
					<c:param name="sort" value="${ SortBy.lastName.ordinal() }" />
				</c:url>
				<!-- construct a sort link for first name -->
				<c:url var="sortByEmail" value="/customer/list">
					<c:param name="sort" value="${ SortBy.email.ordinal() }" />
				</c:url>
				<tr>
					<th><a href="${sortByFirstName}">First Name</a></th>
					<th><a href="${sortByLastName}">Last Name</a></th>
					<th><a href="${sortByEmail}">Email</a></th>
					<th>Action</th>
				</tr>
				<c:forEach var="customer" items="${ customers }">
					<c:url var="updateLink" value="/customer/show-form-for-update">
						<c:param name="customerId" value="${ customer.id }" />
					</c:url>
					<c:url var="deleteLink" value="/customer/delete-customer">
						<c:param name="customerId" value="${ customer.id }" />
					</c:url>
					<tr>
						<td>${ customer.firstName }</td>
						<td>${ customer.lastName }</td>
						<td>${ customer.email }</td>
						<td><a href="${ updateLink }">Update</a> | 
							<a href="${ deleteLink }" 
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">
							Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		
		</div>
	</div>
	
</body>

</html>
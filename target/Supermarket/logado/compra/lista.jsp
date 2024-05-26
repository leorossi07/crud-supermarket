<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>

		<div align="center">
			<h1>
				<fmt:message key="purchases.welcome" />
			</h1>
			<h2>
				<a href="/${sessionScope.contextPath}/compras/cadastro"> 
					<fmt:message key="purchases.create" />
				</a> 
				&nbsp;&nbsp;&nbsp; 
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="exit.link" />
				</a>
			</h2>
			<br />
			<h3><fmt:message key="purchases.list" /></h3>
			<br />
		</div>

		<div align="center">
			<table border="1">
				<caption></caption>
				<tr>
					<th><fmt:message key="purchase.ID" /></th>
					<th><fmt:message key="purchase.date" /></th>
					<th><fmt:message key="purchase.value" /></th>
					<th><fmt:message key="purchase.product.title" /></th>
					<th><fmt:message key="purchase.product.supplier" /></th>
					<th><fmt:message key="purchase.product.category" /></th>
					<th><fmt:message key="purchase.product.amount" /></th>
				</tr>
				<c:forEach var="compra" items="${requestScope.listaCompras}">
					<tr>
						<td>${compra.id}</td>
						<td>${compra.data}</td>
						<td>${compra.valor}</td>
						<td>${compra.produto.titulo}</td>
						<td>${compra.produto.fornecedor.nome}</td>
						<td>${compra.produto.categoria}</td>
						<td>${compra.produto.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>
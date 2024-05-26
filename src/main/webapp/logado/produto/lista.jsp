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
				<fmt:message key="products.welcome" />
			</h1>
			<h2>
				<a href="/${sessionScope.contextPath}/fornecedores">
					<fmt:message key="suppliers.entity" />
				</a>
				&nbsp;&nbsp;&nbsp;
				<a href="/${sessionScope.contextPath}/usuarios"> 
					<fmt:message key="users.entity" />
				</a> 
				&nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/${sessionScope.contextPath}/produtos/cadastro">
					<fmt:message key="products.create" />
				</a>
			</h2>
			<h3><fmt:message key="products.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="product.ID" /></th>
					<th><fmt:message key="product.title" /></th>
					<th><fmt:message key="product.supplier" /></th>
					<th><fmt:message key="product.category" /></th>
					<th><fmt:message key="product.amount" /></th>
					<th><fmt:message key="product.price" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="produto" items="${requestScope.listaProdutos}">
					<tr>
						<td>${produto.id}</td>
						<td>${produto.titulo}</td>
						<td>${produto.fornecedor.nome}</td>
						<td>${produto.categoria}</td>
						<td>${produto.quantidade}</td>
						<td>${produto.preco}</td>
						<td><a href="/${sessionScope.contextPath}/produtos/edicao?id=${produto.id}">
								<fmt:message key="products.update" />
						</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/${sessionScope.contextPath}/produtos/remocao?id=${produto.id}"
							onclick="return confirm('<fmt:message key="confirm.link" />');">
								<fmt:message key="products.delete" />
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</body>
</fmt:bundle>

</html>
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
				<fmt:message key="suppliers.welcome" />
			</h1>
			<h2>
				<a href="/${sessionScope.contextPath}/produtos"> 
					<fmt:message key="products.entity" />
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
				<a href="/${sessionScope.contextPath}/fornecedores/cadastro">
					<fmt:message key="suppliers.create" />
				</a> 
			</h2>
			<h3><fmt:message key="suppliers.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="supplier.ID" /></th>
					<th><fmt:message key="supplier.CNPJ" /></th>
					<th><fmt:message key="supplier.name" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="fornecedor" items="${requestScope.listaFornecedores}">
					<tr>
						<td><c:out value="${fornecedor.id}" /></td>
						<td><c:out value="${fornecedor.CNPJ}" /></td>
						<td><c:out value="${fornecedor.nome}" /></td>
						<td><a
							href="/${sessionScope.contextPath}/fornecedores/edicao?id=<c:out value='${fornecedor.id}' />">
								<fmt:message key="suppliers.update" />
						</a> <c:if test="${fornecedor.qtdeProdutos == 0}">
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/${sessionScope.contextPath}/fornecedores/remocao?id=<c:out value='${fornecedor.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="suppliers.delete" />
								</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>
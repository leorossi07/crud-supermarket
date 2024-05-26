<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1" style="width: 400px; border: 1px solid black">

	<tr>
		<th></th>
		<th>Título</th>
		<th>Fornecedor</th>
		<th>Categoria</th>
		<th>Quantidade</th>
		<th>Preço</th>
	</tr>
	<c:forEach var="produto" items="${produtos}">
		<tr>
			<td style="width: 10%; text-align: center"><input type="radio"
				id="${produto.key}" name="produto" value="${produto.key}" required></td>
			<td>${produto.value.titulo}</td>
			<td>${produto.value.fornecedor.nome}</td>
			<td>${produto.value.categoria}</td>
			<td>${produto.value.quantidade}</td>
			<td>${produto.value.preco}</td>
		</tr>
	</c:forEach>
</table>
<br/>
<br/>

<tr>
	<td colspan="2" align="center"><input type="submit"
		value="<fmt:message key="save.link" />" /></td>
</tr>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${produto != null}">
				<fmt:message key="products.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="products.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${produto != null}">
		<input type="hidden" name="id" value="${produto.id}" />
	</c:if>
	<tr>
		<td><label for="titulo"> <fmt:message key="product.title" />
		</label></td>
		<td><input type="text" id="titulo" name="titulo" size="45"
			required value="${produto.titulo}" /></td>
	</tr>
	<tr>
		<td><label for="categoria"> <fmt:message key="product.category" />
		</label></td>
		<td><input type="text" id="categoria" name="categoria" size="45" required
			value="${produto.categoria}" /></td>
	</tr>
	<tr>
		<td><label for="fornecedor"> <fmt:message
					key="product.supplier" />
		</label></td>
		<td><select name="fornecedor">
				<c:forEach items="${fornecedores}" var="fornecedor">
					<option value="${fornecedor.key}"
						${produto.fornecedor.nome==fornecedor.value ? 'selected' : '' }>
						${fornecedor.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td><label for="quantidade"> <fmt:message key="product.amount" />
		</label></td>
		<td><input type="number" id="quantidade" name="quantidade" size="5" required
			min="1500" value="${produto.quantidade}" /></td>
	</tr>
	<tr>
		<td><label for="preco"> <fmt:message key="product.price" />
		</label></td>
		<td><input type="number" id="preco" name="preco" required
			min="0.01" step="any" size="5" value="${produto.preco}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>
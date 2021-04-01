<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<tags:master pageTitle="Product List">
    <form:form action="${pageContext.request.contextPath}/products/add" method="post" modelAttribute="product">
        Code <input type="text" name="code">
        <form:errors path="code" cssStyle="color: red"/>
        <br>
        Description <input type="text" name="description">
        <form:errors path="description" cssStyle="color: red"/>
        <br>
        Price <input type="text" name="price">
        <form:errors path="price" cssStyle="color: red"/>
        <br>
        Stock <input type="text" name="stock">
        <form:errors path="stock" cssStyle="color: red"/>
        <br>
        ImageUrl <input type="text" name="imageUrl">
        <form:errors path="imageUrl" cssStyle="color: red"/>
        <br>
        <button>Create</button>
    </form:form>
</tags:master>
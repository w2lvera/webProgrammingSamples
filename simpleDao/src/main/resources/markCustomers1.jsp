<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false"%>
<!DOCTYPE html>
<head>
        <title>Spring MVC view layer: Thymeleaf vs. JSP</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" media="all"
          href="../../static/styles.css" th:href="@{/static/styles.css}"/>
</head>
<body>
<h1><%= "Hello World!" %>

<form method="post" action = "./today">

   <h1> Заполняем результат за ${serverTime} </h1>

   <ul>
   <c:forEach var="customer" items="${customers}">
       <li>${customer.name}  <input type ="checkbox" name = "${customer.id}"/></li>
   </c:forEach>
   </ul>
   <!--
    <ul>
        <li th:each="customer:${customers}">
            <label th:text = "${customer.name}"></label>
            <input type ="checkbox" th:name = "${customer.id}"/>
        </li>
    </ul>
    -->

    <input type ="submit"/>
</form>

</h1>
<br/>
<a href="hello">Hello Servlet</a>
</body>
</html>
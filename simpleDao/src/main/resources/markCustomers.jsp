<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Customers today</title>
    <title>Spring MVC view layer: Thymeleaf vs. JSP</title>

            <link rel="stylesheet" type="text/css" media="all"
              href="../../static/styles.css" th:href="@{/static/styles.css}"/>
</head>
<body>
<h1><%= "Hello World!" %>
<form method="post" th:action = "@{today}">
   <h1> Заполняем результат за ${serverTime}</h1>
<h2> ${customers.get(1).name}</h2>
    <ul>
       <li th:each="customer : ${customers}">
            <label th:text = "${customer.name}">...</label>
            <input type ="checkbox" th:name = "${customer.id}"/>
        </li>
    </ul>

    <input type ="submit"/>
</form>

</h1>
<br/>
<a href="hello">Hello Servlet</a>
</body>
</html>
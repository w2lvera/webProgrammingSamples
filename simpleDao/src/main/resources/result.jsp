<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
   <meta charset="UTF-8"/>
   <title>Thymeleaf Example as index</title>
   <link rel="stylesheet" th:href="@{/static/styles.css}" type="text/css"/>
</head>
<body>
<h1> Pезультат на  ${serverTime} </h1>
 <div> Вчера было
 <h3>"${yesterday}"      "${inputString}"</h3>
 <p><h4>"${logList.get(0)}"</h4></p>
   <!--  <th:block th:text="${yesterday}"></th:block>-->
 </div>
 <div class="main">
     Баланс на сегодня:
     <h3>"${now}"</h3>
    <!-- <th:block th:text="${now}"></th:block>-->
 </div>
</body>
</html>

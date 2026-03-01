<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
   <meta charset="UTF-8"/>
   <link rel="stylesheet" type="text/css" media="all"
             href="../../static/styles.css" />
</head>
<body>
<h1> Pезультат на  ${serverTime} </h1>
 <div> Вчера было
 <h3>${yesterday}</h3>
 <!-- <p><h4>${logList.get(0)}</h4></p> -->

 </div>
 <div class="main">
     Баланс на сегодня:
     <h3>${now}</h3>

 </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Добро пожаловать, ${firstName} ${lastName}
        <br/>
        <br/>
        Please do select for operation / Пожалуйста, выберите операцию:
        <br/>
        <img src="bankomat.jpg"/>
    </div>
</div>
    <br/>
    <a href="/addMoney"> Внести деньги на счет </a>
    <br/>
    <br/>
    <a href="/getMoney"> Снять деньги со счета </a>
     <br/>
     <br/>
     <a href="/getStatus"> Получить данные по счетам </a>
    <br/>
    <p>Перейти к
       <a href="/persons"> списку клиентов</a>
    </p>
   <br/>
<a href="/logout">Выход</a> <img src="exit.jpg"/>
</body>
</html>
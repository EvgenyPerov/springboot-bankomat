<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Страница входа</title>
</head>
<body>
<c:if test="${error == true}">
    <p>Input error!</p>
    <br/>
</c:if>

<div class="form-style-2">
     <p>
     <font size="20" color="red" face="Arial Black">БАНКОМАТ</font>
     </p>
    <div class="form-style-2-heading">
        Please Login!
    </div>
<br/>

    <form method="post" action="/login">
        <label for="login">Логин
                   <input class="input-field" type="text" id="login" name="login">
         </label>
        <br/>
        <br/>
        <label for="password">Пароль
                    <input class="input-field" type="password" id="password" name="password">
        </label>
        <br/>
        <br/>
               <input type="submit" value="Войти">
    </form>

</div>
<br/>
<br/>
    <p>Перейти к
<a href="/signUp"> регистрации</a>
    </p>

</body>
</html>
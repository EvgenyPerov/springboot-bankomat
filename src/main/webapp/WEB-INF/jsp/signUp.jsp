<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Форма регистрации нового клиента</title>
</head>

<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>

     <form method="post" action="/signUp">
     <br/>
     <br/>
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
            <label for="firstName">Имя
                <input class="input-field"  id="firstName" name="firstName">
            </label>
            <br/>
            <br/>
            <label for="lastName">Фамилия
                <input class="input-field"  id="lastName" name="lastName">
            </label>
            <br/>
            <br/>
            <input type="submit" value="Зарегистрироваться">

            <br>
     </form>
     </div>
     <a href="/login"> Войти в систему</a>
</body>
</html>

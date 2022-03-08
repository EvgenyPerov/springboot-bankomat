<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Get money from deposit</title>
    <style>
           table {
             border: 3
             font-family: arial, sans-serif;
             border-collapse: collapse;
           }
           td, th {
                       border: 1px solid black;
                       text-align: left ;
                       padding: 8px;
                   }
                   tr:nth-child(even) {
                               background-color: grey;
                           }
           select {
             border: 3;
             background: #FF8500;
             color: #fff;
             outline: none;
             -webkit-appearance: none;
             -moz-appearance: none;
             text-indent: 1px;
             text-overflow: "";
             width: 40px;
             height: 17px;
           }
           select::-ms-expand {
             display: none;
           }

           input[type=text] {
                      padding: 8px 8px 8px 8px;
                      background: #FF8500;
                      color: #fff;
                      width: 100px;
                      height: 12px;
           }
           input[class=input-field] {
                       padding: 8px 10px 8px 10px;
                       background: #FF8500;
                       color: #fff;
                       width: 100px;
                       height: 12px;
           }

         </style>
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Уважаемый/ая, ${firstName} ${lastName}
        <br/>
        <img src="getMoney.jpg"/>
        <br/>
        Please do select for operation / Пожалуйста, выберите валюту:
    </div>
</div>
    <br/>
    <br/>
    <form method="post" action="/getMoney">
         <label for="currency">Валюта
              <select class="selectpicker" id ="currency" name ="currency">
                   <c:forEach  items="${currencies}" var ="currency">
                       <option value = "${currency}"> ${currency} </option>
                   </c:forEach>
              </select>
         </label>
         <br/>
         <br/>
         <label for="amount">Сумма
              <input class="input-field" type="text" id="amount" name="amount">
         </label>
         <br/>
         <br/>
         <button class="btn btn-primary" type="submit"> Выбрать</button>
    </form>
    <br/>
    <br/>
<a href="/welcome">Назад</a>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title> Статус </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"/>
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
         width: 70px;
         height: 100%;
       }
       select::-ms-expand {
         display: none;
       }

       input[type=text] {
                  padding: 8px 8px 8px 8px;
                  background: #FF8500;
                  color: #fff;
                  width: 30px;
                  height: 30%;
       }
       input[class=input-field] {
                   padding: 8px 10px 8px 10px;
                   background: #FF8500;
                   color: #fff;
                   width: 50px;
                   height: 70%;
       }

     </style>
  </head>

  <body>

    <div class="form-style-2-heading">
        Уважаемый/ая, ${firstName} ${lastName}
        <br/>
        <img src="status.jpg"/>
        <br/>
        <br/>
        Статус по вашим счетам:
        <br/>
    </div>
        <table>
            <tr>
               <th>#</th>
               <th> Валюта </th>
               <th width="70"> Сумма </th>
            </tr>
            <c:forEach  items="${depositsListFromServer}" var ="deposit">
            <tr>
               <td> "${depositsListFromServer.indexOf(deposit) + 1}"  </td>
               <td>${deposit.currency}</td>
               <td>${deposit.amount}</td>
            </tr>
            </c:forEach>
        </table>
    <br/>
    <br/>
    <p>Перейти на
        <a href="/welcome"> домашнюю страницу </a>
    </p>

  </body>
</html>
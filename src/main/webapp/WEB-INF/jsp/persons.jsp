<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Clients</title>
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

<br/>
<img src="clients.jpg"/>
<br/>
     <form method="post" action="/persons">
        <label for="id">#
             <select class="select" id ="id" name ="id">
                             <c:forEach  items="${ids}" var ="id">
                                 <option value = "${id}"> ${id} </option>
                                 </c:forEach>
                             </select>
        </label>

         <label for="role">Роль
             <select class="selectpicker" id ="role" name ="role">
                 <c:forEach  items="${roles}" var ="role">
                     <option value = "${role}"> ${role} </option>
                     </c:forEach>
                 </select>
         </label>

         <label for="state">Статус
              <select class="select" id ="state" name ="state">
                  <c:forEach  items="${states}" var ="state">
                         <option value = "${state}"> ${state} </option>
                  </c:forEach>
               </select>
             </label>

         <button class="btn btn-primary" type="submit"> Принять изменения </button>
     </form>

     <br/>

        <table>

            <tr>
               <th>#</th>
               <th>Логин</th>
               <th>Имя</th>
               <th>Фамилия</th>
               <th width="70"> Роль </th>
               <th width="70"> Статус</th>
            </tr>
        <c:forEach  items="${personsFromServer}" var ="person">
            <tr>
               <td>${person.id}</td>
               <td>${person.login}</td>
               <td>${person.firstName}</td>
               <td>${person.lastName}</td>
               <td>
                  ${person.role}
               </td>
               <td>${person.state}</td>
            </tr>
        </c:forEach>

        </table>

    <br/>
    <br/>
    <p>Перейти на
   <a href="/welcome"> домашнюю страницу </a>
       </p>
   <br/>
   <br/>

    <div class="form-style-2-heading">
              ${myProperty}
    </div>

  </body>
</html>
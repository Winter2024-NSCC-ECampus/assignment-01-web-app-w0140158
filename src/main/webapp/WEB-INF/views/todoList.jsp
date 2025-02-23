<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Todo List</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    th, td { border: 1px solid black; padding: 10px; text-align: left; }
    th { background-color: #f2f2f2; }
    a, button { margin-top: 10px; display: inline-block; }
  </style>
</head>
<body>
  <h2>Todo List</h2>
  <c:choose>
    <c:when test="${empty todos}">
      <p>No todos found. Add a new one below.</p>
    </c:when>
    <c:otherwise>
      <table>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Description</th>
        </tr>
        <c:forEach var="todo" items="${todos}">
          <tr>
            <td>${todo.id}</td>
            <td>${todo.title}</td>
            <td>${todo.description}</td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
  <br>
  <a href="${pageContext.request.contextPath}/todos/new">Add New Todo</a>
  <form action="${pageContext.request.contextPath}/todos" method="get" style="display:inline;">
    <button type="submit">Refresh List</button>
  </form>
</body>
</html>

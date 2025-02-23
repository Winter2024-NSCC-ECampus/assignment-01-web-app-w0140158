<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add New Todo</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    form { width: 300px; }
    label { display: block; margin-top: 10px; }
    textarea, input { width: 100%; padding: 8px; margin-top: 5px; }
    button { margin-top: 15px; }
  </style>
</head>
<body>
  <h2>Add New Todo</h2>
  <c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
  </c:if>
  <form action="${pageContext.request.contextPath}/todos" method="post">
    <label>Title:</label>
    <input type="text" name="title" required>
    <label>Description:</label>
    <textarea name="description"></textarea>
    <button type="submit">Add Todo</button>
  </form>
  <br>
  <a href="${pageContext.request.contextPath}/todos">Go Back!</a>
</body>
</html>

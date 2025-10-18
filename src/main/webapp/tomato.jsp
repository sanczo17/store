<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Pomidory</title>
</head>
<body>

<h1>Oto dział w pomidorami:</h1>

<form action="${pageContext.request.contextPath}/add-to-basket" method="post">
  <input type="hidden" name="item" value="pomidor">
  <label for="quantity">Ilość:</label>
  <input type="number" id="quantity" name="quantity" value="1" min="1">
  <input type="submit" value="Kup pomidora">
</form>

<form action="${pageContext.request.contextPath}/index.jsp" method="get">
  <input type="submit" value="Rozmyśliłem się">
</form>

</body>
</html>
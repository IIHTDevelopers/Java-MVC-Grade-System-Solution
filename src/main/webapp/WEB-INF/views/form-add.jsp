<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Form</title>
  </head>
<body>
      <a href="${pageContext.request.contextPath}/grades">Grade List</a>&nbsp&nbsp&nbsp&nbsp&nbsp
      <a href="${pageContext.request.contextPath}/form">Form</a>
      <h2> Grade Portal </h2>
      <form:form method="post" modelAttribute="grade" action="/handleSubmit">
      <form:hidden path="id"/>
       Name : <form:input placeholder="Name" path="name" /> <form:errors path="name" /> <br>
       Score (A,B,C,D or F) : <form:input placeholder="Score" path="score" /> <form:errors path="score" /> <br>
       Subject : <form:input placeholder="Subject" path="subject" /> <form:errors path="subject" /> <br>
        <br><br>
        <input type="submit" value="Submit">
      </form:form>
</body>
</html>
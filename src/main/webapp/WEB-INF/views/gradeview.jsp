<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>
  <head> 
    <title>Grades List</title>
  </head>
  <body>
        <a href="/grades">Grade List</a>&nbsp&nbsp&nbsp&nbsp&nbsp
        <a href="/form">Form</a>

      <h2 > Grade List </h2>

             <h2> Search Grades </h2>
                  <form:form action="/search?page=0&size=5" method="POST">
    			    <input type="text" placeholder="Search By Name/Subject" name="theSearchName" value = "${theSearchName}">
                    <select name="searchGrade">
                       <c:forEach var="item" items="${availableGrades}">
                             <option value="${item}" ${item == searchGrade ? 'selected="selected"' : ''}>${item}</option>
                       </c:forEach>
                    </select>
                     <input type="submit" value="Search"/>
      </form:form>

      <table border = "1">
        <tr>
         <th> Sno. </th>
          <th>Student
	        &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=name,desc"> Desc </a>
            &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=name"> Asc </a>
          </th>
          <th>Subject
	        &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=subject,desc"> Desc </a>
            &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=subject"> Asc </a>
          </th>
          <th>Score
	        &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=score,desc"> Desc </a>
            &nbsp &nbsp <a href= "/grades?page=0&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=score"> Asc </a>
          </th>
          <th>Action</th>
        </tr>
        <c:set var="index" value="${page * 5 + 1}" />
        <c:forEach var="grade" items="${grades}">
            <tr>
            <td>${index}</td>
          <td>${grade.name}</td>
          <td>${grade.subject}</td>
          <td>${grade.score}</td>
          <td> <a href="/form?id=${grade.id}">Update</a> </td>
          <c:set var="index" value="${index + 1}" />
                  </tr>

        </c:forEach>
      </table>
	<br><br>
        	<c:choose>
                <c:when test="${totalPage == 0}">
                    No Record Found
                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                            &nbsp &nbsp<a href="/grades?page=${loop.index}&size=5&theSearchName=${theSearchName}&searchGrade=${searchGrade}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

  </body>
</html>
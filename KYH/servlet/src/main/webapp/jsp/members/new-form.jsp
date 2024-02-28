<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 이 파일은 정적 컨텐츠니까 경로(basic) 및 파일 확장자(.jsp) 다 붙여줘야 함. -->
<!-- http://localhost:8080/jsp/members/new-form.jsp -->

    <form action="/jsp/members/save.jsp" method="post">
        username: <input type="text" name="username" />
        age: <input type="text" name="age" />
        <button type="submit">전송</button>
    </form>
</body>
</html>
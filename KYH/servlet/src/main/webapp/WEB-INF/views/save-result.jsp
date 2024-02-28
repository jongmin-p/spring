<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <%-- 컨트롤러인 MvcMemberSaveServlet.java 에서  Model 에 담은 데이터를 꺼내와서 화면에 출력하자 --%>

        <%--
            <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>
            <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>
            <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>

            ↑↑↑ 복잡하지? 그래서 아래처럼 그냥 ${} 사용 가능
        --%>

        <%-- MvcMemberSaveServlet.java 에서 request.setAttribute("member", member); 코드가 Model 에 데이터 담은 거임 --%>
        <li>id=${member.id}</li>
        <li>username=${member.username}</li>
        <li>age=${member.age}</li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
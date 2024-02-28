<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // request, response 는 JSP 에서는 그냥 사용 가능

    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

    // 1. 이젠 FORM 태그(MemberFormServlet 파일 참고) 에 있던 값을 가져와서 읽어야 함.
    String username = request.getParameter("username");
    // request.getParameter 로 받는 것들은 전부 문자이니까 int 로 바꾸기
    int age =  Integer.parseInt(request.getParameter("age"));

    // 2. 이제 Member 객체 생성을 하는데, 생성할 때는 받아온 값으로 설정
    Member member = new Member(username, age);

    // 3. 위에서 생성한 Member 객체를 저장
    memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
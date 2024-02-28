package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    // 싱글톤이라 new 안 됨.       (저장하기 위해선 MemberRepository 가 필요함)
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 이젠 FORM 태그(MemberFormServlet 파일 참고) 에 있던 값을 가져와서 읽어야 함.
        String username = request.getParameter("username");
        // request.getParameter 로 받는 것들은 전부 문자이니까 int 로 바꾸기
        int age =  Integer.parseInt(request.getParameter("age"));

        // 2. 이제 Member 객체 생성을 하는데, 생성할 때는 받아온 값으로 설정
        Member member = new Member(username, age);

        // 3. 위에서 생성한 Member 객체를 저장
        memberRepository.save(member);


        // 4. Model 에 데이터를 보관해야 함       (name 이 key 가 됨.)
        request.setAttribute("member", member);

        // 5. 이제 View 로 넘어가야 함.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);      // 내부에서 호출
    }
}
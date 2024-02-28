package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // 싱글톤이라 new 안 됨.       (저장하기 위해선 MemberRepository 가 필요함)
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");

        // 1. 이젠 FORM 태그(MemberFormServlet 파일 참고) 에 있던 값을 가져와서 읽어야 함.
        String username = request.getParameter("username");
        // request.getParameter 로 받는 것들은 전부 문자이니까 int 로 바꾸기
        int age =  Integer.parseInt(request.getParameter("age"));

        // 2. 이제 Member 객체 생성을 하는데, 생성할 때는 받아온 값으로 설정
        Member member = new Member(username, age);

        // 3. 위에서 생성한 Member 객체를 저장
        memberRepository.save(member);

        // 4. 저장이 잘 됐는지 보자 (응답을 HTML 코드로 내려보자)
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        w.write (
                    "<html>\n" +
                    "<head>\n" +
                    " <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "성공\n" +
                    "<ul>\n" +
                                            // id 는 sequence 자동 증가하게 만들어 놨음
                    " <li>id = " + member.getId() + "</li>\n" +
                    " <li>username = " + member.getUsername() + "</li>\n" +
                    " <li>age = " + member.getAge() + "</li>\n" +

                    "</ul>\n" +
                    "<a href=\"/index.html\">메인</a>\n" +
                    "</body>\n" +
                    "</html>"
        );
    }
}
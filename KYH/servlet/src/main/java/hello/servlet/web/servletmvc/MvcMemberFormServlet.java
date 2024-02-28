package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 사실 회원 등록 폼 같은 경우에는, 보다시피 아무런 로직이 없다.
        // 하지만, 반드시 Controller 를 거쳐서 View 로 이동해야 하기 때문에, 아래처럼 간단한 코드만 작성함.

        // 특히, WEB-INF 디렉토리 안에 있는 jsp 들은 외부에서(URL으로) 직접 접근할 수 없음. 반드시 Controller 거쳐야 함.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        
        // Controller 에서 View 로 이동할 때 사용 (getRequestDispatcher)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);        // forward() 를 호출하면, 다른 서블릿이나 JSP 를 호출. 즉, 서버 내부에서 다시 호출이 발생함.

    }
}
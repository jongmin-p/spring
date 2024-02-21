package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 웹 브라우저가 WAS 서버로 요청 (request)
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // WAS 서버가 웹 브라우저로 응답 (response)
        response.setContentType("text/plain");  // 단순 문자.    HTTP 헤더에 들어감
        response.setCharacterEncoding("utf-8"); // HTTP 헤더에 들어감
        response.getWriter().write("hello, " + username + "!");       // HTTP 바디에 데이터가 들어감
    }
}
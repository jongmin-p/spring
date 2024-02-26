package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")       // http://localhost:8080/response-json
public class ResponseJsonServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 객체를 JSON 으로 바꾸기 위해 필요
        ObjectMapper objectMapper = new ObjectMapper();


        // Content-Type: application/json
        response.setContentType("application/json");        // HTTP 응답으로 JSON 을 반환할 때는 application/json
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);


        // 위의 정보들(객체) 을 JSON 으로 변환하기 위해서는 ObjectMapper 필요함.       {"username": "kim","age": 20}
        String result = objectMapper.writeValueAsString(helloData);

        // 응답 메시지 (출력해보기)
        response.getWriter().write(result);
    }
}
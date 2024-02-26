package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // JSON 을 객체로 변환하기 위해 필요한 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();                          // 메시지 바디의 내용을 바이트 코드로 바로 얻을 수 있음.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바이트 코드를 string 으로 변환

        System.out.println("messageBody = " + messageBody);                                 // JSON 도 그냥 문자열


        // JSON 을 HelloData.class (즉, 객체) 로 변환 (라이브러리 필요)
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        // JSON 이 객체로 변환됐는지 확인
        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());
        
        response.getWriter().write("Ohhh okay");
    }
}
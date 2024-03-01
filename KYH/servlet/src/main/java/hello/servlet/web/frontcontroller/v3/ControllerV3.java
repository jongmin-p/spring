package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {

    // MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    // 위의 V2 랑 비교해보면, 아래의 ControllerV3 는 우리가 만든 프레임워크에 종속적인 거지 서블릿에 종속적이지 않음

    ModelView process(Map<String, String> paramMap);
}
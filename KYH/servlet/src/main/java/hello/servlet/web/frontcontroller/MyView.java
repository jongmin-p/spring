package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

public class MyView {

    private String viewPath;            // "/WEB-INF/views/new-form.jsp" 등이 됨

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // "Model 추가 - v3" 강의에서 추가된 render 메서드.
    // 파라미터 Map<String, Object> model 이 추가됐음.
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. model 에 있는 데이터를 전부 꺼내서, request.setAttribute 에 넣음(왜? .jsp 쓰니까)   (메서드 따로 생성)
        modelToRequestAttribute(model, request);

        // 2. jsp 로 이동. 그러면 jsp 가 렌더링 됨.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
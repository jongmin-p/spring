package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
            // 기존 코드    (MemberFormControllerV1)

            String viewPath = "/WEB-INF/views/new-form.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
            dispatcher.forward(request, response);
        */

        // 이제는 그냥 생성자에 viewPath 를 넣어주면 됨.
        // MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        // return myView;
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 이젠, view 의 물리 이름, 즉 전체 경로(/WEB-INF/views/new-form.jsp) 를 넣는 게 아니라, 논리 이름(new-form) 만 넣음
        return new ModelView("new-form");
    }
}
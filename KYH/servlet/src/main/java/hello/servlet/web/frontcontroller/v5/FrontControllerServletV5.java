package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // private Map<String, ControllerV4> controllerMap = new HashMap<>();       // 기존꺼
    private final Map<String, Object> handlerMappingMap = new HashMap<>();      // 아무 컨트롤러(V3, V4 등) 다 지원하게 Object
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 위에서 만든 것들에다 값 넣어주기


    public FrontControllerServletV5() {
        // 매핑 정보
        initHandlerMappingMap();

        initHandlerAdapters();
    }


    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. getHandler 메서드 내부에서 "핸들러" 찾음 (호출).
        // 현재는 MemberFormControllerV3 가 반환됨.
        Object handler = getHandler(request);

        // 2-1. 근데 만약 해당 URL 주소가 매핑 정보에 없으면, 404 에러 반환 후 그냥 리턴
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        // 2-2. 반복문 돌려서, "핸들러 어댑터" 를 가져옴/찾음
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3. 그 다음에 handle 을 호출함 (요청 등등 전부 파라미터로 보냄). 그러면 adapter.handle 내부에서 ModelView 를 반환해 줌.
        ModelView mv = adapter.handle(request, response, handler);

        // 그 다음에, 이제 viewName 을 가져와서 viewResolver 를 호출해 줌.

        // 4-1. 이제 논리 이름을 물리 이름으로 바꾸기
        String viewName = mv.getViewName();         // 논리 이름인 new-form 반환 받음

        // 4-2. 아래의 코드는 논리 이름인 new-form 을 가지고 viewResolver 메서드 (따로 만든 메서드) 를 호출함
        // 그러면, new MyView("/WEB-INF/views/" + viewName + ".jsp"); 를 반환해 줌.
        // 즉, 실제 물리 이름인 "/WEB-INF/views/new-form.jsp" 이렇게 만들어 줌.
        MyView view = viewResolver(viewName);

        // 5. render() 를 호출하면서 모델도 같이 넘겨줘야 함.   왜? view 가 렌더링 하려면 Model 정보가 필요함.
        // MyView.java 에다가, 파라미터 Map<String, Object> model 이 추가된 public void render() 를 하나 더 추가해야 함.
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        // 1-1. 요청이 오면
        String requestURI = request.getRequestURI();

        // 1-2. handlerMappingMap 에서 handler 찾음
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;

        for (MyHandlerAdapter adapter : handlerAdapters) {
            // 만약, adapter 가 handler 를 지원하면, adapter 를 반환
            if (adapter.supports(handler)) {
                return adapter;
            }
        }

        // 해당 핸들러 어댑터가 없는 경우, 즉, 핸들러 어댑터를 못 찾은 경우 예외 던짐
        throw new IllegalArgumentException("핸들러 어댑터를 찾을 수 없습니다.  handler = " + handler);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
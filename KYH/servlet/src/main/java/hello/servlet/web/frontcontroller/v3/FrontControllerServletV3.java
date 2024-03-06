package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// URL 경로 중요 (urlPatterns = "/front-controller/v3/*")
// v3 하위의 어떤 URL 이 들어와도, 일단 이 서블릿이 호출됨.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 매핑(Mapping) 정보 만들기
    private Map<String, ControllerV3> controllerMap = new HashMap<>();  // 여기서 key(String) 는 URL

    // 매핑(Mapping) 정보를 생성자에 담아둘거임. (이 서블릿이 생성될 때 세팅되도록)
    // 근데 new FrontControllerServletV3() 하는 곳이 있어야 생성자 내부 코드가 실행되는 것 아님?
    // new FrontControllerServletV3() 한 곳이 없는데, 생성자가 1번 호출됨. (이유가 뭐지?)
    // -> 그냥, HttpServlet 를 상속 받아야 하는 서블릿에서는 기본 생성자가 반드시 있어야 하고,
    // 이 기본 생성자는 자동으로 1번 실행되는 듯 (이게 서블릿 규칙)
    public FrontControllerServletV3() {
        System.out.println("생성자 호출 - FrontControllerServletV333");           // 서버 실행 시, 1번 호출됨. (이 생성자가 실행됐다는 의미. 이유 모름)
        // -> 그냥, HttpServlet 를 상속 받아야 하는 서블릿에서는 반드시 기본 생성자가 있어야 하고, 이 기본 생성자는 자동으로 1번 실행되는 듯 (이게 서블릿 규칙)

                                // ┌> 얘가 호출이 되면,                        ┌> 얘가 실행됨
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)     throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        // 1. request.getRequestURI() 를 쓰면 /front-controller/v3/members/new-form 등등 문자열을 뽑아냄.
        String requestURI = request.getRequestURI();

        // 2. request.getRequestURI() 를 통해 뽑아낸 URL 주소가 생성자에서 설정한 것 중에 있다면, 해당 객체 인스턴스를 반환받음.
        ControllerV3 controller = controllerMap.get(requestURI);

        // 3-1. 근데 만약 해당 URL 주소가 없으면, 404 에러 반환 후 그냥 리턴
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        // 이제 ModelView 로 수정 (v3)

        // 3.1 createParamMap() 을 가지고, HttpServletRequest 에 있느 파라미터들을 다 뽑아서 반환함. (메서드로 따로 만들었음)
        Map<String, String> paramMap = createParamMap(request);

        // 3-2. paramMap 을 넘겨줘야 함.
        ModelView mv = controller.process(paramMap);

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

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// URL 경로 중요 (urlPatterns = "/front-controller/v1/*")
    // v1 하위의 어떤 URL 이 들어와도, 일단 이 서블릿이 호출됨.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑(Mapping) 정보 만들기
    private Map<String, ControllerV1> controllerMap = new HashMap<>();  // 여기서 key(String) 는 URL

    // 매핑(Mapping) 정보를 생성자에 담아둘거임. (이 서블릿이 생성될 때 세팅되도록)
        // 근데 new FrontControllerServletV1() 하는 곳이 있어야 생성자 내부 코드가 실행되는 것 아님?
        // new FrontControllerServletV1() 한 곳이 없는데, 생성자가 1번 호출됨. (이유가 뭐지?)
            // -> 그냥, HttpServlet 를 상속 받아야 하는 서블릿에서는 기본 생성자가 반드시 있어야 하고,
                                                            // 이 기본 생성자는 자동으로 1번 실행되는 듯 (이게 서블릿 규칙)
    public FrontControllerServletV1() {
        System.out.println("생성자 호출 - FrontControllerServletV111");           // 서버 실행 시, 1번 호출됨. (이 생성자가 실행됐다는 의미. 이유 모름)
            // -> 그냥, HttpServlet 를 상속 받아야 하는 서블릿에서는 반드시 기본 생성자가 있어야 하고, 이 기본 생성자는 자동으로 1번 실행되는 듯 (이게 서블릿 규칙)

        // ┌> 얘가 호출이 되면,                        ┌> 얘가 실행됨
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)     throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 1. request.getRequestURI() 를 쓰면 /front-controller/v1/members/new-form 등등 문자열을 뽑아냄.
        String requestURI = request.getRequestURI();

        // 2. request.getRequestURI() 를 통해 뽑아낸 URL 주소가 생성자에서 설정한 것 중에 있다면, 해당 객체 인스턴스를 반환받음.
        ControllerV1 controller = controllerMap.get(requestURI);

        // 3-1. 근데 만약 해당 URL 주소가 없으면, 404 에러 반환 후 그냥 리턴
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        // 3-2. 잘 조회가 됐다면, 해당 객체의 process 메서드 호출(인터페이스 ControllerV1 를 구현한 객체에다가 오버라이딩한 메서드)
        controller.process(request, response);
    }
}
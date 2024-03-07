package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller         // @Controller 적어야, @RequestMapping 이 작동한다.
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    // 싱글톤이라 new 안 됨.       (저장하기 위해선 MemberRepository 가 필요함)
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }


    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        // mv.getModel().put("member", member);
        mv.addObject("member", member);

        return mv;
    }


    // @RequestMapping("/members")      
    // 공통 매핑 주소가 /springmvc/v2/members 인데,  끝에  /members 한 번 더 붙여지니까 그냥 @RequestMapping 만 적기
    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        // mv.getModel().put("members", members);
        mv.addObject("members", members);

        return mv;
    }
}
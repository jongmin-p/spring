package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller         // @Controller 적어야, @RequestMapping 이 작동한다.
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    // 싱글톤이라 new 안 됨.       (저장하기 위해선 MemberRepository 가 필요함)
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }


    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        /*
            Model 은 컨트롤러와 뷰 사이에서 데이터를 전달하는 데 사용되는 객체.
            컨트롤러가 처리한 데이터나 비즈니스 로직의 결과를 뷰에 전달하고자 할 때 model 객체에 데이터를 담아서 보냄.
            뷰 템플릿에서는 이 model 에 담긴 데이터를 사용하여 HTML을 생성함.(즉, 렌더링 함)
        */
        
        model.addAttribute("member", member);

        return "save-result";
    }


    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
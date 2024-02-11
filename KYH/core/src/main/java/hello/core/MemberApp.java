package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // 이 코드는 순수한 Java 코드.   스프링 관련된 코드 없음.
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // 스프링으로 전환
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);   // 1. 저장할 멤버 데이터 생성 (등급은 VIP로)
        memberService.join(member);                                       // 2. 생성한 멤버 데이터 넣기 (회원가입)

        Member findMember = memberService.findMember(1L);       // 3. findMember 메서드를 통해 실제로 확인.
                                                                          // findMember 는 memberId 를 이용해서 찾음.

        System.out.println("new member = " + member.getName());           // new member = memberA
        System.out.println("find Member = " + findMember.getName());      // new member = memberA
    }
}
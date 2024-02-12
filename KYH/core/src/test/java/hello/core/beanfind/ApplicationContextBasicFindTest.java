package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService.getClass() = " + memberService.getClass());

        // 검증 (memberSerivce 가 MemberServiceImpl 의 인스턴스면 성공)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService.getClass() = " + memberService.getClass());

        // 검증 (memberSerivce 가 MemberServiceImpl 의 인스턴스면 성공)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // 인터페이스(MemberService) 가 아닌 구체화된 클래스로 조회 (MemberServiceImpl)
        // 참고로 구체 타입으로 조회하면, 유연성이 떨어짐.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService.getClass() = " + memberService.getClass());

        // 검증 (memberSerivce 가 MemberServiceImpl 의 인스턴스면 성공)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X (실패 테스트)")
    void findBeanByNameX() {
        // MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);    // 에러 발생 (xxxxx 라는 이름의 빈이 없음)

        assertThrows(NoSuchBeanDefinitionException.class,                     // 2. 이 에러가 터져야 한다 (안 터지면 테스트 실패)
                () -> ac.getBean("xxxxx", MemberService.class));        // 1. 해당 로직을 실행하면,

    }
}
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService  ->  new MemoryMemberRepository()
    // @Bean orderService   ->  new MemoryMemberRepository()

    // 예상되는 출력 결과
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제 출력 결과 (ConfigurationSingletonTest.java 파일 실행)
    // call AppConfig.memberService
    // call AppConfig.memberRepository      // 얘가 3번 호출될거라 예상했지만, 1번만 호출됐음. (스프링이 싱글톤 보장)
    // call AppConfig.orderService
        // -> @Configuration 애너테이션을 없애면, 싱글톤이 깨져서 우리가 예상한 결과대로 출력됨 (memberRepository 3번 출력)
        // -> 즉, @Configuration 없이, @Bean 만 사용해도 스프링 빈으로 등록은 된다. (하지만, 싱글톤을 보장하지 않는다)

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();        // 이 부분만 바꿔주면 됨
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());      // 여기서 콜함
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();           // 이 부분만 바꿔주면 됨
        return new RateDiscountPolicy();             // 이 부분만 바꿔주면 됨
    }
}
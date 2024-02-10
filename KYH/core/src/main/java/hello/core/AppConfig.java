package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();        // 이 부분만 바꿔주면 됨
    }

    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();             // 이 부분만 바꿔주면 됨
        return new RateDiscountPolicy();             // 이 부분만 바꿔주면 됨
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());      // 여기서 콜함
    }
}
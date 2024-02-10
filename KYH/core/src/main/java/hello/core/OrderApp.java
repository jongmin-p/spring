package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);     // 1. VIP 멤버 하나 생성.
        memberService.join(member);    // 2. memberService 이용해서 메모리 객체에 넣음 (그래야 주문해서 찾아 쓸 수 있으니까)

        // 3. 오더 생성 (10000원 짜리의 itemA 라는 상품)
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // toString() 으로 오버라이딩한 것이 출력됨
        System.out.println("order = " + order);

        // System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
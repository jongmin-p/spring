package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);     // 1. VIP 멤버 하나 생성.
        memberService.join(member);     // 2. memberService 이용해서 메모리 객체에 넣음 (그래야 주문해서 찾아 쓸 수 있으니까)

        // 3. 오더 생성 (10000원 짜리의 itemA 라는 상품)
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 4. 검증 (VIP 멤버가 1000원 할인 받은게 맞는지)
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
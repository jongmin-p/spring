package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 오더 서비스는 아래의 2가지가 필요. (MemberRepository, DiscountPolicy)

    // 1. MemberRepository 에서 회원 찾을 때 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2. 할인을 어떻게 할 건지 결정할 때 필요
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 일단, 멤버 찾기
        Member member = memberRepository.findById(memberId);

        // 2. OrderService 입장에서는 할인에 대해 잘 모르겠고, 그냥 결과만 discountPolicy 에서 받고자 함.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 이제 order(주문) 만들어서 반환해주면 됨.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // 오더 서비스는 아래의 2가지가 필요. (MemberRepository, DiscountPolicy)

    // 1. MemberRepository 에서 회원 찾을 때 필요
    private final MemberRepository memberRepository;

    // 2. 할인을 어떻게 할 건지 결정할 때 필요
    private final DiscountPolicy discountPolicy;

    // @RequiredArgsConstructor 애너테이션(롬복) 이 아래 생성자 의존 관계 코드를 자동으로 만들어 줌. (대신 final 붙은 필드들만)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 일단, 멤버 찾기
        Member member = memberRepository.findById(memberId);

        // 2. OrderService 입장에서는 할인에 대해 잘 모르겠고, 그냥 결과만 discountPolicy 에서 받고자 함.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 이제 order(주문) 만들어서 반환해주면 됨.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
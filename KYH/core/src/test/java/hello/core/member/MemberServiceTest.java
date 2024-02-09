package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        // 1. 저장할 멤버 데이터 생성 (등급은 VIP로)
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        // 2. 생성한 멤버 데이터 넣기 (회원가입)
        memberService.join(member);

        // 3. findMember 메서드를 통해 실제로 확인.
        Member findMember = memberService.findMember(1L);

        // then
        // 4. 똑같은지 검증
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
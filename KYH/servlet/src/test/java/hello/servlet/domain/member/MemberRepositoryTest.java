package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    // 테스트 할 대상 가져 오기 (MemberRepository)
    // MemberRepository memberRepository = new MemberRepository();      // 싱글톤이기 떄문에 new 로 하면 안 됨.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {                      // 각 테스트가 끝날 때마다 초기화하기 위함.
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given (이런 게 주어졌을 때)
        Member member = new Member("Hello", 20);

        // when (이런 걸 실행했을 때)
        Member savedMember = memberRepository.save(member);

        // then (결과가 이거여야 한다)
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);       // 찾아온 멤버는 저장된 멤버와 같아야 정상.
    }

    @Test
    void findALl() {
        // given (이런 게 주어졌을 때)
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when (이런 걸 실행했을 때)
        List<Member> result = memberRepository.findAll();

        // then (결과가 이거여야 한다)
        assertThat(result.size()).isEqualTo(2);      // 먼저, 사이즈 확인
        assertThat(result).contains(member1, member2);         // 데이터가 맞는지 확인 (member1, member2 가 포함돼 있는지)
    }
}
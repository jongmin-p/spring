package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl 에서 회원을 가입 및 조회하려면  MemberRepository 가 필요
    // MemberRepository 에다가 모든 회원들의 정보가 저장되니까
    private final MemberRepository memberRepository;

    @Autowired   // @Autowired 애너테이션을 사용하면 자동으로 의존 관계 주입 -> ac.getBean(MemberRepository.class) 한 거랑 같음
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
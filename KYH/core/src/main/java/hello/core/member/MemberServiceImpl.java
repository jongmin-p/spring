package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl 에서 회원을 가입 및 조회하려면  MemberRepository 가 필요
    // MemberRepository 에다가 모든 회원들의 정보가 저장되니까
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
}
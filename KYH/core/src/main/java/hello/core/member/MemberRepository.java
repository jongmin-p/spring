package hello.core.member;

public interface MemberRepository {
    
    // 회원 저장하는 메서드
    void save(Member member);

    // 회원 아이디를 이용하여 회원을 찾는 메서드
    Member findById(Long memberId);
}
package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음.  실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // key는 id, value 는 Member
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;      // id 가 하나씩 증가하는 시퀀스

    // 싱글톤으로
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤으로 만들 때는 private 으로 생성자 막기 (아무나 생성하지 못하도록)
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);               //  회원을 id 로 찾음
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // store 에 있는 모든 값들을 꺼내서 새로운 ArrayList 에 담아서 리턴.
                                                    // 기존의 store 에 있는 값들을 건들지 않기 위해서 이렇게 함.
    }

    public void clearStore() {
        store.clear();
    }
}
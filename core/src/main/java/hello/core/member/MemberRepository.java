package hello.core.member;

public interface MemberRepository {
    void save(Member member);//회원을 저장하는것
    Member findById(Long memberId);//멤버의
}

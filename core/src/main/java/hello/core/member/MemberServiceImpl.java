package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
@Primary
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;


//    public MemberServiceImpl( MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        // TODO Auto-generated method stub
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        // TODO Auto-generated method stub

        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

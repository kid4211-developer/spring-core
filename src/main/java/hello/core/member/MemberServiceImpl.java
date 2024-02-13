package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    /**
     * @Autowired - 생성자를 통한 자동 의존관계 주입
     * - 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
     * - 더이상 MemoryMemberRepository 를 직접 의존하지 않고 인터페이스에만 의존한다.
     * - 즉, MemberRepository 인 추상에만 의존하면 된다.법
     *
     * 이전 AppConfig 에서는 @Bean 으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다.
     * @CompnentScan 을 통해 컴포넌트들이 자동으로 SpringBean 으로 등록이 되고 @Autowired 를 통해 등록된 Bean 을 자동으로 주입 받는다.
     * (더이상 AppConfig.java 의 memberService() 와 같은 메서드로 의존성을 일일이 주입받지 않아도 된다)
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        /**
         * @Autowired 의 의미는 코드적으로 this.memberRepository = ac.getBean(MemberRepository.class) 의 의미이다.
         */
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
package hello.core.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    /**
     * 생성자를 통한 의존성 주입 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
     * - 더이상 MemoryMemberRepository 를 직접 의존하지 않고 인터페이스에만 의존한다.
     * - 즉, MemberRepository 인 추상에만 의존하면 된다.
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
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
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * Configuration
 * - Application 전체 동작 방식을 구성(config) 하기 위해, 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
 * - 생성한 객체 인스턴스의 참조(reference)를 생성자를 통해 주입(Injection) 해준다.
 */
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * 이제 할인 정책을 변경해도, Application 구성 역할을 담당하는 AppConfig 만 변경하면 된다.
     * 클라이언트 코드인 ServiceImpl class 인 사용 영역의 어떤 코드도 변경할 필요가 없다.
     */
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

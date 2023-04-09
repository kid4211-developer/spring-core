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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration
 * 1)
 * - Application 전체 동작 방식을 구성(config) 하기 위해, 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
 * - Spring Container(ApplicationContext)는 @Configuration 이 붙은 config 파일을 설정 정보로 사용한다.
 * - @Bean 으로 등록된 메서드를 모두 호출해서 반환된 객체를 Spring Container 에 등록되는데 이렇게 등록된 객체를 Spring Bean 이라고 한다.
 * - ApplicationContext 객체를 통해 getBean() 메서드를 사용하여 객체를 반환 받는다.
 *
 * 2)
 */
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * 이제 할인 정책을 변경해도, Application 구성 역할을 담당하는 AppConfig 만 변경하면 된다.
     * 클라이언트 코드인 ServiceImpl class 인 사용 영역의 어떤 코드도 변경할 필요가 없다.
     */
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

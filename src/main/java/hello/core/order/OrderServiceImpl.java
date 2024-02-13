package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @RequiredArgsConstructor
 * - 생성자를 선언할 필요도 없고 bean 생성자 주입 패턴도 자동으로 형성 시켜준다.
 */
@Component
public class OrderServiceImpl implements OrderService {
    /**
     * < 객체지향 설계 원칙 OCP / DIP 문제 >
     * 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 의 객체 생성 부분을 수정 해야한다.
     * -> 이러한 방식은 인터페이스 뿐만 아니라 구현 클래스에도 의존하고 있어 객체지향 설계 원칙에 어긋난다.
     * -> 지금 코드는 기능을 확장할시에 클라이언트 코드에 영향을 준다.
     *
     * - 해당 설계 문제를 해결하기 위해서는 클라이언트(OrderServiceImpl)에 discountPolicy 구현 객체를 대신 주입해주어야 한다.
     */
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    /**
     * @Autowired 를 사용하면 생성자에 여러 의존관계도 한번에 주입 받을 수 있다.
     * # @Autowired 옵션
     *  1. @Autowired(required = false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출되지 않음
     *  2. org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null 이 입력된다.
     *  3. Optional<> : 자동 주입할 대상이 없으면 "Optional.empty" 가 입력된다.
     */

    /**
     * 1. 생성자 주입 방식
     *  - 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다.
     *  - "불변 / 필수" 의존관계에 사용된다.
     *  - 생성자가 하나만 있는 경우 @Autowired 를 생략 해도 된다.
     *  - spring bean 등록 단계에 객체가 생성되는 과정에서 의존 관계가 주입된다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 2. 수정자 주입 방식
     *  - setter 라 불리는 수정자 메서드를 통해 의존관계를 주입하는 방법
     *  - "선택 / 변경" 가능성이 있는 의존 관계에 사용
     *  - Java Bean 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법
     */
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 3. 필드 주입 방식(Field Injection)
     *  - @Autowired private DiscountPolicy discountPolicy;
     *  - 테스트 관점에서 매우 유연하지 못해서 비권장되는 방식
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        /**
         * 단일체계원칙에 입각한 설계 - 기능 메서드를 단수히 호출함으로써 결과를 전달 받는다.
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
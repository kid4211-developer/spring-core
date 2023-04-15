package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * @Autowired 를 사용하면 생성자에 여러 의존관계도 한번에 주입 받을 수 있다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy
            discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
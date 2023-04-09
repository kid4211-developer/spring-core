package hello.core.xml;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import static org.assertj.core.api.Assertions.*;

/**
 * XML 설정 사용
 * - 최근에는 Spring Boot 를 많이 사용하면서 XML 기반의 설정은 잘 사용하지 않는다.
 * - XML 을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있으므로 배워둘 가치가 있다.
 * - GenericXmlApplicationContext 를 사용하면서 XML 설정 파일을 넘기면 된다.
 */
public class XmlAppContext {
    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
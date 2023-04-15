package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(basePackages = "hello.core", excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
/**
 * - Component Scan 은 이름 그대로 @Component annotation 이 붙은 클래스를 스캔하여 Spring Bean 으로 등록 한다.
 * - Component Scan 을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
 *   AppConfig / TestConfig 등 앞서 만들어 두었던 설정 정보도 함께 등록이 되고 실행이 된다.
 *   excludeFilters 를 이용해 설정정보는 Component scan 의 대상에서 제외 시킬수 있다.
 * - @Configuration 이 스캔의 대상이 된 이유는 그 상위에 @Component 어노테이션이 붙어 있기 때문이다.
 */
public class AutoAppConfig {

}
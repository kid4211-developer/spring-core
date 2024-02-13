package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

/**
 * - @ComponentScan 은 이름 그대로 @Component 어노테이션이 붙은 클래스를 스캔하여 SpringBean 으로 등록 한다.
 * 1. basePackages : 탐색할 패키지 시작 위치를 지정할 수 있다 (관례적으로 config 파일을 프로젝트 최상단에 위치 시키고 basePackages 를 생략한다)
 * 2. excludeFilters
 * - @ComponentScan 을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에 AppConfig/TestConfig 등 앞서 만들어 두었던
 *   설정 정보도 함께 등록이 되고 실행이 된다. excludeFilters 옵션를 이용해 설정 정보는 @ComponentScan 의 대상에서 제외 시킬 수 있다.
 */

/**
 * @ComponentScan 의 기본 대상
 * 1. @Component : 기본적인 컴포넌트 스캔 대상
 * 2. @Controller : 스프림 MVC 컨트롤러
 * 3. @Service : 스프링 비즈니스 로직
 * 4. @Repository : 스프링 데이터 접근 계증
 * 5. @Configuration : 스프링 설정 정보
 */
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Configuration
public class AutoAppConfig {

}
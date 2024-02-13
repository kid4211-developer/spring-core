package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        /**
         * 필터 처리된 beanB 의 빈을 get 하면 NoSuchBeanDefinitionException 에러가 발생 할 것
         */
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    /**
     * @Filter 의 type 에는 5가지 옵션이 있다.
     * 1. ANNOTATION - 기본값, 어노테이션을 인식해서 동작한다.
     * 2. ASSIGNABLE_TYPE - 지정한 타입과 자식 타입을 인식해서 동작한다.
     * 3. ASPECTJ - AspectJ 패턴을 사용 (ex. org.example..*Service+)
     * 4. REGEX - 정규 표현식
     * 5. CUSTOM - TypeFilter 라는 인터페이스를 구현해서 처리한다.
     */
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
        /**
         * includeFilters 에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
         * excludeFilters 에 MyExcludeComponent 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
         */
    }
}
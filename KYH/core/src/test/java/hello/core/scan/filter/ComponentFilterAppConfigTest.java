package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);

        // beanA 는 null 이면 안 됨 (있어야 함), 즉 테스트 통과해야 함 (includeFilters)
        assertThat(beanA).isNotNull();

        // 예외 터짐 (BeanB 는 없으니까) - BeanB는 컴포넌트 스캔 대상에서 빠짐 (excludeFilters)
        // ac.getBean("beanB", BeanB.class);

        // 예외 처리 (이젠 테스트 성공)
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    // 테스트용
    @Configuration
    @ComponentScan(
                    includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
                    )
    static class ComponentFilterAppConfig {
    }
}
package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);      // 빈 등록

        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();     // ApplicationContext 에는 close() 가 없어서, 위에선 ConfigurableApplicationContext 사용함
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close")     // 빈 등록 시, 초기화 및 소멸 메소드 지정
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;       // 2. 호출된 결과물(return networkClient) 이 스프링 빈으로 등록됨.
        }
    }
}
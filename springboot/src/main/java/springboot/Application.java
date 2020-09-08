package springboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication // springboot 프로젝트의 시작부분이다.
public class Application {
    private static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled","true");
        System.setProperty("spring.devtools.livereload.enabled","true");
        SpringApplication.run(Application.class, args);
    }

}

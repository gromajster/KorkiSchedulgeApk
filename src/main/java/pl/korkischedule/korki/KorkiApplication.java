package pl.korkischedule.korki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@EnableAsync
@SpringBootApplication
public class KorkiApplication {


    public static void main(String[] args) {
        System.getenv("JDBC_DATABASE_URL");
        SpringApplication.run(KorkiApplication.class, args);
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}

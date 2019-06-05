package pl.korkischedule.korki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import java.net.URI;
import java.net.URISyntaxException;

@EnableAsync
@SpringBootApplication
public class KorkiApplication {


    public static void main(String[] args) throws URISyntaxException
    {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
    
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        
        System.setProperty("JDBC_DATABASE_URL", dbUrl);
        System.setProperty("JDBC_DATABASE_USERNAME", username);
        System.setProperty("JDBC_DATABASE_PASSWORD", password);
        
        SpringApplication.run(KorkiApplication.class, args);
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}

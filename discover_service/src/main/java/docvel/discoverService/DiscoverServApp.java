package docvel.discoverService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoverServApp {
    public static void main(String[] args) {
        SpringApplication.run(DiscoverServApp.class, args);
    }
}

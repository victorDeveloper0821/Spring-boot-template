package idv.victor.sideproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "idv.victor.sideproject.*")
@SpringBootApplication
public class DemoSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSwaggerApplication.class, args);
    }

}

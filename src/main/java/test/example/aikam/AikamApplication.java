package test.example.aikam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.example.aikam.entity.Buyer;
import test.example.aikam.service.BuyerService;

import java.util.List;

@SpringBootApplication
public class AikamApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AikamApplication.class, args);
        BuyerService buyerService = context.getBean(BuyerService.class);


        List<Buyer> all = buyerService.findAll();


        for (Buyer b : all
             ) {
            System.out.println(b);
        }
    }
}

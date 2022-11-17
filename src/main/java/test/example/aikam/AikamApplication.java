package test.example.aikam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.example.aikam.entity.Buyer;
import test.example.aikam.handling.JsonParser;
import test.example.aikam.service.BuyerService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AikamApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(AikamApplication.class, args);
        BuyerService buyerService = context.getBean(BuyerService.class);
        JsonParser jsonParser = context.getBean(JsonParser.class);

        System.out.println(jsonParser.getCriterionForSearch("input.json"));
        System.out.println(jsonParser.getCriterionForStat("inputStat.json"));
        System.out.println(buyerService.createResponse("input.json"));
        buyerService.writeJson(buyerService.createResponse("input.json"));

    }
}

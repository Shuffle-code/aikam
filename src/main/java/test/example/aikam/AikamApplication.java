package test.example.aikam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.example.aikam.handlingJson.HandlingJson;
import test.example.aikam.handlingJson.JsonParser;
import test.example.aikam.json.RequestParam;
import test.example.aikam.service.BuyerService;
import test.example.aikam.service.PurchaseService;

import java.io.IOException;

@SpringBootApplication
public class AikamApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(AikamApplication.class, args);
        BuyerService buyerService = context.getBean(BuyerService.class);
        PurchaseService purchaseService = context.getBean(PurchaseService.class);
        JsonParser jsonParser = context.getBean(JsonParser.class);
        HandlingJson handling = context.getBean(HandlingJson.class);
        RequestParam criterionForStat = jsonParser.getCriterionForStat("inputStat.json");
        System.out.println(criterionForStat);
        int i = handling.workingDay(criterionForStat.getStartDate(), criterionForStat.getEndDate());
        System.out.println(i);
        System.out.println(buyerService.createResponseSearch("input.json"));
        buyerService.writeJson(buyerService.createResponseSearch("input.json"));
        jsonParser.writeJsonExample(handling.createJsonForStatRequest("inputStat.json"));
        System.out.println(purchaseService.findAllByData(criterionForStat.getStartDate(),
                criterionForStat.getEndDate()));



    }
}

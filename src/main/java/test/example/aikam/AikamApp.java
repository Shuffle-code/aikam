package test.example.aikam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.example.aikam.handlingJson.HandlingJson;
import test.example.aikam.handlingJson.JsonParser;
import test.example.aikam.service.BuyerService;

import java.io.IOException;

@SpringBootApplication
public class AikamApp {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(AikamApp.class, args);
        BuyerService buyerService = context.getBean(BuyerService.class);
        HandlingJson handlingJson = context.getBean(HandlingJson.class);
        JsonParser jsonParser = context.getBean(JsonParser.class);
        String command = args[0];
        String outfile = "output.json";
        if (args.length != 2) {
            System.out.println("ERROR: INVALID NUMBER OF ARGUMENTS.");
        } else if (!(args[0].equals("stat") || args[0].equals("stat"))) {
            System.out.println("Command " + command + " is unknown");
        }

        if (args[0].equals("search")){
            buyerService.createResponseSearch(args[1]);
        } else if (args[0].equals("stat")){
            System.out.println(args[0]);
            outfile = "outputStat.json";
            handlingJson.createJsonForStatRequest(args[1]);
            System.out.println(args[1]);
        }else System.out.println("Command " + command + " is unknown");
    }
}

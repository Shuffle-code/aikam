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
        String outFile = "output.json";
        if (args.length != 2) {
            System.out.println("ERROR: INVALID NUMBER OF ARGUMENTS.");
        } else if (!(args[0].equals("search") || args[0].equals("stat"))) {
            System.out.println("!Command " + command + " is unknown");
        }

        if (args[0].equals("search")){
            System.out.println("This is type: " + args[0]);
            System.out.println("This is string inJSON: " + jsonParser.getInJson("input.json").toString());
            buyerService.createResponseSearch(args[1]);
            System.out.println("This is string outJSON: " + jsonParser.getOutJson("output.json"));
        } else if (args[0].equals("stat")){
            System.out.println("This is type: " + args[0]);
            System.out.println("This is string inJSON: " + jsonParser.getInJson("inputStat.json"));
            outFile = "outputStat.json";
            handlingJson.createJsonForStatRequest(args[1]);
            System.out.println("This is string outJSON: " + jsonParser.getOutJson("outputStat.json"));
        }else System.out.println("Command " + command + " is unknown");
    }
}

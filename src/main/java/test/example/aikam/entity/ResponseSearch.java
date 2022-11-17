package test.example.aikam.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Setter
@Getter
public class ResponseSearch {

    private RequestParam request;
    private List<Buyer> buyerListRequestLastname;
    private List<Buyer> buyerListRequestShipmentAmount;
    private List<Buyer> buyerListRequestMinMax;
    private List<Buyer> buyerListRequestBad;

    @Override
    public String toString() {
        return  "{\"type\":\"search\"," +
                "\"results\":[{" +
                "\"criteria\":{\"lastName\":\"" +
                request.getLastName() + "\"},\"results\":" +
                buyerListRequestLastname + "},{" +
                "\"criteria\":{\"productName\":\"" + request.getProductName() +
                "\",\"minTimes\":" + request.getMinTimes() + "},\"results\":" +
                buyerListRequestShipmentAmount + "},{" +
                "\"criteria\":{\"minExpenses\":\"" + request.getMinExpenses() +
                "\",\"maxExpenses\":" + request.getMaxExpenses() + "},\"results\":" +
                buyerListRequestMinMax + "},{" +
                "\"criteria\":{\"badCustomers\":" + request.getBadCustomers() + "},\"results\":" +
                buyerListRequestBad + "}]}"
                ;
    }

}

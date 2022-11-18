package test.example.aikam.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseSearch implements Json {

    private RequestParam request;
    private List<Buyer> buyerListRequestLastname;
    private List<Buyer> buyerListRequestShipmentAmount;
    private List<Buyer> buyerListRequestMinMax;
    private List<Buyer> buyerListRequestBad;
    @Override
    public String toString() {
            return  "{"+ "\n" +"\"type\":\"search\"," + "\n" +
                    "\"results\":["+ "\n" +"{\"criteria\":{\"lastName\":\"" +
                    this.request.getLastName() + "\"},"+ "\n" +"\"results\":" +
                    buyerListRequestLastname + "},"+ "\n" +"{" +
                    "\"criteria\":{\"productName\":\"" + request.getProductName() +
                    "\",\"minTimes\":" + request.getMinTimes() + "},\"results\":" +
                    buyerListRequestShipmentAmount + "},"+ "\n" +"{" +
                    "\"criteria\":{\"minExpenses\":\"" + request.getMinExpenses() +
                    "\",\"maxExpenses\":" + request.getMaxExpenses() + "},\"results\":" +
                    buyerListRequestMinMax + "},"+ "\n" +"{" +
                    "\"criteria\":{\"badCustomers\":" + request.getBadCustomers() + "},\"results\":" +
                    buyerListRequestBad +"}" + "\n" + "]"+ "\n" +"}"
                    ;

        }

}

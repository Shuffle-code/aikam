package test.example.aikam.json;

import lombok.Getter;
import lombok.Setter;
import test.example.aikam.entity.Buyer;
import test.example.aikam.json.enums.TypeCriteria;
import test.example.aikam.json.enums.TypeRequest;

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
            return  "{"+ "\n" +"\"type\":\"" + TypeRequest.SEARCH.getTitle() + "\"," + "\n" +
                    "\"results\":["+ "\n" +"{\"criteria\":{\"" + TypeCriteria.LASTNAME.getTitle() + "\":\"" +
                    this.request.getLastName() + "\"},"+ "\n" +"\"results\":" +
                    buyerListRequestLastname + "},"+ "\n" +"{" +
                    "\"criteria\":{\"" + TypeCriteria.PRODUCT_NAME.getTitle() + "\":\"" + request.getProductName() +
                    "\",\"" + TypeCriteria.MIN_TIMES.getTitle() +"\":" + request.getMinTimes() + "},\"results\":" +
                    buyerListRequestShipmentAmount + "},"+ "\n" +"{" +
                    "\"criteria\":{\"" + TypeCriteria.MIN_EXPENSE.getTitle() + "\":\"" + request.getMinExpenses() +
                    "\",\"" + TypeCriteria.MAX_EXPENSE.getTitle() + "\":" + request.getMaxExpenses() + "},\"results\":" +
                    buyerListRequestMinMax + "},"+ "\n" +"{" +
                    "\"criteria\":{\"" + TypeCriteria.BAD_CUSTOMERS.getTitle() + "\":" + request.getBadCustomers() + "},\"results\":" +
                    buyerListRequestBad +"}" + "\n" + "]"+ "\n" +"}";

    }

    @Override
    public int toJSONString() {
        return 0;
    }
}

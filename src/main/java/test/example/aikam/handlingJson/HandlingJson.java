package test.example.aikam.handlingJson;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import test.example.aikam.entity.Buyer;
import test.example.aikam.entity.Purchase;
import test.example.aikam.entity.Shipment;
import test.example.aikam.json.RequestParam;
import test.example.aikam.json.enums.TypeCriteria;
import test.example.aikam.json.enums.TypeRequest;
import test.example.aikam.service.BuyerService;
import test.example.aikam.service.PurchaseService;
import test.example.aikam.service.ShipmentService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Service
@RequiredArgsConstructor
public class HandlingJson {
    private final JsonParser jsonParser;
    private final PurchaseService purchaseService;
    private final BuyerService buyerService;
    private final ShipmentService shipmentService;
    private double totalExpenses = 0;


    public void createJsonForStatRequest(String filename) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject();
            RequestParam criterionForStat = jsonParser.getCriterionForStat(filename);
            LocalDate startDate = criterionForStat.getStartDate();
            LocalDate endDate = criterionForStat.getEndDate();
            int totalDays = workingDay(startDate, endDate);
            List<Purchase> buyerByData = getBuyerByData(startDate, endDate);
            Map<Buyer, List<Shipment>> mapBuyerPurchase = createMapBuyerPurchase(buyerByData);
            jsonObject.put(TypeCriteria.TOTAL_DAYS.getTitle(), totalDays);
            jsonObject.put(TypeCriteria.TYPE.getTitle(), TypeRequest.STAT.getTitle());
            jsonObject.put(TypeCriteria.CUSTOMERS.getTitle(), createBuyerJsonArray(mapBuyerPurchase));
            jsonObject.put(TypeCriteria.TOTAL_EXPENSES.getTitle(),BigDecimal.valueOf(totalExpenses));
            jsonObject.put(TypeCriteria.AVG_EXPENSES.getTitle(), BigDecimal.valueOf(totalExpenses / (doubleValue(mapBuyerPurchase.size()))).setScale(2, RoundingMode.DOWN));
            jsonParser.writeJsonExample(jsonObject, "outputStat.json");
        }catch (Exception e){
            e.printStackTrace();
            jsonParser.printError(e.getMessage(), "outputStat.json");
        }
    }

    public List<Purchase> getBuyerByData(LocalDate startDate, LocalDate endDate){
        return purchaseService.findAllByData(startDate, endDate);
    }

    public JSONArray createBuyerJsonArray (Map<Buyer, List<Shipment>> mapBuyerPurchase){
        JSONArray jsonArray = new JSONArray();
        for (Buyer buyer : mapBuyerPurchase.keySet()) {
            JSONObject jsonObjectBuyer = new JSONObject();
            jsonObjectBuyer.put(TypeCriteria.NAME.getTitle(), buyer.getLastname() + " " + buyer.getFirstname());
            JSONArray jsonArrayPurchase = new JSONArray();
            double totalExpenseCurr = 0;
            for (Shipment shipment : mapBuyerPurchase.get(buyer)) {
                JSONObject jsonObjectShipment = new JSONObject();
                jsonObjectShipment.put(TypeCriteria.PRODUCT_NAME.getTitle(), shipment.getTitle());
                jsonObjectShipment.put(TypeCriteria.EXPENSES.getTitle(), shipment.getCost());
                double v = doubleValue(shipment.getCost().setScale(2, RoundingMode.DOWN));
                jsonArrayPurchase.put(jsonObjectShipment);
                totalExpenseCurr += v;
            }
            jsonObjectBuyer.put(TypeCriteria.TOTAL_EXPENSES.getTitle(),totalExpenseCurr);
            jsonObjectBuyer.put(TypeCriteria.PURCHASES.getTitle(),jsonArrayPurchase);
            jsonArray.put(jsonObjectBuyer);
            totalExpenses += totalExpenseCurr;
        }
        return jsonArray;
    }
    public Map<Buyer, List<Shipment>> createMapBuyerPurchase(List<Purchase> purchaseList){
        Map<Buyer, List<Shipment>> buyerMapListPurchase = new HashMap<>();
        for (int i = 0; i < purchaseList.size() ; i++) {
            List<Shipment> shipmentList = new LinkedList<>();
            Long buyerId = purchaseList.get(i).getBuyerId();
            Buyer buyer = buyerService.finById(buyerId);
            buyerMapListPurchase.put(buyer, shipmentList);
            for (int j = 0; j < purchaseList.size(); j++) {
                Long id = purchaseList.get(j).getShipmentId();
                if (buyerId == purchaseList.get(j).getBuyerId()){
                    shipmentList.add(shipmentService.findById(id));
                }
            }
        }
        return buyerMapListPurchase;
    }
    public int workingDay(LocalDate start, LocalDate end){
        int workingDays = 0;
        LocalDate weekday = start;
        while(weekday.isBefore(end)){
            DayOfWeek dayOfWeek = weekday.getDayOfWeek();
            if ((dayOfWeek != DayOfWeek.SATURDAY) && dayOfWeek != DayOfWeek.SUNDAY) {
                workingDays++;
            } weekday = weekday.plusDays(1);
        }
        return workingDays;
    }
}


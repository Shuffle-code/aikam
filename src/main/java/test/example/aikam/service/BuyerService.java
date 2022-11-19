package test.example.aikam.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import test.example.aikam.dao.BuyerDao;
import test.example.aikam.entity.*;
import test.example.aikam.handlingJson.JsonParser;
import test.example.aikam.json.Json;
import test.example.aikam.json.RequestParam;
import test.example.aikam.json.ResponseError;
import test.example.aikam.json.ResponseSearch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerDao buyerDao;
    private final JsonParser jsonParser;

    public List<Buyer> findAll(){
        return buyerDao.findAll();
    }
    public Buyer finById(Long id){
        return buyerDao.findById(id).get();
    }

    public List<Buyer> findByLastname (String lastname){
        return buyerDao.findByLastname(lastname);
    };

    public List<String> findByLastnameNew (String lastname){
        return buyerDao.findByLastnameNew(lastname);
    };

    public List<Buyer> findBadBuyers (Long i){
        List<Long> badBuyers = buyerDao.findBadBuyers(i);
        List<Buyer> buyerList = new ArrayList<>();
        for (Long id :badBuyers) {
            buyerList.add(buyerDao.findById(id).get());
        }
        return buyerList;
    }

    public List<Buyer> executeRequestLastname (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findByLastname(criterionForSearch.getLastName());
        return byLastnames;
    }

    public List<Buyer> executeRequestShipmentAmount (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findBuyersByShipmentAndAmount(criterionForSearch.getProductName(), criterionForSearch.getMinTimes() );
        return byLastnames;
    }

    private List<Buyer> findBuyersByShipmentAndAmount(String title, int i) {
        List<Long> badBuyers = buyerDao.findBuyersByShipmentAndAmount(title, i);
        List<Buyer> buyerList = new LinkedList<>();
        for (Long id :badBuyers) {
            buyerList.add(buyerDao.findById(id).get());
        }
        return buyerList;
    }

    private List<Buyer> findBuyersByMinMax(int min, int max) {
        List<Long> badBuyers = buyerDao.findBuyersByMinMax(min, max);
        List<Buyer> buyerList = new LinkedList<>();
        for (Long id :badBuyers) {
            buyerList.add(buyerDao.findById(id).get());
        }
        return buyerList;
    }

    public List<Buyer> executeRequestMinMax (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findBuyersByMinMax(criterionForSearch.getMinExpenses(), criterionForSearch.getMaxExpenses());
        return byLastnames;
    }

    public List<Buyer> executeRequestBad (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findBadBuyers(criterionForSearch.getBadCustomers());
        return byLastnames;
    }
    public Json createResponseSearch (String filename){
        try {
            ResponseSearch responseSearch = new ResponseSearch();
            responseSearch.setBuyerListRequestLastname(executeRequestLastname(filename));
            responseSearch.setBuyerListRequestShipmentAmount(executeRequestShipmentAmount(filename));
            responseSearch.setBuyerListRequestMinMax(executeRequestMinMax(filename));
            responseSearch.setBuyerListRequestBad(executeRequestBad(filename));
            responseSearch.setRequest(jsonParser.getCriterionForSearch(filename));
            return responseSearch;
        }catch (Exception e){
            ResponseError responseError = new ResponseError();
            responseError.setMassage("Attention Error : " + e.getMessage());
            return responseError;
        }
    }

    public void writeJson(Json response) {
        JSONObject jsonObj = new JSONObject(response.toString());
        try(FileOutputStream fos = new FileOutputStream("storage/response/output.json")) {
            fos.write(jsonObj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

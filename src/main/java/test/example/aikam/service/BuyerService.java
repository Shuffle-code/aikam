package test.example.aikam.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import test.example.aikam.dao.BuyerDao;
import test.example.aikam.entity.Buyer;
import test.example.aikam.entity.RequestParam;
import test.example.aikam.entity.ResponseSearch;
import test.example.aikam.handling.JsonParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerDao buyerDao;
    private final JsonParser jsonParser;

    public List<Buyer> findAll(){
        return buyerDao.findAll();
    }

    public List<Buyer> findByLastname (String lastname){
        return buyerDao.findByLastname(lastname);
    };

    public List<String> findByLastnameNew (String lastname){
        return buyerDao.findByLastnameNew(lastname);
    };

    public List<Buyer> executeRequestLastname (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findByLastname(criterionForSearch.getLastName());
        return byLastnames;
    }

    public List<Buyer> executeRequestShipmentAmount (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findByLastname(criterionForSearch.getLastName());
        return byLastnames;
    }

    public List<Buyer> executeRequestMinMax (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findByLastname(criterionForSearch.getLastName());
        return byLastnames;
    }

    public List<Buyer> executeRequestBad (String fileName) throws IOException {
        RequestParam criterionForSearch = jsonParser.getCriterionForSearch(fileName);
        List<Buyer> byLastnames = findByLastname(criterionForSearch.getLastName());
        return byLastnames;
    }
    public ResponseSearch createResponse (String filename) throws IOException {
        ResponseSearch responseSearch = new ResponseSearch();
        responseSearch.setBuyerListRequestLastname(executeRequestLastname(filename));
        responseSearch.setBuyerListRequestShipmentAmount(executeRequestShipmentAmount(filename));
        responseSearch.setBuyerListRequestMinMax(executeRequestMinMax(filename));
        responseSearch.setBuyerListRequestBad(executeRequestBad(filename));
        responseSearch.setRequest(jsonParser.getCriterionForSearch(filename));
        return responseSearch;
    }

    public void writeJson(ResponseSearch responseSearch) {
        JSONObject jsonObj = new JSONObject(responseSearch.toString());
        try(FileOutputStream fos = new FileOutputStream("output.json")) {
            fos.write(jsonObj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

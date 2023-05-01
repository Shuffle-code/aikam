package test.example.aikam.handling;




import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.example.aikam.entity.Json;
import test.example.aikam.entity.RequestParam;
import test.example.aikam.entity.ResponseError;
import test.example.aikam.entity.ResponseSearch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class JsonParser {

    @Value("${storage.location}/request")
    private Path storagePath;

    public RequestParam getCriterionForSearch(String fileName) throws IOException {
        try {
            JSONObject json = getJson(fileName);
            RequestParam request = new RequestParam();
            JSONArray criteriaS = json.getJSONArray("criteria");
            for (int i = 0; i < criteriaS.length(); i++) {
                JSONObject criterion = criteriaS.getJSONObject(i);
                if (criterion.has("lastName")){
                    request.setLastName(criterion.get("lastName").toString());
                }else if (criterion.has("productName") && criterion.has("minTimes")){
                    request.setProductName(criterion.get("productName").toString());
                    request.setMinTimes(criterion.getInt("minTimes"));
                }else if (criterion.has("minExpenses") && criterion.has("maxExpenses")){
                    request.setMinExpenses(criterion.getInt("minExpenses"));
                    request.setMaxExpenses(criterion.getInt("maxExpenses"));
                }else if (criterion.has("badCustomers")){
                    request.setBadCustomers(criterion.getLong("badCustomers"));
                }
            }
            return request;
        }catch (Exception e){
            e.printStackTrace();
            String errorMassage = e.toString();
            dispatchError(errorMassage);
        }
        return null;
    }

    public void dispatchError(String errorMassage){
        ResponseError responseError = new ResponseError();
        responseError.setMassage(errorMassage);
        JSONObject jsonObj = new JSONObject(errorMassage);
        try(FileOutputStream fos = new FileOutputStream("storage/response/output.json")) {
            fos.write(jsonObj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestParam getCriterionForStat(String fileName) throws IOException {
        JSONObject json = getJson(fileName);
        RequestParam request = new RequestParam();
        request.setStartDate(Date.valueOf((String) json.get("endDate")));
        request.setEndDate(Date.valueOf((String) json.get("startDate")));
        return request;
    }


    public JSONObject getJson(String fileName) throws IOException {
        Path pathJson = storagePath.resolve(fileName);
        String jsonString = new String(Files.readAllBytes(pathJson));
        return new JSONObject(jsonString);
    }
}

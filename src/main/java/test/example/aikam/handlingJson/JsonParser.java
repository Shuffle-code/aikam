package test.example.aikam.handlingJson;




import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.example.aikam.json.Json;
import test.example.aikam.json.RequestParam;
import test.example.aikam.json.ResponseError;
import test.example.aikam.json.enums.TypeCriteria;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Component
public class JsonParser {

    @Value("${storage.location}/request")
    private Path storagePathIn;

    @Value("${storage.location}/response")
    private Path storagePathOut;

    public RequestParam getCriterionForSearch(String fileName) throws IOException {
        try {
            JSONObject json = getJson(fileName);
            RequestParam request = new RequestParam();
            JSONArray criteriaS = json.getJSONArray(TypeCriteria.CRITERIA.getTitle());
            for (int i = 0; i < criteriaS.length(); i++) {
                JSONObject criterion = criteriaS.getJSONObject(i);
                if (criterion.has(TypeCriteria.LASTNAME.getTitle())){
                    request.setLastName(criterion.get(TypeCriteria.LASTNAME.getTitle()).toString());
                }else if (criterion.has(TypeCriteria.PRODUCT_NAME.getTitle()) && criterion.has(TypeCriteria.MIN_TIMES.getTitle())){
                    request.setProductName(criterion.get(TypeCriteria.PRODUCT_NAME.getTitle()).toString());
                    request.setMinTimes(criterion.getInt(TypeCriteria.MIN_TIMES.getTitle()));
                }else if (criterion.has(TypeCriteria.MIN_EXPENSE.getTitle()) && criterion.has(TypeCriteria.MAX_EXPENSE.getTitle())){
                    request.setMinExpenses(criterion.getInt(TypeCriteria.MIN_EXPENSE.getTitle()));
                    request.setMaxExpenses(criterion.getInt(TypeCriteria.MAX_EXPENSE.getTitle()));
                }else if (criterion.has(TypeCriteria.BAD_CUSTOMERS.getTitle())){
                    request.setBadCustomers(criterion.getLong((TypeCriteria.BAD_CUSTOMERS.getTitle())));
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
        try(FileOutputStream fos = new FileOutputStream(String.valueOf(storagePathIn.resolve("output.json")))) {
            fos.write(jsonObj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestParam getCriterionForStat(String fileName) throws IOException {
        JSONObject json = getJson(fileName);
        RequestParam request = new RequestParam();
        request.setStartDate(LocalDate.parse((CharSequence) json.get(TypeCriteria.START_DATE.getTitle())));
        request.setEndDate(LocalDate.parse((CharSequence) json.get(TypeCriteria.END_DATE.getTitle())));
        return request;
    }


    public JSONObject getJson(String fileName) throws IOException {
        Path pathJson = storagePathIn.resolve(fileName);
        String jsonString = new String(Files.readAllBytes(pathJson));
        return new JSONObject(jsonString);
    }

    public void writeJsonExample(JSONObject jsonObject) throws IOException {
        FileWriter file = new FileWriter(String.valueOf(storagePathOut.resolve("outputStat.json")));
        file.write(String.valueOf(jsonObject));
        file.close();
    }


}

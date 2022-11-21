package test.example.aikam.handlingJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.example.aikam.json.JsonResponse;
import test.example.aikam.json.RequestParam;
import test.example.aikam.json.ResponseError;
import test.example.aikam.json.enums.TypeCriteria;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Component
//@RequiredArgsConstructor
public class JsonParser {

//    private final String outputFileName;

    @Value("${storage.location}/request")
    private Path storagePathIn;

    @Value("${storage.location}/response")
    private Path storagePathOut;

//    @Value("C:\\Users\\79130\\IdeaProjects\\aikam\\storage\\request")
//    private Path storagePathIn;
//
//    @Value("C:\\Users\\79130\\IdeaProjects\\aikam\\storage\\response")
//    private Path storagePathOut;

    public RequestParam getCriterionForSearch(String fileName) throws IOException {
        try {
            JSONObject json = getInJson(fileName);
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
        try {
            JSONObject json = getInJson(fileName);
            RequestParam request = new RequestParam();
            request.setStartDate(LocalDate.parse((CharSequence) json.get(TypeCriteria.START_DATE.getTitle())));
            request.setEndDate(LocalDate.parse((CharSequence) json.get(TypeCriteria.END_DATE.getTitle())));
            return request;
        } catch (IOException e) {
            e.printStackTrace();
            String errorMassage = e.toString();
            dispatchError(errorMassage);
        }return null;
    }


    public void printError(String message, String outputFileName) throws IOException {
        JSONObject jsonObjectError = new JSONObject();
        jsonObjectError.put("type", "error");
        jsonObjectError.put("message", message);
        writeJsonExample(jsonObjectError, outputFileName);
    }


    public JSONObject getInJson(String fileName) throws IOException {
        Path pathJson = storagePathIn.resolve(fileName);
        String jsonString = new String(Files.readAllBytes(pathJson));
        return new JSONObject(jsonString);
    }
    public JSONObject getOutJson(String fileName) throws IOException {
        Path pathJson = storagePathOut.resolve(fileName);
        String jsonString = new String(Files.readAllBytes(pathJson));
        return new JSONObject(jsonString);
    }

    public void writeJsonExample(JSONObject jsonObject, String outputFileName) throws IOException {
        FileWriter file = new FileWriter(String.valueOf(storagePathOut.resolve(outputFileName)));
        file.write(String.valueOf(jsonObject));
        file.close();
    }

    public void writeJson(JsonResponse response) {
        JSONObject jsonObj = new JSONObject(response.toString());
        try(FileOutputStream fos = new FileOutputStream("storage/response/output.json")) {
            fos.write(jsonObj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

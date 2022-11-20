package test.example.aikam.json;

import lombok.Getter;
import lombok.Setter;
import test.example.aikam.json.enums.TypeRequest;

@Setter
@Getter
public class ResponseError implements JsonResponse {
    private String massage;

    @Override
    public String toString() {
        return "{"+ "\n" +"\"type\":\"" + TypeRequest.ERROR.getTitle() + "\"," + "\n" +
                "\"message\":\""+ massage  + "\"" + "\n" +"}";
    }
}

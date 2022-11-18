package test.example.aikam.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseError implements Json {
    private String massage;

    @Override
    public String toString() {
        return "{"+ "\n" +"\"type\":\"error\"," + "\n" +
                "\"message\":\""+ massage  + "\"" + "\n" +"}";
    }
}

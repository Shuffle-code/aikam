package test.example.aikam.json.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeRequest {
    SEARCH("search"),
    STAT("stat"),
    ERROR("error");
    private final String title;
}

package test.example.aikam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import test.example.aikam.entity.enums.TypeRequest;

@Getter
@Setter
@AllArgsConstructor
public class Request {
    private TypeRequest typeRequest;
    private String criteria;
}

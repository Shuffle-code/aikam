package test.example.aikam.json.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeCriteria {
    LASTNAME("lastname"),
    PRODUCT_NAME("productName"),
    MIN_TIMES("minTimes"),
    MIN_EXPENSE("minExpenses"),
    MAX_EXPENSE("maxExpenses"),
    BAD_CUSTOMERS("badCustomers"),
    TYPE("type"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    TOTAL_DAYS("totalDays"),
    CUSTOMERS("customers"),
    NAME("name"),
    PURCHASES("purchases"),
    EXPENSES("expenses"),
    TOTAL_EXPENSES("totalExpenses"),
    AVG_EXPENSES("avgExpenses"),
    MESSAGE("purchases"),
    CRITERIA("criteria");
    private final String title;
}

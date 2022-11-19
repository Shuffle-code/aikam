package test.example.aikam.json;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RequestParam implements Json {
    private String lastName;
    private String productName;
    private int minTimes;
    private int minExpenses;
    private int maxExpenses;
    private Long badCustomers;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String toString() {
        return "Request{" +
                "lastName='" + lastName +
                ", productName='" + productName +
                ", minTimes=" + minTimes +
                ", minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                ", badCustomers=" + badCustomers +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public int toJSONString() {
        return 0;
    }
}

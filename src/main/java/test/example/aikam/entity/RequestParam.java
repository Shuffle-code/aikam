package test.example.aikam.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestParam {
    private String lastName;
    private String productName;
    private int minTimes;
    private int minExpenses;
    private int maxExpenses;
    private int badCustomers;
    private Date startDate;
    private Date endDate;

    @Override
    public String toString() {
        return "Request{" +
                "lastName='" + lastName + '\'' +
                ", productName='" + productName + '\'' +
                ", minTimes=" + minTimes +
                ", minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                ", badCustomers=" + badCustomers +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}

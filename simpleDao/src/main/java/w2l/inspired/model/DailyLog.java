package w2l.inspired.model;

import java.time.LocalDate;

public class DailyLog {
    LocalDate date;
    Customer customer;
    CompletionStatus status;

    public DailyLog(LocalDate date, Customer customer, CompletionStatus status) {
        this.date = date;
        this.customer = customer;
        this.status = status;
    }

    public DailyLog(Customer customer, CompletionStatus status) {
        this.date = LocalDate.now();
        this.customer = customer;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CompletionStatus getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

}

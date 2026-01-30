package w2l.inspired.dao;

import w2l.inspired.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Customer getCustomerById(int id);
    Customer getCustomerByName(String name);
    void insertCustomer(Customer t);
}


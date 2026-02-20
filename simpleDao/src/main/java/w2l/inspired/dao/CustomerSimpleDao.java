package w2l.inspired.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import w2l.inspired.model.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
@Component
public class CustomerSimpleDao implements CustomerDao{
    private final List<Customer> customers;
@Autowired
    public CustomerSimpleDao() {
        customers = new LinkedList<>();
        customers.add(new Customer(1, "Ivanov"));
        customers.add(new Customer(2, "Petrov"));
        customers.add(new Customer(3, "Sidorov"));
        customers.add(new Customer(4, "Pushkin"));
    }
    @Autowired
    public CustomerSimpleDao(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) throws NoSuchElementException {
        return customers.stream().filter(e->e.getId() == id).findFirst().get();
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customers.stream().filter(e->e.getName() == name).findFirst().get();
    }

    @Override
    public void insertCustomer(Customer t) {
        customers.add(t);
    }
}

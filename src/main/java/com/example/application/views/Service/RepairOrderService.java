package com.example.application.views.Service;

import com.example.application.data.RepairOrder;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class RepairOrderService {
    private ArrayList<RepairOrder> orders;

    public RepairOrderService() {
        orders= new ArrayList<>();
        }
        public ArrayList<RepairOrder> findAll(){
        ArrayList<RepairOrder> copy= new ArrayList<>(orders);
        return copy;
        }

    public void addOrder(RepairOrder order) {
        orders.add(order);
    }

    public void clearAll() {
        orders.clear();
    }

    public void fillTestData(int amount) {
        Faker faker = new Faker();
        //orders.clear();

        String[] deviceTypes = {
                "Reception Tablet",
                "Hotel TV",
                "Restaurant POS System",
                "Kitchen Display",
                "Office Laptop",
                "Card Payment Terminal",
                "WiFi Router",
                "Printer"
        };
        String[] urgencies = {
                "Low",
                "Normal",
                "High",
                "Emergency"};

        for(int i= 0;i<amount; i++){
            RepairOrder order=new RepairOrder();

            order.setOrderId((long)(i+1));

    order.setCreatedAt(LocalDate.now().minusDays((faker.number().numberBetween(0,365))));
            order.setCompanyName(faker.company().name());
            order.setContactPerson(faker.name().fullName());
            order.setDeviceType(deviceTypes[faker.number().numberBetween(0
    , deviceTypes.length)]);
            order.setProblemDescription(faker.lorem().sentence(12));
            order.setUrgency(urgencies[faker.number().numberBetween(0,urgencies.length)]);
            order.setEstimatedPrice(faker.number().randomDouble(2,29,999));
            order.setBusinessCustomer(true);

            orders.add(order);
        }
    }
    public void addWrongOrder(){
        RepairOrder order= new RepairOrder();

        order.setOrderId((long) (orders.size()+1));
        order.setCreatedAt(LocalDate.now());
        order.setCompanyName("Wrong Company");
        order.setContactPerson("Test Person");
        order.setDeviceType("Hotel TV");
        order.setProblemDescription("This is a test problem description");
        order.setUrgency("Normal");

        order.setEstimatedPrice(-20.0);
        order.setBusinessCustomer(true);
        orders.add(order);
    }

    @Override
    public String toString() {
        return orders.stream().
                map(order-> order.toString())
                .collect(Collectors.joining("\n"));
    }
}

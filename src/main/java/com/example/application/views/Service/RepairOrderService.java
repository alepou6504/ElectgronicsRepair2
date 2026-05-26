package com.example.application.views.Service;

import com.example.application.data.RepairOrder;
import com.github.javafaker.Faker;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
public class RepairOrderService {
    private ArrayList<RepairOrder> orders;

    public RepairOrderService() {
        orders = new ArrayList<>(1000);
    }

    public void addOrder(RepairOrder order) {
        orders.add(order);
    }

    public void clearOrders() {
        orders.clear();
    }

    public void fillTestData(int amount) {
        Faker faker = new Faker();
        orders.clear();

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

    order.setCreatedAt(LocalDate.now().minusDays((faker.number().numberBetween(0,30))));
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

    @Override
    public String toString() {
        return orders.stream().
                map(order-> order.toString())
                .collect(Collectors.joining("\n"));
    }
}

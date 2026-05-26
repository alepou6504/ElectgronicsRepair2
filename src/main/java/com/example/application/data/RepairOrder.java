package com.example.application.data;

import com.example.application.views.Service.RepairOrderService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "orderId")
@Entity
public class RepairOrder extends RepairOrderService implements Cloneable {

    @Id
    private Long orderId;

    private LocalDate createdAt;
    private String companyName;
    private String contactPerson;
    private String deviceType;
    private String problemDescription;
    private String urgency;
    private Double estimatedPrice;
    private Boolean businessCustomer;

    private static final AtomicLong sequence = new AtomicLong(1000);

    private static final String[] urgencies = {
            "Low",
            "Normal",
            "High",
            "Emergency"
    };
    public RepairOrder() {
        setOrderId();
        setCreatedAt(LocalDate.now());
        setCompanyName("Unknown Company");
        setContactPerson("Unknown Contact");
        setDeviceType("Unknown Device");
        setProblemDescription("No problem description yet");
        setUrgency("Normal");
        setEstimatedPrice(79.0);
        setBusinessCustomer(true);
    }

    public RepairOrder(
            LocalDate createdAt,
            String companyName,
            String contactPerson,
            String deviceType,
            String problemDescription,
            String urgency,
            Double estimatedPrice,
            Boolean businessCustomer
    ) {
        setOrderId();
        setCreatedAt(createdAt);
        setCompanyName(companyName);
        setContactPerson(contactPerson);
        setDeviceType(deviceType);
        setProblemDescription(problemDescription);
        setUrgency(urgency);
        setEstimatedPrice(estimatedPrice);
        setBusinessCustomer(businessCustomer);
    }

    public RepairOrder(
            Long orderId,
            LocalDate createdAt,
            String companyName,
            String contactPerson,
            String deviceType,
            String problemDescription,
            String urgency,
            Double estimatedPrice,
            Boolean businessCustomer
    ) {
        setOrderId(orderId);
        setCreatedAt(createdAt);
        setCompanyName(companyName);
        setContactPerson(contactPerson);
        setDeviceType(deviceType);
        setProblemDescription(problemDescription);
        setUrgency(urgency);
        setEstimatedPrice(estimatedPrice);
        setBusinessCustomer(businessCustomer);
    }

    public void setOrderId() {
        this.orderId = sequence.getAndIncrement();
    }
    public void setOrderId(Long orderId){
        this.orderId= orderId;
    }

    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.isBlank()) {
            throw new RepairOrderException("Company name must not be empty");
        }
        this.companyName = companyName;
    }

    public void setDeviceType(String deviceType) {
        if (deviceType == null || deviceType.isBlank()) {
            throw new RepairOrderException("Device type must not be empty");
        }
        this.deviceType = deviceType;
    }

    public void setProblemDescription(String problemDescription) {
        if (problemDescription == null || problemDescription.length() < 10) {
            throw new RepairOrderException("Problem description must have at least 10 characters");
        }
        this.problemDescription = problemDescription;
    }

    public void setUrgency(String urgency) {
        if (!Arrays.asList(urgencies).contains(urgency)) {
            throw new RepairOrderException("Wrong urgency. Must be: " + Arrays.toString(urgencies));
        }
        this.urgency = urgency;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        if (estimatedPrice == null) {
            throw new RepairOrderException("Estimated price must not be null");
        }
        if (estimatedPrice < 29) {
            throw new RepairOrderException("Minimum repair price is 29.0 EUR");
        }
        if (estimatedPrice > 999) {
            throw new RepairOrderException("Maximum repair price is 999.0 EUR");
        }
        this.estimatedPrice = estimatedPrice;
    }

    @Override
    public RepairOrder clone() {
        return new RepairOrder(
                orderId,
                createdAt,
                companyName,
                contactPerson,
                deviceType,
                problemDescription,
                urgency,
                estimatedPrice,
                businessCustomer
        );
    }
}
package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "orderId")
@Entity
public class RepairOrder implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Date cannot be in the future.")
    private LocalDate createdAt = LocalDate.now();

    @NotBlank(message = "Company name cannot be empty")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters.")
    private String companyName = "Unknown Company";

    @NotBlank(message = "Contact person is required")
    private String contactPerson = "Unknown Contact";

    @NotBlank(message = "Device type is required")
    private String deviceType = "Unknown Device";

    @NotBlank(message = "Problem description cannot be empty")
    @Size(min = 10, message = "Problem description must be at least 10 characters.")
    private String problemDescription = "No problem description yet";

    @NotBlank(message = "Urgency is required")
    @Pattern(
            regexp = "Low|Normal|High|Emergency",
            message = "Must be: Low, Normal, High or Emergency"
    )
    private String urgency = "Normal";

    @NotNull(message = "Price cannot be empty")
    @DecimalMin(value = "29.0", message = "Minimum price is 29.00€")
    @DecimalMax(value = "999.0", message = "Maximum price is 999.00€")
    private Double estimatedPrice = 79.0;

    @NotNull(message = "Please confirm you are a Business Customer")
    private Boolean businessCustomer = true;

    // Used for test data generation — orderId left null for DB auto-increment
    public RepairOrder(
            LocalDate createdAt, String companyName, String contactPerson,
            String deviceType, String problemDescription, String urgency,
            Double estimatedPrice, Boolean businessCustomer
    ) {
        this.createdAt          = createdAt;
        this.companyName        = companyName;
        this.contactPerson      = contactPerson;
        this.deviceType         = deviceType;
        this.problemDescription = problemDescription;
        this.urgency            = urgency;
        this.estimatedPrice     = estimatedPrice;
        this.businessCustomer   = businessCustomer;
    }

    @Override
    public RepairOrder clone() {
        try {
            return (RepairOrder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed for RepairOrder", e);
        }
    }
}
/* Die Klasse RepairOrder stellt mein zentrales Domänenmodell dar. Ich habe sie als
JPA-Entität deklarieert, damit sie direkt persistiert werden kann Um die Datensicherheit
und Geschäftsregeln bereits auf unterster Ebene zu erzwingen, nutze ich die
Jakarta.validation-Annotationen.
  Code-dupliezierung un klobige Boilerplate-Strukturen habe ich durch den EInsatz
von Lombok-Annotationen vermieden. Ein wichtiges architektonisches Detail ist zudem
die Implementierung des Cloneable-Interfaces. Die überschrieben clone()-Methode ermöglicht es
der User in der UI, Entitäten für Bearbeitungsprozesse im Formular tiefzukopieren.
So stelle ich sicher, dass fehlerhafte oder abgebrochene User Inputs das Original-Objekt
im Speicher nicht korrumpieren.
 */
package com.example.application.service;

import com.example.application.data.RepairOrder;
import com.example.application.data.RepairOrderException;
import com.example.application.data.RepairOrderRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RepairOrderService {

    private final RepairOrderRepository repository;

    @Autowired // Spring automatically injects the real database implementation here
    public RepairOrderService(RepairOrderRepository repository) {
        this.repository = repository;

        // Auto-prefill the database with mock records if it's currently empty
        if (this.repository.count() == 0) {
            fillTestData(25);
        }
    }

    public List<RepairOrder> findAll() {
        return repository.findAll();
    }

    public void save(RepairOrder order) {
        repository.save(order);
    }

    public void clearAll() {
        repository.deleteAll();
    }

    public void fillTestData(int amount) {
        Faker faker = new Faker();

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
                "Emergency"
        };

        for (int i = 0; i < amount; i++) {
            RepairOrder order = new RepairOrder(
            LocalDate.now().minusDays(faker.number().numberBetween(0, 365)),
                    faker.company().name(),
                    faker.name().fullName(),
                    deviceTypes[faker.number().numberBetween(0, deviceTypes.length)],
                    faker.lorem().sentence(12),
                    urgencies[faker.number().numberBetween(0, urgencies.length)],
                    faker.number().randomDouble(2, 29, 999),
                    true
            );

            repository.save(order);
        }
    }

    public void addWrongOrder() {
        RepairOrder order = new RepairOrder(
                LocalDate.now(),
                "Wrong Company",
                "Test Person",
                "Hotel TV",
                "This is a test problem description",
                "Normal",
                -20.0,   // intentionally violates @DecimalMin(29.0) → triggers validation error
                true
        );
        repository.save(order);
    }

    public void removeRepairOrder(Long orderId) {
        if (orderId == null) {
            throw new RepairOrderException("No order ID provided.");
        }
        if (!repository.existsById(orderId)) {
            throw new RepairOrderException("Order with ID " + orderId + " not found.");
        }
        repository.deleteById(orderId);
    }

    public void increasePrice(Long orderId) {
        if (orderId == null) {
            throw new RepairOrderException("No order ID provided.");
        }
        RepairOrder order = repository.findById(orderId)
                .orElseThrow(() -> new RepairOrderException("Order with ID " + orderId + " not found."));

        order.setEstimatedPrice(order.getEstimatedPrice() + 10.0);
        repository.save(order);
    }

    @Override
    public String toString() {
        return repository.findAll().stream()
                .map(RepairOrder::toString)
                .collect(Collectors.joining("\n"));
    }
}

/* Der RepairOrderService ist die zentrale Instanz für unser Geschäftslogik. Er isoliert
die Vaadin-Views komplett von direkten Datenbankzugriffen. Ein wichtiges Detail
im Konstruktor ist die automatische DB-Check: ist die Tabelle beim Start leer,
nutze ich die Faker-Bibliothek, um sofort 25 praxisnahe Reparaturauftrage für
elektronische Geräte zu generieren.
   In den Methoden removeRepairOrder and increasePrice setze ich auf defensive
Programmierung. Bevor Daten manipuliert werden. validiere ich die Existenz der
Entität. Schlägt diese Prüfung fehl. werde ich meine spezifische RepairOrderException
Zudem demonstriere ich mit der Methode addWrongOrder eine gezieltes Stresstesting des
Validierungs-Frameworks, indem ich einen negativen Preic die jakarta.validation-Grenzen
meines Domänenmodells bewusst verletze.
 */
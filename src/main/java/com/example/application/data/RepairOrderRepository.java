package com.example.application.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    // Extends JpaRepository to dynamically generate database actions at runtime.
}

/* Das RepairOrderRepository bildet die datenbanknahe Schicht meiner Anwendung.
Indem ich das Interface @repository annotiere und vonJpaRepository ableite, nutze
ich die mächtige Abstraktion von Spring Data JPA.
   Dadurch stehen uns sämtliche Standrad-CRUD-Operationen, Paginierunfs- und
Sortierfunktionen ssofort typensicher  zur Verfügung, ohne dass wir feheranfälliges,
manuelles SQL oder JPQL schreiben müssen. Spring generiert die konkrete Implementierung
dieser Schnittstelle vollautomatisch zu Laufzeit und bindet sie sauber in das
Transaktionsmanagement der Anwendung ein.
 */
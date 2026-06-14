package com.example.application.data;


public class RepairOrderException extends RuntimeException {
    public RepairOrderException(String message) {
        super(message);
    }
}
/* Mit der Kalsse RepairOrderException habe ich eine dedizierte Fach-Exception
für mein Domänenmodell implementiert. Sie dient dazu, spezufische Verletzungen von
Geschäftsregeln innerhalb der Reparaturverwaltung sauber und isoliert zu
signalisieren.
   Architektonisch habe ich mich hier für eine Unchecked Exception entschieden, indem
ich von RunTimeException erbe. Das hat den großen Vorteil. dass ich den Quellcode
frei von klobigen Methodensignaturen und erzwungenen throws-Blöcken halten können.
Die Fehlerneldung wird flexibel nach oben gereicht und kann dort zentral, abgefangen
und den Benutzer ausgegeben werden.
 */
/* Da RepairOrderException eine Geschäftsregel verletzt (price too low)
ist die RunTimeException die richtige Wahl, sauber und ohne Boilerplate
 */
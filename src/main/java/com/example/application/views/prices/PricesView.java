package com.example.application.views.prices;

import com.example.application.views.MainLayout;
import com.example.application.views.electronicsrepair.ElectronicsRepair;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@PageTitle("AlePou - Prices")
@Route(value = "prices", layout = MainLayout.class)
public class PricesView extends VerticalLayout {

    public PricesView() {
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        add(ElectronicsRepair.getHeader());

        H1 title = new H1("Transparency Matrix - Core Pricing");
        title.getStyle()
                .set("color", "#dcb3ff")
                .set("font-family", "'Courier New', monospace")
                .set("text-shadow", "0 0 8px rgba(218,179,255,0.6)")
                .set("margin-bottom", "20px");
        add(title);

        Grid<PriceItem> priceGrid = new Grid<>();
        priceGrid.setSizeFull();
        priceGrid.getStyle()
                .set("background", "rgba(26, 9, 51, 0.8)")
                .set("border", "1px solid rgba(142, 68, 173, 0.4)")
                .set("border-radius", "12px");

        priceGrid.addColumn(PriceItem::getService).setHeader("Tactical Stream / Service");
        priceGrid.addColumn(PriceItem::getTimeframe).setHeader("Est. Timeframe");

        priceGrid.addComponentColumn(item -> {
            Span priceSpan = new Span(item.getPrice());
            priceSpan.getStyle()
                    .set("color", "#00ff66")
                    .set("font-weight", "bold")
                    .set("font-family", "'Courier New', monospace")
                    .set("text-shadow", "0 0 5px rgba(0,255,102,0.5)");
            return priceSpan;
        }).setHeader("Base Price (EUR)");

        List<PriceItem> priceList = new ArrayList<>();
        priceList.add(new PriceItem(" Micro-Soldering & Board Repair", "1 - 3 Days", "from €89.00"));
        priceList.add(new PriceItem(" Enterprise Device Recovery (Screen/Battery)", "Same Day / 24h", "from €49.00"));
        priceList.add(new PriceItem("️ Workplace Workstation Tuning & Overhaul", "1 - 2 Days", "from €69.00"));
        priceList.add(new PriceItem(" POS & Payment Terminal Patching", "Express / 12h", "from €119.00"));
        priceList.add(new PriceItem(" Hotel & Reception Display Overhaul", "2 - 4 Days", "from €149.00"));
        priceList.add(new PriceItem(" Cyber Infrastructure Diagnostics", "1 - 3 Days", "from €95.00"));
        priceList.add(new PriceItem(" Express Diagnostics / Urgent Priority", "Within 2 Hours", "€35.00 flat"));

        priceGrid.setItems(priceList);
        add(priceGrid);
    }

    /**
     * Einfache Hilfsklasse für die Tabellen-Einträge
     */
    public static class PriceItem {
        private final String service;
        private final String timeframe;
        private final String price;

        public PriceItem(String service, String timeframe, String price) {
            this.service = service;
            this.timeframe = timeframe;
            this.price = price;
        }

        public String getService() { return service; }
        public String getTimeframe() { return timeframe; }
        public String getPrice() { return price; }
    }
}


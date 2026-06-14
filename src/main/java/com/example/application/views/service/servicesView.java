package com.example.application.views.service;

import com.example.application.views.MainLayout;
import com.example.application.views.electronicsrepair.ElectronicsRepair;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AlePou - Offers")
@Route(value = "services", layout = MainLayout.class)
public class servicesView extends VerticalLayout {

    public servicesView() {
        addClassName("content-page");
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(ElectronicsRepair.getHeader());

        H1 title = new H1("Our Tactical Repair Streams");
        title.getStyle()
                .set("color", "#dcb3ff")
                .set("font-family", "'Courier New', monospace")
                .set("text-shadow", "0 0 8px rgba(218,179,255,0.6)")
                .set("margin-bottom", "30px");
        add(title);

        HorizontalLayout cardsContainer = new HorizontalLayout();
        cardsContainer.setWidthFull();
        cardsContainer.getStyle().set("flex-wrap", "wrap");
        cardsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        cardsContainer.setSpacing(true);

        addServiceCard(cardsContainer, "🔬 Micro-Soldering & Board Repair",
                "Fixing broken traces, burnt capacitors, and micro-soldering chips on motherboards under high precision.");

        addServiceCard(cardsContainer, "📱 Enterprise Device Recovery",
                "Full screen, battery, and internal hardware overhaul for business tablets, smartphones, and mobile scanners.");

        addServiceCard(cardsContainer, "🖥️ Workplace Workstation Tuning",
                "Hardware upgrades, thermal paste replacement, and high-tier component repairs for office laptops and desktops.");

        addServiceCard(cardsContainer, "📟 POS & Payment Terminal Patching",
                "Rapid component swap and structural maintenance for restaurant POS systems and digital card terminals.");

        addServiceCard(cardsContainer, "📺 Hotel & Reception Display Overhaul",
                "Backlight repairs, power board swaps, and matrix corrections for digital signage displays and hospitality TVs.");

        addServiceCard(cardsContainer, "🌐 Cyber Infrastructure Diagnostics",
                "Hardware-level maintenance for high-traffic WiFi routers, network switches, and custom server racks.");

        add(cardsContainer);
    }
    /**
     * Hilfsmethode, um stylische Cyberpunk-Servicekarten zu generieren
     */
    private void addServiceCard(HorizontalLayout container, String serviceTitle, String serviceDesc) {
        VerticalLayout card = new VerticalLayout();
        card.setWidth("340px");
        card.setHeight("200px");
        card.setPadding(true);
        card.getStyle()
                .set("background", "rgba(26, 9, 51, 0.8)")
                .set("border", "1px solid rgba(142, 68, 173, 0.6)")
                .set("border-radius", "8px")
                .set("box-shadow", "0 0 10px rgba(155, 0, 232, 0.2)")
                .set("transition", "all 0.3s ease");

        card.getElement().addEventListener("mouseover", e -> {
            card.getStyle().set("border-color", "#00ff66");
            card.getStyle().set("box-shadow", "0 0 15px rgba(0, 255, 102, 0.4)");
        });
        card.getElement().addEventListener("mouseout", e -> {
            card.getStyle().set("border-color", "rgba(142, 68, 173, 0.6)");
            card.getStyle().set("box-shadow", "0 0 10px rgba(155, 0, 232, 0.2)");
        });

        H3 title = new H3(serviceTitle);
        title.getStyle()
                .set("color", "#00ff66") // Neongrüner Text
                .set("font-family", "'Courier New', monospace")
                .set("margin", "0 0 10px 0");

        Paragraph desc = new Paragraph(serviceDesc);
        desc.getStyle()
                .set("color", "#f1e9ff")
                .set("font-size", "0.95rem")
                .set("margin", "0");

        card.add(title, desc);
        container.add(card);
    }
}


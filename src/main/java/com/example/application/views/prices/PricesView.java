package com.example.application.views.prices;

import com.example.application.views.MainLayout;
import com.example.application.views.electronicsrepair.ElectronicsRepair;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Prices")
@Route(value = "prices", layout = MainLayout.class)
public class PricesView extends VerticalLayout {

    public PricesView() {
        addClassName("content-page");

        H1 title = new H1("Prices");
        title.addClassName("neon-title");

        Paragraph text = new Paragraph(
                "Our prices depend on the device, repair type, spare parts, and urgency." +
                        " Contact AlePou for a fast and fair repair offer."
        );
        text.addClassName("neon-text");

        add(ElectronicsRepair.getHeader());

    }
}

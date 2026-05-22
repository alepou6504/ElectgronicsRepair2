package com.example.application.views.offers;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Services")
@Route(value = "services", layout = MainLayout.class)
public class ServicesView extends VerticalLayout {

    public ServicesView() {
        addClassName("content-page");

        H1 title = new H1("Services");
        title.addClassName("neon-title");

        Paragraph text = new Paragraph(
                "We repair and support electronics for hotels, restaurants, cafés, offices, " +
                        "reception areas, kitchens, and service departments."
        );
        text.addClassName("neon-text");

        add(title, text);
    }
}

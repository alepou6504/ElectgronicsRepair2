package com.example.application.views.Service;

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
        String[] serviceTypes = {
                "Screen Repair",
                "Battery Replacement",
                "Network Problem",
                "Software Problem",
                "Hardware Repair",
                "Maintenance"
        };

        H1 title = new H1("Services");
        title.addClassName("neon-title");

        VerticalLayout servicesLayout = new VerticalLayout();
        servicesLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        for (String serviceType : serviceTypes) {
            Paragraph service = new Paragraph(serviceType);
            service.addClassName("neon-text");
            servicesLayout.add(service);
        }

        add(title, servicesLayout);

    }
}

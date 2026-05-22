package com.example.application.views.electronicsrepair;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
public class ElectronicsRepair extends Div {

    public ElectronicsRepair() {
        addClassName("home-page");

        Image logo = new Image("images/logo.jpeg", "AlePou logo");
        logo.addClassName("home-logo");

        H1 title = new H1("AlePou");
        title.addClassName("neon-title");

        H2 slogan = new H2("Smart repair solutions for modern hospitality and gastronomy.");
        slogan.addClassName("neon-slogan");

        Paragraph advText = new Paragraph(
                "AlePou provides modern, reliable, and fast electronics repair solutions " +
                        "for hotels, restaurants, cafés, and other gastronomic businesses. " +
                        "We understand that working technology is essential for smooth daily operations, " +
                        "guest satisfaction, and professional service. From damaged devices and technical " +
                        "failures to maintenance and quick troubleshooting, AlePou offers ready-to-use " +
                        "solutions that help your business avoid downtime."
        );
        advText.addClassName("neon-text");
        Button cta = new Button("View Offers");
        cta.addClassName("neon-button");
        cta.addClickListener(event -> UI.getCurrent().navigate("offers"));

        add(logo, title, slogan, advText, cta);

    }
}
package com.example.application.views.electronicsrepair;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("AlePou")
@Route(value = "", layout = MainLayout.class)
public class ElectronicsRepair extends VerticalLayout {
    public ElectronicsRepair() {
        setSpacing(false);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getHeader());

        Paragraph advText = new Paragraph(
                "AlePou provides modern, reliable, and fast electronics repair solutions " +
                        "for hotels, restaurants, cafés, and other gastronomic businesses."
        );
        advText.addClassName("neon-text");

        add(advText);
    }

    public static Component getHeader() {
        Image logo = new Image("images/logo.jpeg", "AlePou logo");
        logo.addClassName("home-logo");

        H1 companyName = new H1("AlePou");
        companyName.addClassName("neon-title");

        H2 slogan = new H2("Smart repair solutions for modern hospitality and gastronomy.");
        slogan.addClassName("neon-slogan");

        VerticalLayout headerLayout = new VerticalLayout(logo, companyName, slogan);
        headerLayout.setSpacing(false);
        headerLayout.setPadding(false);
        headerLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return headerLayout;

    }
}
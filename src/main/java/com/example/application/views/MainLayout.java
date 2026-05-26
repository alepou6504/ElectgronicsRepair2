package com.example.application.views;

import com.example.application.views.electronicsrepair.ElectronicsRepair;
import com.example.application.views.Service.ServicesView;
import com.example.application.views.prices.PricesView;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainLayout extends AppLayout {

    public MainLayout() {
        UI.getCurrent().getElement().getThemeList().add(Lumo.DARK);

        DrawerToggle toggle = new DrawerToggle();

        H2 appName = new H2("AlePou");
        appName.addClassName("layout-title");

        Header header = new Header(toggle, appName);
        header.addClassName("main-header");

        addToNavbar(header);

        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Home", ElectronicsRepair.class));
        nav.addItem(new SideNavItem("Offers", ServicesView.class));
        nav.addItem(new SideNavItem("Prices", PricesView.class));

        Scroller scroller = new Scroller(nav);
        addToDrawer(scroller);
    }
}
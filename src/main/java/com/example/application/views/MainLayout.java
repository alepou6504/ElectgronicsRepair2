package com.example.application.views;

import com.example.application.views.orders.OrdersView;
import com.example.application.views.service.servicesView;
import com.example.application.views.electronicsrepair.ElectronicsRepair;
import com.example.application.views.prices.PricesView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouterLayout;

public class MainLayout extends AppLayout implements RouterLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H2 appName = new H2("AlePou");
        appName.addClassName("layout-title");
        appName.getStyle().set("margin", "0");

        Image navLogo= new Image("images/logo.jpeg", "AlePouLogo");
        navLogo.setWidth("35px");
        navLogo.setHeight("35px");
        navLogo.getStyle()
                .set("border-radius", "50%")
                .set("box-shadow", "0 0 10px #bd00ff, 0 0 20px #9b00e8")
                .set("margin-left", "10px");
        HorizontalLayout titleLayout = new HorizontalLayout(toggle, appName, navLogo);
        titleLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        titleLayout.setSpacing(true);

        Header header = new Header(titleLayout);
        header.addClassName("main-header");

        addToNavbar(header);

        SideNav nav = new SideNav();
        SideNavItem homeItem = new SideNavItem("Home", ElectronicsRepair.class);
        homeItem.setPath("");
        nav.addItem(homeItem);
        nav.addItem(new SideNavItem("Services", servicesView.class));
        nav.addItem(new SideNavItem("Pricelist", PricesView.class));
        nav.addItem(new SideNavItem("Orders", OrdersView.class));
        Scroller scroller = new Scroller(nav);
        addToDrawer(scroller);
    }
}

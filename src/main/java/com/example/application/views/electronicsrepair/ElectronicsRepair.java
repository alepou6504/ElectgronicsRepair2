package com.example.application.views.electronicsrepair;
import com.example.application.data.RepairOrder;
import com.example.application.views.MainLayout;
import com.example.application.views.Service.RepairOrderService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.checkbox.Checkbox;

@PageTitle("AlePou")
@Route(value = "", layout = MainLayout.class)
public class ElectronicsRepair extends VerticalLayout {
    private final Grid<RepairOrder> grid = new Grid<>(RepairOrder.class, false);
    private final RepairOrderService repairOrderService;

    public ElectronicsRepair(RepairOrderService RepairOrderService) {
        this.repairOrderService = RepairOrderService;
        if(repairOrderService.findAll().isEmpty()){
            repairOrderService.fillTestData(10);
        }

        setSpacing(false);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getHeader());

        Paragraph advText = new Paragraph(
                "AlePou provides modern, reliable, and fast electronics repair solutions " +
                        "for hotels, restaurants, cafés, and other gastronomic businesses."
        );
        advText.addClassName("neon-text");
        add(advText);

        configureGrid();
        add(grid);
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

    private void configureGrid() {

        grid.addColumn(RepairOrder::getOrderId
        ).setHeader("Order Id").setSortable(true);
        grid.addColumn(RepairOrder::getCreatedAt
        ).setSortable(true);
        grid.addColumn(RepairOrder::getCompanyName
        ).setHeader("Company").setSortable(true);
        grid.addColumn(RepairOrder::getContactPerson
        ).setHeader("Contact person").setSortable(true);
        grid.addColumn(RepairOrder::getDeviceType
        ).setHeader("Device Type").setSortable(true);
        grid.addColumn(RepairOrder::getEstimatedPrice
        ).setHeader("Est. Price").setSortable(true);
        grid.addColumn(RepairOrder::getUrgency
        ).setHeader("Urgency").setSortable(true);

        grid.addComponentColumn(order -> {
                    Checkbox cb = new Checkbox(order.getBusinessCustomer());
                    cb.setReadOnly(true);
                    return cb;
                })
                .setHeader("Business Customer")
                .setSortable(true)
                .setComparator(r -> r.getBusinessCustomer());
        Span problemSpan= new Span("Problem");
        problemSpan.getStyle().set("color", "inherit");
        /*Image toolIcon = new Image("images/logo.jpeg", "AlePou logo");
        toolIcon.setWidth("24px");*/
        grid.addColumn(r -> r.getProblemDescription())
                .setHeader(new HorizontalLayout(problemSpan))
                .setSortable(true);

        grid.setItems(repairOrderService.findAll());
        grid.setWidthFull();
        grid.setHeight("400px");
    }

    public Component ComponentgetHeader() {
        Image logo = new Image("logo.jpeg", "AlePou logo");
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
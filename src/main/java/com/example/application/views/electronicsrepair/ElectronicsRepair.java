package com.example.application.views.electronicsrepair;

import com.example.application.data.RepairOrder;
import com.example.application.data.RepairOrderException;
import com.example.application.service.RepairOrderService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("AlePou - Command Center")
@Route(value = "", layout = MainLayout.class)
public class ElectronicsRepair extends VerticalLayout {

    private final Grid<RepairOrder> grid = new Grid<>(RepairOrder.class, false);
    private final RepairOrderService repairOrderService;

    @Autowired
    public ElectronicsRepair(RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;

        setSizeFull();
        setSpacing(true);
        grid.setSizeFull();

        add(getHeader());
        add(buildPitchBlock());
        add(grid);

        configureGridColumns();
        prepareAndLoadData();
    }

    public static Component getHeader() {
        Image headerLogo = new Image("images/logo.jpeg", "AlePou Logo");
        headerLogo.setWidth("110px");
        headerLogo.setHeight("110px");
        headerLogo.getStyle()
                .set("border-radius", "50%")
                .set("box-shadow", "0 0 15px #bd00ff, 0 0 30px #9b00e8")
                .set("margin-bottom", "10px");

        H1 companyName = new H1("AlePou");
        companyName.getStyle()
                .set("font-family", "'Courier New', Courier, monospace")
                .set("font-size", "4.5rem")
                .set("font-weight", "900")
                .set("margin", "0")
                .set("color", "#00ff66")
                .set("text-shadow", "0 0 5px #00ff66, 0 0 15px #8e44ad");

        H2 subName = new H2("... cyber electronics repair station ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "#dcb3ff")
                .set("font-style", "italic")
                .set("font-size", "1.2rem");

        VerticalLayout headerLayout = new VerticalLayout(headerLogo, companyName, subName);
        headerLayout.setSpacing(false);
        headerLayout.setPadding(false);
        headerLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        headerLayout.getStyle().set("margin-bottom", "20px");

        return headerLayout;
    }

    private Component buildPitchBlock() {
        VerticalLayout pitchLayout = new VerticalLayout();
        pitchLayout.setWidthFull();
        pitchLayout.setPadding(true);
        pitchLayout.getStyle()
                .set("background", "linear-gradient(135deg, rgba(26,9,51,0.6), rgba(142,68,173,0.2))")
                .set("border", "1px solid #00ff66")
                .set("border-radius", "12px")
                .set("box-shadow", "0 0 15px rgba(0,255,102,0.1)")
                .set("margin-bottom", "15px");

        H3 pitchHeadline = new H3(" WE REVIVE DEAD HARDWARE. NO EXCUSES. ");
        pitchHeadline.getStyle()
                .set("color", "#00ff66")
                .set("font-family", "'Courier New', monospace")
                .set("margin", "0 0 10px 0")
                .set("text-shadow", "0 0 5px #00ff66");

        Paragraph pitchBody = new Paragraph(
                "Welcome to AlePou Cybernetics. From bricked B2B terminal networks to delicate micro-soldering " +
                        "board traces, we slice through complex hardware issues with tactical precision. " +
                        "Fast diagnostics, enterprise-grade warranty tracking, and absolute transparency. " +
                        "Lock in your repair stream below."
        );
        pitchBody.getStyle()
                .set("color", "#f1e9ff")
                .set("font-size", "1.1rem")
                .set("margin", "0");

        pitchLayout.add(pitchHeadline, pitchBody);
        return pitchLayout;
    }

    private void configureGridColumns() {
        grid.addColumn(RepairOrder::getOrderId)
                .setHeader("Order ID").setSortable(true).setWidth("90px").setFlexGrow(0);
        grid.addColumn(RepairOrder::getCreatedAt)
                .setHeader("Received Date").setSortable(true);
        grid.addColumn(RepairOrder::getCompanyName)
                .setHeader("Company").setSortable(true);
        grid.addColumn(RepairOrder::getContactPerson)
                .setHeader("Contact Person").setSortable(true);
        grid.addColumn(RepairOrder::getDeviceType)
                .setHeader("Device Model").setSortable(true);
        grid.addColumn(RepairOrder::getUrgency)
                .setHeader("Urgency").setSortable(true);
        grid.addColumn(RepairOrder::getEstimatedPrice)
                .setHeader("Cost (EUR)").setSortable(true);

        grid.addComponentColumn(order -> {
                    Checkbox checkbox = new Checkbox(
                            order.getBusinessCustomer() != null && order.getBusinessCustomer()
                    );
                    checkbox.setReadOnly(true);
                    return checkbox;
                }).setHeader("B2B Account").setSortable(true)
                .setComparator(RepairOrder::getBusinessCustomer);

        grid.addComponentColumn(order -> {
            Button deleteBtn = new Button("Delete", e -> deleteOrder(order.getOrderId()));
            deleteBtn.getStyle()
                    .set("background-color", "#3d0033")
                    .set("color", "#ff3366")
                    .set("border", "1px solid #ff3366")
                    .set("border-radius", "6px");

            Button increasePriceBtn = new Button("+10€", e -> increasePrice(order.getOrderId()));
            increasePriceBtn.getStyle()
                    .set("background-color", "#0a2200")
                    .set("color", "#39ff14")
                    .set("border", "1px solid #39ff14")
                    .set("border-radius", "6px");

            HorizontalLayout actions = new HorizontalLayout(deleteBtn, increasePriceBtn);
            actions.setSpacing(true);
            return actions;
        }).setHeader("Actions").setSortable(false);
    }

    private void prepareAndLoadData() {
        repairOrderService.clearAll();
        repairOrderService.fillTestData(10);
        grid.setItems(repairOrderService.findAll());
    }

    private void deleteOrder(Long orderId) {
        try {
            repairOrderService.removeRepairOrder(orderId);
            refreshGrid();
        } catch (RepairOrderException e) {
            Notification.show(e.getMessage());
        }
    }

    private void increasePrice(Long orderId) {
        try {
            repairOrderService.increasePrice(orderId);
            refreshGrid();
        } catch (RepairOrderException e) {
            Notification.show(e.getMessage());
        }
    }

    private void refreshGrid() {
        grid.setItems(repairOrderService.findAll());
    }
}
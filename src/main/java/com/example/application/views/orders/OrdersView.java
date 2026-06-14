package com.example.application.views.orders;

import com.example.application.data.RepairOrder;
import com.example.application.data.RepairOrderException;
import com.example.application.service.RepairOrderService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("AlePou - Repair Orders")
@Route(value = "orders", layout = MainLayout.class)
public class OrdersView extends VerticalLayout {

    private final Grid<RepairOrder> grid = new Grid<>(RepairOrder.class, false);
    private final RepairOrderService repairOrderService;

    @Autowired
    public OrdersView(RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;

        setSizeFull();
        setSpacing(true);

        H2 title = new H2("Repair Orders");
        title.getStyle()
                .set("color", "#39ff14")
                .set("font-family", "'Courier New', monospace")
                .set("text-shadow", "0 0 8px rgba(57,255,20,0.5)");

        add(title);
        add(buildToolbar());

        grid.setSizeFull();
        add(grid);

        configureGridColumns();
        refreshGrid();
    }

    private HorizontalLayout buildToolbar() {
        Button removeAllBtn = new Button("Remove all orders", e -> {
            repairOrderService.clearAll();
            refreshGrid();
        });
        removeAllBtn.getStyle()
                .set("background-color", "#3d0033")
                .set("color", "#ff3366")
                .set("border", "1px solid #ff3366")
                .set("border-radius", "8px")
                .set("font-weight", "bold");

        Button addTenBtn = new Button("Add 10 orders", e -> {
            repairOrderService.fillTestData(10);
            refreshGrid();
        });
        addTenBtn.getStyle()
                .set("background-color", "#0a2200")
                .set("color", "#39ff14")
                .set("border", "1px solid #39ff14")
                .set("border-radius", "8px")
                .set("font-weight", "bold");

        Button addWrongBtn = new Button("Add WRONG order", e -> {
            repairOrderService.addWrongOrder();
            refreshGrid();
        });
        addWrongBtn.getStyle()
                .set("background-color", "#1a1000")
                .set("color", "#ffaa00")
                .set("border", "1px solid #ffaa00")
                .set("border-radius", "8px")
                .set("font-weight", "bold");

        HorizontalLayout toolbar = new HorizontalLayout(removeAllBtn, addTenBtn, addWrongBtn);
        toolbar.setSpacing(true);
        return toolbar;
    }

    private void configureGridColumns() {
        Div scrollWrapper = new Div(grid);
        scrollWrapper.getStyle()
                .set("overflow-x", "auto")
                .set("width", "100%")
                .set("-webkit-overflow-scrolling", "touch");
        grid.setWidth("1600px");
        grid.setHeight("500px");
        add(scrollWrapper);
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
            Button deleteBtn = new Button("Delete", e -> {
                try {
                    repairOrderService.removeRepairOrder(order.getOrderId());
                    refreshGrid();
                } catch (RepairOrderException ex) {
                    Notification.show(ex.getMessage());
                }
            });
            deleteBtn.getStyle()
                    .set("background-color", "#3d0033")
                    .set("color", "#ff3366")
                    .set("border", "1px solid #ff3366")
                    .set("border-radius", "6px");

            Button increasePriceBtn = new Button("+10€", e -> {
                try {
                    repairOrderService.increasePrice(order.getOrderId());
                    refreshGrid();
                } catch (RepairOrderException ex) {
                    Notification.show(ex.getMessage());
                }
            });
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

    private void refreshGrid() {
        grid.setItems(repairOrderService.findAll());
    }
}
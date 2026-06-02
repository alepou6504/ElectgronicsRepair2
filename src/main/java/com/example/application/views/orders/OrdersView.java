package com.example.application.views.orders;

import com.example.application.data.RepairOrder;
import com.example.application.data.RepairOrderException;
import com.example.application.views.MainLayout;
import com.example.application.views.Service.RepairOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;



@PageTitle("Repair Orders")
@Route(value= "orders", layout= MainLayout.class)
public class OrdersView extends VerticalLayout {
    private final Button buttonRemoveAll = new Button("Remove all orders");
    private final Button buttonAdd10= new Button("Add 10 orders");
    private final Button buttonAddWrong= new Button("Add WRONG order");
    private final Grid<RepairOrder> grid = new Grid<>(RepairOrder.class, true);
    private final RepairOrderService repairOrderService;

    public OrdersView(@Autowired RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;

        setSpacing(true);
        setSizeFull();

        HorizontalLayout buttons= new HorizontalLayout(buttonRemoveAll,
                buttonAdd10, buttonAddWrong);
        buttons.setSpacing(true);
        grid.setSizeFull();
        buttonRemoveAll.addClickListener(event-> removeAllOrders());
        buttonAdd10.addClickListener(event-> add100orders());
        buttonAddWrong.addBlurListener(event-> addWrongOrder());
        add(buttons, grid);
        reload();
    }

    private void addWrongOrder() {
        try {
            repairOrderService.addWrongOrder();
            reload();
        } catch (RepairOrderException e){
            ConfirmDialog dialog= new ConfirmDialog();
            dialog.setHeader("Error");
            dialog.setText(e.getMessage());
            dialog.setConfirmText("OK");
            dialog.open();
        }
    }

    private void removeAllOrders(){
        repairOrderService.clearAll();
        buttonRemoveAll.setEnabled(false);
        reload();
    }
    private void add100orders(){
        repairOrderService.fillTestData(10);
        buttonRemoveAll.setEnabled(true);
        reload();
    }

    private void reload() {
        grid.setItems(repairOrderService.findAll());
        buttonRemoveAll.setEnabled(!repairOrderService.findAll().isEmpty());
    }
}


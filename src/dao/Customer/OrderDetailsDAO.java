package dao.Customer;

import dao.CrudDAO;
import entity.OrderDetails;
import javafx.collections.ObservableList;
import tdm.OrderManageTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, String> {
    public int getNoOfItems(String orderId) throws SQLException, ClassNotFoundException;

    public ObservableList<OrderManageTM> getDataForManageOrder(String orderId) throws SQLException, ClassNotFoundException;

    public boolean updateOrderDetailsByItemCode(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDetails> searchByItemCode(String code) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetails> searchByOrderId(String code) throws SQLException, ClassNotFoundException;
}

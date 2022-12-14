package dao.Customer;

import dao.SuperDAO;
import dto.AdminLoginDTO;
import dto.CashierLoginDTO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException;

    AdminLoginDTO getAdmin() throws SQLException, ClassNotFoundException;

    CashierLoginDTO getCashier() throws SQLException, ClassNotFoundException;

    String getLastOrderId() throws SQLException, ClassNotFoundException;
}

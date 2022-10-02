package bo.Customer.impl;

import bo.Customer.LoginBO;

import dao.Customer.QueryDAO;
import dao.DAOFactory;

import dto.AdminLoginDTO;
import dto.CashierLoginDTO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean adminLoginSuccess(String usrName, String pssWord) throws SQLException, ClassNotFoundException {
        AdminLoginDTO admin = queryDAO.getAdmin();
        String adminUsrName = admin.getAdminUsrName();
        String adminPssword = admin.getAdminPassword();
        return usrName.equals(adminUsrName) && pssWord.equals(adminPssword);
    }

    @Override
    public boolean cashierLoginSuccess(String usrName, String pssWord) throws SQLException, ClassNotFoundException {
        CashierLoginDTO cashier = queryDAO.getCashier();
        String cashierUsrName = cashier.getCashierUsrName();
        String cashierPssword = cashier.getCashierPassword();
        return usrName.equals(cashierUsrName) && pssWord.equals(cashierPssword);
    }
}

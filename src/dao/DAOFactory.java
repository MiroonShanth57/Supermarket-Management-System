package dao;


public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new dao.Customer.impl.CustomerDAOImpl();
            case ITEM:
                return new dao.Customer.impl.ItemDAOImpl();
            case ORDER:
                return new dao.Customer.impl.OrderDAOImpl();
            case ORDERDETAILS:
                return new dao.Customer.impl.OrderDetailsDAOImpl();
            case QUERYDAO:
                return new dao.Customer.impl.QueryDAOImpl();
            case DISCOUNT:
                return new dao.Customer.impl.DiscountCodesDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO, DISCOUNT
    }

}

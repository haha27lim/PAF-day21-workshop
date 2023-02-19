package sg.edu.nus.iss.app.day21workshopSqlRowSet.repository;

public class Queries {
    
    public static final String SQL_SELECT_ALL_CUSTOMERS= "select * from customers limit ? offset ?";
    public static final String SQL_SELECT_CUSTOMERS_BY_ID= "select * from customers where id = ?";
    public static final String SQL_SELECT_ORDERS_CUSTOMER_ID= """
                                    select * from orders join customers 
                                    on orders.customer_id = customers.id where customers.id = ?
                                    """;
}

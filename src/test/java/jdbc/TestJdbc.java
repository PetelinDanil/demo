package jdbc;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestJdbc {

    static final String DB_URL = "jdbc:postgresql://192.168.0.103/modelmapper";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    @Test
    void testConnectionJdbc(){
        System.out.println("--- Testing connection to PostgreSQL JDBC");
        Connection connect = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager
                    .getConnection(DB_URL, USER, PASS);

            stmt = connect.createStatement();
            rs = stmt.executeQuery("select * from products");
            while(rs.next()){
                System.out.println("Product id="+rs.getLong("id")
                        +", Description="+rs.getString(2)
                        +", Name="+rs.getString("name")
                        +", Price="+rs.getFloat("price")
                        +", Remain="+rs.getInt("remain"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

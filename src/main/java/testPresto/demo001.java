package testPresto;

import java.sql.*;

public class demo001 {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
/*        Class.forName("com.facebook.presto.jdbc.PrestoDriver");
        Connection connection = DriverManager.getConnection("jdbc:presto://39.104.112.125:9654/hive/default","hive",null);
        Statement stmt = connection.createStatement();
        SQLWarning warnings = stmt.getWarnings();
        ResultSet rs = stmt.executeQuery("select * from demo001 limit 20");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

        rs.close();
        connection.close();*/
        String[] strings = new String[3];
        com.facebook.presto.cli.Presto.main(strings);
    }
}

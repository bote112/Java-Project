import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/racing",
                    "root",
                    "andreibogos123"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("drop table car");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("driverID"));
                System.out.println(resultSet.getString("carID"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("age"));
                System.out.println(resultSet.getString("country"));
                System.out.println("________________________");

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}

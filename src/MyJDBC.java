import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/schema",
                    "root",
                    "andreibogos123"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CAR");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("carID"));
                System.out.println(resultSet.getString("model"));
                System.out.println(resultSet.getString("color"));
                System.out.println(resultSet.getString("maxSpeed"));
                System.out.println(resultSet.getString("power"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}

package sta.test;

import java.sql.*;
import java.util.Random;

public class IgniteMain {

    public static void main(String[] args) throws SQLException {
        // Open the JDBC connection via DriverManager.

        try(Connection conn = DriverManager.getConnection("jdbc:ignite:thin://localhost")) {
            try(PreparedStatement preparedStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS test (" +
                    "id int," +
                    "name varchar," +
                    "value int," +
                    "PRIMARY KEY (id))")){
                preparedStatement.execute();
                System.out.println("- Table created");
            }


            try(PreparedStatement insert = conn.prepareStatement("INSERT INTO test(id, name, value) VALUES (?,?,?)")){
                Random random = new Random();
                for (int i = 0; i < 1000; i++) {
                    insert.setInt(1, i);
                    insert.setString(2, "value" + i);
                    insert.setInt(3, random.nextInt());
                    insert.executeUpdate();
                }
                System.out.println("- Records inserted");
            }

            try(PreparedStatement count = conn.prepareStatement("SELECT COUNT(*) FROM test")){
                try(ResultSet resultSet = count.executeQuery()){
                    if(resultSet.next()){
                        long aLong = resultSet.getLong(1);
                        System.out.println("- Counted records: " + aLong);
                    }
                }
            }

            try(PreparedStatement delete = conn.prepareStatement("DELETE FROM test;")){
                delete.executeUpdate();
                System.out.println("- Table cleared");
            }

            try(PreparedStatement delete = conn.prepareStatement("DROP TABLE test;")){
                delete.executeUpdate();
                System.out.println("- Table dropped");
            }



        }
    }
}

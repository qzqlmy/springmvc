package until;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class DB {
    private static final String URL="jdbc:mysql:///test?user=root&password=123456";
    public static Connection getCononction() {
        try {
            new Driver();
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;


    }
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection) {
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }

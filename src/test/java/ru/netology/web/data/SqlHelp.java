package ru.netology.web.data;

import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelp {
    private static final QueryRunner runner = new QueryRunner();

    private SqlHelp(){}

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

}

package ru.netology.web.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlHelp {
    private static final QueryRunner runner = new QueryRunner();
    private static final String wrongAmountSQL = "SELECT count(*) FROM payment_entity WHERE AMOUNT <> 45000";
    private static final String ordersSQL = "SELECT count(*) FROM order_entity";

    private SqlHelp(){}

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    // данный метод в рамках задачи не используется,
    // но написан для будущих проектов
    public static void getWholeTable(String tableName) throws SQLException {
        String codeSQL = "SELECT * FROM " + tableName;
        var runner = new QueryRunner();
        try (
            var conn = getConn();
        )
        {
            List<Object[]> res = runner.query(conn, codeSQL, new ArrayListHandler());
            for (Object[] objects : res) {
                System.out.println(Arrays.toString(objects));
            }
        }
    }

    public static void checkOrdersTableFilling() throws SQLException {
        var runner = new QueryRunner();
        try (
                var conn = getConn();
        )
        {
            var res = runner.query(conn, ordersSQL, new ScalarHandler<>());
            Assertions.assertTrue(Integer.parseInt(res.toString()) > 0);
        }
    }
    public static void checkPaymentsAmount() throws SQLException {
        var runner = new QueryRunner();
        try (
                var conn = getConn();
        )
        {
            var res = runner.query(conn, wrongAmountSQL, new ScalarHandler<>());
            Assertions.assertEquals(0, Integer.parseInt(res.toString()));
        }
    }
}

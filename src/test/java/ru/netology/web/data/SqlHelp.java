package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelp {
    private static final QueryRunner runner = new QueryRunner();

    private SqlHelp(){}

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

//    @SneakyThrows
//    public static DataGenerator.VerificationCode getVerificationCode() {
//        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
//        var conn = getConn();
//        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
//        return new DataGenerator.VerificationCode(code);
//    }

//    @SneakyThrows
//    public static void clearDB() {
//        var conn = getConn();
//        runner.execute(conn, "DELETE FROM auth_codes");
//        runner.execute(conn, "DELETE FROM card_transactions");
//        runner.execute(conn, "DELETE FROM cards");
//        runner.execute(conn, "DELETE FROM users");
//    }

}

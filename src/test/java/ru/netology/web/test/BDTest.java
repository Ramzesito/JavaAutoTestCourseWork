package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.SqlHelp;

import java.sql.SQLException;

public class BDTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("2.1 BD Test: check orders table filling")
    public void ordersTableShouldBeFilled() throws SQLException {
        SqlHelp.checkOrdersTableFilling();
    }
    @Test
    @DisplayName("2.2 BD Test: check payments amount")
    public void paymentsAmountShouldBeCorrect() throws SQLException {
        SqlHelp.checkPaymentsAmount();
    }
}

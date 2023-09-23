package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.data.SqlHelp;
import ru.netology.web.page.OrderPage;

import java.sql.SQLException;
import java.util.Date;

import static com.codeborne.selenide.Selenide.open;

public class BDTest {
    OrderPage order;
    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setup() {
        order = open("http://localhost:8080", OrderPage.class);
    }

    @Test
    @DisplayName("2.1 BD Test: check orders table filling")
    public void ordersTableShouldBeFilled() throws SQLException {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.findBankSuccessMessage("Операция одобрена Банком.");

        SqlHelp.checkOrdersTableFilling();
    }
    @Test
    @DisplayName("2.2 BD Test: check payments amount")
    public void paymentsAmountShouldBeCorrect() throws SQLException {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.findBankSuccessMessage("Операция одобрена Банком.");

        SqlHelp.checkPaymentsAmount();
    }
}

package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.OrderPage;

import java.util.Date;
import static com.codeborne.selenide.Selenide.*;

public class TourOrderTest {

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
    @DisplayName("1.1a.Positive test for APPROOVED card")
    public void positiveTestForApproovedCard() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.checkMonthValidationMessage("Неверный формат", false);
        order.checkYearValidationMessage("Неверный формат", false);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", false);
        order.checkCvcValidationMessage("Неверный формат", false);
        order.findBankSuccessMessage("Операция одобрена Банком.");
    }

    @Test
    @DisplayName("1.1b.Negative test for DECLINED card")
    public void negativeTestForDeclinedCard() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getDeclinedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.checkMonthValidationMessage("Неверный формат", false);
        order.checkYearValidationMessage("Неверный формат", false);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", false);
        order.checkCvcValidationMessage("Неверный формат", false);
        order.findBankErrorMessage("Ошибка! Банк отказал в проведении операции.");
    }
    @Test
    @DisplayName("1.1c.Negative test for not registered card")
    public void negativeTestForNotRegisteredCard() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "8723 0945 4589 1290",
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.checkMonthValidationMessage("Неверный формат", false);
        order.checkYearValidationMessage("Неверный формат", false);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", false);
        order.checkCvcValidationMessage("Неверный формат", false);
        order.findBankErrorMessage("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    @DisplayName("1.2a.Negative test for CARD NUMBER (empty)")
    public void negativeTestForCardNumber_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "",
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.2b.Negative test for CARD NUMBER (not fully complete)")
    public void negativeTestForCardNumber_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "1111 2222 3333",
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.2c.Negative test for CARD NUMBER (over complete)")
    public void negativeTestForCardNumber_OverComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum() + "5",
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        Assertions.assertEquals(DataGenerator.getApproovedCardNum(), order.getCardNumber());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.findBankSuccessMessage("Операция одобрена Банком.");
    }
    @Test
    @DisplayName("1.2d.Negative test for CARD NUMBER (not digits)")
    public void negativeTestForCardNumber_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "qw_``,.neg_test!",
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        Assertions.assertEquals("", order.getCardNumber());
        order.checkCardNumberValidationMessage("Неверный формат", true);
    }

    @Test
    @DisplayName("1.3a.Negative test for MONTH (empty)")
    public void negativeTestForMonth_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                "",
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.3b.Negative test for MONTH (not fully complete)")
    public void negativeTestForMonth_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                "1",
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.3c.Negative test for MONTH (over limit)")
    public void negativeTestForCardNumber_OverLimit() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                "13",
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверно указан срок действия карты", true);
    }
    @Test
    @DisplayName("1.3d.Negative test for MONTH (not digits)")
    public void negativeTestForMonth_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                "q!",
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        Assertions.assertEquals("", order.getMonth());
        order.checkMonthValidationMessage("Неверный формат", true);
    }

    @Test
    @DisplayName("1.4a.Negative test for YEAR (empty)")
    public void negativeTestForYear_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                "",
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkYearValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.4b.Negative test for YEAR (not fully complete)")
    public void negativeTestForYear_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                "1",
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkYearValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.4c.Negative test for YEAR (over limit)")
    public void negativeTestForYear_OverLimit() {
        Date testDate = DataGenerator.generateInvalidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkYearValidationMessage("Неверно указан срок действия карты", true);
    }
    @Test
    @DisplayName("1.4d.Negative test for YEAR (not digits)")
    public void negativeTestForYear_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                "q!",
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        Assertions.assertEquals("", order.getYear());
        order.checkYearValidationMessage("Неверный формат", true);
    }

    @Test
    @DisplayName("1.5a.Negative test for OWNER (empty)")
    public void negativeTestForOwner_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                "",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", true);
    }
    @Test
    @DisplayName("1.5b.Negative test for OWNER (not letters)")
    public void negativeTestForOwner_NotLetters() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                "123 _-=",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.5c.Negative test for OWNER (over length)")
    public void negativeTestForOwner_OverLength() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateLongOwner(),
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Слишком длинное имя", true);
    }
    @Test
    @DisplayName("1.5d.Negative test for OWNER (if cyrillic)")
    public void negativeTestForOwner_IfCyrillic() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                "Старик Хоттабыч",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Имя должно быть на латинице", true);
    }

    @Test
    @DisplayName("1.6a.Negative test for CVC (empty)")
    public void negativeTestForCvc_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                "");
        order.checkCvcValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.6b.Negative test for CVC (not fully complete)")
    public void negativeTestForCvc_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                "12");
        order.checkCvcValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.6c.Negative test for CVC (over limit)")
    public void negativeTestForCvc_OverLimit() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                "1234");
        Assertions.assertEquals("123", order.getCvc());
        order.checkCvcValidationMessage("Неверный формат", false);
        order.findBankSuccessMessage("Операция одобрена Банком.");
    }
    @Test
    @DisplayName("1.6d.Negative test for CVC (not digits)")
    public void negativeTestForCvc_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                "q%=");
        Assertions.assertEquals("", order.getCvc());
        order.checkCvcValidationMessage("Неверный формат", true);
    }

    @Test
    @DisplayName("1.7.Negative test for all empty fields")
    public void negativeTestForAllEmptyFields() {
        order.makeOrder(
                "",
                "",
                "",
                "",
                "");
        order.checkCardNumberValidationMessage("Неверный формат", true);
        order.checkMonthValidationMessage("Неверный формат", true);
        order.checkYearValidationMessage("Неверный формат", true);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", true);
        order.checkCvcValidationMessage("Неверный формат", true);
    }

    @Test
    @DisplayName("1.8.Negative test for validate messages update")
    public void negativeTestForValidateMessagesUpdate() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "",
                "",
                "",
                "",
                "");
        order.makeOrder(
                DataGenerator.getApproovedCardNum(),
                DataGenerator.getDateFormatted(testDate, "MM"),
                DataGenerator.getDateFormatted(testDate, "yy"),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.checkMonthValidationMessage("Неверный формат", false);
        order.checkYearValidationMessage("Неверный формат", false);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", false);
        order.checkCvcValidationMessage("Неверный формат", false);
    }
}

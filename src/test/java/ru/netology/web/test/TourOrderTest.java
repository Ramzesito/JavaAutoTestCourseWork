package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.OrderPage;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.codeborne.selenide.Selenide.*;

public class TourOrderTest {

    OrderPage order;
    String approovedCardNum = "1111 2222 3333 4444";
    String declinedCardNum = "5555 6666 7777 8888";
    SimpleDateFormat yearFormatter = new SimpleDateFormat("yy");
    SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");

    @BeforeEach
    public void setup() {
        order = open("http://localhost:8080", OrderPage.class);
    }

    @Test
    @DisplayName("1.1a.Positive test for APPROOVED card")
    public void positiveTestForApproovedCard() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
    @DisplayName("1.1b.Positive test for DECLINED card")
    public void positiveTestForDeclinedCard() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                declinedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.2c.Negative test for CARD NUMBER (over complete)")
    public void negativeTestForCardNumber_OverComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum + "5",
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        Assertions.assertEquals(approovedCardNum, order.getCardNumber());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.findBankSuccessMessage("Операция одобрена Банком.");
    }
    @Test
    @DisplayName("1.2d.Negative test for CARD NUMBER (not digits)")
    public void negativeTestForCardNumber_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                "qw_``,.neg_test!",
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
                approovedCardNum,
                "",
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.3b.Negative test for MONTH (not fully complete)")
    public void negativeTestForMonth_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                "1",
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.3c.Negative test for MONTH (over limit)")
    public void negativeTestForCardNumber_OverLimit() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                "13",
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkMonthValidationMessage("Неверно указан срок действия карты", true);
    }
    @Test
    @DisplayName("1.3d.Negative test for MONTH (not digits)")
    public void negativeTestForMonth_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                "q!",
                yearFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkYearValidationMessage("Неверно указан срок действия карты", true);
    }
    @Test
    @DisplayName("1.4d.Negative test for YEAR (not digits)")
    public void negativeTestForYear_NotDigits() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                "",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", true);
    }
    @Test
    @DisplayName("1.5b.Negative test for OWNER (not letters)")
    public void negativeTestForOwner_NotLetters() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                "123 _-=",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.5c.Negative test for OWNER (over length)")
    public void negativeTestForOwner_OverLength() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateLongOwner(),
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Слишком длинное имя", true);
    }
    @Test
    @DisplayName("1.5d.Negative test for OWNER (if cyrillic)")
    public void negativeTestForOwner_IfCyrillic() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                "Старик Хоттабыч",
                DataGenerator.generateRandomCVC());
        order.checkOwnerValidationMessage("Имя должно быть на латинице", true);
    }

    @Test
    @DisplayName("1.6a.Negative test for CVC (empty)")
    public void negativeTestForCvc_Empty() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                "");
        order.checkCvcValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.6b.Negative test for CVC (not fully complete)")
    public void negativeTestForCvc_NotFullyComplete() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                "12");
        order.checkCvcValidationMessage("Неверный формат", true);
    }
    @Test
    @DisplayName("1.6c.Negative test for CVC (over limit)")
    public void negativeTestForCvc_OverLimit() {
        Date testDate = DataGenerator.generateValidFutureDate();
        order.makeOrder(
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
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
                approovedCardNum,
                monthFormatter.format(testDate),
                yearFormatter.format(testDate),
                DataGenerator.generateRandomOwner(),
                DataGenerator.generateRandomCVC());
        order.checkCardNumberValidationMessage("Неверный формат", false);
        order.checkMonthValidationMessage("Неверный формат", false);
        order.checkYearValidationMessage("Неверный формат", false);
        order.checkOwnerValidationMessage("Поле обязательно для заполнения", false);
        order.checkCvcValidationMessage("Неверный формат", false);
    }
}

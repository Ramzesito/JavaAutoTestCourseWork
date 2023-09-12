package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private ElementsCollection captions = $$("span.input__top");
    //private ElementsCollection inputFields = $$("span.input__box > input");
    private SelenideElement cardNumberField = captions.find(Condition.text("Номер карты")).parent().find("input.input__control");
    private SelenideElement cardMonthField = captions.find(Condition.text("Месяц")).parent().find("input.input__control");
    private SelenideElement cardYearField = captions.find(Condition.text("Год")).parent().find("input.input__control");
    private SelenideElement cardOwnerField = captions.find(Condition.text("Владелец")).parent().find("input.input__control");
    private SelenideElement cardCvcField = captions.find(Condition.text("CVC/CVV")).parent().find("input.input__control");
    private SelenideElement contButton = $(byText("Продолжить"));
    private ElementsCollection messages = $$("div.notification__content");
    private SelenideElement successMessage = messages.get(0);
    private SelenideElement errorMessage = messages.get(1);
    //private ElementsCollection validateMessages = $$("span.input__top");
    private SelenideElement cardNumberValidationMessage = captions.find(Condition.text("Номер карты")).parent().find("span.input__sub");
    private SelenideElement monthValidationMessage = captions.find(Condition.text("Месяц")).parent().find("span.input__sub");
    private SelenideElement yearValidationMessage = captions.find(Condition.text("Год")).parent().find("span.input__sub");
    private SelenideElement ownerValidationMessage = captions.find(Condition.text("Владелец")).parent().find("span.input__sub");
    private SelenideElement cvcValidationMessage = captions.find(Condition.text("CVC/CVV")).parent().find("span.input__sub");

    public void makeOrder(String cardNumber, String cardMonth, String cardYear, String cardOwner, String cardCvc) {
        buyButton.click();
        cardNumberField.setValue(cardNumber);
        cardMonthField.setValue(cardMonth);
        cardYearField.setValue(cardYear);
        cardOwnerField.setValue(cardOwner);
        cardCvcField.setValue(cardCvc);
        contButton.click();

        //SelenideElement par = cardNumberField.parent().parent();
        //System.out.println(cardCvc);


        //if (numberNotify.exists()) {System.out.println(numberNotify.text());}
        //System.out.println(captions.findBy(Condition.text("Номер карты")).attr("class"));
        //SelenideElement sss = captions.findBy(Condition.text("Номер карты"));
        //SelenideElement sss = captions.get(0);
        //sss.setValue("0000");
        //System.out.println(par.find("input.input__control").attr("placeholder"));
        //parent.shouldNotBe(visible);
        //SelenideElement err = par.find("span.input__sub");
        //err.shouldBe(visible);

    }

    public void findBankErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
    public void findBankSuccessMessage(String expectedText) {
        successMessage.shouldHave(Condition.text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void checkCardNumberValidationMessage(String expectedText, boolean visibility) {
        if (visibility) {
            cardNumberValidationMessage.shouldHave(Condition.text(expectedText)).shouldBe(visible);
        }
        else {
            cardNumberValidationMessage.shouldNotBe(visible);
        }
    }
    public void checkMonthValidationMessage(String expectedText, boolean visibility) {
        if (visibility) {
            monthValidationMessage.shouldHave(Condition.text(expectedText)).shouldBe(visible);
        }
        else {
            monthValidationMessage.shouldNotBe(visible);
        }
    }
    public void checkYearValidationMessage(String expectedText, boolean visibility) {
        if (visibility) {
            yearValidationMessage.shouldHave(Condition.text(expectedText)).shouldBe(visible);
        }
        else {
            yearValidationMessage.shouldNotBe(visible);
        }
    }
    public void checkOwnerValidationMessage(String expectedText, boolean visibility) {
        if (visibility) {
            ownerValidationMessage.shouldHave(Condition.text(expectedText)).shouldBe(visible);
        }
        else {
            ownerValidationMessage.shouldNotBe(visible);
        }
    }
    public void checkCvcValidationMessage(String expectedText, boolean visibility) {
        if (visibility) {
            cvcValidationMessage.shouldHave(Condition.text(expectedText)).shouldBe(visible);
        }
        else {
            cvcValidationMessage.shouldNotBe(visible);
        }
    }

    public String getCardNumber() {
        return cardNumberField.getValue();
    }
    public String getMonth() {
        return cardMonthField.getValue();
    }
    public String getYear() {
        return cardYearField.getValue();
    }
    public String getCvc() {
        return cardCvcField.getValue();
    }
}

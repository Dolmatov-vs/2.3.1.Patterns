package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.chord;
import static ru.netology.data.UserData.getUserInfo;


public class CardOrderTest {

    String selectAll = chord(Keys.CONTROL, "a");
    Keys del = Keys.DELETE;

    SelenideElement cityField = $("[data-test-id=city] input");
    SelenideElement dateField = $("[data-test-id=date] input");
    SelenideElement nameField = $("[data-test-id=name] input");
    SelenideElement phoneField = $("[data-test-id=phone] input");
    SelenideElement checkbox = $("[data-test-id=agreement]");
    SelenideElement BookButton = $$(".form button").find(text("Запланировать"));

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulMeetingReservationTest() {

        UserData.UserInfo user = getUserInfo();
        System.out.println(user.getCity());
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(String.valueOf(user.getDate()));
        nameField.setValue(user.getFullName());
        phoneField.setValue(user.getTel());
        checkbox.click();
        BookButton.click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на"));
        $("[data-test-id=success-notification]").shouldHave(text(String.valueOf(user.getDate())));
    }

//    @Test
//    void shouldCityFieldIsRequired() {
//        dateField.sendKeys(selectAll, del);
//        dateField.setValue(CurrentDatePlusDays());
//        nameField.setValue("Иванов Иван");
//        phoneField.setValue("+79281234567");
//        checkbox.click();
//        BookButton.click();
//        $(".input_invalid[data-test-id=city]").shouldHave(text("Поле обязательно для заполнения"));
//        $(".input_invalid[data-test-id=city]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
//        $(withText("Успешно!")).waitUntil(hidden, 15000);
//    }
//
//    @Test
//    void shouldDateFieldIsRequired(){
//        cityField.setValue("Санкт-Петербург");
//        dateField.sendKeys(selectAll, del);
//        dateField.setValue("Иванов Иван");
//        phoneField.setValue("+79281234567");
//        checkbox.click();
//        BookButton.click();
//        $(".calendar-input__custom-control").shouldHave(text("Неверно введена дата"));
//        $(".calendar-input__custom-control").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
//        $(withText("Успешно!")).waitUntil(hidden, 15000);
//    }
//
//    @Test
//    void shouldNameFieldIsRequired(){
//        cityField.setValue("Санкт-Петербург");
//        dateField.sendKeys(selectAll, del);
//        dateField.setValue(CurrentDatePlusDays());
//        phoneField.setValue("+79281234567");
//        checkbox.click();
//        BookButton.click();
//        $("[data-test-id=name]").shouldHave(text("Поле обязательно для заполнения"));
//        $("[data-test-id=name]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
//        $(withText("Успешно!")).waitUntil(hidden, 15000);
//    }
//
//    @Test
//    void shouldTelFieldIsRequired(){
//        cityField.setValue("Санкт-Петербург");
//        dateField.sendKeys(selectAll, del);
//        dateField.setValue(CurrentDatePlusDays());
//        nameField.setValue("Иванов Иван");
//        checkbox.click();
//        BookButton.click();
//        $("[data-test-id=phone]").shouldHave(text("Поле обязательно для заполнения"));
//        $("[data-test-id=phone]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
//        $(withText("Успешно!")).waitUntil(hidden, 15000);
//    }
//
//    @Test
//    void shouldCheckboxFieldIsRequired(){
//        cityField.setValue("Санкт-Петербург");
//        dateField.sendKeys(selectAll, del);
//        dateField.setValue(CurrentDatePlusDays());
//        nameField.setValue("Иванов Иван");
//        phoneField.setValue("+79281234567");
//        BookButton.click();
//        checkbox.shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
//        $(withText("Успешно!")).waitUntil(hidden, 15000);
//    }
}

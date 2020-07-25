package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import org.openqa.selenium.Keys;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.chord;
import static ru.netology.data.UserData.getUserInfo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardOrderTest {

    String selectAll = chord(Keys.CONTROL, "a");
    Keys del = Keys.DELETE;

    SelenideElement cityField = $("[data-test-id=city] input");
    SelenideElement dateField = $("[data-test-id=date] input");
    SelenideElement nameField = $("[data-test-id=name] input");
    SelenideElement phoneField = $("[data-test-id=phone] input");
    SelenideElement checkbox = $("[data-test-id=agreement]");
    SelenideElement BookButton = $$(".form button").find(text("Запланировать"));
    SelenideElement rescheduling = $("[data-test-id='replan-notification']");
    Faker faker = new Faker();
    int randomUser = 25;
    int randomUserMin = 1;
    int randomUserMax = 1000;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    @Order(1)
    void shouldSuccessfulMeetingReservationTest() {
        UserData.UserInfo user = getUserInfo(randomUser);
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        nameField.setValue(user.getFullName());
        phoneField.setValue(user.getPhone());
        checkbox.click();
        BookButton.click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на"));
        $("[data-test-id=success-notification]").shouldHave(text(user.getDate()));
    }

    @Test
    @Order(2)
    void shouldMeetingRescheduledSuccessfully() {
        UserData.UserInfo user = getUserInfo(randomUser);
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        nameField.setValue(user.getFullName());
        phoneField.setValue(user.getPhone());
        checkbox.click();
        BookButton.click();
        rescheduling.waitUntil(visible, 15000);
        rescheduling.$("button").click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на"));
        $("[data-test-id=success-notification]").shouldHave(text(user.getDate()));
    }

    @Test
    void shouldCityFieldIsRequired() {
        UserData.UserInfo user = getUserInfo(faker.random().nextInt(randomUser,1000));
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        nameField.setValue(user.getFullName());
        phoneField.setValue(user.getPhone());
        checkbox.click();
        BookButton.click();
        $(".input_invalid[data-test-id=city]").shouldHave(text("Поле обязательно для заполнения"));
        $(".input_invalid[data-test-id=city]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $(withText("Успешно!")).waitUntil(hidden, 15000);
    }

    @Test
    void shouldDateFieldIsRequired(){
        UserData.UserInfo user = getUserInfo(faker.random().nextInt(randomUserMin,randomUserMax));
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getFullName());
        phoneField.setValue(user.getPhone());
        checkbox.click();
        BookButton.click();
        $(".calendar-input__custom-control").shouldHave(text("Неверно введена дата"));
        $(".calendar-input__custom-control").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $(withText("Успешно!")).waitUntil(hidden, 15000);
    }

    @Test
    void shouldNameFieldIsRequired(){
        UserData.UserInfo user = getUserInfo(faker.random().nextInt(randomUserMin,randomUserMax));
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        phoneField.setValue(user.getPhone());
        checkbox.click();
        BookButton.click();
        $("[data-test-id=name]").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=name]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $(withText("Успешно!")).waitUntil(hidden, 15000);
    }

    @Test
    void shouldTelFieldIsRequired(){
        UserData.UserInfo user = getUserInfo(faker.random().nextInt(randomUserMin,randomUserMax));
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        nameField.setValue(user.getFullName());
        checkbox.click();
        BookButton.click();
        $("[data-test-id=phone]").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=phone]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $(withText("Успешно!")).waitUntil(hidden, 15000);
    }

    @Test
    void shouldCheckboxFieldIsRequired(){
        UserData.UserInfo user = getUserInfo(faker.random().nextInt(randomUserMin,randomUserMax));
        cityField.setValue(user.getCity());
        dateField.sendKeys(selectAll, del);
        dateField.setValue(user.getDate());
        nameField.setValue(user.getFullName());
        phoneField.setValue(user.getPhone());
        BookButton.click();
        checkbox.shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
        $(withText("Успешно!")).waitUntil(hidden, 15000);
    }
}

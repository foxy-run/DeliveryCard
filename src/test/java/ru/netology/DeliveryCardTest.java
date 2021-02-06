package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    @Test

    public void shouldSubmitSuccessRequest() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[class=notification__content]").waitUntil(visible, 15000)
                .shouldHave(exactText("Встреча успешно забронирована на " + inputDate));
    }

    @Test

    public void shouldSubmitEmptyCity() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactText("Поле обязательно для заполнения"));
    }

    @Test

    public void shouldSubmitWrongCity() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Пойма");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactText("Доставка в выбранный город недоступна"));
    }

    @Test

    public void shouldSubmitEmptyDate() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactText("Неверно введена дата"));
    }

    @Test

    public void shouldSubmitEmptyName() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave
                (exactText("Поле обязательно для заполнения"));
    }

    @Test

    public void shouldSubmitEmptyPhone() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldHave
                (exactText("Поле обязательно для заполнения"));
    }

    @Test

    public void shouldSubmitWrongPhone() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("9996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldHave
                (exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test

    public void shouldSubmitAgreement() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Санкт-Петербург");
        String inputDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Иванов Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79996666666");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement] .checkbox__text").shouldHave
                (exactTextCaseSensitive("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}

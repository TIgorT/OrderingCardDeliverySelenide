import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDeliveryTest {
    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    // Заполнение всех полей данной формы валидными значениями
    public void orderCardDeliveryTestFirst() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Краснодар");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(5000));
    }

    @Test
    // Заполнение поля "Город" невалидным значением
    public void orderCardDeliveryTestSecond() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ессентуки");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='city'].input .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"), Duration.ofSeconds(5000));
    }

    @Test
    // Пустое поле "Город"
    public void orderCardDeliveryTestThird() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='city'].input .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(5000));
    }

    @Test
    // Заполнение поля "Дата встречи" невалидным значением
    public void orderCardDeliveryTestFourth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(31, "dd.MM"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='date'] .input .input__sub").shouldHave(exactText("Неверно введена дата"), Duration.ofSeconds(5000));
    }

    @Test
    // Пустое поле "Дата встречи"
    public void orderCardDeliveryTestFifth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue("");
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='date'] .input .input__sub").shouldHave(exactText("Неверно введена дата"), Duration.ofSeconds(5000));
    }

    @Test
    // Заполнение поля "Фамилия и имя" невалидным значением
    public void orderCardDeliveryTestSixth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Veliyev Maxim");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='name'].input .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."), Duration.ofSeconds(5000));
    }

    @Test
    // Пустое поле "Фамилия и имя"
    public void orderCardDeliveryTestSeventh() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='name'].input .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(5000));
    }

    @Test
    // Заполнение поля "Мобильный телефон" невалидным значением
    public void orderCardDeliveryTestEighth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("7+9756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='phone'].input .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(5000));
    }

    @Test
    // Пустое поле "Мобильный телефон"
    public void orderCardDeliveryTestNinth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $("[data-test-id='phone'].input .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(5000));
    }

    @Test
    // Заполнение формы валидными значениями без использования чекбокса
    public void orderCardDeliveryTestTenth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue(generateDate(4, "dd.MM.YYYY"));
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[type=button] .button__content").click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"), Duration.ofSeconds(5000));
    }

    @Test
    // Пустые поля в форме "Карта с доставкой"
    public void orderCardDeliveryTestEleventh() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id ='date'] input").setValue("");
        $("[data-test-id='name'] input ").setValue("");
        $("[data-test-id='phone'] input ").setValue("");
        $("[type=button] .button__content").click();
        $("[data-test-id='city'].input .input__sub").shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(5000));
    }


    @Test
    // Задача №2: взаимодействие с комплексными элементами (необязательная)
    public void orderCardDeliveryTestTwelfth() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Кр");
        $(byText("Краснодар")).click();
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[type=button] .icon-button__content").click();
        $$("[data-day]").get(7).click();
        $("[data-test-id='name'] input ").setValue("Велев Максим");
        $("[data-test-id='phone'] input ").setValue("+79756249171");
        $("[data-test-id='agreement']").click();
        $("[type=button] .button__content").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15000));
    }
}

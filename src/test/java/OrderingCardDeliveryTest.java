import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyPathTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldShowMessageAfterSendRequest() {
        driver.get("http://localhost:9999");

        driver.findElement(By.className("input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white")).sendKeys("Иван Иванов");
        driver.findElement(By.className("input input_type_tel input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white")).sendKeys("+79991234567");
        driver.findElement(By.className("checkbox__control")).click();
        driver.findElement(By.className("button button_view_extra button_size_m button_hovered button_theme_alfa-on-white")).click();
        String text = driver.findElement(By.className("paragraph paragraph_theme_alfa-on-white")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }
}

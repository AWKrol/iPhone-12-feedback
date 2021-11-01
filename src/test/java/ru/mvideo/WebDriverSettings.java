package ru.mvideo;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver"); // путь до chromedriver
        driver = new ChromeDriver(); // создаем экземпляр WebDriver
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // неявное ожидание появления элемента
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // неявное ожидагие загрузки страницы
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS); // неявное ожидание отработки скриптов
        //driver.manage().window().maximize();
    }

    @After
    public void close() {
        driver.quit(); // закрываем браузер
    }
}

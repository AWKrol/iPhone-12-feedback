package ru.mvideo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IPhoneTest {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver"); // путь до chromedriver
        driver = new ChromeDriver(); // создаем экземпляр WebDriver
    }

    @Test
    public void iPhoneTest() {
        String title = "М.Видео - интернет-магазин цифровой и бытовой техники и электроники," +
                " низкие цены, большой каталог, отзывы."; // title сайта mvideo
        String search = "iPhone 12 64GB"; // текст поиска товара

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // неявное ожидание появления элемента
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // неявное ожидагие загрузки страницы
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS); // неявное ожидание отработки скриптов

        driver.get("https://www.mvideo.ru/"); // открываем сайт
        Assert.assertEquals(title, driver.getTitle()); // проверяем, что мы находимся на сайте mvideo
        driver.findElement(By.id("1")) // вводим в стоку поиска товара наш запрос
                .sendKeys(search);
        WebElement element = driver.findElement(By.id("1")); // создаем element для перемещения по узлам документа
        WebElement elementParrent = element.findElement(By.xpath("..")); // переход на родительский узел
        elementParrent.findElement(By.xpath(".//div/div")).click(); // кликаем на кнопку поиск
        driver.findElement(By.cssSelector(
                "[href=\"https://www.mvideo.ru/products/smartfon-apple-iphone-12-" +
                        "64gb-productred-mgj73ru-a-30052887\"]")).click(); // выбираем iPhone
        driver.findElement(By.cssSelector(
                "[class=\"rating-reviews ng-star-inserted\"]")).click(); // кликаем на поле "отзывы"
        String feedback = driver.findElement(By.cssSelector(            // сохраняем в переменой feedback
                "[class=\"content with-overflow\"]")).getText(); // первый отзыв на товар

        try{
            String filePath = "C:\\Users\\1\\Desktop\\feedback iPhone 12.txt"; // переменная пути к файлу
            BufferedWriter bufferedWriter = new BufferedWriter(  // запись отзыва в файл с кодировкой Windows-1251
                    new OutputStreamWriter(new FileOutputStream(filePath), "Windows-1251"));
            bufferedWriter.write(feedback);
            bufferedWriter.close();
        }catch (IOException e) {
            System.out.println(e);
        }
    }
    @After
    public void close() {
        driver.quit(); // закрываем браузер
    }
}

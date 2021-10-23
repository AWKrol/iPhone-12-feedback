package ru.mvideo.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HometPage {

    private WebDriver driver;

    String title = "М.Видео - интернет-магазин цифровой и бытовой техники и электроники," +
            " низкие цены, большой каталог, отзывы."; // title сайта mvideo

    @FindBy(id = "1")
    private WebElement stringSearch;

    public HometPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.mvideo.ru/"); // открываем сайт mvideo.ru
    }

    public void stringSearch(String search) {
        stringSearch.sendKeys(search); // вводим в стоку поиска товара наш запрос
    }

    public void buttonSearch() {
        WebElement element = driver.findElement(By.id("1")); // создаем element для перемещения по узлам документа
        WebElement elementParrent = element.findElement(By.xpath("..")); // переход на родительский узел
        elementParrent.findElement(By.xpath(".//div/div")).click(); // кликаем на кнопку поиск
    }
}

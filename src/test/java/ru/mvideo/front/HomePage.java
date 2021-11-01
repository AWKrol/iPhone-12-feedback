package ru.mvideo.front;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    private WebDriver driver;

    String title = "М.Видео - интернет-магазин цифровой и бытовой техники и электроники," +
            " низкие цены, большой каталог, отзывы."; // title сайта mvideo

    @FindBy(id = "1")
    private WebElement stringSearch;

    @FindBy(id = "1")
    private WebElement stringSearchGet;
    @FindBy(css = "[type=\"geo\"]")

    private WebElement locationButton;

    @FindBy(id = "8")
    private WebElement locationString;

    @FindBy(css = "[class=\"location-select__location ng-star-inserted\"]")
    private WebElement oneCity;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.mvideo.ru/"); // открываем сайт mvideo.ru
    }

    public void location() {
        locationButton.click();
    }

    public void stringSearch(String search) {
        stringSearch.sendKeys(search); // вводим в стоку поиска товара наш запрос
    }
    public String stringSearchGet() {
        return stringSearchGet.getText();
    }

    public void stringSearchClick() {
        stringSearch.click();
    }

    public void buttonSearch() {
        WebElement element = driver.findElement(By.id("1")); // создаем element для перемещения по узлам документа
        WebElement elementParrent = element.findElement(By.xpath("..")); // переход на родительский узел
        elementParrent.findElement(By.xpath(".//div/div")).click(); // кликаем на кнопку поиск
    }

    public void locationString(String city) {
        locationString.sendKeys(city);  // вводим в сторку определения города наш город
    }

    public void oneCity() {
        oneCity.click();  // кликаем на первый город из выпадающего списка
    }
    public String oneCityString() {
        return oneCity.getText();
    }
}

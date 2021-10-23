package ru.mvideo.front;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import ru.mvideo.WebDriverSettings;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class IPhoneTest extends WebDriverSettings {

    @Test
    public void iPhoneTest() {

        String search = "iPhone 12 64GB"; // текст поиска товара

        HometPage hometPage = PageFactory.initElements(driver, HometPage.class);

        hometPage.open(); // открываем сайт mvideo.ru

        Assert.assertEquals(hometPage.title, driver.getTitle()); // проверяем, что мы находимся на сайте mvideo

        hometPage.stringSearch(search); // вводим в стоку поиска товара наш запрос
        hometPage.buttonSearch(); // кликаем на кнопку поиск

        Iphone12Page iphone12Page = PageFactory.initElements(driver, Iphone12Page.class);
        iphone12Page.iPhone12Red64gb(); // выбираем iPhone 12 red 64gb
        iphone12Page.buttonFeedback();  // кликаем на поле "отзывы"
        String feedback = iphone12Page.oneFeedback(); // сохраняем в переменой feedback
                                                      // первый отзыв на товар
        iphone12Page.saveFeedback(feedback); // запись отзыва в файл с кодировкой Windows-1251

    }
}

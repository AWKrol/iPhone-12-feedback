package ru.mvideo.front;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import ru.mvideo.WebDriverSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class IPhoneTest extends WebDriverSettings {

    @Ignore
    @Test
    public void iPhoneTest() {

        String city = "Брянск";
        String search = "iPhone 12 64GB" + "\n"; // текст поиска товара

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.open(); // открываем сайт mvideo.ru

        Assert.assertEquals(homePage.title, driver.getTitle()); // проверяем, что мы находимся на сайте mvideo

        homePage.location(); // кликаем на кнопку местоположение
        homePage.locationString(city); // вводим название города в стороку запроса города

        String  s = "";

        while (s.equals("г Брянск") == false) {
            try {
                s = homePage.oneCityString();
                System.out.println(s);
            }catch(StaleElementReferenceException e){

            }catch(NoSuchElementException es){
            }
        }

        homePage.oneCity(); // кликаем на первый город из выпадающего списка

        int i = 0;
        while(i < 10)
        try {
            homePage.stringSearch(search); // вводим в стоку поиска товара наш запрос
            i = 10;
        }catch(StaleElementReferenceException e) {
            i++;
        };

        Iphone12Page iphone12Page = PageFactory.initElements(driver, Iphone12Page.class);
        iphone12Page.iPhone12Red64gb(); // выбираем iPhone 12 red 64gb
        iphone12Page.buttonFeedback();  // кликаем на поле "отзывы"
        String feedback = iphone12Page.oneFeedback(); // сохраняем в переменой feedback
        // первый отзыв на товар
        iphone12Page.saveFeedback(feedback); // запись отзыва в файл с кодировкой Windows-1251

    }

    @Ignore
    @Test
    public void iPhoneTestLocal() {
        String city = "Брянск";

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.open(); // открываем сайт mvideo.ru
        homePage.location(); // кликаем на кнопку местоположение
        homePage.locationString(city); // вводим название города в стороку запроса города

        String  s = "";

        while (s.equals("г Брянск") == false) {
            try {
                s = homePage.oneCityString();
                System.out.println(s);
            }catch(StaleElementReferenceException e){

            }catch(NoSuchElementException es){
            }
        }

        homePage.oneCity(); // кликаем на первый город из выпадающего списка
    }

    @Test
    public void iPhoneFeedbackList() {

        String city = "Брянск";
        String search = "iPhone 12 64GB"; // текст поиска товара

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.open(); // открываем сайт mvideo.ru

        Assert.assertEquals(homePage.title, driver.getTitle()); // проверяем, что мы находимся на сайте mvideo

        homePage.location(); // кликаем на кнопку местоположение
        homePage.locationString(city); // вводим название города в стороку запроса города

        String  s = "";

        while (s.equals("г Брянск") == false) {
            try {
                s = homePage.oneCityString();
                //System.out.println(s);
            }catch(StaleElementReferenceException e){

            }catch(NoSuchElementException es){
            }
        }

        homePage.oneCity(); // кликаем на первый город из выпадающего списка

        int i = 0;
        while(i < 10) {
            try {
                homePage.stringSearchClick();
                homePage.stringSearch(search); // вводим в стоку поиска товара наш запрос
                i = 10;
            } catch (StaleElementReferenceException e) {
                i++;
            };
        }

        homePage.buttonSearch();

        Iphone12Page iphone12Page = PageFactory.initElements(driver, Iphone12Page.class);
        iphone12Page.iPhone12Red64gb(); // выбираем iPhone 12 red 64gb

        try {
            iphone12Page.buttonFeedback();  // кликаем на поле "отзывы"
            iphone12Page.allFeedback();  // кликаем на поле "все отзывы"
            iphone12Page.lastFeedback(); // кликаем на поле "показать еще отзывы"

        }catch(InvalidSelectorException e){
            System.out.println(e);
        }




        List<WebElement> nameFeedback = iphone12Page.nameFeedbackList(); // список с элементами,
        for(WebElement el : nameFeedback) {                              // которые содержат имена людей,
            System.out.println(el.getText());                            // оставивших отзывы
        }
        System.out.println(nameFeedback.size());

        try {
            Class.forName("org.postgresql.Driver"); // загрузка драйвера для Postgresql в JVM
        }catch(ClassNotFoundException e) {          // выдает ошибку, если не загружена библиотека
            e.printStackTrace();                    // Postgresql в Maven
        };

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mvideo",
                    "postgres",
                    "1234");

            int id = 1;

            String deleteTableSQL = "DELETE FROM name_feedback";
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            for(WebElement el : nameFeedback) {                              // которые содержат имена людей,
                System.out.println(el.getText());                            // оставивших отзывы
                //String deleteTableSQL = "DELETE FROM name_feedback";
                String insertTableSQl = "INSERT INTO name_feedback(id, name) VALUES(" + Integer.toString(id) + "," +
                         "'" + el.getText() + "'" + ")";

                /*Statement statement = connection.createStatement();
                statement.executeUpdate(deleteTableSQL);*/
                statement.executeUpdate(insertTableSQl);
                id++;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(iphone12Page.quantityFeedback() , Integer.toString(nameFeedback.size()));

    }
}
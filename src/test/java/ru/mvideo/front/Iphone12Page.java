package ru.mvideo.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Iphone12Page {
    private WebDriver driver;

    public Iphone12Page(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "[href=\"https://www.mvideo.ru/products/smartfon-apple-iphone-12-mini-64gb-black-mgdx3ru-a-30052870\"]")
    private WebElement iPhone12Red;

    @FindBy(tagName = "mvideo-product-rating")
    private WebElement buttonFeedback;

    @FindBy(css = "[class=\"content with-overflow\"]")
    private WebElement oneFeedback;

    @FindBy(css = "[class=\"show-all mv-main-button--secondary mv-main-button--large mv-button mv-main-button\"]")
    private WebElement allFeedback;

    @FindBy(css = "[class=\"show-all mv-main-button--large" +
            " mv-main-button--secondary mv-button mv-main-button ng-star-inserted\"]")
    private WebElement lastFeedback;

    @FindBy(css = "[class=\"title__count\"]")
    private WebElement quantityFeedback;



    public void iPhone12Red64gb() {
        iPhone12Red.click(); // выбираем iPhone 12 red 64gb
    }

    public void buttonFeedback(){
        buttonFeedback.click(); // кликаем на поле "отзывы"
    }

    public String  oneFeedback(){      // сохраняем в переменой feedback
        return oneFeedback.getText();  // первый отзыв на товар
    }

    public  void saveFeedback(String feedback) {
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

    public List<WebElement> nameFeedbackList() {
        List<WebElement> list = new LinkedList<>();
        return  list =  driver.findElements(By.cssSelector("[class=\"head__name\"]"));
    }

    public void allFeedback() {
        allFeedback.click();  // кликаем на все отзывы
    }

    public void lastFeedback() {
        lastFeedback.click();
    }

    public String quantityFeedback() {
        return quantityFeedback.getText();
    }

}

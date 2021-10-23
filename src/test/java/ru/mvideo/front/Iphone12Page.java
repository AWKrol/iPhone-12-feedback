package ru.mvideo.front;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Iphone12Page {
    private WebDriver driver;

    public Iphone12Page(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "[href=\"https://www.mvideo.ru/products/smartfon-apple-iphone-12-64gb-productred-mgj73ru-a-30052887\"]")
    private WebElement iPhone12Red;

    @FindBy(css = "[class=\"rating-reviews ng-star-inserted\"]")
    private WebElement buttonFeedback;

    @FindBy(css = "[class=\"content with-overflow\"]")
    private WebElement oneFeedback;

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

}

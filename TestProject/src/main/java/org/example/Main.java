package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    @Test
    public void FirstTest() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\lloid\\Documents\\GitHub\\SoftwareTestingLabs\\TestProject\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.quit();
    }

}
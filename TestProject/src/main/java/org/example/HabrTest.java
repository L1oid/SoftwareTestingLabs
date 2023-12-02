package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class HabrTest {
    @Test
    public void FirstTest() {
        String chromeDriverPath = "C:\\Users\\lloid\\Documents\\GitHub\\SoftwareTestingLabs\\TestProject\\drivers\\chromedriver.exe";
        String chromeBinaryPath = "C:\\Users\\lloid\\Desktop\\chrome-win64\\chrome.exe";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(chromeBinaryPath); // Установка пути до браузера
        chromeOptions.addArguments("--start-maximized"); //Установка полноэкранного режима для корректного выполнения теста

        System.setProperty("webdriver.chrome.driver",chromeDriverPath); // Установка пути до драйвера
        WebDriver driver = new ChromeDriver(chromeOptions);// Установка заданных опций в WebDriver и его создание

        JavascriptExecutor js = ((JavascriptExecutor) driver); // Подключение JavascriptExecutor для выполнения скролла

        driver.get("https://habr.com/ru/all"); // Переход на сайт habr
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка перед кликом по "Лупе"
        driver.findElement(By.xpath("//*[@class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']")).click(); // Клик на кнопку "Лупа" для открытия поля поиска
        Assertions.assertEquals(driver.findElement(By.xpath("//input[@name='q']")), driver.switchTo().activeElement()); // Проверка на то, находится ли поле в фокусе страницы
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium WebDriver"); // Ввод в поле поиска
        driver.findElement(By.xpath("//*[@class='tm-svg-img tm-svg-icon']")).click(); // Клик на кнопку "Лупа" для поиска
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка перед поиском статьм
        driver.findElement(By.linkText("Что такое Selenium?")).click(); // Поиск статьи по названию
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка после перехода на страницу статьи
        Assertions.assertEquals("28 сен 2012 в 17:14", driver.findElement(By.xpath("//*[@title='2012-09-28, 17:14']")).getText()); // Сверка даты
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Выполнение скролла вниз страницы
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка после скролла
        driver.findElement(By.xpath("//a[@href='/ru/' and @class='footer-menu__item-link router-link-active']")).click(); // Клик по кнопке "Статьи"

        driver.quit();
    }
}
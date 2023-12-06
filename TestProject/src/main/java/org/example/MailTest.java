package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MailTest {
    @Test
    public void test() {
        String chromeDriverPath = "C:\\Users\\lloid\\Documents\\GitHub\\SoftwareTestingLabs\\TestProject\\drivers\\chromedriver.exe";
        String chromeBinaryPath = "C:\\Users\\lloid\\Desktop\\chrome-win64\\chrome.exe";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(chromeBinaryPath); // Установка пути до браузера
        chromeOptions.addArguments("--start-maximized"); //Установка полноэкранного режима для корректного выполнения теста

        System.setProperty("webdriver.chrome.driver",chromeDriverPath); // Установка пути до драйвера
        WebDriver driver = new ChromeDriver(chromeOptions);// Установка заданных опций в WebDriver и его создание

        driver.get("https://account.mail.ru/login"); // Переход на сайт входа в почту mail.ru
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка
        Assertions.assertEquals(driver.findElement(By.xpath("//input[@name='username']")), driver.switchTo().activeElement()); // Проверка на то, находится ли поле в фокусе страницы
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("testich201"); // Ввод в поле "Имя аккаунта"
        driver.findElement(By.xpath("//*[@class='inner-0-2-89 innerTextWrapper-0-2-90']")).click(); // Клик на кнопку "Ввести пароль"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("toptester123"); // Ввод в поле "Пароль"
        driver.findElement(By.xpath("//*[@class='inner-0-2-89 innerTextWrapper-0-2-90']")).click(); // Клик на кнопку "Войти"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка
        driver.findElement(By.xpath("//*[@class='ph-project ph-project__account svelte-1osmzf1']")).click(); // Клик на иконку почты
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка
        Assertions.assertEquals("Тестовый2 Тестич2", driver.findElement(By.xpath("//*[@aria-label='Тестовый2 Тестич2']")).getText()); // Сверка даты
        driver.findElement(By.xpath("//*[@data-click-counter='75068944']")).click(); // Клик на кнопку "Выйти"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка
        driver.findElement(By.xpath("//*[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big mpnhkph__de8k2m']")).isDisplayed(); // Проверка на наличие кнопки "Создать почту"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Задержка

        driver.quit();
    }
}
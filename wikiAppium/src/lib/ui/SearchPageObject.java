package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text='Поиск по Википедии']",
            SEARCH_INPUT = "//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRING}']",
            TITLE_RESULT = "//android.widget.TextView[@text='{SUBSTRING}']",
            SAVE_BUTTON = "org.wikipedia:id/page_save",
            OK_BUTTON = "android:id/button1",
            SKIP_BUTTON = "//*[contains(@text, 'Пропустить')]",
            ADD_IN_LIST = "//*[contains(@text, 'Добавить в список')]",
            INPUT_NAME_LIST = "//*[contains(@text, 'Название этого списка')]",
            VIEW_LIST = "org.wikipedia:id/snackbar_action",
            DELETE_LIST = "//android.widget.TextView[@resource-id='org.wikipedia:id/title' and @text='Удалить список']",
            OVERFLOW_MENU = "org.wikipedia:id/item_overflow_menu",
            FILTER_LIST = "org.wikipedia:id/menu_search_lists",
            INPUT_FILTER_LIST = "//*[contains(@text, 'Отфильтровать мои списки')]",
            CLOSE_FILTER_LIST = "org.wikipedia:id/action_mode_close_button";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    private static String getResultTitleElement(String substring) {
        return TITLE_RESULT.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        waitForElementAndClick(By.xpath(SKIP_BUTTON),
                "Невозможно нажать 'Пропустить'",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода",
                Duration.ofSeconds(15));
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                search_line,
                "Невозможно найти поле ввода",
                Duration.ofSeconds(15));
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Невозможно найти " + substring, Duration.ofSeconds(15));
    }

    public void equalsTitleSearchResult(String title) {
        String title_result_xpath = getResultTitleElement(title);
        WebElement title_element = this.waitForElementPresent(By.xpath(title_result_xpath),
                "Невозможно найти " + title,
                Duration.ofSeconds(15));
        String result = title_element.getText();
        Assert.assertEquals("Найдено несовпадение в названии статьи",
                title,
                result);
    }

    public void clickPageSaveAndAddToList(String title) {
        this.waitForElementAndClick(By.id(SAVE_BUTTON),
                "Не удалось нажать на 'Сохранить'",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.xpath(ADD_IN_LIST),
                "Невозможно нажать на 'Добавить в список'",
                Duration.ofSeconds(15));
        this.waitForElementAndSendKeys(By.xpath(INPUT_NAME_LIST),
                title,
                "Невозможно ввести название списка",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.id(OK_BUTTON),
                "Невозможно нажать на кнопку 'ОК'",
                Duration.ofSeconds(15));
    }

    public void viewListAndDeleteHim() {
        this.waitForElementAndClick(By.id(VIEW_LIST),
                "Невозможно нажать на 'Посмотреть список'",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.id(OVERFLOW_MENU),
                "Невозможно нажать на кнопку 'Ещё'",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.xpath(DELETE_LIST),
                "Невозможно нажать на 'Удалить список'",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.id(OK_BUTTON),
                "Не удалось нажать на кнопку 'ОК'",
                Duration.ofSeconds(15));
    }

    public void checkMissingList(String title) {
        this.waitForElementAndClick(By.id(FILTER_LIST),
                "Невозможно нажать кнопку фильтра",
                Duration.ofSeconds(15));
        this.waitForElementAndSendKeys(By.xpath(INPUT_FILTER_LIST),
                title, "Невозможно ввести название списка",
                Duration.ofSeconds(15));
        this.waitForElementAndClick(By.id(CLOSE_FILTER_LIST),
                "Невозможно нажать кнопку возврата в раздел списков",
                Duration.ofSeconds(15));
    }
}

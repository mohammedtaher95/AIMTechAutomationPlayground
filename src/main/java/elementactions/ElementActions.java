package elementactions;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public ElementActions click(By locator) {
        System.out.println("Clicking on element: " + locator.toString());
        try {
            driver.findElement(locator).click();
        } catch (NoSuchElementException | ElementClickInterceptedException
                 | StaleElementReferenceException | TimeoutException e) {
            System.out.println("Element not clickable, trying to click using JavaScript");
            scrollToElement(locator);
            clickUsingJavaScript(locator);
        }
        return this;
    }

    public ElementActions fillField(By locator, String text) {
        clearField(locator);
        System.out.println("Filling field: " + locator.toString());
        driver.findElement(locator).sendKeys(text);
        return this;
    }

    public ElementActions clearField(By locator) {
        System.out.println("Clearing field: " + locator.toString());
        driver.findElement(locator).clear();
        return this;
    }

    public String getTextOf(By locator) {
        System.out.println("Getting text from element: " + locator.toString());
        return driver.findElement(locator).getText();
    }

    public ElementActions hoverOnItem(By locator) {
        System.out.println("Hovering on item: " + locator.toString());
        // Implement hover action using Actions class
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
        return this;
    }

    public ElementActions scrollToElement(By locator) {
        System.out.println("Scrolling to element: " + locator.toString());
        // Implement scroll action using JavaScriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
        return this;
    }

    public ElementActions clickUsingJavaScript(By locator) {
        System.out.println("Clicking on element using JavaScript: " + locator.toString());
        // Implement click action using JavaScriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
        return this;
    }

    public ElementActions selectFromDropdownByValue(By locator, String value) {
        System.out.println("Selecting from dropdown by value: " + locator.toString());
        // Implement select action using Select class
        new Select(driver.findElement(locator)).selectByValue(value);
        return this;
    }

    public ElementActions selectFromDropdownByVisibleText(By locator, String visibleText) {
        System.out.println("Selecting from dropdown by visible text: " + locator.toString());
        // Implement select action using Select class
        new Select(driver.findElement(locator)).selectByVisibleText(visibleText);
        return this;
    }

    public ElementActions selectFromDropdownByIndex(By locator, int index) {
        System.out.println("Selecting from dropdown by index: " + locator.toString());
        // Implement select action using Select class
        new Select(driver.findElement(locator)).selectByIndex(index);
        return this;
    }

    public Boolean isDisplayed(By locator) {
        System.out.println("Checking if element is displayed: " + locator.toString());
        // Implement check for element visibility
        return driver.findElement(locator).isDisplayed();
    }

    public Boolean isClickable(By locator) {
        System.out.println("Checking if element is clickable: " + locator.toString());
        // Implement check for element clickability
        return driver.findElement(locator).isEnabled();
    }

    public Boolean isSelected(By locator) {
        System.out.println("Checking if element is selected: " + locator.toString());
        // Implement check for element selection
        return driver.findElement(locator).isSelected();
    }

    public ElementActions waitForElementToBeClickable(By locator, Duration timeout) {
        System.out.println("Waiting for element to be clickable: " + locator.toString());
        // Implement wait for element to be clickable
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    public ElementActions acceptAlert() {
        System.out.println("Accepting alert");
        // Implement alert acceptance
        driver.switchTo().alert().accept();
        return this;
    }

    public ElementActions dismissAlert() {
        System.out.println("Dismissing alert");
        // Implement alert dismissal
        driver.switchTo().alert().dismiss();
        return this;
    }

    public ElementActions addTextToAlert(String text) {
        System.out.println("Adding Text to alert: " + text);
        // Implement sending keys to alert
        driver.switchTo().alert().sendKeys(text);
        return this;
    }

    public String getAlertText() {
        System.out.println("Getting alert text");
        // Implement getting text from alert
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        return alertText;
    }

    public WebElement waitForElementToBeVisible(By locator, Duration timeout) {
        System.out.println("Waiting for element to be visible: " + locator.toString());
        // Implement wait for element to be visible
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public @Nullable WebElement waitForElementToBePresent(By locator, Duration timeout) {
        System.out.println("Waiting for element to be present: " + locator.toString());
        // Implement wait for element to be present in the DOM
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public Boolean waitForElementToBeInvisible(By locator, Duration timeout) {
        System.out.println("Waiting for element to be invisible: " + locator.toString());
        // Implement wait for element to be invisible
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }

}

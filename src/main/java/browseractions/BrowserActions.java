package browseractions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserActions {

    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        System.out.println("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        System.out.println("Page Title: " + driver.getTitle());
        return driver.getTitle();
    }

    public BrowserActions getToUrl(String url) {
        System.out.println("Navigating to URL: " + url);
        driver.get(url);
        return this;
    }

    public BrowserActions navigateToUrl(String url) {
        System.out.println("Navigating to URL: " + url);
        driver.navigate().to(url);
        return this;
    }

    public BrowserActions navigateForward() {
        System.out.println("Navigating forward");
        driver.navigate().forward();
        return this;
    }

    public BrowserActions navigateBack() {
        System.out.println("Navigating back");
        driver.navigate().back();
        return this;
    }

    public BrowserActions refreshPage() {
        System.out.println("Refreshing page");
        driver.navigate().refresh();
        return this;
    }

    public BrowserActions scrollToBottom() {
        System.out.println("Scrolling to bottom");
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public BrowserActions scrollToTop() {
        System.out.println("Scrolling to top");
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        return this;
    }

    public BrowserActions maximizeWindow() {
        System.out.println("Maximizing window");
        driver.manage().window().maximize();
        return this;
    }

    public BrowserActions setWindowSize(int width, int height) {
        System.out.println("Setting window size");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        return this;
    }

    public BrowserActions switchToNewTab() {
        System.out.println("Switching to new tab");
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        return this;
    }

    public BrowserActions switchToNewWindow() {
        System.out.println("Switching to new window");
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.WINDOW);
        return this;
    }

    public BrowserActions switchToWindow(String windowHandle) {
        System.out.println("Switching to window: " + windowHandle);
        driver.switchTo().window(windowHandle);
        return this;
    }

    public BrowserActions closeCurrentWindow() {
        System.out.println("Closing current window");
        driver.close();
        return this;
    }

    public BrowserActions switchToFrame(String frameNameOrId) {
        System.out.println("Switching to frame: " + frameNameOrId);
        driver.switchTo().frame(frameNameOrId);
        return this;
    }

    public BrowserActions switchToFrame(int frameIndex) {
        System.out.println("Switching to frame: " + frameIndex);
        driver.switchTo().frame(frameIndex);
        return this;
    }

    public BrowserActions switchToFrame(WebElement frameElement) {
        System.out.println("Switching to frame: " + frameElement);
        driver.switchTo().frame(frameElement);
        return this;
    }

    public BrowserActions switchToDefaultContent() {
        System.out.println("Switching to default content");
        driver.switchTo().defaultContent();
        return this;
    }

    public BrowserActions switchToParentFrame() {
        System.out.println("Switching to parent frame");
        driver.switchTo().parentFrame();
        return this;
    }

    public BrowserActions addCookie(Cookie cookie) {
        System.out.println("Adding cookie: " + cookie);
        driver.manage().addCookie(cookie);
        return this;
    }


    public BrowserActions deleteCookie(Cookie cookie) {
        System.out.println("Deleting cookie: " + cookie);
        driver.manage().deleteCookie(cookie);
        return this;
    }

    public BrowserActions deleteCookieNamed(String name) {
        System.out.println("Deleting cookie with name: " + name);
        driver.manage().deleteCookieNamed(name);
        return this;
    }

    public BrowserActions deleteAllCookies() {
        System.out.println("Deleting all cookies");
        driver.manage().deleteAllCookies();
        return this;
    }
}

package in.susovan87.hybridappautomator.screen;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.susovan87.hybridappautomator.common.DriverBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class AbstractScreen
{
    protected final AppiumDriver<MobileElement> driver;

    public AbstractScreen(AppiumDriver<MobileElement> driver)
    {
        this.driver = driver;
    }

    public MobileElement findElementWithTimeout(By by, int timeOutInSeconds)
    {
        return (MobileElement) (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public abstract boolean isDisplayed();

    public boolean isDisplayed(MobileElement... elements)
    {
        WebDriverWait wait = new WebDriverWait(driver, DriverBuilder.EXPLICIT_WAIT);
        try
        {
            wait.until(ExpectedConditions.visibilityOfAllElements(Arrays.asList(elements)));
        }
        catch (TimeoutException e)
        {
            return false;
        }

        return true;
    }
}

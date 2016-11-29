package in.susovan87.hybridappautomator.screen;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class NativeScreen extends AbstractScreen
{
    public NativeScreen(AppiumDriver<MobileElement> driver)
    {
        super(driver);

        driver.context("NATIVE_APP");

        PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);
    }

    @Override
    public abstract boolean isDisplayed();
}

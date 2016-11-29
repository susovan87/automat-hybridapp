package in.susovan87.hybridappautomator.screen;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class WebScreen extends AbstractScreen
{
    public WebScreen(AppiumDriver<MobileElement> driver)
    {
        super(driver);

        for (String context : driver.getContextHandles())
        {
            if (context.startsWith("WEBVIEW"))
            {
                driver.context(context);
                System.out.println("Switched context to " + context);
                break;
            }
        }

        PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);
    }

    @Override
    public abstract boolean isDisplayed();
}

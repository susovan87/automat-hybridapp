package in.susovan87.hybridappautomator.app;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.testng.Reporter;

import in.susovan87.hybridappautomator.common.Screenshot;
import in.susovan87.hybridappautomator.screen.AbstractScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class BaseApp
{
    public static final String APP = "APP";

    protected AppiumDriver<MobileElement> driver;

    private final boolean isReLaunchable;

    public BaseApp(boolean isReLaunchable)
    {
        this.isReLaunchable = isReLaunchable;
    }

    public void setDriver(AppiumDriver<MobileElement> driver)
    {
        this.driver = driver;
    }

    public void launch()
    {
        this.driver.launchApp();
    }

    public void close()
    {
        this.driver.closeApp();
    }

    public void reLaunch()
    {
        this.driver.resetApp();
    }

    public void uninstall()
    {
        this.driver.removeApp(getPackage());
    }

    public <T extends AbstractScreen> T getScreen(Class<T> typeOfT)
    {
        try
        {
            T screen = typeOfT.getDeclaredConstructor(AppiumDriver.class).newInstance(this.driver);
            return screen;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public File takeScreenShot()
    {
        return new Screenshot(Reporter.getCurrentTestResult(), driver).take();
    }

    public abstract String getPackage();

    public abstract String getMainActivity();

    public boolean isReLaunchable()
    {
        return isReLaunchable;
    }
}

package in.susovan87.hybridappautomator.test;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import in.susovan87.hybridappautomator.app.BaseApp;
import in.susovan87.hybridappautomator.common.DevConfig;
import in.susovan87.hybridappautomator.common.DriverBuilder;
import in.susovan87.hybridappautomator.common.Platform;
import in.susovan87.hybridappautomator.listeners.TestListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

@Listeners({TestListener.class})
public abstract class AbstractTest<T extends BaseApp>
{
    private AppiumDriver<MobileElement> driver;
    private URL url;
    private T app;

    public Platform platform;

    @BeforeMethod
    @Parameters({"platform", "host", "port"})
    public void connect(@Optional() String strPlatform, @Optional("localhost") String host, @Optional("4723") String port)
        throws MalformedURLException
    {
        String strUrl;
        if (strPlatform == null)
        {
            System.out.println("Trying to load 'devconfig.properties' as parameters are not supplied");
            strUrl = "http://" + DevConfig.HOST + ":" + DevConfig.PORT + "/wd/hub";
            this.platform = Platform.parse(DevConfig.PLATFORM.toUpperCase());
        }
        else
        {
            strUrl = "http://" + host + ":" + port + "/wd/hub";
            this.platform = Platform.parse(strPlatform.toUpperCase());
        }

        this.url = new URL(strUrl);
    }

    @AfterMethod
    public void closeSession() throws Exception
    {
        if (driver != null)
        {

            if (app.isReLaunchable())
            {
                app.uninstall();
            }

            driver.closeApp();

            System.out.println("[" + platform.toString() + "] Closing session: " + driver.getSessionId());
            driver.quit();
        }
    }

    protected T getReLaunchableApp(Class<T> typeOfT)
    {
        return createApp(typeOfT, true);
    }

    protected T getApp(Class<T> typeOfT)
    {
        return createApp(typeOfT, false);
    }

    private T createApp(Class<T> typeOfT, boolean isReLaunchable)
    {
        try
        {
            app = typeOfT.getDeclaredConstructor(boolean.class).newInstance(isReLaunchable);
            DriverBuilder<T> builder = new DriverBuilder<T>(app);
            this.driver = builder.build(url, platform);
            app.setDriver(driver);
            Reporter.getCurrentTestResult().getTestContext().setAttribute(BaseApp.APP, app);
            return app;
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
}

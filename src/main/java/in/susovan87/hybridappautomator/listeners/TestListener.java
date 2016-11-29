package in.susovan87.hybridappautomator.listeners;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import in.susovan87.hybridappautomator.app.BaseApp;
import in.susovan87.hybridappautomator.common.Screenshot;

public class TestListener implements ITestListener
{

    public void onTestStart(ITestResult result)
    {
        result.getTestContext().setAttribute(Screenshot.TEST_METHOD, result.getName());
    }

    public void onTestSuccess(ITestResult result)
    {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult result)
    {
        BaseApp app = (BaseApp) result.getTestContext().getAttribute(BaseApp.APP);
        if (app != null)
        {
            app.takeScreenShot();
        }
    }

    public void onTestSkipped(ITestResult result)
    {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context)
    {
        Path screenshotPath = Paths.get(Paths.get(context.getOutputDirectory()).getParent().toString(), "screenshot", context.getName());
        new File(screenshotPath.toString()).mkdirs();
        context.setAttribute(Screenshot.SCREENSHOT_DIR, screenshotPath.toString());
    }

    public void onFinish(ITestContext context)
    {
        // TODO Auto-generated method stub

    }
}

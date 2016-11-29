/*******************************************************************************
 * Copyright (c) 2016 Susovan Ghosh <susovan87@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/
package com.github.susovan87.hybridappautomator.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Screenshot {
  public static final String SCREENSHOT_DIR = "SCREENSHOT_DIR";
  public static final String TEST_METHOD = "TEST_METHOD";

  private final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

  private String fullName, name, relativePath;
  private ITestResult testResult;
  private AppiumDriver<MobileElement> driver;

  public Screenshot(ITestResult testResult, AppiumDriver<MobileElement> driver) {
    this.testResult = testResult;
    this.driver = driver;
    this.name = this.generateFileName();
  }

  public File take() {
    File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
    try {
      File screenShot = new File(this.getDir(), this.getName());
      FileUtils.copyFile(srcFile, screenShot);
      fullName = screenShot.getAbsolutePath();
      System.setProperty(ESCAPE_PROPERTY, "false");
      Reporter.log(generateHtml());
      return screenShot;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String getFullName() {
    return this.fullName;
  }

  public String getDir() {
    return (String) testResult.getTestContext().getAttribute(SCREENSHOT_DIR);
  }

  public String getName() {
    return this.name;
  }

  public String getRelativePath() {
    if (this.relativePath == null) {
      String[] arrPath = Paths.get(getDir(), this.name).toString().split(File.separator);
      String[] arrRelativePath = Arrays.copyOfRange(arrPath, arrPath.length - 4, arrPath.length);
      arrRelativePath[0] = "..";
      this.relativePath = String.join("/", arrRelativePath);
    }

    return this.relativePath;
  }

  private String generateHtml() {
    String imageHtmlFormat = "<a href='%s'> <img src='%s' style='%s' title='%s'/></a>";
    return String.format(imageHtmlFormat, getRelativePath(), getRelativePath(), getStyle(),
        getName());
  }

  private String getStyle() {
    System.out.println(this.testResult.getStatus());
    String stylePattern = "height:200px;border:%s solid %spx;";
    if (this.testResult.getStatus() == ITestResult.FAILURE) {
      return String.format(stylePattern, "red", 2);
    }
    return String.format(stylePattern, "gray", 1);
  }

  private String generateFileName() {
    String nameFormat = "%s.%s.jpg";
    if (this.testResult.getStatus() == ITestResult.FAILURE) {
      nameFormat = "%s.%s.ERR.jpg";
    }

    return String.format(nameFormat, testResult.getTestClass().getRealClass().getSimpleName(),
        getTestMethod());
  }

  private String getTestMethod() {
    return (String) testResult.getTestContext().getAttribute(TEST_METHOD);
  }
}

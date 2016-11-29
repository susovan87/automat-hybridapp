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
package com.github.susovan87.hybridappautomator.screen;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.susovan87.hybridappautomator.common.DriverBuilder;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class AbstractScreen {
  protected final AppiumDriver<MobileElement> driver;

  public AbstractScreen(AppiumDriver<MobileElement> driver) {
    this.driver = driver;
  }

  public MobileElement findElementWithTimeout(By by, int timeOutInSeconds) {
    return (MobileElement) (new WebDriverWait(driver, timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public abstract boolean isDisplayed();

  public boolean isDisplayed(MobileElement... elements) {
    WebDriverWait wait = new WebDriverWait(driver, DriverBuilder.EXPLICIT_WAIT);
    try {
      wait.until(ExpectedConditions.visibilityOfAllElements(Arrays.asList(elements)));
    } catch (TimeoutException e) {
      return false;
    }

    return true;
  }
}

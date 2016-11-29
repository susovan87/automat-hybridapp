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

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.github.susovan87.hybridappautomator.app.BaseApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverBuilder<T extends BaseApp> {
  public static int IMPLICIT_WAIT = 35;
  public static int EXPLICIT_WAIT = 20;

  final DesiredCapabilities cap;

  public DriverBuilder(T app) {
    cap = new DesiredCapabilities();

    if (app.getPackage() != null) {
      cap.setCapability("appPackage", app.getPackage());
    }

    if (app.getMainActivity() != null) {
      cap.setCapability("appActivity", app.getMainActivity());
    }

    if (app.isReLaunchable()) {
      cap.setCapability("fullReset", false);
      cap.setCapability("noReset", true);
    }
  }

  public AppiumDriver<MobileElement> build(URL url, Platform platform) {
    AppiumDriver<MobileElement> driver;
    switch (platform) {
      case ANDROID:
        driver = new AndroidDriver<MobileElement>(url, cap);
        break;
      case IOS:
        driver = new IOSDriver<MobileElement>(url, cap);
        break;
      default:
        throw new IllegalArgumentException("Platform must be supplied (Android/iOS)");
    }

    driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
    System.out.println("[" + platform + "] Creating new session: " + driver.getSessionId());
    return driver;
  }
}

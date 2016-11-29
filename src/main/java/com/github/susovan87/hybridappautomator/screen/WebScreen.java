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

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class WebScreen extends AbstractScreen {
  public WebScreen(AppiumDriver<MobileElement> driver) {
    super(driver);

    for (String context : driver.getContextHandles()) {
      if (context.startsWith("WEBVIEW")) {
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

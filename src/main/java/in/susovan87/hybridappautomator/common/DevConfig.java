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
package in.susovan87.hybridappautomator.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class DevConfig {

  static Properties prop;
  public static final String PLATFORM = getPlatform();
  public static final String HOST = getHost();
  public static final String PORT = getPort();

  private DevConfig() {}

  public static void loadProperties() {
    try {
      prop = new Properties();
      InputStream input = new FileInputStream("devconfig.properties");
      prop.load(input);
      input.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private static String getPort() {
    if (prop == null) {
      loadProperties();
    }

    return getValueOrDefault(prop.getProperty("port"), "4723");
  }

  private static String getHost() {
    if (prop == null) {
      loadProperties();
    }

    return getValueOrDefault(prop.getProperty("host"), "localhost");
  }

  @SuppressWarnings("null")
  public static String getPlatform() {
    if (prop == null) {
      loadProperties();
    }

    String platform = prop.getProperty("platform");
    if (platform != null || platform.toUpperCase() == "ANDROID"
        || platform.toUpperCase() == "IOS") {
      return platform;

    }
    throw new IllegalArgumentException("'platform' not available inside devconfig.properties");
  }

  static String getValueOrDefault(String value, String defaultValue) {
    return Optional.ofNullable(value).filter(s -> s != null && !s.isEmpty()).orElse(defaultValue);
  }
}

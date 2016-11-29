package in.susovan87.hybridappautomator.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class DevConfig
{

    static Properties prop;
    public static final String PLATFORM = getPlatform();
    public static final String HOST = getHost();
    public static final String PORT = getPort();

    private DevConfig()
    {
    }

    public static void loadProperties()
    {
        try {
            prop = new Properties();
            InputStream input = new FileInputStream("devconfig.properties");
            prop.load(input);
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getPort()
    {
        if (prop == null)
        {
            loadProperties();
        }

        return getValueOrDefault(prop.getProperty("port"), "4723");
    }

    private static String getHost()
    {
        if (prop == null)
        {
            loadProperties();
        }

        return getValueOrDefault(prop.getProperty("host"), "localhost");
    }

    @SuppressWarnings("null")
    public static String getPlatform()
    {
        if (prop == null)
        {
            loadProperties();
        }

        String platform = prop.getProperty("platform");
        if (platform != null || platform.toUpperCase() == "ANDROID" || platform.toUpperCase() == "IOS")
        {
            return platform;

        }
        throw new IllegalArgumentException("'platform' not available inside devconfig.properties");
    }

    static String getValueOrDefault(String value, String defaultValue)
    {
        return Optional.ofNullable(value).filter(s -> s != null && !s.isEmpty()).orElse(defaultValue);
    }
}

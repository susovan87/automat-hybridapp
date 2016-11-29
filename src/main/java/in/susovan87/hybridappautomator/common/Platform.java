package in.susovan87.hybridappautomator.common;

public enum Platform
{
    ANDROID(1), IOS(2);

    private final int platformCode;

    Platform(int levelCode)
    {
        this.platformCode = levelCode;
    }

    public int getLevelCode()
    {
        return this.platformCode;
    }

    public static Platform parse(String strPlatform)
    {
        return Platform.valueOf(strPlatform);
    }
}

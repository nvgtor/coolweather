package app.coolweather.com.coolweather.util;

/**
 * Created by nvgtor on 2015/5/17.
 */
public interface HttpCallbackListener
{
    void onFinish(String response);

    void onError(Exception e);
}

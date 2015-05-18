package app.coolweather.com.coolweather.util;

import android.text.TextUtils;

import app.coolweather.com.coolweather.db.CoolWeatherDB;
import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.County;
import app.coolweather.com.coolweather.model.Province;

/**
 * Created by nvgtor on 2015/5/18.
 */
public class Utility
{
    /**
     *  �����ʹ�����������ص�ʡ������
      */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
                                                               String response)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allProvince = response.split(",");
            if (allProvince != null && allProvince.length > 0)
            {
                for (String p : allProvince)
                {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //���������������ݸ����洢��Province��
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  �����ʹ���������Żص��м�����
     */
    public static boolean handleCitesResponse(CoolWeatherDB coolWeatherDB, String response, int provincedId)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0)
            {
                for (String c : allCities)
                {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provincedId);
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  �����ʹ�����������ص��ؼ�����
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId)
    {
        if (!TextUtils.isEmpty(response))
        {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0)
            {
                for (String c : allCounties)
                {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //���������������ݴ洢��County��
                    coolWeatherDB.savaCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}

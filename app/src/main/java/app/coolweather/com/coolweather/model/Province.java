package app.coolweather.com.coolweather.model;

/**
 * Created by nvgtor on 2015/5/17.
 */
public class Province
{
    private int id;
    private String provinceName;
    private String provinceCode;

    public int getId()
    {
        return id;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode()
    {
        return provinceCode;
    }

}

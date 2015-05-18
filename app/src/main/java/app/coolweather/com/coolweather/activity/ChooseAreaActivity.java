package app.coolweather.com.coolweather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.coolweather.com.coolweather.R;
import app.coolweather.com.coolweather.db.CoolWeatherDB;
import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.County;
import app.coolweather.com.coolweather.model.Province;

/**
 * Created by nvgtor on 2015/5/18.
 */
public class ChooseAreaActivity extends Activity
{
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CoolWeatherDB coolWeatherDB;
    private List<String> datalist = new ArrayList<String>();

    /**
     *  省列表
     */
    private List<Province> provinceList;
    /**
     *  市列表
     */
    private List<City> cityList;
    /**
     *  县列表
     */
    private List<County> countyList;
    /**
     *  选中的省份
     */
    private Province selectedProvince;
    /**
     *  选中的城市
     */
    private City selectedCity;
    /**
     *  当前选中的级别
     */
    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        listView = (ListView)findViewById(R.id.list_view);
        titleText = (TextView) findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datalist);
        listView.setAdapter(adapter);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (currentLevel == LEVEL_PROVINCE)
                {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                }else if(currentLevel == LEVEL_CITY)
                {
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
                return false; //和OnItemClickListenner的区别
            }
        });
        queryProvinces(); //加载省级数据
    }

    /**
     *  查询所有的省，优先从数据库查询，如果没有查询到再去服务器查询
     */
    private void queryProvinces()
    {
        provinceList = coolWeatherDB.loadProvinces();
        if (provinceList.size() > 0)
        {
            datalist.clear();
            for (Province province : provinceList)
            {
                datalist.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        }else
        {
            queryFromServer(null, "province");
        }
    }

    /**
     *  查询选中省内所有的市，优先从数据查询，若果没有查询到再去服务器上查询
     */
    private void queryCities()
    {
        cityList = coolWeatherDB.loadCities(selectedProvince.getId());
        if (cityList.size() > 0)
        {
            datalist.clear();;
            for (City city : cityList)
            {
                datalist.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectedProvince.getProvinceCode());
            currentLevel = LEVEL_CITY;
        }else
        {
            queryFromServer(selectedProvince.getProvinceCode(), "city");
        }
    }

    /**
     *  查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCounties()
    {

    }

    /**
     *  根据传入的代号和类型从服务器上查询省市县数据
     */
    private void queryFromServer(final String code, final String type)
    {

    }

    /**
     *  显示进度条对话框
     */
    private void showProgressDialog()
    {

    }
    /**
     *  关闭进度对话框
     */
    private void closeProgressDialog()
    {

    }

    /**
     * 捕获Back按键，根据当前的级别来判断，此时应该返回市列表、省列表、还是直接退出
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}

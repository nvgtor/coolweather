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
     *  ʡ�б�
     */
    private List<Province> provinceList;
    /**
     *  ���б�
     */
    private List<City> cityList;
    /**
     *  ���б�
     */
    private List<County> countyList;
    /**
     *  ѡ�е�ʡ��
     */
    private Province selectedProvince;
    /**
     *  ѡ�еĳ���
     */
    private City selectedCity;
    /**
     *  ��ǰѡ�еļ���
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
                return false; //��OnItemClickListenner������
            }
        });
        queryProvinces(); //����ʡ������
    }

    /**
     *  ��ѯ���е�ʡ�����ȴ����ݿ��ѯ�����û�в�ѯ����ȥ��������ѯ
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
            titleText.setText("�й�");
            currentLevel = LEVEL_PROVINCE;
        }else
        {
            queryFromServer(null, "province");
        }
    }

    /**
     *  ��ѯѡ��ʡ�����е��У����ȴ����ݲ�ѯ������û�в�ѯ����ȥ�������ϲ�ѯ
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
     *  ��ѯѡ���������е��أ����ȴ����ݿ��ѯ�����û�в�ѯ����ȥ�������ϲ�ѯ
     */
    private void queryCounties()
    {

    }

    /**
     *  ���ݴ���Ĵ��ź����ʹӷ������ϲ�ѯʡ��������
     */
    private void queryFromServer(final String code, final String type)
    {

    }

    /**
     *  ��ʾ�������Ի���
     */
    private void showProgressDialog()
    {

    }
    /**
     *  �رս��ȶԻ���
     */
    private void closeProgressDialog()
    {

    }

    /**
     * ����Back���������ݵ�ǰ�ļ������жϣ���ʱӦ�÷������б�ʡ�б�����ֱ���˳�
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}

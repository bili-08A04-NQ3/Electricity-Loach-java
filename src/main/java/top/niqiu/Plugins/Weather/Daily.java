package top.niqiu.Plugins.Weather;

import com.alibaba.fastjson2.annotation.JSONField;

public class Daily {
    @JSONField
    public String date;
    @JSONField
    public String text_day;
    @JSONField
    public String code_day;
    @JSONField
    public String text_night;
    @JSONField
    public String code_night;
    @JSONField
    public String high;
    @JSONField
    public String low;
    @JSONField
    public String rainfall;
    @JSONField
    public String precip;
    @JSONField
    public String wind_direction;
    @JSONField
    public String wind_direction_degree;
    @JSONField
    public String wind_speed;
    @JSONField
    public String wind_scale;
    @JSONField
    public String humidity;

    public String toString(String city, String timezone_offset) {
        String Result = "泥鳅牌天气预报\n城市:" + city + ",日期:" + date + "(UTC " + timezone_offset + ")\n";
        Result += "白天" + text_day + ",夜晚" + text_night + "\n";
        Result += "当日最高温度:" + high + "摄氏度,最低温度:" + low + "摄氏度\n";
        Result += "当日降水量:" + rainfall + ",降水概率:" + precip + "\n";
        Result += "风向:" + wind_direction + ",风速:" + wind_speed + "风速等级:" + wind_scale + "\n";
        Result += "湿度:" + humidity;
        return Result;
    }
}

package top.niqiu.Plugins.Weather;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import top.niqiu.Main;
import top.niqiu.Plugins.Plugin;
import top.niqiu.core.Sesson.MessageSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Weather extends Plugin {
    public static String weatherAPI;
    public static String weatherAPIKey;
    @Override
    public void onMessage(MessageSession session) throws IOException {
        super.onMessage(session);
        String[] rd = session.message.split(" ");
        if (rd[0].equals("天气")) {
            String org = "api并没有返回";
            try {
                String city = rd[1];
                String s = weatherAPI + "?key=" + weatherAPIKey + "&location=" +
                        city +
                        "&language=zh-Hans&unit=c&start=-1&days=8";
                org = FetchURL(new URL(s));
                JSONObject object = JSONObject.parseObject(org, JSONObject.class);
                JSONArray array = object.getJSONArray("results");
                JSONObject location = array.getJSONObject(0).getJSONObject("location");
                String Timezone_Offset = location.getString("timezone_offset");
                JSONArray weathers = array.getJSONObject(0).getJSONArray("daily");
                Daily daily = weathers.getJSONObject(0).toJavaObject(Daily.class);
                session.send(daily.toString(city, Timezone_Offset));
            } catch (IOException e) {
                e.printStackTrace();
                session.send("获取失败, api返回值: " + org);
            }
        }
    }

    public static String FetchURL(URL url) throws IOException{
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(10 * 1000);
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String s = reader.readLine();
        reader.close();
        return s;
    }

    public void loadBasicSettings() throws IOException {
        JSONObject object = Main.getResource(this.getResourcesPath());
        if (object != null) {
            weatherAPI = object.getString("weatherAPI");
            weatherAPIKey = object.getString("weatherAPIKey");
        } else {
            throw new IOException();
        }
    }
}

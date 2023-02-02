package top.niqiu;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import top.niqiu.Plugins.Hash.Hash;
import top.niqiu.Plugins.Plugin;
import top.niqiu.Plugins.Random.RandomInt;
import top.niqiu.Plugins.Weather.Weather;
import top.niqiu.core.Bot;
import top.niqiu.core.BotEventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Main {
    public static Random random = new Random();
    public static Bot receiver;

    public static void main(String[] args) {
         try {
             BotEventHandler handler = new BotEventHandler(1145);
             receiver = new Bot("localhost", 3735, "Observer", handler);
             new Weather();
             new Hash();
             new RandomInt();
             Plugin.loadAllBasicSettings();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static JSONObject getResource(String path) throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(path);
        if (stream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
            reader.close();
            return (JSONObject) JSON.parse(builder.toString());
        }
        throw new IOException();
    }
}

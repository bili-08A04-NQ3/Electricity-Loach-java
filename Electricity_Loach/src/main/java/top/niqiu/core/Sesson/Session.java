package top.niqiu.core.Sesson;

import com.alibaba.fastjson2.JSONObject;
import top.niqiu.core.Bot;

import java.io.IOException;

public abstract class Session {
    public String message;
    public abstract String send(String msg) throws IOException;
//     public abstract String reply(String msg) throws IOException;
    public abstract void setBot(Bot bot);

    public static Session getSession(String orgMessage) {
        JSONObject o = JSONObject.parseObject(orgMessage, JSONObject.class);
        System.out.println(o);
        String type = o.getString("post_type");
        switch (type) {
            case "message":
                return JSONObject.parseObject(orgMessage, MessageSession.class);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Session{" +
                "message='" + message + '\'' +
                '}';
    }
}

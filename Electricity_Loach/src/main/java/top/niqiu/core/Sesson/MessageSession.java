package top.niqiu.core.Sesson;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;
import top.niqiu.core.Bot;

import java.io.IOException;

@JSONType
public class MessageSession extends Session {
    @JSONField
    public String post_type;
    @JSONField
    public String message_type;
    @JSONField
    public long time;
    @JSONField
    public long self_id;
    @JSONField
    public String sub_type;
    @JSONField
    public long user_id;
    @JSONField
    public int message_id;
    @JSONField
    public long group_id;
    @JSONField
    public String message;
    @JSONField
    public long message_seq;
    @JSONField
    public String raw_message;
    @JSONField
    public Sender sender;
    public Bot bot;

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Bot getBot() {
        return bot;
    }

    @Override
    public String send(String msg) throws IOException {
        if (this.message_type.equals("group")) {
             return this.bot.sendGroupMessage(this.group_id, msg);
        }
        return this.bot.sendPrivateMessage(this.user_id, msg);
    }

    @Override
    public String toString() {
        return "MessageSession{" +
                "post_type='" + post_type + '\'' +
                ", message_type='" + message_type + '\'' +
                ", time=" + time +
                ", self_id=" + self_id +
                ", sub_type='" + sub_type + '\'' +
                ", user_id=" + user_id +
                ", message_id=" + message_id +
                ", group_id=" + group_id +
                ", message='" + message + '\'' +
                ", message_seq=" + message_seq +
                ", raw_message='" + raw_message + '\'' +
                ", sender=" + sender +
                ", bot=" + bot +
                '}';
    }

    //    @Override
//    public String reply(String msg) throws IOException {
//        return this.bot.replyMessage(this.message_id, msg);
//    }
}

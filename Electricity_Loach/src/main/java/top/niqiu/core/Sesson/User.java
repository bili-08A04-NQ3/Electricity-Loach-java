package top.niqiu.core.Sesson;

import com.alibaba.fastjson2.annotation.JSONField;

public class User {
    @JSONField
    public String age;
    @JSONField
    public String area;
    @JSONField
    public String card;
    @JSONField
    public String level;
    @JSONField
    public String nickname;
    @JSONField
    public String role;
    @JSONField
    public String sex;
    @JSONField
    public String title;
    @JSONField
    public User user_id;

    @Override
    public String toString() {
        return "User{" +
                "age='" + age + '\'' +
                ", area='" + area + '\'' +
                ", card='" + card + '\'' +
                ", level='" + level + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role='" + role + '\'' +
                ", sex='" + sex + '\'' +
                ", title='" + title + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}

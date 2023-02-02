package top.niqiu.core.Sesson;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;

@JSONType
public class Sender {
    @JSONField
    public int age;
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
    public long user_id;
}

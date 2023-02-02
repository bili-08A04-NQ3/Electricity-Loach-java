package top.niqiu.core;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class Bot {
    public final String ip;
    public final int port;
    public String name;
    public BotEventHandler handler;

    private Bot(String ip, int port, BotEventHandler handler) {
        this.ip = ip;
        this.port = port;
        this.handler = handler.setBot(this);
    }

    public Bot(String ip, int port, String name, BotEventHandler handler) {
        this(ip, port, handler);
        this.name = name;
    }

    public String acceptFriendAddRequest(String flag, String remark) throws IOException {

        String pattern = "set_friend_add_request?flag=[flag]&remark=[remark]&approve=true"
                .replace("[flag]", flag).replace("remark", remark);
        return this.send(pattern);
    }

    public String refuseFriendAddRequest(String flag) throws IOException {
        String pattern = "set_friend_add_request?flag=[flag]&remark=[remark]&approve=false"
                .replace("[flag]", flag).replace("remark", "");
        return this.send(pattern);
    }

    public String acceptGroupAddRequest(String flag, String type) throws IOException {
        String pattern = "set_group_add_request?flag=[flag]&type=[type]&approve=true"
                .replace("type", type).replace("[flag]", flag);
        return this.send(pattern);
    }

    public String refuseGroupAddRequest(String flag, String type, String reason) throws IOException {
        String pattern = "set_group_add_request?flag=[flag]&type=[type]&reason=[reason]"
                .replace("[type]", type).replace("[flag]", flag)
                .replace("[reason]", reason);
        return this.send(pattern);
    }

    public String sendPrivateMessage(long user_id, String message) throws IOException {
        return this.sendPrivateMessage(user_id, 0, message, false);
    }

    public String sendPrivateMessage(long user_id, long group_id, String message, boolean auto_escape) throws IOException {
        message = URLEncoder.encode(message);
        String pattern = "send_private_msg?user_id=[user_id]&group_id=[group_id]&message=[message]&auto_escape=[auto_escape]"
                .replace("[user_id]", String.valueOf(user_id)).replace("group_id=[group_id]&", (group_id == 0) ? "" : "group_id=" + group_id + "&")
                .replace("[message]", message).replace("[auto_escape]", String.valueOf(auto_escape));
        return this.send(pattern);
    }

    public String sendGroupMessage(long group_id, String message) throws IOException {
        return this.sendGroupMessage(group_id, message, false);
    }

    public String sendGroupMessage(long group_id, String message, boolean auto_escape) throws IOException {
        message = URLEncoder.encode(message);
        String pattern = "send_group_msg?group_id=[group_id]&message=[message]&auto_escape=[auto_escape]"
                .replace("[group_id]", String.valueOf(group_id))
                .replace("[message]", message).replace("[auto_escape]", String.valueOf(auto_escape));
        return this.send(pattern);
    }

    public String deleteMessage(int message_id) throws IOException {
        String pattern = "delete_msg?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

    public String getMessage(int message_id) throws IOException {
        String pattern = "get_msg?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

    public String getForwardMessage(int message_id) throws IOException {
        String pattern = "get_forward_msg?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

    public String getImage(String file) throws IOException {
        String pattern = "get_image?file=[file]"
                .replace("[file]", file);
        return this.send(pattern);
    }

    public String markMessageAsRead(int message_id) throws IOException {
        String pattern = "mark_msg_as_read?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

    public String setGroupKick(long group_id, long user_id) throws IOException {
        return this.setGroupKick(group_id, user_id, false);
    }

    public String setGroupKick(long group_id, long user_id, boolean reject_add_request) throws IOException {
        String pattern = "set_group_kick?group_id=[group_id]&user_id=[user_id]&reject_add_request=[reject_add_request]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id)).replace("[reject_add_request]", String.valueOf(reject_add_request));
        return this.send(pattern);
    }

    public String setGroupBan(long group_id, long user_id) throws IOException {
        return this.setGroupBan(group_id, user_id, 1800);
    }

    public String setGroupBan(long group_id, long user_id, long duration) throws IOException {
        String pattern = "set_group_ban?group_id=[group_id]&user_id=[user_id]&duration=[duration]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id))
                .replace("[duration]", String.valueOf(duration));
        return this.send(pattern);
    }

    public String setGroupWholeBan(long group_id) throws IOException {
        return this.setGroupWholeBan(group_id, true);
    }

    public String setGroupWholeBan(long group_id, boolean enable) throws IOException {
        String pattern = "set_group_whole_ban?group_id=[group_id]&enable=[enable]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[enable]", String.valueOf(enable));
        return this.send(pattern);
    }

    public String setGroupAdmin(long group_id, long user_id) throws IOException {
        return this.setGroupAdmin(group_id, user_id, true);
    }

    public String setGroupAdmin(long group_id, long user_id, boolean enable) throws IOException {
        String pattern = "set_group_admin?group_id=[group_id]&user_id=[user_id]&enable=[enable]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id))
                .replace("[enable]", String.valueOf(enable));
        return this.send(pattern);
    }

    public String setGroupCard(long group_id, long user_id) throws IOException {
        return this.setGroupCard(group_id, user_id, "2323");
    }

    public String setGroupCard(long group_id, long user_id, String card) throws IOException {
        String pattern = "set_group_card?group_id=[group_id]&user_id=[user_id]&card=[card]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id))
                .replace("[card]", card);
        return this.send(pattern);
    }

    public String setGroupName(long group_id, String group_name) throws IOException {
        String pattern = "set_group_name?group_id=[group_id]&group_name=[group_name]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[group_name]", String.valueOf(group_name));
        return this.send(pattern);
    }

    public String setGroupLeave(long group_id) throws IOException {
        return this.setGroupLeave(group_id, false);
    }

    public String setGroupLeave(long group_id, boolean is_dismiss) throws IOException {
        String pattern = "set_group_leave?group_id=[group_id]&is_dismiss=[is_dismiss]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[is_dismiss]", String.valueOf(is_dismiss));
        return this.send(pattern);
    }

    public String setGroupSpecialTitle(long group_id, long user_id, String special_title) throws IOException {
        String pattern = "set_group_special_title?group_id=[group_id]&user_id=[user_id]&special_title=[special_title]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id))
                .replace("[special_title]", special_title);
        return this.send(pattern);
    }

    public String sendGroupSign(long group_id) throws IOException {
        String pattern = "send_group_sign?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String getLoginInfo() throws IOException {
        String pattern = "get_login_info";
        return this.send(pattern);
    }

    public String getStrangerInfo(long user_id) throws IOException {
        String pattern = "get_stranger_info?user_id=[user_id]"
                .replace("[user_id]", String.valueOf(user_id));
        return this.send(pattern);
    }

    public String getFriendList() throws IOException {
        String pattern = "get_friend_list";
        return this.send(pattern);
    }

    public String getUnidirectionalFriendList() throws IOException {
        String pattern = "get_unidirectional_friend_list";
        return this.send(pattern);
    }

    public String deleteFriend(long user_id) throws IOException {
        String pattern = "delete_friend?user_id=[user_id]"
                .replace("[user_id]", String.valueOf(user_id));
        return this.send(pattern);
    }

    public String getGroupInfo(long group_id) throws IOException {
        String pattern = "get_group_info?group_id=[group_id]"
                .replace("[get_group_info]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String getGroupList() throws IOException {
        String pattern = "get_group_list";
        return this.send(pattern);
    }

    public String getGroupMemberInfo(long group_id, long user_id) throws IOException {
        String pattern = "get_group_member_info?group_id=[group_id]&user_id=[user_id]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[user_id]", String.valueOf(user_id));
        return this.send(pattern);
    }

    public String getGroupMemberList(long group_id) throws IOException {
        String pattern = "get_group_member_list?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String getGroupHonorInfo(long group_id) throws IOException {
        return this.getGroupHonorInfo(group_id, "all");
    }

    public String getGroupHonorInfo(long group_id, String type) throws IOException {
        String pattern = "get_group_honor_info?group_id=[group_id]&type=[type]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[type]", type);
        return this.send(pattern);
    }

    public String canSendImage() throws IOException {
        String pattern = "can_send_image";
        return this.send(pattern);
    }

    public String canSendRecord() throws IOException {
        String pattern = "can_send_record";
        return this.send(pattern);
    }

    public String setGroupPortrait(long group_id, String file) throws IOException {
        String pattern = "set_group_portrait?group_id=[group_id]&file=[file]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[file]", file);
        return this.send(pattern);
    }

    public String getGroupSystemMessage() throws IOException {
        String pattern = "get_group_system_msg";
        return this.send(pattern);
    }

    public String uploadPrivateFile(long user_id, String file, String name) throws IOException {
        String pattern = "upload_private_file?user_id=[user_id]&file=[file]&name=[name]"
                .replace("[user_id]", String.valueOf(user_id)).replace("[file]", file)
                .replace("[name]", name);
        return this.send(pattern);
    }

    public String uploadGroupFile(long group_id, String file, String name, String folder) throws IOException {
        String pattern = "upload_group_file?group_id=[group_id]&file=[file]&name=[name]&folder=[folder]"
                .replace("[user_id]", String.valueOf(group_id)).replace("[file]", file)
                .replace("[name]", name).replace("[folder]", folder);
        return this.send(pattern);
    }

    public String getGroupFileSystemInfo(long group_id) throws IOException {
        String pattern = "getGroup_file_system_info?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String getGroupRootFiles(long group_id) throws IOException {
        String pattern = "get_group_root_files?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String getGroupFilesByFolder(long group_id, String folder_id) throws IOException {
        String pattern = "get_group_files_by_folder?group_id=[group_id]&folder_id=[folder_id]"
                .replace("[group_id]", String.valueOf(group_id)).replace("folder_id", folder_id);
        return this.send(pattern);
    }

    public String createGroupFileFolder(long group_id, String name) throws IOException {
        String pattern = "create_group_file_folder?group_id=[group_id]&name=[name]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[name]", name);
        return this.send(pattern);
    }

    public String deleteGroupFolder(long group_id, String folder) throws IOException {
        String pattern = "delete_group_folder?group_id=[group_id]&folder=[folder]"
                .replace("[group_id]", String.valueOf(group_id)).replace("[folder]", folder);
        return this.send(pattern);
    }

    public String getGroupFileUrl(long group_id, String file_id, String busid) throws IOException {
        String pattern = "get_group_file_url?group_id=[group_id]&file_id=[file_id]&busid=[busid]"
                .replace("group_id", String.valueOf(group_id)).replace("[file_id]", file_id)
                .replace("[busid]", busid);
        return this.send(pattern);
    }

    public String getGroupAtAllRemain(long group_id) throws IOException {
        String pattern = "get_group_at_all_remain?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String sendGroupNotice(long group_id, String content, String image) throws IOException {
        String pattern = "_send_group_notice?group_id=[group_id]&content=[content]&image=[image]"
                .replace("group_id", String.valueOf(group_id)).replace("[content]", content)
                .replace("&image=[image]", (image == null) ? "" : "&image=" + image);
        return this.send(pattern);
    }

    public String getGroupNotice(long group_id) throws IOException {
        String pattern = "_get_group_notice?group_id=[group_id]"
                .replace("[group_id]", String.valueOf(group_id));
        return this.send(pattern);
    }

    public String downloadFile(long group_id) throws IOException {
        return this.downloadFile(group_id, 6);
    }

    public String downloadFile(long group_id, int thread_count) throws IOException {
        String pattern = ("download_file?group_id=[group_id]&thread_count=[thread_count]&headers=User-Agent=" + this.name)
                .replace("[group_id]", String.valueOf(group_id)).replace("[thread_count]", String.valueOf(thread_count));
        return this.send(pattern);
    }

    public String setEssenceMessage(int message_id) throws IOException {
        String pattern = "set_essence_msg?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

    public String deleteEssenceMessage(int message_id) throws IOException {
        String pattern = "delete_essence_msg?message_id=[message_id]"
                .replace("[message_id]", String.valueOf(message_id));
        return this.send(pattern);
    }

//    public String replyMessage(int message_id, String text) throws IOException {
//
//    }

    public String send(String pattern) throws IOException {
        String u = "http://[ip]:[port]/[pattern]".replace("[ip]", this.ip).replace("[port]", String.valueOf(this.port)).replace("[pattern]", pattern);
        URL url = new URL(u);
        System.out.println(url);
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String s = reader.readLine();
        reader.close();
        return s;
    }

    public String send(Map<String, String> pattern) throws IOException {
        return null;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}

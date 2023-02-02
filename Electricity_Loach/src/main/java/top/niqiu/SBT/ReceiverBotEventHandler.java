//package top.niqiu.SBT;
//
//import top.niqiu.Sesson.MessageSession;
//import top.niqiu.core.Bot;
//import top.niqiu.core.BotEventHandler;
//
//import java.io.IOException;
//
//public class ReceiverBotEventHandler extends BotEventHandler {
//    public Bot sender;
//    public ReceiverBotEventHandler(int port) throws IOException {
//        super(port);
//    }
//
//    @Override
//    public void onMessage(MessageSession session) throws IOException {
//        if (session.group_id == 930011920L/* && session.sender.user_id == 2824375514L*/) {
//            try {
//                String s = this.sender.sendGroupMessage(747968752L, session.sender.nickname + ": " + session.message);
//                System.out.println(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void setSender(Bot sender) {
//        this.sender = sender;
//    }
//}

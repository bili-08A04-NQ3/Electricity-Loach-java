package top.niqiu.Plugins.Random;

import top.niqiu.Main;
import top.niqiu.Plugins.Plugin;
import top.niqiu.core.Sesson.MessageSession;

import java.io.IOException;

public class RandomInt extends Plugin {
    @Override
    public void onMessage(MessageSession session) throws IOException {
        super.onMessage(session);
        if (session.message.split(" ")[0].equals(".r")) {
            session.send(String.valueOf(Main.random.nextInt(0, 100)));
        }
    }
}

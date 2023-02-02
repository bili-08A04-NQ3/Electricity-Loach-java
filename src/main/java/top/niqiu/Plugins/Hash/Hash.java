package top.niqiu.Plugins.Hash;

import top.niqiu.Plugins.Plugin;
import top.niqiu.core.Sesson.MessageSession;

import java.io.IOException;

public class Hash extends Plugin {
    @Override
    public void onMessage(MessageSession session) throws IOException {
        super.onMessage(session);
        String[] rd = session.message.split(" ");
        if (rd[0].equals("hash")) {
            if (rd.length == 1) {
                session.send("0");
            } else {
                session.send(String.valueOf(rd[1].hashCode()));
            }
        }
    }
}

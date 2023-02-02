package top.niqiu.Plugins;

import top.niqiu.core.Sesson.MessageSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plugin {
    public static List<Plugin> plugins = new ArrayList<>();
    public String registryName = this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1];

    public Plugin() {
        plugins.add(this);
    }

    public void onMessage(MessageSession session) throws IOException {
    }

    public void loadBasicSettings() throws IOException {
    }

    public static void loadAllBasicSettings() throws IOException {
        for (Plugin plugin: plugins) {
            plugin.loadBasicSettings();
        }
    }

    public String getResourcesPath() {
        return "plugins/" + this.registryName + ".json";
    }
}

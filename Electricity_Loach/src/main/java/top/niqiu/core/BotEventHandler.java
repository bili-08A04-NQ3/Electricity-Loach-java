package top.niqiu.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import top.niqiu.Plugins.Plugin;
import top.niqiu.core.Sesson.MessageSession;
import top.niqiu.core.Sesson.Session;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class BotEventHandler implements HttpHandler {
    public Bot bot;

    public BotEventHandler(int port) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", this);
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
    }

    public BotEventHandler setBot(Bot bot) {
        this.bot = bot;
        return this;
    }

    public Bot getBot() {
        return bot;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String s = this.getRequestParam(exchange);
            Session session = Session.getSession(s);
            if (session instanceof MessageSession message) {
                message.setBot(this.getBot());
                if (((MessageSession) session).group_id == 732116973L) {
                    this.onMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // gocq要求返回一个东西
        String responseContentStr = "response";
        byte[] responseContentByte = responseContentStr.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type:", "text/text;charset=utf-8");
        exchange.sendResponseHeaders(200, responseContentByte.length);
        OutputStream out = exchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }

    private String getRequestParam(HttpExchange httpExchange) throws IOException {
        String paramStr;
        if (httpExchange.getRequestMethod().equals("GET")) {
            paramStr = httpExchange.getRequestURI().getQuery();
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8));
            StringBuilder requestBodyContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                requestBodyContent.append(line);
            }
            paramStr = requestBodyContent.toString();
        }

        return paramStr;
    }

    public void onMessage(MessageSession session) throws IOException {
        for(Plugin plugin: Plugin.plugins) {
            plugin.onMessage(session);
        }
    }
}

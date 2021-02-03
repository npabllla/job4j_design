package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket server = new ServerSocket(9000);
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String message = in.readLine();
                    if (message.contains("Exit")) {
                        System.out.println(message);
                        server.close();
                    } else if (message.contains("Hello")) {
                        System.out.println(message);
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("Hello".getBytes());
                    } else {
                        System.out.println(message);
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("What.".getBytes());
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Something went wrong", e);
        }
    }
}
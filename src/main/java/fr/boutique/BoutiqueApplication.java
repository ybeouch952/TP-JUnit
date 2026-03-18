package fr.boutique;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public final class BoutiqueApplication {

    private BoutiqueApplication() {
    }

    public static void main(String[] args) {
        int port = resolvePort();
        System.out.println("Boutique service started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket client = serverSocket.accept();
                     OutputStream out = client.getOutputStream()) {
                    byte[] body = "Boutique API is running\n".getBytes(StandardCharsets.UTF_8);
                    String headers =
                            "HTTP/1.1 200 OK\r\n"
                                    + "Content-Type: text/plain; charset=UTF-8\r\n"
                                    + "Content-Length: " + body.length + "\r\n"
                                    + "Connection: close\r\n"
                                    + "\r\n";

                    out.write(headers.getBytes(StandardCharsets.US_ASCII));
                    out.write(body);
                    out.flush();
                } catch (IOException e) {
                    System.err.println("Request error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Startup error on port " + port + ": " + e.getMessage());
            System.exit(1);
        }
    }

    private static int resolvePort() {
        String rawPort = System.getenv("PORT");
        if (rawPort == null || rawPort.isBlank()) {
            return 8080;
        }

        try {
            return Integer.parseInt(rawPort);
        } catch (NumberFormatException e) {
            return 8080;
        }
    }
}
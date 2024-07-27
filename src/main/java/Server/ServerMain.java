package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {
    private static final int PORT = 12345;
    private ArrayList<Squad> squads;
    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandler();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String response;
                while ((response = in.readLine()) != null) {
                    if (response.equals("END")) {
                        break;
                    }
                    requestHandler.handel(response, out);
                    System.out.println("The response is " + response);
                }
                System.out.println("Finish Process!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

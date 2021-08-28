package Server;

import Game.GameSocket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server Listening at port " + port);
        SQL.sql.getalldata();   // print all record
        while(true){
            Socket client = ss.accept();
            GameSocket s = new GameSocket(client);
            System.out.println("Receive Client Connection" + client.getRemoteSocketAddress() + ":" + client.getPort());
            Thread game = new game(s);
            game.start();
        }

    }
}

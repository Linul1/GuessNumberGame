package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import Game.*;

public class client {
    public static void main(String[] args) throws IOException {
        GameSocket client = new GameSocket(new Socket("localhost",1234));
        System.out.println("Connect to server successfully. Game Start!");
        System.out.println("Please enter your playername");
        Scanner sc = new Scanner(System.in);
        String playername = sc.next();
        while(true){ // clientside handle playername too long
            if (playername.length() > 12){
                System.out.println("Your playername is too long, please retype");
                playername = sc.next();
                continue;
            }
            else{
                break;
            }
        }
        client.Send(playername);
        System.out.println("Server give a random int number from 0 to 100, guess the number and type in");
        while(true){
            int answer = sc.nextInt();
            client.Send(String.valueOf(answer));
            System.out.println(client.Receive());
        }
    }

}

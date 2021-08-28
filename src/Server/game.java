package Server;

import Game.GameSocket;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

public class game extends Thread {
    GameSocket s;
    public game(GameSocket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            String playername = s.Receive();
            while(true){ // serverside handle playername too long error again
                if (playername.length() > 12){
                    System.out.println("Playername too long, retry receive");
                    playername = s.Receive();
                    continue;
                }
                else{
                    break;
                }
            }
            System.out.println("Playername is " + playername);
            Random r = new Random();
            int correctanswer = r.nextInt(101);
            System.out.println("The correct answer is" + correctanswer);
            int time = 1;
            String answer = s.Receive();
//            System.out.println("Receive data: " + answer);
            while(Integer.valueOf(answer) != correctanswer){
                time++;
                if (Integer.valueOf(answer) > correctanswer){
                    s.Send("Your number is too large");
                }
                else if (Integer.valueOf(answer) < correctanswer){
                    s.Send("Your number is too small");
                }
                answer = s.Receive();
            }
            s.Send("You win! You use " + time + " time " + "to guess the right number");
            SQL.sql.addData(playername, new Date(System.currentTimeMillis()),time); // insert date to datebase
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

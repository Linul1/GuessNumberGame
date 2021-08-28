package Game;

import java.io.*;
import java.net.Socket;

public class GameSocket {
    Socket socket;

    public GameSocket(Socket socket) {
        this.socket = socket;
    }

    // Send Method
    public void Send(String s) throws IOException {
        OutputStream outToServer = this.socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(s);
    }

    // Receive Method
    public String Receive() throws IOException {
        InputStream inFromServer = this.socket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        return in.readUTF();
    }

    public void close() throws IOException {
        this.socket.close();
    }

}

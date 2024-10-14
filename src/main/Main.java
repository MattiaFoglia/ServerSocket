package it.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket ss;
            ss = new ServerSocket(4316);
            Socket mySocket = ss.accept();
            String stringRed = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());
            do {
                stringRed = in.readLine();
                System.out.println("String received : " + stringRed);
                stringRed = stringRed.toUpperCase();
                out.writeBytes(stringRed + '\n');
            } while(!stringRed.equals("!"));
            mySocket.close();
            ss.close();
        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}

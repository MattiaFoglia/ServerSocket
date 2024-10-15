import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            String stringRead = "";
            String stringRead2 = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                stringRead = in.readLine();
                System.out.println("String received : " + stringRead);

                if (stringRead.equals("!")) {
                    System.out.println("The client wants to close");
                    s.close();
                }
                
                stringRead2 = in.readLine();
                System.out.println("Switch Case received : " + stringRead2);

                switch (stringRead2) {
                    case "a":
                        stringRead = stringRead.toUpperCase();
                        break;
                    case "b":
                        stringRead = stringRead.toLowerCase();
                        break;
                    case "c":
                        stringRead = new StringBuilder(stringRead).reverse().toString();
                        break;
                    case "d":
                        stringRead = stringRead.length() + "";
                        break;

                    default:
                        stringRead = "Not Existing Case";
                        break;
                }

            out.writeBytes(stringRead + '\n');
            } while (true);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}

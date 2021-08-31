import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadSocket implements Runnable {
    private Socket socket;
    public ThreadSocket(Socket s) {
        socket =s;
    }
    public void run()
    {
        try {
            System.out.println("Подключение");
            ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());

            Transport[] transports = (Transport[]) in.readObject();
            out.writeDouble(StaticMethos.Average5(transports));
            out.flush();
            System.out.println("Значение передано клиенту");
            socket.close();
        } catch (Exception e) {
        }
    }
}

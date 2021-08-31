import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class CreationThread implements Runnable {
    private String filename;
    private Transport transport;
    private ArrayBlockingQueue abq;

    public CreationThread(String filename, ArrayBlockingQueue abq) throws IOException,ClassNotFoundException,InterruptedException
    {
        this.abq=abq;
        this.filename=filename;

    }
    @Override
    public void run()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String Mark = (String) br.readLine();
            Transport t = new Car(Mark, 0);
            abq.add(t);
        }
        catch (Exception e)
        {
            System.out.println("Ошибка");
        }
    }
}

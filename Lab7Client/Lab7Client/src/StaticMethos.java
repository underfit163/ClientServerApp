import java.io.*;
import java.util.Arrays;
import java.lang.reflect.*;
import java.util.Scanner;

public class StaticMethos {
public static void PrintAllNames(Transport model)
{
    System.out.println(("Массив названий моделей"+Arrays.toString(model.getNameArray())));
}
    public static void PrintAllPrices(Transport model){
    System.out.println("Массив цен моделей"+Arrays.toString(model.getPayArray()));
    }
    public static double AveragePrice(Transport model){
        double avg = 0;
        for (int i = 0; i <model.getSize() ; i++) {
            avg += model.getPayArray()[i];
        }
        return (avg/model.getSize());
    }
//*******************3laba**********************
    public static void outputTransport(Transport t, OutputStream out) throws IOException {
        DataOutputStream dostream = new DataOutputStream(out);
        byte[] data = t.getMark().getBytes();
        dostream.writeInt(data.length);
        for (int i = 0; i < data.length; i++) {
            dostream.writeByte(data[i]);
        }
        dostream.writeInt(t.getSize());
        for (int i = 0; i < t.getSize(); i++) {
            data = t.getNameArray()[i].getBytes();
            dostream.writeInt(data.length);
            for (int j = 0; j < data.length; j++) {
                dostream.writeByte(data[j]);
            }
            dostream.writeDouble(t.getPayArray()[i]);
        }
    }

    public static Transport inputTransport(InputStream in) throws IOException,DuplicateModelNameException {
        DataInputStream distream = new DataInputStream(in);
        int len = distream.readInt();
        byte [] data = new byte[len];
        for(int i=0; i< len; i++){
            data[i] = distream.readByte();
        }
        String mark = new String(data);
        int lenOfModel = distream.readInt();
        double price;
        String model;
        Transport t = new Car(mark, 0);
        for(int i = 0; i < lenOfModel; i++){
            len = distream.readInt();
            data = new byte[len];
            for(int j = 0; j < len; j++){
                data[j] = distream.readByte();
            }
            model = new String(data);
            price = distream.readDouble();
            t.addModel(model,price);
        }
        return t;
    }

    public static void writeTransport (Transport v, Writer out){
        PrintWriter stream = new PrintWriter(out);
        stream.println(v.getMark());
        stream.println(v.getSize());
        for(int i=0 ;i < v.getSize(); i++){
            stream.println(v.getNameArray()[i]);
            stream.println(v.getPayArray()[i]);
        }
        stream.flush();
    }

    public static Transport readTransport(Reader in)throws DuplicateModelNameException,IOException{
        BufferedReader stream = new BufferedReader(in);
        String mark = stream.readLine();
        double price;
        String model;
        int len = Integer.parseInt(stream.readLine());
        Transport t = new Car(mark, 0);
        for(int i = 0; i < len; i++){
            model = stream.readLine();
            price=Double.parseDouble(stream.readLine());
            t.addModel(model,price);
        }
        return t;
    }

    public static Transport createTransport(String name, int k, Transport trans)  {
        Class clss = trans.getClass();
        try {
            Constructor constructor = clss.getConstructor(new Class[]{String.class, Integer.TYPE});
            Transport transport = (Transport) constructor.newInstance(new Object[]{name, k});
            return transport;
        }catch (Exception e)
        {
            return null;
        }
    }

    public static double Average5(Transport... models) {
        double avg = 0;
        for (int i = 0; i < models.length; i++) {
            avg += AveragePrice(models[i]);
        }
        return (avg/models.length);
    }

    public static void writeModels(Transport t) {
        String[] models = t.getNameArray();
        double[] prices = t.getPayArray();
        System.out.println("Марка транспортного средства:");
        System.out.println(t.getMark());
        System.out.println("Количество:");
        System.out.println(t.getSize());
        for (int i = 0; i < t.getSize(); i++) {
            System.out.println("Модель:");
            System.out.println(models[i]);
            System.out.println("Цена:");
            System.out.println(prices[i]);
        }
    }

    public static Transport inputTransport() throws DuplicateModelNameException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Марка: ");
        String mark = scanner.next();
        System.out.print("Количество: ");
        int count = scanner.nextInt();
        Transport t = new Car(mark, 0);
        for (int i = 0; i < count; i++) {
            System.out.print("Название модели: ");
            String s = scanner.next();
            System.out.print("Цена: ");
            double d = scanner.nextDouble();
            t.addModel(s, d);
        }
        return t;
    }
    public static void printfTransport (Transport transport)
    {
        System.out.printf("Транспортное средство: %s%n Количество моделей: %s%n ",transport.getMark(), transport.getSize());
        for (int i=0; i< transport.getSize(); i++)
        {
            System.out.printf("Название модели: %s; Цена: %s%n", transport.getNameArray()[i], transport.getPayArray()[i]);
        }
    }
}

import java.io.*;
import java.lang.reflect.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.net.*;
public class Main {
    public static void main(String [] args) throws Exception, DuplicateModelNameException, IOException, CloneNotSupportedException, NoSuchModelNameException, ClassNotFoundException, NoSuchMethodException {
        Transport car= new Car("Lada",3);
        car.addModel("priora",30);
        Transport car2= new Car("bmw",1);
        car.addModel("X5",50);
        car.addModel("X7",2000);
        Transport bike= new Bike("yam",3);
        car.addModel("q3",500);

        Transport [] arr=new Transport[]{car, car2, bike};

        int serverPort = 1024;
        String address = "localhost";

        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {

            socket = new Socket(address, serverPort);
            System.out.println("Соединение с сервером установлено");
            System.out.println(InetAddress.getByName(address));
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(arr);
            out.flush();

            System.out.println("Получен ответ от сервера");
            System.out.println("Средняя стоимость: " + in.readDouble());

        } catch (IOException e) {
            System.err.println(e);
        }
        finally {
            out.close();
            in.close();
            socket.close();
        }


       //laba6
       /* Transport lada = new Car("Лада", 100);
        lada.addModel("Калина", 150);
        lada.addModel("Веста", 421);
        lada.addModel("Приора", 50);*/
        //1
      /*  System.out.println("1 задание:");
        ModelsThread mt = new ModelsThread(lada);
        PricesThread pt = new PricesThread(lada);
        mt.setPriority(Thread.MAX_PRIORITY);
        pt.setPriority(Thread.MAX_PRIORITY);
        pt.start();
        mt.start();*/
        //2
       /* System.out.println("2 задание:");
        TransportSynchronizer ts = new TransportSynchronizer(lada);
        Thread trp = new Thread(new RunPricesThread(ts));
        Thread trm = new Thread(new RunModelsThread(ts));
        trp.start();
        trm.start();*/
        //3
        /*System.out.println("3 задание:");
        ReentrantLock lock = new ReentrantLock();
        Thread tlm = new Thread(new LockModelsThread(lada,lock));
        Thread tlp = new Thread(new LockPricesThread(lada,lock));
        tlm.start();
        tlp.start();*/
        //4
        /*System.out.println("4 задание:");
        Transport t1 = new Car("T1",1);
        Transport t2 = new Car("T2",2);
        Transport t3 = new Car("T3",1);
        Transport t4 = new Car("T4",1);
        ExecutorService es = Executors.newFixedThreadPool(2);
        MarkTread mt1 = new MarkTread(t1);
        MarkTread mt2 = new MarkTread(t2);
        MarkTread mt3 = new MarkTread(t3);
        MarkTread mt4 = new MarkTread(t4);
        es.submit(mt1);
        es.submit(mt2);
        es.submit(mt3);
        es.submit(mt4);
        es.shutdown();*/
        //5
        /*System.out.println("5 задание:");
        ArrayBlockingQueue<Transport> arr = new ArrayBlockingQueue<Transport>(4);
        String [] names = {"first.txt","second.txt","third.txt","fourth.txt","fifth.txt"};
        for(int i=0;i<5;i++)
        {
            Thread th = new Thread(new CreationThread(names[i],arr));
            th.start();
        }
        Transport [] tr = new Transport[5];
        for(int i=0;i<5;i++)
        {
            tr[i]=arr.take();
            System.out.println(tr[i].toString());
        }
        /* //Laba5
        //1):
        if (args.length == 3) {
            try {
                System.out.println("Задание 1: ");
        Car lada = new Car("Лада", 3);
        lada.addModel("Калина", 100);
        lada.addModel("Веста", 421);
        lada.addModel("Приора", 50);
        Class clss = Class.forName(args[0]);
        Method setpaynew = clss.getMethod(args[1], new Class[]{String.class, Double.TYPE});
        Double val = Double.valueOf(args[2]);
        setpaynew.invoke(lada, new Object[]{"Калина", val});
        Method getmark = clss.getMethod("getMark",new Class[]{});
        Method getN = clss.getMethod("getNameArray",new Class[]{});
        Method getP=clss.getMethod("getPayArray",new Class[]{});
        System.out.println(getmark.invoke(lada));
        String[] name=(String[]) getN.invoke(lada);
        System.out.println(Arrays.toString(name));
        double[] pay =(double[]) getP.invoke(lada);
        System.out.println(Arrays.toString(pay));
            } catch (ClassNotFoundException e) {
                System.out.println("Класс с таким именем не найден");
                System.out.println(e);
            } catch (NoSuchMethodException e) {
                System.out.println("Метод с таким именем не найден");
                System.out.println(e);
            } catch (IllegalAccessException e) {
                System.out.println("Метод недоступен");
                System.out.println(e);
            } catch (InvocationTargetException e) {
                System.out.println("При вызове метода возникло исключение");
                System.out.println(e);
            }
        }
        else {
            System.out.println("Необходимо ввести 3 параметра");
        }

        //2):
        System.out.println("Задание 2: ");
        Car bmw = new Car("",0);
        Transport transport = StaticMethos.createTransport("BMW",3, bmw);
        System.out.println(transport.toString());

        System.out.println("Задание 3: ");
        Transport scooter = new Scooter("Скутес", 3);
        scooter.setName("Scooter0", "Россия");
        scooter.setPayNew("Scooter1", 30);
        scooter.addModel("Scooter5", 120);
        scooter.dellModel("Scooter2");
        StaticMethos.PrintAllPrices(scooter);
        StaticMethos.PrintAllNames(scooter);

        System.out.println("Задание 4: ");
        Transport qvadric = new Qvadric("Вездеход", 4);
        qvadric.setName("квадроцикл0", "Победа");
        qvadric.setPayNew("квадроцикл1", 44);
        qvadric.addModel("ВВВ", 34);
        qvadric.dellModel("квадроцикл2");
        StaticMethos.PrintAllNames(qvadric);
        StaticMethos.PrintAllPrices(qvadric);

        System.out.println("Задание 5: ");
        Transport moped = new Moped("Alfa", 3);
        moped.setName("мопед0", "Beta");
        moped.setPayNew("мопед1", 20);
        moped.addModel("Gamma", 40);
        moped.dellModel("мопед1");
        StaticMethos.PrintAllNames(moped);
        StaticMethos.PrintAllPrices(moped);

        System.out.println("Задание 6: ");
        System.out.println(StaticMethos.Average5(scooter,qvadric,moped));

        System.out.println("Задание 7: ");
        Transport t = StaticMethos.inputTransport();
        StaticMethos.printfTransport(t);*/


          /*  Bike toyota = new Bike("Toyota", 4);
            toyota.addModel("Supra", 2);
            toyota.addModel("Corola", 1.3);
            toyota.addModel("Prado", 2.5);
            toyota.addModel("Camry", 1.6);
            //toyota.addModel("Camry",1.6);
            toyota.setPayNew("Supra", 4);
//toyota.setPayNew("No name", 4);
            toyota.setName("Prado", "Camryy");
//toyota.setName("Supra","Prado");
            toyota.dellModel("Camry");
//toyota.dellModel("No name");
            System.out.println("Размерность массива");
            System.out.println(toyota.getSize());
            System.out.println("Массив названий Тойоты");
            System.out.println(Arrays.toString(toyota.getNameArray()));
            System.out.println("Массив цен Тойоты");
            System.out.println(Arrays.toString(toyota.getPayArray()));
            System.out.println("Конкретная цена");
            System.out.println(toyota.getPriceModel("Corola")); */

      /*  Bike bmw = new Bike("БМВ",4);

        bmw.addModel("Гоночный", 3);
        bmw.addModel("Трассовый", 5);
        bmw.addModel("Велосипед", 10);
        bmw.addModel("Внедорожный", 20);
        //bmw.addModel("Внедорожный", 20);

        bmw.setPayNew("Гоночный",1000);
        //bmw.setPayNew("НетуИмени",1000000);
        bmw.setName("Внедорожный", "ОдноКолесный");
        //bmw.setName("Внедорожный", "Трассовый");
        bmw.dellModel("Велосипед");
        //bmw.dellModel("НетуТакойМодели");

        System.out.println("Размерность массива");
        System.out.println(bmw.getSize());
        System.out.println("Массив названий БМВ");
        System.out.println(Arrays.toString(bmw.getNameArray()));
        System.out.println("Массив цен БМВ");
        System.out.println(Arrays.toString(bmw.getPayArray()));
        System.out.println("Конкретная цена");
        System.out.println(bmw.getPriceModel("Трассовый"));*/
/*
        Car lada = new Car("Лада",3);
        lada.addModel("Калина",100);
        lada.addModel("Веста",421);
        lada.addModel("Приора",50);
        System.out.println(lada.getSize());
        System.out.println(Arrays.toString(lada.getNameArray()));
        System.out.println(Arrays.toString(lada.getPayArray()));

        //**********lab4*************
        Car ladaCopy = (Car) lada.clone();

        //toString()
        System.out.println("*********toString: ");
        System.out.println(lada.toString());

        //equals();
        Car ladaCopy = new Car("Лада",3);
        ladaCopy.addModel("Калина",100);
        ladaCopy.addModel("Веста",421);
        ladaCopy.addModel("Приора",50);
        System.out.println(lada.equals(ladaCopy));

        //hashcode()
        System.out.println("*********Хэш-коды: ");
        System.out.println(lada.hashCode());
        System.out.println(ladaCopy.hashCode());

        //clone()
        System.out.println("*********clone: ");
        System.out.println("-----------До изменения названия-------: ");
        System.out.println(lada);
        System.out.println(ladaCopy);
        lada.setName("Калина","Гранта");
        System.out.println("-----------После изменения названия-------: ");
        System.out.println(lada);
        System.out.println(ladaCopy);*/
            //************Lab3****************
        /*try {
        System.out.println("Введите имя файла: ");
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();
        FileOutputStream fout = new FileOutputStream(filename);
        StaticMethos.outputTransport(Lada, fout);
        fout.close();

        FileInputStream fin = new FileInputStream(filename);
        Transport tCopy = StaticMethos.inputTransport(fin);
        fin.close();

        System.out.println("Оригинал: ");
        System.out.println("Модели: ");
        StaticMethos.PrintAllNames(Lada);
        System.out.println("Цены: ");
        StaticMethos.PrintAllPrices(Lada);
        System.out.println("Копия: ");
        System.out.println("Модели: ");
        StaticMethos.PrintAllNames(tCopy);
        System.out.println("Цены: ");
        StaticMethos.PrintAllPrices(tCopy);
        System.out.println("------------");

        FileWriter write = new FileWriter("write");
        StaticMethos.writeTransport(Lada, write);

        FileReader read = new FileReader("write");
        Transport t2Copy = StaticMethos.readTransport(read);

        System.out.println("Оригинал: ");
        System.out.println("Модели: ");
        StaticMethos.PrintAllNames(Lada);
        System.out.println("Цены: ");
        StaticMethos.PrintAllPrices(Lada);
        System.out.println("Копия: ");
        System.out.println("Модели: ");
        StaticMethos.PrintAllNames(t2Copy);
        System.out.println("Цены: ");
        StaticMethos.PrintAllPrices(t2Copy);
        System.out.println("-----------------");

        //запись в символьный поток
        System.out.println("Введите марку, кол-во моделей, название модели, стоимость: ");
        Transport s2 = StaticMethos.readTransport(new InputStreamReader(System.in));
        StaticMethos.writeTransport(s2, new OutputStreamWriter(System.out));

        System.out.println("Введите имя файла записи: ");
        filename = scan.nextLine();
        ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(filename));
        out2.writeObject(s2);
        out2.close();

        //чтение из символьного потока
        System.out.println("Название файла загрузки: ");
        filename = scan.nextLine();
        FileInputStream fin2 = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fin2);
        Transport s3 = (Transport) ois.readObject();
        fin2.close();

        StaticMethos.PrintAllNames(s3);
        StaticMethos.PrintAllPrices(s3);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (DuplicateModelNameException | ModelPriceOutOfBoundsException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

       /* Transport Hama = new Bike("Хама",3);
        Hama.addModel("Супер", 540);
        Hama.addModel("Корсар", 123);
        Hama.addModel("Пушка", 421);
        System.out.println(Hama.getSize());
        System.out.println(Arrays.toString(Hama.getNameArray()));
        System.out.println(Arrays.toString(Hama.getPayArray()));

        StaticMethos.PrintAllPrices(toyota);
        StaticMethos.PrintAllPrices(bmw);
        StaticMethos.PrintAllNames(toyota);
        StaticMethos.PrintAllNames(bmw);
        System.out.println(StaticMethos.AveragePrice(toyota));
        System.out.println(StaticMethos.AveragePrice(bmw));*/
        }
    }


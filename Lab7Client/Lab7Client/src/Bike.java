import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.Serializable;
import java.util.Arrays;

public class Bike implements Transport,Serializable,Cloneable{
    private String mark;//поле типа String, хранящее марку мотоцикла

    @Override
    public String getMark() //метод для получения марки автомобиля
    {
        return mark;
    }

    @Override
    public void setMark(String m) //метод для модификации марки автомобиля
    {
        this.mark = m;
    }

    public Bike(String mark, int n) throws DuplicateModelNameException //Конструктор класса должен принимать
    // в качестве параметров значение Марки автомобиля и размер массива Моделей
    {
        this.mark = mark;
        Model Q = new Model();
        for (int i = 0; i < n; i++) {
            addModel("модель" + i,0);
        }
    }

    private class Model implements Serializable,Cloneable {
        private String name = null;
        private double pay = Double.NaN;
        Model prev = null;
        Model next = null;
        public Model(){}
        public Model(String name, double pay) //конструктор
        {
            this.name = name;
            this.pay = pay;
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }


    private int size = 0;

    // далее код по заданию
//Метод для модификации названия модели
    @Override
    public void setName(String name, String newName)throws NoSuchModelNameException, DuplicateModelNameException {
        Model p = head.next;
        int id = -1;
        for (int i = 0; i < size; i++) {
            if (p.name.equals(name)) {
                id = i;
                break;
            }
            p = p.next;
        }
        testDuplicate(newName);
        if (id == -1) throw new NoSuchModelNameException(name);
        else p.name = newName;
    }

    //метод возвращающий массив названий моделей
    @Override
    public String[] getNameArray() {
        String[] namearr = new String[size];
        Model p = head.next;
        int i = 0;
        if (p == null)
            return null;
        do {
            namearr[i++] = p.name;
            p = p.next;
        } while (p != head);
        return namearr;
    }
    // метод для получения значения цены модели по ее названию
    @Override
    public double getPriceModel(String name) throws NoSuchModelNameException
    {
        double s = 0;
        Model p=head.next;
        if (p == null)
            return 0;
        do {
            if(p.name.equals(name))
            { 
              return p.pay;
            }
            p=p.next;
        }while (p!=head);
        throw new NoSuchModelNameException(name);
    }
    
    //метод для модификации значения цены модели по ее названию
    @Override
    public void setPayNew(String name, double pay) throws NoSuchModelNameException
    {
        if(pay < 0) throw new ModelPriceOutOfBoundsException(pay);
        Model p = head.next;
        int id = -1;
        for (int i = 0; i < size; i++) {
            if (p.name.equals(name)) {
                id = i;
                break;
            }
            p = p.next;
        }
        if (id == -1) throw new NoSuchModelNameException(name);
        else p.pay = pay;
    }

    //метод возвращающий массив значений цен моделей
    @Override
    public double[] getPayArray() {
        double[] payarr = new double[size];
        Model p = head.next;
        int i = 0;
        if (p == null)
            return null;
        do {
            payarr[i++] = p.pay;
            p = p.next;
        } while (p != head);
        return payarr;
    }


    public void testDuplicate(String nameNew) throws DuplicateModelNameException
    {
        Model test=head.next;
        do {
            if(test.name.equals(nameNew)){
                throw new DuplicateModelNameException(nameNew);
            }
            test=test.next;
        }while (test!=head);

    }

    //метод добавления названия модели и ее цены
    @Override
    public void addModel(String name, double pay)throws DuplicateModelNameException
    {
        if(pay<0) throw new ModelPriceOutOfBoundsException(pay);
        Model model = new Model(name,pay);
        if (head.next==head)
        {
            model.next=head;
            model.prev=head;
            head.next=model;
            head.prev=model;
        }
        else {
            testDuplicate(name);
            model.next=head;
            model.prev=head.prev;
            head.prev.next=model;
            head.prev=model;
        }
        size++;
    }

    //метод для удаления модели с заданным именем и ее цены
    @Override
    public void dellModel(String name) throws NoSuchModelNameException{
    {
        Model p=head;
        do{
            p=p.next;
        }while ((p.next!=head)&&(!p.name.equals(name)));
        if(!p.name.equals(name)) throw new NoSuchModelNameException(name);
        p.prev.next=p.next;
        p.next.prev=p.prev;
        size--;
    }
    }

    //метод для получения размера списка Моделей
    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public  String toString()
    {
        Model p = head.next;
        StringBuffer sb = new StringBuffer();
        sb.append("Марка машины: ");
        sb.append(mark);
        sb.append("\n");
        sb.append("Количество машин: ");
        sb.append(getSize());
        sb.append("\n");
        sb.append("Модели машины: ");
        sb.append("\n");
        while (p!=head)
        {
            sb.append(" ");
            sb.append(p.name);
            sb.append(" ее цена: ");
            sb.append(p.pay);
            sb.append("\n");
        p=p.next;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj==null) return false;
        if (obj instanceof Transport) {
            Transport tr = (Transport) obj;
            if (!tr.getMark().equals(getMark())) {
                return false;
            } else if (tr.getSize() != getSize()) {
                return false;
            }
            for (int i = 0; i < getSize(); i++) {
                if ((!tr.getNameArray()[i].equals(getNameArray()[i]))) {
                    return false;
                } else if (tr.getPayArray()[i] != getPayArray()[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = mark != null ? mark.hashCode() : 0;
        result = 31 * result + (getNameArray() != null ? Arrays.hashCode(getNameArray()) : 0);
        result = 31 * result + (getPayArray() != null ? Arrays.hashCode(getPayArray()) : 0);
        return result;
    }

    public Object clone() throws CloneNotSupportedException{
        Bike bike =(Bike)super.clone();
        bike.head = new Model();
        bike.head.prev = bike.head;
        bike.head.next = bike.head;
        Model p = this.head.next;
        while (p!=head){
            Model pclone = (Model) p.clone();
            bike.head.prev.next=pclone;
            pclone.next=bike.head;
            pclone.prev=bike.head.prev;
            bike.head.prev=pclone;
            p=p.next;
        }
        return (Bike) bike;
    }
}




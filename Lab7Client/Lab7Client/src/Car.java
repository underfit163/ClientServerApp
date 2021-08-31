import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.lang.Cloneable;
import java.lang.Object;

public class Car implements Transport,Serializable,Cloneable {
    private String mark; //поле типа String, хранящее марку автомобиля

    private  Model[] models;
    public Car(String mark,int n) //Конструктор класса должен принимать
    // в качестве параметров значение Марки автомобиля и размер массива Моделей
    {
        this.mark=mark;
        models=new Model[n];
        for(int i=0; i< n; i++)
        {
            models[i]=new Model("model"+i, 100);
        }
    }

    public String getMark() //метод для получения марки автомобиля
    {
        return mark;
    }

    public void setMark(String m) //метод для модификации марки автомобиля
    {
        this.mark = m;
    }

    private class Model implements Serializable, Cloneable//внутренний класс Модель
    {
        private String name; //имеющий поля название модели и её цену
        private double pay;


        public Model(String name, double pay) //конструктор
        {
            this.name = name;
            this.pay = pay;
        }
        public void setName(String n) //метод для модификации значения названия модели
        {
            this.name= n;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        public String getName()
        {
            return name;
        }
        public double getPay()
        {
            return pay;
        }
        public void setPay(double p)
        {
            this.pay=p;
        }
    }

    public String[] getNameArray() //метод, возвращающий массив названий всех моделей
    {
        String[] a=new String[models.length];
        for(int i=0; i<a.length; i++)
        {
            a[i]=models[i].getName();
        }
        return a;
    }
    public double getPriceModel(String name)throws NoSuchModelNameException ////метод для получения значения цены модели по её названию
    {
        for(int i=0; i<models.length; i++)
        {
            if (models[i].getName().equals(name))
            {
                return models[i].getPay();
            }
        }
        throw new NoSuchModelNameException(name);
    }
    public void setPayNew(String name, double pay)throws NoSuchModelNameException //метод для модификации значения цены модели по её названию//исправить исключение
    {
        if (pay<0) throw new ModelPriceOutOfBoundsException(pay);
        double y=0;
        for(int i=0; i<models.length; i++)
        {
            if (models[i].getName().equals(name))
            {
                models[i].setPay(pay);
                return;
            }
        }
        throw new NoSuchModelNameException(name);
    }
    public void setName(String name, String newname)throws DuplicateModelNameException, NoSuchModelNameException //метод для модификации значения названия модели по её названию
    {
        int index=-1;
        for(int i=0; i<models.length; i++)
        {
            if (models[i].name.equals(newname)){throw new  DuplicateModelNameException(newname);}
            else if (models[i].name.equals(name))
            {
                index = i;
            }
        }
        if (index == -1) throw new NoSuchModelNameException(name);
        else models[index].name=newname;
    }

    public double[] getPayArray() //метод, возвращающий массив значений цен моделей
    {
        double[] a=new double[models.length];
        for(int i=0; i<a.length; i++)
        {
            a[i]=models[i].getPay();
        }
        return a;

    }
    @Override
    public void addModel(String name, double pay) throws DuplicateModelNameException   //метод добавления названия модели и её цены
        // (путем создания нового массива Моделей), использовать метод Arrays.copyOf()
        { if (pay <0) throw new ModelPriceOutOfBoundsException(pay);
            int modelleng = models.length + 1;
            for(int i=0; i<models.length; i++)
            {if(models[i].name.equals(name)) throw new DuplicateModelNameException(name);}
            models = Arrays.copyOf(models, modelleng);
            models[modelleng - 1] = new Model(name, pay);
        }

    public void dellModel(String name)throws NoSuchModelNameException //метод удаления модели с заданным именем и её цены,
    // использовать методы System.arraycopy, Arrays.copyOf(),
    {int id=-1;
        for (int i = 0; i < models.length; i++) {
            if (name.equals(models[i].getName())) {
                id=i;
                break;
            }
        }
        if (id == -1) throw new NoSuchModelNameException(name);
        System.arraycopy(models,id+1, models, id, models.length-id-1);
        models=Arrays.copyOf(models,models.length-1);

    }
    //метод для получения размера массива Моделей
    public int getSize()
    {
        return models.length;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Марка машины: ");
        sb.append(mark);
        sb.append("\n");
        sb.append("Количество машин: ");
        sb.append(getSize());
        sb.append("\n");
        sb.append("Модели машины: ");
        sb.append("\n");
        for(int i=0; i <getSize(); i++)
        {
            sb.append(" ");
            sb.append(models[i].name);
            sb.append(" ее цена: ");
            sb.append(models[i].pay);
            sb.append("\n");
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
                } else if (tr.getPayArray()[i]!=(getPayArray()[i])) {
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car car = (Car) super.clone();
        car.models = new Model[getSize()];
        for (int i=0; i<getSize();i++)
        {
            car.models[i] =(Model) this.models[i].clone();
        }
            return (Car) car;
    }
}
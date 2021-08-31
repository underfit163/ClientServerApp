import java.util.LinkedList;

public class Moped implements Transport {
    private String mark;
    private LinkedList<Model> models;

    public Moped(String name, int n ) throws DuplicateModelNameException {
        this.mark = name;
        models = new LinkedList<Model>();
        for(int i=0; i<n;i++) {
            models.add(new Model("мопед" + i, 100));
        }
    }
    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public void setMark(String m) {
        mark=m;
    }

    class Model {
        private String name;
        private double price;

        public  Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    @Override
    public void setName(String name, String newname) throws NoSuchModelNameException, DuplicateModelNameException {
        if(isExist(name)) {
            if(!isExist(newname)){
                int i = getIndex(name);
                models.get(i).name = newname;
            } else throw new DuplicateModelNameException(newname);
        } else throw new NoSuchModelNameException(name);
    }

    @Override
    public String[] getNameArray() {
        String[] array = new String[models.size()];
        for(int i = 0; i < models.size(); i++) {
            array[i] = models.get(i).name;
        }
        return array;
    }

    @Override
    public double[] getPayArray() {
        double[] allModelPrices = new double[models.size()];
        for(int i = 0; i < models.size(); i++) {
            allModelPrices[i] = models.get(i).price;
        }
        return  allModelPrices;
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @Override
    public double getPriceModel(String name) throws NoSuchModelNameException {
        if(isExist(name)){
            int p = getIndex(name);
            return models.get(p).price;
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public void setPayNew(String name, double pay) throws NoSuchModelNameException {
        if(isExist(name)) {
            if (pay<0) throw new ModelPriceOutOfBoundsException(pay);
            int p = getIndex(name);
            models.get(p).price = pay;
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public void addModel(String Name, double Price) throws DuplicateModelNameException {
        if (Price<0) throw new ModelPriceOutOfBoundsException(Price);
        if(!isExist(Name)){
            Model newModel = new Model(Name,Price);
            models.add(newModel);
        }
        else throw new DuplicateModelNameException(Name);
    }

    @Override
    public void dellModel(String name) throws NoSuchModelNameException {
        if(isExist(name)) {
            int i = getIndex(name);
            models.remove(i);
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }

    private boolean isExist(String name) {
        for (int i = 0; i < models.size(); i++)
            if (models.get(i).name.equals(name)) return true;
        return false;
    }
    private int getIndex(String name) throws NoSuchModelNameException {
        for (int i = 0; i < models.size(); i++)
            if (models.get(i).name.equals(name)) return i;
        throw new NoSuchModelNameException(name);
    }
}

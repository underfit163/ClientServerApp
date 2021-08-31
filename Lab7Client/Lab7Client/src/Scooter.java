import java.util.HashMap;

public class Scooter implements Transport {
    private String mark;
    private HashMap<String, Double> models;

    public Scooter(String mark, int n){
        this.mark = mark;
        this.models = new HashMap<String,Double>();
        for(int i = 0; i < n; i++){
            models.put("Scooter" + i, 100.0);
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

    @Override
    public void setName(String name, String newname) throws NoSuchModelNameException, DuplicateModelNameException {
        if(models.containsKey(name)){
            if(!models.containsKey(newname)){
                double price = models.get(name);
                models.remove(name);
                models.put(newname,price);
            }else throw new DuplicateModelNameException(newname);
        }else throw new NoSuchModelNameException(name);
    }

    @Override
    public String[] getNameArray() {
        String[] array = new String[models.size()];
        int i = 0;
        for(String key: models.keySet()){
            array[i] = key;
            i++;
        }
        return array;
    }

    @Override
    public double[] getPayArray() {
        double[] allModelPrices = new double[models.size()];
        int i = 0;
        for(Double price: models.values()) {
            allModelPrices[i] = price;
            i++;
        }
        return allModelPrices;
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @Override
    public double getPriceModel(String name) throws NoSuchModelNameException {
        if(models.containsKey(name)) {
            return models.get(name);
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public void setPayNew(String name, double pay) throws NoSuchModelNameException {
        if (pay<0) throw new ModelPriceOutOfBoundsException(pay);
        else if(models.containsKey(name)) {
            models.remove(name);
            models.put(name,pay);
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price<0) throw new ModelPriceOutOfBoundsException(price);
        if(!models.containsKey(name)) {
            models.put(name,price);
            }else{
            throw new DuplicateModelNameException (name);
        }
    }

    @Override
    public void dellModel(String name) throws NoSuchModelNameException {
        if(models.containsKey(name)) {
            models.remove(name);
        }
        else {
            throw new NoSuchModelNameException(name);
        }
    }
}

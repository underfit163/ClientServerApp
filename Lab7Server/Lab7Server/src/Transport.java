import java.io.Serializable;

public interface Transport {
    String getMark();
    void setMark(String m);
    void setName(String name, String newname) throws NoSuchModelNameException, DuplicateModelNameException;
    String[] getNameArray();
    double[] getPayArray();
    int getSize();
    double getPriceModel(String name) throws NoSuchModelNameException;
    void setPayNew(String name, double pay) throws NoSuchModelNameException;
    void addModel(String Name, double Price) throws DuplicateModelNameException;
    void dellModel(String name) throws NoSuchModelNameException;
}

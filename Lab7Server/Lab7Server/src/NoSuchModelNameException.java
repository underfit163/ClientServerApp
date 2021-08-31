public class NoSuchModelNameException extends  Exception {
    private String atrrName;
    public NoSuchModelNameException(){ }
    public NoSuchModelNameException(String name) {
        super("Название модели " + name + " не найдено");
        atrrName=name;
    }
}

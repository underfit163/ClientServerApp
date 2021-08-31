public class DuplicateModelNameException extends Exception {
    private String atrrName;
    public DuplicateModelNameException(){ }
    public DuplicateModelNameException(String name){
        super("Такая модель " + name + " уже существует");
        this.atrrName = name;
    }
}



public class ModelPriceOutOfBoundsException extends Error {
    private double atrrName;
    public ModelPriceOutOfBoundsException(){}
    public ModelPriceOutOfBoundsException(double pay){
        super("неверно заданна цена" + pay);
        this.atrrName = pay;
    }
}

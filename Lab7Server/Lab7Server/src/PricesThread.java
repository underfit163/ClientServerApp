public class PricesThread extends Thread {
    private Transport transport;
    public PricesThread(Transport transport)
    {
        this.transport = transport;
    }
    @Override
    public void run()
    {
        double[] arr=transport.getPayArray();
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}

public class RunPricesThread implements Runnable {
    private TransportSynchronizer ts;
    public RunPricesThread(TransportSynchronizer ts)
    {
        this.ts = ts;
    }

    @Override
    public void run()
    {
        try{
            while(ts.canPrintPrice()){
                ts.printPrice();
            }
        }
        catch(InterruptedException e){

        }

    }
}

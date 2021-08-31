public class RunModelsThread implements Runnable {
    private TransportSynchronizer ts;
    public RunModelsThread(TransportSynchronizer ts)
    {
        this.ts = ts;
    }
    @Override
    public void run()
    {
        try{
            while(ts.canPrintModel()){
                ts.printModel();
            }
        }
        catch(InterruptedException e){

        }
    }
}

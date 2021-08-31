import java.util.concurrent.locks.ReentrantLock;

public class LockPricesThread implements Runnable {
    private Transport transport;
    private ReentrantLock lock;
    public LockPricesThread(Transport transport,ReentrantLock lock)
    {
        this.transport = transport;
        this.lock = lock;
    }
    @Override
    public void run()
    {
        lock.lock();
        try{
            double[] arr=transport.getPayArray();
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            }
        }
        finally{
            lock.unlock();
        }

    }
}

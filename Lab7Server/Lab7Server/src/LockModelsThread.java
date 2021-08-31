import java.util.concurrent.locks.ReentrantLock;
public class LockModelsThread implements Runnable{
    private Transport transport;
    private ReentrantLock lock;//
    public LockModelsThread(Transport transport,ReentrantLock lock)
    {
        this.transport = transport;
        this.lock = lock;
    }

    @Override
    public void run()
    {
        lock.lock();
        try{
            String[] arr=transport.getNameArray();
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            }
        }
        finally{
            lock.unlock();
        }
    }
}

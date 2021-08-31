public class TransportSynchronizer {
    private Transport v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Transport v)
    {
        this.v = v;
    }

    public double printPrice() throws InterruptedException
    {
        double val;
        synchronized(lock)
        {
            double [] p = v.getPayArray();
            if (!canPrintPrice()) throw new InterruptedException();

            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printModel() throws InterruptedException
    {
        synchronized(lock)
        {
            String [] s = v.getNameArray();
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice()
    {
        return current < v.getSize();
    }

    public boolean canPrintModel()
    {
        return (!set && current < v.getSize()) || (set && current < v.getSize() - 1);

    }
}

public class MarkTread implements Runnable{
    private Transport transport;
    public MarkTread(Transport transport)
    {
        this.transport=transport;
    }
    @Override
    public void run()
    {
        System.out.println(transport.getMark());
    }
}

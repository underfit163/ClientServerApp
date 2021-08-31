public class ModelsThread extends Thread {
    private Transport transport;
    public ModelsThread(Transport transport)
    {
        this.transport=transport;
    }
    @Override
    public void run ()
    {
        String[] arr =transport.getNameArray();
        for(int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i]);
        }
    }
}

package sample;

public class Update extends Thread
{
    public void run()
    {
        boolean loop = true;
        while(loop)
        {
            try
            {
                System.out.println("test");
                Thread.sleep(8000);
                System.out.println("test");
                //mainGUI.updateTemp();
                Thread.sleep(2000);
                System.out.println("test");
                //mainGUI.updateLight();
            }
            catch (Exception e)
            {
                loop = false;
            }
        }
    }

}

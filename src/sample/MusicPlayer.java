package sample;

import arduino.Arduino;

import java.util.ArrayList;

public class MusicPlayer extends Thread
{
    private MyArduino arduino;
    private ArrayList<String> nummers;
    public boolean done;
    public boolean previous;
    public boolean stop;
    private static Update updater;

    public MusicPlayer(MyArduino arduino, ArrayList<String> nummers)
    {
        this.arduino = arduino;
        this.nummers = nummers;
    }

    @Override
    public void run()
    {
        Arduino arduinoCon = MyArduino.getArduinoCon();
        try
        {
            for (int i = 0; i< nummers.size(); i++)
            {
                System.out.println(nummers.size());
                arduinoCon.serialWrite(nummers.get(i));
                previous = false;
                done = false;
                int test =0;
                while (!done)
                {
                    test++;
                    if(stop)
                    {
                        arduinoCon.serialWrite("stop");
                        done=true;
                        i=nummers.size();
                        updater.start();
                    }
                    System.out.println("muziektest"+test);
                    String serial = arduinoCon.serialRead().replaceAll("[^a-zA-Z]", "");
                    System.out.println("muziek"+test);
                    if (serial.equals("done"))
                    {
                        System.out.println("klaar");
                        done = true;
                        System.out.println(serial);
                    }
                    if (previous)
                    {
                        if(i==0) i=nummers.size()-1; else i = i-2;
                        done = true;
                    }

                }
            }
        }
        catch (Exception e)
        {
            arduinoCon.serialWrite("stop");
            e.printStackTrace();
        }
    }

    public static void setUpdater(Update updater)
    {
        MusicPlayer.updater=updater;
    }
}

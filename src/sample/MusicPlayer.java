package sample;

import arduino.Arduino;

import java.util.ArrayList;

public class MusicPlayer extends Thread
{
    private MyArduino arduino;
    private ArrayList<String> nummers;
    public boolean done;
    public boolean previous;

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
            for (int i = 1; i<= nummers.size(); i++)
            {
                arduinoCon.serialWrite(nummers.get(i));
                previous = false;
                done = false;
                while (!done)
                {
                    Thread.sleep(100);
                    String serial = arduinoCon.serialRead().replaceAll("[^a-zA-Z]", "");
                    if (serial.equals("done")) done = true;
                    if (previous)
                    {
                        i = i-2;
                        done = true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            arduinoCon.serialWrite("stop");
        }
    }
}

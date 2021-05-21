package sample;

import arduino.Arduino;

import java.util.ArrayList;

public class MyArduino
{

    private ArrayList<String> nummers = new ArrayList<String>();
    private static Arduino arduinoCon;
    private boolean done;
    private MusicPlayer musicPlayer;

    public void play()
    {
        musicPlayer = new MusicPlayer(this, nummers);
        //een nummer uit nummers array is bijvoorbeeld "1" of "3".
        musicPlayer.start();
    }
    public void stop()
    {
        musicPlayer.interrupt();
    }
    public void next()
    {
        musicPlayer.done=true;
    }
    public void previous()
    {
        musicPlayer.previous=true;
    }
    public void pause()
    {
        arduinoCon.serialWrite("pause");
    }
    public void resume()
    {
        arduinoCon.serialWrite("resume");
    }
    public void verwarmingAan()
    {
        arduinoCon.serialWrite("vaan");
    }
    public void verwarmingUit()
    {
        arduinoCon.serialWrite("vuit");
    }
    public String getLight() throws Exception
    {
        arduinoCon.serialWrite("light");
        return arduinoCon.serialRead();
    }
    public void lampAan()
    {
        arduinoCon.serialWrite("laan");
    }
    public void lampUit()
    {
        arduinoCon.serialWrite("luit");
    }

    public static void setArduinoCon(String arduinoPort)
    {
        arduinoCon = new Arduino(arduinoPort, 9600);
        arduinoCon.openConnection();
    }
    public void addNummer(String nummer)
    {
        nummers.add(nummer);
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public static Arduino getArduinoCon() {
        return arduinoCon;
    }
}

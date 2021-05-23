package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Update extends Thread
{

    public static Account account;
    public boolean test= true;

    public void run()
    {
        boolean loop = true;
        while(loop)
        {
            try
            {
                Thread.sleep(5000);
                try
                {
                    double laatsteTemp;
                    if(account.getRpi()==null)
                    {
                        laatsteTemp = 0.0;
                    }
                    else
                    {
                        laatsteTemp = account.getRpi().getLaatsteTemp();
                    }
                    if (laatsteTemp>0.0)
                    {
                        Database.logTemp(laatsteTemp);
                        if (laatsteTemp>Main.maxTemp)
                        {
                            hoofdschermController.temp = "Temperatuur: " + laatsteTemp + ", verwarming uit.";
                            account.getMyArduino().verwarmingUit();
                        }
                        else
                        {
                            hoofdschermController.temp = "Temperatuur: " + laatsteTemp + ", verwarming aan.";
                            account.getMyArduino().verwarmingAan();
                        }
                    }
                    else
                    {
                        hoofdschermController.temp = "Geen verbinding met RPI, verwarming uit.";
                        account.getMyArduino().verwarmingUit();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        loop=false;
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                }
                Thread.sleep(2000);
                try
                {
                    System.out.println("voorLamp");
                    String sLight = account.getMyArduino().getLight();
                    System.out.println("naLamp");
                    int iLight = Integer.parseInt(sLight.replaceAll("[^0-9]", ""));
                    Database.logLight(iLight);

                    if(iLight<Main.minLight)
                    {
                        account.getMyArduino().lampAan();
                        hoofdschermController.light ="lichtsterkte: "+iLight+ " , lamp aan.";
                    }
                    else
                    {
                        hoofdschermController.light ="lichtsterkte: "+iLight+ " , lamp uit." ;
                        account.getMyArduino().lampUit();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        loop=false;
                    }
                }
                catch (Exception e)
                {
                    hoofdschermController.light = "Geen verbinding met arduino, lamp uit.";
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                loop = false;
            }
        }
    }

}

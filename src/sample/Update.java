package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Update extends Thread
{

    public static Account account;

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

                        if (laatsteTemp>Main.maxTemp)
                        {
                            hoofdschermController.temp = "Temperatuur: " + Double.toString(laatsteTemp)+ ", verwarming uit.";
                            account.getMyArduino().verwarmingUit();
                        }
                        else
                        {
                            hoofdschermController.temp = "Temperatuur: " + Double.toString(laatsteTemp)+ ", verwarming aan.";
                            account.getMyArduino().verwarmingAan();
                        }
                    }
                    else
                    {
                        hoofdschermController.temp = "Geen verbinding met RPI, verwarming uit.";
                        System.out.println(hoofdschermController.temp);
                        account.getMyArduino().verwarmingUit();
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                }

                Thread.sleep(2000);

                try
                {
                    String sLight;
                    if(account.getMyArduino()==null)
                    {
                        sLight = "";
                    }
                    else
                    {
                        sLight = account.getMyArduino().getLight();
                    }

                    if (!sLight.equals(""))
                    {
                        int iLight = Integer.parseInt(sLight.replaceAll("[^0-9]", ""));
                        if(iLight<Main.minLight)
                        {
                            account.getMyArduino().lampAan();
                            hoofdschermController.light ="lichtsterkte: "+sLight+ " , lamp aan.";
                        }
                        else
                        {
                            hoofdschermController.light ="lichtsterkte: "+sLight+ " , lamp uit." ;
                            account.getMyArduino().lampUit();
                        }
                    }
                    else
                    {
                        hoofdschermController.light = "Geen verbinding met arduino, lamp uit.";
                        account.getMyArduino().lampUit();
                    }
                }
                catch (Exception e)
                {
                    hoofdschermController.light = "Geen verbinding met arduino, lamp uit.";
                    //e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                loop = false;
            }
        }
    }

}

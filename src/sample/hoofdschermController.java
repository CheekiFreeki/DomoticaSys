package sample;

public class hoofdschermController
{
    private Account account;

    public void updateTemp()
    {
        try
        {
            if (account.getRpi().getLaatsteTemp()>0.0)
            {
                /*
                tempLabel.setText("actuele temperatuur: " + Double.toString(account.getRpi().getLaatsteTemp()));
                if (account.getRpi().getLaatsteTemp()>Main.maxTemp)
                {
                    this.verwarmingLabel.setText("verwarming staat uit.");
                    account.getMyArduino().verwarmingUit();
                }
                else
                {
                    this.verwarmingLabel.setText("verwarming staat aan.");
                    account.getMyArduino().verwarmingAan();
                }

                 */
            }
            else
            {
            /*
                this.tempLabel.setText("Geen verbinding met RPI.");
                this.verwarmingLabel.setText("verwarming staat uit.");
                account.getMyArduino().verwarmingUit();

                 */
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateLight()
    {
        try
        {
            String sLight = account.getMyArduino().getLight();
            if (sLight.equals("")) return;
            /*
            lightLabel.setText("actuele lichtsterkte: "+sLight);
            int iLight = Integer.parseInt(sLight.replaceAll("[^0-9]", ""));
            if(iLight<Main.minLight)
            {
                account.getMyArduino().lampAan();
                lampLabel.setText("Lamp is aan.");
            }
            else
            {
                lampLabel.setText("Lamp is uit.");
                account.getMyArduino().lampUit();
            }

             */
        }
        catch (Exception e)
        {
            /*
            lightLabel.setText("Arduino niet aangesloten.");
            lampLabel.setText("Lamp is uit.");
            account.getMyArduino().lampUit();

            e.printStackTrace();

             */
        }
    }
}

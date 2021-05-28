package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RaspberryPi
{
    private String ip;

    public RaspberryPi(String ip)
    {
        this.ip=ip;
    }

    public double getLaatsteTemp() throws Exception
    {
        String jsonArray;
        BufferedReader in;

        try
        {
            URL url = new URL("http://" + ip + "/throwdata.py");
            URLConnection uc = url.openConnection();
            uc.setConnectTimeout(2000);
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            jsonArray = in.readLine();
        }
        catch (Exception exception)
        {
            jsonArray = "";
        }

        if (jsonArray.equals(""))
        {
            return 0.0;
        }
        else
        {
            JSONArray json = new JSONArray(jsonArray);
            JSONObject laatsteMeting = json.getJSONObject(json.length() - 1);

            return laatsteMeting.getDouble("waarde");
        }
    }
}

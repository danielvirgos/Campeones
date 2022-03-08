package com.example.campeones.model.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CampeonList {
    public String getKalos(String urlKalos) {
        try
        {
            URL url = new URL(urlKalos);
            URLConnection uc = url.openConnection();
            //String j = (String) uc.getContent();
            uc.setDoInput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            StringBuilder a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();
            return a.toString();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}

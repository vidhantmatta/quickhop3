package com.example.vidhant.auth;

import com.example.vidhant.auth.extras.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.stream.IntStream;

/**
 * Created by vidhant on 12/3/17.
 */

public class parsejson {
    public static String[] ids;
    public static String[] Names;
    public static String[] Prices;
    public static String[] Prices1;

    private JSONArray cart=null;
    private String json;
    double price1;
    double price;
    double sum=0;
    public parsejson(String json){
        this.json=json;
    }
    protected void parsejson(){
        JSONObject jsonObject=null;
        try{
            jsonObject=new JSONObject(json);
            cart=jsonObject.getJSONArray(Config.JSON_ARRAYCART);
            ids= new String[cart.length()];
            Names= new String[cart.length()];
            Prices=new String[cart.length()];
            Prices1=new String[cart.length()];
            for (int j=0;j<cart.length();j++) {
                JSONObject jo1 = cart.getJSONObject(j);

                Prices1[j] = jo1.getString(Config.KEY_PRICE);
                price1 = Double.parseDouble(parsejson.Prices1[j]);

                sum = sum + price1;
            }
            price=sum;

            for (int i=0 ; i<cart.length() ; i++) {
                JSONObject jo = cart.getJSONObject(i);
                ids[i] = jo.getString(Config.KEY_ID);
                Names[i] = jo.getString(Config.KEY_NAME);
                Prices[i] = jo.getString(Config.KEY_PRICE);



            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

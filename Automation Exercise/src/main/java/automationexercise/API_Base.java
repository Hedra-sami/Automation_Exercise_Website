package automationexercise;

import com.shaft.driver.SHAFT;

public class API_Base {
    private SHAFT.API api ;

    public  API_Base(SHAFT.API api){
        this.api=api;
    }
    public static String apibaseurl = System.getProperty("baseUrl")+"/api";

    public static final int success = 200 ;
}

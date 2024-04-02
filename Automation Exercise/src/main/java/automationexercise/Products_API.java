package automationexercise;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products_API {
    private SHAFT.API api ;
    public Products_API(SHAFT.API api){
        this.api = api;
    }

    // services
    private static final String productlistservice = "/productsList";
    private static final String postproductlistservice = "/productsList";


    // Actions
    @Step("Get all products list")
    public Products_API getproductlist(){
        api.get(productlistservice).setContentType(ContentType.JSON).setTargetStatusCode(API_Base.success).perform();
        return this ;
    }

    @Step("Post to products list")
    public Products_API posttoproductslist(){
        api.post(postproductlistservice).setContentType(ContentType.JSON).perform();
        return this;
    }




    // Assertions
    public void verifygetproducts(){
        api.verifyThatResponse().body().contains("products").perform();
    }

    public void verifypostproducts(){
        api.assertThatResponse().extractedJsonValue("message").isEqualTo("This request method is not supported.").perform();
    }




}

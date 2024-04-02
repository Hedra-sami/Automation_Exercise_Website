package Tests;

import automationexercise.API_Base;
import automationexercise.Products_API;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("Products List")
@Story("Get all products list")
public class product_list_test {
    private SHAFT.API api ;

    @Test(groups = "Product")
    @Description("Verify that the user can get all products list")
    public void getproductlisttest(){
      Products_API  test =new Products_API(api);
      test.getproductlist().verifygetproducts();
    }

    @Test(groups = "Product")
    @Description("Verify that the user cant post to products list")
    public void posttoproductstest(){
        Products_API test = new Products_API(api);
        test.posttoproductslist().verifypostproducts();
    }

    @BeforeMethod
    public void beforemethod(){
        api = new SHAFT.API(API_Base.apibaseurl);

    }

}

package Tests;

import automationexercise.API_Base;
import automationexercise.Account_Api;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Account Actions")
@Story("Register")

public class account_user_api {
    private SHAFT.API api ;
    private static Faker fakedata;
    private static String name;
    private static String email;
    private static String firstname;
    private static String password;
    private static String lastname;
    private static String address;
    private static String country;
    private static String zipcode;
    private static String state;
    private static String city;
    private static String phonenumber;
    private static String timestamp;


    @Test(groups = "account")
    @Description("Verify that the user can create a new account after entering required data")

    public void registerapitest(){


        name = fakedata.name().username();
        firstname = fakedata.name().firstName();
        password = String.valueOf(fakedata.random());
        lastname = fakedata.name().lastName();
        address = String.valueOf(fakedata.address());
        country = String.valueOf(fakedata.country());
        zipcode = String.valueOf(fakedata.address().zipCode());
        state = String.valueOf(fakedata.address().state());
        city = fakedata.address().city();
        phonenumber = fakedata.phoneNumber().phoneNumber();
        email = firstname + timestamp + "@gmail.com";

        Account_Api test = new Account_Api(api)
                .create_account(name, email, password,
                        firstname, lastname, address,
                        country, zipcode, state,
                        city, phonenumber).verifyaccountcreated();


        }


    @Test(description = "Delete Account Test",dependsOnMethods = {"registerapitest"} , groups = "account")
    @Description("Verify that the user can delete account")

    public void deleteaccountapitest(){
       new Account_Api(api).deleteacciunt(email,password).verifyaccountdeleted();
    }

    @Test(description = "Get Account Data Test" , dependsOnMethods = {"deleteaccountapitest"},groups = "account")
    @Description("Verify that the user can't get its data by email")

    public void getdataapitest(){
        new Account_Api(api).getuserdata(email).verifyaccountnotfound(email);
    }


        @BeforeMethod
    public void beforemethod(){
        api = new SHAFT.API(API_Base.apibaseurl);
        fakedata = new Faker();
        }
    }









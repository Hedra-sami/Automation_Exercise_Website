package automationexercise;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class Account_Api {
    private SHAFT.API api;

    public Account_Api(SHAFT.API api){
        this.api = api ;
    }
    // Services
    private static final String create_account_service = "/createAccount";
    private static final String verify_login_service = "/verifyLogin";
    private static final String delete_account_service = "/deleteAccount";
    private static final String update_account_service = "/updateAccount";
    private static final String get_user_data_service = "/getUserDetailByEmail";



    // Actions

    @Step("POST To Create/Register User Account")
    public Account_Api create_account(String username , String email , String password , String firstname , String lastname ,
                                      String address1 ,
                                      String country , String zipcode , String state ,
                                      String city , String mobile_number){
        List<List<Object>> formdata = Arrays.asList(
                Arrays.asList("name", username),
                Arrays.asList("email", email),
                Arrays.asList("password", password),
                Arrays.asList("firstname",firstname),
                Arrays.asList("lastname",lastname),
                Arrays.asList("address1",address1),
                Arrays.asList("country",country),
                Arrays.asList("zipcode",zipcode),
                Arrays.asList("state",state),
                Arrays.asList("city",city),
                Arrays.asList("mobile_number",mobile_number));

        api.post(create_account_service)
                .setParameters(formdata , RestActions.ParametersType.FORM)
                .setTargetStatusCode(API_Base.success)
                .setContentType(ContentType.URLENC)
                .perform();
        return this ;
    }


    @Step("PUT METHOD To Update User Account")
    public Account_Api update_account(String username , String email , String password , String firstname , String lastname ,
                                      String address1 , String country , String zipcode , String state ,
                                      String city , String mobile_number){
        List<List<Object>> formdata = Arrays.asList(
                Arrays.asList("name", username),
                Arrays.asList("email", email),
                Arrays.asList("password", password),
                Arrays.asList("firstname",firstname),
                Arrays.asList("lastname",lastname),
                Arrays.asList("address1",address1),
                Arrays.asList("country",country),
                Arrays.asList("zipcode",zipcode),
                Arrays.asList("state",state),
                Arrays.asList("city",city),
                Arrays.asList("mobile_number",mobile_number));

        api.put(update_account_service)
                .setParameters(formdata , RestActions.ParametersType.FORM)
                .setTargetStatusCode(API_Base.success)
                .setContentType(ContentType.URLENC)
                .perform();
        return this ;
    }
    @Step("POST To Verify Login with valid details")

    public Account_Api Login(String email , String password){
        List<List<Object>> dataforlogin = Arrays.asList(
                Arrays.asList("email", email),
                Arrays.asList("password", password));
        api.post(verify_login_service).setContentType(ContentType.URLENC)
                .setParameters(dataforlogin , RestActions.ParametersType.FORM)
                .setTargetStatusCode(API_Base.success)
                .perform();
        return this;

    }
    @Step("DELETE METHOD To Delete User Account")

    public Account_Api deleteacciunt(String email , String password){
        List<List<Object>> datafordelete = Arrays.asList(
                Arrays.asList("email", email),
                Arrays.asList("password", password));
        api.delete(delete_account_service).setContentType(ContentType.URLENC)
                .setParameters(datafordelete , RestActions.ParametersType.FORM).setTargetStatusCode(API_Base.success)
                .perform();
        return this;

    }
    @Step("GET user account detail by email")

    public Account_Api getuserdata(String email){
        List<List<Object>> emailtogetdata = Arrays.asList(
                Arrays.asList("email", email));
        api.get(get_user_data_service).setContentType(ContentType.URLENC)
                .setParameters(emailtogetdata , RestActions.ParametersType.FORM)
                .setTargetStatusCode(API_Base.success)
                .perform();
        return this;

    }


    // Validation
    @Step("Verify account created")
    public Account_Api verifyaccountcreated(){
        api.verifyThatResponse().extractedJsonValue("message").isEqualTo("User created!").perform();
        return this ;
    }
    @Step("Verify account deleted")
    public Account_Api verifyaccountdeleted(){
        api.verifyThatResponse().extractedJsonValue("message").isEqualTo("Account deleted!").perform();
        return this ;
    }
    public void verifyLogin(String email, String password){
        api.assertThatResponse().extractedJsonValue("message").isEqualTo("User exists!");

    }
   /* @Step("Verify Login")
    public Response verifyLogin(String email,String password){
        String body = api.getResponseBody();
        System.out.println(body);
        return Response ;
    }*/
    @Step("Verify account not found")
    public Account_Api verifyaccountnotfound(String email){
        getuserdata(email);
        api.verifyThatResponse().extractedJsonValue("message").isEqualTo("Account not found with this email, try another email!").perform();
        return this ;
    }

}

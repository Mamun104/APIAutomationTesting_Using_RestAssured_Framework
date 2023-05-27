package controller;

import Setup.Setup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User extends Setup {

    public User() throws IOException {
        initConfig();
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void userLoginWithValidCredential(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonpath = res.jsonPath();
        String token = jsonpath.get("token");
        String message=jsonpath.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);

    }
    public void userCanNotLoginWithInvalidEmail(String email, String password) throws ConfigurationException {

        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response response =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();


        JsonPath jsonpath = response.jsonPath();
        String token = jsonpath.get("token");
        String message=jsonpath.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);

    }

    public void userCanNotLoginWithInvalidPassword(String email, String password) throws ConfigurationException {

        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response response =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(401).extract().response();



        JsonPath jsonpath = response.jsonPath();
        String token = jsonpath.get("token");
        String message=jsonpath.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);

    }

    public void userCanNotLoginWithEmptyData(String email, String password) throws ConfigurationException {

        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response response =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();


        JsonPath jsonpath = response.jsonPath();
        String token = jsonpath.get("token");
        String message=jsonpath.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);

    }

    public String callingUserListAPI() throws IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .when()
                        .get("/user/list")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonpath = response.jsonPath();
        String id = jsonpath.get("users[0].id").toString();
        return id;
    }
}

package pages;

import base.APIControlActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TokenService extends APIControlActions {

    private ThreadLocal<String> csrfTokenThreadLocal = new ThreadLocal<>();

    private void login() {

        Response response = given()
                .baseUri(BASE_URI)
                .accept(ContentType.HTML)
                .when()
                .get("/auth/login")
                .then()
                .extract().response();
        Assertions.assertEquals(response.statusCode(), 200);
        cookiesThreadLocal.set(response.cookies());

        //Option 1 : Jsoup
        Document document = Jsoup.parse(response.asString());
        //Option 1.a : If we have ID
        csrfTokenThreadLocal.set(document.getElementById("login__csrf_token").attr("value"));
    }

    private void validateCredential(String userName, String password) {
        Map<String, String> formData = new HashMap<>();
        formData.put("login[_csrf_token]", csrfTokenThreadLocal.get());
        formData.put("hdnUserTimeZoneOffset", "5.5");
        formData.put("txtUsername", userName);
        formData.put("txtPassword", password);

        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.URLENC)
                .formParams(formData)
                .cookies(cookiesThreadLocal.get())
                .when()
                .post("/auth/validateCredentials")
                .then()
                .extract().response();

        Assertions.assertEquals(response.statusCode(), 200);
        cookiesThreadLocal.set(response.cookies());
    }

    private void getAccessToken() {
        Response response = given()
                .accept(ContentType.JSON)
                .baseUri(BASE_URI)
                .cookies(cookiesThreadLocal.get()) //Validate Response Cookies
                .when()
                .get("/core/getLoggedInAccountToken")
                .then()
                .extract().response();
        Assertions.assertEquals(response.statusCode(), 200);

        bearerTokenThreadLocal.set(response.jsonPath().getString("token.access_token"));
        Assertions.assertNotNull(bearerTokenThreadLocal.get(),"Bearer Token is Null");
//        setToken(bearerToken);
    }

    public void getAccessTokenFor(String userName, String password) {
        //Option 1.2 : If we don't have ID
//        String csrfTokenUsingCSS = document.select("input[name='login[_csrf_token]']").attr("value");
//        System.out.println(csrfTokenUsingCSS);


//        Option 2 : Regular Expression
//        Pattern pattern = Pattern.compile("[a-z0-9]{32}");
//        Matcher matcher = pattern.matcher(response.asString());
//        String csrfTokenUsingRegEx = "";
//        if (matcher.find()) {
//            csrfTokenUsingRegEx = matcher.group();
//        }
//        System.out.println(csrfTokenUsingRegEx);
        login();
        validateCredential(userName, password);
        getAccessToken();
    }

}

package steps;

import base.ApplicationConfig;
import constants.FileConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.TokenService;
import utility.PropertyUtils;

public class CommonSteps {

    TokenService tokenService = new TokenService();
    PropertyUtils propertyUtils = new PropertyUtils(FileConstants.CONFIG_PROPERTIES);

    @Given("user generate token for profile {string}")
    public void user_generate_token_for_profile(String profile) {
        String entry = propertyUtils.getValue(ApplicationConfig.getEnvironment()+"."+profile);
        String userName = entry.split(" ")[0];
        String password = entry.split(" ")[1];
        tokenService.getAccessTokenFor(userName,password);
    }

}

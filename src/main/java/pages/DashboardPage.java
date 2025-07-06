package pages;

import base.APIControlActions;
import io.restassured.response.Response;

public class DashboardPage extends APIControlActions {

    public Response getAllWidgets() {
        return executeGetRequest("/api/dashboard/widgets");
    }


// Hybrid = Data Driven + Functional + Modular + POM

}

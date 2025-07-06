package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import pages.DashboardPage;

import java.util.List;

public class DashboardSteps {

    DashboardPage dashboardPage = new DashboardPage();


    @Then("verity below widget list are coming")
    public void verityBelowWidgetListAreComing(DataTable table) {
        List<String> expectedList = table.asList();
        System.out.println(expectedList);

        Response widgetsResponse = dashboardPage.getAllWidgets();
        List<String> actualWidgetList = widgetsResponse.jsonPath().getList("data.title");

        Assertions.assertEquals(expectedList, actualWidgetList, "Widgets are not matches");
    }
}

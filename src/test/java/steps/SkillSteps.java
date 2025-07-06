package steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constants.StatusCode;
import constants.TestContext;
import entity.createEmployee.CreateEmployeeJsonPayload;
import entity.createEmployee.Data;
import entity.createEmployee.Params;
import entity.skillCreate.CreateSkillPayload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import pages.EmployeeListPage;
import pages.QualificationsSkillPage;
import utility.GenerateData;

import java.awt.*;
import java.util.Arrays;

public class SkillSteps {


    QualificationsSkillPage qualificationsSkillPage = new QualificationsSkillPage();
    EmployeeListPage employeeListPage = new EmployeeListPage();


    @Given("user create skill")
    public void user_create_skill(String docString) {
        String payload = docString.replace("skillName", GenerateData.getProgrammingLanguageName()).replace("skillDescription", GenerateData.getSomeDescription());
        Response createSkillResponse = qualificationsSkillPage.createSkill(payload);
        Assertions.assertEquals(createSkillResponse.statusCode(), StatusCode.CREATED);

        String skillID = createSkillResponse.jsonPath().getString("data.id");
        String skillDescription = createSkillResponse.jsonPath().getString("data.description");
        String skillName = createSkillResponse.jsonPath().getString("data.name");

        TestContext.setEntry("skillid", skillID);
        TestContext.setEntry("createSkillResponse", createSkillResponse);
        TestContext.setEntry("skilldescription", skillDescription);
        TestContext.setEntry("skillname", skillName);
    }


    @And("user update skill")
    public void userUpdateSkill(String docString) {
        String payload = docString.replace("skillName", GenerateData.getProgrammingLanguageName()).replace("skillDescription", GenerateData.getSomeDescription());
        Response updateSkillResponse = qualificationsSkillPage.updateSkill((String) TestContext.getValue("skillid"), payload);
        Assertions.assertEquals(updateSkillResponse.statusCode(), StatusCode.OK);

        Response response = (Response) TestContext.getValue("createSkillResponse");
        System.out.println(response.prettyPrint());
    }

    @And("user create random skill")
    public void userCreateRandomSkill() throws Exception {
        Response createSkillResponse = qualificationsSkillPage.createSkill();
        Assertions.assertEquals(createSkillResponse.statusCode(), StatusCode.CREATED);
        String skillID = createSkillResponse.jsonPath().getString("data.id");
        TestContext.setEntry("skillid", skillID);
    }

    @And("user update skill with random data")
    public void userUpdateSkillWithRandomData() throws Exception {
        Response updateSkillResponse = qualificationsSkillPage.updateSkill((String) TestContext.getValue("skillid"));
        Assertions.assertEquals(updateSkillResponse.statusCode(), StatusCode.OK);
    }


    //File read & replace
    //Serlization
    @And("user create entity for {string}")
    public void userCreateSkillUsing(String payloadType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String payload = "";
        switch (payloadType.toLowerCase()) {
            case "createskillpayload":
                CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(GenerateData.getProgrammingLanguageName()).description(GenerateData.getSomeDescription()).build();
                payload = mapper.writeValueAsString(createSkillPayload);
                Response createSkillResponse = qualificationsSkillPage.createSkill(payload);
                String skillID = createSkillResponse.jsonPath().getString("data.id");
                TestContext.setEntry("skillid",skillID);
                break;

            case "createemployeepayload":
                String firstName = GenerateData.getFirstName();
                String lastName = GenerateData.getLastName();
                String middleName = GenerateData.getMiddleName();
                String joiningDate = GenerateData.getFuture();
                String userName = GenerateData.getUserName();
                String password = GenerateData.getRandomString();

                Data primaryInformation = Data.builder().firstName(firstName).middleName(middleName).lastName(lastName).chkLogin(true).locationId("1").joinedDate(joiningDate).autoGenerateEmployeeId(true).initiatePreboarding(false).employeeId(null).userName(userName).userPassword(password).rePassword(password).status("Enabled").essRoleId("2").supervisorRoleId("3").adminRoleId("1").build();
                Params params = Params.builder().build();
                CreateEmployeeJsonPayload firstJsonObject = CreateEmployeeJsonPayload.builder().data(primaryInformation).params(params).method("POST").endpoint("employees").build();

                ObjectNode firstJSONObject = mapper.valueToTree(firstJsonObject);
                ObjectNode data = (ObjectNode) firstJSONObject.get("data");
                data.putNull("employeeId");


                Data personInformation = Data.builder().firstName(firstName).middleName(middleName).lastName(lastName).emp_gender(null).emp_marital_status(null).nation_code("82").emp_birthday(null).emp_dri_lice_exp_date(null).build();
                CreateEmployeeJsonPayload secondsJsonObject = CreateEmployeeJsonPayload.builder().data(personInformation).params(params).method("PATCH").endpoint("employees/<EMPNUMBER>").build();

                ObjectNode secondJSONObject = mapper.valueToTree(secondsJsonObject);
                ObjectNode dataSecondJson = (ObjectNode) secondJSONObject.get("data");
                dataSecondJson.putNull("emp_marital_status");
                dataSecondJson.putNull("emp_gender");
                dataSecondJson.putNull("emp_birthday");
                dataSecondJson.putNull("emp_dri_lice_exp_date");

                Data jobInformation = Data.builder().joined_date(joiningDate).probation_end_date(null).date_of_permanency(null).job_title_id("12").employment_status_id("3").job_category_id("").subunit_id("").location_id("1").work_schedule_id("2").cost_centre_id(null).has_contract_details("1").contract_start_date(null).contract_end_date(null).comment("").effective_date(joiningDate).event_id("1").build();

                CreateEmployeeJsonPayload thirdJsonObject = CreateEmployeeJsonPayload.builder().data(jobInformation).params(params).method("PATCH").endpoint("employees/<EMPNUMBER>/job").build();

                ObjectNode thirdJSONObject = mapper.valueToTree(thirdJsonObject);
                ObjectNode jobData = (ObjectNode) thirdJSONObject.get("data");
                jobData.putNull("probation_end_date");
                jobData.putNull("date_of_permanency");
                jobData.putNull("cost_centre_id");
                jobData.putNull("contract_start_date");
                jobData.putNull("contract_end_date");

                Data subData = Data.builder().eventTemplate("2").dueDate(joiningDate).owners(Arrays.asList("29", "38")).build();
                Data mainData = Data.builder().data(subData).build();

                CreateEmployeeJsonPayload fourthJsonObject = CreateEmployeeJsonPayload.builder().endpoint("employeeOnboarding/<EMPNUMBER>/create").method("POST").params(params).data(mainData).build();


                String first = mapper.writeValueAsString(firstJSONObject);
                String second = mapper.writeValueAsString(secondJSONObject);
                String third = mapper.writeValueAsString(thirdJSONObject);
                String fourth = mapper.writeValueAsString(fourthJsonObject);

                String finalPayload = String.valueOf(Arrays.asList(first, second, third, fourth));
                employeeListPage.createEmployee(finalPayload);
                break;
        }
    }

    @And("user update entity for {string}")
    public void userUpdateSkillUsing(String payloadType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String payload = "";
        switch (payloadType.toLowerCase()) {
            case "updateskillpayload":
                CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(GenerateData.getProgrammingLanguageName()).description(GenerateData.getSomeDescription()).build();
                payload = mapper.writeValueAsString(createSkillPayload);
                qualificationsSkillPage.updateSkill((String) TestContext.getValue("skillid"), payload);
                break;
        }
    }

    @And("user delete skill using {string}")
    public void userDeleteSkillUsing(String payloadType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String payload = "";
        switch (payloadType.toLowerCase()) {
            case "deleteskillpayload":
                CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(GenerateData.getProgrammingLanguageName()).description(GenerateData.getSomeDescription()).build();
                payload = mapper.writeValueAsString(createSkillPayload);
                break;
        }
    }
}

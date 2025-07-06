package pages;

import base.APIControlActions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.skillCreate.CreateSkillPayload;
import io.restassured.response.Response;
import utility.GenerateData;

import java.util.HashMap;
import java.util.Map;

public class QualificationsSkillPage extends APIControlActions {


    public Response getAllSkill(String limit, String fieldName, String sortingOrder) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("limit", limit);
        queryParams.put("sortingFeild", fieldName);
        queryParams.put("sortingOrder", sortingOrder);
        setQueryParam(queryParams);
        return executeGetRequest("/api/skills");
    }

    public Response getAllSkill() {
        return executeGetRequest("/api/skills");
    }


    public Response createSkill() throws JsonProcessingException {
        CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name("").description("").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createSkillPayload);
        setPayload(payload);
        return executePostRequest("/api/skills");
    }

    public Response createSkill(String payload) {
        setPayload(payload);
        return executePostRequest("/api/skills");
    }

    public Response updateSkill(String skillID, String payload) {
        setPayload(payload);
        return executePatchRequest("/api/skills/" + skillID);
    }

    public Response updateSkill(String skillID) throws JsonProcessingException {
        CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(GenerateData.getProgrammingLanguageName()).description(GenerateData.getSomeDescription()).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createSkillPayload);
        setPayload(payload);
        return executePatchRequest("/api/skills/" + skillID);
    }

    public Response deleteSkill(String payload) {
        setPayload(payload);
        return executeDeleteRequest("/api/skills");
    }
}

package de.org.api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

public class BaseMethod {

    protected RequestSpecification request;
    protected String methodType;
    private final String requestPayload;
    private String apiUrl;

    public BaseMethod(String methodType, String apiUrl, String requestPayload) {
        this.requestPayload = readFile(requestPayload);
        this.request = RestAssured.given();
        this.apiUrl = apiUrl;
        this.methodType = methodType;
    }

    public void setHeader(String key, Object value) {
        this.request.header(key, value);
    }

    public void validateResponseBody(Response response, String status) {
        String actualResponse = response.getBody().asString();
        Assert.assertTrue(actualResponse.contains(status), "Response does not match expected output!");
    }

    private String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addBody() {
        if(this.requestPayload != null) {
            this.request.body(this.requestPayload);
        }
    }

    public Response callAPI() {
        return send(this.request, this.apiUrl, this.methodType);
    }

    private Response send(RequestSpecification rq, String url, String method) {
        return switch (method) {
            case "GET" -> rq.get(url);
            case "POST" -> rq.post(url);
            default -> throw new RuntimeException("Unknown method");
        };
    }
}

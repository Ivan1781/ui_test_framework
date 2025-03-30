package de.org.api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Objects;

@Log4j2
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
            ClassLoader classLoader = BaseMethod.class.getClassLoader();
            return new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(classLoader.getResource(path)).toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void addBody() {
        if(this.requestPayload != null) {
            this.request.body(this.requestPayload);
        }
    }

    public Response callAPI() {
        addBody();
        Response response = send(this.request, this.apiUrl, this.methodType);
        log.info(String.format("%s %s %s", this.methodType, this.apiUrl, this.request));
        log.info(response.getBody().print());
        return response;
    }

    private Response send(RequestSpecification rq, String url, String method) {
        return switch (method) {
            case "GET" -> rq.get(url);
            case "POST" -> rq.post(url);
            default -> throw new RuntimeException("Unknown method");
        };
    }
}

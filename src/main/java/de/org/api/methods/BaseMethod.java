package de.org.api.methods;

import de.org.common.properties.Props;
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
import java.util.Properties;

@Log4j2
public class BaseMethod {

    protected RequestSpecification request;
    protected String methodType;
    private final String requestPayload;
//    private String apiUrl;
    private Properties properties;
    protected String methodPath;

    public BaseMethod(String methodType, String url, String requestPayload) {
        this.requestPayload = readFile(requestPayload);
        this.request = RestAssured.given();
        this.methodPath = url;
        replaceUrlPlaceholder("base_url", Props.BASE_URL );
        this.methodType = methodType;
        this.initProperties();
    }

    public Response callAPI() {
        addBody();
        Response response = send(this.request, this.methodPath, this.methodType);
//        log.info(String.format("%s %s %s", this.methodType, this.apiUrl, this.request));
        log.info(response.getBody().print());
        return response;
    }

    public void replaceUrlPlaceholder(String placeholder, String value) {
        if (value != null) {
            this.methodPath = this.methodPath.replace("${" + placeholder + "}", value);
        } else {
            this.methodPath = this.methodPath.replace("${" + placeholder + "}", "");
        }
    }

    public void setHeader(String key, Object value) {
        this.request.header(key, value);
    }

    public void validateResponseBody(Response response, String status) {
        String actualResponse = response.getBody().asString();
        Assert.assertTrue(actualResponse.contains(status), "Response does not match expected output!");
    }

    public void addProperty(String key, Object value) {
        Objects.requireNonNull(this.properties, "Properties are not initialized");
        this.properties.put(key, value);
    }

    protected void replaceIdPlaceholderInUrl(String id) {
        replaceUrlPlaceholder("id", id);
        addProperty("id", id);
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
        if (this.requestPayload != null) {
            this.request.body(this.requestPayload);
        }
    }

    private Response send(RequestSpecification rq, String url, String method) {
        return switch (method) {
            case "GET" -> rq.get(url);
            case "POST" -> rq.post(url);
            default -> throw new RuntimeException("Unknown method");
        };
    }

    private void initProperties() {
        this.properties = new Properties();
    }
}

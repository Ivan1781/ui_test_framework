package de.org.api.methods;

import de.org.api.fileprocessor.FileProcessor;
import de.org.common.properties.Props;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.util.Objects;
import java.util.Properties;

@Log4j2
public class BaseMethod extends AbstractMethod {

    public BaseMethod(String methodType, String url) {
        this(methodType, url, null);
    }

    public BaseMethod(String methodType, String url, String requestPayload) {
        if(requestPayload != null) {
            this.requestPayload = FileProcessor.readFile(requestPayload);
        }
        this.request = RestAssured.given();
        this.methodPath = url;
        replaceUrlPlaceholder("base_url", Props.BASE_URL );
        this.methodType = methodType;
        this.initProperties();
    }

    public Response callAPI() {
        addBody();
        Response response = send(this.request, this.methodPath, this.methodType);
        log.info(response.getBody().print());
        return response;
    }

    public void validateResponseBody(Response response, String status) {
        String actualResponse = response.getBody().asString();
        Assert.assertTrue(actualResponse.contains(status), "Response does not match expected output!");
    }

    public void addProperty(String key, Object value) {
        Objects.requireNonNull(this.properties, "Properties are not initialized");
        this.properties.put(key, value);
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

package de.org.api.methods;

import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

public class AbstractMethod {

    protected Properties properties;
    protected RequestSpecification request;
    protected String methodType;
    protected String requestPayload;
    protected String methodPath;

    public void setHeader(String key, String value) {
        this.request.header(key, value);
    }

    public void setHeaders(Map<String, String> headers) {
        if(headers != null) {
            headers.forEach(this::setHeader);
        }
    }

    protected void addQueryParamsToUrl(Map<String, Object> queryParams) {
        if (queryParams != null) {
            queryParams.forEach((x, y) -> this.request.queryParam(x, y));
        }
    }

    public void replaceUrlPlaceholder(String placeholder, String value) {
        if (value != null) {
            this.methodPath = this.methodPath.replace("${" + placeholder + "}", value);
        } else {
            this.methodPath = this.methodPath.replace("${" + placeholder + "}", "");
        }
    }

    public void addBody() {
        if (this.requestPayload != null) {
            this.request.body(this.requestPayload);
        }
    }

}

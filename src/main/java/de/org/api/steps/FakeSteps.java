package de.org.api.steps;

import de.org.api.methods.FakeMethod;
import io.restassured.response.Response;

public class FakeSteps implements ICall {

    public void postFake() {
        Response response = callApi(false, new FakeMethod());
    }

}

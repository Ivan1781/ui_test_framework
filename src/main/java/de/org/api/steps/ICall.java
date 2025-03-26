package de.org.api.steps;

import de.org.api.methods.BaseMethod;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public interface ICall {

    default Response callApi(boolean validateResponse, BaseMethod method) {
        Response response = method.callAPI();
        Assertions.assertThat(response.statusCode()).isEqualTo(201);
        return response;
    }
}

package de.org.api.methods;

import org.apache.http.Header;

public class FakeMethod extends BaseMethod {

    public FakeMethod() {
        super("POST", "https://jsonplaceholder.typicode.com/posts", "api/fake.json");
        setHeader("Content-type", "application/json; charset=UTF-8");
    }

}

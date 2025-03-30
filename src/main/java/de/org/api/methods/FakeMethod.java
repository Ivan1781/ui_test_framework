package de.org.api.methods;

public class FakeMethod extends BaseMethod {

    public FakeMethod() {
        super("POST", "https://jsonplaceholder.typicode.com/posts", "api/fake.json");
        setHeader("Content-type", "application/json; charset=UTF-8");
    }

}

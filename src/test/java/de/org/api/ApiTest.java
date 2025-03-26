package de.org.api;

import de.org.api.steps.FakeSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiTest {

    private FakeSteps fakeSteps;

    @BeforeClass
    public void precondition() {
        fakeSteps = new FakeSteps();
    }

    @Test
    public void apiFakeTest() {
        fakeSteps.postFake();
    }
}

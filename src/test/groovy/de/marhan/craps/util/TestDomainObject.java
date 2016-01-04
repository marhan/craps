package de.marhan.craps.util;

public class TestDomainObject extends DomainObject{

    private String testField = "testValue";

    @Override
    public String buildMessage() {
        return testField;
    }
}

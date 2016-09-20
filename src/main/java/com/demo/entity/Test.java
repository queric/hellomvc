package com.demo.entity;

import javax.persistence.*;

/**
 * Created by Queric on 2016/7/29.
 */
@Entity
public class Test {
    private int testId;
    private String testName;
    private String testFor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestFor() {
        return testFor;
    }

    public void setTestFor(String testFor) {
        this.testFor = testFor;
    }
}

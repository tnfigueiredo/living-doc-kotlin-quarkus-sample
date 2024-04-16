package com.tnfigueiredo.docsample.bdd

import io.quarkiverse.cucumber.CucumberQuarkusTest
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
class CucumberTestRunner: CucumberQuarkusTest() {

}
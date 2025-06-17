package com.intflow.apiintflowservice;

import com.intuit.karate.junit5.Karate;

class ApiIntFlowServiceTest {

    @Karate.Test
    Karate testApiIntFlowService() {
        return Karate.run("classpath:karate/apiintflowservice").relativeTo(getClass());
    }
}
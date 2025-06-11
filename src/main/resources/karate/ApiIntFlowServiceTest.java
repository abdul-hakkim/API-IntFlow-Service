package karate;

import com.intuit.karate.junit5.Karate;

class ApiIntFlowServiceTest {

    @Karate.Test
    Karate testApiIntFlowService() {
        return Karate.run("apiintflowservice").relativeTo(getClass());
    }
}
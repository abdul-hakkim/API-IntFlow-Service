package com.intflow.apiintflowservice;

import com.intflow.apiintflowservice.repository.UserRepository;
import com.intflow.apiintflowservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class ApiIntFlowServiceApplicationTests {

    @MockBean
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        given(userRepository.findById(1L)).willReturn(Optional.of(new User(1L, "mocked")));
    }
}
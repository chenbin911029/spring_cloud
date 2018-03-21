package spring.cloud.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenbin at 2018/3/21 17:56
 */
@Service
public class HystrixService {
    @Autowired
    private CallDependencyService dependencyService;

    public String callDependencyService() {
        return dependencyService.mockGetUserInfo();
    }
}

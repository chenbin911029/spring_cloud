package spring.cloud.core.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author chenbin at 2018/3/21 17:53
 */
@Component
public class CallDependencyService {
    private Random random = new Random();
    /**
     * 模拟获取用户信息(通过网络调用)
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String mockGetUserInfo() {
        int randomInt = random.nextInt(10);
        if(randomInt<8){  //模拟调用失败情况
            throw new RuntimeException("call dependency service fail.");
        }else{
            return "UserName:liaokailin;number:"+randomInt;
        }
    }

    public String fallback() {
        return "some exception occur call fallback method.";
    }

}

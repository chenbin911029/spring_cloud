package org.spring.springcloud.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springcloud.service.FeignProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Customer HelloWorld 案例
 * <p>
 * Created by bysocket on 06/22/17.
 */
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类
    @Autowired
    private FeignProductService feignProductService;

    @RequestMapping("/customer")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://CLUSTER-PROVIDER-SERVICE/provider",
                String.class).getBody();

        return "Hello,Customer! msg from provider : <br/><br/> " + providerMsg;
    }

    @RequestMapping("/loadBalanceGetProductInfo")
    @HystrixCommand(fallbackMethod = "error")
    public Object loadBalanceGetProductInfo(){

//        Random random = new Random();
//        int randomInt = random.nextInt(10);
//        if(randomInt<8){  //模拟调用失败情况
//            throw new RuntimeException("call dependency service fail.");
//        }

        Object result = restTemplate.getForObject("http://CLUSTER-PROVIDER-SERVICE/getProductById/13", Object.class);
        return result;
    }

    @RequestMapping("/getProductInfoByFeign")
    public Object getProductInfoByFeign() {
        Object result = feignProductService.getProductById("12");
        return result;
    }

    public String error() {
        return "error Hystrix 断路器。";
    }

}
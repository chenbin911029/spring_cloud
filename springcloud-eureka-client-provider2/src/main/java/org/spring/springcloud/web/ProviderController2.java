package org.spring.springcloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springcloud.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provider HelloWorld 案例
 * <p>
 * Created by bysocket on 06/22/17.
 */
@RestController
public class ProviderController2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController2.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Registration registration;

    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        LOGGER.info("provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        return "Hello,Provider!";
    }

    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @RequestMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable String id){

        Product product = new Product();
        product.setId(id);
        product.setName("测试产品2");
        product.setPrice(22.1f);
        return product;
    }
}
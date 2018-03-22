package org.spring.springcloud.service;

import org.spring.springcloud.domain.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chenbin at 2018/3/22 9:13
 */
@FeignClient(value = "CLUSTER-PROVIDER-SERVICE")
public interface FeignProductService {
    @RequestMapping(value = "/getProductById/{id}",method = RequestMethod.GET)
    public Product getProductById(@PathVariable(value="id") String id);
}

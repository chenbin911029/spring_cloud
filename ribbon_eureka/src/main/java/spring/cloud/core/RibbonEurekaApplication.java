package spring.cloud.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RibbonEurekaApplication {

	@Autowired
	RestTemplate restTemplate;

	/**
	 * LoadBalanced 注解表明restTemplate使用LoadBalancerClient执行请求
	 *
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);
		return template;
	}

	@RequestMapping("/ribbonTest")
	public String helloWrold() {
		String providerMsg = restTemplate.getForEntity("http://CLUSTER-PROVIDER-SERVICE/provider",
				String.class).getBody();

		return "ribbonTest! msg from provider : <br/><br/> " + providerMsg;
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonEurekaApplication.class, args);
	}
}

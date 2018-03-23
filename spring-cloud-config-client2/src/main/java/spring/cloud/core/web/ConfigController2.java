package spring.cloud.core.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenbin at 2018/3/22 10:41
 */
@RestController
public class ConfigController2 {
    @Value("${mysql.host}")
    private String mysqlHost;
    @Value("${my.faith}")
    private String myFaith;
    @Value("${my.name}")
    private String myName;

    @RequestMapping("/getProperty")
    public Object getProperty2(){
        StringBuilder sb2 = new StringBuilder();
        sb2.append("mysqlHost:");
        sb2.append(mysqlHost);
        sb2.append("  myFaith:");
        sb2.append(myFaith);
        sb2.append("  myName:");
        sb2.append(myName);

        return sb2.toString();
    }
}

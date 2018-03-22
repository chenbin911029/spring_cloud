package spring.cloud.core.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenbin at 2018/3/22 10:41
 */
@RestController
public class ConfigController {
    @Value("${mysql.host}")
    private String mysqlHost;
    @Value("${my.faith}")
    private String myFaith;
    @Value("${my.name}")
    private String myName;

    @RequestMapping("/getProperty")
    public Object getProperty(){
        StringBuilder sb = new StringBuilder();
        sb.append("mysqlHost:");
        sb.append(mysqlHost);
        sb.append("  myFaith:");
        sb.append(myFaith);
        sb.append("  myName:");
        sb.append(myName);

        return sb.toString();
    }
}

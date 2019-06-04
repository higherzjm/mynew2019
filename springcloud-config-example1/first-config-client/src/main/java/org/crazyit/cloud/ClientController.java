package org.crazyit.cloud;

import org.apache.catalina.util.Strftime;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Autowired
	private Environment env;
	@RequestMapping(value = "/gp", method = RequestMethod.GET)
	public String getProp() {
		System.out.println("获取参数值");
		String value=env.getProperty("springcloud.username");
		if (value==null){
			value=env.getProperty("springcloud.config.username");
		}
		System.out.println("value:"+value);
		return value;
	}

	@RequestMapping(value = "/refreshConfig", method = RequestMethod.GET)
	public String refreshConfig() {
		String  value="";
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://localhost:8080/refresh");
			HttpResponse response = client.execute(post);
			value="刷新成功："+EntityUtils.toString(response.getEntity());
		}catch (Exception e){
			e.printStackTrace();
			value="刷新异常";
		}
		System.out.println("value:"+value);
		return value;
	}
}

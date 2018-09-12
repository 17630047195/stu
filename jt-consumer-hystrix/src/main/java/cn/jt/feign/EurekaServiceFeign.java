package cn.jt.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//标识是一个Feign客户端程序，去调用服务提供者，通过application.name
@FeignClient(value="provider-user")
public interface EurekaServiceFeign {
	//仿照服务提供者方法写一个接口方法
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name);
}

package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.feign.EurekaServiceFeign;

@RestController
public class HelloController {
	@Autowired //注入Feign接口
	private EurekaServiceFeign eurekaServiceFeign;
	
	//用户就访问RESTFul URL请求
	@RequestMapping("/hello/{name}")
	//支持断路器Hystrix，fallbackMethod参数就指定失败时方法名
	@HystrixCommand(fallbackMethod = "fallbackHello")
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}
	
	//写一个方法业务访问失败时调用方法，要求：参数和返回值要调用微服务一致
	public String fallbackHello(String name){
		return "lizhuangzhi";	//设置一个默认范围值
	}
}



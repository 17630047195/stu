package cn.jt.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

//@Component //底层包扫描和创建实例
public class ZuulFallback implements ZuulFallbackProvider{

	@Override //提供者application.name 
	public String getRoute() {
		
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		
		return new ClientHttpResponse(){

			@Override   //设置返回内容，当出现异常时，断路器返回默认值
			public InputStream getBody() throws IOException {
				
				return new ByteArrayInputStream(new String("tony").getBytes());
			}

			@Override//设置头信息
			public HttpHeaders getHeaders() {
				HttpHeaders heads = new HttpHeaders();
				heads.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return heads;
			}

			@Override  
			public HttpStatus getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.BAD_REQUEST;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.BAD_REQUEST.value();
			}

			@Override
			public String getStatusText() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
   
}

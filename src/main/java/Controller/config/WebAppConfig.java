package Controller.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
//@Configuration
//@ComponentScan(basePackages = "tw.leonchen")
//@EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;




@Configuration
@ComponentScan(basePackages = "Controller")
@EnableWebMvc
@PropertySource("classpath:database.properties")//基本連接註冊sql password url
@EnableTransactionManagement

//    /* 上面三個等於下面三個設定
//<context:annotation-config/>
//<context:component-scan base-package="tw.leonchen"/>
//<mvc:annotation-driven/>
public class WebAppConfig implements WebMvcConfigurer {
	
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "hotel");
		
	}
	@Override  
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Bean
	public InternalResourceViewResolver irViewResolver() {
		InternalResourceViewResolver irve1 = new InternalResourceViewResolver();
		irve1.setPrefix("/WEB-INF/pages/");
		irve1.setSuffix(".jsp");
		irve1.setOrder(6);
		return irve1;
	}
//	完成檔案上傳功能  from org.springframework.web.multipart.commons.CommonsMultipartResolver
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr1 = new CommonsMultipartResolver();
		cmr1.setDefaultEncoding("UTF-8");
		return cmr1;
	}
	@Bean
	public MappingJackson2JsonView jasonVIew() {
		MappingJackson2JsonView jason2view = new MappingJackson2JsonView();
		jason2view.setPrettyPrint(true);
		return jason2view;
	}
	@Bean
	public  Jaxb2Marshaller jaxbMarshaller() {
		Jaxb2Marshaller jax2 = new Jaxb2Marshaller();
		jax2.setPackagesToScan("tw.leonchen");
		return jax2;
	}
	@Bean
	public ContentNegotiatingViewResolver negociateViewResolver() {
			ContentNegotiatingViewResolver cnvr1 = new ContentNegotiatingViewResolver();		
			ArrayList<View> list = new ArrayList<View>();
			list.add(jasonVIew());			
			cnvr1.setDefaultViews(list);
			return cnvr1;
			
	}

}

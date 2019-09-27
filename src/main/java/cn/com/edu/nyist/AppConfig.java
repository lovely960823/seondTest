package cn.com.edu.nyist;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import cn.com.edu.nyist.interceptor.LoginInterceptor;


@Configuration
public class AppConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment env;
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setUsername(env.getProperty("spring.datasource.username").trim());
		ds.setPassword(env.getProperty("spring.datasource.password").trim());
		ds.setUrl(env.getProperty("spring.datasource.url").trim());
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name").trim());
		return ds;
	}
	//开放upload目录可以访问到    需要继承WebMvcConfigurerAdapter
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("WEB-INF/upload/");
	}
	//配置拦截器
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			//registry.addInterceptor(new ThemeChangeInterceptor());
			registry.addInterceptor(new LoginInterceptor());
		}
//错误页面
		@Bean
		public EmbeddedServletContainerCustomizer containerCustomizer() {
			return new EmbeddedServletContainerCustomizer() {
				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));            
	                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));         
					container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
				}
			};
		}
		
}

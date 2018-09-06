package com.taxation.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxation.filter.MyFilter;
import com.taxation.service.impl.PersonService;
import com.taxation.service.interfaces.IPersonService;

@Configuration
public class TaxationConfig {

	@Bean
	@Qualifier("personService")
	public IPersonService getMessageService() {
		return new PersonService();
	}

	@Bean
	public FilterRegistrationBean<MyFilter> myFilterBean() {
		final FilterRegistrationBean<MyFilter> filterRegBean = new FilterRegistrationBean<MyFilter>();
		filterRegBean.setFilter(new MyFilter());
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("My Filter");
		filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}
}

package com.taxation.configuration;

import com.taxation.dao.interfaces.ITaxDao;
import com.taxation.service.impl.*;
import com.taxation.service.interfaces.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxation.filter.MyFilter;

@Configuration
public class TaxationConfig {

	@Bean
	@Qualifier("personService")
	public IPersonService getPersonService() {
		return new PersonService();
	}

	@Bean
	@Qualifier("propertyService")
	public IPropertyService getPropertyService() {
		return new PropertyService();
	}

	@Bean
	@Qualifier("seedService")
	public ISeedService getSeedService(){ return new SeedService();}

	@Bean
	@Qualifier("propertyTypeService")
	public IPropertyTypeService getPropertyTypeService(){	return  new PropertyTypeService();}

	@Bean
	@Qualifier("propertyUsageService")
	public IPropertyUsageService getPropertyUsageServcie(){ return  new PropertyUsageService();}

	@Bean
	@Qualifier("taxService")
	public ITaxService getTaxService(){ return new TaxService();}

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

package com.taxation.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxation.service.impl.PanchayatService;
import com.taxation.service.impl.PersonService;
import com.taxation.service.impl.PropertyService;
import com.taxation.service.impl.PropertyTypeService;
import com.taxation.service.impl.PropertyUsageService;
import com.taxation.service.impl.SeedService;
import com.taxation.service.impl.TaxDetailsService;
import com.taxation.service.impl.TaxService;
import com.taxation.service.interfaces.IPanchayatService;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.IPropertyService;
import com.taxation.service.interfaces.IPropertyTypeService;
import com.taxation.service.interfaces.IPropertyUsageService;
import com.taxation.service.interfaces.ISeedService;
import com.taxation.service.interfaces.ITaxDetailsService;
import com.taxation.service.interfaces.ITaxService;

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
	@Qualifier("panchayatServiceProvider")
	public IPanchayatService getPanchayatService(){ return new PanchayatService();}

	@Bean
	@Qualifier("taxDetailsService")
	public ITaxDetailsService getTaxDetailsService(){ return new TaxDetailsService();}

	/*@Bean
	public FilterRegistrationBean<MyFilter> myFilterBean() {
		final FilterRegistrationBean<MyFilter> filterRegBean = new FilterRegistrationBean<MyFilter>();
		filterRegBean.setFilter(new MyFilter());
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("My Filter");
		filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}*/
}

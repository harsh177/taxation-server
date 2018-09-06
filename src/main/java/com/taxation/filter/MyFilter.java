package com.taxation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

//If we are registring this filter at configuration then no need to add @Component over here
@Order(2)
public class MyFilter implements Filter {

	private final static Logger logger = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		logger.debug("init myfilter");
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		logger.debug("myfilter called" + request.getRemoteAddr());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.debug("Destroy myfilter");
	}
}
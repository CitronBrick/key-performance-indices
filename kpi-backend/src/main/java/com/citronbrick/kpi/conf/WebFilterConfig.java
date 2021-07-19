package com.citronbrick.kpi.conf;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.boot.web.servlet.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import com.citronbrick.kpi.services.*;
import org.springframework.stereotype.*;


@Configuration
@AllArgsConstructor
public class WebFilterConfig {

	private AuthFilter authFilter;


	@Bean
	public FilterRegistrationBean<Filter> makeFilter() {
		var res = new FilterRegistrationBean<Filter>();
		res.addUrlPatterns("/rest/companies/details");
		res.setFilter(authFilter);
		return res;
	}


}

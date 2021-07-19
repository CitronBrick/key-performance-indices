package com.citronbrick.kpi.services;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.citronbrick.kpi.web.*;



@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthFilter implements Filter {

	@Autowired
	private TokenService tokenService;

	public void init(FilterConfig fc) {

	}

	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain chain) throws IOException, ServletException {
		if(sreq instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)sreq;

			if(!hreq.getMethod().equals("GET")) {
				chain.doFilter(sreq,sres);
			} else {
				var token = hreq.getHeader("Authorization");
				System.out.println("Authorization: "+token);
				System.out.println(tokenService.findUser(token));
				if(tokenService.findUser(token) == null) {
					HttpServletResponse hres = (HttpServletResponse)sres;
					hres.setStatus(HttpServletResponse.SC_FORBIDDEN);
					/*hres.reset();
					// hres.sendError(403,"You are not authorized !");
					// throw new NotAuthorizedException();
					/*PrintWriter pw = sres.getWriter();
					pw.println("No Authorization");*/
				}
			}


		}
		chain.doFilter(sreq,sres);
	}


	public void destroy() {

	}

}


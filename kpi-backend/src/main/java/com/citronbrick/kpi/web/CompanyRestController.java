package com.citronbrick.kpi.web;

import com.citronbrick.kpi.services.*;
import com.citronbrick.kpi.entities.*;
import java.util.*;
import javax.servlet.http.*;




import org.springframework.web.bind.annotation.*;
import lombok.*;


@RestController
@RequestMapping("/rest/companies")
@AllArgsConstructor
// @CrossOrigin({"http://localhost:3000"})
public class CompanyRestController {

	private FakeCompanyService fcs;

	


	@GetMapping("/details")
	Object getAllCompaniesDetails(HttpServletResponse hres) {
		if(hres.getStatus()==403) {
			return "You are not authorized";

		}
		return fcs.getCompanies();
	}	

	@RequestMapping(value="/details",method=RequestMethod.OPTIONS)
	public String seeOptions() {
		return "abc";		
	}

	


}
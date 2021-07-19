package com.citronbrick.kpi.services;

import com.citronbrick.kpi.entities.*;



import org.springframework.stereotype.*;
import javax.annotation.*;
import lombok.*;
import java.util.*;
import java.io.*;
import org.springframework.core.io.*;


@Service
@NoArgsConstructor
public class FakeCompanyService {

	@Getter
	private List<String> companyNames = new ArrayList<>();

	@Getter
	private List<Company> companies = new ArrayList<>();

	private static Random rand = new Random();


	// company names : https://gist.github.com/demersdesigns/aac366882659a989e958
	@PostConstruct
	public void loadCompanies() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("companies.txt").getInputStream()))) {
			for(String line = br.readLine(); line != null; line = br.readLine()) {
				String name = br.readLine();
				Company c = new Company(name);
				for(PerformanceIndices pi : PerformanceIndices.values()) {
					Map<Integer, Double> perfData = new TreeMap<>();
					for(var year = 2000; year <= 2020; year++) {
							perfData.put(year,(double)rand.nextInt(100));
					}
					c.putValue(pi, perfData);

				}
				companies.add(c);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	


}
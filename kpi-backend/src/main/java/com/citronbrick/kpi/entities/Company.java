package com.citronbrick.kpi.entities;

import lombok.*;
import java.util.*;
import java.io.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {

	private static final long serialVersionUID = 22;

	@Id
	private String name;
	private Map<PerformanceIndices,Map<Integer, Double>> performance = new TreeMap<>(); 


	public Company(String name) {
		this.name = name;
	}


	public void putValue(PerformanceIndices p, Map<Integer,Double> d) {
		performance.put(p,d);
	}


	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		Company c = (Company)o;
		return name.equals(c.name);
	}

	@Override
	public String toString() {
		return name + performance.toString();
	}

}
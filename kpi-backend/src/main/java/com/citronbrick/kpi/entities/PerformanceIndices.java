package com.citronbrick.kpi.entities;

import lombok.*;

public enum PerformanceIndices {

	// https://www.investopedia.com/terms/k/kpi.asp
	REVENUE_GROWTH("Revenue Growth"),
	REVENUE_PER_CLIENT("Revenue per Client"),
	PROFIT_MARGIN("Profit margin"),
	CLIENT_RETENTION_RATE("Client retention rate"),
	CUSTOMER_SATISFACTION("Customer satisfaction");

	@Getter
	private String name;

	private PerformanceIndices(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

package com.portal.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceName = DynamicDataSourceHolder.getDataSourceName(); 
		return dataSourceName;
	}

}

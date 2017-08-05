package com.github.joaquimsn.querysearch.mock;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.github.joaquimsn.querysearch.AbstractSqlSearchFilter;
import com.github.joaquimsn.querysearch.template.TemplateObjectQueryConstruct;
import com.github.joaquimsn.querysearch.template.TemplateObjectQueryResult;

public class AddressFilterSql extends AbstractSqlSearchFilter {
	private static final long serialVersionUID = -7533052247645801370L;

	@Override
	public TemplateObjectQueryConstruct buildTemplateResult() {
		return new AddressTemplateObjectConstruct();
	}

	@Override
	public String queryFilter() {
		return "SELECT a.id as id, a.state as state, a.city as city, a.zipcode as zipcode, a.street_address as streetAddress  FROM ADDRESS a ";
	}

	@Override
	public String queryCount() {
		return "SELECT COUNT(*) FROM ADDRESS a";
	}
	
	// only exemple
	public class AddressTemplateObjectConstruct implements TemplateObjectQueryConstruct {
		
		@Override
		public TemplateObjectQueryResult create(Query query) {
			return new AddressTemplateObjectResult(query);
		}
		
		// Inner class only example
		public class AddressTemplateObjectResult implements TemplateObjectQueryResult {
			private Query query;
			
			public AddressTemplateObjectResult(Query query) {
				this.query = query;
			}

			@Override
			public Query build() {
				query.unwrap(SQLQuery.class)
					.addScalar("id", LongType.INSTANCE)
					.addScalar("state", StringType.INSTANCE)
					.addScalar("city", StringType.INSTANCE)
					.addScalar("zipcode", StringType.INSTANCE)
					.addScalar("streetAddress", StringType.INSTANCE);
				return query;
			}
			
		}
	}
}

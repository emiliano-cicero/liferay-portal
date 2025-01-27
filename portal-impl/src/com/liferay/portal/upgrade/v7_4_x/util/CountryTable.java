/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.upgrade.v7_4_x.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class CountryTable {

	public static final String TABLE_NAME = "Country";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"defaultLanguageId", Types.VARCHAR}, {"countryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"a2", Types.VARCHAR},
		{"a3", Types.VARCHAR}, {"active_", Types.BOOLEAN},
		{"billingAllowed", Types.BOOLEAN},
		{"groupFilterEnabled", Types.BOOLEAN}, {"idd_", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"number_", Types.VARCHAR},
		{"position", Types.DOUBLE}, {"shippingAllowed", Types.BOOLEAN},
		{"subjectToVAT", Types.BOOLEAN}, {"zipRequired", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("defaultLanguageId", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("countryId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("a2", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("a3", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("billingAllowed", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("groupFilterEnabled", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("idd_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("number_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("position", Types.DOUBLE);

TABLE_COLUMNS_MAP.put("shippingAllowed", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("subjectToVAT", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("zipRequired", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);

}
	public static final String TABLE_SQL_CREATE =
"create table Country (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,defaultLanguageId VARCHAR(75) null,countryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,a2 VARCHAR(75) null,a3 VARCHAR(75) null,active_ BOOLEAN,billingAllowed BOOLEAN,groupFilterEnabled BOOLEAN,idd_ VARCHAR(75) null,name VARCHAR(75) null,number_ VARCHAR(75) null,position DOUBLE,shippingAllowed BOOLEAN,subjectToVAT BOOLEAN,zipRequired BOOLEAN,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table Country";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_717B97E1 on Country (a2[$COLUMN_LENGTH:75$])",
		"create unique index IX_717B9BA2 on Country (a3[$COLUMN_LENGTH:75$])",
		"create index IX_25D734CD on Country (active_)",
		"create unique index IX_19DA007B on Country (name[$COLUMN_LENGTH:75$])",
		"create index IX_BEAF8B0 on Country (uuid_[$COLUMN_LENGTH:75$], companyId)"
	};

}
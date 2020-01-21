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

package com.liferay.depot.model.impl;

import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.model.DepotEntryModel;
import com.liferay.depot.model.DepotEntrySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DepotEntry service. Represents a row in the &quot;DepotEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DepotEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DepotEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DepotEntryImpl
 * @generated
 */
@JSON(strict = true)
public class DepotEntryModelImpl
	extends BaseModelImpl<DepotEntry> implements DepotEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a depot entry model instance should use the <code>DepotEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "DepotEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"depotEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("depotEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DepotEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,depotEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table DepotEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY depotEntry.depotEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DepotEntry.depotEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long DEPOTENTRYID_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static DepotEntry toModel(DepotEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		DepotEntry model = new DepotEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setDepotEntryId(soapModel.getDepotEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DepotEntry> toModels(DepotEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<DepotEntry> models = new ArrayList<DepotEntry>(soapModels.length);

		for (DepotEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public DepotEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _depotEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDepotEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _depotEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DepotEntry.class;
	}

	@Override
	public String getModelClassName() {
		return DepotEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DepotEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DepotEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DepotEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((DepotEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DepotEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DepotEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DepotEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DepotEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DepotEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DepotEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DepotEntry.class.getClassLoader(), DepotEntry.class,
			ModelWrapper.class);

		try {
			Constructor<DepotEntry> constructor =
				(Constructor<DepotEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<DepotEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DepotEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DepotEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DepotEntry, Object>>();
		Map<String, BiConsumer<DepotEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DepotEntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", DepotEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DepotEntry, Long>)DepotEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", DepotEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<DepotEntry, String>)DepotEntry::setUuid);
		attributeGetterFunctions.put(
			"depotEntryId", DepotEntry::getDepotEntryId);
		attributeSetterBiConsumers.put(
			"depotEntryId",
			(BiConsumer<DepotEntry, Long>)DepotEntry::setDepotEntryId);
		attributeGetterFunctions.put("groupId", DepotEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<DepotEntry, Long>)DepotEntry::setGroupId);
		attributeGetterFunctions.put("companyId", DepotEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DepotEntry, Long>)DepotEntry::setCompanyId);
		attributeGetterFunctions.put("userId", DepotEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<DepotEntry, Long>)DepotEntry::setUserId);
		attributeGetterFunctions.put("userName", DepotEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DepotEntry, String>)DepotEntry::setUserName);
		attributeGetterFunctions.put("createDate", DepotEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DepotEntry, Date>)DepotEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DepotEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DepotEntry, Date>)DepotEntry::setModifiedDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getDepotEntryId() {
		return _depotEntryId;
	}

	@Override
	public void setDepotEntryId(long depotEntryId) {
		_depotEntryId = depotEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(DepotEntry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DepotEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DepotEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DepotEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DepotEntryImpl depotEntryImpl = new DepotEntryImpl();

		depotEntryImpl.setMvccVersion(getMvccVersion());
		depotEntryImpl.setUuid(getUuid());
		depotEntryImpl.setDepotEntryId(getDepotEntryId());
		depotEntryImpl.setGroupId(getGroupId());
		depotEntryImpl.setCompanyId(getCompanyId());
		depotEntryImpl.setUserId(getUserId());
		depotEntryImpl.setUserName(getUserName());
		depotEntryImpl.setCreateDate(getCreateDate());
		depotEntryImpl.setModifiedDate(getModifiedDate());

		depotEntryImpl.resetOriginalValues();

		return depotEntryImpl;
	}

	@Override
	public int compareTo(DepotEntry depotEntry) {
		long primaryKey = depotEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DepotEntry)) {
			return false;
		}

		DepotEntry depotEntry = (DepotEntry)obj;

		long primaryKey = depotEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		DepotEntryModelImpl depotEntryModelImpl = this;

		depotEntryModelImpl._originalUuid = depotEntryModelImpl._uuid;

		depotEntryModelImpl._originalGroupId = depotEntryModelImpl._groupId;

		depotEntryModelImpl._setOriginalGroupId = false;

		depotEntryModelImpl._originalCompanyId = depotEntryModelImpl._companyId;

		depotEntryModelImpl._setOriginalCompanyId = false;

		depotEntryModelImpl._setModifiedDate = false;

		depotEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DepotEntry> toCacheModel() {
		DepotEntryCacheModel depotEntryCacheModel = new DepotEntryCacheModel();

		depotEntryCacheModel.mvccVersion = getMvccVersion();

		depotEntryCacheModel.uuid = getUuid();

		String uuid = depotEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			depotEntryCacheModel.uuid = null;
		}

		depotEntryCacheModel.depotEntryId = getDepotEntryId();

		depotEntryCacheModel.groupId = getGroupId();

		depotEntryCacheModel.companyId = getCompanyId();

		depotEntryCacheModel.userId = getUserId();

		depotEntryCacheModel.userName = getUserName();

		String userName = depotEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			depotEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			depotEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			depotEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			depotEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			depotEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return depotEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DepotEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DepotEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DepotEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DepotEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<DepotEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DepotEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DepotEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DepotEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DepotEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _depotEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private DepotEntry _escapedModel;

}
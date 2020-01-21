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

package com.liferay.layout.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.layout.model.LayoutClassedModelUsageModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LayoutClassedModelUsage service. Represents a row in the &quot;LayoutClassedModelUsage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutClassedModelUsageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutClassedModelUsageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutClassedModelUsageImpl
 * @generated
 */
public class LayoutClassedModelUsageModelImpl
	extends BaseModelImpl<LayoutClassedModelUsage>
	implements LayoutClassedModelUsageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout classed model usage model instance should use the <code>LayoutClassedModelUsage</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutClassedModelUsage";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"layoutClassedModelUsageId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"containerKey", Types.VARCHAR}, {"containerType", Types.BIGINT},
		{"plid", Types.BIGINT}, {"type_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutClassedModelUsageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("containerKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("containerType", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutClassedModelUsage (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,layoutClassedModelUsageId LONG not null primary key,groupId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,containerKey VARCHAR(200) null,containerType LONG,plid LONG,type_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table LayoutClassedModelUsage";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutClassedModelUsage.layoutClassedModelUsageId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutClassedModelUsage.layoutClassedModelUsageId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long CONTAINERKEY_COLUMN_BITMASK = 4L;

	public static final long CONTAINERTYPE_COLUMN_BITMASK = 8L;

	public static final long GROUPID_COLUMN_BITMASK = 16L;

	public static final long PLID_COLUMN_BITMASK = 32L;

	public static final long TYPE_COLUMN_BITMASK = 64L;

	public static final long UUID_COLUMN_BITMASK = 128L;

	public static final long LAYOUTCLASSEDMODELUSAGEID_COLUMN_BITMASK = 256L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public LayoutClassedModelUsageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutClassedModelUsageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutClassedModelUsageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutClassedModelUsageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutClassedModelUsage.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutClassedModelUsage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutClassedModelUsage, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutClassedModelUsage, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutClassedModelUsage, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutClassedModelUsage)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutClassedModelUsage, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutClassedModelUsage, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutClassedModelUsage)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutClassedModelUsage, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutClassedModelUsage, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutClassedModelUsage>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutClassedModelUsage.class.getClassLoader(),
			LayoutClassedModelUsage.class, ModelWrapper.class);

		try {
			Constructor<LayoutClassedModelUsage> constructor =
				(Constructor<LayoutClassedModelUsage>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutClassedModelUsage, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<LayoutClassedModelUsage, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutClassedModelUsage, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<LayoutClassedModelUsage, Object>>();
		Map<String, BiConsumer<LayoutClassedModelUsage, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<LayoutClassedModelUsage, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LayoutClassedModelUsage::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setMvccVersion);
		attributeGetterFunctions.put("uuid", LayoutClassedModelUsage::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<LayoutClassedModelUsage, String>)
				LayoutClassedModelUsage::setUuid);
		attributeGetterFunctions.put(
			"layoutClassedModelUsageId",
			LayoutClassedModelUsage::getLayoutClassedModelUsageId);
		attributeSetterBiConsumers.put(
			"layoutClassedModelUsageId",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setLayoutClassedModelUsageId);
		attributeGetterFunctions.put(
			"groupId", LayoutClassedModelUsage::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setGroupId);
		attributeGetterFunctions.put(
			"createDate", LayoutClassedModelUsage::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutClassedModelUsage, Date>)
				LayoutClassedModelUsage::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutClassedModelUsage::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutClassedModelUsage, Date>)
				LayoutClassedModelUsage::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", LayoutClassedModelUsage::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", LayoutClassedModelUsage::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setClassPK);
		attributeGetterFunctions.put(
			"containerKey", LayoutClassedModelUsage::getContainerKey);
		attributeSetterBiConsumers.put(
			"containerKey",
			(BiConsumer<LayoutClassedModelUsage, String>)
				LayoutClassedModelUsage::setContainerKey);
		attributeGetterFunctions.put(
			"containerType", LayoutClassedModelUsage::getContainerType);
		attributeSetterBiConsumers.put(
			"containerType",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setContainerType);
		attributeGetterFunctions.put("plid", LayoutClassedModelUsage::getPlid);
		attributeSetterBiConsumers.put(
			"plid",
			(BiConsumer<LayoutClassedModelUsage, Long>)
				LayoutClassedModelUsage::setPlid);
		attributeGetterFunctions.put("type", LayoutClassedModelUsage::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<LayoutClassedModelUsage, Integer>)
				LayoutClassedModelUsage::setType);
		attributeGetterFunctions.put(
			"lastPublishDate", LayoutClassedModelUsage::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<LayoutClassedModelUsage, Date>)
				LayoutClassedModelUsage::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

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

	@Override
	public long getLayoutClassedModelUsageId() {
		return _layoutClassedModelUsageId;
	}

	@Override
	public void setLayoutClassedModelUsageId(long layoutClassedModelUsageId) {
		_layoutClassedModelUsageId = layoutClassedModelUsageId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public String getContainerKey() {
		if (_containerKey == null) {
			return "";
		}
		else {
			return _containerKey;
		}
	}

	@Override
	public void setContainerKey(String containerKey) {
		_columnBitmask |= CONTAINERKEY_COLUMN_BITMASK;

		if (_originalContainerKey == null) {
			_originalContainerKey = _containerKey;
		}

		_containerKey = containerKey;
	}

	public String getOriginalContainerKey() {
		return GetterUtil.getString(_originalContainerKey);
	}

	@Override
	public long getContainerType() {
		return _containerType;
	}

	@Override
	public void setContainerType(long containerType) {
		_columnBitmask |= CONTAINERTYPE_COLUMN_BITMASK;

		if (!_setOriginalContainerType) {
			_setOriginalContainerType = true;

			_originalContainerType = _containerType;
		}

		_containerType = containerType;
	}

	public long getOriginalContainerType() {
		return _originalContainerType;
	}

	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		_columnBitmask |= PLID_COLUMN_BITMASK;

		if (!_setOriginalPlid) {
			_setOriginalPlid = true;

			_originalPlid = _plid;
		}

		_plid = plid;
	}

	public long getOriginalPlid() {
		return _originalPlid;
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, LayoutClassedModelUsage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutClassedModelUsage toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutClassedModelUsage>
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
		LayoutClassedModelUsageImpl layoutClassedModelUsageImpl =
			new LayoutClassedModelUsageImpl();

		layoutClassedModelUsageImpl.setMvccVersion(getMvccVersion());
		layoutClassedModelUsageImpl.setUuid(getUuid());
		layoutClassedModelUsageImpl.setLayoutClassedModelUsageId(
			getLayoutClassedModelUsageId());
		layoutClassedModelUsageImpl.setGroupId(getGroupId());
		layoutClassedModelUsageImpl.setCreateDate(getCreateDate());
		layoutClassedModelUsageImpl.setModifiedDate(getModifiedDate());
		layoutClassedModelUsageImpl.setClassNameId(getClassNameId());
		layoutClassedModelUsageImpl.setClassPK(getClassPK());
		layoutClassedModelUsageImpl.setContainerKey(getContainerKey());
		layoutClassedModelUsageImpl.setContainerType(getContainerType());
		layoutClassedModelUsageImpl.setPlid(getPlid());
		layoutClassedModelUsageImpl.setType(getType());
		layoutClassedModelUsageImpl.setLastPublishDate(getLastPublishDate());

		layoutClassedModelUsageImpl.resetOriginalValues();

		return layoutClassedModelUsageImpl;
	}

	@Override
	public int compareTo(LayoutClassedModelUsage layoutClassedModelUsage) {
		long primaryKey = layoutClassedModelUsage.getPrimaryKey();

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

		if (!(obj instanceof LayoutClassedModelUsage)) {
			return false;
		}

		LayoutClassedModelUsage layoutClassedModelUsage =
			(LayoutClassedModelUsage)obj;

		long primaryKey = layoutClassedModelUsage.getPrimaryKey();

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
		LayoutClassedModelUsageModelImpl layoutClassedModelUsageModelImpl =
			this;

		layoutClassedModelUsageModelImpl._originalUuid =
			layoutClassedModelUsageModelImpl._uuid;

		layoutClassedModelUsageModelImpl._originalGroupId =
			layoutClassedModelUsageModelImpl._groupId;

		layoutClassedModelUsageModelImpl._setOriginalGroupId = false;

		layoutClassedModelUsageModelImpl._setModifiedDate = false;

		layoutClassedModelUsageModelImpl._originalClassNameId =
			layoutClassedModelUsageModelImpl._classNameId;

		layoutClassedModelUsageModelImpl._setOriginalClassNameId = false;

		layoutClassedModelUsageModelImpl._originalClassPK =
			layoutClassedModelUsageModelImpl._classPK;

		layoutClassedModelUsageModelImpl._setOriginalClassPK = false;

		layoutClassedModelUsageModelImpl._originalContainerKey =
			layoutClassedModelUsageModelImpl._containerKey;

		layoutClassedModelUsageModelImpl._originalContainerType =
			layoutClassedModelUsageModelImpl._containerType;

		layoutClassedModelUsageModelImpl._setOriginalContainerType = false;

		layoutClassedModelUsageModelImpl._originalPlid =
			layoutClassedModelUsageModelImpl._plid;

		layoutClassedModelUsageModelImpl._setOriginalPlid = false;

		layoutClassedModelUsageModelImpl._originalType =
			layoutClassedModelUsageModelImpl._type;

		layoutClassedModelUsageModelImpl._setOriginalType = false;

		layoutClassedModelUsageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutClassedModelUsage> toCacheModel() {
		LayoutClassedModelUsageCacheModel layoutClassedModelUsageCacheModel =
			new LayoutClassedModelUsageCacheModel();

		layoutClassedModelUsageCacheModel.mvccVersion = getMvccVersion();

		layoutClassedModelUsageCacheModel.uuid = getUuid();

		String uuid = layoutClassedModelUsageCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutClassedModelUsageCacheModel.uuid = null;
		}

		layoutClassedModelUsageCacheModel.layoutClassedModelUsageId =
			getLayoutClassedModelUsageId();

		layoutClassedModelUsageCacheModel.groupId = getGroupId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutClassedModelUsageCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutClassedModelUsageCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutClassedModelUsageCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			layoutClassedModelUsageCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutClassedModelUsageCacheModel.classNameId = getClassNameId();

		layoutClassedModelUsageCacheModel.classPK = getClassPK();

		layoutClassedModelUsageCacheModel.containerKey = getContainerKey();

		String containerKey = layoutClassedModelUsageCacheModel.containerKey;

		if ((containerKey != null) && (containerKey.length() == 0)) {
			layoutClassedModelUsageCacheModel.containerKey = null;
		}

		layoutClassedModelUsageCacheModel.containerType = getContainerType();

		layoutClassedModelUsageCacheModel.plid = getPlid();

		layoutClassedModelUsageCacheModel.type = getType();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			layoutClassedModelUsageCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			layoutClassedModelUsageCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return layoutClassedModelUsageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutClassedModelUsage, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutClassedModelUsage, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutClassedModelUsage, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((LayoutClassedModelUsage)this));
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
		Map<String, Function<LayoutClassedModelUsage, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutClassedModelUsage, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutClassedModelUsage, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((LayoutClassedModelUsage)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, LayoutClassedModelUsage>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _layoutClassedModelUsageId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _containerKey;
	private String _originalContainerKey;
	private long _containerType;
	private long _originalContainerType;
	private boolean _setOriginalContainerType;
	private long _plid;
	private long _originalPlid;
	private boolean _setOriginalPlid;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private LayoutClassedModelUsage _escapedModel;

}
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

package com.liferay.layout.page.template.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureModel;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureSoap;
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
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the LayoutPageTemplateStructure service. Represents a row in the &quot;LayoutPageTemplateStructure&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutPageTemplateStructureModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPageTemplateStructureImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateStructureImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutPageTemplateStructureModelImpl
	extends BaseModelImpl<LayoutPageTemplateStructure>
	implements LayoutPageTemplateStructureModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout page template structure model instance should use the <code>LayoutPageTemplateStructure</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutPageTemplateStructure";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"layoutPageTemplateStructureId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutPageTemplateStructureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutPageTemplateStructure (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,layoutPageTemplateStructureId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table LayoutPageTemplateStructure";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutPageTemplateStructure.layoutPageTemplateStructureId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutPageTemplateStructure.layoutPageTemplateStructureId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long LAYOUTPAGETEMPLATESTRUCTUREID_COLUMN_BITMASK = 32L;

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
	public static LayoutPageTemplateStructure toModel(
		LayoutPageTemplateStructureSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		LayoutPageTemplateStructure model =
			new LayoutPageTemplateStructureImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setLayoutPageTemplateStructureId(
			soapModel.getLayoutPageTemplateStructureId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutPageTemplateStructure> toModels(
		LayoutPageTemplateStructureSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<LayoutPageTemplateStructure> models =
			new ArrayList<LayoutPageTemplateStructure>(soapModels.length);

		for (LayoutPageTemplateStructureSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public LayoutPageTemplateStructureModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutPageTemplateStructureId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutPageTemplateStructureId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPageTemplateStructureId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPageTemplateStructure.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPageTemplateStructure.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutPageTemplateStructure, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutPageTemplateStructure, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateStructure, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(LayoutPageTemplateStructure)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutPageTemplateStructure, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutPageTemplateStructure, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutPageTemplateStructure)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutPageTemplateStructure, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutPageTemplateStructure, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutPageTemplateStructure>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutPageTemplateStructure.class.getClassLoader(),
			LayoutPageTemplateStructure.class, ModelWrapper.class);

		try {
			Constructor<LayoutPageTemplateStructure> constructor =
				(Constructor<LayoutPageTemplateStructure>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<LayoutPageTemplateStructure, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<LayoutPageTemplateStructure, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutPageTemplateStructure, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<LayoutPageTemplateStructure, Object>>();
		Map<String, BiConsumer<LayoutPageTemplateStructure, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<LayoutPageTemplateStructure, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LayoutPageTemplateStructure::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setMvccVersion);
		attributeGetterFunctions.put(
			"uuid", LayoutPageTemplateStructure::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<LayoutPageTemplateStructure, String>)
				LayoutPageTemplateStructure::setUuid);
		attributeGetterFunctions.put(
			"layoutPageTemplateStructureId",
			LayoutPageTemplateStructure::getLayoutPageTemplateStructureId);
		attributeSetterBiConsumers.put(
			"layoutPageTemplateStructureId",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setLayoutPageTemplateStructureId);
		attributeGetterFunctions.put(
			"groupId", LayoutPageTemplateStructure::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setGroupId);
		attributeGetterFunctions.put(
			"companyId", LayoutPageTemplateStructure::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setCompanyId);
		attributeGetterFunctions.put(
			"userId", LayoutPageTemplateStructure::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setUserId);
		attributeGetterFunctions.put(
			"userName", LayoutPageTemplateStructure::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<LayoutPageTemplateStructure, String>)
				LayoutPageTemplateStructure::setUserName);
		attributeGetterFunctions.put(
			"createDate", LayoutPageTemplateStructure::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutPageTemplateStructure, Date>)
				LayoutPageTemplateStructure::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutPageTemplateStructure::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutPageTemplateStructure, Date>)
				LayoutPageTemplateStructure::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", LayoutPageTemplateStructure::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", LayoutPageTemplateStructure::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<LayoutPageTemplateStructure, Long>)
				LayoutPageTemplateStructure::setClassPK);

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
	public long getLayoutPageTemplateStructureId() {
		return _layoutPageTemplateStructureId;
	}

	@Override
	public void setLayoutPageTemplateStructureId(
		long layoutPageTemplateStructureId) {

		_layoutPageTemplateStructureId = layoutPageTemplateStructureId;
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

	@JSON
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

	@JSON
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
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				LayoutPageTemplateStructure.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutPageTemplateStructure.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutPageTemplateStructure toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutPageTemplateStructure>
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
		LayoutPageTemplateStructureImpl layoutPageTemplateStructureImpl =
			new LayoutPageTemplateStructureImpl();

		layoutPageTemplateStructureImpl.setMvccVersion(getMvccVersion());
		layoutPageTemplateStructureImpl.setUuid(getUuid());
		layoutPageTemplateStructureImpl.setLayoutPageTemplateStructureId(
			getLayoutPageTemplateStructureId());
		layoutPageTemplateStructureImpl.setGroupId(getGroupId());
		layoutPageTemplateStructureImpl.setCompanyId(getCompanyId());
		layoutPageTemplateStructureImpl.setUserId(getUserId());
		layoutPageTemplateStructureImpl.setUserName(getUserName());
		layoutPageTemplateStructureImpl.setCreateDate(getCreateDate());
		layoutPageTemplateStructureImpl.setModifiedDate(getModifiedDate());
		layoutPageTemplateStructureImpl.setClassNameId(getClassNameId());
		layoutPageTemplateStructureImpl.setClassPK(getClassPK());

		layoutPageTemplateStructureImpl.resetOriginalValues();

		return layoutPageTemplateStructureImpl;
	}

	@Override
	public int compareTo(
		LayoutPageTemplateStructure layoutPageTemplateStructure) {

		long primaryKey = layoutPageTemplateStructure.getPrimaryKey();

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

		if (!(obj instanceof LayoutPageTemplateStructure)) {
			return false;
		}

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			(LayoutPageTemplateStructure)obj;

		long primaryKey = layoutPageTemplateStructure.getPrimaryKey();

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
		LayoutPageTemplateStructureModelImpl
			layoutPageTemplateStructureModelImpl = this;

		layoutPageTemplateStructureModelImpl._originalUuid =
			layoutPageTemplateStructureModelImpl._uuid;

		layoutPageTemplateStructureModelImpl._originalGroupId =
			layoutPageTemplateStructureModelImpl._groupId;

		layoutPageTemplateStructureModelImpl._setOriginalGroupId = false;

		layoutPageTemplateStructureModelImpl._originalCompanyId =
			layoutPageTemplateStructureModelImpl._companyId;

		layoutPageTemplateStructureModelImpl._setOriginalCompanyId = false;

		layoutPageTemplateStructureModelImpl._setModifiedDate = false;

		layoutPageTemplateStructureModelImpl._originalClassNameId =
			layoutPageTemplateStructureModelImpl._classNameId;

		layoutPageTemplateStructureModelImpl._setOriginalClassNameId = false;

		layoutPageTemplateStructureModelImpl._originalClassPK =
			layoutPageTemplateStructureModelImpl._classPK;

		layoutPageTemplateStructureModelImpl._setOriginalClassPK = false;

		layoutPageTemplateStructureModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutPageTemplateStructure> toCacheModel() {
		LayoutPageTemplateStructureCacheModel
			layoutPageTemplateStructureCacheModel =
				new LayoutPageTemplateStructureCacheModel();

		layoutPageTemplateStructureCacheModel.mvccVersion = getMvccVersion();

		layoutPageTemplateStructureCacheModel.uuid = getUuid();

		String uuid = layoutPageTemplateStructureCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutPageTemplateStructureCacheModel.uuid = null;
		}

		layoutPageTemplateStructureCacheModel.layoutPageTemplateStructureId =
			getLayoutPageTemplateStructureId();

		layoutPageTemplateStructureCacheModel.groupId = getGroupId();

		layoutPageTemplateStructureCacheModel.companyId = getCompanyId();

		layoutPageTemplateStructureCacheModel.userId = getUserId();

		layoutPageTemplateStructureCacheModel.userName = getUserName();

		String userName = layoutPageTemplateStructureCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutPageTemplateStructureCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutPageTemplateStructureCacheModel.createDate =
				createDate.getTime();
		}
		else {
			layoutPageTemplateStructureCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutPageTemplateStructureCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			layoutPageTemplateStructureCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutPageTemplateStructureCacheModel.classNameId = getClassNameId();

		layoutPageTemplateStructureCacheModel.classPK = getClassPK();

		return layoutPageTemplateStructureCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutPageTemplateStructure, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutPageTemplateStructure, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateStructure, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(LayoutPageTemplateStructure)this));
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
		Map<String, Function<LayoutPageTemplateStructure, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutPageTemplateStructure, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPageTemplateStructure, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(LayoutPageTemplateStructure)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, LayoutPageTemplateStructure>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _layoutPageTemplateStructureId;
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
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _columnBitmask;
	private LayoutPageTemplateStructure _escapedModel;

}
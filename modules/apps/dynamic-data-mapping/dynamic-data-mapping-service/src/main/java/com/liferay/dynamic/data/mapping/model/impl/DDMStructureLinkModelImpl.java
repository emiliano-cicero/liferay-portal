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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.model.DDMStructureLinkModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMStructureLink service. Represents a row in the &quot;DDMStructureLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMStructureLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStructureLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLinkImpl
 * @generated
 */
public class DDMStructureLinkModelImpl
	extends BaseModelImpl<DDMStructureLink> implements DDMStructureLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm structure link model instance should use the <code>DDMStructureLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMStructureLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"structureLinkId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"structureId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMStructureLink (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,structureLinkId LONG not null,companyId LONG,classNameId LONG,classPK LONG,structureId LONG,primary key (structureLinkId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table DDMStructureLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmStructureLink.structureLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMStructureLink.structureLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long STRUCTUREID_COLUMN_BITMASK = 4L;

	public static final long STRUCTURELINKID_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public DDMStructureLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _structureLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStructureLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _structureLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMStructureLink.class;
	}

	@Override
	public String getModelClassName() {
		return DDMStructureLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMStructureLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMStructureLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStructureLink, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMStructureLink)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMStructureLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMStructureLink, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMStructureLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMStructureLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMStructureLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMStructureLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMStructureLink.class.getClassLoader(), DDMStructureLink.class,
			ModelWrapper.class);

		try {
			Constructor<DDMStructureLink> constructor =
				(Constructor<DDMStructureLink>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDMStructureLink, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMStructureLink, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMStructureLink, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<DDMStructureLink, Object>>();
		Map<String, BiConsumer<DDMStructureLink, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<DDMStructureLink, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMStructureLink::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMStructureLink, Long>)
				DDMStructureLink::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMStructureLink::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMStructureLink, Long>)
				DDMStructureLink::setCtCollectionId);
		attributeGetterFunctions.put(
			"structureLinkId", DDMStructureLink::getStructureLinkId);
		attributeSetterBiConsumers.put(
			"structureLinkId",
			(BiConsumer<DDMStructureLink, Long>)
				DDMStructureLink::setStructureLinkId);
		attributeGetterFunctions.put(
			"companyId", DDMStructureLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMStructureLink, Long>)DDMStructureLink::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", DDMStructureLink::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<DDMStructureLink, Long>)
				DDMStructureLink::setClassNameId);
		attributeGetterFunctions.put("classPK", DDMStructureLink::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<DDMStructureLink, Long>)DDMStructureLink::setClassPK);
		attributeGetterFunctions.put(
			"structureId", DDMStructureLink::getStructureId);
		attributeSetterBiConsumers.put(
			"structureId",
			(BiConsumer<DDMStructureLink, Long>)
				DDMStructureLink::setStructureId);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getStructureLinkId() {
		return _structureLinkId;
	}

	@Override
	public void setStructureLinkId(long structureLinkId) {
		_structureLinkId = structureLinkId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getStructureId() {
		return _structureId;
	}

	@Override
	public void setStructureId(long structureId) {
		_columnBitmask |= STRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalStructureId) {
			_setOriginalStructureId = true;

			_originalStructureId = _structureId;
		}

		_structureId = structureId;
	}

	public long getOriginalStructureId() {
		return _originalStructureId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMStructureLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMStructureLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMStructureLink>
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
		DDMStructureLinkImpl ddmStructureLinkImpl = new DDMStructureLinkImpl();

		ddmStructureLinkImpl.setMvccVersion(getMvccVersion());
		ddmStructureLinkImpl.setCtCollectionId(getCtCollectionId());
		ddmStructureLinkImpl.setStructureLinkId(getStructureLinkId());
		ddmStructureLinkImpl.setCompanyId(getCompanyId());
		ddmStructureLinkImpl.setClassNameId(getClassNameId());
		ddmStructureLinkImpl.setClassPK(getClassPK());
		ddmStructureLinkImpl.setStructureId(getStructureId());

		ddmStructureLinkImpl.resetOriginalValues();

		return ddmStructureLinkImpl;
	}

	@Override
	public int compareTo(DDMStructureLink ddmStructureLink) {
		long primaryKey = ddmStructureLink.getPrimaryKey();

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

		if (!(obj instanceof DDMStructureLink)) {
			return false;
		}

		DDMStructureLink ddmStructureLink = (DDMStructureLink)obj;

		long primaryKey = ddmStructureLink.getPrimaryKey();

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
		DDMStructureLinkModelImpl ddmStructureLinkModelImpl = this;

		ddmStructureLinkModelImpl._originalClassNameId =
			ddmStructureLinkModelImpl._classNameId;

		ddmStructureLinkModelImpl._setOriginalClassNameId = false;

		ddmStructureLinkModelImpl._originalClassPK =
			ddmStructureLinkModelImpl._classPK;

		ddmStructureLinkModelImpl._setOriginalClassPK = false;

		ddmStructureLinkModelImpl._originalStructureId =
			ddmStructureLinkModelImpl._structureId;

		ddmStructureLinkModelImpl._setOriginalStructureId = false;

		ddmStructureLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMStructureLink> toCacheModel() {
		DDMStructureLinkCacheModel ddmStructureLinkCacheModel =
			new DDMStructureLinkCacheModel();

		ddmStructureLinkCacheModel.mvccVersion = getMvccVersion();

		ddmStructureLinkCacheModel.ctCollectionId = getCtCollectionId();

		ddmStructureLinkCacheModel.structureLinkId = getStructureLinkId();

		ddmStructureLinkCacheModel.companyId = getCompanyId();

		ddmStructureLinkCacheModel.classNameId = getClassNameId();

		ddmStructureLinkCacheModel.classPK = getClassPK();

		ddmStructureLinkCacheModel.structureId = getStructureId();

		return ddmStructureLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMStructureLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMStructureLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStructureLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DDMStructureLink)this));
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
		Map<String, Function<DDMStructureLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMStructureLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStructureLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DDMStructureLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMStructureLink>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _structureLinkId;
	private long _companyId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _structureId;
	private long _originalStructureId;
	private boolean _setOriginalStructureId;
	private long _columnBitmask;
	private DDMStructureLink _escapedModel;

}
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

package com.liferay.asset.list.service.base;

import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.asset.list.service.persistence.AssetListEntrySegmentsEntryRelPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the asset list entry segments entry rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.asset.list.service.impl.AssetListEntrySegmentsEntryRelLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.list.service.impl.AssetListEntrySegmentsEntryRelLocalServiceImpl
 * @generated
 */
public abstract class AssetListEntrySegmentsEntryRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, AssetListEntrySegmentsEntryRelLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetListEntrySegmentsEntryRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the asset list entry segments entry rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntrySegmentsEntryRel the asset list entry segments entry rel
	 * @return the asset list entry segments entry rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetListEntrySegmentsEntryRel addAssetListEntrySegmentsEntryRel(
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel) {

		assetListEntrySegmentsEntryRel.setNew(true);

		return assetListEntrySegmentsEntryRelPersistence.update(
			assetListEntrySegmentsEntryRel);
	}

	/**
	 * Creates a new asset list entry segments entry rel with the primary key. Does not add the asset list entry segments entry rel to the database.
	 *
	 * @param assetListEntrySegmentsEntryRelId the primary key for the new asset list entry segments entry rel
	 * @return the new asset list entry segments entry rel
	 */
	@Override
	@Transactional(enabled = false)
	public AssetListEntrySegmentsEntryRel createAssetListEntrySegmentsEntryRel(
		long assetListEntrySegmentsEntryRelId) {

		return assetListEntrySegmentsEntryRelPersistence.create(
			assetListEntrySegmentsEntryRelId);
	}

	/**
	 * Deletes the asset list entry segments entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntrySegmentsEntryRelId the primary key of the asset list entry segments entry rel
	 * @return the asset list entry segments entry rel that was removed
	 * @throws PortalException if a asset list entry segments entry rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetListEntrySegmentsEntryRel deleteAssetListEntrySegmentsEntryRel(
			long assetListEntrySegmentsEntryRelId)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.remove(
			assetListEntrySegmentsEntryRelId);
	}

	/**
	 * Deletes the asset list entry segments entry rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntrySegmentsEntryRel the asset list entry segments entry rel
	 * @return the asset list entry segments entry rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetListEntrySegmentsEntryRel deleteAssetListEntrySegmentsEntryRel(
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel) {

		return assetListEntrySegmentsEntryRelPersistence.remove(
			assetListEntrySegmentsEntryRel);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AssetListEntrySegmentsEntryRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetListEntrySegmentsEntryRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntrySegmentsEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return assetListEntrySegmentsEntryRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntrySegmentsEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return assetListEntrySegmentsEntryRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return assetListEntrySegmentsEntryRelPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return assetListEntrySegmentsEntryRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AssetListEntrySegmentsEntryRel fetchAssetListEntrySegmentsEntryRel(
		long assetListEntrySegmentsEntryRelId) {

		return assetListEntrySegmentsEntryRelPersistence.fetchByPrimaryKey(
			assetListEntrySegmentsEntryRelId);
	}

	/**
	 * Returns the asset list entry segments entry rel matching the UUID and group.
	 *
	 * @param uuid the asset list entry segments entry rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset list entry segments entry rel, or <code>null</code> if a matching asset list entry segments entry rel could not be found
	 */
	@Override
	public AssetListEntrySegmentsEntryRel
		fetchAssetListEntrySegmentsEntryRelByUuidAndGroupId(
			String uuid, long groupId) {

		return assetListEntrySegmentsEntryRelPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the asset list entry segments entry rel with the primary key.
	 *
	 * @param assetListEntrySegmentsEntryRelId the primary key of the asset list entry segments entry rel
	 * @return the asset list entry segments entry rel
	 * @throws PortalException if a asset list entry segments entry rel with the primary key could not be found
	 */
	@Override
	public AssetListEntrySegmentsEntryRel getAssetListEntrySegmentsEntryRel(
			long assetListEntrySegmentsEntryRelId)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.findByPrimaryKey(
			assetListEntrySegmentsEntryRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			assetListEntrySegmentsEntryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			AssetListEntrySegmentsEntryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetListEntrySegmentsEntryRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			assetListEntrySegmentsEntryRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AssetListEntrySegmentsEntryRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetListEntrySegmentsEntryRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			assetListEntrySegmentsEntryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			AssetListEntrySegmentsEntryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetListEntrySegmentsEntryRelId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<AssetListEntrySegmentsEntryRel>() {

				@Override
				public void performAction(
						AssetListEntrySegmentsEntryRel
							assetListEntrySegmentsEntryRel)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, assetListEntrySegmentsEntryRel);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					AssetListEntrySegmentsEntryRel.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return assetListEntrySegmentsEntryRelLocalService.
			deleteAssetListEntrySegmentsEntryRel(
				(AssetListEntrySegmentsEntryRel)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the asset list entry segments entry rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset list entry segments entry rels
	 * @param companyId the primary key of the company
	 * @return the matching asset list entry segments entry rels, or an empty list if no matches were found
	 */
	@Override
	public List<AssetListEntrySegmentsEntryRel>
		getAssetListEntrySegmentsEntryRelsByUuidAndCompanyId(
			String uuid, long companyId) {

		return assetListEntrySegmentsEntryRelPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of asset list entry segments entry rels matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset list entry segments entry rels
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of asset list entry segments entry rels
	 * @param end the upper bound of the range of asset list entry segments entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching asset list entry segments entry rels, or an empty list if no matches were found
	 */
	@Override
	public List<AssetListEntrySegmentsEntryRel>
		getAssetListEntrySegmentsEntryRelsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<AssetListEntrySegmentsEntryRel>
				orderByComparator) {

		return assetListEntrySegmentsEntryRelPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the asset list entry segments entry rel matching the UUID and group.
	 *
	 * @param uuid the asset list entry segments entry rel's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset list entry segments entry rel
	 * @throws PortalException if a matching asset list entry segments entry rel could not be found
	 */
	@Override
	public AssetListEntrySegmentsEntryRel
			getAssetListEntrySegmentsEntryRelByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return assetListEntrySegmentsEntryRelPersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the asset list entry segments entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.list.model.impl.AssetListEntrySegmentsEntryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset list entry segments entry rels
	 * @param end the upper bound of the range of asset list entry segments entry rels (not inclusive)
	 * @return the range of asset list entry segments entry rels
	 */
	@Override
	public List<AssetListEntrySegmentsEntryRel>
		getAssetListEntrySegmentsEntryRels(int start, int end) {

		return assetListEntrySegmentsEntryRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset list entry segments entry rels.
	 *
	 * @return the number of asset list entry segments entry rels
	 */
	@Override
	public int getAssetListEntrySegmentsEntryRelsCount() {
		return assetListEntrySegmentsEntryRelPersistence.countAll();
	}

	/**
	 * Updates the asset list entry segments entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetListEntrySegmentsEntryRel the asset list entry segments entry rel
	 * @return the asset list entry segments entry rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetListEntrySegmentsEntryRel updateAssetListEntrySegmentsEntryRel(
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel) {

		return assetListEntrySegmentsEntryRelPersistence.update(
			assetListEntrySegmentsEntryRel);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AssetListEntrySegmentsEntryRelLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		assetListEntrySegmentsEntryRelLocalService =
			(AssetListEntrySegmentsEntryRelLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetListEntrySegmentsEntryRelLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetListEntrySegmentsEntryRel.class;
	}

	protected String getModelClassName() {
		return AssetListEntrySegmentsEntryRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				assetListEntrySegmentsEntryRelPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected AssetListEntrySegmentsEntryRelLocalService
		assetListEntrySegmentsEntryRelLocalService;

	@Reference
	protected AssetListEntrySegmentsEntryRelPersistence
		assetListEntrySegmentsEntryRelPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}
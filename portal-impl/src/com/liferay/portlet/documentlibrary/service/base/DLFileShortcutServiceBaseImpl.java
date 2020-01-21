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

package com.liferay.portlet.documentlibrary.service.base;

import com.liferay.asset.kernel.service.persistence.AssetEntryFinder;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetTagFinder;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.service.DLFileShortcutService;
import com.liferay.document.library.kernel.service.persistence.DLFileShortcutPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFolderFinder;
import com.liferay.document.library.kernel.service.persistence.DLFolderPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.kernel.service.persistence.TrashEntryPersistence;
import com.liferay.trash.kernel.service.persistence.TrashVersionPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the document library file shortcut remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.documentlibrary.service.impl.DLFileShortcutServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.documentlibrary.service.impl.DLFileShortcutServiceImpl
 * @generated
 */
public abstract class DLFileShortcutServiceBaseImpl
	extends BaseServiceImpl
	implements DLFileShortcutService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DLFileShortcutService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.document.library.kernel.service.DLFileShortcutServiceUtil</code>.
	 */

	/**
	 * Returns the document library file shortcut local service.
	 *
	 * @return the document library file shortcut local service
	 */
	public
		com.liferay.document.library.kernel.service.DLFileShortcutLocalService
			getDLFileShortcutLocalService() {

		return dlFileShortcutLocalService;
	}

	/**
	 * Sets the document library file shortcut local service.
	 *
	 * @param dlFileShortcutLocalService the document library file shortcut local service
	 */
	public void setDLFileShortcutLocalService(
		com.liferay.document.library.kernel.service.DLFileShortcutLocalService
			dlFileShortcutLocalService) {

		this.dlFileShortcutLocalService = dlFileShortcutLocalService;
	}

	/**
	 * Returns the document library file shortcut remote service.
	 *
	 * @return the document library file shortcut remote service
	 */
	public DLFileShortcutService getDLFileShortcutService() {
		return dlFileShortcutService;
	}

	/**
	 * Sets the document library file shortcut remote service.
	 *
	 * @param dlFileShortcutService the document library file shortcut remote service
	 */
	public void setDLFileShortcutService(
		DLFileShortcutService dlFileShortcutService) {

		this.dlFileShortcutService = dlFileShortcutService;
	}

	/**
	 * Returns the document library file shortcut persistence.
	 *
	 * @return the document library file shortcut persistence
	 */
	public DLFileShortcutPersistence getDLFileShortcutPersistence() {
		return dlFileShortcutPersistence;
	}

	/**
	 * Sets the document library file shortcut persistence.
	 *
	 * @param dlFileShortcutPersistence the document library file shortcut persistence
	 */
	public void setDLFileShortcutPersistence(
		DLFileShortcutPersistence dlFileShortcutPersistence) {

		this.dlFileShortcutPersistence = dlFileShortcutPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService
		getAssetEntryLocalService() {

		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService
			assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.asset.kernel.service.AssetEntryService
		getAssetEntryService() {

		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.asset.kernel.service.AssetEntryService assetEntryService) {

		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {

		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.asset.kernel.service.AssetTagLocalService
		getAssetTagLocalService() {

		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.asset.kernel.service.AssetTagLocalService
			assetTagLocalService) {

		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag remote service.
	 *
	 * @return the asset tag remote service
	 */
	public com.liferay.asset.kernel.service.AssetTagService
		getAssetTagService() {

		return assetTagService;
	}

	/**
	 * Sets the asset tag remote service.
	 *
	 * @param assetTagService the asset tag remote service
	 */
	public void setAssetTagService(
		com.liferay.asset.kernel.service.AssetTagService assetTagService) {

		this.assetTagService = assetTagService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(
		AssetTagPersistence assetTagPersistence) {

		this.assetTagPersistence = assetTagPersistence;
	}

	/**
	 * Returns the asset tag finder.
	 *
	 * @return the asset tag finder
	 */
	public AssetTagFinder getAssetTagFinder() {
		return assetTagFinder;
	}

	/**
	 * Sets the asset tag finder.
	 *
	 * @param assetTagFinder the asset tag finder
	 */
	public void setAssetTagFinder(AssetTagFinder assetTagFinder) {
		this.assetTagFinder = assetTagFinder;
	}

	/**
	 * Returns the dl app local service.
	 *
	 * @return the dl app local service
	 */
	public com.liferay.document.library.kernel.service.DLAppLocalService
		getDLAppLocalService() {

		return dlAppLocalService;
	}

	/**
	 * Sets the dl app local service.
	 *
	 * @param dlAppLocalService the dl app local service
	 */
	public void setDLAppLocalService(
		com.liferay.document.library.kernel.service.DLAppLocalService
			dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}

	/**
	 * Returns the dl app remote service.
	 *
	 * @return the dl app remote service
	 */
	public com.liferay.document.library.kernel.service.DLAppService
		getDLAppService() {

		return dlAppService;
	}

	/**
	 * Sets the dl app remote service.
	 *
	 * @param dlAppService the dl app remote service
	 */
	public void setDLAppService(
		com.liferay.document.library.kernel.service.DLAppService dlAppService) {

		this.dlAppService = dlAppService;
	}

	/**
	 * Returns the trash entry local service.
	 *
	 * @return the trash entry local service
	 */
	@SuppressWarnings("deprecation")
	public com.liferay.trash.kernel.service.TrashEntryLocalService
		getTrashEntryLocalService() {

		return trashEntryLocalService;
	}

	/**
	 * Sets the trash entry local service.
	 *
	 * @param trashEntryLocalService the trash entry local service
	 */
	@SuppressWarnings("deprecation")
	public void setTrashEntryLocalService(
		com.liferay.trash.kernel.service.TrashEntryLocalService
			trashEntryLocalService) {

		this.trashEntryLocalService = trashEntryLocalService;
	}

	/**
	 * Returns the trash entry remote service.
	 *
	 * @return the trash entry remote service
	 */
	@SuppressWarnings("deprecation")
	public com.liferay.trash.kernel.service.TrashEntryService
		getTrashEntryService() {

		return trashEntryService;
	}

	/**
	 * Sets the trash entry remote service.
	 *
	 * @param trashEntryService the trash entry remote service
	 */
	@SuppressWarnings("deprecation")
	public void setTrashEntryService(
		com.liferay.trash.kernel.service.TrashEntryService trashEntryService) {

		this.trashEntryService = trashEntryService;
	}

	/**
	 * Returns the trash entry persistence.
	 *
	 * @return the trash entry persistence
	 */
	public TrashEntryPersistence getTrashEntryPersistence() {
		return trashEntryPersistence;
	}

	/**
	 * Sets the trash entry persistence.
	 *
	 * @param trashEntryPersistence the trash entry persistence
	 */
	public void setTrashEntryPersistence(
		TrashEntryPersistence trashEntryPersistence) {

		this.trashEntryPersistence = trashEntryPersistence;
	}

	/**
	 * Returns the trash version local service.
	 *
	 * @return the trash version local service
	 */
	@SuppressWarnings("deprecation")
	public com.liferay.trash.kernel.service.TrashVersionLocalService
		getTrashVersionLocalService() {

		return trashVersionLocalService;
	}

	/**
	 * Sets the trash version local service.
	 *
	 * @param trashVersionLocalService the trash version local service
	 */
	@SuppressWarnings("deprecation")
	public void setTrashVersionLocalService(
		com.liferay.trash.kernel.service.TrashVersionLocalService
			trashVersionLocalService) {

		this.trashVersionLocalService = trashVersionLocalService;
	}

	/**
	 * Returns the trash version persistence.
	 *
	 * @return the trash version persistence
	 */
	public TrashVersionPersistence getTrashVersionPersistence() {
		return trashVersionPersistence;
	}

	/**
	 * Sets the trash version persistence.
	 *
	 * @param trashVersionPersistence the trash version persistence
	 */
	public void setTrashVersionPersistence(
		TrashVersionPersistence trashVersionPersistence) {

		this.trashVersionPersistence = trashVersionPersistence;
	}

	/**
	 * Returns the document library folder local service.
	 *
	 * @return the document library folder local service
	 */
	public com.liferay.document.library.kernel.service.DLFolderLocalService
		getDLFolderLocalService() {

		return dlFolderLocalService;
	}

	/**
	 * Sets the document library folder local service.
	 *
	 * @param dlFolderLocalService the document library folder local service
	 */
	public void setDLFolderLocalService(
		com.liferay.document.library.kernel.service.DLFolderLocalService
			dlFolderLocalService) {

		this.dlFolderLocalService = dlFolderLocalService;
	}

	/**
	 * Returns the document library folder remote service.
	 *
	 * @return the document library folder remote service
	 */
	public com.liferay.document.library.kernel.service.DLFolderService
		getDLFolderService() {

		return dlFolderService;
	}

	/**
	 * Sets the document library folder remote service.
	 *
	 * @param dlFolderService the document library folder remote service
	 */
	public void setDLFolderService(
		com.liferay.document.library.kernel.service.DLFolderService
			dlFolderService) {

		this.dlFolderService = dlFolderService;
	}

	/**
	 * Returns the document library folder persistence.
	 *
	 * @return the document library folder persistence
	 */
	public DLFolderPersistence getDLFolderPersistence() {
		return dlFolderPersistence;
	}

	/**
	 * Sets the document library folder persistence.
	 *
	 * @param dlFolderPersistence the document library folder persistence
	 */
	public void setDLFolderPersistence(
		DLFolderPersistence dlFolderPersistence) {

		this.dlFolderPersistence = dlFolderPersistence;
	}

	/**
	 * Returns the document library folder finder.
	 *
	 * @return the document library folder finder
	 */
	public DLFolderFinder getDLFolderFinder() {
		return dlFolderFinder;
	}

	/**
	 * Sets the document library folder finder.
	 *
	 * @param dlFolderFinder the document library folder finder
	 */
	public void setDLFolderFinder(DLFolderFinder dlFolderFinder) {
		this.dlFolderFinder = dlFolderFinder;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DLFileShortcutService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DLFileShortcut.class;
	}

	protected String getModelClassName() {
		return DLFileShortcut.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dlFileShortcutPersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.document.library.kernel.service.DLFileShortcutLocalService.class
	)
	protected
		com.liferay.document.library.kernel.service.DLFileShortcutLocalService
			dlFileShortcutLocalService;

	@BeanReference(type = DLFileShortcutService.class)
	protected DLFileShortcutService dlFileShortcutService;

	@BeanReference(type = DLFileShortcutPersistence.class)
	protected DLFileShortcutPersistence dlFileShortcutPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetEntryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetEntryService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryService
		assetEntryService;

	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;

	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetTagLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetTagService.class
	)
	protected com.liferay.asset.kernel.service.AssetTagService assetTagService;

	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;

	@BeanReference(type = AssetTagFinder.class)
	protected AssetTagFinder assetTagFinder;

	@BeanReference(
		type = com.liferay.document.library.kernel.service.DLAppLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@BeanReference(
		type = com.liferay.document.library.kernel.service.DLAppService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppService
		dlAppService;

	@BeanReference(
		type = com.liferay.trash.kernel.service.TrashEntryLocalService.class
	)
	@SuppressWarnings("deprecation")
	protected com.liferay.trash.kernel.service.TrashEntryLocalService
		trashEntryLocalService;

	@BeanReference(
		type = com.liferay.trash.kernel.service.TrashEntryService.class
	)
	@SuppressWarnings("deprecation")
	protected com.liferay.trash.kernel.service.TrashEntryService
		trashEntryService;

	@BeanReference(type = TrashEntryPersistence.class)
	protected TrashEntryPersistence trashEntryPersistence;

	@BeanReference(
		type = com.liferay.trash.kernel.service.TrashVersionLocalService.class
	)
	@SuppressWarnings("deprecation")
	protected com.liferay.trash.kernel.service.TrashVersionLocalService
		trashVersionLocalService;

	@BeanReference(type = TrashVersionPersistence.class)
	protected TrashVersionPersistence trashVersionPersistence;

	@BeanReference(
		type = com.liferay.document.library.kernel.service.DLFolderLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLFolderLocalService
		dlFolderLocalService;

	@BeanReference(
		type = com.liferay.document.library.kernel.service.DLFolderService.class
	)
	protected com.liferay.document.library.kernel.service.DLFolderService
		dlFolderService;

	@BeanReference(type = DLFolderPersistence.class)
	protected DLFolderPersistence dlFolderPersistence;

	@BeanReference(type = DLFolderFinder.class)
	protected DLFolderFinder dlFolderFinder;

}
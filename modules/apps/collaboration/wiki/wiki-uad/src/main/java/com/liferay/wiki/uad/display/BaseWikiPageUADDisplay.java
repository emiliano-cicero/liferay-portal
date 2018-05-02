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

package com.liferay.wiki.uad.display;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;

import com.liferay.user.associated.data.display.BaseModelUADDisplay;

import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalService;
import com.liferay.wiki.uad.constants.WikiUADConstants;

import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;

/**
 * Provides the base implementation for the WikiPage UAD display.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link WikiPageUADDisplay}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseWikiPageUADDisplay extends BaseModelUADDisplay<WikiPage> {
	@Override
	public WikiPage get(Serializable primaryKey) throws PortalException {
		return wikiPageLocalService.getWikiPage(Long.valueOf(
				primaryKey.toString()));
	}

	@Override
	public String getApplicationName() {
		return WikiUADConstants.APPLICATION_NAME;
	}

	@Override
	public String[] getDisplayFieldNames() {
		return new String[] { "title", "content", "summary" };
	}

	@Override
	public String getKey() {
		return WikiUADConstants.CLASS_NAME_WIKI_PAGE;
	}

	@Override
	public String getTypeName(Locale locale) {
		return "WikiPage";
	}

	@Override
	protected long doCount(DynamicQuery dynamicQuery) {
		return wikiPageLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	protected DynamicQuery doGetDynamicQuery() {
		return wikiPageLocalService.dynamicQuery();
	}

	@Override
	protected List<WikiPage> doGetRange(DynamicQuery dynamicQuery, int start,
		int end) {
		return wikiPageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return WikiUADConstants.USER_ID_FIELD_NAMES_WIKI_PAGE;
	}

	@Reference
	protected WikiPageLocalService wikiPageLocalService;
}
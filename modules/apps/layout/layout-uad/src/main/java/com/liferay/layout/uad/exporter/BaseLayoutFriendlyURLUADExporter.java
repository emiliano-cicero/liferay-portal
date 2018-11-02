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

package com.liferay.layout.uad.exporter;

import com.liferay.layout.uad.constants.LayoutUADConstants;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout friendly url UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link LayoutFriendlyURLUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseLayoutFriendlyURLUADExporter
	extends DynamicQueryUADExporter<LayoutFriendlyURL> {
	@Override
	public Class<LayoutFriendlyURL> getTypeClass() {
		return LayoutFriendlyURL.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return layoutFriendlyURLLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return LayoutUADConstants.USER_ID_FIELD_NAMES_LAYOUT_FRIENDLY_URL;
	}

	@Override
	protected String toXmlString(LayoutFriendlyURL layoutFriendlyURL) {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.LayoutFriendlyURL");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>layoutFriendlyURLId</column-name><column-value><![CDATA[");
		sb.append(layoutFriendlyURL.getLayoutFriendlyURLId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(layoutFriendlyURL.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(layoutFriendlyURL.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected LayoutFriendlyURLLocalService layoutFriendlyURLLocalService;
}
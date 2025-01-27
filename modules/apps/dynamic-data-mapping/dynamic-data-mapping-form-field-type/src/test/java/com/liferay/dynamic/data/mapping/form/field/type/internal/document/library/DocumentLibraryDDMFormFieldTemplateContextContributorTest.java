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

package com.liferay.dynamic.data.mapping.form.field.type.internal.document.library;

import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletURL;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.util.HtmlImpl;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.CoreMatchers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.expectation.PowerMockitoStubber;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Pedro Queiroz
 */
@PrepareForTest(RequestBackedPortletURLFactoryUtil.class)
@RunWith(PowerMockRunner.class)
public class DocumentLibraryDDMFormFieldTemplateContextContributorTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	public HttpServletRequest createHttpServletRequest() {
		MockHttpServletRequest httpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setPathContext("/my/path/context/");
		themeDisplay.setPathThemeImages("/my/theme/images/");

		httpServletRequest.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		httpServletRequest.setParameter("formInstanceId", "12345");

		return httpServletRequest;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setUpDLAppService();
		setUpFileEntry();
		setUpJSONFactory();
		setUpHtml();
		setUpParamUtil();
		setUpRequestBackedPortletURLFactoryUtil();
	}

	@Test
	public void testGetParametersShouldContainFileEntryURL() {
		DDMFormField ddmFormField = new DDMFormField("field", "numeric");

		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy();

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setHttpServletRequest(
			createHttpServletRequest());
		ddmFormFieldRenderingContext.setProperty("groupId", 12345);
		ddmFormFieldRenderingContext.setReadOnly(true);
		ddmFormFieldRenderingContext.setValue(
			"{\"uuid\": \"0000-1111\", \"title\": \"File Title\"}");

		Map<String, Object> parameters = spy.getParameters(
			ddmFormField, ddmFormFieldRenderingContext);

		Assert.assertTrue(parameters.containsKey("fileEntryURL"));
	}

	@Test
	public void testGetParametersShouldContainItemSelectorAuthToken() {
		DDMFormField ddmFormField = new DDMFormField("field", "numeric");

		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy();

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setHttpServletRequest(
			createHttpServletRequest());
		ddmFormFieldRenderingContext.setValue(
			"{\"uuid\": \"0000-1111\", \"title\": \"Title\"}");

		Map<String, Object> parameters = spy.getParameters(
			ddmFormField, ddmFormFieldRenderingContext);

		Assert.assertEquals("token", parameters.get("itemSelectorAuthToken"));
	}

	@Test
	public void testGetParametersShouldContainUploadURL() {
		DDMFormField ddmFormField = new DDMFormField(
			"field", "document_library");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setHttpServletRequest(
			createHttpServletRequest());
		ddmFormFieldRenderingContext.setProperty("groupId", 23456);

		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy();

		Map<String, Object> parameters = spy.getParameters(
			ddmFormField, ddmFormFieldRenderingContext);

		String uploadURL = String.valueOf(parameters.get("uploadURL"));

		Assert.assertThat(
			uploadURL,
			CoreMatchers.containsString(
				"param_javax.portlet.action=/dynamic_data_mapping_form" +
					"/upload_file_entry"));
		Assert.assertThat(
			uploadURL,
			CoreMatchers.containsString("param_formInstanceId=12345"));
		Assert.assertThat(
			uploadURL, CoreMatchers.containsString("param_groupId=23456"));
	}

	@Test
	public void testGetParametersShouldUseFileEntryTitle() {
		DDMFormField ddmFormField = new DDMFormField("field", "numeric");

		DocumentLibraryDDMFormFieldTemplateContextContributor spy = createSpy();

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setHttpServletRequest(
			createHttpServletRequest());
		ddmFormFieldRenderingContext.setReadOnly(true);
		ddmFormFieldRenderingContext.setValue(
			"{\"uuid\": \"0000-1111\", \"title\": \"Old Title\"}");

		Map<String, Object> parameters = spy.getParameters(
			ddmFormField, ddmFormFieldRenderingContext);

		Assert.assertEquals("New Title", parameters.get("fileEntryTitle"));
	}

	protected DocumentLibraryDDMFormFieldTemplateContextContributor
		createSpy() {

		DocumentLibraryDDMFormFieldTemplateContextContributor spy =
			PowerMockito.spy(
				_documentLibraryDDMFormFieldTemplateContextContributor);

		PowerMockitoStubber stubber = PowerMockito.doReturn(_resourceBundle);

		stubber.when(
			spy
		).getResourceBundle(
			Matchers.any(Locale.class)
		);

		stubber = PowerMockito.doReturn("token");

		stubber.when(
			spy
		).getItemSelectorAuthToken(
			Matchers.any(HttpServletRequest.class)
		);

		ThemeDisplay themeDisplay = new ThemeDisplay();

		User user = new UserImpl();

		user.setDefaultUser(true);

		themeDisplay.setUser(user);

		stubber = PowerMockito.doReturn(themeDisplay);

		stubber.when(
			spy
		).getThemeDisplay(
			Matchers.any(HttpServletRequest.class)
		);

		return spy;
	}

	protected RequestBackedPortletURLFactory
		mockRequestBackedPortletURLFactory() {

		RequestBackedPortletURLFactory requestBackedPortletURLFactory = mock(
			RequestBackedPortletURLFactory.class);

		when(
			requestBackedPortletURLFactory.createActionURL(
				DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM)
		).thenReturn(
			new MockLiferayPortletURL()
		);

		return requestBackedPortletURLFactory;
	}

	protected void setUpDLAppService() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class,
			"dlAppService"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor,
			_dlAppService
		);

		PowerMockito.when(
			_dlAppService.getFileEntryByUuidAndGroupId(
				Matchers.anyString(), Matchers.anyLong())
		).thenReturn(
			_fileEntry
		);
	}

	protected void setUpFileEntry() {
		_fileEntry.setUuid("0000-1111");
		_fileEntry.setGroupId(12345);

		PowerMockito.when(
			_fileEntry.getTitle()
		).thenReturn(
			"New Title"
		);
	}

	protected void setUpHtml() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class, "html"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor, _html
		);
	}

	protected void setUpJSONFactory() throws Exception {
		MemberMatcher.field(
			DocumentLibraryDDMFormFieldTemplateContextContributor.class,
			"jsonFactory"
		).set(
			_documentLibraryDDMFormFieldTemplateContextContributor, _jsonFactory
		);
	}

	protected void setUpParamUtil() {
		PropsUtil.setProps(Mockito.mock(Props.class));
	}

	protected void setUpRequestBackedPortletURLFactoryUtil() {
		PowerMockito.mockStatic(RequestBackedPortletURLFactoryUtil.class);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			mockRequestBackedPortletURLFactory();

		PowerMockito.when(
			RequestBackedPortletURLFactoryUtil.create(
				Matchers.any(HttpServletRequest.class))
		).thenReturn(
			requestBackedPortletURLFactory
		);
	}

	@Mock
	private DLAppService _dlAppService;

	private final DocumentLibraryDDMFormFieldTemplateContextContributor
		_documentLibraryDDMFormFieldTemplateContextContributor =
			new DocumentLibraryDDMFormFieldTemplateContextContributor();

	@Mock
	private FileEntry _fileEntry;

	private final Html _html = new HtmlImpl();
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

	@Mock
	private ResourceBundle _resourceBundle;

}
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCache;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.auth.EmailAddressGenerator;
import com.liferay.portal.security.auth.EmailAddressGeneratorFactory;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.social.model.SocialEquityValue;
import com.liferay.portlet.social.service.SocialEquityUserLocalServiceUtil;
import com.liferay.util.UniqueList;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Wesley Gong
 */
public class UserImpl extends UserModelImpl implements User {

	public UserImpl() {
	}

	public Date getBirthday() throws PortalException, SystemException {
		return getContact().getBirthday();
	}

	public String getCompanyMx() throws PortalException, SystemException {
		Company company = CompanyLocalServiceUtil.getCompanyById(
			getCompanyId());

		return company.getMx();
	}

	public Contact getContact() throws PortalException, SystemException {
		return ContactLocalServiceUtil.getContact(getContactId());
	}

	public String getDigest() {
		String digest = super.getDigest();

		if (Validator.isNull(digest) && !isPasswordEncrypted()) {
			digest = getDigest(getPassword());
		}

		return digest;
	}

	public String getDigest(String password) {
		if (Validator.isNull(getScreenName())) {
			throw new IllegalStateException("Screen name cannot be null");
		}
		else if (Validator.isNull(getEmailAddress())) {
			throw new IllegalStateException("Email address cannot be null");
		}

		StringBundler sb = new StringBundler(5);

		String digest1 = DigesterUtil.digestHex(
			Digester.MD5, getEmailAddress(), Portal.PORTAL_REALM, password);

		sb.append(digest1);
		sb.append(StringPool.COMMA);

		String digest2 = DigesterUtil.digestHex(
			Digester.MD5, getScreenName(), Portal.PORTAL_REALM, password);

		sb.append(digest2);
		sb.append(StringPool.COMMA);

		String digest3 = DigesterUtil.digestHex(
			Digester.MD5, String.valueOf(getUserId()), Portal.PORTAL_REALM,
			password);

		sb.append(digest3);

		return sb.toString();
	}

	public String getDisplayEmailAddress() {
		String emailAddress = super.getEmailAddress();

		EmailAddressGenerator emailAddressGenerator =
			EmailAddressGeneratorFactory.getInstance();

		if (emailAddressGenerator.isFake(emailAddress)) {
			emailAddress = StringPool.BLANK;
		}

		return emailAddress;
	}

	public String getDisplayURL(ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return getDisplayURL(
			themeDisplay.getPortalURL(), themeDisplay.getPathMain());
	}

	public String getDisplayURL(String portalURL, String mainPath)
		throws PortalException, SystemException {

		if (isDefaultUser()) {
			return StringPool.BLANK;
		}

		Group group = getGroup();

		int publicLayoutsPageCount = group.getPublicLayoutsPageCount();

		if (publicLayoutsPageCount > 0) {
			StringBundler sb = new StringBundler(5);

			sb.append(portalURL);
			sb.append(mainPath);
			sb.append("/my_places/view?groupId=");
			sb.append(group.getGroupId());
			sb.append("&privateLayout=0");

			return sb.toString();
		}

		return StringPool.BLANK;
	}

	public boolean getFemale() throws PortalException, SystemException {
		return !getMale();
	}

	public String getFullName() {
		FullNameGenerator fullNameGenerator =
			FullNameGeneratorFactory.getInstance();

		return fullNameGenerator.getFullName(
			getFirstName(), getMiddleName(), getLastName());
	}

	public Group getGroup() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getUserGroup(getCompanyId(), getUserId());
	}

	public long[] getGroupIds() throws PortalException, SystemException {
		List<Group> groups = getGroups();

		long[] groupIds = new long[groups.size()];

		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);

			groupIds[i] = group.getGroupId();
		}

		return groupIds;
	}

	public List<Group> getGroups() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getUserGroups(getUserId());
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getLogin() throws PortalException, SystemException {
		String login = null;

		Company company = CompanyLocalServiceUtil.getCompanyById(
			getCompanyId());

		if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_EA)) {
			login = getEmailAddress();
		}
		else if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_SN)) {
			login = getScreenName();
		}
		else if (company.getAuthType().equals(CompanyConstants.AUTH_TYPE_ID)) {
			login = String.valueOf(getUserId());
		}

		return login;
	}

	public boolean getMale() throws PortalException, SystemException {
		return getContact().getMale();
	}

	public List<Group> getMyPlaces() throws PortalException, SystemException {
		return getMyPlaces(null, QueryUtil.ALL_POS);
	}

	public List<Group> getMyPlaces(int max)
		throws PortalException, SystemException {

		return getMyPlaces(null, max);
	}

	public List<Group> getMyPlaces(String[] classNames, int max)
		throws PortalException, SystemException {

		if (isDefaultUser()) {
			return Collections.EMPTY_LIST;
		}

		ThreadLocalCache<List<Group>> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(
				Lifecycle.REQUEST, _GET_MY_PLACES_CACHE_NAME);

		String key = StringUtil.toHexString(max);

		if ((classNames != null) && (classNames.length > 0)) {
			key = StringUtil.merge(classNames).concat(StringPool.POUND).concat(
				key);
		}

		List<Group> myPlaces = threadLocalCache.get(key);

		if (myPlaces != null) {
			return myPlaces;
		}

		myPlaces = new UniqueList<Group>();

		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (max != QueryUtil.ALL_POS) {
			start = 0;
			end = max;
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, Group.class.getName())) {

			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("usersGroups", new Long(getUserId()));
			//groupParams.put("pageCount", StringPool.BLANK);

			myPlaces.addAll(
				GroupLocalServiceUtil.search(
					getCompanyId(), null, null, groupParams, start, end));
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, Organization.class.getName())) {

			LinkedHashMap<String, Object> organizationParams =
				new LinkedHashMap<String, Object>();

			organizationParams.put("usersOrgs", new Long(getUserId()));

			List<Organization> userOrgs = OrganizationLocalServiceUtil.search(
				getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID,
				null, null, null, null, organizationParams, start, end);

			for (Organization organization : userOrgs) {
				myPlaces.add(0, organization.getGroup());

				if (!PropsValues.ORGANIZATIONS_MEMBERSHIP_STRICT) {
					for (Organization ancestorOrganization :
							organization.getAncestors()) {

						myPlaces.add(0, ancestorOrganization.getGroup());
					}
				}
			}
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, User.class.getName())) {

			if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED ||
				PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

				Group userGroup = getGroup();

				myPlaces.add(0, userGroup);
			}
		}

		if ((max != QueryUtil.ALL_POS) && (myPlaces.size() > max)) {
			myPlaces = ListUtil.subList(myPlaces, start, end);
		}

		threadLocalCache.put(key, myPlaces);

		return myPlaces;
	}

	public long[] getOrganizationIds() throws PortalException, SystemException {
		List<Organization> organizations = getOrganizations();

		long[] organizationIds = new long[organizations.size()];

		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);

			organizationIds[i] = organization.getOrganizationId();
		}

		return organizationIds;
	}

	public List<Organization> getOrganizations()
		throws PortalException, SystemException {

		return OrganizationLocalServiceUtil.getUserOrganizations(
			getUserId());
	}

	public boolean getPasswordModified() {
		return _passwordModified;
	}

	public PasswordPolicy getPasswordPolicy()
		throws PortalException, SystemException {

		return PasswordPolicyLocalServiceUtil.getPasswordPolicyByUserId(
			getUserId());
	}

	public String getPasswordUnencrypted() {
		return _passwordUnencrypted;
	}

	public int getPrivateLayoutsPageCount()
		throws PortalException, SystemException {

		Group group = getGroup();

		return group.getPrivateLayoutsPageCount();
	}

	public int getPublicLayoutsPageCount()
		throws PortalException, SystemException {

		Group group = getGroup();

		return group.getPublicLayoutsPageCount();
	}

	public Set<String> getReminderQueryQuestions()
		throws PortalException, SystemException {

		Set<String> questions = new TreeSet<String>();

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getUserOrganizations(
				getUserId(), true);

		for (Organization organization : organizations) {
			Set<String> organizationQuestions =
				organization.getReminderQueryQuestions(getLanguageId());

			if (organizationQuestions.size() == 0) {
				Organization parentOrganization =
					organization.getParentOrganization();

				while ((organizationQuestions.size() == 0) &&
						(parentOrganization != null)) {

					organizationQuestions =
						parentOrganization.getReminderQueryQuestions(
							getLanguageId());

					parentOrganization =
						parentOrganization.getParentOrganization();
				}
			}

			questions.addAll(organizationQuestions);
		}

		if (questions.size() == 0) {
			Set<String> defaultQuestions = SetUtil.fromArray(
				PropsUtil.getArray(PropsKeys.USERS_REMINDER_QUERIES_QUESTIONS));

			questions.addAll(defaultQuestions);
		}

		return questions;
	}

	public long[] getRoleIds() throws SystemException {
		List<Role> roles = getRoles();

		long[] roleIds = new long[roles.size()];

		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);

			roleIds[i] = role.getRoleId();
		}

		return roleIds;
	}

	public List<Role> getRoles() throws SystemException {
		return RoleLocalServiceUtil.getUserRoles(getUserId());
	}

	public double getSocialContributionEquity() {
		if (_socialContributionEquity == null) {
			try {
				SocialEquityValue socialEquityValue =
					SocialEquityUserLocalServiceUtil.getContributionEquity(
						getUserId());

				_socialContributionEquity = new AtomicReference<Double>(
					socialEquityValue.getValue());
			}
			catch (SystemException se) {
				return 0;
			}
		}

		return _socialContributionEquity.get();
	}

	public double getSocialParticipationEquity() {
		if (_socialParticipationEquity == null) {
			try {
				SocialEquityValue socialEquityValue =
					SocialEquityUserLocalServiceUtil.getParticipationEquity(
						getUserId());

				_socialParticipationEquity = new AtomicReference<Double>(
					socialEquityValue.getValue());
			}
			catch (SystemException se) {
				return 0;
			}
		}

		return _socialParticipationEquity.get();
	}

	public double getSocialPersonalEquity() {
		return getSocialContributionEquity() + getSocialParticipationEquity();
	}

	public long[] getTeamIds() throws SystemException {
		List<Team> teams = getTeams();

		long[] teamIds = new long[teams.size()];

		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);

			teamIds[i] = team.getTeamId();
		}

		return teamIds;
	}

	public List<Team> getTeams() throws SystemException {
		return TeamLocalServiceUtil.getUserTeams(getUserId());
	}

	public long[] getUserGroupIds() throws SystemException {
		List<UserGroup> userGroups = getUserGroups();

		long[] userGroupIds = new long[userGroups.size()];

		for (int i = 0; i < userGroups.size(); i++) {
			UserGroup userGroup = userGroups.get(i);

			userGroupIds[i] = userGroup.getUserGroupId();
		}

		return userGroupIds;
	}

	public List<UserGroup> getUserGroups() throws SystemException {
		return UserGroupLocalServiceUtil.getUserUserGroups(getUserId());
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	public boolean hasCompanyMx() throws PortalException, SystemException {
		return hasCompanyMx(getEmailAddress());
	}

	public boolean hasCompanyMx(String emailAddress)
		throws PortalException, SystemException {

		if (Validator.isNull(emailAddress)) {
			return false;
		}

		Company company = CompanyLocalServiceUtil.getCompanyById(
			getCompanyId());

		return company.hasCompanyMx(emailAddress);
	}

	public boolean hasMyPlaces() throws PortalException, SystemException {
		if (isDefaultUser()) {
			return false;
		}

		List<Group> groups = getMyPlaces(PropsValues.MY_PLACES_MAX_ELEMENTS);

		if (groups.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasOrganization() throws PortalException, SystemException {
		if (getOrganizations().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPrivateLayouts() throws PortalException, SystemException {
		if (getPrivateLayoutsPageCount() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPublicLayouts() throws PortalException, SystemException {
		if (getPublicLayoutsPageCount() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasReminderQuery() {
		if (Validator.isNotNull(getReminderQueryQuestion()) &&
			Validator.isNotNull(getReminderQueryAnswer())) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isFemale() throws PortalException, SystemException {
		return getFemale();
	}

	public boolean isMale() throws PortalException, SystemException {
		return getMale();
	}

	public boolean isPasswordModified() {
		return _passwordModified;
	}

	public void setLanguageId(String languageId) {
		_locale = LocaleUtil.fromLanguageId(languageId);

		super.setLanguageId(LocaleUtil.toLanguageId(_locale));
	}

	public void setPasswordModified(boolean passwordModified) {
		_passwordModified = passwordModified;
	}

	public void setPasswordUnencrypted(String passwordUnencrypted) {
		_passwordUnencrypted = passwordUnencrypted;
	}

	public void setTimeZoneId(String timeZoneId) {
		if (Validator.isNull(timeZoneId)) {
			timeZoneId = TimeZoneUtil.getDefault().getID();
		}

		_timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		super.setTimeZoneId(timeZoneId);
	}

	public void updateSocialContributionEquity(double value) {
		if (_socialContributionEquity != null) {
			double currentValue = 0;
			double newValue = 0;

			do {
				currentValue = _socialContributionEquity.get();

				newValue = currentValue + value;
			}
			while (!_socialContributionEquity.compareAndSet(
						currentValue, newValue));
		}
	}

	public void updateSocialParticipationEquity(double value) {
		if (_socialParticipationEquity != null) {
			double currentValue = 0;
			double newValue = 0;

			do {
				currentValue = _socialParticipationEquity.get();

				newValue = currentValue + value;
			}
			while (!_socialParticipationEquity.compareAndSet(
						currentValue, newValue));
		}
	}

	private static final String _GET_MY_PLACES_CACHE_NAME = "GET_MY_PLACES";

	private Locale _locale;
	private boolean _passwordModified;
	private String _passwordUnencrypted;
	private AtomicReference<Double> _socialContributionEquity;
	private AtomicReference<Double> _socialParticipationEquity;
	private TimeZone _timeZone;

}
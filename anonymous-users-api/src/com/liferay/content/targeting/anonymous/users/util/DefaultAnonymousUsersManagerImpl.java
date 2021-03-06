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

package com.liferay.content.targeting.anonymous.users.util;

import com.liferay.content.targeting.anonymous.users.model.AnonymousUser;
import com.liferay.content.targeting.anonymous.users.service.AnonymousUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component
public class DefaultAnonymousUsersManagerImpl implements AnonymousUsersManager {

	@Override
	public AnonymousUser getAnonymousUser(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		long companyId = PortalUtil.getCompanyId(request);
		long userId = PortalUtil.getUserId(request);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		AnonymousUser anonymousUser = null;

		if (userId > 0) {
			anonymousUser = getAnonymousUser(request, userId);

			if (!anonymousUser.getLastIp().equals(request.getRemoteAddr())) {
				AnonymousUserLocalServiceUtil.updateLastIp(
					anonymousUser.getAnonymousUserId(),
					request.getRemoteAddr());
			}

			return anonymousUser;
		}

		anonymousUser = getAnonymousUserFromCookie(request);

		if (anonymousUser == null) {
			anonymousUser = AnonymousUserLocalServiceUtil.addAnonymousUser(
				0, request.getRemoteAddr(), null, serviceContext);

			_anonymousUsersCookieManager.addCookie(
				request, response, anonymousUser.getAnonymousUserId());
		}
		else if (!anonymousUser.getLastIp().equals(request.getRemoteAddr())) {
			AnonymousUserLocalServiceUtil.updateLastIp(
				anonymousUser.getAnonymousUserId(), request.getRemoteAddr());
		}

		return anonymousUser;
	}

	@Override
	public AnonymousUser getAnonymousUser(
			HttpServletRequest request, long userId)
		throws PortalException, SystemException {

		long companyId = PortalUtil.getCompanyId(request);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		AnonymousUser anonymousUser =
			AnonymousUserLocalServiceUtil.fetchAnonymousUserByUserId(userId);

		if (anonymousUser == null) {
			anonymousUser = getAnonymousUserFromCookie(request);

			if ((anonymousUser == null) ||
				((anonymousUser.getUserId() != 0) &&
				 (anonymousUser.getUserId() != userId))) {

				anonymousUser =
					AnonymousUserLocalServiceUtil.addAnonymousUser(
						userId, request.getRemoteAddr(), null, serviceContext);
			}
			else {
				anonymousUser =
					AnonymousUserLocalServiceUtil.updateAnonymousUser(
						anonymousUser.getAnonymousUserId(), userId,
						request.getRemoteAddr(),
						anonymousUser.getTypeSettings(), serviceContext);
			}
		}

		return anonymousUser;
	}

	@Override
	public AnonymousUser getAnonymousUser(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException, SystemException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			renderResponse);

		return getAnonymousUser(request, response);
	}

	public AnonymousUsersCookieManager getAnonymousUsersCookieManager() {
		return _anonymousUsersCookieManager;
	}

	@Reference
	public void setAnonymousUsersCookieManager(
		AnonymousUsersCookieManager anonymousUsersCookieManager) {

		_anonymousUsersCookieManager = anonymousUsersCookieManager;
	}

	protected AnonymousUser getAnonymousUserFromCookie(
			HttpServletRequest request)
		throws SystemException {

		AnonymousUser anonymousUser = null;

		long anonymousUserId = _anonymousUsersCookieManager.getAnonymousUserId(
			request);

		if (anonymousUserId > 0) {
			anonymousUser =
				AnonymousUserLocalServiceUtil.fetchAnonymousUser(
					anonymousUserId);
		}

		return anonymousUser;
	}

	private AnonymousUsersCookieManager _anonymousUsersCookieManager;

}
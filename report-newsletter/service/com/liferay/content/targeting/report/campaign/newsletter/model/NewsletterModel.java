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

package com.liferay.content.targeting.report.campaign.newsletter.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Newsletter service. Represents a row in the &quot;ContentTargeting_CampaignNewsletter_Newsletter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.content.targeting.report.campaign.newsletter.model.impl.NewsletterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.content.targeting.report.campaign.newsletter.model.impl.NewsletterImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Newsletter
 * @see com.liferay.content.targeting.report.campaign.newsletter.model.impl.NewsletterImpl
 * @see com.liferay.content.targeting.report.campaign.newsletter.model.impl.NewsletterModelImpl
 * @generated
 */
public interface NewsletterModel extends BaseModel<Newsletter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a newsletter model instance should use the {@link Newsletter} interface instead.
	 */

	/**
	 * Returns the primary key of this newsletter.
	 *
	 * @return the primary key of this newsletter
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this newsletter.
	 *
	 * @param primaryKey the primary key of this newsletter
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the newsletter ID of this newsletter.
	 *
	 * @return the newsletter ID of this newsletter
	 */
	public long getNewsletterId();

	/**
	 * Sets the newsletter ID of this newsletter.
	 *
	 * @param newsletterId the newsletter ID of this newsletter
	 */
	public void setNewsletterId(long newsletterId);

	/**
	 * Returns the campaign ID of this newsletter.
	 *
	 * @return the campaign ID of this newsletter
	 */
	public long getCampaignId();

	/**
	 * Sets the campaign ID of this newsletter.
	 *
	 * @param campaignId the campaign ID of this newsletter
	 */
	public void setCampaignId(long campaignId);

	/**
	 * Returns the alias of this newsletter.
	 *
	 * @return the alias of this newsletter
	 */
	@AutoEscape
	public String getAlias();

	/**
	 * Sets the alias of this newsletter.
	 *
	 * @param alias the alias of this newsletter
	 */
	public void setAlias(String alias);

	/**
	 * Returns the element ID of this newsletter.
	 *
	 * @return the element ID of this newsletter
	 */
	@AutoEscape
	public String getElementId();

	/**
	 * Sets the element ID of this newsletter.
	 *
	 * @param elementId the element ID of this newsletter
	 */
	public void setElementId(String elementId);

	/**
	 * Returns the event type of this newsletter.
	 *
	 * @return the event type of this newsletter
	 */
	@AutoEscape
	public String getEventType();

	/**
	 * Sets the event type of this newsletter.
	 *
	 * @param eventType the event type of this newsletter
	 */
	public void setEventType(String eventType);

	/**
	 * Returns the count of this newsletter.
	 *
	 * @return the count of this newsletter
	 */
	public int getCount();

	/**
	 * Sets the count of this newsletter.
	 *
	 * @param count the count of this newsletter
	 */
	public void setCount(int count);

	/**
	 * Returns the modified date of this newsletter.
	 *
	 * @return the modified date of this newsletter
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this newsletter.
	 *
	 * @param modifiedDate the modified date of this newsletter
	 */
	public void setModifiedDate(Date modifiedDate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Newsletter newsletter);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Newsletter> toCacheModel();

	@Override
	public Newsletter toEscapedModel();

	@Override
	public Newsletter toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
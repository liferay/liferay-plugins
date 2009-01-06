/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shindig.util;

import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.PhoneServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.gadgets.GadgetToken;
import org.apache.shindig.social.opensocial.model.Activity;
import org.apache.shindig.social.opensocial.model.Email;
import org.apache.shindig.social.opensocial.model.Enum;
import org.apache.shindig.social.opensocial.model.MediaItem.Type;
import org.apache.shindig.social.opensocial.model.MediaItem;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Phone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <a href="OpenSocialUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class ShindigUtil {

	public static final String OPEN_SOCIAL_DATA =
		"OPEN_SOCIAL_DATA";

	public static final String GADGET_USER_PREFERENCES =
		"GADGET_USER_PREFERENCES";

	public static String createBaseSecurityToken(
			String ownerId, long viewerId, String appId, String domain,
			String appUrl) {

		StringBuilder sb = new StringBuilder();

		sb.append(HttpUtil.encodeURL(ownerId, true));
		sb.append(StringPool.COLON);
		sb.append(HttpUtil.encodeURL(String.valueOf(viewerId), true));
		sb.append(StringPool.COLON);
		sb.append(HttpUtil.encodeURL(appId, true));
		sb.append(StringPool.COLON);
		sb.append(HttpUtil.encodeURL(domain, true));
		sb.append(StringPool.COLON);
		sb.append(HttpUtil.encodeURL(appUrl, true));

		return sb.toString();
	}

	public static Activity getActivity(SocialActivity socialActivity) {
		Activity activity = new Activity(
			String.valueOf(socialActivity.getClassPK()),
			String.valueOf(socialActivity.getUserId()));

		try {
			JSONObject extraData = new JSONObject(
				socialActivity.getExtraData());

			if (extraData.has(Activity.Field.APP_ID.toString())) {
				activity.setAppId(extraData.getString("appId"));
			}

			if (extraData.has(Activity.Field.BODY.toString())) {
				activity.setBody(extraData.getString("body"));
			}

			if (extraData.has(Activity.Field.BODY_ID.toString())) {
				activity.setBodyId(extraData.getString(
					"bodyId"));
			}

			if (extraData.has(Activity.Field.EXTERNAL_ID.toString())) {
				activity.setExternalId(
					extraData.getString("externalId"));
			}

			if (extraData.has(Activity.Field.MEDIA_ITEMS.toString())) {
				activity.setMediaItems(
					ShindigUtil.getMediaItems(
						extraData.getJSONArray("mediaItems")));
			}

			if (extraData.has(Activity.Field.POSTED_TIME.toString())) {
				activity.setPostedTime(
					extraData.getLong("postedTime"));
			}

			if (extraData.has(Activity.Field.PRIORITY.toString())) {
				activity.setPriority(
					Float.parseFloat(
						extraData.getString("priority")));
			}

			if (extraData.has(Activity.Field.STREAM_FAVICON_URL.toString())) {
				activity.setStreamFaviconUrl(
					extraData.getString("streamFaviconUrl"));
			}

			if (extraData.has(Activity.Field.STREAM_SOURCE_URL.toString())) {
				activity.setStreamSourceUrl(
					extraData.getString("streamSourceUrl"));
			}

			if (extraData.has(Activity.Field.STREAM_TITLE.toString())) {
				activity.setStreamTitle(
					extraData.getString("streamTitle"));
			}

			if (extraData.has(Activity.Field.STREAM_URL.toString())) {
				activity.setStreamUrl(
					extraData.getString("streamUrl"));
			}

			if (extraData.has(Activity.Field.TEMPLATE_PARAMS.toString())) {
				activity.setTemplateParams(
					ShindigUtil.getTemplateParams(
						extraData.getJSONArray(
							"templateParams")));
			}

			if (extraData.has(Activity.Field.TITLE.toString())) {
				activity.setTitle(
					extraData.getString("title"));
			}

			if (extraData.has(Activity.Field.TITLE_ID.toString())) {
				activity.setTitleId(
					extraData.getString("titleId"));
			}

			if (extraData.has(Activity.Field.URL.toString())) {
				activity.setUrl(extraData.getString("url"));
			}
		}
		catch (JSONException je) {
			_log.error(je, je);
		}

		return activity;
	}

	public static ExpandoColumn getColumn(String columnName) {
    	ExpandoColumn expandoColumn = null;

		ExpandoTable defaultTable = ShindigUtil.getTable(OPEN_SOCIAL_DATA);

		try {
			expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
				User.class.getName(), OPEN_SOCIAL_DATA, columnName);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		if (expandoColumn == null) {
			try {
				expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
					defaultTable.getTableId(), columnName,
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		return expandoColumn;
	}

	public static List<Email> getEmails(User user) {
		List<Email> emails = new ArrayList<Email>();

		emails.add(new Email(user.getEmailAddress(), "Primary"));

		try {
			List<EmailAddress> emailAddresses =
				EmailAddressLocalServiceUtil.getEmailAddresses(
					user.getCompanyId(), User.class.getName(),
					user.getUserId());

			for (EmailAddress emailAddress : emailAddresses) {
				emails.add(new Email(
					emailAddress.getAddress(),
					emailAddress.getType().getName()));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return emails;
	}

	public static JSONArray getMediaItems(List<MediaItem> list) {
		if (list == null) {
			return null;
		}

		JSONArray mediaItems = new JSONArray();

		for (MediaItem mediaItem : list) {
			try {
		    	JSONObject mediaItemJson = new JSONObject();

		    	mediaItemJson.put("mimeType", mediaItem.getMimeType());
		    	mediaItemJson.put("type", mediaItem.getType());
		    	mediaItemJson.put("url", mediaItem.getUrl());

		    	mediaItems.put(mediaItemJson);
			}
			catch (JSONException je) {
			}
		}

		return mediaItems;
	}

	public static List<MediaItem> getMediaItems(JSONArray array) {
		if (array == null) {
			return null;
		}

		List<MediaItem> items = new ArrayList<MediaItem>();

		for (int i = 0; i < array.length(); i++) {
			try {
				JSONObject jsonItem = array.getJSONObject(i);
				MediaItem item = new MediaItem(
					jsonItem.getString("mimeType"),
					Type.valueOf(jsonItem.getString("type")),
					jsonItem.getString("url"));

				items.add(item);
			}
			catch (JSONException je) {
			}
		}

		return items;
	}

	public static Person getPerson(
		Group group, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				"G-" + group.getGroupId(),
				new Name(group.getName() + " (Community)"));

			person.setGender(new Enum<Enum.Gender>(
				Enum.Gender.MALE));

			if (token.getViewerId().equals(person.getId())) {
				person.setIsViewer(true);
			}

			if (token.getOwnerId().equals(person.getId())) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	public static Person getPerson(
		Organization org, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				"G-" + org.getGroup().getGroupId(),
				new Name(org.getName() + " (Organization)"));

			if (profileDetails.contains("phone_numbers")) {
				person.setPhoneNumbers(getPhoneNumbers(
					Organization.class.getName(), org.getOrganizationId()));
			}

			if (profileDetails.contains("gender")) {
				person.setGender(new Enum<Enum.Gender>(
					Enum.Gender.MALE));
			}

			if (token.getViewerId().equals(person.getId())) {
				person.setIsViewer(true);
			}

			if (token.getOwnerId().equals(person.getId())) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	public static Person getPerson(
		User user, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				String.valueOf(user.getUserId()), new Name(user.getFullName()));

			// Required Fields

			StringBuilder sb = new StringBuilder();
			sb.append(token.getDomain());
			sb.append(PortalUtil.getPathFriendlyURLPublic());
			sb.append(user.getScreenName());

			person.setProfileUrl(sb.toString());

			sb = new StringBuilder();
			sb.append(token.getDomain());
			sb.append(PortalUtil.getPathImage());
			sb.append("/user_");
			sb.append(user.isFemale() ? "female" : "male");
			sb.append("_portrait?img_id=");
			sb.append(user.getPortraitId());
			sb.append("&t=");
			sb.append(ImageServletTokenUtil.getToken(user.getPortraitId()));

			person.setThumbnailUrl(sb.toString());

			// Optional Fields

			if (profileDetails.contains(Person.Field.ABOUT_ME.toString())) {
				person.setAboutMe(user.getComments());
			}

			if (profileDetails.contains(Person.Field.AGE.toString())) {
			    Calendar dateOfBirth = new GregorianCalendar();
			    dateOfBirth.setTime(user.getBirthday());

			    Calendar today = Calendar.getInstance();
			    int age = today.get(
			    	Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

			    dateOfBirth.add(Calendar.YEAR, age);

			    if (today.before(dateOfBirth)) {
			        age--;
			    }

				person.setAge(age);
			}

			if (profileDetails.contains(
					Person.Field.DATE_OF_BIRTH.toString())) {
				person.setDateOfBirth(user.getBirthday());
			}

			if (profileDetails.contains(Person.Field.EMAILS)) {
				person.setEmails(getEmails(user));
			}

			if (profileDetails.contains(Person.Field.GENDER.toString())) {
				if (user.isFemale()) {
					person.setGender(new Enum<Enum.Gender>(Enum.Gender.FEMALE));
				} else {
					person.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));
				}
			}

			if (profileDetails.contains(Person.Field.NICKNAME.toString())) {
				person.setNickname(user.getScreenName());
			}

			if (profileDetails.contains(
					Person.Field.PHONE_NUMBERS.toString())) {
				person.setPhoneNumbers(getPhoneNumbers(
					Contact.class.getName(), user.getContactId()));
			}

			if (profileDetails.contains(Person.Field.TIME_ZONE.toString())) {
				person.setTimeZone(new Long(user.getTimeZone().getRawOffset()));
			}

			if (token.getViewerId().equals(person.getId())) {
				person.setIsViewer(true);
			}

			if (token.getOwnerId().equals(person.getId())) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	public static List<Phone> getPhoneNumbers(String className, long classPK) {
		List<Phone> phoneNumbers = new ArrayList<Phone>();

		try {
			List<com.liferay.portal.model.Phone> liferayPhones =
				PhoneServiceUtil.getPhones(className, classPK);

			for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
				phoneNumbers.add(new Phone(
					liferayPhone.getNumber(),
					liferayPhone.getType().getName()));
			}
		}
		catch (Exception e) {
		}

		return phoneNumbers;
	}

	public static ExpandoTable getTable(String tableName) {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				User.class.getName(), tableName);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		if (expandoTable == null) {
			try {
				expandoTable = ExpandoTableLocalServiceUtil.addTable(
					User.class.getName(), tableName);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		return expandoTable;
	}

	public static JSONArray getTemplateParams(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		JSONArray items = new JSONArray();

		for (Entry<String,String> entry : map.entrySet()) {
			try {
		    	JSONObject jsonEntry = new JSONObject();

		    	jsonEntry.put(entry.getKey(), entry.getValue());

		    	items.put(jsonEntry);
			}
			catch (JSONException je) {
			}
		}

		return items;
	}

	public static Map<String, String> getTemplateParams(JSONArray array) {
		if (array == null) {
			return null;
		}

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < array.length(); i++) {
			try {
				JSONObject jsonItem = array.getJSONObject(i);

				JSONArray names = jsonItem.names();

				for (int j = 0; j < names.length(); j++) {
					String name = (String)names.get(j);

					map.put(name, jsonItem.getString(name));
				}
			}
			catch (JSONException je) {
			}
		}

		return map;
	}

	public static String getValue(String columnName, String userId) {
		String value = StringPool.BLANK;

		try {
			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(
				User.class.getName(), OPEN_SOCIAL_DATA,
				columnName, Long.parseLong(userId));

			value = expandoValue.getData();
		}
		catch (Exception e) {
		}

		return value;
	}

	public static boolean isValidKey(String key) {
		if (key == null || key.length() == 0) {
			return false;
		}

		for (int i = 0; i < key.length(); ++i) {
			char c = key.charAt(i);

			if ((c >= 'a' && c <= 'z') ||
					(c >= 'A' && c <= 'Z') ||
					(c >= '0' && c <= '9') ||
					(c == '-') ||
					(c == '_') ||
					(c == '.')) {
				continue;
			}

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactory.getLog(ShindigUtil.class);

}
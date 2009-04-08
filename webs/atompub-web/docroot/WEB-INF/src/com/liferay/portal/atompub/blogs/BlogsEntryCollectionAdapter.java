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

package com.liferay.portal.atompub.blogs;

import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.PortalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;

import org.apache.abdera.protocol.server.impl.AbstractEntityCollectionAdapter;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.context.ResponseContextException;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Person;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Text;
import org.apache.abdera.parser.stax.FOMContent;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

/**
 * <a href="BlogsEntryCollectionAdapter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 */
public class BlogsEntryCollectionAdapter
	extends AbstractEntityCollectionAdapter<BlogsEntry> {

	public void deleteEntry(String resourceName, RequestContext requestContext)
		throws ResponseContextException {
		long entryId = GetterUtil.getLong(resourceName);

		if (entryId > 0) {
			try {
				BlogsEntryLocalServiceUtil.deleteBlogsEntry(entryId);
			}
			catch (Exception e) {
			}
		}
	}

	public String getAuthor(RequestContext requestContext)
		throws ResponseContextException {

		return "Liferay";
	}

	public List<Person> getAuthors(BlogsEntry entry, RequestContext request)
		throws ResponseContextException {
		Person author = request.getAbdera().getFactory().newAuthor();

		author.setName(entry.getUserName());

		return Arrays.asList(author);
	}

	public Object getContent(
			BlogsEntry blogsEntry, RequestContext requestContext)
		throws ResponseContextException {
		Content content = requestContext.getAbdera().getFactory().newContent(
			Content.Type.HTML);

		content.setText(blogsEntry.getContent());

		return content; 
	}

	public Iterable<BlogsEntry> getEntries(RequestContext requestContext)
		throws ResponseContextException {

		try {
			long groupId = GetterUtil.getLong(
				requestContext.getParameter("groupId"), 0L);
			long companyId = GetterUtil.getLong(
				requestContext.getParameter("companyId"), 0L);
			int start = GetterUtil.getInteger(
				requestContext.getParameter("start"), 0);
			int end = GetterUtil.getInteger(
				requestContext.getParameter("end"), 10);

			if (groupId > 0) {
				return BlogsEntryLocalServiceUtil.getGroupEntries(
					groupId, start, end);
			}
			else if (companyId > 0) {
				return BlogsEntryLocalServiceUtil.getCompanyEntries(
					companyId, start, end);
			}
			else {
				return BlogsEntryLocalServiceUtil.getBlogsEntries(start, end);
			}
		}
		catch (SystemException se) {
			throw new ResponseContextException(0, se);
		}
	}

	public BlogsEntry getEntry(
			String resourceName, RequestContext requestContext)
		throws ResponseContextException {

		long entryId = GetterUtil.getLong(resourceName);

		try {
			return BlogsEntryLocalServiceUtil.getEntry(entryId);
		}
		catch (Exception e) {
			throw new ResponseContextException(0, e);
		}
	}

	public String getId(BlogsEntry blogsEntry) throws ResponseContextException {
		return _UUID_PREFIX + blogsEntry.getEntryId();
	}

	public String getId(RequestContext requestContext) {
		return "tags:liferay.com,blogs";
	}

	public String getName(BlogsEntry blogsEntry)
		throws ResponseContextException {

		return blogsEntry.getTitle();
	}

	public String getTitle(BlogsEntry blogsEntry)
		throws ResponseContextException {

		return blogsEntry.getTitle();
	}

	public String getTitle(RequestContext requestContext) {
		return null;
	}

	public Date getUpdated(BlogsEntry blogsEntry)
		throws ResponseContextException {

		return blogsEntry.getModifiedDate();
	}

	public BlogsEntry postEntry(
		String title, IRI iri, String summary, Date date, List<Person> persons,
		Content content, RequestContext requestContext)
		throws ResponseContextException {

		long groupId = GetterUtil.getLong(
			requestContext.getParameter("groupId"), 0L);
		long userId = GetterUtil.getLong(
			requestContext.getParameter("userId"), 0L);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);

		try {
			return BlogsEntryLocalServiceUtil.addEntry(
				userId, title, content.getText(), date.getMonth(),
				date.getDay(), date.getYear(), date.getHours(),
				date.getMinutes(), false, true, new String[0], serviceContext);
		}
		catch (Exception e) {
			throw new ResponseContextException(0, e);
		}
	}

	public void putEntry(
		BlogsEntry oldBlogsEntry, String title, Date date, List<Person> persons,
		String summary, Content content, RequestContext requestContext)
		throws ResponseContextException {

//		long groupId = GetterUtil.getLong(
//			requestContext.getParameter("groupId"), 0L);
//
		ServiceContext serviceContext = new ServiceContext();
//
//		serviceContext.setScopeGroupId(groupId);

		try {
			BlogsEntryLocalServiceUtil.updateEntry(
				oldBlogsEntry.getUserId(), oldBlogsEntry.getEntryId(), title,
		        content.getText(), oldBlogsEntry.getDisplayDate().getMonth(),
				oldBlogsEntry.getDisplayDate().getDay(),
				oldBlogsEntry.getDisplayDate().getYear(),
				oldBlogsEntry.getDisplayDate().getHours(),
				oldBlogsEntry.getDisplayDate().getMinutes(),
				false, true, new String[0], serviceContext);
		}
		catch (Exception e) {
			throw new ResponseContextException(0, e);
		}
	}

	private static final String _UUID_PREFIX = "uuid:";

}

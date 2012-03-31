package com.liferay.calendar.service.persistence;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.impl.CalendarImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;


public class CalendarFinderImpl
	extends BasePersistenceImpl<Calendar>
	implements CalendarFinder {

	public static final String COUNT_BY_C_G_C_N_D =
		CalendarFinder.class.getName() + ".countByC_G_C_N_D";

	public static final String FIND_BY_C_G_C_N_D =
		CalendarFinder.class.getName() + ".findByC_G_C_N_D";

	public int countByKeywords(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	public int countByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return countByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	public int countByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, false);
	}

	public int filterCountByKeywords(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	public int filterCountByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	public int filterCountByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, false);
	}

	public List<Calendar> filterFindByKeywords(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	public List<Calendar> filterFindByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator,
			int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	public List<Calendar> filterFindByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator, true);
	}

	public List<Calendar> findByKeywords(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	public List<Calendar> findByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String name, String description, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return findByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	public List<Calendar> findByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator, false);
	}

	protected int doCountByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator,
			boolean inlineSQLHelper)
		throws SystemException {

		String[] calendarResourceIdsString = null;

		if (calendarResourceIds == null) {
			calendarResourceIdsString = new String[] {null};
		}
		else {
			calendarResourceIdsString =
				ArrayUtil.toStringArray(calendarResourceIds);
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, Calendar.class.getName(), "Calendar.calendarId",
					groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "calendarResourceIds", StringPool.EQUAL, false,
				calendarResourceIdsString);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(calendarResourceIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<Calendar> doFindByC_G_C_N_D(
			long companyId, long[] groupIds, long[] calendarResourceIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator orderByComparator,
			boolean inlineSQLHelper)
		throws SystemException {

		String[] calendarResourceIdsString = null;

		if (calendarResourceIds == null) {
			calendarResourceIdsString = new String[] {null};
		}
		else {
			calendarResourceIdsString =
				ArrayUtil.toStringArray(calendarResourceIds);
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, Calendar.class.getName(),
					"Calendar.calendarId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "calendarResourceIds", StringPool.EQUAL, false,
				calendarResourceIdsString);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			StringBundler sb = new StringBundler();

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, "Calendar.", orderByComparator);
			}

			sql = StringUtil.replace(sql, "[$ORDER_BY$]", sb.toString());

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Calendar", CalendarImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(calendarResourceIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<Calendar>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getGroupIds(long[] groupIds) {
		if (groupIds.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(groupIds.length * 2);

		sb.append("(");

		for (int i = 0; i < groupIds.length; i++) {
			sb.append("groupId = ?");

			if ((i + 1) < groupIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

}

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

package com.liferay.customsql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Raymond Augé
 */
public class CustomSQL {

	public static final String DB2_FUNCTION_IS_NOT_NULL =
		"CAST(? AS VARCHAR(32672)) IS NOT NULL";

	public static final String DB2_FUNCTION_IS_NULL =
		"CAST(? AS VARCHAR(32672)) IS NULL";

	public static final String INFORMIX_FUNCTION_IS_NOT_NULL =
		"NOT lportal.isnull(?)";

	public static final String INFORMIX_FUNCTION_IS_NULL = "lportal.isnull(?)";

	public static final String MYSQL_FUNCTION_IS_NOT_NULL =
		"IFNULL(?, '1') = '0'";

	public static final String MYSQL_FUNCTION_IS_NULL = "IFNULL(?, '1') = '1'";

	public static final String SYBASE_FUNCTION_IS_NOT_NULL =
		"CONVERT(VARCHAR,?) IS NOT NULL";

	public static final String SYBASE_FUNCTION_IS_NULL =
		"CONVERT(VARCHAR,?) IS NULL";

	public CustomSQL() {
		try {
			reloadCustomSQL();
		}
		catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	public String appendCriteria(String sql, String criteria) {
		if (Validator.isNull(criteria)) {
			return sql;
		}

		if (!criteria.startsWith(StringPool.SPACE)) {
			criteria = StringPool.SPACE.concat(criteria);
		}

		if (!criteria.endsWith(StringPool.SPACE)) {
			criteria = criteria.concat(StringPool.SPACE);
		}

		int pos = sql.indexOf(_GROUP_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(criteria).concat(
				sql.substring(pos + 1));
		}

		pos = sql.indexOf(_ORDER_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(criteria).concat(
				sql.substring(pos + 1));
		}

		return sql.concat(criteria);
	}

	public String get(String id) {
		return _sqlPool.get(id);
	}

	public String get(String id, QueryDefinition queryDefinition) {
		return get(id, queryDefinition, StringPool.BLANK);
	}

	public String get(
		String id, QueryDefinition queryDefinition, String tableName) {

		String sql = get(id);

		if (!Validator.isBlank(tableName) &&
			!tableName.endsWith(StringPool.PERIOD)) {

			tableName = tableName.concat(StringPool.PERIOD);
		}

		if (queryDefinition.getStatus() == WorkflowConstants.STATUS_ANY) {
			sql = sql.replace(_STATUS_KEYWORD, _STATUS_CONDITION_EMPTY);
		}
		else {
			if (queryDefinition.isExcludeStatus()) {
				sql = sql.replace(
					_STATUS_KEYWORD,
					tableName.concat(_STATUS_CONDITION_INVERSE));
			}
			else {
				sql = sql.replace(
					_STATUS_KEYWORD,
					tableName.concat(_STATUS_CONDITION_DEFAULT));
			}
		}

		return sql;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to a DB2 database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to a DB2 database
	 */
	public boolean isVendorDB2() {
		return _vendorDB2;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to a Hypersonic
	 * database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to a Hypersonic
	 *         database
	 */
	public boolean isVendorHSQL() {
		return _vendorHSQL;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to an Informix
	 * database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to an Informix
	 *         database
	 */
	public boolean isVendorInformix() {
		return _vendorInformix;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to a MySQL database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to a MySQL database
	 */
	public boolean isVendorMySQL() {
		return _vendorMySQL;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to an Oracle
	 * database. Oracle has a nasty bug where it treats '' as a
	 * <code>NULL</code> value. See
	 * http://thedailywtf.com/forums/thread/26879.aspx for more information on
	 * this nasty bug.
	 *
	 * @return <code>true</code> if Hibernate is connecting to an Oracle
	 *         database
	 */
	public boolean isVendorOracle() {
		return _vendorOracle;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to a PostgreSQL
	 * database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to a PostgreSQL
	 *         database
	 */
	public boolean isVendorPostgreSQL() {
		return _vendorPostgreSQL;
	}

	/**
	 * Returns <code>true</code> if Hibernate is connecting to a Sybase
	 * database.
	 *
	 * @return <code>true</code> if Hibernate is connecting to a Sybase database
	 */
	public boolean isVendorSybase() {
		return _vendorSybase;
	}

	public String[] keywords(String keywords) {
		return keywords(keywords, true);
	}

	public String[] keywords(String keywords, boolean lowerCase) {
		if (Validator.isNull(keywords)) {
			return new String[] {null};
		}

		if (_CUSTOM_SQL_AUTO_ESCAPE_WILDCARDS_ENABLED) {
			keywords = escapeWildCards(keywords);
		}

		if (lowerCase) {
			keywords = StringUtil.toLowerCase(keywords);
		}

		keywords = keywords.trim();

		List<String> keywordsList = new ArrayList<String>();

		for (int i = 0; i < keywords.length(); i++) {
			char c = keywords.charAt(i);

			if (c == CharPool.QUOTE) {
				int pos = i + 1;

				i = keywords.indexOf(CharPool.QUOTE, pos);

				if (i == -1) {
					i = keywords.length();
				}

				if (i > pos) {
					String keyword = keywords.substring(pos, i);

					keywordsList.add(
						StringUtil.quote(keyword, StringPool.PERCENT));
				}
			}
			else {
				while (Character.isWhitespace(c)) {
					i++;

					c = keywords.charAt(i);
				}

				int pos = i;

				while (!Character.isWhitespace(c)) {
					i++;

					if (i == keywords.length()) {
						break;
					}

					c = keywords.charAt(i);
				}

				String keyword = keywords.substring(pos, i);

				keywordsList.add(StringUtil.quote(keyword, StringPool.PERCENT));
			}
		}

		return keywordsList.toArray(new String[keywordsList.size()]);
	}

	public String[] keywords(String[] keywordsArray) {
		return keywords(keywordsArray, true);
	}

	public String[] keywords(String[] keywordsArray, boolean lowerCase) {
		if (ArrayUtil.isEmpty(keywordsArray)) {
			return new String[] {null};
		}

		if (lowerCase) {
			for (int i = 0; i < keywordsArray.length; i++) {
				keywordsArray[i] = StringUtil.lowerCase(keywordsArray[i]);
			}
		}

		return keywordsArray;
	}

	public void reloadCustomSQL() throws SQLException {
		PortalUtil.initCustomSQL();

		Connection con = DataAccess.getConnection();

		String functionIsNull = PortalUtil.getCustomSQLFunctionIsNull();
		String functionIsNotNull = PortalUtil.getCustomSQLFunctionIsNotNull();

		try {
			if (Validator.isNotNull(functionIsNull) &&
				Validator.isNotNull(functionIsNotNull)) {

				_functionIsNull = functionIsNull;
				_functionIsNotNull = functionIsNotNull;

				if (_log.isDebugEnabled()) {
					_log.debug(
						"functionIsNull is manually set to " + functionIsNull);
					_log.debug(
						"functionIsNotNull is manually set to " +
							functionIsNotNull);
				}
			}
			else if (con != null) {
				DatabaseMetaData metaData = con.getMetaData();

				String dbName = GetterUtil.getString(
					metaData.getDatabaseProductName());

				if (_log.isInfoEnabled()) {
					_log.info("Database name " + dbName);
				}

				if (dbName.startsWith("DB2")) {
					_vendorDB2 = true;
					_functionIsNull = DB2_FUNCTION_IS_NULL;
					_functionIsNotNull = DB2_FUNCTION_IS_NOT_NULL;

					if (_log.isInfoEnabled()) {
						_log.info("Detected DB2 with database name " + dbName);
					}
				}
				else if (dbName.startsWith("HSQL")) {
					_vendorHSQL = true;

					if (_log.isInfoEnabled()) {
						_log.info("Detected HSQL with database name " + dbName);
					}
				}
				else if (dbName.startsWith("Informix")) {
					_vendorInformix = true;
					_functionIsNull = INFORMIX_FUNCTION_IS_NULL;
					_functionIsNotNull = INFORMIX_FUNCTION_IS_NOT_NULL;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Detected Informix with database name " + dbName);
					}
				}
				else if (dbName.startsWith("MySQL")) {
					_vendorMySQL = true;
					//_functionIsNull = MYSQL_FUNCTION_IS_NULL;
					//_functionIsNotNull = MYSQL_FUNCTION_IS_NOT_NULL;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Detected MySQL with database name " + dbName);
					}
				}
				else if (dbName.startsWith("Sybase") || dbName.equals("ASE")) {
					_vendorSybase = true;
					_functionIsNull = SYBASE_FUNCTION_IS_NULL;
					_functionIsNotNull = SYBASE_FUNCTION_IS_NOT_NULL;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Detected Sybase with database name " + dbName);
					}
				}
				else if (dbName.startsWith("Oracle")) {
					_vendorOracle = true;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Detected Oracle with database name " + dbName);
					}
				}
				else if (dbName.startsWith("PostgreSQL")) {
					_vendorPostgreSQL = true;

					if (_log.isInfoEnabled()) {
						_log.info(
							"Detected PostgreSQL with database name " + dbName);
					}
				}
				else {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Unable to detect database with name " + dbName);
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			DataAccess.cleanUp(con);
		}

		if (_sqlPool == null) {
			_sqlPool = new HashMap<String, String>();
		}
		else {
			_sqlPool.clear();
		}

		try {
			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			String[] configs = getConfigs();

			for (String _config : configs) {
				read(classLoader, _config);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public String removeGroupBy(String sql) {
		int x = sql.indexOf(_GROUP_BY_CLAUSE);

		if (x != -1) {
			int y = sql.indexOf(_ORDER_BY_CLAUSE);

			if (y == -1) {
				sql = sql.substring(0, x);
			}
			else {
				sql = sql.substring(0, x) + sql.substring(y);
			}
		}

		return sql;
	}

	public String removeOrderBy(String sql) {
		int pos = sql.indexOf(_ORDER_BY_CLAUSE);

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	public String replaceAndOperator(String sql, boolean andOperator) {
		String andOrConnector = "OR";
		String andOrNullCheck = "AND ? IS NOT NULL";

		if (andOperator) {
			andOrConnector = "AND";
			andOrNullCheck = "OR ? IS NULL";
		}

		sql = StringUtil.replace(
			sql,
			new String[] {
				"[$AND_OR_CONNECTOR$]", "[$AND_OR_NULL_CHECK$]"
			},
			new String[] {
				andOrConnector, andOrNullCheck
			});

		if (_vendorPostgreSQL) {
			sql = StringUtil.replace(
				sql,
				new String[] {
					"Date >= ? AND ? IS NOT NULL",
					"Date <= ? AND ? IS NOT NULL", "Date >= ? OR ? IS NULL",
					"Date <= ? OR ? IS NULL"
				},
				new String[] {
					"Date >= ? AND CAST(? AS TIMESTAMP) IS NOT NULL",
					"Date <= ? AND CAST(? AS TIMESTAMP) IS NOT NULL",
					"Date >= ? OR CAST(? AS TIMESTAMP) IS NULL",
					"Date <= ? OR CAST(? AS TIMESTAMP) IS NULL"
				});
		}

		sql = replaceIsNull(sql);

		return sql;
	}

	public String replaceGroupBy(String sql, String groupBy) {
		if (groupBy == null) {
			return sql;
		}

		int x = sql.indexOf(_GROUP_BY_CLAUSE);

		if (x != -1) {
			int y = sql.indexOf(_ORDER_BY_CLAUSE);

			if (y == -1) {
				sql = sql.substring(0, x + _GROUP_BY_CLAUSE.length()).concat(
					groupBy);
			}
			else {
				sql = sql.substring(0, x + _GROUP_BY_CLAUSE.length()).concat(
					groupBy).concat(sql.substring(y));
			}
		}
		else {
			int y = sql.indexOf(_ORDER_BY_CLAUSE);

			if (y == -1) {
				sql = sql.concat(_GROUP_BY_CLAUSE).concat(groupBy);
			}
			else {
				StringBundler sb = new StringBundler(4);

				sb.append(sql.substring(0, y));
				sb.append(_GROUP_BY_CLAUSE);
				sb.append(groupBy);
				sb.append(sql.substring(y));

				sql = sb.toString();
			}
		}

		return sql;
	}

	public String replaceIsNull(String sql) {
		if (Validator.isNotNull(_functionIsNull)) {
			sql = StringUtil.replace(
				sql,
				new String[] {
					"? IS NULL", "? IS NOT NULL"
				},
				new String[] {
					_functionIsNull, _functionIsNotNull
				});
		}

		return sql;
	}

	public String replaceKeywords(
		String sql, String field, boolean last, int[] values) {

		if ((values != null) && (values.length == 1)) {
			return sql;
		}

		StringBundler oldSql = new StringBundler(4);

		oldSql.append(StringPool.OPEN_PARENTHESIS);
		oldSql.append(field);
		oldSql.append(" = ?)");

		if (!last) {
			oldSql.append(" [$AND_OR_CONNECTOR$]");
		}

		if (ArrayUtil.isEmpty(values)) {
			return StringUtil.replace(sql, oldSql.toString(), StringPool.BLANK);
		}

		StringBundler newSql = new StringBundler(values.length * 4 + 3);

		newSql.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				newSql.append(" OR ");
			}

			newSql.append(StringPool.OPEN_PARENTHESIS);
			newSql.append(field);
			newSql.append(" = ?)");
		}

		newSql.append(StringPool.CLOSE_PARENTHESIS);

		if (!last) {
			newSql.append(" [$AND_OR_CONNECTOR$]");
		}

		return StringUtil.replace(sql, oldSql.toString(), newSql.toString());
	}

	public String replaceKeywords(
		String sql, String field, boolean last, long[] values) {

		if ((values != null) && (values.length == 1)) {
			return sql;
		}

		StringBundler oldSql = new StringBundler(4);

		oldSql.append(StringPool.OPEN_PARENTHESIS);
		oldSql.append(field);
		oldSql.append(" = ?)");

		if (!last) {
			oldSql.append(" [$AND_OR_CONNECTOR$]");
		}

		if (ArrayUtil.isEmpty(values)) {
			return StringUtil.replace(sql, oldSql.toString(), StringPool.BLANK);
		}

		StringBundler newSql = new StringBundler(values.length * 4 + 3);

		newSql.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				newSql.append(" OR ");
			}

			newSql.append(StringPool.OPEN_PARENTHESIS);
			newSql.append(field);
			newSql.append(" = ?)");
		}

		newSql.append(StringPool.CLOSE_PARENTHESIS);

		if (!last) {
			newSql.append(" [$AND_OR_CONNECTOR$]");
		}

		return StringUtil.replace(sql, oldSql.toString(), newSql.toString());
	}

	public String replaceKeywords(
		String sql, String field, String operator, boolean last,
		String[] values) {

		if ((values != null) && (values.length <= 1)) {
			return sql;
		}

		StringBundler oldSql = new StringBundler(6);

		oldSql.append(StringPool.OPEN_PARENTHESIS);
		oldSql.append(field);
		oldSql.append(" ");
		oldSql.append(operator);
		oldSql.append(" ? [$AND_OR_NULL_CHECK$])");

		if (!last) {
			oldSql.append(" [$AND_OR_CONNECTOR$]");
		}

		StringBundler newSql = new StringBundler(values.length * 6 + 3);

		newSql.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				newSql.append(" OR ");
			}

			newSql.append(StringPool.OPEN_PARENTHESIS);
			newSql.append(field);
			newSql.append(" ");
			newSql.append(operator);
			newSql.append(" ? [$AND_OR_NULL_CHECK$])");
		}

		newSql.append(StringPool.CLOSE_PARENTHESIS);

		if (!last) {
			newSql.append(" [$AND_OR_CONNECTOR$]");
		}

		return StringUtil.replace(sql, oldSql.toString(), newSql.toString());
	}

	public String replaceOrderBy(String sql, OrderByComparator obc) {
		if (obc == null) {
			return sql;
		}

		String orderBy = obc.getOrderBy();

		int pos = sql.indexOf(_ORDER_BY_CLAUSE);

		if ((pos != -1) && (pos < sql.length())) {
			sql = sql.substring(0, pos + _ORDER_BY_CLAUSE.length()).concat(
				orderBy);
		}
		else {
			sql = sql.concat(_ORDER_BY_CLAUSE).concat(orderBy);
		}

		return sql;
	}

	protected String[] getConfigs() {
		if (PortalClassLoaderUtil.getClassLoader() ==
				CustomSQL.class.getClassLoader()) {

			Properties propsUtil = PortalUtil.getPortalProperties();

			return StringUtil.split(
				propsUtil.getProperty("custom.sql.configs"));
		}
		else {
			return new String[] {"custom-sql/default.xml"};
		}
	}

	protected void read(ClassLoader classLoader, String source)
		throws Exception {

		InputStream is = classLoader.getResourceAsStream(source);

		if (is == null) {
			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Loading " + source);
		}

		Document document = SAXReaderUtil.read(is);

		Element rootElement = document.getRootElement();

		for (Element sqlElement : rootElement.elements("sql")) {
			String file = sqlElement.attributeValue("file");

			if (Validator.isNotNull(file)) {
				read(classLoader, file);
			}
			else {
				String id = sqlElement.attributeValue("id");
				String content = transform(sqlElement.getText());

				content = replaceIsNull(content);

				_sqlPool.put(id, content);
			}
		}
	}

	protected String transform(String sql) {
		sql = PortalUtil.transformCustomSQL(sql);

		StringBundler sb = new StringBundler();

		try {
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new UnsyncStringReader(sql));

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				sb.append(line.trim());
				sb.append(StringPool.SPACE);
			}

			unsyncBufferedReader.close();
		}
		catch (IOException ioe) {
			return sql;
		}

		return sb.toString();
	}

	private String escapeWildCards(String keywords) {
		if (!isVendorMySQL() && !isVendorOracle()) {
			return keywords;
		}

		StringBuilder sb = new StringBuilder(keywords);

		for (int i = 0; i < sb.length(); ++i) {
			char c = sb.charAt(i);

			if (c == CharPool.BACK_SLASH) {
				i++;

				continue;
			}

			if ((c == CharPool.UNDERLINE) || (c == CharPool.PERCENT)) {
				sb.insert(i, CharPool.BACK_SLASH);

				i++;

				continue;
			}
		}

		return sb.toString();
	}

	private static final boolean _CUSTOM_SQL_AUTO_ESCAPE_WILDCARDS_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.CUSTOM_SQL_AUTO_ESCAPE_WILDCARDS_ENABLED));

	private static final String _GROUP_BY_CLAUSE = " GROUP BY ";

	private static final String _ORDER_BY_CLAUSE = " ORDER BY ";

	private static final String _STATUS_CONDITION_DEFAULT = "status = ?";

	private static final String _STATUS_CONDITION_EMPTY =
		WorkflowConstants.STATUS_ANY + " = ?";

	private static final String _STATUS_CONDITION_INVERSE = "status != ?";

	private static final String _STATUS_KEYWORD = "[$STATUS$]";

	private static Log _log = LogFactoryUtil.getLog(CustomSQL.class);

	private String _functionIsNotNull;
	private String _functionIsNull;
	private Map<String, String> _sqlPool;
	private boolean _vendorDB2;
	private boolean _vendorHSQL;
	private boolean _vendorInformix;
	private boolean _vendorMySQL;
	private boolean _vendorOracle;
	private boolean _vendorPostgreSQL;
	private boolean _vendorSybase;

}
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service.base;

import com.liferay.knowledgebase.service.KBArticleServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KBArticleServiceClpInvoker {
	public KBArticleServiceClpInvoker() {
		_methodName80 = "getBeanIdentifier";

		_methodParameterTypes80 = new String[] {  };

		_methodName81 = "setBeanIdentifier";

		_methodParameterTypes81 = new String[] { "java.lang.String" };

		_methodName86 = "addAttachment";

		_methodParameterTypes86 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName87 = "addKBArticle";

		_methodParameterTypes87 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String[][]",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName88 = "deleteAttachment";

		_methodParameterTypes88 = new String[] {
				"long", "long", "java.lang.String", "long", "java.lang.String"
			};

		_methodName89 = "deleteKBArticle";

		_methodParameterTypes89 = new String[] { "long" };

		_methodName90 = "deleteKBArticles";

		_methodParameterTypes90 = new String[] { "long", "long[][]" };

		_methodName91 = "getGroupKBArticles";

		_methodParameterTypes91 = new String[] {
				"long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName92 = "getGroupKBArticlesCount";

		_methodParameterTypes92 = new String[] { "long", "int" };

		_methodName93 = "getGroupKBArticlesRSS";

		_methodParameterTypes93 = new String[] {
				"int", "int", "java.lang.String", "java.lang.String",
				"com.liferay.portal.theme.ThemeDisplay"
			};

		_methodName94 = "getKBArticle";

		_methodParameterTypes94 = new String[] { "long", "int" };

		_methodName95 = "getKBArticleAndAllDescendants";

		_methodParameterTypes95 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName96 = "getKBArticleRSS";

		_methodParameterTypes96 = new String[] {
				"long", "int", "int", "java.lang.String", "java.lang.String",
				"com.liferay.portal.theme.ThemeDisplay"
			};

		_methodName97 = "getKBArticles";

		_methodParameterTypes97 = new String[] {
				"long", "long[][]", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName98 = "getKBArticleSearchDisplay";

		_methodParameterTypes98 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int",
				"java.util.Date", "java.util.Date", "boolean", "int[][]", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName99 = "getKBArticleVersions";

		_methodParameterTypes99 = new String[] {
				"long", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName100 = "getKBArticleVersionsCount";

		_methodParameterTypes100 = new String[] { "long", "long", "int" };

		_methodName101 = "getLatestKBArticle";

		_methodParameterTypes101 = new String[] { "long", "int" };

		_methodName102 = "getSectionsKBArticles";

		_methodParameterTypes102 = new String[] {
				"long", "java.lang.String[][]", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName103 = "getSectionsKBArticlesCount";

		_methodParameterTypes103 = new String[] {
				"long", "java.lang.String[][]", "int"
			};

		_methodName104 = "getSiblingKBArticles";

		_methodParameterTypes104 = new String[] {
				"long", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName105 = "getSiblingKBArticlesCount";

		_methodParameterTypes105 = new String[] { "long", "long", "int" };

		_methodName106 = "moveKBArticle";

		_methodParameterTypes106 = new String[] { "long", "long", "double" };

		_methodName107 = "subscribeGroupKBArticles";

		_methodParameterTypes107 = new String[] { "long", "java.lang.String" };

		_methodName108 = "subscribeKBArticle";

		_methodParameterTypes108 = new String[] { "long", "long" };

		_methodName109 = "unsubscribeGroupKBArticles";

		_methodParameterTypes109 = new String[] { "long", "java.lang.String" };

		_methodName110 = "unsubscribeKBArticle";

		_methodParameterTypes110 = new String[] { "long" };

		_methodName111 = "updateAttachments";

		_methodParameterTypes111 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName112 = "updateKBArticle";

		_methodParameterTypes112 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String[][]", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "updateKBArticlesPriorities";

		_methodParameterTypes113 = new String[] { "long", "java.util.Map" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return KBArticleServiceUtil.getBeanIdentifier();
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			KBArticleServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			KBArticleServiceUtil.addAttachment((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.io.InputStream)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);

			return null;
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return KBArticleServiceUtil.addKBArticle((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4],
				(java.lang.String[])arguments[5],
				(java.lang.String)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			KBArticleServiceUtil.deleteAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(), (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return KBArticleServiceUtil.deleteKBArticle(((Long)arguments[0]).longValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			KBArticleServiceUtil.deleteKBArticles(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return KBArticleServiceUtil.getGroupKBArticles(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return KBArticleServiceUtil.getGroupKBArticlesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return KBArticleServiceUtil.getGroupKBArticlesRSS(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(com.liferay.portal.theme.ThemeDisplay)arguments[4]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticle(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticleAndAllDescendants(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticleRSS(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.theme.ThemeDisplay)arguments[5]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticleSearchDisplay(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.util.Date)arguments[4], (java.util.Date)arguments[5],
				((Boolean)arguments[6]).booleanValue(), (int[])arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticleVersions(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return KBArticleServiceUtil.getKBArticleVersionsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return KBArticleServiceUtil.getLatestKBArticle(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KBArticleServiceUtil.getSectionsKBArticles(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return KBArticleServiceUtil.getSectionsKBArticlesCount(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1],
				((Integer)arguments[2]).intValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return KBArticleServiceUtil.getSiblingKBArticles(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return KBArticleServiceUtil.getSiblingKBArticlesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			KBArticleServiceUtil.moveKBArticle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Double)arguments[2]).doubleValue());

			return null;
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			KBArticleServiceUtil.subscribeGroupKBArticles(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			KBArticleServiceUtil.subscribeKBArticle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			KBArticleServiceUtil.unsubscribeGroupKBArticles(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			KBArticleServiceUtil.unsubscribeKBArticle(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return KBArticleServiceUtil.updateAttachments((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return KBArticleServiceUtil.updateKBArticle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				(java.lang.String[])arguments[4],
				(java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			KBArticleServiceUtil.updateKBArticlesPriorities(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.Long, java.lang.Double>)arguments[1]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
}
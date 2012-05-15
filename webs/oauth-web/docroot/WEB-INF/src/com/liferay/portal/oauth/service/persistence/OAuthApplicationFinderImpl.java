package com.liferay.portal.oauth.service.persistence;

import java.util.Iterator;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.model.impl.OAuthApplicationImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class OAuthApplicationFinderImpl
	extends BasePersistenceImpl<OAuthApplication> implements OAuthApplicationFinder{
	public static final String COUNT_ALL =
			OAuthApplicationFinder.class.getName() + ".countAll";
	
	public static final String COUNT_BY_NAME =
			OAuthApplicationFinder.class.getName() + ".countByName";
	
	public static final String COUNT_BY_N_O =
			OAuthApplicationFinder.class.getName() + ".countByN_O";
	
	public static final String COUNT_BY_WEBSITE =
			OAuthApplicationFinder.class.getName() + ".countByWebsite";
	
	public static final String FIND_ALL =
			OAuthApplicationFinder.class.getName() + ".findAll";
	
	public static final String FIND_BY_NAME =
			OAuthApplicationFinder.class.getName() + ".findByName";
	
	public static final String FIND_BY_N_O =
			OAuthApplicationFinder.class.getName() + ".findByN_O";
	
	public static final String FIND_BY_WEBSITE =
			OAuthApplicationFinder.class.getName() + ".findByWebsite";
	
	public int countAll()
			throws SystemException {
			
			Session session = null;
			try {
				session = openSession();
			
				String query = CustomSQLUtil.get(COUNT_ALL);
				
				SQLQuery q = session.createSQLQuery(query);
				
				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
				
				Iterator<Long> itr = q.iterate();
				
				if (itr.hasNext()) {
					Long count = itr.next();
					
					if(null != count){
						return count.intValue();
					}
				}
				
				return 0;
			}
			catch(Exception e) {
				throw new SystemException();
			}
			finally {
				closeSession(session);
			}
		}
	
	public int countByName(String name)
		throws SystemException {
		
		return countByN_O(name, 0L);
	}
	
	public int countByN_O(String name, long ownerId)
			throws SystemException {
			
			Session session = null;
			try {
				session = openSession();
				
				String query = CustomSQLUtil.get(COUNT_BY_NAME);
				
				if (ownerId > 0L) {
					query = CustomSQLUtil.get(COUNT_BY_N_O);
				}
			
				SQLQuery q = session.createSQLQuery(query);
				
				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
				
				QueryPos qPos = QueryPos.getInstance(q);
				
				qPos.add((null == name) ? "%" : name);
				
				if (ownerId > 0L) {
					qPos.add(ownerId);
				}
				
				Iterator<Long> itr = q.iterate();
				
				if (itr.hasNext()) {
					Long count = itr.next();
					
					if(null != count){
						return count.intValue();
					}
				}
				
				return 0;
			}
			catch(Exception e) {
				throw new SystemException();
			}
			finally {
				closeSession(session);
			}
		}
	
	public int countByWebsite(String website)
		throws SystemException {
		
		Session session = null;
		try {
			session = openSession();
			
			String query = CustomSQLUtil.get(COUNT_BY_WEBSITE);
			
			SQLQuery q = session.createSQLQuery(query);
			
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
			
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(website);
			
			Iterator<Long> itr = q.iterate();
			
			if (itr.hasNext()) {
				Long count = itr.next();
				
				if(null != count){
					return count.intValue();
				}
			}
			
			return 0;
		}
		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}
	
	public List<OAuthApplication> findAll(int start, int end,
				OrderByComparator orderByComparator)
			throws SystemException {
			Session session = null;
			try {
				session = openSession();
				
				String query = CustomSQLUtil.get(FIND_ALL);
				
				query = CustomSQLUtil.replaceOrderBy(
						query, orderByComparator);
			
				SQLQuery q = session.createSQLQuery(query);
				
				q.addEntity("OAuthApplication", OAuthApplicationImpl.class);
				
				return (List<OAuthApplication>)QueryUtil.list(
						q, getDialect(), start, end);
			}
			catch(Exception e) {
				throw new SystemException();
			}
			finally {
				closeSession(session);
			}
		}
	
	public List<OAuthApplication> findByName(String name, int start, int end,
				OrderByComparator orderByComparator)
			throws SystemException {
			return findByN_O(name, 0L, start, end, orderByComparator);
		}
	
	public List<OAuthApplication> findByN_O(String name, long ownerId,
			int start, int end,	OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;
		try {
			session = openSession();
			
			String query = CustomSQLUtil.get(FIND_BY_NAME);
			
			if (ownerId > 0L) {
				query = CustomSQLUtil.get(FIND_BY_N_O);
			}
			
			query = CustomSQLUtil.replaceOrderBy(
					query, orderByComparator);
			
			SQLQuery q = session.createSQLQuery(query);
			
			q.addEntity("OAuthApplication", OAuthApplicationImpl.class);
			
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add((null == name) ? "%" : name);
			
			if (ownerId > 0L) {
				qPos.add(ownerId);
			}
			
			return (List<OAuthApplication>)QueryUtil.list(
					q, getDialect(), start, end);
		}
		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}
	
	public List<OAuthApplication> findByWebsite(String website, int start, int end,
				OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;
		try {
			session = openSession();
			
			String query = CustomSQLUtil.get(FIND_BY_WEBSITE);
			
			query = CustomSQLUtil.replaceOrderBy(
					query, orderByComparator);
		
			SQLQuery q = session.createSQLQuery(query);
			
			q.addEntity("OAuthApplication", OAuthApplicationImpl.class);
			
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(website);
			
			return (List<OAuthApplication>)QueryUtil.list(
					q, getDialect(), start, end);
		}
		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}
}

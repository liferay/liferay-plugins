package com.liferay.portal.oauth.service.persistence;

import java.util.Iterator;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class OAuthApplications_UsersFinderImpl
	extends BasePersistenceImpl<OAuthApplications_Users> implements OAuthApplications_UsersFinder {
	public static final String COUNT_ALL =
			OAuthApplications_UsersFinder.class.getName() + ".countAll";
	
	public static final String COUNT_BY_OWNER_AUTHORIZED =
			OAuthApplications_UsersFinder.class.getName() + ".countByO_A";
	
	public static final String FIND_ALL =
			OAuthApplications_UsersFinder.class.getName() + ".findAll";
	
	public static final String FIND_BY_OWNER_AUTHORIZED =
			OAuthApplications_UsersFinder.class.getName() + ".findByO_A";
	
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
	
	public int countByO_A(long ownerId, boolean authorized)
			throws SystemException {
			
			Session session = null;
			try {
				session = openSession();
				
				String query = CustomSQLUtil.get(COUNT_BY_OWNER_AUTHORIZED);
				
				SQLQuery q = session.createSQLQuery(query);
				
				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
				
				QueryPos qPos = QueryPos.getInstance(q);
				
				qPos.add(ownerId);
				qPos.add(authorized);
				
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
	
	public List<OAuthApplications_Users> findAll(int start, int end,
				OrderByComparator orderByComparator)
			throws SystemException {
			Session session = null;
			try {
				session = openSession();
				
				String query = CustomSQLUtil.get(FIND_ALL);
				
				query = CustomSQLUtil.replaceOrderBy(
						query, orderByComparator);
			
				SQLQuery q = session.createSQLQuery(query);
				
				q.addEntity("OAuthApplications_Users", OAuthApplications_UsersImpl.class);
				
				return (List<OAuthApplications_Users>)QueryUtil.list(
						q, getDialect(), start, end);
			}
			catch(Exception e) {
				throw new SystemException();
			}
			finally {
				closeSession(session);
			}
		}
	
	public List<OAuthApplications_Users> findByO_A(long ownerId, boolean authorized, int start, int end,
				OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;
		try {
			session = openSession();
			
			String query = CustomSQLUtil.get(FIND_BY_OWNER_AUTHORIZED);
			
			query = CustomSQLUtil.replaceOrderBy(
					query, orderByComparator);
		
			SQLQuery q = session.createSQLQuery(query);
			
			q.addEntity("OAuthApplications_Users", OAuthApplications_UsersImpl.class);
			
			QueryPos qPos = QueryPos.getInstance(q);
			
			qPos.add(ownerId);
			qPos.add(authorized);
			
			return (List<OAuthApplications_Users>)QueryUtil.list(
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

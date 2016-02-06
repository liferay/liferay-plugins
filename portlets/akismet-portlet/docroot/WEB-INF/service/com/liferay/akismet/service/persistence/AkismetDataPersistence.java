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

package com.liferay.akismet.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.akismet.exception.NoSuchDataException;
import com.liferay.akismet.model.AkismetData;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the akismet data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.akismet.service.persistence.impl.AkismetDataPersistenceImpl
 * @see AkismetDataUtil
 * @generated
 */
@ProviderType
public interface AkismetDataPersistence extends BasePersistence<AkismetData> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AkismetDataUtil} to access the akismet data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the akismet datas where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching akismet datas
	*/
	public java.util.List<AkismetData> findByLtModifiedDate(Date modifiedDate);

	/**
	* Returns a range of all the akismet datas where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of matching akismet datas
	*/
	public java.util.List<AkismetData> findByLtModifiedDate(Date modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the akismet datas where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching akismet datas
	*/
	public java.util.List<AkismetData> findByLtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator);

	/**
	* Returns an ordered range of all the akismet datas where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching akismet datas
	*/
	public java.util.List<AkismetData> findByLtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet data
	* @throws NoSuchDataException if a matching akismet data could not be found
	*/
	public AkismetData findByLtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException;

	/**
	* Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet data, or <code>null</code> if a matching akismet data could not be found
	*/
	public AkismetData fetchByLtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator);

	/**
	* Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet data
	* @throws NoSuchDataException if a matching akismet data could not be found
	*/
	public AkismetData findByLtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException;

	/**
	* Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet data, or <code>null</code> if a matching akismet data could not be found
	*/
	public AkismetData fetchByLtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator);

	/**
	* Returns the akismet datas before and after the current akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param akismetDataId the primary key of the current akismet data
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next akismet data
	* @throws NoSuchDataException if a akismet data with the primary key could not be found
	*/
	public AkismetData[] findByLtModifiedDate_PrevAndNext(long akismetDataId,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException;

	/**
	* Removes all the akismet datas where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public void removeByLtModifiedDate(Date modifiedDate);

	/**
	* Returns the number of akismet datas where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching akismet datas
	*/
	public int countByLtModifiedDate(Date modifiedDate);

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchDataException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching akismet data
	* @throws NoSuchDataException if a matching akismet data could not be found
	*/
	public AkismetData findByC_C(long classNameId, long classPK)
		throws NoSuchDataException;

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	*/
	public AkismetData fetchByC_C(long classNameId, long classPK);

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	*/
	public AkismetData fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the akismet data where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the akismet data that was removed
	*/
	public AkismetData removeByC_C(long classNameId, long classPK)
		throws NoSuchDataException;

	/**
	* Returns the number of akismet datas where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching akismet datas
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Caches the akismet data in the entity cache if it is enabled.
	*
	* @param akismetData the akismet data
	*/
	public void cacheResult(AkismetData akismetData);

	/**
	* Caches the akismet datas in the entity cache if it is enabled.
	*
	* @param akismetDatas the akismet datas
	*/
	public void cacheResult(java.util.List<AkismetData> akismetDatas);

	/**
	* Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	*
	* @param akismetDataId the primary key for the new akismet data
	* @return the new akismet data
	*/
	public AkismetData create(long akismetDataId);

	/**
	* Removes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data that was removed
	* @throws NoSuchDataException if a akismet data with the primary key could not be found
	*/
	public AkismetData remove(long akismetDataId) throws NoSuchDataException;

	public AkismetData updateImpl(AkismetData akismetData);

	/**
	* Returns the akismet data with the primary key or throws a {@link NoSuchDataException} if it could not be found.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data
	* @throws NoSuchDataException if a akismet data with the primary key could not be found
	*/
	public AkismetData findByPrimaryKey(long akismetDataId)
		throws NoSuchDataException;

	/**
	* Returns the akismet data with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data, or <code>null</code> if a akismet data with the primary key could not be found
	*/
	public AkismetData fetchByPrimaryKey(long akismetDataId);

	@Override
	public java.util.Map<java.io.Serializable, AkismetData> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the akismet datas.
	*
	* @return the akismet datas
	*/
	public java.util.List<AkismetData> findAll();

	/**
	* Returns a range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of akismet datas
	*/
	public java.util.List<AkismetData> findAll(int start, int end);

	/**
	* Returns an ordered range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of akismet datas
	*/
	public java.util.List<AkismetData> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator);

	/**
	* Returns an ordered range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of akismet datas
	*/
	public java.util.List<AkismetData> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AkismetData> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the akismet datas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of akismet datas.
	*
	* @return the number of akismet datas
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}
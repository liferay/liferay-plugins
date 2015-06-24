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

package com.liferay.samplehibernate.util;

import com.liferay.samplehibernate.model.FoodItem;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * @author Charles May
 */
public class FoodItemUtil {

	public static void addFoodItem(FoodItem foodItem) throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			session.beginTransaction();

			session.save(foodItem);

			session.flush();

			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static void deleteFoodItem(long foodItemId) throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			session.beginTransaction();

			FoodItem foodItem = (FoodItem)session.get(
				FoodItem.class, Long.valueOf(foodItemId));

			if (foodItem != null) {
				session.delete(foodItem);

				session.flush();
			}

			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static FoodItem getFoodItem(long foodItemId) throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			session.beginTransaction();

			FoodItem foodItem = (FoodItem)session.get(
				FoodItem.class, Long.valueOf(foodItemId));

			session.getTransaction().commit();

			return foodItem;
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static List getFoodItems() throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			session.beginTransaction();

			SQLQuery q = session.createSQLQuery(_GET_FOOD_ITEMS);

			q.addEntity("FoodItem", FoodItem.class);

			List list = q.list();

			session.getTransaction().commit();

			return list;
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static void updateFoodItem(FoodItem foodItem) throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			session.beginTransaction();

			session.update(foodItem);

			session.flush();

			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	private static final String _GET_FOOD_ITEMS =
		"SELECT {FoodItem.*} FROM FoodItem ORDER BY name_ ASC";

}
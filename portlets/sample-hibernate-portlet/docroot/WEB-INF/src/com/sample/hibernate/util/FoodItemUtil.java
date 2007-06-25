/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.hibernate.util;

import com.sample.hibernate.model.FoodItem;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * <a href="FoodItemUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
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
				FoodItem.class, new Long(foodItemId));

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
				FoodItem.class, new Long(foodItemId));

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
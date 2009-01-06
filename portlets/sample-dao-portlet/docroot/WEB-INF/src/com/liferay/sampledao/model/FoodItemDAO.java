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

package com.liferay.sampledao.model;

import com.liferay.sampledao.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="FoodItemDAO.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FoodItemDAO {

	public static void addFoodItem(FoodItem foodItem) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.getConnection();

			ps = con.prepareStatement(_ADD_FOOD_ITEM);

			ps.setString(1, foodItem.getName());
			ps.setInt(2, foodItem.getPoints());

			ps.executeUpdate();
		}
		finally {
			ConnectionPool.cleanUp(con, ps);
		}
	}

	public static void deleteFoodItem(long foodItemId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.getConnection();

			ps = con.prepareStatement(_DELETE_FOOD_ITEM);

			ps.setLong(1, foodItemId);

			ps.executeUpdate();
		}
		finally {
			ConnectionPool.cleanUp(con, ps);
		}
	}

	public static FoodItem getFoodItem(long foodItemId) throws SQLException {
		FoodItem foodItem = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getConnection();

			ps = con.prepareStatement(_GET_FOOD_ITEM);

			ps.setLong(1, foodItemId);

			rs = ps.executeQuery();

			if (rs.next()) {
				foodItem = new FoodItem();

				foodItem.setFoodItemId(foodItemId);
				foodItem.setName(rs.getString(2));
				foodItem.setPoints(rs.getInt(3));
			}
		}
		finally {
			ConnectionPool.cleanUp(con, ps, rs);
		}

		return foodItem;
	}

	public static List getFoodItems() throws SQLException {
		List list = new ArrayList();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getConnection();

			ps = con.prepareStatement(_GET_FOOD_ITEMS);

			rs = ps.executeQuery();

			while (rs.next()) {
				FoodItem foodItem = new FoodItem();

				foodItem.setFoodItemId(rs.getLong(1));
				foodItem.setName(rs.getString(2));
				foodItem.setPoints(rs.getInt(3));

				list.add(foodItem);
			}
		}
		finally {
			ConnectionPool.cleanUp(con, ps, rs);
		}

		return list;
	}

	public static void updateFoodItem(FoodItem foodItem) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionPool.getConnection();

			ps = con.prepareStatement(_UPDATE_FOOD_ITEM);

			ps.setString(1, foodItem.getName());
			ps.setInt(2, foodItem.getPoints());
			ps.setLong(3, foodItem.getFoodItemId());

			ps.executeUpdate();
		}
		finally {
			ConnectionPool.cleanUp(con, ps);
		}
	}

	private static final String _ADD_FOOD_ITEM =
		"INSERT INTO FoodItem (name_, points) VALUES (?, ?)";

	private static final String _DELETE_FOOD_ITEM =
		"DELETE FROM FoodItem WHERE foodItemId = ?";

	private static final String _GET_FOOD_ITEM =
		"SELECT foodItemId, name_, points FROM FoodItem WHERE foodItemId = ?";

	private static final String _GET_FOOD_ITEMS =
		"SELECT foodItemId, name_, points FROM FoodItem ORDER BY name_ ASC";

	private static final String _UPDATE_FOOD_ITEM =
		"UPDATE FoodItem SET name_ = ?, points = ? WHERE foodItemId = ?";

}
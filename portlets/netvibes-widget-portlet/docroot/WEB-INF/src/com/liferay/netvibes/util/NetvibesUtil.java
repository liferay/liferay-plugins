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

package com.liferay.netvibes.util;

import com.liferay.netvibes.model.NetvibesCategory;
import com.liferay.netvibes.model.NetvibesRegion;
import com.liferay.netvibes.model.NetvibesSort;
import com.liferay.netvibes.model.NetvibesType;
import com.liferay.netvibes.model.NetvibesWidget;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="NetvibesUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Julio Camarero
 */
public class NetvibesUtil {

	public final static int NUMBER_OF_WIDGETS_PER_PAGE = 20;

	public static int getNumberOfWidgets () {
		return _numberOfWidgets;
	}

	public static List<NetvibesCategory> getNetvibesCategories()
		throws Exception{

		List<NetvibesCategory> categories = new ArrayList<NetvibesCategory>();

		String jsontext = getJSONNetvibesCategories();
		JSONObject received = new JSONObject (jsontext);
		JSONArray jsonCategories = received.getJSONArray("categories");

		for (int i = 0; i < jsonCategories.length(); i++) {
			JSONObject category = jsonCategories.getJSONObject(i);
			categories.add(new NetvibesCategory(
				category.getInt("id"), category.getString("label")));
		}

		return categories;
	}

	public static List<NetvibesType> getNetvibesTypes(){
		List<NetvibesType> types = new ArrayList<NetvibesType>();
		types.add(new NetvibesType("1","miniapi"));
		types.add(new NetvibesType("2","feed"));
		types.add(new NetvibesType("3","event"));
		types.add(new NetvibesType("4","podcast"));
		types.add(new NetvibesType("5","tab"));
		types.add(new NetvibesType("6","uwa"));
		types.add(new NetvibesType("7","universe"));
		types.add(new NetvibesType("8","widget"));
		types.add(new NetvibesType("9","application"));
		types.add(new NetvibesType("10","flash"));
		types.add(new NetvibesType("11","native"));
		types.add(new NetvibesType("12","multifeed"));

		return types;
	}

	public static List<NetvibesRegion> getNetvibesRegions(){
		List<NetvibesRegion> regions = new ArrayList<NetvibesRegion>();

		regions.add(new NetvibesRegion("al","Albania"));
		regions.add(new NetvibesRegion("dz","Algeria"));
		regions.add(new NetvibesRegion("ar","Argentina"));
		regions.add(new NetvibesRegion("au","Australia"));
		regions.add(new NetvibesRegion("at","Austria"));
		regions.add(new NetvibesRegion("bh","Bahreïn"));
		regions.add(new NetvibesRegion("pv","Basque Country"));
		regions.add(new NetvibesRegion("by","Belarus"));
		regions.add(new NetvibesRegion("be","Belgium (dutch)"));
		regions.add(new NetvibesRegion("bef","Belgium (french)"));
		regions.add(new NetvibesRegion("bz","Belize"));
		regions.add(new NetvibesRegion("bo","Bolivia"));
		regions.add(new NetvibesRegion("ba","Bosnia and Herzegovina"));
		regions.add(new NetvibesRegion("br","Brazil"));
		regions.add(new NetvibesRegion("bg","Bulgaria"));
		regions.add(new NetvibesRegion("cm","Cameroon"));
		regions.add(new NetvibesRegion("ca","Canada"));
		regions.add(new NetvibesRegion("qc","Canada (Quebec)"));
		regions.add(new NetvibesRegion("cl","Chile"));
		regions.add(new NetvibesRegion("cn","China"));
		regions.add(new NetvibesRegion("hk","China (Hong Kong)"));
		regions.add(new NetvibesRegion("tw","China (Taiwan)"));
		regions.add(new NetvibesRegion("co","Colombia"));
		regions.add(new NetvibesRegion("cd","Congo"));
		regions.add(new NetvibesRegion("cr","Costa Rica"));
		regions.add(new NetvibesRegion("hr","Croatia"));
		regions.add(new NetvibesRegion("cy","Cyprus"));
		regions.add(new NetvibesRegion("cz","Czech Republic"));
		regions.add(new NetvibesRegion("dk","Denmark"));
		regions.add(new NetvibesRegion("ec","Ecuador"));
		regions.add(new NetvibesRegion("eg","Egypt"));
		regions.add(new NetvibesRegion("sv","El Salvador"));
		regions.add(new NetvibesRegion("ee","Estonia"));
		regions.add(new NetvibesRegion("et","Ethiopia"));
		regions.add(new NetvibesRegion("fk","Falkland Islands"));
		regions.add(new NetvibesRegion("fi","Finland"));
		regions.add(new NetvibesRegion("fr","France"));
		regions.add(new NetvibesRegion("de","Germany"));
		regions.add(new NetvibesRegion("gr","Greece"));
		regions.add(new NetvibesRegion("gra","Greece (Attica)"));
		regions.add(new NetvibesRegion("gt","Guatemala"));
		regions.add(new NetvibesRegion("gy","Guyana"));
		regions.add(new NetvibesRegion("hn","Honduras"));
		regions.add(new NetvibesRegion("hu","Hungary"));
		regions.add(new NetvibesRegion("is","Iceland"));
		regions.add(new NetvibesRegion("in","India"));
		regions.add(new NetvibesRegion("id","Indonesia"));
		regions.add(new NetvibesRegion("ir","Iran"));
		regions.add(new NetvibesRegion("iq","Iraq"));
		regions.add(new NetvibesRegion("ie","Ireland"));
		regions.add(new NetvibesRegion("il","Israel"));
		regions.add(new NetvibesRegion("it","Italy"));
		regions.add(new NetvibesRegion("ci","Ivory Coast"));
		regions.add(new NetvibesRegion("jp","Japan"));
		regions.add(new NetvibesRegion("jo","Jordan"));
		regions.add(new NetvibesRegion("ke","Kenya"));
		regions.add(new NetvibesRegion("ko","Kosovo"));
		regions.add(new NetvibesRegion("kw","Kuwait"));
		regions.add(new NetvibesRegion("lv","Latvia"));
		regions.add(new NetvibesRegion("lb","Lebanon"));
		regions.add(new NetvibesRegion("lt","Lithuania"));
		regions.add(new NetvibesRegion("lu","Luxembourg"));
		regions.add(new NetvibesRegion("ly","Lybia"));
		regions.add(new NetvibesRegion("my","Malaysia"));
		regions.add(new NetvibesRegion("mr","Mauritania"));
		regions.add(new NetvibesRegion("mx","Mexico"));
		regions.add(new NetvibesRegion("mn","Mongolia"));
		regions.add(new NetvibesRegion("ma","Morocco"));
		regions.add(new NetvibesRegion("nl","Netherlands"));
		regions.add(new NetvibesRegion("nlf","Netherlands (Friesland)"));
		regions.add(new NetvibesRegion("nz","New Zealand"));
		regions.add(new NetvibesRegion("ni","Nicaragua"));
		regions.add(new NetvibesRegion("ng","Nigeria"));
		regions.add(new NetvibesRegion("no","Norway"));
		regions.add(new NetvibesRegion("pk","Pakistan"));
		regions.add(new NetvibesRegion("ps","Palestine"));
		regions.add(new NetvibesRegion("pa","Panama"));
		regions.add(new NetvibesRegion("py","Paraguay"));
		regions.add(new NetvibesRegion("pe","Peru"));
		regions.add(new NetvibesRegion("ph","Philippines"));
		regions.add(new NetvibesRegion("pl","Poland"));
		regions.add(new NetvibesRegion("pt","Portugal"));
		regions.add(new NetvibesRegion("ro","Romania"));
		regions.add(new NetvibesRegion("ru","Russia"));
		regions.add(new NetvibesRegion("sa","Saudi Arabia"));
		regions.add(new NetvibesRegion("sn","Senegal"));
		regions.add(new NetvibesRegion("rs","Serbia"));
		regions.add(new NetvibesRegion("sg","Singapore"));
		regions.add(new NetvibesRegion("sk","Slovakia"));
		regions.add(new NetvibesRegion("si","Slovenia"));
		regions.add(new NetvibesRegion("so","Somalia"));
		regions.add(new NetvibesRegion("kr","South Korea"));
		regions.add(new NetvibesRegion("za","South Africa"));
		regions.add(new NetvibesRegion("ct","Spain (Catalonia)"));
		regions.add(new NetvibesRegion("es","Spain"));
		regions.add(new NetvibesRegion("ga","Spain (Galicia)"));
		regions.add(new NetvibesRegion("sr","Suriname"));
		regions.add(new NetvibesRegion("se","Sweden"));
		regions.add(new NetvibesRegion("ch","Switzerland"));
		regions.add(new NetvibesRegion("chf","Switzerland (french)"));
		regions.add(new NetvibesRegion("sy","Syria"));
		regions.add(new NetvibesRegion("th","Thailand"));
		regions.add(new NetvibesRegion("tn","Tunisia"));
		regions.add(new NetvibesRegion("tr","Turkey"));
		regions.add(new NetvibesRegion("ua","Ukraina"));
		regions.add(new NetvibesRegion("ae","United Arab Emirates"));
		regions.add(new NetvibesRegion("gb","United Kingdom"));
		regions.add(new NetvibesRegion("us","United States"));
		regions.add(new NetvibesRegion("uy","Uruguay"));
		regions.add(new NetvibesRegion("ve","Venezuela"));
		regions.add(new NetvibesRegion("vn","Vietnam"));

		return regions;
	}

	public static List<NetvibesSort> getNetvibesSorts(){
		List<NetvibesSort> sorts = new ArrayList<NetvibesSort>();
		sorts.add(new NetvibesSort("popular", "Most Popular"));
		sorts.add(new NetvibesSort("recent", "Most Recent"));

		return sorts;
	}

	public static List<NetvibesWidget> getNetvibesWidgets(
		String query, String sort,
		int category, String region, int page) throws Exception {

		List<NetvibesWidget> widgets = new ArrayList<NetvibesWidget>();

		String searchResults =
			getSearchResult(query, sort, category, region, page);

		if (Validator.isNotNull(searchResults)) {
			JSONObject received = new JSONObject(searchResults);

			_numberOfWidgets = received.getInt("total");

			JSONArray jsonWidgets =received.getJSONArray("items");

			for (int i = 0; i < jsonWidgets.length(); i++) {
				JSONObject widget = jsonWidgets.getJSONObject(i);
				widgets.add(
					new NetvibesWidget(
						 widget.getString("title"),
						 widget.getString("summary"),
						 widget.getString("link"),
						 widget.getString("thumbnail"),
						 widget.getString("author")
					)
				);
			}
		}

		return widgets;
	}

	protected static String getJSONNetvibesCategories() {

		WebCacheItem wci = new NetvibesCategoryWebCacheItem();

		String key =
			NetvibesUtil.class.getName() + StringPool.PERIOD + "category";

		try {
			return (String) WebCachePoolUtil.get(key, wci);
		}
		catch (ClassCastException cce) {
			WebCachePoolUtil.remove(key);

			return (String) WebCachePoolUtil.get(key, wci);
		}
	}

	protected static String getSearchResult(
		String query, String sort,
		int category, String region, int page) {

		WebCacheItem wci = new NetvibesSearchResultWebCacheItem(
			query, sort, category, region, page);

		String key = NetvibesUtil.class.getName() + StringPool.PERIOD + query +
			StringPool.PERIOD + sort + StringPool.PERIOD + category +
				StringPool.PERIOD + region + StringPool.PERIOD + page;

		String result = (String) WebCachePoolUtil.get(key, wci);

		if (Validator.isNotNull(result)) {
			return result;
		}
		else {
			WebCachePoolUtil.remove(key);

			return (String) WebCachePoolUtil.get(key, wci);
		}
	}

	private static int _numberOfWidgets = 0;

}
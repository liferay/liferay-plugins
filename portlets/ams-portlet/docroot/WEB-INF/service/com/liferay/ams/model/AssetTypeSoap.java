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

package com.liferay.ams.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="AssetTypeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetTypeSoap implements Serializable {
	public static AssetTypeSoap toSoapModel(AssetType model) {
		AssetTypeSoap soapModel = new AssetTypeSoap();

		soapModel.setAssetTypeId(model.getAssetTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static AssetTypeSoap[] toSoapModels(List<AssetType> models) {
		List<AssetTypeSoap> soapModels = new ArrayList<AssetTypeSoap>(models.size());

		for (AssetType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetTypeSoap[soapModels.size()]);
	}

	public AssetTypeSoap() {
	}

	public long getPrimaryKey() {
		return _assetTypeId;
	}

	public void setPrimaryKey(long pk) {
		setAssetTypeId(pk);
	}

	public long getAssetTypeId() {
		return _assetTypeId;
	}

	public void setAssetTypeId(long assetTypeId) {
		_assetTypeId = assetTypeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _assetTypeId;
	private long _groupId;
	private String _name;
}
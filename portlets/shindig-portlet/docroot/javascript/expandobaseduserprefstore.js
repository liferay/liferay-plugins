/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

gadgets.ExpandoBasedUserPrefStore = function() {
  gadgets.UserPrefStore.call(this);
};

gadgets.ExpandoBasedUserPrefStore.inherits(gadgets.UserPrefStore);

gadgets.ExpandoBasedUserPrefStore.prototype.USER_PREFS_PREFIX = 'gadgetUserPrefs-';

gadgets.ExpandoBasedUserPrefStore.prototype.getPrefs = function(gadget) {
 	var userPrefs = {};
 	var columnName = this.USER_PREFS_PREFIX + gadget.id;

	var expandoColumn = Liferay.Service.Expando.ExpandoColumn.getColumn({
		tableId: 9,
		name: columnName
	});

	if (!expandoColumn.exception) {
		var expandoValue = Liferay.Service.Expando.ExpandoValue.getValue({
			columnId: expandoColumn.columnId,
			rowId: themeDisplay.getUserId()
		});

		if (expandoValue.data) {
			var pairs = expandoValue.data.split('&');
			for (var i = 0; i < pairs.length; i++) {
			  var nameValue = pairs[i].split('=');
			  var name = decodeURIComponent(nameValue[0]);
			  var value = decodeURIComponent(nameValue[1]);
			  userPrefs[name] = value;
			}
		}
	}

	return userPrefs;
};

gadgets.ExpandoBasedUserPrefStore.prototype.savePrefs = function(gadget) {
	var pairs = [];

	for (var name in gadget.getUserPrefs()) {
		var value = gadget.getUserPref(name);
		var pair = encodeURIComponent(name) + '=' + encodeURIComponent(value);
		pairs.push(pair);
	}

	var values = pairs.join('&');

 	var columnName = this.USER_PREFS_PREFIX + gadget.id;

	var expandoColumn = Liferay.Service.Expando.ExpandoColumn.getColumn({
		tableId: 9,
		name: columnName
	});

	if (expandoColumn.exception) {
		expandoColumn = Liferay.Service.Expando.ExpandoColumn.addColumn({
			tableId: 9,
			name: columnName,
			type: 13
		});
	}

	Liferay.Service.Expando.ExpandoValue.addValue({
		columnId: expandoColumn.columnId,
		rowId: themeDisplay.getUserId(),
		value: values
	});
};

gadgets.Container.prototype.userPrefStore = new gadgets.ExpandoBasedUserPrefStore();
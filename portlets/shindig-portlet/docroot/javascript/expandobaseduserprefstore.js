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

gadgets.ExpandoBasedUserPrefStore.prototype.USER_PREFS_PREFIX = 'GADGET_USER_PREFERENCES';

gadgets.ExpandoBasedUserPrefStore.prototype.getPrefs = function(gadget) {
 	var userPrefs = {};

	var expandoValue = Liferay.Service.Expando.ExpandoValue.getValue({
		className: 'com.liferay.portal.model.User',
		tableName: this.USER_PREFS_PREFIX,
		columnName: gadget.portletId,
		classPK: themeDisplay.getUserId(),
		serviceParameterTypes: [
			'java.lang.String',
			'java.lang.String',
			'java.lang.String',
			'long'
		].join(',')
	});

	if (!expandoValue.exception && expandoValue.data) {
		var pairs = expandoValue.data.split('&');
		for (var i = 0; i < pairs.length; i++) {
		  var nameValue = pairs[i].split('=');
		  var name = decodeURIComponent(nameValue[0]);
		  var value = decodeURIComponent(nameValue[1]);
		  userPrefs[name] = value;
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

	Liferay.Service.Expando.ExpandoValue.addValue({
		className: 'com.liferay.portal.model.User',
		tableName: this.USER_PREFS_PREFIX,
		columnName: gadget.portletId,
		classPK: themeDisplay.getUserId(),
		value: values,
		serviceParameterTypes: [
			'java.lang.String',
			'java.lang.String',
			'java.lang.String',
			'long',
			'java.lang.String'
		].join(',')
	});
};

gadgets.Container.prototype.userPrefStore = new gadgets.ExpandoBasedUserPrefStore();
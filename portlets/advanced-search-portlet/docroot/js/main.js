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

AUI().add(
	'liferay-plugin-advancedsearch',
	function(A) {
		Liferay.AdvancedSearch = {
			handleTagEvent: function(term, fieldName, namespace, randomNamespace) {
				var instance = this;

				var wasSelfSelected = false;

				var field = document[namespace + 'fm'][namespace + fieldName];

				var currentTerms = A.all('.advanced-search-portlet .menu .' + randomNamespace + 'asset_tags .entry.current-term a');

				if (currentTerms) {
					currentTerms.each(
						function(item, index, collection) {
							item.ancestor('.entry').removeClass('current-term');

							if (item == term) {
								wasSelfSelected = true;
							}
						}
					);

					field.value = '';
				}

				if (!wasSelfSelected) {
					term.ancestor('.entry').addClass('current-term');

					field.value = term.text();
				}

				event.halt();
			}
		};
	},
	'',
	{
		requires: ['aui-base']
	}
);
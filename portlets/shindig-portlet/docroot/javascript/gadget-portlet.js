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

gadgets.StaticLayoutManager.prototype.addGadgetChromeId = function(gadgetChromeId) {
	if (!this.gadgetChromeIds_) {
	  	this.gadgetChromeIds_ = [];
	}

  	this.gadgetChromeIds_.push(gadgetChromeId);
};

gadgets.IfrGadget.prototype.getTitleBarContent = function(continuation) {
	var nl = '\n';
	continuation(
		[
			'<div id="',this.cssClassTitleBar,'-',this.id,'" class="',this.cssClassTitleBar,'"><!----></div>'
		].join("")
	);
};

gadgets.IfrGadget.prototype.getUserPrefsDialogContent = function(continuation) {
	continuation(
		[
			'<div id="',this.getUserPrefsDialogId(),'" class="',this.cssClassGadgetUserPrefsDialog,'"><!----></div>'
		].join("")
	);
};

/*
jQuery(document).last(
	function() {
		gadgets.container.renderGadgets();
	}
);
*/
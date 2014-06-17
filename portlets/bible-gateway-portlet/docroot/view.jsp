<%--
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
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:tabs
	names="passage-lookup,passage-comparison,word-search"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<aui:form method="get" name='<%= renderResponse.getNamespace() + "fm1" %>' onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "lookup();" %>' portletNamespace="" target="_blank">
			<aui:input name="showfn" type="hidden" value="yes" />
			<aui:input name="showxref" type="hidden" value="yes" />

			<aui:fieldset>
				<aui:input label="" name="passage" size="30" />

				<aui:select label="" name="version">
					<aui:option label="esv" value="ESV" />
					<aui:option label="niv" value="NIV" />
					<aui:option label="nasb" value="NASB" />
					<aui:option label="msg" value="MSG" />
					<aui:option label="amp" value="AMP" />
					<aui:option label="nlt" value="NLT" />
					<aui:option label="kjv" value="KJV" />
					<aui:option label="nkjv" value="NKJV" />
				</aui:select>

				<aui:input checked="true" label="footnotes" name="showfn_cb" type="checkbox" />

				<aui:input checked="true" label="cross-references" name="showxref_cb" type="checkbox" />
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="lookup" />
			</aui:button-row>
		</aui:form>
	</liferay-ui:section>
	<liferay-ui:section>
		<aui:form name="fm2" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "compare();" %>'>
			<aui:fieldset>
				<aui:input label="" name="passage" size="30" type="text" />

				<aui:input id="version_esv" label="esv" name="version" type="checkbox" value="ESV" />

				<aui:input id="version_niv" label="niv" name="version" type="checkbox" value="NIV" />

				<aui:input id="version_nasb" label="nasb" name="version" type="checkbox" value="NASB" />

				<aui:input id="version_msg" label="msg" name="version" type="checkbox" value="MSG" />

				<aui:input id="version_amp" label="amp" name="version" type="checkbox" value="AMP" />

				<aui:input id="version_nlt" label="nlt" name="version" type="checkbox" value="NLT" />

				<aui:input id="version_kjv" label="kjv" name="version" type="checkbox" value="KJV" />
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="compare" />
			</aui:button-row>
		</aui:form>
	</liferay-ui:section>
	<liferay-ui:section>
		<aui:form method="get" name='<%= renderResponse.getNamespace() + "fm3" %>' onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "search();" %>' portletNamespace="" target="_blank">
			<aui:fieldset>
				<aui:input label="" name="search" size="30" type="text" />

				<aui:select label="" name="version">
					<aui:option label="esv" value="ESV" />
					<aui:option label="niv" value="NIV" />
					<aui:option label="nasb" value="NASB" />
					<aui:option label="msg" value="MSG" />
					<aui:option label="amp" value="AMP" />
					<aui:option label="nlt" value="NLT" />
					<aui:option label="kjv" value="KJV" />
					<aui:option label="nkjv" value="NKJV" />
				</aui:select>

				<aui:select label="" name="SearchType">
					<aui:option label="all-words" value="AND" />
					<aui:option label="any-words" value="OR" />
					<aui:option label="exact-phrase" value="EXACT" />
					<aui:option label="similar-phrase" value="FULL_TEXT" />
				</aui:select>

				<aui:input label="match-whole-words" name="SearchWholeWords" type="checkbox" value="yes" />

				<aui:input label="references-only" name="SearchAsLink" type="checkbox" value="yes" />

				<aui:select label="restrict-to" name="restrict" onChange='<%= renderResponse.getNamespace() + "resetA();" %>'>
					<aui:option label="" value="" />
					<aui:option label="apocalyptic-books" value="Apocalyptic_Books" />
					<aui:option label="books-of-moses" value="Books_of_Moses" />
					<aui:option label="epistles" value="Epistles" />
					<aui:option label="gospels" value="Gospels" />
					<aui:option label="major-prophets" value="Major_Prophets" />
					<aui:option label="minor-prophets" value="Minor_Prophets" />
					<aui:option label="new-testament" value="New_Testament" />
					<aui:option label="old-testament" value="Old_Testament" />
					<aui:option label="ot-historical-books" value="OT_Historical_Books" />
					<aui:option label="pauline-epistles" value="Pauline_Epistles" />
					<aui:option label="wisdom-books" value="Wisdom_Books" />
				</aui:select>

				<aui:select label="start-from" name="StartRestrict" onChange='<%= renderResponse.getNamespace() + "resetB();" %>'>
					<aui:option label="" value="" />
					<aui:option label="genesis" value="GEN" />
					<aui:option label="exodus" value="EXOD" />
					<aui:option label="leviticus" value="LEV" />
					<aui:option label="numbers" value="NUM" />
					<aui:option label="deuteronomy" value="DEUT" />
					<aui:option label="joshua" value="JOSH" />
					<aui:option label="judges" value="JUDG" />
					<aui:option label="ruth" value="RUTH" />
					<aui:option label="1-samuel" value="1SAM" />
					<aui:option label="2-samuel" value="2SAM" />
					<aui:option label="1-kings" value="1KGS" />
					<aui:option label="2-kings" value="2KGS" />
					<aui:option label="1-chronicles" value="1CHRON" />
					<aui:option label="2-chronicles" value="2CHRON" />
					<aui:option label="ezra" value="EZRA" />
					<aui:option label="nehemiah" value="NEH" />
					<aui:option label="esther" value="ESTH" />
					<aui:option label="job" value="JOB" />
					<aui:option label="psalm" value="PS" />
					<aui:option label="proverbs" value="PROV" />
					<aui:option label="ecclesiastes" value="ECC" />
					<aui:option label="song-of-solomon" value="SONG" />
					<aui:option label="isaiah" value="ISA" />
					<aui:option label="jeremiah" value="JER" />
					<aui:option label="lamentations" value="LAM" />
					<aui:option label="ezekiel" value="EZEK" />
					<aui:option label="daniel" value="DAN" />
					<aui:option label="hosea" value="HOSEA" />
					<aui:option label="joel" value="JOEL" />
					<aui:option label="amos" value="AMOS" />
					<aui:option label="obadiah" value="OBAD" />
					<aui:option label="jonah" value="JONAH" />
					<aui:option label="micah" value="MICAH" />
					<aui:option label="nahum" value="NAHUM" />
					<aui:option label="habakkuk" value="HAB" />
					<aui:option label="zephaniah" value="ZEPH" />
					<aui:option label="haggai" value="HAG" />
					<aui:option label="zechariah" value="ZECH" />
					<aui:option label="malachi" value="MAL" />
					<aui:option label="matthew" value="MATT" />
					<aui:option label="mark" value="MARK" />
					<aui:option label="luke" value="LUKE" />
					<aui:option label="john" value="JOHN" />
					<aui:option label="acts" value="ACTS" />
					<aui:option label="romans" value="ROM" />
					<aui:option label="1-corinthians" value="1COR" />
					<aui:option label="2-corinthians" value="2COR" />
					<aui:option label="Galatians" value="GAL" />
					<aui:option label="ephesians" value="EPH" />
					<aui:option label="philippians" value="PHIL" />
					<aui:option label="colossians" value="COL" />
					<aui:option label="1-thessalonians" value="1THES" />
					<aui:option label="2-thessalonians" value="2THES" />
					<aui:option label="1-timothy" value="1TIM" />
					<aui:option label="2-timothy" value="2TIM" />
					<aui:option label="titus" value="TIT" />
					<aui:option label="philemon" value="PHILEM" />
					<aui:option label="hebrews" value="HEB" />
					<aui:option label="james" value="JAS" />
					<aui:option label="1-peter" value="1PET" />
					<aui:option label="2-peter" value="2PET" />
					<aui:option label="1-john" value="1JOHN" />
					<aui:option label="2-john" value="2JOHN" />
					<aui:option label="3-john" value="3JOHN" />
					<aui:option label="jude" value="JUDE" />
					<aui:option label="revelation" value="REV" />
				</aui:select>

				<aui:select label="end-at" name="EndRestrict" onChange='<%= renderResponse.getNamespace() + "resetC();" %>'>
					<aui:option label="" value="" />
					<aui:option label="genesis" value="GEN" />
					<aui:option label="exodus" value="EXOD" />
					<aui:option label="leviticus" value="LEV" />
					<aui:option label="numbers" value="NUM" />
					<aui:option label="deuteronomy" value="DEUT" />
					<aui:option label="joshua" value="JOSH" />
					<aui:option label="judges" value="JUDG" />
					<aui:option label="ruth" value="RUTH" />
					<aui:option label="1-samuel" value="1SAM" />
					<aui:option label="2-samuel" value="2SAM" />
					<aui:option label="1-kings" value="1KGS" />
					<aui:option label="2-kings" value="2KGS" />
					<aui:option label="1-chronicles" value="1CHRON" />
					<aui:option label="2-chronicles" value="2CHRON" />
					<aui:option label="ezra" value="EZRA" />
					<aui:option label="nehemiah" value="NEH" />
					<aui:option label="esther" value="ESTH" />
					<aui:option label="job" value="JOB" />
					<aui:option label="psalm" value="PS" />
					<aui:option label="proverbs" value="PROV" />
					<aui:option label="ecclesiastes" value="ECC" />
					<aui:option label="song-of-solomon" value="SONG" />
					<aui:option label="isaiah" value="ISA" />
					<aui:option label="jeremiah" value="JER" />
					<aui:option label="lamentations" value="LAM" />
					<aui:option label="ezekiel" value="EZEK" />
					<aui:option label="daniel" value="DAN" />
					<aui:option label="hosea" value="HOSEA" />
					<aui:option label="joel" value="JOEL" />
					<aui:option label="amos" value="AMOS" />
					<aui:option label="obadiah" value="OBAD" />
					<aui:option label="jonah" value="JONAH" />
					<aui:option label="micah" value="MICAH" />
					<aui:option label="nahum" value="NAHUM" />
					<aui:option label="habakkuk" value="HAB" />
					<aui:option label="zephaniah" value="ZEPH" />
					<aui:option label="haggai" value="HAG" />
					<aui:option label="zechariah" value="ZECH" />
					<aui:option label="malachi" value="MAL" />
					<aui:option label="matthew" value="MATT" />
					<aui:option label="mark" value="MARK" />
					<aui:option label="luke" value="LUKE" />
					<aui:option label="john" value="JOHN" />
					<aui:option label="acts" value="ACTS" />
					<aui:option label="romans" value="ROM" />
					<aui:option label="1-corinthians" value="1COR" />
					<aui:option label="2-corinthians" value="2COR" />
					<aui:option label="galatians" value="GAL" />
					<aui:option label="ephesians" value="EPH" />
					<aui:option label="philippians" value="PHIL" />
					<aui:option label="colossians" value="COL" />
					<aui:option label="1-thessalonians" value="1THES" />
					<aui:option label="2-thessalonians" value="2THES" />
					<aui:option label="1-timothy" value="1TIM" />
					<aui:option label="2-timothy" value="2TIM" />
					<aui:option label="titus" value="TIT" />
					<aui:option label="philemon" value="PHILEM" />
					<aui:option label="hebrews" value="HEB" />
					<aui:option label="james" value="JAS" />
					<aui:option label="1-peter" value="1PET" />
					<aui:option label="2-peter" value="2PET" />
					<aui:option label="1-john" value="1JOHN" />
					<aui:option label="2-john" value="2JOHN" />
					<aui:option label="3-john" value="3JOHN" />
					<aui:option label="jude" value="JUDE" />
					<aui:option label="revelation" value="REV" />
				</aui:select>
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="search" />
			</aui:button-row>
		</aui:form>
	</liferay-ui:section>
</liferay-ui:tabs>

<aui:script>
	var A = AUI();

	function <portlet:namespace />compare() {
		var url = 'http://bible.gospelcom.net/cgi-bin/bible?showfn=yes&passage=' + encodeURIComponent(A.one('#<portlet:namespace />passage').val());

		var versionCheckboxes = A.all('input[name=<portlet:namespace />version]');

		A.each(
			versionCheckboxes,
			function(item, index) {
				if (item.attr('checked')) {
					url += '&' + item.val() + '_version=yes';
				}
			}
		);

		window.open(url);

		return false;
	}

	function <portlet:namespace />lookup() {
		if (!A.one('input[name=showfn_cb]').attr('checked')) {
			A.one('#showfn').val('no');
		}

		if (!A.one('input[name=showxref_cb]').attr('checked')) {
			A.one('#showxref').val('no');
		}

		submitForm(document.<portlet:namespace />fm1, 'http://www.biblegateway.com/cgi-bin/bible', false);

		return false;
	}

	function <portlet:namespace />search() {
		submitForm(document.<portlet:namespace />fm3, 'http://www.biblegateway.com/cgi-bin/bible', false);

		return false;
	}

	function <portlet:namespace />resetA() {
		A.one('#StartRestrict').attr('selectedIndex', 0);

		A.one('#EndRestrict').attr('selectedIndex', 0);
	}

	function <portlet:namespace />resetB() {
		A.one('#restrict').attr('selectedIndex', 0);

		var startRestrict = A.one('#StartRestrict');
		var endRestrict = A.one('#EndRestrict');

		var text = startRestrict.val();

		if (startRestrict.attr('selectedIndex') > endRestrict.attr('selectedIndex')) {
			A.each(
				endRestrict.all('option'),
				function(item, index) {
					if (item.val() == text) {
						endRestrict.attr('selectedIndex', index);
					}
				}
			);
		}
	}

	function <portlet:namespace />resetC() {
		A.one('#restrict').attr('selectedIndex', 0);

		var startRestrict = A.one('#StartRestrict');
		var endRestrict = A.one('#EndRestrict');

		var text = endRestrict.val();

		if (startRestrict.attr('selectedIndex') == 0) {
			startRestrict.attr('selectedIndex', endRestrict.attr('selectedIndex'));
		}
		else if (startRestrict.attr('selectedIndex') > endRestrict.attr('selectedIndex')) {
			A.each(
				endRestrict.all('option'),
				function(item, index) {
					if (item.val() == text) {
						startRestrict.attr('selectedIndex', index);
					}
				}
			);
		}
	}
</aui:script>
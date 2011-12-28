<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-ui:tabs
	names="passage-lookup,passage-comparison,word-search"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<form method="get" target="_blank" name="<portlet:namespace />fm1" onSubmit="if (!document.<portlet:namespace />fm1.showfn_cb.checked) { document.<portlet:namespace />fm1.showfn.value = 'no'; } if (!document.<portlet:namespace />fm1.showxref_cb.checked) { document.<portlet:namespace />fm1.showxref.value = 'no'; } submitForm(document.<portlet:namespace />fm1, 'http://www.biblegateway.com/cgi-bin/bible', false); return false;">
		<input name="showfn" type="hidden" value="yes" />
		<input name="showxref" type="hidden" value="yes" />

		<input name="passage" type="text" size="30" />

		<select name="version">
			<option value="ESV">ESV</option>
			<option value="NIV">NIV</option>
			<option value="NASB">NASB</option>
			<option value="MSG">MSG</option>
			<option value="AMP">AMP</option>
			<option value="NLT">NLT</option>
			<option value="KJV">KJV</option>
			<option value="NKJV">NKJV</option>
		</select>

		<input checked name="showfn_cb" type="checkbox" /> <span style="font-size: xx-small;"><liferay-ui:message key="footnotes" /></span>

		<input checked name="showxref_cb" type="checkbox" /> <span style="font-size: xx-small;"><liferay-ui:message key="cross-references" /></span>

		<br /><br />

		<input type="submit" value="<liferay-ui:message key="lookup" />" />

		</form>
	</liferay-ui:section>
	<liferay-ui:section>
		<form name="<portlet:namespace />fm2"
			onSubmit="
				var url = 'http://bible.gospelcom.net/cgi-bin/bible?showfn=yes&passage=' + encodeURIComponent(document.<portlet:namespace />fm2.<portlet:namespace />passage.value);

				for (var i = 0; i < document.<portlet:namespace />fm2.<portlet:namespace />version.length; i++) {
					if (document.<portlet:namespace />fm2.<portlet:namespace />version[i].checked) {
						url += '&' + document.<portlet:namespace />fm2.<portlet:namespace />version[i].value + '_version=yes';
					}
				}

				window.open(url);

				return false;"
		>

		<input name="<portlet:namespace />passage" type="text" size="30" /><br />

		<input name="<portlet:namespace />version" type="checkbox" value="ESV" /> <span style="font-size: xx-small;">ESV</span>

		<input name="<portlet:namespace />version" type="checkbox" value="NIV" /> <span style="font-size: xx-small;">NIV</span>

		<input name="<portlet:namespace />version" type="checkbox" value="NASB" /> <span style="font-size: xx-small;">NASB</span>

		<input name="<portlet:namespace />version" type="checkbox" value="MSG" /> <span style="font-size: xx-small;">MSG</span>

		<input name="<portlet:namespace />version" type="checkbox" value="AMP" /> <span style="font-size: xx-small;">AMP</span>

		<input name="<portlet:namespace />version" type="checkbox" value="NLT" /> <span style="font-size: xx-small;">NLT</span>

		<input name="<portlet:namespace />version" type="checkbox" value="KJV" /> <span style="font-size: xx-small;">KJV</span>

		<br /><br />

		<input type="submit" value="<liferay-ui:message key="compare" />" />

		</form>
	</liferay-ui:section>
	<liferay-ui:section>
		<form method="get" target="_blank" name="<portlet:namespace />fm3" onSubmit="submitForm(document.<portlet:namespace />fm3, 'http://www.biblegateway.com/cgi-bin/bible', false); return false;">

		<input name="search" type="text" size="30" />

		<select name="version">
			<option value="ESV">ESV</option>
			<option value="NIV">NIV</option>
			<option value="NASB">NASB</option>
			<option value="MSG">MSG</option>
			<option value="AMP">AMP</option>
			<option value="NLT">NLT</option>
			<option value="KJV">KJV</option>
			<option value="NKJV">NKJV</option>
		</select>

		<select name="SearchType">
			<option value="AND"><liferay-ui:message key="all-words" /></option>
			<option value="OR"><liferay-ui:message key="any-words" /></option>
			<option value="EXACT"><liferay-ui:message key="exact-phrase" /></option>
			<option value="FULL_TEXT"><liferay-ui:message key="similar-phrase" /></option>
		</select>

		<br />

		<input name="SearchWholeWords" type="checkbox" value="yes" /> <span style="font-size: xx-small;"><liferay-ui:message key="match-whole-words" /></span>

		<input name="SearchAsLink" type="checkbox" value="yes" /> <span style="font-size: xx-small;"><liferay-ui:message key="references-only" /></span>

		<br /><br />

		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<liferay-ui:message key="restrict-to" />
			</td>
			<td rowspan="3" style="padding-left: 10px;"></td>
			<td>
				<select name="restrict" onChange="document.<portlet:namespace />fm3.StartRestrict.selectedIndex = 0; document.<portlet:namespace />fm3.EndRestrict.selectedIndex = 0;">
					<option value=""></option>
					<option value="Apocalyptic_Books">Apocalyptic Books</option>
					<option value="Books_of_Moses">Books of Moses</option>
					<option value="Epistles">Epistles</option>
					<option value="Gospels">Gospels</option>
					<option value="Major_Prophets">Major Prophets</option>
					<option value="Minor_Prophets">Minor Prophets</option>
					<option value="New_Testament">New Testament</option>
					<option value="Old_Testament">Old Testament</option>
					<option value="OT_Historical_Books">OT Historical Books</option>
					<option value="Pauline_Epistles">Pauline Epistles</option>
					<option value="Wisdom_Books">Wisdom Books</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="start-from" />
			</td>
			<td>
				<select name="StartRestrict" onChange="document.<portlet:namespace />fm3.restrict.selectedIndex = 0; text = document.<portlet:namespace />fm3.StartRestrict[document.<portlet:namespace />fm3.StartRestrict.selectedIndex].value; if (document.<portlet:namespace />fm3.StartRestrict.selectedIndex > document.<portlet:namespace />fm3.EndRestrict.selectedIndex) { for (var i = 0; i < document.<portlet:namespace />fm3.EndRestrict.length; i++) { if (document.<portlet:namespace />fm3.EndRestrict[i].value == text) { document.<portlet:namespace />fm3.EndRestrict.selectedIndex = i; } } }">
					<option value=""></option>
					<option value="GEN">Genesis</option>
					<option value="EXOD">Exodus</option>
					<option value="LEV">Leviticus</option>
					<option value="NUM">Numbers</option>
					<option value="DEUT">Deuteronomy</option>
					<option value="JOSH">Joshua</option>
					<option value="JUDG">Judges</option>
					<option value="RUTH">Ruth</option>
					<option value="1SAM">1 Samuel</option>
					<option value="2SAM">2 Samuel</option>
					<option value="1KGS">1 Kings</option>
					<option value="2KGS">2 Kings</option>
					<option value="1CHRON">1 Chronicles</option>
					<option value="2CHRON">2 Chronicles</option>
					<option value="EZRA">Ezra</option>
					<option value="NEH">Nehemiah</option>
					<option value="ESTH">Esther</option>
					<option value="JOB">Job</option>
					<option value="PS">Psalm</option>
					<option value="PROV">Proverbs</option>
					<option value="ECC">Ecclesiastes</option>
					<option value="SONG">Song of Solomon</option>
					<option value="ISA">Isaiah</option>
					<option value="JER">Jeremiah</option>
					<option value="LAM">Lamentations</option>
					<option value="EZEK">Ezekiel</option>
					<option value="DAN">Daniel</option>
					<option value="HOSEA">Hosea</option>
					<option value="JOEL">Joel</option>
					<option value="AMOS">Amos</option>
					<option value="OBAD">Obadiah</option>
					<option value="JONAH">Jonah</option>
					<option value="MICAH">Micah</option>
					<option value="NAHUM">Nahum</option>
					<option value="HAB">Habakkuk</option>
					<option value="ZEPH">Zephaniah</option>
					<option value="HAG">Haggai</option>
					<option value="ZECH">Zechariah</option>
					<option value="MAL">Malachi</option>
					<option value="MATT">Matthew</option>
					<option value="MARK">Mark</option>
					<option value="LUKE">Luke</option>
					<option value="JOHN">John</option>
					<option value="ACTS">Acts</option>
					<option value="ROM">Romans</option>
					<option value="1COR">1 Corinthians</option>
					<option value="2COR">2 Corinthians</option>
					<option value="GAL">Galatians</option>
					<option value="EPH">Ephesians</option>
					<option value="PHIL">Philippians</option>
					<option value="COL">Colossians</option>
					<option value="1THES">1 Thessalonians</option>
					<option value="2THES">2 Thessalonians</option>
					<option value="1TIM">1 Timothy</option>
					<option value="2TIM">2 Timothy</option>
					<option value="TIT">Titus</option>
					<option value="PHILEM">Philemon</option>
					<option value="HEB">Hebrews</option>
					<option value="JAS">James</option>
					<option value="1PET">1 Peter</option>
					<option value="2PET">2 Peter</option>
					<option value="1JOHN">1 John</option>
					<option value="2JOHN">2 John</option>
					<option value="3JOHN">3 John</option>
					<option value="JUDE">Jude</option>
					<option value="REV">Revelation</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="end-at" />
			</td>
			<td>
				<select name="EndRestrict" onChange="document.<portlet:namespace />fm3.restrict.selectedIndex = 0; text = document.<portlet:namespace />fm3.StartRestrict[document.<portlet:namespace />fm3.EndRestrict.selectedIndex].value; if (document.<portlet:namespace />fm3.StartRestrict.selectedIndex == 0) { document.<portlet:namespace />fm3.StartRestrict.selectedIndex = document.<portlet:namespace />fm3.EndRestrict.selectedIndex; } else if (document.<portlet:namespace />fm3.StartRestrict.selectedIndex > document.<portlet:namespace />fm3.EndRestrict.selectedIndex) { for (var i = 0; i < document.<portlet:namespace />fm3.EndRestrict.length; i++) { if (document.<portlet:namespace />fm3.EndRestrict[i].value == text) { document.<portlet:namespace />fm3.StartRestrict.selectedIndex = i; } } }">
					<option value=""></option>
					<option value="GEN">Genesis</option>
					<option value="EXOD">Exodus</option>
					<option value="LEV">Leviticus</option>
					<option value="NUM">Numbers</option>
					<option value="DEUT">Deuteronomy</option>
					<option value="JOSH">Joshua</option>
					<option value="JUDG">Judges</option>
					<option value="RUTH">Ruth</option>
					<option value="1SAM">1 Samuel</option>
					<option value="2SAM">2 Samuel</option>
					<option value="1KGS">1 Kings</option>
					<option value="2KGS">2 Kings</option>
					<option value="1CHRON">1 Chronicles</option>
					<option value="2CHRON">2 Chronicles</option>
					<option value="EZRA">Ezra</option>
					<option value="NEH">Nehemiah</option>
					<option value="ESTH">Esther</option>
					<option value="JOB">Job</option>
					<option value="PS">Psalm</option>
					<option value="PROV">Proverbs</option>
					<option value="ECC">Ecclesiastes</option>
					<option value="SONG">Song of Solomon</option>
					<option value="ISA">Isaiah</option>
					<option value="JER">Jeremiah</option>
					<option value="LAM">Lamentations</option>
					<option value="EZEK">Ezekiel</option>
					<option value="DAN">Daniel</option>
					<option value="HOSEA">Hosea</option>
					<option value="JOEL">Joel</option>
					<option value="AMOS">Amos</option>
					<option value="OBAD">Obadiah</option>
					<option value="JONAH">Jonah</option>
					<option value="MICAH">Micah</option>
					<option value="NAHUM">Nahum</option>
					<option value="HAB">Habakkuk</option>
					<option value="ZEPH">Zephaniah</option>
					<option value="HAG">Haggai</option>
					<option value="ZECH">Zechariah</option>
					<option value="MAL">Malachi</option>
					<option value="MATT">Matthew</option>
					<option value="MARK">Mark</option>
					<option value="LUKE">Luke</option>
					<option value="JOHN">John</option>
					<option value="ACTS">Acts</option>
					<option value="ROM">Romans</option>
					<option value="1COR">1 Corinthians</option>
					<option value="2COR">2 Corinthians</option>
					<option value="GAL">Galatians</option>
					<option value="EPH">Ephesians</option>
					<option value="PHIL">Philippians</option>
					<option value="COL">Colossians</option>
					<option value="1THES">1 Thessalonians</option>
					<option value="2THES">2 Thessalonians</option>
					<option value="1TIM">1 Timothy</option>
					<option value="2TIM">2 Timothy</option>
					<option value="TIT">Titus</option>
					<option value="PHILEM">Philemon</option>
					<option value="HEB">Hebrews</option>
					<option value="JAS">James</option>
					<option value="1PET">1 Peter</option>
					<option value="2PET">2 Peter</option>
					<option value="1JOHN">1 John</option>
					<option value="2JOHN">2 John</option>
					<option value="3JOHN">3 John</option>
					<option value="JUDE">Jude</option>
					<option value="REV">Revelation</option>
				</select>
			</td>
		</tr>
		</table>

		<br />

		<input type="submit" value="<liferay-ui:message key="search" />" />

		</form>
	</liferay-ui:section>
</liferay-ui:tabs>
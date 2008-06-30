<%@ include file="/html/portlet/css_init.jsp" %>

/* ---------- Notepad styles ---------- */

.sample-portlet-notepad img {
	vertical-align: text-bottom;
}

/* ---------- Entry styles ---------- */

.sample-portlet-notepad .notepad-entry {
	margin-bottom: 1.5em;
}

.sample-portlet-notepad .notepad-entry-button {
	margin-top: 1em;
}

.sample-portlet-notepad .notepad-header {
	padding: 6px 0;
}

.ie .sample-portlet-notepad .notepad-header {
	padding: 10px 0 0;
}

.sample-portlet-notepad .notepad-text-field {
	margin-right: 4px;
}

.sample-portlet-notepad .portlet-msg-info,
.sample-portlet-notepad .portlet-msg-success {
	margin-bottom: 0;
}

/* ---------- Category styles ---------- */

.sample-portlet-notepad .notepad-important,
.sample-portlet-notepad .notepad-none,
.sample-portlet-notepad .notepad-other,
.sample-portlet-notepad .notepad-social,
.sample-portlet-notepad .notepad-work {
	border: 2px solid #FFF;
	cursor: pointer;
	line-height: 26px;
	margin: 2px;
	padding: 3px 5px;
	white-space: nowrap;
}

.ie .sample-portlet-notepad .notepad-important,
.ie .sample-portlet-notepad .notepad-none,
.ie .sample-portlet-notepad .notepad-other,
.ie .sample-portlet-notepad .notepad-social,
.ie .sample-portlet-notepad .notepad-work {
	line-height: 28px;
	white-space: normal;
}

.sample-portlet-notepad .notepad-important.current,
.sample-portlet-notepad .notepad-important:hover {
	border-color: #FF6D6D;
}

.sample-portlet-notepad .notepad-none.current,
.sample-portlet-notepad .notepad-none:hover {
	border-color: #CCC;
}

.sample-portlet-notepad .notepad-other.current,
.sample-portlet-notepad .notepad-other:hover {
	border-color: #708CFF;
}

.sample-portlet-notepad .notepad-social.current,
.sample-portlet-notepad .notepad-social:hover {
	border-color: #78DB6F;
}

.sample-portlet-notepad .notepad-work.current,
.sample-portlet-notepad .notepad-work:hover {
	border-color: #FFE46F;
}

/* ---------- Notes styles ---------- */

.sample-portlet-notepad .notes-container {
	display: none;
}

.sample-portlet-notepad .notes-table {
	width: 100%;
}

.sample-portlet-notepad #notes-spacer {
	height: 1px;
}

.sample-portlet-notepad .notes {
	float: right;
	padding: 3px 5px 0px 5px;
}

.sample-portlet-notepad .notes-holder {
	border-bottom: 1px dotted #AAA;
}

.sample-portlet-notepad .notes-category {
	padding: 3px 0;
	width: 16px;
}

.sample-portlet-notepad .notes-controller {
	float: right;
	padding:0 0 3px 15px;
}

.sample-portlet-notepad .notes-date {
	color: #333;
	font-weight: bold;
}

.sample-portlet-notepad .notes-delete {
	padding: 0 0 0 10px;
}
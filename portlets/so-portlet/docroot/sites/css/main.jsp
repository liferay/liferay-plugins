<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/css_init.jsp" %>

.so-portlet-sites {
	ul.site-list {
		background: #E8EFF4;
		border: 1px solid #C8C9CA;
		list-style: none;
		margin: 1em 0;

		li {
			background: url(<%= PortalUtil.getPathContext() %>/html/icons/sites_admin.png) no-repeat 10px 3px;
			border: 1px solid #E8EFF4;
			margin: 4px;
			padding: 3px 10px 3px 30px;

			span {
				display: block;
			}

			.action-not-allowed {
				float: right;
				height: 16px;
				margin: 0 5px;
				width: 16px;
			}

			.action a {
				float: right;
				font-size: 0px;
				height: 16px;
				margin: 0 5px;
				width: 16px;
			}

			.star a {
				background: url(<%= request.getContextPath() %>/sites/images/star_empty.png) no-repeat 0 50%;
				float: right;
			}

			.unstar a {
				background: url(<%= request.getContextPath() %>/sites/images/star.png) no-repeat 0 50%;
				float: right;
			}

			.name {
				font-weight: bold;
			}
		}

		li:hover {
			background-color: #FFF;
			border: 1px solid #C8C9CA;
		}

		li.empty {
			background-image: url(<%= themeImagesPath %>/messages/alert.png);
			padding-left: 25px;
		}

		li.empty:hover {
			background-color: transparent;
			border: 1px solid #E8EFF4;
		}

		li.more {
			background-image: url(<%= themeImagesPath %>/common/view.png);

			a {
				color: #8F0920;
				font-weight: bold;
			}
		}

		li.social-office-enabled {
			background-image: url(<%= PortalUtil.getPathContext() %>/html/icons/social_office.png);
		}
	}

	ul.site-list,
	ul.site-list li:hover {
		-moz-border-radius: 4px;
		border-radius: 4px;
	}

	.site-list-container {
		clear: both;
	}

	.sites-tabs {
		float: left;

		select {
			padding: 5px 1px;
		}
	}
	.search {
		float: right;
		margin-bottom:10px;

		.search-input {
			vertical-align: top;
			width: 150px;
		}
	}

	.hide-notice {
		float: right;
	}
}

.so-portlet-sites-dialog,
.so-sites-directory {
	ul.directory-list {
		border: 1px solid #CCC;
		clear: both;
		font-size: 12px;
		list-style: none;
		margin: 1em 0;

		li {
			background: url(<%= PortalUtil.getPathContext() %>/html/icons/sites_admin.png) no-repeat 5px 5px;
			padding: 5px 10px 5px 25px;

			.action-not-allowed {
				float: right;
				height: 16px;
				margin: 0 5px;
				width: 16px;
			}

			.action a {
				float: right;
				font-size: 0px;
				height: 16px;
				margin: 0 5px;
				width: 16px;
			}

			.star a {
				background: url(<%= request.getContextPath() %>/sites/images/star_empty.png) no-repeat 0 50%;
				float: right;
			}

			.unstar a {
				background: url(<%= request.getContextPath() %>/sites/images/star.png) no-repeat 0 50%;
				float: right;
			}

			.join a {
				background: url(<%= themeImagesPath %>/common/join.png) no-repeat 0 50%;
			}

			.leave a {
				background: url(<%= themeImagesPath %>/common/leave.png) no-repeat 0 50%;
			}

			.delete a {
				background: url(<%= themeImagesPath %>/common/delete.png) no-repeat 0 50%;
			}

			.description {
				color: #777;
				display: block;
				font-size: 11px;
				padding-right: 75px;
			}
		}

		li.alt {
			background-color: #F0F5F7;
		}

		li.empty {
			background-image: url(<%= themeImagesPath %>/messages/alert.png);
		}

		li.social-office-enabled {
			background-image: url(<%= PortalUtil.getPathContext() %>/html/icons/social_office.png);
		}
	}

	.buttons-left {
		float: left;
	}

	.buttons-right {
		float: right;
	}

	.page-indicator {
		display: block;
		font-weight: bold;
		padding: .5em;
	}

	.search {
		float: none;
	}
}
<%
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
%>

<%@include file="init.jsp" %>

<h2>Styling Guide</h2>
<liferay-ui:tabs
	names="Typography Example,Paragraphs,Blockquote,Blockquote.small,Inline Styles,Headings,Address"
	refresh="<%= false %>"
>

	<liferay-ui:section>
		<aui:layout>
			<aui:column columnWidth="70" first="true">
				<div class="typography">
					<h4 class="style-heading">Paragraphs</h4>
					<p>
						Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore
						magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper
						suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in
						hendrerit in vulputate velit esse molestie consequat
					</p>
					<p>
						El illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim
						qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam
						liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim
						placerat facer possim assum.
					</p>
				</div>

				<div class="typography">
					<h4 class="style-heading">Blockquote</h4>
					<blockquote>
						<p>
							lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit
							in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan
							et iusto odio
							<span>Someone Important</span>
						</p>
					</blockquote>
				</div>

				<div class="typography">
					<h4 class="style-heading">Blockquote Small</h4>
					<blockquote class="small">
						<p>
							lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit
							in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan
							et iusto odio
							<span>Someone Important</span>
						</p>
					</blockquote>
				</div>
			</aui:column>

			<aui:column columnWidth="30" last="true">
				<div class="typography">
					<h4 class="style-heading">Inline Styles</h4>
					<ul>
						<li><strong>Strong</strong></li>
						</br>
						<li><em>Emphasis</em></li>
						</br>
						<li><a href="">Inline Link</a></li>
						</br>
						<li><strike>Strike</strike></li>
						</br>
						<li>Inline <span class="aui-buttonitem-icon aui-icon aui-icon-person"></span> Icons</li>
						</br>
						<li><code>&lt;h1&gt;Sample Code&lt;/h1&gt;</code></li>
					</ul>
				</div>
				<hr />

				<div class="typography">
					<h1 class="style-heading">Heading 1</h1>
					<h2 class="style-heading">Heading 2</h2>
					<h3 class="style-heading">Heading 3</h3>
					<h4 class="style-heading">Heading 4</h4>
					<h5 class="style-heading">Heading 5</h5>
					<h6 class="style-heading">Heading 6</h6>
				</div>
				<hr />

				<div class="typography">
					<h4 class="style-heading">Address</h4>
					<address><p>
						1234 South Creek Lane<br />
						Calgary, Alberta, Canada<br />
						T4Bâ€“1S6
					</p></address>
				</div>
			</aui:column>
		</aui:layout>
	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h4>Paragraphs</h4>
<p>
	Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore
	magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper
	suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in
	hendrerit in vulputate velit esse molestie consequat
</p>
<p>
	El illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim
	qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam
	liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim
	placerat facer possim assum.
</p>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Paragraphs Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<blockquote>
	<p>
		lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit
		in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan
		et iusto odio
		<span>
			Someone Important
		</span>
	</p>
</blockquote>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Blockquote Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h4 class="style-heading">Blockquote Small</h4>
<blockquote class="small">
	<p>
		lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit
		in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan
		et iusto odio
		<span>
			Someone Important
		</span>
	</p>
</blockquote>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Blockquote Small Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<li><strong>Strong</strong></li>
<li><em>Emphasis</em></li>
<li><a href="">Inline Link</a></li>
<li><strike>Strike</strike></li>
<li>Inline <span class="aui-buttonitem-icon aui-icon aui-icon-person"></span> Icons</li>
<li><code>&lt;h1&gt;Sample Code&lt;/h1&gt;</code></li>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Inline Styles Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h1>Heading 1</h1>
<h2>Heading 2</h2>
<h3>Heading 3</h3>
<h4>Heading 4</h4>
<h5>Heading 5</h5>
<h6>Heading 6</h6>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Headings Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h4 class="style-heading">Address</h4>
	<address><p>
		1234 South Creek Lane<br />
		Calgary, Alberta, Canada<br />
		T4Bâ€“1S6
	</p></address>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Address Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Lists</h2>
<liferay-ui:tabs
	names="List Example,UL,OL,UL.icons,UL.alt"
	refresh="<%= false %>"
>

	<liferay-ui:section>
		<div id="lists">
			<aui:layout>
				<aui:column columnWidth="25" first="true">
					<h4 class="list-heading">Unordered List</h4>
					<ul>
						<li>tation ullamcorper suscipit lobortis</li>
						<li>Nam liber tempor cum soluta nobis</li>
						<li>imperdiet doming id quod mazim</li>
						<li>suscipit lobortis nisl ut aliquip ex</li>
					</ul>
				</aui:column>
				<aui:column columnWidth="25">
					<h4 class="list-heading">Ordered List</h4>
					<ol>
						<li>tation ullamcorper suscipit lobortis</li>
						<li>Nam liber tempor cum soluta nobis</li>
						<li>imperdiet doming id quod mazim</li>
						<li>suscipit lobortis nisl ut aliquip ex</li>
					</ol>
				</aui:column>
				<aui:column columnWidth="25">
					<h4 class="list-heading">UL.icons</h4>
					<ul class="icon">
						<li><span class="aui-icon aui-icon-check"></span>tation ullamcorper suscipit lobortis</li>
						<li><span class="aui-icon aui-icon-check"></span>Nam liber tempor cum soluta nobis</li>
						<li><span class="aui-icon aui-icon-check"></span>imperdiet doming id quod mazim</li>
						<li><span class="aui-icon aui-icon-check"></span>suscipit lobortis nisl ut aliquip ex</li>
					</ul>
				</aui:column>
				<aui:column columnWidth="25" last="true">
					<h4 class="list-heading">UL.alt</h4>
					<ul class="alt">
						<li><span class="aui-icon carat"></span>tation ullamcorper suscipit lobortis</li>
						<li><span class="aui-icon close"></span>Nam liber tempor cum soluta nobis</li>
						<li><span class="aui-icon signal"></span>imperdiet doming id quod mazim</li>
						<li><span class="aui-icon alert"></span>suscipit lobortis nisl ut aliquip ex</li>
					</ul>
				</aui:column>
			</aui:layout>
		</div>
	</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
<h4>Unordered List</h4>
<ul>
	<li>tation ullamcorper suscipit lobortis</li>
	<li>Nam liber tempor cum soluta nobis</li>
	<li>imperdiet doming id quod mazim</li>
	<li>suscipit lobortis nisl ut aliquip ex</li>
</ul>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Unordered List Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
<h4>Ordered List</h4>
<ol>
	<li>tation ullamcorper suscipit lobortis</li>
	<li>Nam liber tempor cum soluta nobis</li>
	<li>imperdiet doming id quod mazim</li>
	<li>suscipit lobortis nisl ut aliquip ex</li>
</ol>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Unordered List Icon Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
<h4 class="list-heading">UL.icons</h4>
	<ul class="icon">
		<li><span class="aui-icon aui-icon-check"></span>tation ullamcorper suscipit lobortis</li>
		<li><span class="aui-icon aui-icon-check"></span>Nam liber tempor cum soluta nobis</li>
		<li><span class="aui-icon aui-icon-check"></span>imperdiet doming id quod mazim</li>
		<li><span class="aui-icon aui-icon-check"></span>suscipit lobortis nisl ut aliquip ex</li>
	</ul>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Ordered List Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
<h4 class="list-heading">UL.alt</h4>
	<ul class="alt">
		<li><span class="aui-icon aui-icon-carat-1-r"></span>tation ullamcorper suscipit lobortis</li>
		<li><span class="aui-icon aui-icon-close"></span>Nam liber tempor cum soluta nobis</li>
		<li><span class="aui-icon aui-icon-signal"></span>imperdiet doming id quod mazim</li>
		<li><span class="aui-icon aui-icon-alert"></span>suscipit lobortis nisl ut aliquip ex</li>
	</ul>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Unordered List Unique Icon Code" class="textarea" />

		</liferay-ui:section>
</liferay-ui:tabs>
</br>
<hr class="alt1"/>

<h2>Navigation Portlet</h2>
<liferay-ui:tabs
	names="Navigation Example,Navigation Setting"
	refresh="<%= false %>"
>

	<liferay-ui:section>
		<div class="aui-w33 aui-column aui-column-first">
			<div class="aui-column-content-first">
				<liferay-ui:navigation
					bulletStyle="dots"
					displayStyle="[custom]"
					headerType="root-layout"
					includedLayouts="all"
					nestedChildren="true"
					rootLayoutLevel="0"
					rootLayoutType="absolute"
				/>
			</div>
			<div class="aui-column-content-last">
				<liferay-ui:navigation
					bulletStyle="dots"
					displayStyle="defaultBulletStyle"
					headerType="root-layout"
					includedLayouts="current"
					nestedChildren="true"
					rootLayoutLevel="0"
					rootLayoutType="absolute"
				/>
			</div>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
Navigation Portlet Code
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Navigation Portlet Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Tooltips</h2>
<liferay-ui:tabs
	names="Tooltips Example,Sharing Trigger,With Image,Video Preview,No Arrow"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="tooltips">
			<aui:layout>
				<aui:column columnWidth="25" first="true">
					<h6 class="tooltips-heading">Sharing Trigger</h6>
					<a href="javascript:void(0);" class="t1" title="Here's a sample Tooltip. The pointer requires no images! First trigger.">Tooltip - Sharing the same trigger, content from title attribute</a>
					<br/>
					<a href="javascript:void(0);" class="t1" title="Here's a sample Tooltip. The pointer requires no images! Second trigger.">Tooltip - Sharing the same trigger, content from title attribute</a>
				</aui:column>
				<aui:column columnWidth="25">
					<h6 class="tooltips-heading">With Image</h6>
					<p><a href="javascript:void(0);" class="t2">Tooltip - With Image</a>
					<br/>
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
				<aui:column columnWidth="25">
					<h6 class="tooltips-heading">Video Preview</h6>
					<p><a href="javascript:void(0);" class="t4">Tooltip - Video Preview</a>
					<br/>
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
				<aui:column columnWidth="25" last="true">
					<h6 class="tooltips-heading">No Arrow</h6>
					<p><a href="javascript:void(0);" class="t3">Tooltip - Simple tooltip without arrow and no animation</a>
					<br/>
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
			</aui:layout>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h6 class="tooltips-heading">Sharing Trigger</h6>
	<a href="javascript:void(0);" class="t1" title="Here's a sample Tooltip. The pointer requires no images! First trigger.">Tooltip - Sharing the same trigger, content from title attribute</a>
	<br/>
	<a href="javascript:void(0);" class="t1" title="Here's a sample Tooltip. The pointer requires no images! Second trigger.">Tooltip - Sharing the same trigger, content from title attribute</a>

	<aui:script use="aui-tooltip,aui-io-plugin">
		var t1 = new A.Tooltip({
			trigger: '.t1',
			align: { points: [ 'bc', 'tc' ] },
			title: true
		})
		.render();
	</aui:script>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Sharing Trigger Code" class="textarea" />

	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h6 class="tooltips-heading">With Image</h6>
	<p><a href="javascript:void(0);" class="t2">Tooltip - With Image</a>
	<br/>
	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

	<aui:script use="aui-tooltip,aui-io-plugin">
		var t2 = new A.Tooltip({
			trigger: '.t2',
			bodyContent: '<img src="http://placehold.it/182x154/98BF0D/1F1A16/&text=Image+Example" /><br/><div style="text-align: center;">Image Example</div>',
		})

		.render();
	</aui:script>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Tooltip with Image Code" class="textarea" />

	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h6 class="tooltips-heading">Video Preview</h6>
	<p><a href="javascript:void(0);" class="t4">Tooltip - Video Preview</a>
	<br/>
	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

	<aui:script use="aui-tooltip,aui-io-plugin">
		var t4 = new A.Tooltip({
			trigger: '.t4',
			bodyContent: '<object width="560" height="340"><param name="movie" value="http://www.youtube.com/v/PiSxJwB29R8&hl=en&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/PiSxJwB29R8&hl=en&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="560" height="340"></embed></object>',
		})

		.render();
	</aui:script>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Tooltip with Video Code" class="textarea" />

	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h6 class="tooltips-heading">No Arrow</h6>
	<p><a href="javascript:void(0);" class="t3">Tooltip - Simple tooltip without arrow and no animation</a>
	<br/>
	Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

	<aui:script use="aui-tooltip,aui-io-plugin">
		var t3 = new A.Tooltip({
			trigger: '.t3',
			align: { points: [ 'lc', 'rc' ] },
			showArrow: false,
			bodyContent: 'Simple tooltip without arrow! No animation.'
		})

		.render();
	</aui:script>
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Tooltip without Arrow and no Animation Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Horizontal Rules</h2>
<liferay-ui:tabs
	names="Horizontal Rules Example,HTML"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="hr">
			<aui:layout>
				<aui:column columnWidth="33" first="true">
					<h5 class="hr-heading">HR</h4>
					<hr />
				</aui:column>
				<aui:column columnWidth="33">
					<h5 class="hr-heading">HR.alt1</h4>
					<hr class="alt1" />
				</aui:column>
				<aui:column columnWidth="33" last="true">
					<h5 class="hr-heading">HR.alt2</h4>
					<hr class="alt2" />
				</aui:column>
			</aui:layout>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<h4>HR</h4>
<hr />

<h4>HR.alt1</h4>
<hr class="alt1" />

<h4>HR.alt1</h4>
<hr class="alt1" />
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Horizontal Rules Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Icons</h2>
<liferay-ui:tabs
	names="Icon Example,Icon Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<ul class="icon-example">
			<li><span class="aui-icon aui-icon-carat-1-t"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-tr"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-r"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-br"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-bl"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-l"></span></li>
			<li><span class="aui-icon aui-icon-carat-1-tl"></span></li>
			<li><span class="aui-icon aui-icon-carat-2-t-b"></span></li>
			<li><span class="aui-icon aui-icon-carat-2-r-1"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-t"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-tr"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-r"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-br"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-b"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-bl"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-l"></span></li>
			<li><span class="aui-icon aui-icon-triangle-1-tl"></span></li>
			<li><span class="aui-icon aui-icon-triangle-2-t-b"></span></li>
			<li><span class="aui-icon aui-icon-triangle-2-r-1"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-tr"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-br"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-bl"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrow-1-tl"></span></li>
			<li><span class="aui-icon aui-icon-arrow-2-t-b"></span></li>
			<li><span class="aui-icon aui-icon-arrow-2-tr-bl"></span></li>
			<li><span class="aui-icon aui-icon-arrow-2-r-l"></span></li>
			<li><span class="aui-icon aui-icon-arrow-2-br-tl"></span></li>
			<li><span class="aui-icon aui-icon-arrowstop-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowstop-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowstop-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowstop-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-tr"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-br"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-bl"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-1-tl"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-2-t-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-2-tr-bl"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-2-r-1"></span></li>
			<li><span class="aui-icon aui-icon-arrowthick-2-br-tl"></span></li>
			<li><span class="aui-icon aui-icon-arrowthickstop-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowthickstop-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowthickstop-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowthickstop-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturnthick-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturnthick-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturnthick-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturnthick-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturn-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturn-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturn-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowreturn-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrowrefresh-1-l"></span></li>
			<li><span class="aui-icon aui-icon-arrowrefresh-1-t"></span></li>
			<li><span class="aui-icon aui-icon-arrowrefresh-1-r"></span></li>
			<li><span class="aui-icon aui-icon-arrowrefresh-1-b"></span></li>
			<li><span class="aui-icon aui-icon-arrow-4"></span></li>
			<li><span class="aui-icon aui-icon-arrow-4-diag"></span></li>
			<li><span class="aui-icon aui-icon-extlink"></span></li>
			<li><span class="aui-icon aui-icon-newwin"></span></li>
			<li><span class="aui-icon aui-icon-refresh"></span></li>
			<li><span class="aui-icon aui-icon-shuffle"></span></li>
			<li><span class="aui-icon aui-icon-transfer-r-1"></span></li>
			<li><span class="aui-icon aui-icon-transferthick-r-1"></span></li>
			<li><span class="aui-icon aui-icon-folder-collapsed"></span></li>
			<li><span class="aui-icon aui-icon-folder-open"></span></li>
			<li><span class="aui-icon aui-icon-document"></span></li>
			<li><span class="aui-icon aui-icon-document-b"></span></li>
			<li><span class="aui-icon aui-icon-note"></span></li>
			<li><span class="aui-icon aui-icon-mail-closed"></span></li>
			<li><span class="aui-icon aui-icon-mail-open"></span></li>
			<li><span class="aui-icon aui-icon-suitcase"></span></li>
			<li><span class="aui-icon aui-icon-comment"></span></li>
			<li><span class="aui-icon aui-icon-person"></span></li>
			<li><span class="aui-icon aui-icon-print"></span></li>
			<li><span class="aui-icon aui-icon-trash"></span></li>
			<li><span class="aui-icon aui-icon-locked"></span></li>
			<li><span class="aui-icon aui-icon-unlocked"></span></li>
			<li><span class="aui-icon aui-icon-bookmark"></span></li>
			<li><span class="aui-icon aui-icon-tag"></span></li>
			<li><span class="aui-icon aui-icon-home"></span></li>
			<li><span class="aui-icon aui-icon-flag"></span></li>
			<li><span class="aui-icon aui-icon-calendar"></span></li>
			<li><span class="aui-icon aui-icon-cart"></span></li>
			<li><span class="aui-icon aui-icon-pencil"></span></li>
			<li><span class="aui-icon aui-icon-clock"></span></li>
			<li><span class="aui-icon aui-icon-disk"></span></li>
			<li><span class="aui-icon aui-icon-calculator"></span></li>
			<li><span class="aui-icon aui-icon-zoomin"></span></li>
			<li><span class="aui-icon aui-icon-zoomout"></span></li>
			<li><span class="aui-icon aui-icon-search"></span></li>
			<li><span class="aui-icon aui-icon-wrench"></span></li>
			<li><span class="aui-icon aui-icon-gear"></span></li>
			<li><span class="aui-icon aui-icon-heart"></span></li>
			<li><span class="aui-icon aui-icon-star"></span></li>
			<li><span class="aui-icon aui-icon-link"></span></li>
			<li><span class="aui-icon aui-icon-cancel"></span></li>
			<li><span class="aui-icon aui-icon-plus"></span></li>
			<li><span class="aui-icon aui-icon-plusthick"></span></li>
			<li><span class="aui-icon aui-icon-minus"></span></li>
			<li><span class="aui-icon aui-icon-minusthick"></span></li>
			<li><span class="aui-icon aui-icon-close"></span></li>
			<li><span class="aui-icon aui-icon-closethick"></span></li>
			<li><span class="aui-icon aui-icon-key"></span></li>
			<li><span class="aui-icon aui-icon-lightbulb"></span></li>
			<li><span class="aui-icon aui-icon-scissors"></span></li>
			<li><span class="aui-icon aui-icon-clipboard"></span></li>
			<li><span class="aui-icon aui-icon-copy"></span></li>
			<li><span class="aui-icon aui-icon-contact"></span></li>
			<li><span class="aui-icon aui-icon-image"></span></li>
			<li><span class="aui-icon aui-icon-video"></span></li>
			<li><span class="aui-icon aui-icon-script"></span></li>
			<li><span class="aui-icon aui-icon-alert"></span></li>
			<li><span class="aui-icon aui-icon-info"></span></li>
			<li><span class="aui-icon aui-icon-notice"></span></li>
			<li><span class="aui-icon aui-icon-help"></span></li>
			<li><span class="aui-icon aui-icon-check"></span></li>
			<li><span class="aui-icon aui-icon-bullet"></span></li>
			<li><span class="aui-icon aui-icon-radio-off"></span></li>
			<li><span class="aui-icon aui-icon-radio-on"></span></li>
			<li><span class="aui-icon aui-icon-pin-l"></span></li>
			<li><span class="aui-icon aui-icon-pin-b"></span></li>
			<li><span class="aui-icon aui-icon-play"></span></li>
			<li><span class="aui-icon aui-icon-pause"></span></li>
			<li><span class="aui-icon aui-icon-seek-next"></span></li>
			<li><span class="aui-icon aui-icon-seek-prev"></span></li>
			<li><span class="aui-icon aui-icon-seek-end"></span></li>
			<li><span class="aui-icon aui-icon-seek-first"></span></li>
			<li><span class="aui-icon aui-icon-stop"></span></li>
			<li><span class="aui-icon aui-icon-eject"></span></li>
			<li><span class="aui-icon aui-icon-volume-off"></span></li>
			<li><span class="aui-icon aui-icon-volume-on"></span></li>
			<li><span class="aui-icon aui-icon-power"></span></li>
			<li><span class="aui-icon aui-icon-signal-diag"></span></li>
			<li><span class="aui-icon aui-icon-signal"></span></li>
			<li><span class="aui-icon aui-icon-battery-0"></span></li>
			<li><span class="aui-icon aui-icon-battery-1"></span></li>
			<li><span class="aui-icon aui-icon-battery-2"></span></li>
			<li><span class="aui-icon aui-icon-battery-3"></span></li>
			<li><span class="aui-icon aui-icon-circle-plus"></span></li>
			<li><span class="aui-icon aui-icon-circle-minus"></span></li>
			<li><span class="aui-icon aui-icon-circle-close"></span></li>
			<li><span class="aui-icon aui-icon-circle-triangle-r"></span></li>
			<li><span class="aui-icon aui-icon-circle-triangle-b"></span></li>
			<li><span class="aui-icon aui-icon-circle-triangle-l"></span></li>
			<li><span class="aui-icon aui-icon-circle-triangle-t"></span></li>
			<li><span class="aui-icon aui-icon-circle-arrow-r"></span></li>
			<li><span class="aui-icon aui-icon-circle-arrow-b"></span></li>
			<li><span class="aui-icon aui-icon-circle-arrow-l"></span></li>
			<li><span class="aui-icon aui-icon-circle-arrow-t"></span></li>
			<li><span class="aui-icon aui-icon-circle-zoomin"></span></li>
			<li><span class="aui-icon aui-icon-circle-zoomout"></span></li>
			<li><span class="aui-icon aui-icon-circle-check"></span></li>
			<li><span class="aui-icon aui-icon-circlesmall-plus"></span></li>
			<li><span class="aui-icon aui-icon-circlesmall-minus"></span></li>
			<li><span class="aui-icon aui-icon-circlesmall-close"></span></li>
			<li><span class="aui-icon aui-icon-squaresmall-plus"></span></li>
			<li><span class="aui-icon aui-icon-squaresmall-minus"></span></li>
			<li><span class="aui-icon aui-icon-squaresmall-close"></span></li>
			<li><span class="aui-icon aui-icon-grip-dotted-vertical"></span></li>
			<li><span class="aui-icon aui-icon-grip-dotted-horizontal"></span></li>
			<li><span class="aui-icon aui-icon-grip-solid-vertical"></span></li>
			<li><span class="aui-icon aui-icon-grip-solid-horizontal"></span></li>
			<li><span class="aui-icon aui-icon-gripsmall-diagonal-br"></span></li>
			<li><span class="aui-icon aui-icon-grip-diagonal-br"></span></li>
			<li><span class="aui-icon aui-icon-loading"></span></li>
		</ul>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<ul class="icon-example">
	<li><span class="aui-icon aui-icon-carat-1-t"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-tr"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-r"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-br"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-bl"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-l"></span></li>
	<li><span class="aui-icon aui-icon-carat-1-tl"></span></li>
	<li><span class="aui-icon aui-icon-carat-2-t-b"></span></li>
	<li><span class="aui-icon aui-icon-carat-2-r-1"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-t"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-tr"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-r"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-br"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-b"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-bl"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-l"></span></li>
	<li><span class="aui-icon aui-icon-triangle-1-tl"></span></li>
	<li><span class="aui-icon aui-icon-triangle-2-t-b"></span></li>
	<li><span class="aui-icon aui-icon-triangle-2-r-1"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-tr"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-br"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-bl"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrow-1-tl"></span></li>
	<li><span class="aui-icon aui-icon-arrow-2-t-b"></span></li>
	<li><span class="aui-icon aui-icon-arrow-2-tr-bl"></span></li>
	<li><span class="aui-icon aui-icon-arrow-2-r-l"></span></li>
	<li><span class="aui-icon aui-icon-arrow-2-br-tl"></span></li>
	<li><span class="aui-icon aui-icon-arrowstop-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowstop-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowstop-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowstop-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-tr"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-br"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-bl"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-1-tl"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-2-t-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-2-tr-bl"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-2-r-1"></span></li>
	<li><span class="aui-icon aui-icon-arrowthick-2-br-tl"></span></li>
	<li><span class="aui-icon aui-icon-arrowthickstop-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowthickstop-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowthickstop-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowthickstop-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturnthick-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturnthick-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturnthick-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturnthick-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturn-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturn-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturn-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowreturn-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrowrefresh-1-l"></span></li>
	<li><span class="aui-icon aui-icon-arrowrefresh-1-t"></span></li>
	<li><span class="aui-icon aui-icon-arrowrefresh-1-r"></span></li>
	<li><span class="aui-icon aui-icon-arrowrefresh-1-b"></span></li>
	<li><span class="aui-icon aui-icon-arrow-4"></span></li>
	<li><span class="aui-icon aui-icon-arrow-4-diag"></span></li>
	<li><span class="aui-icon aui-icon-extlink"></span></li>
	<li><span class="aui-icon aui-icon-newwin"></span></li>
	<li><span class="aui-icon aui-icon-refresh"></span></li>
	<li><span class="aui-icon aui-icon-shuffle"></span></li>
	<li><span class="aui-icon aui-icon-transfer-r-1"></span></li>
	<li><span class="aui-icon aui-icon-transferthick-r-1"></span></li>
	<li><span class="aui-icon aui-icon-folder-collapsed"></span></li>
	<li><span class="aui-icon aui-icon-folder-open"></span></li>
	<li><span class="aui-icon aui-icon-document"></span></li>
	<li><span class="aui-icon aui-icon-document-b"></span></li>
	<li><span class="aui-icon aui-icon-note"></span></li>
	<li><span class="aui-icon aui-icon-mail-closed"></span></li>
	<li><span class="aui-icon aui-icon-mail-open"></span></li>
	<li><span class="aui-icon aui-icon-suitcase"></span></li>
	<li><span class="aui-icon aui-icon-comment"></span></li>
	<li><span class="aui-icon aui-icon-person"></span></li>
	<li><span class="aui-icon aui-icon-print"></span></li>
	<li><span class="aui-icon aui-icon-trash"></span></li>
	<li><span class="aui-icon aui-icon-locked"></span></li>
	<li><span class="aui-icon aui-icon-unlocked"></span></li>
	<li><span class="aui-icon aui-icon-bookmark"></span></li>
	<li><span class="aui-icon aui-icon-tag"></span></li>
	<li><span class="aui-icon aui-icon-home"></span></li>
	<li><span class="aui-icon aui-icon-flag"></span></li>
	<li><span class="aui-icon aui-icon-calendar"></span></li>
	<li><span class="aui-icon aui-icon-cart"></span></li>
	<li><span class="aui-icon aui-icon-pencil"></span></li>
	<li><span class="aui-icon aui-icon-clock"></span></li>
	<li><span class="aui-icon aui-icon-disk"></span></li>
	<li><span class="aui-icon aui-icon-calculator"></span></li>
	<li><span class="aui-icon aui-icon-zoomin"></span></li>
	<li><span class="aui-icon aui-icon-zoomout"></span></li>
	<li><span class="aui-icon aui-icon-search"></span></li>
	<li><span class="aui-icon aui-icon-wrench"></span></li>
	<li><span class="aui-icon aui-icon-gear"></span></li>
	<li><span class="aui-icon aui-icon-heart"></span></li>
	<li><span class="aui-icon aui-icon-star"></span></li>
	<li><span class="aui-icon aui-icon-link"></span></li>
	<li><span class="aui-icon aui-icon-cancel"></span></li>
	<li><span class="aui-icon aui-icon-plus"></span></li>
	<li><span class="aui-icon aui-icon-plusthick"></span></li>
	<li><span class="aui-icon aui-icon-minus"></span></li>
	<li><span class="aui-icon aui-icon-minusthick"></span></li>
	<li><span class="aui-icon aui-icon-close"></span></li>
	<li><span class="aui-icon aui-icon-closethick"></span></li>
	<li><span class="aui-icon aui-icon-key"></span></li>
	<li><span class="aui-icon aui-icon-lightbulb"></span></li>
	<li><span class="aui-icon aui-icon-scissors"></span></li>
	<li><span class="aui-icon aui-icon-clipboard"></span></li>
	<li><span class="aui-icon aui-icon-copy"></span></li>
	<li><span class="aui-icon aui-icon-contact"></span></li>
	<li><span class="aui-icon aui-icon-image"></span></li>
	<li><span class="aui-icon aui-icon-video"></span></li>
	<li><span class="aui-icon aui-icon-script"></span></li>
	<li><span class="aui-icon aui-icon-alert"></span></li>
	<li><span class="aui-icon aui-icon-info"></span></li>
	<li><span class="aui-icon aui-icon-notice"></span></li>
	<li><span class="aui-icon aui-icon-help"></span></li>
	<li><span class="aui-icon aui-icon-check"></span></li>
	<li><span class="aui-icon aui-icon-bullet"></span></li>
	<li><span class="aui-icon aui-icon-radio-off"></span></li>
	<li><span class="aui-icon aui-icon-radio-on"></span></li>
	<li><span class="aui-icon aui-icon-pin-l"></span></li>
	<li><span class="aui-icon aui-icon-pin-b"></span></li>
	<li><span class="aui-icon aui-icon-play"></span></li>
	<li><span class="aui-icon aui-icon-pause"></span></li>
	<li><span class="aui-icon aui-icon-seek-next"></span></li>
	<li><span class="aui-icon aui-icon-seek-prev"></span></li>
	<li><span class="aui-icon aui-icon-seek-end"></span></li>
	<li><span class="aui-icon aui-icon-seek-first"></span></li>
	<li><span class="aui-icon aui-icon-stop"></span></li>
	<li><span class="aui-icon aui-icon-eject"></span></li>
	<li><span class="aui-icon aui-icon-volume-off"></span></li>
	<li><span class="aui-icon aui-icon-volume-on"></span></li>
	<li><span class="aui-icon aui-icon-power"></span></li>
	<li><span class="aui-icon aui-icon-signal-diag"></span></li>
	<li><span class="aui-icon aui-icon-signal"></span></li>
	<li><span class="aui-icon aui-icon-battery-0"></span></li>
	<li><span class="aui-icon aui-icon-battery-1"></span></li>
	<li><span class="aui-icon aui-icon-battery-2"></span></li>
	<li><span class="aui-icon aui-icon-battery-3"></span></li>
	<li><span class="aui-icon aui-icon-circle-plus"></span></li>
	<li><span class="aui-icon aui-icon-circle-minus"></span></li>
	<li><span class="aui-icon aui-icon-circle-close"></span></li>
	<li><span class="aui-icon aui-icon-circle-triangle-r"></span></li>
	<li><span class="aui-icon aui-icon-circle-triangle-b"></span></li>
	<li><span class="aui-icon aui-icon-circle-triangle-l"></span></li>
	<li><span class="aui-icon aui-icon-circle-triangle-t"></span></li>
	<li><span class="aui-icon aui-icon-circle-arrow-r"></span></li>
	<li><span class="aui-icon aui-icon-circle-arrow-b"></span></li>
	<li><span class="aui-icon aui-icon-circle-arrow-l"></span></li>
	<li><span class="aui-icon aui-icon-circle-arrow-t"></span></li>
	<li><span class="aui-icon aui-icon-circle-zoomin"></span></li>
	<li><span class="aui-icon aui-icon-circle-zoomout"></span></li>
	<li><span class="aui-icon aui-icon-circle-check"></span></li>
	<li><span class="aui-icon aui-icon-circlesmall-plus"></span></li>
	<li><span class="aui-icon aui-icon-circlesmall-minus"></span></li>
	<li><span class="aui-icon aui-icon-circlesmall-close"></span></li>
	<li><span class="aui-icon aui-icon-squaresmall-plus"></span></li>
	<li><span class="aui-icon aui-icon-squaresmall-minus"></span></li>
	<li><span class="aui-icon aui-icon-squaresmall-close"></span></li>
	<li><span class="aui-icon aui-icon-grip-dotted-vertical"></span></li>
	<li><span class="aui-icon aui-icon-grip-dotted-horizontal"></span></li>
	<li><span class="aui-icon aui-icon-grip-solid-vertical"></span></li>
	<li><span class="aui-icon aui-icon-grip-solid-horizontal"></span></li>
	<li><span class="aui-icon aui-icon-gripsmall-diagonal-br"></span></li>
	<li><span class="aui-icon aui-icon-grip-diagonal-br"></span></li>
	<li><span class="aui-icon aui-icon-loading"></span></li>
</ul>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Icon Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Buttons</h2>
<liferay-ui:tabs
	names="Button Example,Button Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div class="button-example">
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-t"></span><span class="aui-buttonitem-label">carat-1-t</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-tr"></span><span class="aui-buttonitem-label">carat-1-tr</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-r"></span><span class="aui-buttonitem-label">carat-1-r</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-br"></span><span class="aui-buttonitem-label">carat-1-br</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-b"></span><span class="aui-buttonitem-label">carat-1-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-bl"></span><span class="aui-buttonitem-label">carat-1-bl</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-l"></span><span class="aui-buttonitem-label">carat-1-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-tl"></span><span class="aui-buttonitem-label">carat-1-tl</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-2-t-b"></span><span class="aui-buttonitem-label">carat-2-t-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-2-r-l"></span><span class="aui-buttonitem-label">carat-2-r-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-t"></span><span class="aui-buttonitem-label">triangle-1-t</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-tr"></span><span class="aui-buttonitem-label">triangle-1-tr</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-r"></span><span class="aui-buttonitem-label">triangle-1-r</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-br"></span><span class="aui-buttonitem-label">triangle-1-br</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-b"></span><span class="aui-buttonitem-label">triangle-1-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-bl"></span><span class="aui-buttonitem-label">triangle-1-bl</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-l"></span><span class="aui-buttonitem-label">triangle-1-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-tl"></span><span class="aui-buttonitem-label">triangle-1-tl</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-2-t-b"></span><span class="aui-buttonitem-label">triangle-2-t-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-2-r-l"></span><span class="aui-buttonitem-label">triangle-2-r-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-1-t"></span><span class="aui-buttonitem-label">arrow-1-t</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-2-t-b"></span><span class="aui-buttonitem-label">arrow-2-t-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowstop-1-r"></span><span class="aui-buttonitem-label">arrowstop-1-r</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthick-1-tl"></span><span class="aui-buttonitem-label">arrowthick-1-tl</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthick-2-r-l"></span><span class="aui-buttonitem-label">arrowthick-2-r-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthickstop-1-b"></span><span class="aui-buttonitem-label">arrowthickstop-1-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowreturnthick-1-l"></span><span class="aui-buttonitem-label">arrowreturnthick-1-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowreturn-1-r"></span><span class="aui-buttonitem-label">arrowreturn-1-r</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-4"></span><span class="aui-buttonitem-label">arrow-4</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-4-diag"></span><span class="aui-buttonitem-label">arrow-4-diag</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-extlink"></span><span class="aui-buttonitem-label">extlink</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-newwin"></span><span class="aui-buttonitem-label">newwin</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-refresh"></span><span class="aui-buttonitem-label">refresh</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-shuffle"></span><span class="aui-buttonitem-label">shuffle</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-transfer-r-l"></span><span class="aui-buttonitem-label">transfer-r-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-transferthick-r-l"></span><span class="aui-buttonitem-label">transferthick-r-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-folder-collapsed"></span><span class="aui-buttonitem-label">folder-collapsed</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-folder-open"></span><span class="aui-buttonitem-label">folder-open</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-document"></span><span class="aui-buttonitem-label">document</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-document-b"></span><span class="aui-buttonitem-label">document-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-note"></span><span class="aui-buttonitem-label">note</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-mail-closed"></span><span class="aui-buttonitem-label">mail-closed</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-mail-open"></span><span class="aui-buttonitem-label">mail-open</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-suitcase"></span><span class="aui-buttonitem-label">suitcase</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-person"></span><span class="aui-buttonitem-label">person</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-print"></span><span class="aui-buttonitem-label">print</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-trash"></span><span class="aui-buttonitem-label">trash</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-locked"></span><span class="aui-buttonitem-label">locked</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-unlocked"></span><span class="aui-buttonitem-label">unlocked</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-comment"></span><span class="aui-buttonitem-label">comment</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-bookmark"></span><span class="aui-buttonitem-label">bookmark</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-tag"></span><span class="aui-buttonitem-label">tag</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-home"></span><span class="aui-buttonitem-label">home</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-flag"></span><span class="aui-buttonitem-label">flag</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-calendar"></span><span class="aui-buttonitem-label">calendar</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pencil"></span><span class="aui-buttonitem-label">pencil</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-cart"></span><span class="aui-buttonitem-label">cart</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-calculator"></span><span class="aui-buttonitem-label">calculator</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-disk"></span><span class="aui-buttonitem-label">disk</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-clock"></span><span class="aui-buttonitem-label">clock</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-zoomin"></span><span class="aui-buttonitem-label">zoomin</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-zoomout"></span><span class="aui-buttonitem-label">zoomout</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-search"></span><span class="aui-buttonitem-label">search</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-wrench"></span><span class="aui-buttonitem-label">wrench</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-gear"></span><span class="aui-buttonitem-label">gear</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-heart"></span><span class="aui-buttonitem-label">heart</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-star"></span><span class="aui-buttonitem-label">star</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-link"></span><span class="aui-buttonitem-label">link</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-cancel"></span><span class="aui-buttonitem-label">cancel</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-plus"></span><span class="aui-buttonitem-label">plus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-plusthick"></span><span class="aui-buttonitem-label">plusthick</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-minus"></span><span class="aui-buttonitem-label">minus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-minusthick"></span><span class="aui-buttonitem-label">minusthick</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-close"></span><span class="aui-buttonitem-label">close</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-closethick"></span><span class="aui-buttonitem-label">closethick</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-key"></span><span class="aui-buttonitem-label">key</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-lightbulb"></span><span class="aui-buttonitem-label">lightbulb</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-scissors"></span><span class="aui-buttonitem-label">scissors</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-clipboard"></span><span class="aui-buttonitem-label">clipboard</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-copy"></span><span class="aui-buttonitem-label">copy</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-image"></span><span class="aui-buttonitem-label">image</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-video"></span><span class="aui-buttonitem-label">video</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-script"></span><span class="aui-buttonitem-label">script</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-alert"></span><span class="aui-buttonitem-label">alert</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-info"></span><span class="aui-buttonitem-label">info</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-notice"></span><span class="aui-buttonitem-label">notice</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-help"></span><span class="aui-buttonitem-label">help</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-check"></span><span class="aui-buttonitem-label">check</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-bullet"></span><span class="aui-buttonitem-label">bullet</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-radio-off"></span><span class="aui-buttonitem-label">radio-off</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-radio-on"></span><span class="aui-buttonitem-label">radio-on</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pin-l"></span><span class="aui-buttonitem-label">pin-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pin-b"></span><span class="aui-buttonitem-label">pin-b</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-play"></span><span class="aui-buttonitem-label">play</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pause"></span><span class="aui-buttonitem-label">pause</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-next"></span><span class="aui-buttonitem-label">seek-next</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-prev"></span><span class="aui-buttonitem-label">seek-prev</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-end"></span><span class="aui-buttonitem-label">seek-end</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-first"></span><span class="aui-buttonitem-label">seek-first</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-stop"></span><span class="aui-buttonitem-label">stop</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-eject"></span><span class="aui-buttonitem-label">eject</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-volume-off"></span><span class="aui-buttonitem-label">volume-off</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-volume-on"></span><span class="aui-buttonitem-label">volume-on</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-power"></span><span class="aui-buttonitem-label">power</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-signal-diag"></span><span class="aui-buttonitem-label">signal-diag</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-signal"></span><span class="aui-buttonitem-label">signal</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-0"></span><span class="aui-buttonitem-label">battery-0</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-1"></span><span class="aui-buttonitem-label">battery-1</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-2"></span><span class="aui-buttonitem-label">battery-2</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-3"></span><span class="aui-buttonitem-label">battery-3</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-plus"></span><span class="aui-buttonitem-label">circle-plus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-minus"></span><span class="aui-buttonitem-label">circle-minus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-close"></span><span class="aui-buttonitem-label">circle-close</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-triangle-r"></span><span class="aui-buttonitem-label">circle-triangle-r</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-arrow-l"></span><span class="aui-buttonitem-label">circle-arrow-l</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-zoomin"></span><span class="aui-buttonitem-label">circle-zoomin</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-check"></span><span class="aui-buttonitem-label">circle-check</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circlesmall-plus"></span><span class="aui-buttonitem-label">circlesmall-plus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circlesmall-minus"></span><span class="aui-buttonitem-label">circlesmall-minus</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-dotted-vertical"></span><span class="aui-buttonitem-label">grip-dotted-vertical</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-dotted-horizontal"></span><span class="aui-buttonitem-label">grip-dotted-horizontal</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-solid-vertical"></span><span class="aui-buttonitem-label">grip-solid-vertical</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-solid-horizontal"></span><span class="aui-buttonitem-label">grip-solid-horizontal</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-gripsmall-diagonal-br"></span><span class="aui-buttonitem-label">gripsmall-diagonal-br</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-diagonal-br"></span><span class="aui-buttonitem-label">grip-diagonal-br</span></button>
			<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-loading"></span><span class="aui-buttonitem-label">loading</span></button>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<div class="button-example">
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-t"></span><span class="aui-buttonitem-label">carat-1-t</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-tr"></span><span class="aui-buttonitem-label">carat-1-tr</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-r"></span><span class="aui-buttonitem-label">carat-1-r</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-br"></span><span class="aui-buttonitem-label">carat-1-br</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-b"></span><span class="aui-buttonitem-label">carat-1-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-bl"></span><span class="aui-buttonitem-label">carat-1-bl</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-l"></span><span class="aui-buttonitem-label">carat-1-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-1-tl"></span><span class="aui-buttonitem-label">carat-1-tl</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-2-t-b"></span><span class="aui-buttonitem-label">carat-2-t-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-carat-2-r-l"></span><span class="aui-buttonitem-label">carat-2-r-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-t"></span><span class="aui-buttonitem-label">triangle-1-t</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-tr"></span><span class="aui-buttonitem-label">triangle-1-tr</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-r"></span><span class="aui-buttonitem-label">triangle-1-r</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-br"></span><span class="aui-buttonitem-label">triangle-1-br</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-b"></span><span class="aui-buttonitem-label">triangle-1-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-bl"></span><span class="aui-buttonitem-label">triangle-1-bl</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-l"></span><span class="aui-buttonitem-label">triangle-1-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-1-tl"></span><span class="aui-buttonitem-label">triangle-1-tl</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-2-t-b"></span><span class="aui-buttonitem-label">triangle-2-t-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-triangle-2-r-l"></span><span class="aui-buttonitem-label">triangle-2-r-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-1-t"></span><span class="aui-buttonitem-label">arrow-1-t</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-2-t-b"></span><span class="aui-buttonitem-label">arrow-2-t-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowstop-1-r"></span><span class="aui-buttonitem-label">arrowstop-1-r</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthick-1-tl"></span><span class="aui-buttonitem-label">arrowthick-1-tl</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthick-2-r-l"></span><span class="aui-buttonitem-label">arrowthick-2-r-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowthickstop-1-b"></span><span class="aui-buttonitem-label">arrowthickstop-1-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowreturnthick-1-l"></span><span class="aui-buttonitem-label">arrowreturnthick-1-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrowreturn-1-r"></span><span class="aui-buttonitem-label">arrowreturn-1-r</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-4"></span><span class="aui-buttonitem-label">arrow-4</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-arrow-4-diag"></span><span class="aui-buttonitem-label">arrow-4-diag</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-extlink"></span><span class="aui-buttonitem-label">extlink</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-newwin"></span><span class="aui-buttonitem-label">newwin</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-refresh"></span><span class="aui-buttonitem-label">refresh</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-shuffle"></span><span class="aui-buttonitem-label">shuffle</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-transfer-r-l"></span><span class="aui-buttonitem-label">transfer-r-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-transferthick-r-l"></span><span class="aui-buttonitem-label">transferthick-r-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-folder-collapsed"></span><span class="aui-buttonitem-label">folder-collapsed</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-folder-open"></span><span class="aui-buttonitem-label">folder-open</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-document"></span><span class="aui-buttonitem-label">document</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-document-b"></span><span class="aui-buttonitem-label">document-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-note"></span><span class="aui-buttonitem-label">note</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-mail-closed"></span><span class="aui-buttonitem-label">mail-closed</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-mail-open"></span><span class="aui-buttonitem-label">mail-open</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-suitcase"></span><span class="aui-buttonitem-label">suitcase</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-person"></span><span class="aui-buttonitem-label">person</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-print"></span><span class="aui-buttonitem-label">print</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-trash"></span><span class="aui-buttonitem-label">trash</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-locked"></span><span class="aui-buttonitem-label">locked</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-unlocked"></span><span class="aui-buttonitem-label">unlocked</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-comment"></span><span class="aui-buttonitem-label">comment</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-bookmark"></span><span class="aui-buttonitem-label">bookmark</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-tag"></span><span class="aui-buttonitem-label">tag</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-home"></span><span class="aui-buttonitem-label">home</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-flag"></span><span class="aui-buttonitem-label">flag</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-calendar"></span><span class="aui-buttonitem-label">calendar</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pencil"></span><span class="aui-buttonitem-label">pencil</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-cart"></span><span class="aui-buttonitem-label">cart</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-calculator"></span><span class="aui-buttonitem-label">calculator</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-disk"></span><span class="aui-buttonitem-label">disk</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-clock"></span><span class="aui-buttonitem-label">clock</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-zoomin"></span><span class="aui-buttonitem-label">zoomin</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-zoomout"></span><span class="aui-buttonitem-label">zoomout</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-search"></span><span class="aui-buttonitem-label">search</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-wrench"></span><span class="aui-buttonitem-label">wrench</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-gear"></span><span class="aui-buttonitem-label">gear</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-heart"></span><span class="aui-buttonitem-label">heart</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-star"></span><span class="aui-buttonitem-label">star</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-link"></span><span class="aui-buttonitem-label">link</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-cancel"></span><span class="aui-buttonitem-label">cancel</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-plus"></span><span class="aui-buttonitem-label">plus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-plusthick"></span><span class="aui-buttonitem-label">plusthick</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-minus"></span><span class="aui-buttonitem-label">minus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-minusthick"></span><span class="aui-buttonitem-label">minusthick</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-close"></span><span class="aui-buttonitem-label">close</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-closethick"></span><span class="aui-buttonitem-label">closethick</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-key"></span><span class="aui-buttonitem-label">key</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-lightbulb"></span><span class="aui-buttonitem-label">lightbulb</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-scissors"></span><span class="aui-buttonitem-label">scissors</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-clipboard"></span><span class="aui-buttonitem-label">clipboard</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-copy"></span><span class="aui-buttonitem-label">copy</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-image"></span><span class="aui-buttonitem-label">image</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-video"></span><span class="aui-buttonitem-label">video</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-script"></span><span class="aui-buttonitem-label">script</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-alert"></span><span class="aui-buttonitem-label">alert</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-info"></span><span class="aui-buttonitem-label">info</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-notice"></span><span class="aui-buttonitem-label">notice</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-help"></span><span class="aui-buttonitem-label">help</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-check"></span><span class="aui-buttonitem-label">check</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-bullet"></span><span class="aui-buttonitem-label">bullet</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-radio-off"></span><span class="aui-buttonitem-label">radio-off</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-radio-on"></span><span class="aui-buttonitem-label">radio-on</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pin-l"></span><span class="aui-buttonitem-label">pin-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pin-b"></span><span class="aui-buttonitem-label">pin-b</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-play"></span><span class="aui-buttonitem-label">play</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-pause"></span><span class="aui-buttonitem-label">pause</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-next"></span><span class="aui-buttonitem-label">seek-next</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-prev"></span><span class="aui-buttonitem-label">seek-prev</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-end"></span><span class="aui-buttonitem-label">seek-end</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-seek-first"></span><span class="aui-buttonitem-label">seek-first</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-stop"></span><span class="aui-buttonitem-label">stop</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-eject"></span><span class="aui-buttonitem-label">eject</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-volume-off"></span><span class="aui-buttonitem-label">volume-off</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-volume-on"></span><span class="aui-buttonitem-label">volume-on</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-power"></span><span class="aui-buttonitem-label">power</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-signal-diag"></span><span class="aui-buttonitem-label">signal-diag</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-signal"></span><span class="aui-buttonitem-label">signal</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-0"></span><span class="aui-buttonitem-label">battery-0</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-1"></span><span class="aui-buttonitem-label">battery-1</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-2"></span><span class="aui-buttonitem-label">battery-2</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-battery-3"></span><span class="aui-buttonitem-label">battery-3</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-plus"></span><span class="aui-buttonitem-label">circle-plus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-minus"></span><span class="aui-buttonitem-label">circle-minus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-close"></span><span class="aui-buttonitem-label">circle-close</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-triangle-r"></span><span class="aui-buttonitem-label">circle-triangle-r</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-arrow-l"></span><span class="aui-buttonitem-label">circle-arrow-l</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-zoomin"></span><span class="aui-buttonitem-label">circle-zoomin</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circle-check"></span><span class="aui-buttonitem-label">circle-check</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circlesmall-plus"></span><span class="aui-buttonitem-label">circlesmall-plus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-circlesmall-minus"></span><span class="aui-buttonitem-label">circlesmall-minus</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-dotted-vertical"></span><span class="aui-buttonitem-label">grip-dotted-vertical</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-dotted-horizontal"></span><span class="aui-buttonitem-label">grip-dotted-horizontal</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-solid-vertical"></span><span class="aui-buttonitem-label">grip-solid-vertical</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-solid-horizontal"></span><span class="aui-buttonitem-label">grip-solid-horizontal</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-gripsmall-diagonal-br"></span><span class="aui-buttonitem-label">gripsmall-diagonal-br</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-grip-diagonal-br"></span><span class="aui-buttonitem-label">grip-diagonal-br</span></button>
	<button type="button" class="aui-buttonitem-content" title=""><span class="aui-buttonitem-icon aui-icon aui-icon-loading"></span><span class="aui-buttonitem-label">loading</span></button>
</div>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Icon Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Tabs</h2>
<liferay-ui:tabs
	names="Tabs Example,Tabs Code,Tabs taglib Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div class="markupTabs">
			<ul class="aui-tabview-list aui-widget-hd tab-list">
				<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">First Tab</a></li>
				<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">Second Tab</a></li>
				<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">Third Tab</a></li>
			</ul>

			<div class="aui-tabview-content aui-widget-bd tab-content">
				<div class="aui-tabview-content-item">
					<h5 class="tab-heading">1. First Tab</h5><br />
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</div>
				<div class="aui-tabview-content-item">
					<h5 class="tab-heading">2. Second Tab</h5><br />
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</div>
				<div class="aui-tabview-content-item">
					<h5 class="tab-heading">3.Third Tab</h5><br />
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</div>
			</div>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<div class="markupTabs">
	<ul class="aui-tabview-list aui-widget-hd tab-list">
		<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">Hello A</a></li>
		<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">Hello B</a></li>
		<li class="aui-tab"><a class="aui-tab-label" href="javascript:;">Hello C</a></li>
	</ul>

	<div class="aui-tabview-content aui-widget-bd tab-content">
		<div class="aui-tabview-content-item">
			<h5 class="tab-heading">1. New tab content</h5><br />
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
		<div class="aui-tabview-content-item">
			<h5 class="aui-tabview-content-item">2. New tab content</h5><br />
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
		<div class="aui-tabview-content-item">
			<h5 class="aui-tabview-content-item">3. New tab content</h5><br />
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		</div>
	</div>
</div>

<script type="text/javascript">
AUI().ready( 'aui-tabs', 'substitute', function(A) {
	var tabs = A.all('.markupTabs');
	tabs.each( function(item){
		var tab = new A.TabView(
			{
				boundingBox: item,
				listNode: item.one('.tab-list'),
				contentNode: item.one('.tab-content')
			}
		).render();
	});
});
</script>
			</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Tabs Code" class="textarea" />

	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
&lt;liferay-ui:tabs
	names="First Tab,Second Tab,Third Tab"
&gt;
	&lt;liferay-ui:section&gt;
		&lt;h5&gt;1. First Tab&lt;/h5&gt;&lt;br /&gt;
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	&lt;/liferay-ui:section&gt;
	&lt;liferay-ui:section&gt;
	&lt;h5&gt;2. Second Tab&lt;/h5&gt;&lt;br /&gt;
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	&lt;/liferay-ui:section&gt;
	&lt;liferay-ui:section&gt;
	&lt;h5&gt;3. Third Tab&lt;/h5&gt;&lt;br /&gt;
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	&lt;/liferay-ui:section&gt;
&lt;/liferay-ui:tabs&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Tabs Taglib Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Breadcrumb Portlet</h2>
<liferay-ui:tabs
	names="Breadcrumb Example,Breadcrumb Simple,Breadcrumb Styling"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div class="aui-w33 aui-column aui-column-first">
			<div class="aui-column-content-only">
				<liferay-ui:breadcrumb displayStyle="horizontal"/>
			</div>
		</div>
		<div class="aui-w33 aui-column aui-column-first">
			<div class="aui-column-content-only">
				<div class="breadcrumb-styled">
					<liferay-ui:breadcrumb displayStyle="horizontal"/>
				</div>
			</div>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
Breadcrumb Portlet
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Breadcrumb Code" class="textarea" />

	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
Breadcrumb Portlet

/* ---------- Breadcrumbs CSS---------- */

.breadcrumb-styled {
	border: 2px solid #0E080A;
	@include border-radius(5px);
	height: 4em;
	margin-bottom: 2em;
}

.breadcrumb-styled li a {
	padding: 10px 25px 10px 15px;
	text-decoration: none;
	border-top: 1px solid #EFEFEF;
	border-bottom: 1px solid #EFEFEF;
	font-size: 12px;
}

.breadcrumb-styled li a:hover {
	text-decoration: underline;
}
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Breadcrumb Styling Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Columns</h2>
<liferay-ui:tabs
	names="Column Example,Single Column,Two Columns (50% / 50%),Two Columns (33% / 66%),Two Columns (25% / 75%),Sub Columns,Three Columns (33% / 33% / 33%),All Possible Column Widths"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="columns">
			<aui:layout>
				<aui:column columnWidth="100" first="true">
					<h5 class="column-heading">One and Only Column (100%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
			</aui:layout>
			<hr class="alt1"/>

			<aui:layout>
				<aui:column columnWidth="50" first="true">
					<h5 class="column-heading">Column 1 (50%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>

				<aui:column columnWidth="50" last="true">
					<h5 class="column-heading">Column 2 (50%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
			</aui:layout>
			<hr class="alt1"/>

			<aui:layout>
				<aui:column columnWidth="33" first="true">
					<h5 class="column-heading">Column 3 (33%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
				<aui:column columnWidth="66" last="true">
					<h5 class="column-heading">Column 4 (66%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
			</aui:layout>
			<hr class="alt1"/>

			<aui:layout>
				<aui:column columnWidth="25" first="true">
					<h5 class="column-heading">Column 5 (25%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</aui:column>
				<aui:column columnWidth="75" last="true">
					<h5 class="column-heading">Column 6 (75%)</h5>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					<p>
						<ul>
							<li>Item 1</li>
							<li>Item 2</li>
							<li>Item 3</li>
							<li>Item 4</li>
							<li>Item 5</li>
							<li>Item 6</li>
							<li>Item 7</li>
						</ul>
					</p>
				</aui:column>
			</aui:layout>
				<hr class="alt1"/>

				<aui:layout>
					<aui:column columnWidth="25" first="true">
						<h5 class="column-heading">Column 7 (25%)</h5>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
						<p>
							<ul>
								<li>Item 1</li>
								<li>Item 2</li>
								<li>Item 3</li>
								<li>Item 4</li>
								<li>Item 5</li>
								<li>Item 6</li>
								<li>Item 7</li>
							</ul>
						</p>
					</aui:column>
					<aui:column columnWidth="50" center="true">
						<h5 class="column-heading">Column 8 (50% container)</h5>
						<aui:layout>
							<aui:column columnWidth="60" first="true">
								<h6 class="column-heading">Sub-Column 1 (60%)</h6>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
							</aui:column>
							<aui:column columnWidth="40" last="true">
										<h6 class="column-heading">Sub-Column 2 (40%)</h6>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
							</aui:column>
						</aui:layout>
					</aui:column>
					<aui:column columnWidth="25" last="true">
						<h5 class="column-heading">Column 9 (25%)</h5>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					</aui:column>
				</aui:layout>
					<hr class="alt1"/>

				<aui:layout>
					<aui:column columnWidth="33" first="true">
						<h5 class="column-heading">Column 10 (33%)</h5>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					</aui:column>
					<aui:column columnWidth="33">
						<h5 class="column-heading">Column 11 (33%)</h5>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					</aui:column>
					<aui:column columnWidth="33" last="true">
						<h5 class="column-heading">Column 12 (33%)</h5>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
					</aui:column>
				</aui:layout>
					<hr class="alt1"/>

				<div class="column-width">
					<h5>Example of all Possible Column Widths</h5>
					<aui:layout >
						<aui:column columnWidth="10" first="true">
							<h5>10%</h5>
						</aui:column>
						<aui:column columnWidth="90" last="true">
							<h5>90%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="15" first="true">
							<h5>15%</h5>
						</aui:column>
						<aui:column columnWidth="85" last="true">
							<h5>85%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="20" first="true">
							<h5>20%</h5>
						</aui:column>
						<aui:column columnWidth="80" last="true">
							<h5>80%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="25" first="true">
							<h5>25%</h5>
						</aui:column>
						<aui:column columnWidth="75" last="true">
							<h5>75%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="30" first="true">
							<h5>30%</h5>
						</aui:column>
						<aui:column columnWidth="70" last="true">
							<h5>70%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="33" first="true">
							<h5>33%</h5>
						</aui:column>
						<aui:column columnWidth="66" last="true">
							<h5>66%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="35" first="true">
							<h5>35%</h5>
						</aui:column>
						<aui:column columnWidth="65" last="true">
							<h5>65%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="40" first="true">
							<h5>40%</h5>
						</aui:column>
						<aui:column columnWidth="60" last="true">
							<h5>60%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="45" first="true">
							<h5>45%</h5>
						</aui:column>
						<aui:column columnWidth="55" last="true">
							<h5>55%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="50" first="true">
							<h5>50%</h5>
						</aui:column>
						<aui:column columnWidth="50" last="true">
							<h5>50%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="55" first="true">
							<h5>55%</h5>
						</aui:column>
						<aui:column columnWidth="45" last="true">
							<h5>45%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="60" first="true">
							<h5>60%</h5>
						</aui:column>
						<aui:column columnWidth="40" last="true">
							<h5>40%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="62" first="true">
							<h5>62%</h5>
						</aui:column>
						<aui:column columnWidth="38" last="true">
							<h5>38%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="65" first="true">
							<h5>65%</h5>
						</aui:column>
						<aui:column columnWidth="35" last="true">
							<h5>35%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="66" first="true">
							<h5>66%</h5>
						</aui:column>
						<aui:column columnWidth="33" last="true">
							<h5>33%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="70" first="true">
							<h5>70%</h5>
						</aui:column>
						<aui:column columnWidth="30" last="true">
							<h5>30%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="75" first="true">
							<h5>75%</h5>
						</aui:column>
						<aui:column columnWidth="25" last="true">
							<h5>25%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="80" first="true">
							<h5>80%</h5>
						</aui:column>
						<aui:column columnWidth="20" last="true">
							<h5>20%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="85" first="true">
							<h5>85%</h5>
						</aui:column>
						<aui:column columnWidth="15" last="true">
							<h5>15%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="90" first="true">
							<h5>90%</h5>
						</aui:column>
						<aui:column columnWidth="10" last="true">
							<h5>10%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="95" first="true">
							<h5>95%</h5>
						</aui:column>
					</aui:layout>
					<aui:layout>
						<aui:column columnWidth="100" first="true">
							<h5>100%</h5>
						</aui:column>
					</aui:layout>
					</div>
			</div>
		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="100" first="true"&gt;
		&lt;h5 class="column-heading"&gt;One and Only Column (100%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Single Column Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="50" first="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 1 (50%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;

	&lt;aui:column columnWidth="50" last="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 2 (50%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Two Columns (50% / 50%) Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="33" first="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 3 (33%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="66" last="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 4 (66%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Two Columns (33% / 66%) Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="25" first="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 5 (25%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="75" last="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 6 (75%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;
			&lt;ul&gt;
				&lt;li&gt;Item 1&lt;/li&gt;
				&lt;li&gt;Item 2&lt;/li&gt;
				&lt;li&gt;Item 3&lt;/li&gt;
				&lt;li&gt;Item 4&lt;/li&gt;
				&lt;li&gt;Item 5&lt;/li&gt;
				&lt;li&gt;Item 6&lt;/li&gt;
				&lt;li&gt;Item 7&lt;/li&gt;
			&lt;/ul&gt;
		&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Two Columns (25% / 75%) Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="25" first="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 7 (25%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;
			&lt;ul&gt;
				&lt;li&gt;Item 1&lt;/li&gt;
				&lt;li&gt;Item 2&lt;/li&gt;
				&lt;li&gt;Item 3&lt;/li&gt;
				&lt;li&gt;Item 4&lt;/li&gt;
				&lt;li&gt;Item 5&lt;/li&gt;
				&lt;li&gt;Item 6&lt;/li&gt;
				&lt;li&gt;Item 7&lt;/li&gt;
			&lt;/ul&gt;
		&lt;/p&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="50" center="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 8 (50% container)&lt;/h5&gt;
		&lt;aui:layout&gt;
			&lt;aui:column columnWidth="60" first="true"&gt;
				&lt;h6 class="column-heading"&gt;Sub-Column 1 (60%)&lt;/h6&gt;
				&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
			&lt;/aui:column&gt;
			&lt;aui:column columnWidth="40" last="true"&gt;
						&lt;h6 class="column-heading"&gt;Sub-Column 2 (40%)&lt;/h6&gt;
					&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
			&lt;/aui:column&gt;
		&lt;/aui:layout&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="25" last="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 9 (25%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Sub Columns Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="33" first="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 10 (33%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="33"&gt;
		&lt;h5 class="column-heading"&gt;Column 11 (33%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="33" last="true"&gt;
		&lt;h5 class="column-heading"&gt;Column 12 (33%)&lt;/h5&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
		&lt;p&gt;Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.&lt;/p&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Three Columns (33% / 33% / 33%) Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:layout &gt;
	&lt;aui:column columnWidth="10" first="true"&gt;
		&lt;h5&gt;10%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="90" last="true"&gt;
		&lt;h5&gt;90%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="15" first="true"&gt;
		&lt;h5&gt;15%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="85" last="true"&gt;
		&lt;h5&gt;85%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="20" first="true"&gt;
		&lt;h5&gt;20%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="80" last="true"&gt;
		&lt;h5&gt;80%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="25" first="true"&gt;
		&lt;h5&gt;25%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="75" last="true"&gt;
		&lt;h5&gt;75%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="30" first="true"&gt;
		&lt;h5&gt;30%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="70" last="true"&gt;
		&lt;h5&gt;70%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="33" first="true"&gt;
		&lt;h5&gt;33%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="66" last="true"&gt;
		&lt;h5&gt;66%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="35" first="true"&gt;
		&lt;h5&gt;35%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="65" last="true"&gt;
		&lt;h5&gt;65%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="40" first="true"&gt;
		&lt;h5&gt;40%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="60" last="true"&gt;
		&lt;h5&gt;60%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="45" first="true"&gt;
		&lt;h5&gt;45%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="55" last="true"&gt;
		&lt;h5&gt;55%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="50" first="true"&gt;
		&lt;h5&gt;50%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="50" last="true"&gt;
		&lt;h5&gt;50%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="55" first="true"&gt;
		&lt;h5&gt;55%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="45" last="true"&gt;
		&lt;h5&gt;45%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="60" first="true"&gt;
		&lt;h5&gt;60%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="40" last="true"&gt;
		&lt;h5&gt;40%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="62" first="true"&gt;
		&lt;h5&gt;62%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="38" last="true"&gt;
		&lt;h5&gt;38%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="65" first="true"&gt;
		&lt;h5&gt;65%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="35" last="true"&gt;
		&lt;h5&gt;35%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="66" first="true"&gt;
		&lt;h5&gt;66%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="33" last="true"&gt;
		&lt;h5&gt;33%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="70" first="true"&gt;
		&lt;h5&gt;70%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="30" last="true"&gt;
		&lt;h5&gt;30%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="75" first="true"&gt;
		&lt;h5&gt;75%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="25" last="true"&gt;
		&lt;h5&gt;25%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="80" first="true"&gt;
		&lt;h5&gt;80%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="20" last="true"&gt;
		&lt;h5&gt;20%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="85" first="true"&gt;
		&lt;h5&gt;85%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="15" last="true"&gt;
		&lt;h5&gt;15%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="90" first="true"&gt;
		&lt;h5&gt;90%&lt;/h5&gt;
	&lt;/aui:column&gt;
	&lt;aui:column columnWidth="10" last="true"&gt;
		&lt;h5&gt;10%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="95" first="true"&gt;
		&lt;h5&gt;95%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
&lt;aui:layout&gt;
	&lt;aui:column columnWidth="100" first="true"&gt;
		&lt;h5&gt;100%&lt;/h5&gt;
	&lt;/aui:column&gt;
&lt;/aui:layout&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="All Possible Columns Code" class="textarea" />

		</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Images</h2>
<liferay-ui:tabs
	names="Image Viewer Example,Image Viewer Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="gallery1" class="gallery">
			<a href="http://placehold.it/640x420/789723/ffffff/&text=Image+1" title="Image 1">
				<img class="picture" src="http://placehold.it/240x120/789723/ffffff/&text=Image+1" />
			</a>
			<a href="http://placehold.it/640x420/E8117F/ffffff/&text=Image+2" title="Image 2">
				<img class="picture" src="http://placehold.it/240x120/E8117F/ffffff/&text=Image+2" />
			</a>
			<a href="http://placehold.it/640x420/6BB2FB/ffffff/&text=Image+3" title="Image 3">
				<img class="picture" src="http://placehold.it/240x120/6BB2FB/ffffff/&text=Image+3" />
			</a>
			<a href="http://placehold.it/640x420/FFA142/ffffff/&text=Image+4" title="Image 4">
				<img class="picture" src="http://placehold.it/240x120/FFA142/ffffff/&text=Image+4" />
			</a>
			<a href="http://placehold.it/640x420/86BC00/ffffff/&text=Image+5" title="Image 5">
				<img class="picture" src="http://placehold.it/240x120/86BC00/ffffff/&text=Image+5" />
			</a>
			<a href="http://placehold.it/640x420/DC250F/ffffff/&text=Image+6" title="Image 6">
				<img class="picture" src="http://placehold.it/240x120/DC250F/ffffff/&text=Image+6" />
			</a>
			<a href="http://placehold.it/640x420/E4DB0F/ffffff/&text=Image+7" title="Image 7">
				<img class="picture" src="http://placehold.it/240x120/E4DB0F/ffffff/&text=Image+7" />
			</a>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<div id="gallery1" class="gallery">
	<a href="http://placehold.it/640x420/789723/ffffff/&text=Image+1" title="Image 1"> <img class="picture" src="http://placehold.it/240x120/789723/ffffff/&text=Image+1" />
	</a>
	<a href="http://placehold.it/640x420/E8117F/ffffff/&text=Image+2" title="Image 2">
		<img class="picture" src="http://placehold.it/240x120/E8117F/ffffff/&text=Image+2" />
	</a>
	<a href="http://placehold.it/640x420/6BB2FB/ffffff/&text=Image+3" title="Image 3">
		<img class="picture" src="http://placehold.it/240x120/6BB2FB/ffffff/&text=Image+3" />
	</a>
	<a href="http://placehold.it/640x420/FFA142/ffffff/&text=Image+4" title="Image 4">
		<img class="picture" src="http://placehold.it/240x120/FFA142/ffffff/&text=Image+4" />
	</a>
	<a href="http://placehold.it/640x420/86BC00/ffffff/&text=Image+5" title="Image 5">
		<img class="picture" src="http://placehold.it/240x120/86BC00/ffffff/&text=Image+5" />
	</a>
	<a href="http://placehold.it/640x420/DC250F/ffffff/&text=Image+6" title="Image 6">
		<img class="picture" src="http://placehold.it/240x120/DC250F/ffffff/&text=Image+6" />
	</a>
	<a href="http://placehold.it/640x420/E4DB0F/ffffff/&text=Image+7" title="Image 7">
		<img class="picture" src="http://placehold.it/240x120/E4DB0F/ffffff/&text=Image+7" />
	</a>
</div>

<script type="text/javascript">

	AUI().ready('aui-image-viewer-base', function(A) {

	var imageViewer1 = new A.ImageViewer({
		links: '#gallery1 a',
		captionFromTitle: true,
		preloadAllImages: true,
		showInfo: true,
		after: {
			anim: function(event) {
			},
			request: function(event) {
			},
			load: function(event) {
			}
		}
	})
	.render();
	});

</script>
			</liferay-util:buffer>

			<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Image Viewer Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Image Carousel</h2>
<liferay-ui:tabs
	names="Image Carousel Example,Image Carousel Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="demo">
			<div style="background: url(http://placehold.it/940x260/789723/ffffff/&text=Image+1); width: 940px; height: 260px;" class="aui-carousel-item">Image1</div>
			<div style="background: url(http://placehold.it/940x260/E8117F/ffffff/&text=Image+2); width: 940px; height: 260px;" class="aui-carousel-item">Image2</div>
			<div style="background: url(http://placehold.it/940x260/6BB2FB/ffffff/&text=Image+3); width: 940px; height: 260px;" class="aui-carousel-item">Image3</div>
			<div style="background: url(http://placehold.it/940x260/FFA142/ffffff/&text=Image+4); width: 940px; height: 260px;" class="aui-carousel-item">Image4</div>
			<div style="background: url(http://placehold.it/940x260/86BC00/ffffff/&text=Image+5); width: 940px; height: 260px;" class="aui-carousel-item">Image5</div>
			<div style="background: url(http://placehold.it/940x260/DC250F/ffffff/&text=Image+6); width: 940px; height: 260px;" class="aui-carousel-item">Image6</div>
			<div style="background: url(http://placehold.it/940x260/E4DB0F/ffffff/&text=Image+7); width: 940px; height: 260px;" class="aui-carousel-item">Image7</div>
			<div style="background: url(http://placehold.it/940x260/7400E4/ffffff/&text=Image+8); width: 940px; height: 260px;" class="aui-carousel-item">Image8</div>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<div id="demo-example">
	<div style="background: url(http://placehold.it/940x260/789723/ffffff/&text=Image+1); width: 940px; height: 260px;" class="aui-carousel-item">Image1</div>
	<div style="background: url(http://placehold.it/940x260/E8117F/ffffff/&text=Image+2); width: 940px; height: 260px;" class="aui-carousel-item">Image2</div>
	<div style="background: url(http://placehold.it/940x260/6BB2FB/ffffff/&text=Image+3); width: 940px; height: 260px;" class="aui-carousel-item">Image3</div>
	<div style="background: url(http://placehold.it/940x260/FFA142/ffffff/&text=Image+4); width: 940px; height: 260px;" class="aui-carousel-item">Image4</div>
	<div style="background: url(http://placehold.it/940x260/86BC00/ffffff/&text=Image+5); width: 940px; height: 260px;" class="aui-carousel-item">Image5</div>
	<div style="background: url(http://placehold.it/940x260/DC250F/ffffff/&text=Image+6); width: 940px; height: 260px;" class="aui-carousel-item">Image6</div>
	<div style="background: url(http://placehold.it/940x260/E4DB0F/ffffff/&text=Image+7); width: 940px; height: 260px;" class="aui-carousel-item">Image7</div>
	<div style="background: url(http://placehold.it/940x260/7400E4/ffffff/&text=Image+8); width: 940px; height: 260px;" class="aui-carousel-item">Image8</div>
</div>

<script type="text/javascript" charset="utf-8">

AUI().ready('aui-carousel', function(A) {

	var component = new A.Carousel(
		{
			intervalTime: 1,
			contentBox: '#demo',
			activeIndex: 0,
			height: 260,
			width: 940
		}
	).render();
});

</script>
			</liferay-util:buffer>

			<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Image Carousel Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Media Gallery</h2>
<liferay-ui:tabs
	names="Media Gallery Example,Media Gallery Code"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="gallery" class="gallery">
			<a href="http://www.youtube.com/watch?v=z-mPuFs4Gls&width=560&height=349" title="Liferay Retreat 2010">
				<img alt="Liferay Retreat 2010 (YouTube movie)" class="picture" src="http://placehold.it/240x120/C8322C/ffffff/&text=YouTube+Video">
			</a>
			<a data-options="width=400&height=300" href="http://vimeo.com/18225907" title="Institucional SEA Liferay Roadshow 2010 (Vimeo movie)">
				<img alt="Institucional SEA Liferay Roadshow 2010 - parte 2 (Vimeo movie)" class="picture" src="http://placehold.it/240x120/388FC5/ffffff/&text=Vimeo+Movie">
			</a>
			<a href="http://www.dailymotion.com/video/xdi011_op-nworks-presente-le-portail-d-ent_tech&width=480&height=270" title="Op'nWorks presente le portail d'entreprise Liferay">
				<img alt="Op'nWorks presente le portail d'entreprise Liferay" class="picture" src="http://placehold.it/240x120/204A6A/ffffff/&text=Dailymotion">
			</a>
			<a href="http://placehold.it/640x420/789723/ffffff/&text=Image+1" title="Image 1">
				<img alt="Image 1" class="picture" src="http://placehold.it/240x120/789723/ffffff/&text=Image+1">
			</a>
			<a href="http://placehold.it/640x420/E8117F/ffffff/&text=Image+2" title="Image 2">
				<img alt="Image 2" class="picture" src="http://placehold.it/240x120/E8117F/ffffff/&text=Image+2">
			</a>
			<a href="http://placehold.it/640x420/6BB2FB/ffffff/&text=Image+3" title="Image 3">
				<img alt="Image 3" class="picture" src="http://placehold.it/240x120/6BB2FB/ffffff/&text=Image+3">
			</a>
			<a href="http://placehold.it/640x420/FFA142/ffffff/&text=Image+4" title="Image 4">
				<img alt="Image 4" class="picture" src="http://placehold.it/240x120/FFA142/ffffff/&text=Image+4" />
			</a>
		</div>
	</liferay-ui:section>
	<liferay-ui:section>
		<liferay-util:buffer var="codeContent">
<div id="gallery" class="gallery">
	<a href="http://www.youtube.com/watch?v=z-mPuFs4Gls&width=560&height=349" title="Liferay Retreat 2010">
		<img alt="Liferay Retreat 2010 (YouTube movie)" class="picture" src="http://placehold.it/240x120/C8322C/ffffff/&text=YouTube+Video">
	</a>
	<a data-options="width=400&height=300" href="http://vimeo.com/18225907" title="Institucional SEA Liferay Roadshow 2010 (Vimeo movie)">
		<img alt="Institucional SEA Liferay Roadshow 2010 - parte 2 (Vimeo movie)" class="picture" src="http://placehold.it/240x120/388FC5/ffffff/&text=Vimeo+Movie">
	</a>
	<a href="http://www.dailymotion.com/video/xdi011_op-nworks-presente-le-portail-d-ent_tech&width=480&height=270" title="Op'nWorks presente le portail d'entreprise Liferay">
		<img alt="Op'nWorks presente le portail d'entreprise Liferay" class="picture" src="http://placehold.it/240x120/204A6A/ffffff/&text=Dailymotion">
	</a>
	<a href="http://placehold.it/640x420/789723/ffffff/&text=Image+1" title="Image 1">
		<img alt="Image 1" class="picture" src="http://placehold.it/240x120/789723/ffffff/&text=Image+1">
	</a>
	<a href="http://placehold.it/640x420/E8117F/ffffff/&text=Image+2" title="Image 2">
		<img alt="Image 2" class="picture" src="http://placehold.it/240x120/E8117F/ffffff/&text=Image+2">
	</a>
	<a href="http://placehold.it/640x420/6BB2FB/ffffff/&text=Image+3" title="Image 3">
		<img alt="Image 3" class="picture" src="http://placehold.it/240x120/6BB2FB/ffffff/&text=Image+3">
	</a>
	<a href="http://placehold.it/640x420/FFA142/ffffff/&text=Image+4" title="Image 4">
		<img alt="Image 4" class="picture" src="http://placehold.it/240x120/FFA142/ffffff/&text=Image+4" />
	</a>
</div>

<script type="text/javascript">

AUI().ready('aui-image-viewer-gallery', 'aui-media-viewer-plugin', function(A) {

	var mediaGallery = new A.ImageGallery({
		caption: 'AlloyUI - MediaGallery mixed demo',
		delay: 3000,
		links: '#gallery a',
		plugins: [
			{
				cfg: {
					'providers.dailymotion': {
						container: '<iframe frameborder="0" width="{width}" height="{height}" src="http://www.dailymotion.com/embed/video/{media}"></iframe>',
						matcher: new RegExp(
							A.Lang.sub(
								A.MediaViewerPlugin.REGEX_DOMAIN,
								{
									domain: 'dailymotion.com'
								}
							),
							'i'
						),
						options: A.MediaViewerPlugin.DEFAULT_OPTIONS,
						mediaRegex: /\/video\/([^&#_]*)/i
					}
				},
				fn: A.MediaViewerPlugin
			}
		],
		preloadNeighborImages: true
	});

	mediaGallery.render();
</script>
			</liferay-util:buffer>

			<aui:input type="textarea" value="<%= codeContent %>" name="code" label="Media Gallery Code" class="textarea" />

	</liferay-ui:section>
</liferay-ui:tabs>
<hr class="alt1"/>

<h2>Forms</h2>
<liferay-ui:tabs
	names="Form Example,Example 1 Code,Example 2 Code,Example 3 Code"
	refresh="<%= false %>"
>
		<liferay-ui:section>
			<aui:layout>
				<aui:column columnWidth="33" first="true">
					<div id="myForm">
						<aui:form name="Form Example 1">

							<div class="aui-fieldset-bd aui-widget-bd">
								<aui:input name="Text" value="" label="Text" />
								<aui:input name="Password" value="" label="Password" />

								<aui:select name="selectList" label="Select(Menu)">
									<aui:option name="menuSelection1" label="Menu Selection 1" />
									<aui:option name="menuSelection2" label="Menu Selection 2" />
									<aui:option name="menuSelection3" label="Menu Selection 3" />
								</aui:select>

								<aui:select name="selectMultipleList" label="Select Multiple(Menu)" multiple="true">
									<aui:option name="menuSelection1" label="Menu Selection 1" selected="true" />
									<aui:option name="menuSelection2" label="Menu Selection 2" />
									<aui:option name="menuSelection3" label="Menu Selection 3" />
								</aui:select>

								<aui:input name="textarea" type="textarea" value="" label="Textarea" />

								<aui:field-wrapper name="checkboxes" label="Checkboxes">
									<aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 1" />
									<aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 2" />
									<aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 3" />
								</aui:field-wrapper>

								<aui:field-wrapper name="radio buttons">
									<aui:input inlineLabel="right" name="radio buttons" type="radio" value="1" label="Radio 1" />
									<aui:input inlineLabel="right" name="radio buttons" type="radio" value="2" label="Radio 2" />
									<aui:input inlineLabel="right" name="radio buttons" type="radio" value="3" label="Radio 3" />
								</aui:field-wrapper>

							</div>

								<aui:button name="submitButton" type="button" value="submit" first="true" />
								<aui:button name="resetButton" type="button" value="reset" />
								<aui:button name="cancelButton" type="button" value="cancel" last="true" />

						</aui:form>
					</div>
				</aui:column>

				<aui:column columnWidth="33" class="aui-field-labels-inline">
					<aui:fieldset column="true" title="Form Example 2" class="aui-field-labels-inline">

						<aui:input alignLabel="right" name="username" value="" label="Username" />
							<p class="aui-form-hint">
								<span>The username you wish to use.</span>
							</p>

						<aui:input alignLabel="right" name="email" value="" label="Email" />
							<p class="aui-form-hint">
								<span>The email address to associate with this account.</span>
							</p>

						<aui:select alignLabel="right" name="accountType" label="Account Type">
							<aui:option name="premium" label="Premium User" />
							<aui:option name="midlevel" label="Mid-level User" />
							<aui:option name="regular" label="Regular User" />
						</aui:select>
						<p class="aui-form-hint">
							<span>What level of user would you like to be?</span>
						</p>

						<aui:input alignLabel="right" name="interests" type="textarea" value="" label="Interests" />
							<p class="aui-form-hint">
								<span>What are some of your interests?</span>
							</p>

						<aui:field-wrapper name="userOptions" label="User Options">
							<aui:input alignLabel="right" type="checkbox" name="notifyMessages" value="1" label="Do you wish to be notified by email when another user sends you a message?" />
							<aui:input alignLabel="right" type="checkbox" name="displayEmail" value="1" label="Do you wish for other users to be able to see your email address?" />
							<aui:input alignLabel="right" type="checkbox" name="subscribeNewsletter" value="1" label="Would you like to receive our monthly newsletter?" />
						</aui:field-wrapper>
						<p class="aui-form-hint">
							We will not sell your information, or needlessly spam you. This is just to keep you in the loop.
						</p>

						<div class="aui-button-holder">
							<aui:button name="submitButton" type="button" value="submit" first="true" />
							<aui:button name="resetButton" type="button" value="reset" last="true" />
						</div>
					</aui:fieldset>
				</aui:column>

				<aui:column columnWidth="33" last="true">
					<aui:fieldset column="true" title="Form Example 3" class="aui-field-labels-inline">

						<aui:input alignLabel="right" name="fullName" value="" label="Full Name" />
							<p class="aui-form-hint">
								<span>Your full name.</span>
							</p>

						<aui:input alignLabel="right" name="cityState" value="" label="City, State" />
							<p class="aui-form-hint">
								<span>What city and state do you live in?</span>
							</p>

						<aui:select alignLabel="right" name="ageGroup" label="Your Age Group">
							<aui:option name="teen" label="13 - 19" selected="true" />
							<aui:option name="young-adult" label="20 - 30" />
							<aui:option name="adult" label="31 - 50" />
							<aui:option name="vintage-adult" label="51+" />
						</aui:select>
						<p class="aui-form-hint">
							<span>The age group that best describes you.</span>
						</p>

						<aui:input alignLabel="right" name="description" type="textarea" value="" label="Description" />
							<p class="aui-form-hint">
								<span>Please describe yourself.</span>
							</p>

						<aui:field-wrapper name="radio buttons">
							<aui:input inlineLabel="right" name="usageLevel" type="radio" value="1" label="All the time. I'm already addicted just by signing up." />
							<aui:input inlineLabel="right" name="usageLevel" type="radio" value="2" label="Sometimes, but maybe more. Depends how good it is." />
							<aui:input inlineLabel="right" name="usageLevel" type="radio" value="3" label="Probably not that often, I'm just checking it out." />
						</aui:field-wrapper>
							<p class="aui-form-hint">
								This helps us guage your interest in us.
							</p>

						<div class="aui-button-holder">
							<aui:button name="submitButton" type="button" value="submit" first="true" />
							<aui:button name="resetButton" type="button" value="reset" last="true" />
						</div>
					</aui:fieldset>
				</aui:column>
			</aui:layout>
		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:form name="Form Example 1"&gt;

	&lt;div class="aui-fieldset-bd aui-widget-bd"&gt;
		&lt;aui:input name="Text" value="" label="Text" /&gt;
		&lt;aui:input name="Password" value="" label="Password" /&gt;

		&lt;aui:select name="selectList" label="Select(Menu)"&gt;
			&lt;aui:option name="menuSelection1" label="Menu Selection 1" /&gt;
			&lt;aui:option name="menuSelection2" label="Menu Selection 2" /&gt;
			&lt;aui:option name="menuSelection3" label="Menu Selection 3" /&gt;
		&lt;/aui:select&gt;

		&lt;aui:select name="selectMultipleList" label="Select Multiple(Menu)" multiple="true"&gt;
			&lt;aui:option name="menuSelection1" label="Menu Selection 1" selected="true" /&gt;
			&lt;aui:option name="menuSelection2" label="Menu Selection 2" /&gt;
			&lt;aui:option name="menuSelection3" label="Menu Selection 3" /&gt;
		&lt;/aui:select&gt;

		&lt;aui:input name="textarea" type="textarea" value="" label="Textarea" /&gt;

		&lt;aui:field-wrapper name="checkboxes" label="Checkboxes"&gt;
			&lt;aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 1" /&gt;
			&lt;aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 2" /&gt;
			&lt;aui:input inlineLabel="right" type="checkbox" name="checkboxes" value="1" label="Checkbox 3" /&gt;
		&lt;/aui:field-wrapper&gt;

		&lt;aui:field-wrapper name="radio buttons"&gt;
			&lt;aui:input inlineLabel="right" name="radio buttons" type="radio" value="1" label="Radio 1" /&gt;
			&lt;aui:input inlineLabel="right" name="radio buttons" type="radio" value="2" label="Radio 2" /&gt;
			&lt;aui:input inlineLabel="right" name="radio buttons" type="radio" value="3" label="Radio 3" /&gt;
		&lt;/aui:field-wrapper&gt;

	&lt;/div&gt;

		&lt;aui:button name="submitButton" type="button" value="submit" first="true" /&gt;
		&lt;aui:button name="resetButton" type="button" value="reset" /&gt;
		&lt;aui:button name="cancelButton" type="button" value="cancel" last="true" /&gt;

&lt;/aui:form&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Form Example 1 Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:fieldset column="true" title="Form Example 2" class="aui-field-labels-inline"&gt;

	&lt;aui:input alignLabel="right" name="username" value="" label="Username" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;The username you wish to use.&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:input alignLabel="right" name="email" value="" label="Email" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;The email address to associate with this account.&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:select alignLabel="right" name="accountType" label="Account Type"&gt;
		&lt;aui:option name="premium" label="Premium User" /&gt;
		&lt;aui:option name="midlevel" label="Mid-level User" /&gt;
		&lt;aui:option name="regular" label="Regular User" /&gt;
	&lt;/aui:select&gt;
	&lt;p class="aui-form-hint"&gt;
		&lt;span&gt;What level of user would you like to be?&lt;/span&gt;
	&lt;/p&gt;

	&lt;aui:input alignLabel="right" name="interests" type="textarea" value="" label="Interests" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;What are some of your interests?&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:field-wrapper name="userOptions" label="User Options"&gt;
		&lt;aui:input alignLabel="right" type="checkbox" name="notifyMessages" value="1" label="Do you wish to be notified by email when another user sends you a message?" /&gt;
		&lt;aui:input alignLabel="right" type="checkbox" name="displayEmail" value="1" label="Do you wish for other users to be able to see your email address?" /&gt;
		&lt;aui:input alignLabel="right" type="checkbox" name="subscribeNewsletter" value="1" label="Would you like to receive our monthly newsletter?" /&gt;
	&lt;/aui:field-wrapper&gt;
	&lt;p class="aui-form-hint"&gt;
		We will not sell your information, or needlessly spam you. This is just to keep you in the loop.
	&lt;/p&gt;

	&lt;div class="aui-button-holder"&gt;
		&lt;aui:button name="submitButton" type="button" value="submit" first="true" /&gt;
		&lt;aui:button name="resetButton" type="button" value="reset" last="true" /&gt;
	&lt;/div&gt;
&lt;/aui:fieldset&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Form Example 2 Code" class="textarea" />

		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:buffer var="codeContent">
&lt;aui:fieldset column="true" title="Form Example 3" class="aui-field-labels-inline"&gt;

	&lt;aui:input alignLabel="right" name="fullName" value="" label="Full Name" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;Your full name.&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:input alignLabel="right" name="cityState" value="" label="City, State" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;What city and state do you live in?&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:select alignLabel="right" name="ageGroup" label="Your Age Group"&gt;
		&lt;aui:option name="teen" label="13 - 19" selected="true" /&gt;
		&lt;aui:option name="young-adult" label="20 - 30" /&gt;
		&lt;aui:option name="adult" label="31 - 50" /&gt;
		&lt;aui:option name="vintage-adult" label="51+" /&gt;
	&lt;/aui:select&gt;
	&lt;p class="aui-form-hint"&gt;
		&lt;span&gt;The age group that best describes you.&lt;/span&gt;
	&lt;/p&gt;

	&lt;aui:input alignLabel="right" name="description" type="textarea" value="" label="Description" /&gt;
		&lt;p class="aui-form-hint"&gt;
			&lt;span&gt;Please describe yourself.&lt;/span&gt;
		&lt;/p&gt;

	&lt;aui:field-wrapper name="radio buttons"&gt;
		&lt;aui:input inlineLabel="right" name="usageLevel" type="radio" value="1" label="All the time. I'm already addicted just by signing up." /&gt;
		&lt;aui:input inlineLabel="right" name="usageLevel" type="radio" value="2" label="Sometimes, but maybe more. Depends how good it is." /&gt;
		&lt;aui:input inlineLabel="right" name="usageLevel" type="radio" value="3" label="Probably not that often, I'm just checking it out." /&gt;
	&lt;/aui:field-wrapper&gt;
		&lt;p class="aui-form-hint"&gt;
			This helps us guage your interest in us.
		&lt;/p&gt;

	&lt;div class="aui-button-holder"&gt;
		&lt;aui:button name="submitButton" type="button" value="submit" first="true" /&gt;
		&lt;aui:button name="resetButton" type="button" value="reset" last="true" /&gt;
	&lt;/div&gt;
&lt;/aui:fieldset&gt;
		</liferay-util:buffer>

		<aui:input type="textarea" value="<%= HtmlUtil.unescape(codeContent) %>" name="code" label="Form Example 3 Code" class="textarea" />

		</liferay-ui:section>
	</div>
</div>
</liferay-ui:tabs>

<script type="text/javascript">
	AUI().ready( 'aui-tabs', 'substitute', function(A) {
		var tabs = A.all('.markupTabs');
		tabs.each( function(item){
			var tab = new A.TabView(
				{
					boundingBox: item,
					listNode: item.one('.tab-list'),
					contentNode: item.one('.tab-content')
				}
			).render();
		});
	});
</script>

<script src="../../build/aui/aui.js" type="text/javascript"></script>
<script type="text/javascript">

AUI().ready('aui-tooltip', 'aui-io-plugin', function(A) {

	var t1 = new A.Tooltip({
		trigger: '.t1',
		align: { points: [ 'bc', 'tc' ] },
		title: true
	})
	.render();


	var t2 = new A.Tooltip({
		trigger: '.t2',
		bodyContent: '<img src="http://placehold.it/182x154/98BF0D/1F1A16/&text=Image+Example" /><br/><div style="text-align: center;">Image Example</div>',
	})
	.render();

	var t3 = new A.Tooltip({
		trigger: '.t3',
		align: { points: [ 'lc', 'rc' ] },
		showArrow: false,
		bodyContent: 'Simple tooltip without arrow! No animation.'
	})
	.render();

	var t4 = new A.Tooltip({
		trigger: '.t4',
		bodyContent: '<object width="560" height="340"><param name="movie" value="http://www.youtube.com/v/PiSxJwB29R8&hl=en&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/PiSxJwB29R8&hl=en&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="560" height="340"></embed></object>',
	})
	.render();
});

</script>

<script type="text/javascript">

AUI().ready('aui-image-viewer-base', function(A) {

	var imageViewer1 = new A.ImageViewer({
		links: '#gallery1 a',
		captionFromTitle: true,
		preloadAllImages: true,
		showInfo: true,
		after: {
			anim: function(event) {
			},
			request: function(event) {
			},
			load: function(event) {
			}
		}
	})
	.render();
});

</script>

<script type="text/javascript" charset="utf-8">

AUI().ready('aui-carousel', function(A) {

	var component = new A.Carousel(
		{
			intervalTime: 1,
			contentBox: '#demo',
			activeIndex: 0,
			height: 260,
			width: 940
		}
	).render();
});

</script>

<script type="text/javascript">

AUI().ready('aui-image-viewer-gallery', 'aui-media-viewer-plugin', function(A) {

	var mediaGallery = new A.ImageGallery({
		caption: 'AlloyUI - MediaGallery mixed demo',
		delay: 3000,
		links: '#gallery a',
		plugins: [
			{
				cfg: {
					'providers.dailymotion': {
						container: '<iframe frameborder="0" width="{width}" height="{height}" src="http://www.dailymotion.com/embed/video/{media}"></iframe>',
						matcher: new RegExp(
							A.Lang.sub(
								A.MediaViewerPlugin.REGEX_DOMAIN,
								{
									domain: 'dailymotion.com'
								}
							),
							'i'
						),
						options: A.MediaViewerPlugin.DEFAULT_OPTIONS,
						mediaRegex: /\/video\/([^&#_]*)/i
					}
				},
				fn: A.MediaViewerPlugin
			}
		],
		preloadNeighborImages: true
	});

	mediaGallery.render();
});

</script>
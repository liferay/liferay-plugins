<div class="columns-1-3-2" id="main-content" role="main">
	#if ($browserSniffer.isIe($request) && $browserSniffer.getMajorVersion($request) < 8)
		<table class="portlet-layout">
		<tr>
			<td class="aui-w100 portlet-column portlet-column-only">
				$processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
			</td>
		</tr>
		</table>

		<table class="portlet-layout">
		<tr>
			<td class="aui-w33 portlet-column portlet-column-first">
				$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
			</td>
			<td class="aui-w33 portlet-column">
				$processor.processColumn("column-3", "portlet-column-content")
			</td>
			<td class="aui-w33 portlet-column portlet-column-last">
				$processor.processColumn("column-4", "portlet-column-content portlet-column-content-last")
			</td>
		</tr>
		</table>

		<table class="portlet-layout">
		<tr>
			<td class="aui-w66 portlet-column">
				<table class="portlet-layout">
				<tr>
					<td class="aui-w100 portlet-column portlet-column-first">
						$processor.processColumn("column-5", "portlet-column-content portlet-column-content-first")
					</td>
				</tr>
				</table>

				<table class="portlet-layout">
				<tr>
					<td class="aui-w50 portlet-column portlet-column-first">
						$processor.processColumn("column-6", "portlet-column-content portlet-column-content-first")
					</td>
					<td class="aui-w50 portlet-column">
						$processor.processColumn("column-7", "portlet-column-content")
					</td>
				</tr>
				</table>
			</td>
			<td class="aui-w33 portlet-column portlet-column-last">
				$processor.processColumn("column-8", "portlet-column-content portlet-column-content-last")
			</td>
		</tr>
		</table>
	#else
		<div class="portlet-layout">
			<div class="aui-w100 portlet-column portlet-column-only">
				$processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
			</div>
		</div>

		<div class="portlet-layout">
			<div class="aui-w33 portlet-column portlet-column-first">
				$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
			</div>

			<div class="aui-w33 portlet-column">
				$processor.processColumn("column-3", "portlet-column-content")
			</div>

			<div class="aui-w33 portlet-column portlet-column-last">
				$processor.processColumn("column-4", "portlet-column-content portlet-column-content-last")
			</div>
		</div>

		<div class="portlet-layout">
			<div class="aui-w66 portlet-column">
				<div class="portlet-layout">
					<div class="aui-w100 portlet-column portlet-column-first">
						$processor.processColumn("column-5", "portlet-column-content portlet-column-content-first")
					</div>
				</div>

				<div class="portlet-layout">
					<div class="aui-w50 portlet-column portlet-column-first">
						$processor.processColumn("column-6", "portlet-column-content portlet-column-content-first")
					</div>

					<div class="aui-w50 portlet-column">
						$processor.processColumn("column-7", "portlet-column-content")
					</div>
				</div>
			</div>

			<div class="aui-w33 portlet-column portlet-column-last">
				$processor.processColumn("column-8", "portlet-column-content portlet-column-content-last")
			</div>
		</div>
	#end
</div>
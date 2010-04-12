<div class="home" id="main-content" role="main">
	<div class="portlet-layout">
		<div class="portlet-column portlet-column-only aui-w100">
			$processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
		</div>
	</div>

	<div class="portlet-layout">
		<div class="portlet-column portlet-column-first aui-w33">
			$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
		</div>
		<div class="portlet-column aui-w33">
			$processor.processColumn("column-3", "portlet-column-content")
		</div>
		<div class="portlet-column portlet-column-last aui-w33">
			$processor.processColumn("column-4", "portlet-column-content portlet-column-content-last")
		</div>
	</div>

	<div class="portlet-layout">
		<div class="portlet-column aui-w66">
			<div class="portlet-layout">
				<div class="portlet-column portlet-column-first aui-w100">
					$processor.processColumn("column-5", "portlet-column-content portlet-column-content-first")
				</div>
			</div>
			<div class="portlet-layout">
				<div class="portlet-column portlet-column-first aui-w50">
					$processor.processColumn("column-6", "portlet-column-content portlet-column-content-first")
				</div>
				<div class="portlet-column aui-w50">
					$processor.processColumn("column-7", "portlet-column-content")
				</div>
			</div>
		</div>
		<div class="portlet-column portlet-column-last aui-w33">
			$processor.processColumn("column-8", "portlet-column-content portlet-column-content-last")
		</div>
	</div>
</div>
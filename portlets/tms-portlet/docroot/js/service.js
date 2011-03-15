Liferay.Service.register("Liferay.Service.TMS", "com.liferay.tms.service", "tms-portlet");

Liferay.Service.registerClass(
	Liferay.Service.TMS, "TasksEntry",
	{
		addTasksEntry: true,
		getTasksEntry: true,
		updateTasksEntry: true
	}
);
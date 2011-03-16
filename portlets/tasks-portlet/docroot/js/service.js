Liferay.Service.register("Liferay.Service.TMS", "com.liferay.tasks.service", "tasks-portlet");

Liferay.Service.registerClass(
	Liferay.Service.TMS, "TasksEntry",
	{
		addTasksEntry: true,
		getTasksEntry: true,
		updateTasksEntry: true
	}
);
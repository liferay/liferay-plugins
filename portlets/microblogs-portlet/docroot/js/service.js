Liferay.Service.register("Liferay.Service.Microblogs", "com.liferay.microblogs.service", "microblogs-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Microblogs, "MicroblogsEntry",
	{
		addMicroblogsEntry: true,
		getMicroblogsEntriesCount: true,
		deleteMicroblogsEntry: true,
		getMicroblogsEntries: true,
		getMicroblogsEntry: true,
		updateMicroblogsEntry: true
	}
);
Liferay.Service.register("Liferay.Service.Microblogs", "com.liferay.microblogs.service", "microblogs-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Microblogs, "MicroblogsEntry",
	{
		addMicroblogsEntry: true,
		deleteMicroblogsEntry: true,
		getMicroblogsEntries: true,
		getMicroblogsEntriesCount: true,
		getMicroblogsEntry: true,
		getUserMicroblogsEntries: true,
		getUserMicroblogsEntriesCount: true,
		updateMicroblogsEntry: true
	}
);
Liferay.Service.register("Liferay.Service.Contacts", "com.liferay.contacts.service", "contacts-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Contacts, "Entry",
	{
		addEntry: true,
		deleteEntry: true,
		updateEntry: true
	}
);
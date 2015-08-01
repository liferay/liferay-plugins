create table SampleLARBooking (
	uuid_ VARCHAR(75) null,
	sampleLARBookingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bookingNumber VARCHAR(75) null,
	lastPublishDate DATE null
);
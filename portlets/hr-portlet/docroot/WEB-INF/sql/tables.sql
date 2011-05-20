create table HRAsset (
	hrAssetId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrAssetDefinitionId LONG,
	hrAssetTypeId LONG,
	serialNumber VARCHAR(75) null,
	inactiveDate DATE null
);

create table HRAssetCheckout (
	hrAssetCheckoutId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrAssetId LONG,
	hrUserId LONG,
	checkoutDate DATE null,
	expectedCheckInDate DATE null,
	actualCheckInDate DATE null
);

create table HRAssetDefinition (
	hrAssetDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrAssetProductId VARCHAR(75) null,
	hrAssetTypeId LONG,
	hrAssetVendorId LONG,
	definitionNumber VARCHAR(75) null,
	orderId DATE null,
	orderDate DATE null,
	quantity INTEGER,
	individualPrice DOUBLE
);

create table HRAssetProduct (
	hrAssetProductId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrAssetVendorId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRAssetType (
	hrAssetTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRAssetVendor (
	hrAssetVendorId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRBillability (
	hrBillabilityId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRBranch (
	hrBranchId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	organizationId LONG
);

create table HRBranches_HRJobTitles (
	hrBranchId LONG not null,
	hrJobTitleId LONG not null,
	primary key (hrBranchId, hrJobTitleId)
);

create table HRClient (
	hrClientId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HREmploymentType (
	hrEmploymentTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRExpense (
	hrExpenseId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrExpenseAccountId LONG,
	hrExpenseTypeId LONG,
	hrUserId LONG,
	expenseDate DATE null,
	expenseAmount DOUBLE,
	expenseHRExpenseCurrencyId LONG,
	reimbursementAmount DOUBLE,
	reimbursementHRExpenseCurrencyId LONG,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table HRExpenseAccount (
	hrExpenseAccountId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRExpenseCurrency (
	hrExpenseCurrencyId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRExpenseCurrencyConversion (
	hrExpenseCurrencyConversionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fromHRExpenseCurrencyId LONG,
	toHRExpenseCurrencyId LONG,
	conversionDate DATE null,
	conversionValue DOUBLE
);

create table HRExpenseType (
	hrExpenseTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRHoliday (
	hrHolidayId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	dayOfYear INTEGER,
	year INTEGER,
	paid BOOLEAN
);

create table HRHolidays_HROffices (
	hrHolidayId LONG not null,
	hrOfficeId LONG not null,
	primary key (hrHolidayId, hrOfficeId)
);

create table HRJobTitle (
	hrJobTitleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HROffice (
	hrOfficeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	organizationId LONG
);

create table HRProject (
	hrProjectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrClientId LONG,
	hrProjectStatusId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	estimatedStartDate DATE null,
	estimatedEndDate DATE null,
	estimatedHours DOUBLE,
	estimatedHoursCost DOUBLE,
	estimatedHoursCostCurrencyCode VARCHAR(75) null,
	estimatedExpenses DOUBLE,
	estimatedExpensesCurrencyCode VARCHAR(75) null,
	actualStartDate DATE null,
	actualEndDate DATE null,
	actualHours DOUBLE,
	actualHoursCost DOUBLE,
	actualHoursCostCurrencyCode VARCHAR(75) null,
	actualExpenses DOUBLE,
	actualExpensesCurrencyCode DOUBLE
);

create table HRProjectBillingRate (
	hrProjectBillingRateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrProjectId LONG,
	hrProjectRoleId LONG,
	rate DOUBLE
);

create table HRProjectRole (
	hrProjectRoleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRProjectStatus (
	hrProjectStatusId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRTask (
	hrTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrBillabilityId LONG,
	hrProjectId LONG,
	hrTaskStatusId LONG,
	parentHRTaskId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	estimatedStartDate DATE null,
	estimatedEndDate DATE null,
	estimatedHours DOUBLE,
	estimatedHoursCost DOUBLE,
	estimatedHoursCostCurrencyCode VARCHAR(75) null,
	estimatedExpenses DOUBLE,
	estimatedExpensesCurrencyCode VARCHAR(75) null,
	actualStartDate DATE null,
	actualEndDate DATE null,
	actualHours DOUBLE,
	actualHoursCost DOUBLE,
	actualHoursCostCurrencyCode VARCHAR(75) null,
	actualExpenses DOUBLE,
	actualExpensesCurrencyCode VARCHAR(75) null
);

create table HRTaskStatus (
	hrTaskStatusId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRTerminationType (
	hrTerminationTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRTimeOff (
	hrTimeOffId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrTimeOffTypeId LONG,
	hrTimeSheetId LONG,
	hrUserId LONG,
	startDayOfYear INTEGER,
	endDayOfYear INTEGER,
	year INTEGER,
	totalHours DOUBLE,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table HRTimeOffFrequencyType (
	hrTimeOffFrequencyTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRTimeOffPolicy (
	hrTimeOffPolicyId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrTimeOffTypeId LONG,
	hrUserId LONG,
	accrueHRTimeOffFrequencyTypeId LONG,
	resetHRTimeOffFrequencyTypeId LONG,
	effectiveDate DATE null,
	inactive BOOLEAN,
	hoursAllowed DOUBLE,
	hoursBaseAmount DOUBLE,
	hoursToAccrue DOUBLE,
	carryOverHoursAllowed DOUBLE
);

create table HRTimeOffType (
	hrTimeOffTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table HRTimeSheet (
	hrTimeSheetId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrUserId LONG,
	startDayOfYear INTEGER,
	endDayOfYear INTEGER,
	year INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table HRTimeSheetDay (
	hrTimeSheetDayId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrTimeSheetId LONG,
	hrUserId LONG,
	dayOfYear INTEGER,
	year INTEGER,
	hours DOUBLE
);

create table HRTimeSheetHoursPerDay (
	hrTimeSheetHoursPerDayId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrOfficeId LONG,
	hoursPerDay DOUBLE
);

create table HRUser (
	hrUserId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrEmploymentTypeId LONG,
	hrJobTitleId LONG,
	hrOfficeId LONG,
	hrTerminationTypeId LONG,
	hrWageTypeId LONG,
	hireDate DATE null,
	terminationDate DATE null,
	wageAmount DOUBLE,
	wageCurrencyCode VARCHAR(75) null,
	benefitsExempt BOOLEAN,
	overtimeExempt BOOLEAN
);

create table HRUserHistory (
	hrUserHistoryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	hrEmploymentTypeId LONG,
	hrJobTitleId LONG,
	hrOfficeId LONG,
	hrTerminationTypeId LONG,
	hrWageTypeId LONG,
	hireDate DATE null,
	terminationDate DATE null,
	wageAmount DOUBLE,
	wageCurrencyCode VARCHAR(75) null,
	benefitsExempt BOOLEAN,
	overtimeExempt BOOLEAN
);

create table HRUserProject (
	hrUserProjectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrProjectBillingRateId LONG,
	hrProjectId LONG,
	hrProjectRoleId LONG,
	hrUserId LONG,
	actualRate DOUBLE
);

create table HRUserTask (
	hrUserTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrBillabilityId LONG,
	hrTaskId LONG,
	hrTimesheetId LONG,
	hrUserId LONG
);

create table HRUserTimeOff (
	hrUserTimeOffId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	hrTimeOffTypeId LONG,
	hrUserId LONG,
	year INTEGER,
	hoursAllowed DOUBLE,
	hoursAccrued DOUBLE,
	hoursCarriedOver DOUBLE,
	hoursUsed DOUBLE
);

create table HRWageType (
	hrWageTypeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	code_ VARCHAR(75) null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);
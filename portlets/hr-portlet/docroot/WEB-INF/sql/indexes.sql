create unique index IX_612555B7 on HRBillability (groupId, code_);

create index IX_8905C6CA on HRBranches_HRJobTitles (hrBranchId);
create index IX_E8CD2E83 on HRBranches_HRJobTitles (hrJobTitleId);

create unique index IX_952E2B0E on HREmploymentType (groupId, code_);

create unique index IX_112B7BE4 on HRExpenseAccount (groupId, name);

create unique index IX_1C0A2D96 on HRExpenseCurrencyConversion (groupId, fromHRExpenseCurrencyId, toHRExpenseCurrencyId, conversionDate);

create unique index IX_EDA6191B on HRExpenseType (groupId, name);

create index IX_88F699E0 on HRHolidays_HROffices (hrHolidayId);
create index IX_136E532E on HRHolidays_HROffices (hrOfficeId);

create unique index IX_C83A335F on HRProjectStatus (groupId, code_);

create unique index IX_51A0D65F on HRTaskStatus (groupId, code_);

create unique index IX_DDCF4712 on HRTerminationType (groupId, code_);

create unique index IX_7B1A893C on HRTimeOffFrequencyType (groupId, code_);

create unique index IX_DE33EACA on HRWageType (groupId, code_);
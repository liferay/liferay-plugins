numStr = preferences.getValue("number", "0");

int num = numStr.toInteger() + 1;

preferences.setValue("number", num.toString());

preferences.store();
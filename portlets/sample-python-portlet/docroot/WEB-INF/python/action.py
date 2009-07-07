numStr = preferences.getValue("number", "0");

num = int(numStr) + 1;

preferences.setValue("number", str(num));

preferences.store();
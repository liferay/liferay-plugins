numStr = preferences.getValue("number", "0");

num = (+numStr) + 1;

preferences.setValue("number", num);

preferences.store();
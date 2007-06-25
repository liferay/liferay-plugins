numStr = $preferences.getValue("number", "0")

num = numStr.to_i + 1

$preferences.setValue("number", num.to_s)

$preferences.store
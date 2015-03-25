#!/bin/sh

#set -x

if [ $# -lt 2 ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your portlet id. A new directory will be created based
	echo on the portlet id.
	echo
	echo The second \"Hello World\" is the portlet\'s display name. The quotation marks are
	echo only needed because there is a space in the display name.
	echo
	echo A third value can be passed to specify the portlet framework to use. Valid
	echo values are \"jsf\", \"icefaces\", \"liferay_faces_alloy\", \"mvc\", \"primefaces\",
	echo \"richfaces\", \"spring_mvc\", or \"vaadin\". The default value is \"mvc\". The
	echo quotation marks are not needed.

	exit 127
fi

chmod a+x ../tools/gradle/gradlew

../tools/gradle/gradlew --build-file=../sdk.gradle -PportletName=$1 -PportletDisplayName=\"$2\" -PportletFramework=$3 createPortlet

#ant deploy

exit 0
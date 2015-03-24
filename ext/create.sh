#!/bin/sh

#set -x

if [ $# -lt 2 ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your extension environment name. A new directory will
	echo be created based on the extension environment name.
	echo
	echo The second "Hello World" is the extension environment\'s display name. The
	echo quotation marks are only needed because there is a space in the display name.

	exit 127
fi

chmod a+x ../tools/gradle/gradlew

../tools/gradle/gradlew --build-file=../sdk.gradle -PextName=$1 -PextDisplayName=\"$2\" createExt

#ant deploy

exit 0
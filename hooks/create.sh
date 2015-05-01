#!/bin/sh

#set -x

if [ $# -lt 2 ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your hook id. A new directory will be created based on
	echo the hook id.
	echo
	echo The second \"Hello World\" is the hook\'s display name. The quotation marks are
	echo only needed because there is a space in the display name.

	exit 127
fi

chmod a+x ../tools/gradle/gradlew

../tools/gradle/gradlew --build-file=../sdk.gradle -PhookName=$1 -PhookDisplayName="$2" createHook

#ant deploy

exit 0
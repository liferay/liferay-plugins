#!/bin/sh

#set -x

if [ $# -lt 2 ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your layouttpl id. A new directory will be created
	echo based on the layouttpl id.
	echo
	echo The second \"Hello World\" is the layouttpl\'s display name. The quotation marks
	echo are only needed because there is a space in the display name.

	exit 127
fi

chmod a+x ../tools/gradle/gradlew

../tools/gradle/gradlew --build-file=../sdk.gradle -PlayouttplName=$1 -PlayouttplDisplayName=\"$2\" createLayouttpl

cd $1-layouttpl

#ant deploy

exit 0
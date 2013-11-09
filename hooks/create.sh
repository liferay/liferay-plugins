#!/bin/sh

#set -x

if [ -z "$1" ]; then
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

if [ -z "$3" ]; then
    ant -Dhook.name=$1 -Dhook.display.name=\"$2\" create
else
    ant -Dhook.name=$1 -Dhook.display.name=\"$2\" -Dhook.lang=\"$3\" create
fi

#ant deploy

exit 0
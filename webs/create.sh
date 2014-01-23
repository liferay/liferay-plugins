#!/bin/sh

#set -x

if [ $# -lt 2 ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your web id. A new directory will be created based on
	echo the web id.
	echo
	echo The second \"Hello World\" is the web\'s display name. The quotation marks are only
	echo needed because there is a space in the display name.

	exit 127
fi

ant -Dweb.name=$1 -Dweb.display.name=\"$2\" create

#ant deploy

exit 0
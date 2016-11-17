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

ant -Dext.name=$1 -Dext.display.name=\"$2\" create

#ant deploy

exit 0
#!/bin/sh

#set -x

if [ -z "$1" ]; then
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
	echo values are "mvc", "jsf", or "vaadin". The default value is "mvc". The quotation
	echo marks are not needed.

	exit 127
fi

ant -Dportlet.name=$1 -Dportlet.display.name=\"$2\" -Dportlet.framework=$3 create

#ant deploy

exit 0
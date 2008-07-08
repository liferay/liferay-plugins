#!/bin/sh

#set -x

if [ -z "$1" ]; then
	echo
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo
	echo The first hello-world is your layouttpl id. A new directory will be created based
	echo on the layouttpl id.
	echo
	echo The second \"Hello World\" is the layouttpl\'s display name. The quotation marks are
	echo only needed because there is a space in the display name.

	exit 127
fi

ant -Dlayouttpl.name=$1 -Dlayouttpl.display.name=\"$2\" create

svn add $1-layouttpl

cd $1-layouttpl/docroot

svn ps svn:ignore -F .cvsignore .

svn remove --force .cvsignore

cd WEB-INF

svn ps svn:ignore -F .cvsignore .

svn remove --force .cvsignore

cd ..

cd ..

ant deploy

exit 0
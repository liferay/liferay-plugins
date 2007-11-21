#! /bin/sh

# Uncomment the next line for testing
#set -x

# First determine the number of arguments is correct
if ["$1" = ""]; then
	echo Usage: ./create.sh hello-world \"Hello World\"
	echo 
	echo The first hello-world is your portlet id. A new directory will be created based
	echo on the portlet id. 
	echo
	echo The second \"Hello World\" is the portlet\'s display name. The quotation marks are
	echo only needed because there is a space in the display name. 
	exit 127
fi

ant -Dportlet.name=$1 -Dportlet.display.name=\"$2\" create

svn add $1-portlet
cd $1-portlet/docroot
svn ps svn:ignore -F .cvsignore .
svn remove --force .cvsignore
cd WEB-INF
svn ps svn:ignore -F .cvsignore .
svn remove --force .cvsignore
cd ..
cd ..
ant deploy

# All done, exit ok
exit 0

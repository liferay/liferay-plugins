# Liferay Plugins

The liferay-plugins repository is part of the Liferay Portal project. [Liferay
Portal](http://www.liferay.com/community/liferay-projects/liferay-portal) is an
open source enterprise web platform for building business solutions that deliver
immediate results and long-term value. Liferay Portal started out as a personal
development project in 2000 and was open sourced in 2001.

To get started, check out the project's community homepage at
[http://liferay.org](http://liferay.org)!

Most of the plugins found in the liferay-plugins repository can be easily
installed using [Liferay Marketplace](http://liferay.com/marketplace). If you
wish to build one or more of the plugins yourself, read below for details.

## Source Code

Liferay's main source code resides in two repositories:
[liferay-portal](https://github.com/liferay/liferay-portal) and
[liferay-plugins](https://github.com/liferay/liferay-plugins). Liferay has
additional repositories for the following:

* [AlloyUI](https://github.com/liferay/alloy-ui)
* [Documentation](https://github.com/liferay/liferay-docs)
* [Git Tools](https://github.com/liferay/git-tools)
* [Liferay Faces](https://github.com/liferay/liferay-faces)
* [Liferay IDE](https://github.com/liferay/liferay-ide)
* [Maven Support](https://github.com/liferay/liferay-maven-support)

Liferay Portal *releases* are built from the liferay-portal repository and
include select plugins from the liferay-plugins repository. You can build
Liferay Portal, its plugins, and/or any of the other supporting technologies
from their respective repositories.

For more information on building liferay-portal, visit the
[README](https://github.com/liferay/liferay-portal/blob/master/README.markdown)
from the liferay-portal repository.

## Quick Start

In the liferay-plugins repository, plugins are laid out in the form of a Liferay
Plugins SDK. Read [The Plugins
SDK](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/the-plugins-s-3)
chapter of Liferay's Development Guide to learn more about working with the
Liferay Plugins SDK.

Once you fork this repository, to quickly build and deploy one or more plugins,
create a `build.${username}.properties` file in the root directory of your
clone. Make sure to replace `${username}` with your login name, which on Windows
can be determined through the `%USERNAME%` environment variable, and on Unix by
using the `whoami` command). Within this file, override any necessary settings
from the base `build.properties` file (do not edit the base file). If you are
using Tomcat, for example, all you need to do is override the `app.server.dir`
setting to point to an existing copy of a deployed instance of Liferay bundled
with Tomcat. For example, if your user name is *joe*, then create a
`build.joe.properties` text file, with a single line:

    app.server.dir=/Users/joe/liferay-portal-6.1.1-ga2/tomcat-7.0.27

Then, visit any of the directories containing your desired plugin, and use the
`ant deploy` command to build and deploy the plugin to your configured Liferay
instance.

For example, to build the sample JSP portlet, use the following commands on Unix
(similar to Windows):

    cd portlets/sample-jsp-portlet
    ant deploy

This will compile the plugin, build a WAR file in the `dist` directory, and copy
the resulting WAR file to Liferay's *Hot Deploy* directory.

There are many other options for developing new Liferay plugins using the
Plugins SDK. Consult the [Liferay Development
Guide](http://www.liferay.com/documentation/liferay-portal/6.1/development) for
indispensable explanations, examples, and reference material on the Liferay
Plugins SDK and surrounding technologies.

Also, check out Liferay IDE. The [Liferay IDE
project](http://www.liferay.com/community/liferay-projects/liferay-ide) provides
an Eclipse-based Liferay development environment to help you build and maintain
Liferay projects easily.

Finally, consider using Maven to build Liferay Plugins. For excellent overviews
of Maven support for Liferay, check out [Mika Koivisto's
presentation](http://www.slideshare.net/koivimik/developing-liferay-plugins-with-maven)
and [Getting Started with Liferay Maven
SDK](http://www.liferay.com/web/mika.koivisto/blog/-/blogs/12322618).

## Contributing

Liferay welcomes any and all contributions! If you have an idea for a new plugin
or a new feature in an existing plugin, and wish to implement it, follow the
contribution steps outlined in the
[CONTRIBUTING](https://github.com/liferay/liferay-portal/blob/master/CONTRIBUTING.markdown)
guide. This guide contains details on how to contribute to Liferay and
additional useful resource links.

## More Information

For more information about filing bugs, keeping up with Liferay on social media,
and other ways to participate, check out the [Liferay Community
Homepage](http://liferay.org) and read the
[README](https://github.com/liferay/liferay-portal/blob/master/README.markdown)
file located in the liferay-portal repository. 

## Liferay Portal Community Edition License

This library, *Liferay Portal Community Edition*, is free software ("Licensed
Software"); you can redistribute it and/or modify it under the terms of the [GNU
Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) as
published by the Free Software Foundation; either version 2.1 of the License, or
(at your option) any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; including but not limited to, the implied warranty of MERCHANTABILITY,
NONINFRINGEMENT, or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
Public License for more details.

You should have received a copy of the [GNU Lesser General Public
License](http://www.gnu.org/licenses/lgpl-2.1.html) along with this library; if
not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
Floor, Boston, MA 02110-1301 USA


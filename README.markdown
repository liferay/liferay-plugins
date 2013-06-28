# Liferay Plugins

The liferay-plugins repository is part of the Liferay Portal project. [Liferay
Portal](http://www.liferay.com/community/liferay-projects/liferay-portal) is an
open source enterprise web platform for building business solutions that deliver
immediate results and long-term value. Liferay Portal started out as a personal
development project in 2000 and was open sourced in 2001.

To get started, check out the project's community homepage at
[http://liferay.org](http://liferay.org)!

Most of the plugins found in the liferay-plugins repository can be easily
installed on Liferay Portal via [Liferay
Marketplace](http://liferay.com/marketplace). To build one or more of the
plugins yourself, read below for details.

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

For more information on building liferay-portal, see the [README
file](https://github.com/liferay/liferay-portal/blob/master/README.markdown) in
the liferay-portal repository.

## Quick Start

In the [liferay-plugins](https://github.com/liferay/liferay-plugins) repository,
plugins are laid out in a software development kit (SDK) -- the Liferay Plugins
SDK. All Liferay plugin types, including portlets, themes, layout templates,
hooks, and EXT plugins, can be created and maintained in the SDK. [The Plugins
SDK](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/the-plugins-s-3)
chapter of Liferay's [Development
Guide](http://www.liferay.com/documentation/liferay-portal/6.1/development)
explains how to create, build, and deploy your plugins. Follow the instructions
in this section to build and deploy any of the existing SDK plugins quickly.

For demonstration purposes, let's pretend your user name is *joe* and you have a
Liferay instance bundled with Apache Tomcat running in your `/home/joe/`
directory.

1. Fork the [liferay-plugins](https://github.com/liferay/liferay-plugins)
repository.

2. Clone your fork of the repository.
3. Create a `build.${username}.properties` file in the root directory of your
liferay-plugins repository clone. Be sure to replace `${username}` with your
user name.

		/home/joe/liferay-plugins/build.joe.properties

	Note, to determine your user name, execute `echo %USERNAME%` on Windows or
	`whoami` on Unix/Linux.

4. In your `build.${username}.properties` file, specify the
`app.server.parent.dir` property set to the parent path of your app server.

    	app.server.parent.dir=/home/joe/liferay-portal-6.1.1-ga2

	Use your `build.${username}.properties` file to specify any additional
	properties you wish to override from the base `build.properties` file; do
	not modify the base file.

5. Navigate to the directory of a plugin (e.g. *Sample JSP Portlet*) and deploy
it using Ant.

		cd /home/joe/liferay-plugins/portlets/sample-jsp-portlet
		ant deploy

	The plugin compiles, its WAR file is built to the plugin's `dist` directory,
	the WAR file is copied to your Liferay *Hot Deploy* directory, and the
	plugin is deployed immediately. It's just that easy!

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
contribution steps outlined in the [CONTRIBUTING
guide](https://github.com/liferay/liferay-portal/blob/master/CONTRIBUTING.markdown).
It explains how to contribute to Liferay and contains links to additional useful
resources.

## More Information

For more information about filing bugs, staying updated with Liferay on social
media, and other ways to participate, check out the [Liferay Community
Homepage](http://liferay.org) and consult the [README
file](https://github.com/liferay/liferay-portal/blob/master/README.markdown) in
the liferay-portal repository.

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


Liferay Plugins
=================

This repository is part of the Liferay Portal project. Liferay Portal is an open source enterprise web platform for building business solutions that deliver immediate results and long-term value. It started out as a personal development project in 2000 and was open sourced in 2001.

To get started, check out the project's community homepage at http://liferay.org!

Most of the plugins found in this repository here can be easily installed through the [Liferay Marketplace](http://liferay.com/marketplace).  If you wish to build one or more of the plugins yourself, read below for details.

Source Code
-----------
Liferay's main source code resides in two repositories: [liferay-portal](https://github.com/liferay/liferay-portal) and [liferay-plugins](https://github.com/liferay/liferay-plugins).  Additional repositories include:

* [Documentation](https://github.com/liferay/liferay-docs)
* [Liferay IDE](https://github.com/liferay/liferay-ide)
* [AlloyUI](https://github.com/liferay/alloy-ui)
* [Maven Support](https://github.com/liferay/liferay-maven-support)
* [Liferay Faces](https://github.com/liferay/liferay-faces)
* [Git Tools for Liferay](https://github.com/liferay/git-tools)

Liferay's releases are built from the `liferay-portal` repository, along with a select few plugins from the `liferay-plugins` repository.

To build Liferay yourself you must build at least the `liferay-portal` repository.  You can optionally build and install plugins from this repository (the `liferay-plugins` repository), or build any of the other supporting technologies by visiting any of the above repositories and reading the instructions.

For more information on building `liferay-portal`, visit the [README](https://github.com/liferay/liferay-portal/README.md) from the `liferay-portal` repository.

Quick start
-----------
In this repository, plugins are laid out in the form of a Liferay Plugins SDK.  Read the *Liferay Development Guide* included in the [Liferay Documentation](http://www.liferay.com/documentation) to learn more about working with the Liferay Plugins SDK.

Once you fork this repository, to quickly build and deploy one or more plugins, you should first create a `build.${username}.properties` file in the root directory of your clone (replace `${username}` with your login name, which on Windows can be determined through the `%USERNAME%` environment variable, and on unix by using the `whoami` command).  Within this file, override any necessary settings from the base `build.properties` file (do not edit the base file).  If you are using Tomcat, for example, all you need to do is override the `app.server.dir` setting to point to an existing copy of a deployed instance of Liferay+Tomcat.  For example, if your username is *joe*, then create a `build.joe.properties` text file, with a single line:

    app.server.dir=/Users/joe/liferay-portal-6.1.1-ga2/tomcat-7.0.27

Once you have done that, visit any of the directories containing your desired plugin, and use the `ant deploy` command to build and deploy the plugin to your configured Liferay instance.

For example, to build the sample JSP portlet, use these commands on unix (Windows is similar):

    cd portlets/sample-jsp-portlet
    ant deploy

This will compile the plugin, build a WAR file in the `dist` directory, and copy the resulting WAR file to Liferay's *Hot Deploy* directory.

There are many other options for developing new Liferay plugins using the Plugins SDK.  Consult the 
*Liferay Development Guide* in [Liferay's Official Documentation](http://liferay.com/documentation) for more details.

Also, check out the [Liferay IDE project](http://www.liferay.com/community/liferay-projects/liferay-ide).  This project provides an Eclipse-based Liferay development environment to help quickly build and maintain Liferay projects.

Finally, you can also use Maven to build Liferay Plugins.  For an excellent overview of Maven support, check out [Mika's presentation](http://www.slideshare.net/koivimik/developing-liferay-plugins-with-maven) and [Getting Started with Liferay Maven SDK](http://www.liferay.com/web/mika.koivisto/blog/-/blogs/12322618).

Contributing
------------

Liferay welcomes any and all contributions!  If you have an idea for a new plugin or a new feature in an existing plugin, and wish to implement it, follow the contribution steps outlined in the [CONTRIBUTING](https://github.com/liferay/liferay-portal/CONTRIBUTING.md) guide found in the `liferay-portal` repository.  This guide contains details on how to contribute to Liferay and additional useful resource links, and applies to this repository as well.

More Information
----------------

For more information about filing bugs, keeping up with Liferay on social media, and other ways to participate, check out the [Liferay Community Homepage](http://liferay.org) and read the [README](https://github.com/liferay/liferay-portal/README.md) in the `liferay-portal` repository.

Liferay Portal Community Edition License
----------------------------------------

This library, Liferay Portal Community Edition, is free software ("Licensed Software"); you can redistribute it and/or modify it under the terms of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; including but not limited to, the implied warranty of MERCHANTABILITY, NONINFRINGEMENT, or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

You should have received a copy of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA


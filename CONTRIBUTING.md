# How to contribute

Liferay is developed by its community, including Liferay users, enthusiasts, employees, customers, partners, and others.  Contributions can originate with any community member, and are highly encouraged.  There are several ways
in which you can contribute to Liferay's open source project, including bugfixes, new features, documentation improvements, translations, and enhancements to existing features.  

To maintain the quality and innovation Liferay has come to be known for, all changes will be code reviewed by a core set of Liferay project maintainers.  All community contributors are encouraged to get to know and work with the [core maintainer(s)](http://issues.liferay.com/browse/LPS#selectedTab=com.atlassian.jira.plugin.system.project%3Acomponents-panel)  for the area in which they are working.

If you have an idea for a new feature, and wish to implement it, follow the contribution steps outlined here.  This is a basic outline.  For more detail on specific steps, check out Liferay's extensive Wiki, including articles on [Understanding and Improving Liferay](http://www.liferay.com/community/wiki/-/wiki/tag/understanding+and+improving+liferay), and read the links listed below under *Additional Resources*.

## Getting Started

* Make sure you have a [JIRA Account](http://issues.liferay.com)
* Make sure you have a [GitHub account](https://github.com/signup/free)
* [Submit a ticket](http://issues.liferay.com) for your issue, following the [established JIRA process](http://www.liferay.com/community/wiki/-/wiki/Main/JIRA), assuming a ticket does not already exist.
  * Clearly describe the issue including steps to reproduce when it is a bug.
  * Make sure to choose an appropriate Category for the ticket
  * Make sure you fill in the earliest version that you know has the issue.
* Liferay's core source code resides in the [liferay-portal](https://github.com/liferay/liferay-portal) repository.  Source code for plugins resides in the [liferay-plugins](https://github.com/liferay/liferay-plugins) repository.  Fork the appropriate repository. 
* Read [Contributing to Liferay using Git and Github](http://www.liferay.com/community/wiki/-/wiki/Main/Contribute+using+Git+and+GitHub)

## Making Changes

* Create a branch from where you want to base your work.
  * This is usually the master branch.
* Make commits of logical units.
* Ensure your code follows the [Liferay Development Style](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style).  If you are using Liferay IDE, there is a built-in code formater accessible via *Java > Code Style > Formatter > Active Profile* menu.
* Include the ticket reference (e.g. LPS-XXXXX) in your commit messages. For example:

````
    LPS-83432 Make the example in CONTRIBUTING imperative and concrete
````

* TEST your changes thoroughly!  Liferay is used with a wide variety of operating systems, databases, application servers, and other related technologies.  Code that fixes a bug in one environment may break something in a different environment.  See [Unit and Integration Tests](http://www.liferay.com/community/wiki/-/wiki/Main/Unit+and+Integration+tests) for details on how to execute automated tests.

## Submitting Changes

* Push your changes to your branch in your fork of the appropriate Liferay repository.
* Submit a pull request to the component lead associated with the area in which the bug resides.
* On the LPS ticket, click the "Contribute Solution" button - this will require you to agree to the [Contributor License Agreement](http://www.liferay.com/legal/contributors-agreement).  You must also provide a pointer to the pull request on github.com.
* You're done!  Well, not quite... Be sure to watch the pull request and respond to any follow-up comments.

# Additional Resources

* [Getting Started as a Liferay Developer](http://www.liferay.com/community/wiki/-/wiki/Main/Getting+started+as+a+Liferay+Developer+in+a+few+steps)
* [Liferay and JIRA](http://www.liferay.com/community/wiki/-/wiki/Main/JIRA)
* [Contribute to Liferay on Github](http://www.liferay.com/community/wiki/-/wiki/Main/Contribute+using+Git+and+GitHub)
* [Liferay Core Development Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+Core+Development+Guidelines)
* [Setting up and using Liferay IDE](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+Contributor+Development+Environment+Setup)
* [Contributor License Agreement](http://www.liferay.com/legal/contributors-agreement)
* [General GitHub documentation](http://help.github.com/)
* [GitHub pull request documentation](http://help.github.com/send-pull-requests/)
* #liferay IRC channel on freenode.org

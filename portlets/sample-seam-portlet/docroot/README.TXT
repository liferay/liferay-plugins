README: sample-seam-portlet 6/27/2007

This sample portlet contains the JBoss Seam "Registration" example, which can be
found in the JBoss Seam 1.2.1GA production download, available here:
http://labs.jboss.com/jbossseam/download/index.html

Note that due to the following issues in Apache MyFaces and JBoss Seam, it was
necessary to create custom Liferay classes (which can be found in the sample's
WEB-INF/src/com/liferay/seam folder).

ISSUE: MYFACES-1671
JIRA: https://issues.apache.org/jira/browse/MYFACES-1671
DESCRIPTION: MyFacesGenericPortlet is causing multiple instances of PhaseListeners to be created
LIFERAY PROVIDED WORKAROUND: See JBSEAM-1556 below

ISSUE: JBSEAM-1556
JIRA: http://jira.jboss.com/jira/browse/JBSEAM-1556
DESCRIPTION: Multiple instances of Seam PhaseListener permitted to be registered in JSF lifecycle
LIFERAY PROVIDED WORKAROUND: Specify either com.liferay.seam.jsf.SeamPortletPhaseListener
or com.liferay.seam.jsf.TransactionalSeamPortletPhaseListener in the <phase-listener>
entry found in the WEB-INF/faces-config.xml file.

ISSUE: JBSEAM-1557
JIRA: http://jira.jboss.com/jira/browse/JBSEAM-1557
DESCRIPTION: Seam is peforming a redirect in when running as a portlet, rather than using the portlet lifecycle
LIFERAY PROVIDED WORKAROUND: The com.liferay.seam.core.Manager Seam component will automatically
override the org.jboss.core.Manager component found in the jboss-seam.jar library, since it has
a higher precedence value in the @Install annotation.

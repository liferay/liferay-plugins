<?php
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

$request = quercus_servlet_request();

$themeDisplay = $request->getAttribute(WebKeys::THEME_DISPLAY);

$myAccountURL = PortletURLFactoryUtil::create(
	$request, PortletKeys::MY_ACCOUNT, $themeDisplay->getPlid(),
	PortletRequest::RENDER_PHASE);

$myAccountURL->setParameter("struts_action", "/my_account/edit_user");
$myAccountURL->setPortletMode(PortletMode::VIEW);
$myAccountURL->setWindowState(WindowState::MAXIMIZED);
?>

<a href="<?php echo $myAccountURL->toString(); ?>">My Account</a>

<?php include("navigation.php"); ?>
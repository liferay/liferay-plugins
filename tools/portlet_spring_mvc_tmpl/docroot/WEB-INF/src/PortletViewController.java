package @portlet.java.package.name@;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class @portlet.java.class.name@PortletViewController {

    @RenderMapping
    public String question(Model model) {
        _LOG.info("Showing release info.");
        
		model.addAttribute("releaseInfo", ReleaseInfo.getReleaseInfo());
        
        return "@portlet.name@/view";
    }

	private static Log _LOG = LogFactoryUtil.getLog(
		@portlet.java.class.name@PortletViewController.class);
	
}

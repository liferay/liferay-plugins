package com.liferay.cdi.portlet.bridge;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

import com.liferay.cdi.portlet.bridge.context.PortletSessionScoped;

/**
 * CDI entry point for registering the {@link PortletSessionScoped} scope and the according {@link PortletSessionContext}.
 */
public class CDIExtension implements Extension {
    
    public void addScope(@Observes final BeforeBeanDiscovery event) {
        event.addScope(PortletSessionScoped.class, true, true);
    }

    public void registerContext(@Observes final AfterBeanDiscovery event) {
        event.addContext(new CDIPortletSessionContext());
    }
}

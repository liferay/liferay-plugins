package com.liferay.cdi.portlet.bridge;

import javax.portlet.PortletRequest;

class PortletRequestContainer {
    
    private static ThreadLocal<PortletRequest> currentPortletRequest = new ThreadLocal<PortletRequest>();

    static void registerPortletRequest(final PortletRequest request) {
        currentPortletRequest.set(request);
    }
    
    static void unregisterPortletRequest() {
        currentPortletRequest.set(null);
    }
    
    static PortletRequest getCurrentPortletRequest() {
        return currentPortletRequest.get();
    }
}

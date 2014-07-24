package com.liferay.cdi.portlet.bridge;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.portlet.PortletSession;

import com.liferay.cdi.portlet.bridge.context.PortletSessionScoped;

/**
 * A simple {@link Context} implementation that stores CDI beans within the {@link PortletSession}.
 * 
 * @author Michael Scholz
 */
public class CDIPortletSessionContext implements Context, Serializable {
    private static final long serialVersionUID = 1L;

    private static final String ATTRIBUTE_PREFIX = CDIPortletSessionContext.class.getName();

    @Override
    public Class<? extends Annotation> getScope() {
        return PortletSessionScoped.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(final Contextual<T> contextual, final CreationalContext<T> creationalContext) {
        if(!(contextual instanceof Bean<?>)) {
            return null;
        }

        final String attributeName = getAttributeName(contextual);

        final PortletSession portletSession = PortletRequestContainer.getCurrentPortletRequest().getPortletSession(true);

        T result = (T) portletSession.getAttribute(attributeName);
        if(result == null) {
            result = contextual.create(creationalContext);
            portletSession.setAttribute(attributeName, result);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(final Contextual<T> contextual) {
        if(!(contextual instanceof Bean<?>)) {
            return null;
        }

        final PortletSession portletSession = PortletRequestContainer.getCurrentPortletRequest().getPortletSession();
        return portletSession != null ? (T) portletSession.getAttribute(getAttributeName(contextual)) : null;
    }

    @Override
    public boolean isActive() {
        return PortletRequestContainer.getCurrentPortletRequest() != null;
    }

    private static <T> String getAttributeName(final Contextual<T> contextual) {
        final Bean<T> bean = (Bean<T>) contextual;
        final String beanClassName = bean.getBeanClass().getName();

        return ATTRIBUTE_PREFIX + "$" + beanClassName;
    }
}

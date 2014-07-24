package com.liferay.cdi.portlet.bridge.context;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.NormalScope;
import javax.portlet.PortletSession;

/**
 * A custom CDI Scope that is bound to the {@link PortletSession}.
 *  
 * @author Michael Scholz
 */
@Target(value = { METHOD, FIELD, TYPE })
@Retention(RUNTIME)
@NormalScope(passivating = true)
@Inherited
public @interface PortletSessionScoped {}

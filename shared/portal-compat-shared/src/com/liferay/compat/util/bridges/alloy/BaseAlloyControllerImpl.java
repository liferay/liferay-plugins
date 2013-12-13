/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.util.bridges.alloy;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.bean.ConstantsBeanFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.InvokerMessageListener;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.scheduler.CronText;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseAlloyControllerImpl implements AlloyController {

	public static final String TOUCH =
		BaseAlloyControllerImpl.class.getName() + "#TOUCH#";

	@Override
	public void afterPropertiesSet() {
		initClass();
		initServletVariables();
		initPortletVariables();
		initThemeDisplayVariables();
		initMethods();
		initPaths();
		initIndexer();
		initMessageListeners();
	}

	@Override
	public void execute() throws Exception {
		if (permissioned &&
			!AlloyPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), portlet.getRootPortletId(),
				controllerPath, actionPath)) {

			renderError(
				"you-do-not-have-permission-to-access-the-requested-resource");
		}

		Method method = getMethod(actionPath);

		if (method == null) {
			if (log.isDebugEnabled()) {
				log.debug("No method found for action " + actionPath);
			}
		}

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			Class<?> superClass = clazz.getSuperclass();

			Method executeActionMethod = superClass.getDeclaredMethod(
				"executeAction", new Class<?>[] {Method.class});

			ServiceBeanMethodInvocationFactoryUtil.proceed(
				this, BaseAlloyControllerImpl.class, executeActionMethod,
				new Object[] {method}, new String[] {"transactionAdvice"});
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			executeRender(method);
		}
		else if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
			executeResource(method);
		}
	}

	@Override
	public Portlet getPortlet() {
		return portlet;
	}

	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public ThemeDisplay getThemeDisplay() {
		return themeDisplay;
	}

	@Override
	public long increment() throws Exception {
		return CounterLocalServiceUtil.increment();
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public void updateModel(BaseModel<?> baseModel) throws Exception {
		BeanPropertiesUtil.setProperties(baseModel, request);

		if (baseModel.isNew()) {
			baseModel.setPrimaryKeyObj(increment());
		}

		updateAuditedModel(baseModel);
		updateGroupedModel(baseModel);
		updateAttachedModel(baseModel);

		if (baseModel instanceof PersistedModel) {
			PersistedModel persistedModel = (PersistedModel)baseModel;

			persistedModel.persist();
		}

		if ((indexer != null) &&
			indexerClassName.equals(baseModel.getModelClassName())) {

			indexer.reindex(baseModel);
		}
		else {
			Indexer baseModelIndexer = IndexerRegistryUtil.getIndexer(
				baseModel.getModelClass());

			if (baseModelIndexer != null) {
				baseModelIndexer.reindex(baseModel);
			}
		}
	}

	protected void addSuccessMessage() {
		String successMessage = ParamUtil.getString(
			portletRequest, "successMessage");

		SessionMessages.add(portletRequest, "requestProcessed", successMessage);
	}

	protected MessageListener buildControllerMessageListener() {
		return null;
	}

	protected String buildIncludePath(String viewPath) {
		if (viewPath.equals(_VIEW_PATH_ERROR)) {
			return "/WEB-INF/jsp/".concat(
				portlet.getFriendlyURLMapping()).concat("/views/error.jsp");
		}

		StringBundler sb = new StringBundler(7);

		sb.append("/WEB-INF/jsp/");
		sb.append(portlet.getFriendlyURLMapping());
		sb.append("/views/");
		sb.append(controllerPath);
		sb.append(StringPool.SLASH);
		sb.append(viewPath);
		sb.append(".jsp");

		return sb.toString();
	}

	protected Indexer buildIndexer() {
		return null;
	}

	protected MessageListener buildSchedulerMessageListener() {
		return null;
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	protected void executeAction(Method method) throws Exception {
		if (method != null) {
			method.invoke(this);
		}

		actionRequest.setAttribute(
			CALLED_PROCESS_ACTION, Boolean.TRUE.toString());

		if (Validator.isNotNull(viewPath)) {
			actionRequest.setAttribute(VIEW_PATH, viewPath);

			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		}
		else if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	protected void executeRender(Method method) throws Exception {
		boolean calledProcessAction = GetterUtil.getBoolean(
			(String)request.getAttribute(CALLED_PROCESS_ACTION));

		if (!calledProcessAction) {
			if (method != null) {
				method.invoke(this);
			}
		}

		if (Validator.isNull(viewPath)) {
			viewPath = actionPath;
		}

		String includePath = buildIncludePath(viewPath);

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(includePath);

		if (portletRequestDispatcher == null) {
			log.error(includePath + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(portletRequest, portletResponse);
		}

		Boolean touch = (Boolean)portletContext.getAttribute(
			TOUCH + portlet.getRootPortletId());

		if (touch != null) {
			return;
		}

		String touchPath =
			"/WEB-INF/jsp/" + portlet.getFriendlyURLMapping() +
				"/views/touch.jsp";

		if (log.isDebugEnabled()) {
			log.debug(
				"Touch " + portlet.getRootPortletId() + " by including " +
					touchPath);
		}

		portletContext.setAttribute(
			TOUCH + portlet.getRootPortletId(), Boolean.FALSE);

		portletRequestDispatcher = portletContext.getRequestDispatcher(
			touchPath);

		if (portletRequestDispatcher != null) {
			portletRequestDispatcher.include(portletRequest, portletResponse);
		}
	}

	protected void executeResource(Method method) throws Exception {
		if (method != null) {
			method.invoke(this);
		}
	}

	protected Object getConstantsBean(Class<?> clazz) {
		return ConstantsBeanFactoryUtil.getConstantsBean(clazz);
	}

	protected String getControllerDestinationName() {
		return "liferay/alloy/controller/".concat(
			getMessageListenerGroupName());
	}

	protected String getMessageListenerGroupName() {
		String rootPortletId = portlet.getRootPortletId();

		return rootPortletId.concat(StringPool.SLASH).concat(controllerPath);
	}

	protected Method getMethod(String methodName, Class<?>... parameterTypes) {
		String methodKey = getMethodKey(methodName, parameterTypes);

		return methodsMap.get(methodKey);
	}

	protected String getMethodKey(
		String methodName, Class<?>... parameterTypes) {

		StringBundler sb = new StringBundler(parameterTypes.length * 2 + 2);

		sb.append(methodName);
		sb.append(StringPool.POUND);

		for (Class<?> parameterType : parameterTypes) {
			sb.append(parameterType.getName());
			sb.append(StringPool.POUND);
		}

		return sb.toString();
	}

	protected String getSchedulerDestinationName() {
		return "liferay/alloy/scheduler/".concat(getMessageListenerGroupName());
	}

	protected String getSchedulerJobName() {
		return getMessageListenerGroupName();
	}

	protected Trigger getSchedulerTrigger() {
		CronText cronText = new CronText(
			CalendarFactoryUtil.getCalendar(), CronText.DAILY_FREQUENCY, 1);

		return new CronTrigger(
			getSchedulerJobName(), getMessageListenerGroupName(),
			cronText.toString());
	}

	protected Map<String, Serializable> getSearchAttributes(
			Object... attributes)
		throws Exception {

		Map<String, Serializable> attributesMap =
			new HashMap<String, Serializable>();

		if ((attributes.length == 0) || ((attributes.length % 2) != 0)) {
			throw new Exception("Arguments length is not an even number");
		}

		for (int i = 0; i < attributes.length; i += 2) {
			String name = String.valueOf(attributes[i]);

			Serializable value = (Serializable)attributes[i + 1];

			attributesMap.put(name, value);
		}

		return attributesMap;
	}

	protected long increment(String name) throws Exception {
		return CounterLocalServiceUtil.increment(name);
	}

	protected void initClass() {
		clazz = getClass();
		classLoader = clazz.getClassLoader();
	}

	protected void initIndexer() {
		indexer = buildIndexer();

		if (indexer == null) {
			return;
		}

		indexerClassName = indexer.getClassNames()[0];

		Indexer existingIndexer = IndexerRegistryUtil.getIndexer(
			indexerClassName);

		if ((existingIndexer != null) && (existingIndexer == indexer)) {
			BaseAlloyIndexer baseAlloyIndexer = (BaseAlloyIndexer)indexer;

			alloyServiceInvoker = baseAlloyIndexer.getAlloyServiceInvoker();

			return;
		}

		alloyServiceInvoker = new AlloyServiceInvoker(indexerClassName);

		BaseAlloyIndexer baseAlloyIndexer = (BaseAlloyIndexer)indexer;

		baseAlloyIndexer.setAlloyServiceInvoker(alloyServiceInvoker);
		baseAlloyIndexer.setPortletId(portlet.getRootPortletId());

		PortletBag portletBag = PortletBagPool.get(portlet.getPortletId());

		List<Indexer> indexerInstances = portletBag.getIndexerInstances();

		if (existingIndexer != null) {
			IndexerRegistryUtil.unregister(existingIndexer);

			indexerInstances.remove(existingIndexer);
		}

		IndexerRegistryUtil.register(indexer);

		indexerInstances.add(indexer);
	}

	protected void initMessageListener(
		String destinationName, MessageListener messageListener,
		boolean enableScheduler) {

		MessageBus messageBus = MessageBusUtil.getMessageBus();

		Destination destination = messageBus.getDestination(destinationName);

		if (destination != null) {
			Set<MessageListener> messageListeners =
				destination.getMessageListeners();

			for (MessageListener curMessageListener : messageListeners) {
				if (!(curMessageListener instanceof InvokerMessageListener)) {
					continue;
				}

				InvokerMessageListener invokerMessageListener =
					(InvokerMessageListener)curMessageListener;

				curMessageListener =
					invokerMessageListener.getMessageListener();

				if (messageListener == curMessageListener) {
					return;
				}

				Class<?> messageListenerClass = messageListener.getClass();

				String messageListenerClassName =
					messageListenerClass.getName();

				Class<?> curMessageListenerClass =
					curMessageListener.getClass();

				if (!messageListenerClassName.equals(
						curMessageListenerClass.getName())) {

					continue;
				}

				try {
					if (enableScheduler) {
						SchedulerEngineHelperUtil.unschedule(
							getSchedulerJobName(),
							getMessageListenerGroupName(),
							StorageType.MEMORY_CLUSTERED);
					}

					MessageBusUtil.unregisterMessageListener(
						destinationName, curMessageListener);
				}
				catch (Exception e) {
					log.error(e, e);
				}

				break;
			}
		}
		else {
			SerialDestination serialDestination = new SerialDestination();

			serialDestination.setName(destinationName);

			serialDestination.open();

			MessageBusUtil.addDestination(serialDestination);
		}

		try {
			MessageBusUtil.registerMessageListener(
				destinationName, messageListener);

			if (enableScheduler) {
				SchedulerEngineHelperUtil.schedule(
					getSchedulerTrigger(), StorageType.MEMORY_CLUSTERED, null,
					destinationName, null, 0);
			}
		}
		catch (Exception e) {
			log.error(e, e);
		}
	}

	protected void initMessageListeners() {
		controllerMessageListener = buildControllerMessageListener();

		if (controllerMessageListener != null) {
			initMessageListener(
				getControllerDestinationName(), controllerMessageListener,
				false);
		}

		schedulerMessageListener = buildSchedulerMessageListener();

		if (schedulerMessageListener != null) {
			initMessageListener(
				getSchedulerDestinationName(), schedulerMessageListener, true);
		}
	}

	protected void initMethods() {
		methodsMap = new HashMap<String, Method>();

		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			String methodKey = getMethodKey(
				method.getName(), method.getParameterTypes());

			methodsMap.put(methodKey, method);
		}
	}

	protected void initPaths() {
		controllerPath = ParamUtil.getString(request, "controller");

		if (Validator.isNull(controllerPath)) {
			Map<String, String> defaultRouteParameters =
				alloyPortlet.getDefaultRouteParameters();

			controllerPath = defaultRouteParameters.get("controller");
		}

		if (log.isDebugEnabled()) {
			log.debug("Controller path " + controllerPath);
		}

		actionPath = ParamUtil.getString(request, "action");

		if (Validator.isNull(actionPath)) {
			Map<String, String> defaultRouteParameters =
				alloyPortlet.getDefaultRouteParameters();

			actionPath = defaultRouteParameters.get("action");
		}

		if (log.isDebugEnabled()) {
			log.debug("Action path " + actionPath);
		}

		viewPath = GetterUtil.getString(
			(String)request.getAttribute(VIEW_PATH));

		request.removeAttribute(VIEW_PATH);

		if (log.isDebugEnabled()) {
			log.debug("View path " + viewPath);
		}

		if (mimeResponse != null) {
			portletURL = mimeResponse.createRenderURL();

			portletURL.setParameter("action", actionPath);
			portletURL.setParameter("controller", controllerPath);
			portletURL.setParameter("format", "html");

			if (log.isDebugEnabled()) {
				log.debug("Portlet URL " + portletURL);
			}
		}
	}

	protected void initPortletVariables() {
		liferayPortletConfig = (LiferayPortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		portletContext = liferayPortletConfig.getPortletContext();

		portlet = liferayPortletConfig.getPortlet();

		alloyPortlet = (AlloyPortlet)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_PORTLET);

		alloyPortlet.registerAlloyController(this);

		portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		liferayPortletResponse = (LiferayPortletResponse)portletResponse;

		lifecycle = GetterUtil.getString(
			(String)request.getAttribute(PortletRequest.LIFECYCLE_PHASE));

		if (log.isDebugEnabled()) {
			log.debug("Lifecycle " + lifecycle);
		}

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			actionRequest = (ActionRequest)portletRequest;
			actionResponse = (ActionResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.EVENT_PHASE)) {
			eventRequest = (EventRequest)portletRequest;
			eventResponse = (EventResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			mimeResponse = (MimeResponse)portletResponse;
			renderRequest = (RenderRequest)portletRequest;
			renderResponse = (RenderResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
			mimeResponse = (MimeResponse)portletResponse;
			resourceRequest = (ResourceRequest)portletRequest;
			resourceResponse = (ResourceResponse)portletResponse;
		}
	}

	protected void initServletVariables() {
		servletConfig = pageContext.getServletConfig();
		servletContext = pageContext.getServletContext();
		request = (HttpServletRequest)pageContext.getRequest();
		response = (HttpServletResponse)pageContext.getResponse();
	}

	protected void initThemeDisplayVariables() {
		themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		company = themeDisplay.getCompany();
		locale = themeDisplay.getLocale();
		user = themeDisplay.getUser();
	}

	protected void redirectTo(PortletURL portletURL) {
		redirectTo(portletURL.toString());
	}

	protected void redirectTo(String redirect) {
		if (!lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			throw new IllegalArgumentException(
				"redirectTo can only be called during the action phase");
		}

		if (Validator.isNotNull(viewPath)) {
			throw new IllegalArgumentException(
				"redirectTo cannot be called if render has been called");
		}

		this.redirect = redirect;
	}

	protected void render(String actionPath) {
		if (Validator.isNotNull(redirect)) {
			throw new IllegalArgumentException(
				"render cannot be called if redirectTo has been called");
		}

		viewPath = actionPath;
	}

	protected void renderError(String pattern, Object... arguments) {
		portletRequest.setAttribute("arguments", arguments);
		portletRequest.setAttribute("pattern", pattern);

		render(_VIEW_PATH_ERROR);
	}

	protected AlloySearchResult search(
			Map<String, Serializable> attributes, String keywords, Sort sort)
		throws Exception {

		return search(attributes, keywords, new Sort[] {sort});
	}

	protected AlloySearchResult search(
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		if (indexer == null) {
			throw new Exception("No indexer found for " + controllerPath);
		}

		AlloySearchResult alloySearchResult = new AlloySearchResult();

		alloySearchResult.setAlloyServiceInvoker(alloyServiceInvoker);

		SearchContainer<BaseModel<?>> searchContainer =
			new SearchContainer<BaseModel<?>>(
				portletRequest, portletURL, null, null);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		if ((attributes != null) && !attributes.isEmpty()) {
			searchContext.setAttributes(attributes);
		}

		searchContext.setEnd(searchContainer.getEnd());

		Class<?> indexerClass = Class.forName(indexerClassName);

		try {
			indexerClass.getField(Field.GROUP_ID);
		}
		catch (Exception e) {
			searchContext.setGroupIds(null);
		}

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		if (ArrayUtil.isNotEmpty(sorts)) {
			searchContext.setSorts(sorts);
		}

		searchContext.setStart(searchContainer.getStart());

		Hits hits = indexer.search(searchContext);

		alloySearchResult.setHits(hits);

		alloySearchResult.setPortletURL(portletURL);

		alloySearchResult.afterPropertiesSet();

		return alloySearchResult;
	}

	protected AlloySearchResult search(String keywords) throws Exception {
		return search(keywords, (Sort[])null);
	}

	protected AlloySearchResult search(String keywords, Sort sort)
		throws Exception {

		return search(keywords, new Sort[] {sort});
	}

	protected AlloySearchResult search(String keywords, Sort[] sorts)
		throws Exception {

		return search(null, keywords, sorts);
	}

	protected void setAlloyServiceInvokerClass(Class<?> clazz) {
		alloyServiceInvoker = new AlloyServiceInvoker(clazz.getName());
	}

	protected void setPermissioned(boolean permissioned) {
		this.permissioned = permissioned;
	}

	protected String translate(String pattern, Object... arguments) {
		return LanguageUtil.format(locale, pattern, arguments);
	}

	protected void updateAttachedModel(BaseModel<?> baseModel)
		throws Exception {

		if (!(baseModel instanceof AttachedModel)) {
			return;
		}

		AttachedModel attachedModel = (AttachedModel)baseModel;

		long classNameId = 0;

		String className = ParamUtil.getString(request, "className");

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		if (classNameId > 0) {
			attachedModel.setClassNameId(classNameId);
		}

		long classPK = ParamUtil.getLong(request, "classPK");

		if (classPK > 0) {
			attachedModel.setClassPK(classPK);
		}
	}

	protected void updateAuditedModel(BaseModel<?> baseModel) throws Exception {
		if (!(baseModel instanceof AuditedModel)) {
			return;
		}

		AuditedModel auditedModel = (AuditedModel)baseModel;

		if (baseModel.isNew()) {
			auditedModel.setCompanyId(company.getCompanyId());
			auditedModel.setUserId(user.getUserId());
			auditedModel.setUserName(user.getFullName());
			auditedModel.setCreateDate(new Date());
			auditedModel.setModifiedDate(auditedModel.getCreateDate());
		}
		else {
			auditedModel.setModifiedDate(new Date());
		}
	}

	protected void updateGroupedModel(BaseModel<?> baseModel) throws Exception {
		if (!(baseModel instanceof GroupedModel) || !baseModel.isNew()) {
			return;
		}

		GroupedModel groupedModel = (GroupedModel)baseModel;

		groupedModel.setGroupId(themeDisplay.getScopeGroupId());
	}

	protected void writeJSON(Object json) throws Exception {
		if (actionResponse != null) {
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, json.toString());
		}
		else if (mimeResponse != null) {
			mimeResponse.setContentType(ContentTypes.APPLICATION_JSON);

			PortletResponseUtil.write(mimeResponse, json.toString());
		}
	}

	protected static final String CALLED_PROCESS_ACTION =
		BaseAlloyControllerImpl.class.getName() + "#CALLED_PROCESS_ACTION";

	protected static final String VIEW_PATH =
		BaseAlloyControllerImpl.class.getName() + "#VIEW_PATH";

	protected static Log log = LogFactoryUtil.getLog(
		BaseAlloyControllerImpl.class);

	protected String actionPath;
	protected ActionRequest actionRequest;
	protected ActionResponse actionResponse;
	protected AlloyPortlet alloyPortlet;
	protected AlloyServiceInvoker alloyServiceInvoker;
	protected ClassLoader classLoader;
	protected Class<?> clazz;
	protected Company company;
	protected MessageListener controllerMessageListener;
	protected String controllerPath;
	protected EventRequest eventRequest;
	protected EventResponse eventResponse;
	protected Indexer indexer;
	protected String indexerClassName;
	protected String lifecycle;
	protected LiferayPortletConfig liferayPortletConfig;
	protected LiferayPortletResponse liferayPortletResponse;
	protected Locale locale;
	protected Map<String, Method> methodsMap;
	protected MimeResponse mimeResponse;
	protected PageContext pageContext;
	protected boolean permissioned;
	protected Portlet portlet;
	protected PortletContext portletContext;
	protected PortletRequest portletRequest;
	protected PortletResponse portletResponse;
	protected PortletURL portletURL;
	protected String redirect;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected HttpServletRequest request;
	protected ResourceRequest resourceRequest;
	protected ResourceResponse resourceResponse;
	protected HttpServletResponse response;
	protected MessageListener schedulerMessageListener;
	protected ServletConfig servletConfig;
	protected ServletContext servletContext;
	protected ThemeDisplay themeDisplay;
	protected User user;
	protected String viewPath;

	private static final String _VIEW_PATH_ERROR = "VIEW_PATH_ERROR";

}
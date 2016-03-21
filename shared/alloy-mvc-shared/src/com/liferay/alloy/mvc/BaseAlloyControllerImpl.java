/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.alloy.mvc;

import com.liferay.alloy.mvc.jsonwebservice.AlloyMockUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.bean.ConstantsBeanFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.InvokerMessageListener;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import java.lang.reflect.Method;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

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

	public static final String VIEW_PATH =
		BaseAlloyControllerImpl.class.getName() + "#VIEW_PATH";

	public static void setAuditedModel(
			BaseModel<?> baseModel, Company company, User user)
		throws Exception {

		if (!(baseModel instanceof AuditedModel) || (company == null) ||
			(user == null)) {

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

	public static void setAuditedModel(
			BaseModel<?> baseModel, HttpServletRequest request)
		throws Exception {

		if (!(baseModel instanceof AuditedModel) || (request == null)) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		setAuditedModel(
			baseModel, themeDisplay.getCompany(), themeDisplay.getUser());
	}

	public static void setAuditedModel(BaseModel<?> baseModel, User user)
		throws Exception {

		if (!(baseModel instanceof AuditedModel) || (user == null)) {
			return;
		}

		long companyId = CompanyLocalServiceUtil.getCompanyIdByUserId(
			user.getUserId());

		setAuditedModel(
			baseModel, CompanyLocalServiceUtil.getCompany(companyId), user);
	}

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

		registerAlloyController();
	}

	@Override
	public void execute() throws Exception {
		Method method = getMethod(actionPath);

		if (method == null) {
			if (log.isDebugEnabled()) {
				log.debug("No method found for action " + actionPath);
			}
		}

		if (!hasPermission()) {
			renderError(
				"you-do-not-have-permission-to-access-the-requested-resource");

			method = null;
		}

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			executeAction(method);
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			executeRender(method);
		}
		else if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
			executeResource(method);
		}

		if ((alloyNotificationEventHelper != null) &&
			!viewPath.equals(_VIEW_PATH_ERROR)) {

			alloyNotificationEventHelper.addUserNotificationEvents(
				request, controllerPath, actionPath,
				alloyNotificationEventHelperPayloadJSONObject);
		}
	}

	public BaseModel<?> fetchBaseModel(String className, long classPK)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			className);

		return alloyServiceInvoker.fetchModel(classPK);
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
	public String getResponseContent() {
		return responseContent;
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
	public void indexModel(BaseModel<?> baseModel) throws Exception {
		if ((indexer != null) &&
			indexerClassName.equals(baseModel.getModelClassName())) {

			indexer.reindex(baseModel);
		}
		else {
			Indexer<BaseModel<?>> baseModelIndexer =
				(Indexer<BaseModel<?>>)IndexerRegistryUtil.getIndexer(
					baseModel.getModelClass());

			if (baseModelIndexer != null) {
				baseModelIndexer.reindex(baseModel);
			}
		}
	}

	@Override
	public void persistModel(BaseModel<?> baseModel) throws Exception {
		if (!(baseModel instanceof PersistedModel)) {
			return;
		}

		PersistedModel persistedModel = (PersistedModel)baseModel;

		persistedModel.persist();
	}

	@Override
	public void setModel(BaseModel<?> baseModel, Object... properties)
		throws Exception {

		if (baseModel.isNew()) {
			baseModel.setPrimaryKeyObj(increment());
		}

		setAuditedModel(baseModel);
		setGroupedModel(baseModel);
		setAttachedModel(baseModel);

		if ((properties.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Properties length is not an even number");
		}

		for (int i = 0; i < properties.length; i += 2) {
			String propertyName = String.valueOf(properties[i]);
			Object propertyValue = properties[i + 1];

			BeanPropertiesUtil.setProperty(
				baseModel, propertyName, propertyValue);
		}
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String translate(String pattern, Object... arguments) {
		PortletConfig portletConfig = PortletConfigFactoryUtil.create(
			portlet, servletContext);

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(locale);

		return LanguageUtil.format(resourceBundle, pattern, arguments);
	}

	@Override
	public void updateModel(BaseModel<?> baseModel, Object... properties)
		throws Exception {

		BeanPropertiesUtil.setProperties(baseModel, request);

		updateModelIgnoreRequest(baseModel, properties);
	}

	@Override
	public void updateModelIgnoreRequest(
			BaseModel<?> baseModel, Object... properties)
		throws Exception {

		setModel(baseModel, properties);

		persistModel(baseModel);
	}

	protected void addOpenerSuccessMessage() {
		Map<String, String> data = (Map<String, String>)SessionMessages.get(
			request,
			portlet.getPortletId() +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET_DATA);

		if ((data == null) ||
			!GetterUtil.getBoolean(data.get("addSuccessMessage"))) {

			return;
		}

		addSuccessMessage();

		data.put("addSuccessMessage", StringPool.FALSE);

		SessionMessages.add(
			request,
			portlet.getPortletId() +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET_DATA,
			data);
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
		StringBundler sb = new StringBundler(4);

		sb.append("/WEB-INF/jsp/");
		sb.append(portlet.getFriendlyURLMapping());
		sb.append("/views/");

		if (viewPath.equals(_VIEW_PATH_ERROR)) {
			sb.append("error.jsp");

			return sb.toString();
		}

		sb = new StringBundler(new String[] {sb.toString()}, 4);

		sb.append(controllerPath);
		sb.append(StringPool.SLASH);
		sb.append(viewPath);
		sb.append(".jsp");

		return sb.toString();
	}

	protected Indexer<BaseModel<?>> buildIndexer() {
		return null;
	}

	protected String buildResponseContent(
			Object data, String message, int status)
		throws Exception {

		String responseContent = StringPool.BLANK;

		if (isRespondingTo("json")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (data instanceof Exception) {
				String stackTrace = getStackTrace((Exception)data);

				jsonObject.put("data", stackTrace);
			}
			else if (data instanceof JSONArray) {
				jsonObject.put("data", (JSONArray)data);
			}
			else if (data instanceof JSONObject) {
				jsonObject.put("data", (JSONObject)data);
			}
			else if (data != null) {
				jsonObject.put(
					"data",
					JSONFactoryUtil.createJSONObject(String.valueOf(data)));
			}

			jsonObject.put("message", message);
			jsonObject.put("status", status);

			responseContent = jsonObject.toString();
		}

		return responseContent;
	}

	protected MessageListener buildSchedulerMessageListener() {
		return null;
	}

	protected void executeAction(Method method) throws Exception {
		executeResource(method);

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
			executeResource(method);

			addOpenerSuccessMessage();
		}

		if (Validator.isNull(responseContent)) {
			if (Validator.isNull(viewPath)) {
				viewPath = actionPath;
			}

			String includePath = buildIncludePath(viewPath);

			include(includePath);
		}

		touch();
	}

	protected void executeResource(Method method) throws Exception {
		try {
			if (method != null) {
				Class<?> superClass = clazz.getSuperclass();

				Method invokeMethod = superClass.getDeclaredMethod(
					"invoke", new Class<?>[] {Method.class});

				ServiceBeanMethodInvocationFactoryUtil.proceed(
					this, BaseAlloyControllerImpl.class, invokeMethod,
					new Object[] {method}, new String[] {"transactionAdvice"});
			}
		}
		catch (Exception e) {
			String message = "an-unexpected-system-error-occurred";

			Throwable rootCause = getRootCause(e);

			if (rootCause instanceof AlloyException) {
				AlloyException ae = (AlloyException)rootCause;

				if (ae.log) {
					log.error(rootCause, rootCause);
				}

				message = rootCause.getMessage();
			}
			else {
				log.error(e, e);
			}

			renderError(HttpServletResponse.SC_BAD_REQUEST, e, message);
		}
		finally {
			if (isRespondingTo()) {
				String contentType = response.getContentType();

				if (isRespondingTo("json")) {
					contentType = ContentTypes.APPLICATION_JSON;
				}

				writeResponse(responseContent, contentType);
			}
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

	protected PortletURL getPortletURL(
			String controller, String action, PortletMode portletMode,
			String lifecycle)
		throws Exception {

		return getPortletURL(
			controller, action, portletMode, lifecycle,
			portletRequest.getWindowState(), null);
	}

	protected PortletURL getPortletURL(
			String controller, String action, PortletMode portletMode,
			String lifecycle, Object... parameters)
		throws Exception {

		return getPortletURL(
			controller, action, portletMode, lifecycle,
			portletRequest.getWindowState(), parameters);
	}

	protected PortletURL getPortletURL(
			String controller, String action, PortletMode portletMode,
			String lifecycle, WindowState windowState)
		throws Exception {

		return getPortletURL(
			controller, action, portletMode, lifecycle, windowState, null);
	}

	protected PortletURL getPortletURL(
			String controller, String action, PortletMode portletMode,
			String lifecycle, WindowState windowState, Object... parameters)
		throws Exception {

		Layout layout = themeDisplay.getLayout();

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, portlet.getPortletId(), layout.getPlid(), lifecycle);

		portletURL.setParameter("action", action);
		portletURL.setParameter("controller", controller);

		portletURL.setPortletMode(portletMode);
		portletURL.setWindowState(windowState);

		if (parameters == null) {
			return portletURL;
		}

		if ((parameters.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Parameters length is not an even number");
		}

		for (int i = 0; i < parameters.length; i += 2) {
			String parameterName = String.valueOf(parameters[i]);
			String parameterValue = String.valueOf(parameters[i + 1]);

			portletURL.setParameter(parameterName, parameterValue);
		}

		return portletURL;
	}

	protected Throwable getRootCause(Throwable throwable) {
		if (throwable.getCause() == null) {
			return throwable;
		}

		return getRootCause(throwable.getCause());
	}

	protected String getSchedulerDestinationName() {
		return "liferay/alloy/scheduler/".concat(getMessageListenerGroupName());
	}

	protected String getSchedulerJobName() {
		return getMessageListenerGroupName();
	}

	protected Trigger getSchedulerTrigger() {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		return TriggerFactoryUtil.createTrigger(
			getSchedulerJobName(), getMessageListenerGroupName(),
			calendar.getTime(), 1, TimeUnit.DAY);
	}

	protected Map<String, Serializable> getSearchAttributes(
			Object... attributes)
		throws Exception {

		Map<String, Serializable> attributesMap = new HashMap<>();

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

	protected String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();

		PrintWriter printWriter = new PrintWriter(stringWriter);

		e.printStackTrace(printWriter);

		return stringWriter.toString();
	}

	protected boolean hasPermission() {
		if (permissioned &&
			!AlloyPermission.contains(
				themeDisplay, controllerPath, actionPath)) {

			return false;
		}

		return true;
	}

	protected void include(String path) throws Exception {
		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(path);

		if (portletRequestDispatcher != null) {
			portletRequestDispatcher.include(portletRequest, portletResponse);
		}
		else {
			log.error(path + " is not a valid include");
		}
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
		baseAlloyIndexer.setClassName(portlet.getModelClassName());

		PortletBag portletBag = PortletBagPool.get(portlet.getPortletId());

		List<Indexer<?>> indexerInstances = portletBag.getIndexerInstances();

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
		methodsMap = new HashMap<>();

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

		format = ParamUtil.getString(request, "format");

		if (Validator.isNull(format)) {
			Map<String, String> defaultRouteParameters =
				alloyPortlet.getDefaultRouteParameters();

			format = defaultRouteParameters.get("format");
		}

		if (log.isDebugEnabled()) {
			log.debug("Format " + format);
		}

		if (mimeResponse != null) {
			portletURL = mimeResponse.createRenderURL();

			portletURL.setParameter("action", actionPath);
			portletURL.setParameter("controller", controllerPath);

			if (Validator.isNotNull(format)) {
				portletURL.setParameter("format", format);
			}

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

	@SuppressWarnings("unused")
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	protected void invoke(Method method) throws Exception {
		method.invoke(this);
	}

	protected boolean isRespondingTo() {
		return Validator.isNotNull(format);
	}

	protected boolean isRespondingTo(String format) {
		return StringUtil.equalsIgnoreCase(this.format, format);
	}

	@SuppressWarnings("unused")
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	protected String processDataRequest(ActionRequest actionRequest)
		throws Exception {

		return null;
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

	protected void registerAlloyController() {
		alloyPortlet.registerAlloyController(this);
	}

	protected void render(String actionPath) {
		if (Validator.isNotNull(redirect)) {
			throw new IllegalArgumentException(
				"render cannot be called if redirectTo has been called");
		}

		viewPath = actionPath;
	}

	protected void renderError(
			int status, Exception e, String pattern, Object... arguments)
		throws Exception {

		Throwable rootCause = getRootCause(e);

		if (isRespondingTo()) {
			responseContent = buildResponseContent(
				rootCause, translate(pattern, arguments), status);

			return;
		}

		portletRequest.setAttribute("arguments", arguments);

		String stackTrace = getStackTrace((Exception)rootCause);

		portletRequest.setAttribute("data", stackTrace);

		portletRequest.setAttribute("pattern", pattern);
		portletRequest.setAttribute("status", status);

		render(_VIEW_PATH_ERROR);
	}

	protected void renderError(int status, String pattern, Object... arguments)
		throws Exception {

		AlloyException alloyException = new AlloyException(
			translate("unspecified-cause"));

		renderError(status, alloyException, pattern, arguments);
	}

	protected void renderError(String pattern, Object... arguments)
		throws Exception {

		renderError(HttpServletResponse.SC_BAD_REQUEST, pattern, arguments);
	}

	protected boolean respondWith(int status, String message, Object object)
		throws Exception {

		Object data = null;

		if (isRespondingTo("json")) {
			if (object instanceof AlloySearchResult) {
				Hits hits = ((AlloySearchResult)object).getHits();

				Document[] documents = hits.getDocs();

				data = toJSONArray(documents);
			}
			else if (object instanceof Collection) {
				Object[] objects =
					((Collection)object).toArray(new BaseModel[0]);

				data = toJSONArray(objects);
			}
			else if (object instanceof JSONArray) {
				data = object;
			}
			else if (object != null) {
				data = toJSONObject(object);
			}
		}

		responseContent = buildResponseContent(data, message, status);

		return true;
	}

	@SuppressWarnings("unused")
	protected boolean respondWith(Object object) throws Exception {
		return respondWith(HttpServletResponse.SC_OK, null, object);
	}

	protected boolean respondWith(String message) throws Exception {
		return respondWith(message, null);
	}

	protected boolean respondWith(String message, Object object)
		throws Exception {

		return respondWith(HttpServletResponse.SC_OK, message, object);
	}

	protected AlloySearchResult search(
			HttpServletRequest request, PortletRequest portletRequest,
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		return search(
			request, portletRequest, null, attributes, keywords, sorts);
	}

	protected AlloySearchResult search(
			HttpServletRequest request, PortletRequest portletRequest,
			SearchContainer<? extends BaseModel<?>> searchContainer,
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		return search(
			indexer, alloyServiceInvoker, request, portletRequest,
			searchContainer, attributes, keywords, sorts);
	}

	protected AlloySearchResult search(
			Indexer indexer, AlloyServiceInvoker alloyServiceInvoker,
			HttpServletRequest request, PortletRequest portletRequest,
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		return search(
			indexer, alloyServiceInvoker, request, portletRequest, null,
			attributes, keywords, sorts);
	}

	protected AlloySearchResult search(
			Indexer indexer, AlloyServiceInvoker alloyServiceInvoker,
			HttpServletRequest request, PortletRequest portletRequest,
			Map<String, Serializable> attributes, String keywords, Sort[] sorts,
			int start, int end)
		throws Exception {

		if (indexer == null) {
			throw new Exception("No indexer found for " + controllerPath);
		}

		AlloySearchResult alloySearchResult = new AlloySearchResult();

		alloySearchResult.setAlloyServiceInvoker(alloyServiceInvoker);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		boolean andOperator = false;

		boolean advancedSearch = ParamUtil.getBoolean(
			request, "advancedSearch");

		if (advancedSearch) {
			andOperator = ParamUtil.getBoolean(request, "andOperator");
		}

		searchContext.setAndSearch(andOperator);

		if ((attributes != null) && !attributes.isEmpty()) {
			searchContext.setAttributes(attributes);
		}

		searchContext.setEnd(end);

		Class<?> indexerClass = Class.forName(indexer.getClassNames()[0]);

		if (!GroupedModel.class.isAssignableFrom(indexerClass)) {
			searchContext.setGroupIds(null);
		}
		else if (searchContext.getAttribute(Field.GROUP_ID) != null) {
			long groupId = GetterUtil.getLong(
				searchContext.getAttribute(Field.GROUP_ID));

			searchContext.setGroupIds(new long[] {groupId});
		}

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		if (ArrayUtil.isNotEmpty(sorts)) {
			searchContext.setSorts(sorts);
		}

		searchContext.setStart(start);

		Hits hits = indexer.search(searchContext);

		alloySearchResult.setHits(hits);

		alloySearchResult.setPortletURL(
			portletURL, searchContext.getAttributes());

		alloySearchResult.afterPropertiesSet();

		return alloySearchResult;
	}

	protected AlloySearchResult search(
			Indexer indexer, AlloyServiceInvoker alloyServiceInvoker,
			HttpServletRequest request, PortletRequest portletRequest,
			SearchContainer<? extends BaseModel<?>> searchContainer,
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		if (searchContainer == null) {
			searchContainer = new SearchContainer<>(
				portletRequest, portletURL, null, null);
		}

		return search(
			indexer, alloyServiceInvoker, request, portletRequest, attributes,
			keywords, sorts, searchContainer.getStart(),
			searchContainer.getEnd());
	}

	protected AlloySearchResult search(
			Map<String, Serializable> attributes, String keywords, Sort sort)
		throws Exception {

		return search(attributes, keywords, new Sort[] {sort});
	}

	protected AlloySearchResult search(
			Map<String, Serializable> attributes, String keywords, Sort[] sorts)
		throws Exception {

		return search(request, portletRequest, attributes, keywords, sorts);
	}

	protected AlloySearchResult search(
			Map<String, Serializable> attributes, String keywords, Sort[] sorts,
			int start, int end)
		throws Exception {

		return search(
			indexer, alloyServiceInvoker, request, portletRequest, attributes,
			keywords, sorts, start, end);
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

	protected void setAlloyNotificationEventHelper(
		AlloyNotificationEventHelper alloyNotificationEventHelper) {

		this.alloyNotificationEventHelper = alloyNotificationEventHelper;

		alloyNotificationEventHelperPayloadJSONObject = null;
	}

	protected void setAlloyServiceInvokerClass(Class<?> clazz) {
		alloyServiceInvoker = new AlloyServiceInvoker(clazz.getName());
	}

	protected void setAttachedModel(BaseModel<?> baseModel) throws Exception {
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

	protected void setAuditedModel(BaseModel<?> baseModel) throws Exception {
		setAuditedModel(baseModel, company, user);
	}

	protected void setGroupedModel(BaseModel<?> baseModel) throws Exception {
		if (!(baseModel instanceof GroupedModel) || !baseModel.isNew()) {
			return;
		}

		GroupedModel groupedModel = (GroupedModel)baseModel;

		groupedModel.setGroupId(themeDisplay.getScopeGroupId());
	}

	protected void setOpenerSuccessMessage() {
		SessionMessages.add(
			portletRequest,
			portlet.getPortletId() + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portlet.getPortletId());

		Map<String, String> data = new HashMap<>();

		data.put("addSuccessMessage", StringPool.TRUE);

		SessionMessages.add(
			request,
			portlet.getPortletId() +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET_DATA,
			data);
	}

	protected void setPermissioned(boolean permissioned) {
		this.permissioned = permissioned;
	}

	protected JSONArray toJSONArray(Object[] objects) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Object object : objects) {
			jsonArray.put(toJSONObject(object));
		}

		return jsonArray;
	}

	protected JSONObject toJSONObject(BaseModel<?> baseModel) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> modelAttributes = baseModel.getModelAttributes();

		for (String key : modelAttributes.keySet()) {
			Object value = modelAttributes.get(key);

			if (value instanceof Boolean) {
				jsonObject.put(String.valueOf(key), (Boolean)value);
			}
			else if (value instanceof Date) {
				jsonObject.put(String.valueOf(key), (Date)value);
			}
			else if (value instanceof Double) {
				jsonObject.put(String.valueOf(key), (Double)value);
			}
			else if (value instanceof Integer) {
				jsonObject.put(String.valueOf(key), (Integer)value);
			}
			else if (value instanceof Long) {
				jsonObject.put(String.valueOf(key), (Long)value);
			}
			else if (value instanceof Short) {
				jsonObject.put(String.valueOf(key), (Short)value);
			}
			else {
				jsonObject.put(String.valueOf(key), String.valueOf(value));
			}
		}

		return jsonObject;
	}

	protected JSONObject toJSONObject(Document document) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Field> fields = document.getFields();

		for (String key : fields.keySet()) {
			Field field = fields.get(key);

			jsonObject.put(field.getName(), field.getValue());
		}

		return jsonObject;
	}

	protected JSONObject toJSONObject(Object object) throws Exception {
		if (object instanceof BaseModel<?>) {
			return toJSONObject((BaseModel<?>)object);
		}
		else if (object instanceof Document) {
			return toJSONObject((Document)object);
		}
		else if (object instanceof JSONObject) {
			return (JSONObject)object;
		}

		throw new AlloyException(
			"Unable to convert " + object + " to a JSON object");
	}

	protected void touch() throws Exception {
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

		include(touchPath);
	}

	protected void writeResponse(Object content, String contentType)
		throws Exception {

		HttpServletResponse response = this.response;

		if (!(response instanceof AlloyMockUtil.MockHttpServletResponse)) {
			response = PortalUtil.getHttpServletResponse(portletResponse);
		}

		response.setContentType(contentType);

		ServletResponseUtil.write(response, content.toString());
	}

	protected static final String CALLED_PROCESS_ACTION =
		BaseAlloyControllerImpl.class.getName() + "#CALLED_PROCESS_ACTION";

	protected static Log log = LogFactoryUtil.getLog(
		BaseAlloyControllerImpl.class);

	protected String actionPath;
	protected ActionRequest actionRequest;
	protected ActionResponse actionResponse;
	protected AlloyNotificationEventHelper alloyNotificationEventHelper;
	protected JSONObject alloyNotificationEventHelperPayloadJSONObject;
	protected AlloyPortlet alloyPortlet;
	protected AlloyServiceInvoker alloyServiceInvoker;
	protected ClassLoader classLoader;
	protected Class<?> clazz;
	protected Company company;
	protected MessageListener controllerMessageListener;
	protected String controllerPath;
	protected EventRequest eventRequest;
	protected EventResponse eventResponse;
	protected String format;
	protected Indexer<BaseModel<?>> indexer;
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
	protected String responseContent = StringPool.BLANK;
	protected MessageListener schedulerMessageListener;
	protected ServletConfig servletConfig;
	protected ServletContext servletContext;
	protected ThemeDisplay themeDisplay;
	protected User user;
	protected String viewPath;

	private static final String _VIEW_PATH_ERROR = "VIEW_PATH_ERROR";

}
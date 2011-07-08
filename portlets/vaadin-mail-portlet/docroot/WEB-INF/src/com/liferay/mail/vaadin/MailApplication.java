/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.vaadin;

import com.liferay.mail.model.Account;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.vaadin.Composer.ComposerListener;
import com.liferay.mail.vaadin.PasswordPrompt.Status;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.gwt.server.PortletApplicationContext2.PortletListener;
import com.vaadin.terminal.gwt.server.PortletApplicationContext2;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Window;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/**
 * @author Henri Sara
 */
public class MailApplication extends Application {

	private static Log _log = LogFactoryUtil.getLog(MailApplication.class);

	// cannot use custom portlet modes due to #LPS-4090
	private static final String PARAM_RENDER_MODE = "mail_mode";
	private static final String MODE_COMPOSE = "compose";
	private static final String MODE_UNREAD = "unread";
	private static final String MODE_ACCOUNT = "account";

	private static final String PARAM_ACCOUNT = "initialAccountEntryId";

	Window mainWindow;

	private MainMailView mainMailView;
	private Controller controller;
	private String portletTitle;

	private String summaryViewURL;

	@Override
	public void init() {

		controller = new Controller(this);
		mainWindow = new Window();
		setMainWindow(mainWindow);

		if (getContext() instanceof PortletApplicationContext2) {
			PortletApplicationContext2 ctx =
				(PortletApplicationContext2) getContext();
			ctx.addPortletListener(this, new MailPortletListener());
		}
		else {
			// should not happen with portlet 2.0
			getMainWindow().showNotification(
				"Not inited via Portal!", Notification.TYPE_ERROR_MESSAGE);
		}

	}

	public Controller getController() {

		return controller;
	}

	private class MailPortletListener implements PortletListener {

		public void handleActionRequest(ActionRequest request,
				ActionResponse response, Window window) {
			// set the user and initialize any required managers
			controller.setRequest(request);
		}

		public void handleRenderRequest(RenderRequest request,
				RenderResponse response, final Window window) {
			// set the user and initialize any required managers
			controller.setRequest(request);

			if (summaryViewURL == null) {
				PortletURL url = response.createRenderURL();
				try {
					url.setPortletMode(PortletMode.VIEW);
					url.setWindowState(WindowState.NORMAL);
					summaryViewURL = url.toString();
				}
				catch (PortletModeException e) {
					_log.debug(e);
				}
				catch (WindowStateException e) {
					_log.debug(e);
				}
			}

			if (!PortletMode.VIEW.equals(request.getPortletMode()) && !PortletMode.EDIT.equals(request.getPortletMode())) {
				return;
			}

			String pid = PortalUtil.getPortletId(request);
			portletTitle = PortalUtil.getPortletTitle(pid, PortalUtil.getLocale(request));

			controller.setPreferences(request.getPreferences());

			// check mode in request and act accordingly
			if (PortletMode.VIEW.equals(request.getPortletMode())
					&& WindowState.MAXIMIZED.equals(request.getWindowState())) {
				// compose, view specific account or by default view unread?
				if (MODE_COMPOSE.equals(request.getParameter(PARAM_RENDER_MODE))) {
					switchToCompose(new Composer(), summaryViewURL);
				} else if (MODE_ACCOUNT.equals(request.getParameter(PARAM_RENDER_MODE))) {
					String accountParam = request.getParameter(PARAM_ACCOUNT);
					Account account = null;
					boolean promptOpen = false;

					VerticalLayout loading = new VerticalLayout();
					loading.setHeight("550px");
					window.setContent(loading);

					try {
						long id = Long.parseLong(accountParam);
						account = AccountLocalServiceUtil.getAccount(id);

						if (!account.isSavePassword()) {
							String password = Controller.get().getPasswordRetriever().getPassword(account.getAccountId());
							if (password != null) {
								try {
									Controller.get().getMailManager().storePassword(account.getAccountId(), password);
									Controller.get().getAccountManager().updateAccount(account, controller);
									Controller.get().getMailManager().synchronizeAccount(account.getAccountId());
								} catch (PortalException e1) {
									Controller.get().showError(
											Lang.get("unable-to-synchronize-account"), e1);
								} catch (SystemException e1) {
									Controller.get().showError(
											Lang.get("unable-to-synchronize-account"), e1);
								}
							} else {
								promptOpen = true;
								showPasswordPrompt(account, window);
							}
						}
					} catch (NumberFormatException e) {
						// ignore, accountEntry stays null
					} catch (PortalException e) {
						_log.warn("Could not get account", e);
						// account entry stays null
					} catch (SystemException e) {
						_log.warn("Could not get account", e);
						// account entry stays null
					}
					if (account != null && !promptOpen) {
						mainMailView = new MainMailView();
						mainMailView.setHeight("550px");
						window.setContent(mainMailView);
						mainMailView.show(account);
					} else if (!promptOpen) {
						window.open(new ExternalResource(summaryViewURL));
					}
				} else {
					// unread mode by default
					try {
						Account account = AccountLocalServiceUtil.getAccounts(Controller.get()
								.getUser().getUserId()).get(0);
						boolean promptOpen = false;

						VerticalLayout loading = new VerticalLayout();
						loading.setHeight("550px");
						window.setContent(loading);

						if (!account.isSavePassword()) {
							String password = Controller.get().getPasswordRetriever().getPassword(account.getAccountId());
							if (password != null) {
								try {
									Controller.get().getMailManager().storePassword(account.getAccountId(), password);
									Controller.get().getAccountManager().updateAccount(account, controller);
									Controller.get().getMailManager().synchronizeAccount(account.getAccountId());
								} catch (PortalException e1) {
									Controller.get().showError(
											Lang.get("unable-to-synchronize-account"), e1);
								} catch (SystemException e1) {
									Controller.get().showError(
											Lang.get("unable-to-synchronize-account"), e1);
								}
							} else {
								promptOpen = true;
								showPasswordPrompt(account, window);
							}
						}

						if (!promptOpen) {
							mainMailView = new MainMailView();
							mainMailView.setHeight("550px");
							window.setContent(mainMailView);
							mainMailView.show(account);
						}
					} catch (SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PortalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if (PortletMode.EDIT.equals(request.getPortletMode())) {
				window.setContent(new PreferencesView(controller));
			}
		}

		public void handleEventRequest(EventRequest request,
				EventResponse response, Window window) {
			// nothing to do
		}

		public void handleResourceRequest(ResourceRequest request,
				ResourceResponse response, Window window) {
			// nothing to do
		}

		public void showPasswordPrompt(Account account, final Window window) {
			PasswordPrompt prompt = new PasswordPrompt(account);
			account = null;
			prompt.addListener(new Window.CloseListener() {
				//@Override
				public void windowClose(CloseEvent e) {
					PasswordPrompt prompt = (PasswordPrompt)e.getWindow();

					mainMailView = new MainMailView();
					mainMailView.setHeight("550px");

					if (prompt.getStatus() == Status.VALIDATED) {
						mainMailView.setEnabled(true);

						try {
							controller.getMailManager().synchronizeAccount(prompt.getAccount().getAccountId());
						} catch (PortalException e1) {
							Controller.get().showError(
									Lang.get("unable-to-synchronize-account"), e1);
						} catch (SystemException e1) {
							Controller.get().showError(
									Lang.get("unable-to-synchronize-account"), e1);
						}

						window.setContent(mainMailView);
						mainMailView.show(prompt.getAccount());
						mainMailView.update();

					} else if (prompt.getStatus() == Status.CANCELED) {
						window.open(new ExternalResource(summaryViewURL));
					}
				}
			});
			window.addWindow(prompt);
			prompt.center();
		}
	}

	/**
	 * Switch to the compose mode, and return from it to the previous view when
	 * finished. If returnUrl is not <code>null</code>, go to that URL instead
	 * when finished.
	 */
	public void switchToCompose(Composer composer, final String returnUrl) {
		final ComponentContainer previousView = mainWindow.getContent();
		// TODO handle size better
		composer.setHeight("550px");
		mainWindow.setContent(composer);
		composer.addListener(new ComposerListener() {
			public void messageDiscarded(Composer composer) {
				if (returnUrl == null) {
					mainWindow.setContent(previousView);
				} else {
					mainWindow.open(new ExternalResource(returnUrl));
				}
			}
			public void messageSaved(Composer composer, Message message) {
				if (returnUrl == null) {
					mainWindow.setContent(previousView);
				}
				mainWindow.showNotification(
						Lang.get("saved-successfully"),
						Notification.TYPE_TRAY_NOTIFICATION);
				if (returnUrl != null) {
					mainWindow.open(new ExternalResource(returnUrl));
				}
			}
			public void messageSent(Composer composer, Message message) {
				if (returnUrl == null) {
					mainWindow.setContent(previousView);
				}

				String caption  = Lang.get("sent-successfully");
				String description =  Lang.get("to")
					+":</br>" + message.getTo()
					+ "<br/>" + Lang.get("subject") + ": "
					+ composer.getSubject() + "<br/>"
					+ composer.getAttachments().size() + " "
					+ Lang.get("attachments");

				mainWindow.showNotification(caption,description,
						Notification.TYPE_TRAY_NOTIFICATION);

				// Refresh messages
				try {
					// Update account
					Controller.get().getMailManager().synchronizeAccount(composer.getFrom().getAccountId());

					// Refresh main mail view if necessary
					if (previousView instanceof MainMailView) {
						((MainMailView)previousView).update();
					}

				} catch (PortalException e) {
					Controller.get().showError(Lang.get("unable-to-synchronize-account"), e);
				} catch (SystemException e) {
					Controller.get().showError(Lang.get("unable-to-synchronize-account"), e);
				}

				if (returnUrl != null) {
					mainWindow.open(new ExternalResource(returnUrl));
				}
			}
		});
	}

	/**
	 * Show the summary page JSP for non-maximized view mode: mail accounts,
	 * compose, etc.
	 */
	public void writeViewPageHtml(RenderRequest request,
			RenderResponse response, Window window) throws IOException,
			PortletException {

		PortletContext context = null;
		// portlet 2.0 only
		if (getContext() instanceof PortletApplicationContext2) {
			PortletApplicationContext2 ctx = (PortletApplicationContext2) getContext();
			context = ctx.getPortletSession().getPortletContext();
		}
		if (context != null) {
			PortletRequestDispatcher portletRequestDispatcher = context
					.getRequestDispatcher("/vaadin_summary_view.jsp");
			if (portletRequestDispatcher != null) {
				portletRequestDispatcher.include(request, response);
			}
			// for now, do not clear the parameters
   			// response.setProperty("clear-request-parameters", "true");
		}
	}

}
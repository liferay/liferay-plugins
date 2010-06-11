
package com.vaadin.liferay.mail;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AttachmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.liferay.mail.util.Lang;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class MessageView extends VerticalLayout implements ClickListener {

	private static Log _log = LogFactoryUtil.getLog(MessageView.class);

	private Label messageLabel;
	private VerticalLayout headersAndAttachmentLayout = new VerticalLayout();
	private FormLayout headersLayout;

	public MessageView() {

		setMargin(false, true, true, true);

		messageLabel = new Label("", Label.CONTENT_XHTML);
		messageLabel.setSizeFull();

		headersLayout = new FormLayout();

		headersAndAttachmentLayout.setStyleName("message-header");
		headersAndAttachmentLayout.addComponent(headersLayout);
		headersAndAttachmentLayout.setVisible(false);

		addComponent(headersAndAttachmentLayout);
		addComponent(new Label("<hr />", Label.CONTENT_XHTML));
		addComponent(messageLabel);

		setExpandRatio(messageLabel, 1);
	}

	public void showMessage(Message msg) {

		if (msg == null) {
			messageLabel.setVisible(false);
			headersAndAttachmentLayout.setVisible(false);
			return;
		}
		else {
			messageLabel.setVisible(true);
			headersAndAttachmentLayout.setVisible(true);
		}
		// Body
		String text = "";
		if (msg != null) {
			text = msg.getBody();
		}
		messageLabel.setValue(text);

		// Headers
		headersLayout = new FormLayout();
		headersLayout.setSpacing(false);
		headersLayout.setMargin(false);
		if (msg != null) {
			String to = msg.getTo();
			String cc = msg.getCc();
			// String replyTo = msg.get();

			Label subject = new Label(msg.getSubject());
			subject.setCaption(Lang.get("subject"));
			headersLayout.addComponent(subject);

			Label from = new Label(msg.getSender());
			from.setCaption(Lang.get("from"));
			headersLayout.addComponent(from);

			if (to != null && !to.equals("")) {
				Label toLabel = new Label(to);
				toLabel.setCaption(Lang.get("to"));
				headersLayout.addComponent(toLabel);
			}
			if (cc != null && !cc.equals("")) {
				Label ccLabel = new Label(cc);
				ccLabel.setCaption(Lang.get("cc"));
				headersLayout.addComponent(ccLabel);
			}

			Label date = new Label(formatDate(msg.getSentDate()));
			date.setCaption(Lang.get("date"));
			headersLayout.addComponent(date);

			if (MessageUtil.isImportant(msg)) {
				Label flag = new Label(Lang.get("important"));
				flag.setStyleName(MessageList.STYLE_IMPORTANT);
				flag.setCaption(Lang.get("flag"));
				headersLayout.addComponent(flag);

			}
		}

		// Attachments
		try {
			headersAndAttachmentLayout.removeAllComponents();
			headersAndAttachmentLayout.addComponent(headersLayout);

			Controller controller = Controller.get();
			List<Attachment> attachments = AttachmentLocalServiceUtil
					.getAttachments(msg.getMessageId());
			if (attachments != null && !attachments.isEmpty()) {
				for (Attachment attachment : attachments) {
					Button attachmentDownload = new Button();
					attachmentDownload.setStyleName(BaseTheme.BUTTON_LINK);

					attachmentDownload.setCaption(attachment.getFileName()
							+ " "
							+ MessageUtil.formatSize(attachment.getSize(),
									controller.getUserLocale()));
					attachmentDownload.setData(attachment);
					attachmentDownload.addListener(this);

					headersAndAttachmentLayout.addComponent(attachmentDownload);
				}
			}
		}
		catch (SystemException e) {
			_log.debug(e);
		}
	}

	private String formatDate(Date date) {

		if (date == null) {
			return "";
		}

		return DateFormat.getDateTimeInstance().format(date);
	}

	public void buttonClick(ClickEvent event) {

		Button b = event.getButton();
		Object data = b.getData();
		if (data != null && data instanceof Attachment) {
			final Attachment attachment = (Attachment) data;
			StreamResource r = new StreamResource(new StreamSource() {

				public InputStream getStream() {

					try {
						return AttachmentLocalServiceUtil.getInputStream(attachment.getAttachmentId());
					}
					catch (PortalException e) {
						Controller.get().showError(
							Lang.get("unable-to-fetch-attachment"), e);
					}
					catch (SystemException e) {
						Controller.get().showError(
							Lang.get("unable-to-fetch-attachment"), e);
					}
					return null;
				}

			}, attachment.getFileName(), Controller.get().getApplication());

			Controller.get().getApplication().getMainWindow().open(r, "_blank");
		}

	}
}

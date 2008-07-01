Liferay.Notepad = {
	init: function(params) {
		var instance = this;

		instance.namespace = params.namespace;

		// Common variables

		instance.addConstant = params.addConstant;
		instance.deleteConstant = params.deleteConstant;
		instance.imagePath = params.imagePath;
		instance.infoMessage = params.infoMessage;
		instance.notepadShowAllText = params.notepadShowAllText;
		instance.notepadShowLessText = params.notepadShowLessText;
		instance.successText = params.successText;
		instance.url = params.url;

		instance.categoryLinks = jQuery('.sample-portlet-notepad .notepad-entry span');
		instance.notesContainer = jQuery('.sample-portlet-notepad .notes-container');
		instance.notesTable = jQuery('.sample-portlet-notepad .notes-table');

		instance.entry = jQuery('#' + instance.namespace + 'notepad-entry');
		instance.entryButton = jQuery('#' + instance.namespace + 'notepad-entry-button');
		instance.entryLink = jQuery('#' + instance.namespace + 'notepad-entry-link');
		instance.status = jQuery('#' + instance.namespace + 'notepad-status');
		instance.notes = jQuery('#' + instance.namespace + 'notes');

		// Init methods

		instance.setCategoryLink();
		instance.setNotesContainer();
	},

	setCategoryLink: function() {
		var instance = this;

		instance.categoryLinks.each(function(index) {
			jQuery(this).click(function () {
				instance.categoryLinks.removeClass("current");

				jQuery(this).addClass("current");
			});
		});
	},

	setNotesContainer: function() {
		var instance = this;

		var totalNotes = jQuery('.sample-portlet-notepad .notes-holder').size();

		if (totalNotes != 0) {
			instance.notesContainer.css({display: 'block'});
		}
	},

	notepadLink: function() {
		var instance = this;

		var action = instance.entryLink.attr("action");

		if (action == "expand") {
			instance.entry.slideDown("normal");
			instance.entryLink.text(instance.notepadShowLessText);
			instance.entryLink.attr("action", "collapse");
		}
		else if (action == "collapse") {
			instance.entry.slideUp("normal");
			instance.entryLink.text(instance.notepadShowAllText);
			instance.entryLink.attr("action", "expand");
		}
	},

	addNotepadEntry: function() {
		var instance = this;

		var categoryId = jQuery('.sample-portlet-notepad .notepad-entry .current').attr('categoryid');

		var url = instance.url + "&cmd=" + instance.addConstant + "&categoryid=" + categoryId + "&notes=" + instance.notes.val();

		if (instance.notes.val() != '') {
			jQuery.ajax({
				url: url,
				dataType: 'json',
				success: function(message) {
					var newNotepadEntry = '';

					newNotepadEntry += '<tr class="notes-holder" id="' + instance.namespace + message.notepadEntryId + '">';
					newNotepadEntry += '	<td class="notes-category" valign="top">';
					newNotepadEntry += '		<img src="' + instance.imagePath + message.categoryImage + '" />';
					newNotepadEntry += '	</td>';
					newNotepadEntry += '	<td class="notes">';
					newNotepadEntry += '		<span class="notes-controller">';
					newNotepadEntry += '			<span class="notes-date" valign="top">' + message.createDate + '</span>';
					newNotepadEntry += '			<span class="notes-delete" valign="top">';
					newNotepadEntry += '				<a href="javascript: Liferay.Notepad.deleteNotepadEntry(' + message.notepadEntryId + ');">';
					newNotepadEntry += '					<img src="' + instance.imagePath + 'close.png" />';
					newNotepadEntry += '				</a>';
					newNotepadEntry += '			</span>';
					newNotepadEntry += '		</span>';
					newNotepadEntry += 			message.notes;
					newNotepadEntry += '	</td>';
					newNotepadEntry += '</tr>';

					jQuery('.sample-portlet-notepad .notes-table tr:first').after(newNotepadEntry);

					instance.notes.val("");

					var totalNotes = jQuery('.sample-portlet-notepad .notes-holder').size();

					if (totalNotes == 1) {
						instance.notesContainer.css({display: 'block'});
					}

					instance.status.removeClass().text(instance.successText);
					instance.status.attr('class', 'portlet-msg-success');

					jQuery('.sample-portlet-notepad .portlet-msg-success').fadeOut(2000, function() {
						jQuery(this).css({display:''});
						jQuery(this).attr('class', 'portlet-msg-info');
						jQuery(this).text(instance.infoMessage);
					});
				}
			});
		}
	},

	deleteNotepadEntry: function(notepadEntryId) {
		var instance = this;

		var url = instance.url + "&cmd=" + instance.deleteConstant + "&notepadEntryId=" + notepadEntryId;

		jQuery.ajax({
			url: url,
			dataType: 'json',
			success: function(message) {
				var totalNotes = jQuery('.sample-portlet-notepad .notes-holder').size();

				if (totalNotes == 1) {
					instance.notesContainer.css({display: ''});
				}

				jQuery('#' + instance.namespace + notepadEntryId).remove();
			}
		});
	}

}
Liferay.KnowledgeBase = {
	initEditArticle: function(params) {
		var instance = this;

		// Common variables

		instance.namespace = params.namespace;
		instance.templateURL = params.templateURL;

		instance.applyTemplateButton = jQuery('#' + instance.namespace + 'applyTemplateButton');
		instance.templateContent = jQuery('#' + instance.namespace + 'templateContent');

		// Init methods
		instance.loadBlankTemplate();
	},

	initViewArticle: function(params) {
		var instance = this;

		// Common variables

		instance.articleResourcePrimKey = params.articleResourcePrimKey;
		instance.averageScore = params.averageScore;
		instance.namespace = params.namespace;
		instance.feedbackURL = params.feedbackURL;
		instance.score = params.score;
		instance.userId = params.userId;

		instance.textAverage = params.textAverage;
		instance.textNo = params.textNo;
		instance.textSuccess = params.textSuccess;
		instance.textThanksComment = params.textThanksComment;
		instance.textThanksVote = params.textThanksVote;
		instance.textUpdateFeedback = params.updateFeedback;
		instance.textVote = params.textVote;
		instance.textVotes = params.textVotes;
		instance.textYes = params.textYes;

		instance.averageRating = jQuery('#' + instance.namespace + 'averageRating');
		instance.comments = jQuery('#' + instance.namespace + 'comments');
		instance.commentsMessage = jQuery('#' + instance.namespace + 'commentsMessage');
		instance.ctrlHolderFeedbackComments = jQuery('#' + instance.namespace + 'ctrlHolderFeedbackComments');
		instance.feedbackContainer = jQuery('#' + instance.namespace + 'feedbackContainer');
		instance.feedbackForm = jQuery('#' + instance.namespace + 'feedbackForm');
		instance.feedbackStatus = jQuery('#' + instance.namespace + 'feedbackStatus');
		instance.feedbackUpdateLink = jQuery('#' + instance.namespace + 'feedbackUpdateLink');
		instance.noPercentage = jQuery('#' + instance.namespace + 'noPercentage');
		instance.totalEntries = jQuery('#' + instance.namespace + 'totalEntries');
		instance.totalVotes = jQuery('#' + instance.namespace + 'totalVotes');
		instance.yesPercentage = jQuery('#' + instance.namespace + 'yesPercentage');

		// Init methods

		instance.loadScore();
	},

	getTemplate: function(templateResourcePrimKey) {
		var instance = this;

		if (templateResourcePrimKey == "") {
			instance.loadBlankTemplate();
		}
		else {
			jQuery.ajax({
				url: instance.templateURL,
				data: {
					actionName: 'get_template',
					templateResourcePrimKey: templateResourcePrimKey
				},
				dataType: 'json',
				success: function(message) {
					instance.templateContent.html(message.content);

					instance.templateContent.show();
					instance.applyTemplateButton.show();
				}
			});
		}
	},

	loadBlankTemplate: function() {
		var instance = this;

		document.getElementById(instance.namespace + "templates").selectedIndex = 0;

		instance.templateContent.hide();
		instance.applyTemplateButton.hide();
	},

	loadScore: function() {
		var instance = this;

		knowledgeBaseYourRatingObj = new Liferay.Portal.StarRating(
			instance.namespace + 'yourRating',
			{
				rating: instance.score,
				onComplete: function(rating) {
					jQuery.ajax({
						url: instance.feedbackURL,
						data: {
							actionName: 'feedback_score',
							articleResourcePrimKey: instance.articleResourcePrimKey,
							score: rating,
							userId: instance.userId
						},
						dataType: 'json',
						success: function(message) {
							var entriesHtml = (message.totalScoreEntries == 1) ? instance.textAverage + ' (' + message.totalScoreEntries + ' ' + instance.textVote + ')' : instance.textAverage + ' (' + message.totalScoreEntries + ' ' + instance.textVotes + ')';

							instance.totalEntries.html(entriesHtml);
							instance.averageRating.removeAttr('onmousemove');

							knowledgeBaseAverageRatingObj.display(message.averageScore);
						}
					});
				}
			}
		);

		knowledgeBaseAverageRatingObj = new Liferay.Portal.StarRating(
			instance.namespace + 'averageRating',
			{
				displayOnly: true,
				rating: instance.averageScore
			}
		);
	},

	saveFeedbackComments: function() {
		var instance = this;

		jQuery.ajax({
			url: instance.feedbackURL,
			data: {
				actionName: 'feedback_comments',
				articleResourcePrimKey: instance.articleResourcePrimKey,
				comments: instance.comments.val(),
				userId: instance.userId
			},
			dataType: 'json',
			success: function(message) {
				instance.feedbackStatus.attr('class', 'portlet-msg-success');
				instance.feedbackStatus.text(instance.textThanksComment);

				instance.feedbackForm.hide();
			}
		});
	},

	saveFeedbackVote: function(vote) {
		var instance = this;

		jQuery.ajax({
			url: instance.feedbackURL,
			data: {
				actionName: 'feedback_vote',
				articleResourcePrimKey: instance.articleResourcePrimKey,
				userId: instance.userId,
				vote: vote
			},
			dataType: 'json',
			success: function(message) {
				var totalVotes = (message.totalVotes == 1) ? '(' + message.totalVotes + ' ' + instance.textVote + ')' : '(' + message.totalVotes + ' ' + instance.textVotes + ')';
				var yesPercentage = parseInt((message.yesVotes / message.totalVotes) * 100);
				var noPercentage = 100 - yesPercentage;

				instance.feedbackStatus.attr('class', 'portlet-msg-success');
				instance.feedbackStatus.text(instance.textSuccess);

				instance.yesPercentage.text(yesPercentage + "%");
				instance.noPercentage.text(noPercentage + "%");
				instance.totalVotes.text(totalVotes);

				if (vote == 1) {
					instance.commentsMessage.text(instance.textYes);
				}
				else {
					instance.commentsMessage.text(instance.textNo);
				}

				instance.feedbackStatus
					.animate({opacity: 1.0}, 1500)
					.fadeOut(
						500,
						function() {
							instance.feedbackStatus.css({display: ''});
							instance.feedbackStatus.attr('class', 'portlet-msg-info');
							instance.feedbackStatus.text(instance.textThanksVote);
						}
					);

				instance.ctrlHolderFeedbackComments.show();
			}
		});
	},

	updateLink: function() {
		var instance = this;

		instance.feedbackStatus.text(instance.textUpdateFeedback);

		instance.feedbackContainer.show();
		instance.feedbackUpdateLink.hide();
	}

}
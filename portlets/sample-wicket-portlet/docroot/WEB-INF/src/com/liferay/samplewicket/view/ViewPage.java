/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.samplewicket.view;

import com.liferay.samplewicket.model.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * <a href="ViewPage.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 */
public class ViewPage extends WebPage {

	protected FeedbackPanel feedBackPanel = new FeedbackPanel("message");
	protected List<Person> persons = new ArrayList<Person>();
	protected Label personsLabel;
	protected ListView<Person> personsView;

	public ViewPage() {

		final Form<Person> form = new Form<Person>(
			"form", new CompoundPropertyModel<Person>(new Person())) {

			protected void onSubmit() {
				persons.add(getModelObject());

				setModelObject(new Person());

				feedBackPanel.info("Your resquest was processed succesfully!");

				personsLabel.setVisible(!persons.isEmpty());

				personsView.setVisible(!persons.isEmpty());
			}
		};

		TextField<String> name = new TextField<String>("name");

		name.setRequired(true);

		List<Character> genders = new ArrayList<Character>();

		genders.add('F');
		genders.add('M');

		final ChoiceRenderer<Character> renderer =
			new ChoiceRenderer<Character>() {

			public Object getDisplayValue(Character object) {

				if (object.charValue() == 'F') {
					return "Female";
				}
				else {
					return "Male";
				}
			};
		};

		DropDownChoice<Character> gender = new DropDownChoice<Character>(
			"gender", genders, renderer);

		gender.setNullValid(false);

		personsLabel = new Label("personsLabel", "Persons");
		personsLabel.setVisible(!persons.isEmpty());

		personsView = new ListView<Person>("persons", persons) {

			protected void populateItem(ListItem<Person> listItem) {
				Person person = listItem.getModelObject();

				String nameLabel = person.getName();
				String genderLabel = renderer.getDisplayValue(
					person.getGender()).toString();

				listItem.add(new Label("name", nameLabel));
				listItem.add(new Label("gender", genderLabel));
			}
		};

		personsView.setVisible(!persons.isEmpty());

		form.add(name);
		form.add(gender);

		add(feedBackPanel);
		add(form);
		add(personsLabel);
		add(personsView);
	}

	protected void onBeforeRender() {
		if (feedBackPanel.anyMessage()) {
			feedBackPanel.setVisible(true);
		}
		else {
			feedBackPanel.setVisible(false);
		}
		super.onBeforeRender();
	}

}
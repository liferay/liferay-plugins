/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.shindig.social.opensocial;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.apache.shindig.gadgets.GadgetToken;

import com.liferay.portlet.shindig.social.service.BasicPeopleService;
import com.liferay.portlet.shindig.social.service.BasicDataService;
import com.liferay.portlet.shindig.social.service.BasicActivitiesService;

import org.apache.shindig.social.opensocial.PeopleService;
import org.apache.shindig.social.opensocial.DataService;
import org.apache.shindig.social.opensocial.model.IdSpec;
import org.apache.shindig.social.opensocial.model.Activity;
import org.apache.shindig.social.ResponseError;
import org.apache.shindig.social.RequestItem;
import org.apache.shindig.social.GadgetDataHandler;
import org.apache.shindig.social.ResponseItem;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for serving the data required for opensocial.
 * This will expand to be more sophisticated as time goes on.
 */
public class OpenSocialDataHandler implements GadgetDataHandler {
  private static final Logger logger
      = Logger.getLogger("org.apache.shindig.social");

  // TODO: get through injection
  private static PeopleService peopleHandler = new BasicPeopleService();
  private static DataService dataHandler = new BasicDataService();
  private static ActivitiesService activitiesHandler
      = new BasicActivitiesService();

  public enum OpenSocialDataType {
    FETCH_PEOPLE,
    FETCH_PERSON_APP_DATA, UPDATE_PERSON_APP_DATA,
    FETCH_ACTIVITIES, CREATE_ACTIVITY
  }

  public boolean shouldHandle(String requestType) {
    try {
      // There should be a cleaner way to do this...
      OpenSocialDataType.valueOf(requestType);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public ResponseItem handleRequest(RequestItem request) {
    OpenSocialDataType type = OpenSocialDataType.valueOf(request.getType());
    ResponseItem response = new ResponseItem<Object>(
        ResponseError.NOT_IMPLEMENTED,
        request.getType() + " has not been implemented yet.",
        new JSONObject());

    try {
      JSONObject params = request.getParams();
      String jsonSpec = params.getString("idSpec");
      List<String> peopleIds = peopleHandler.getIds(IdSpec.fromJson(jsonSpec),
          request.getToken());

      switch (type) {
        case FETCH_PEOPLE :
          JSONArray profileDetail = params.getJSONArray("profileDetail");

          Set<String> profileDetailFields = new HashSet<String>(
              profileDetail.length() + 1, 1);
          for (int i = 0; i < profileDetail.length(); i++) {
            profileDetailFields.add(profileDetail.getString(i));
          }

          PeopleService.SortOrder sortOrder = PeopleService.SortOrder.valueOf(
              params.getString("sortOrder"));
          PeopleService.FilterType filter = PeopleService.FilterType.valueOf(
              params.getString("filter"));
          int first = params.getInt("first");
          int max = params.getInt("max");

          // TODO: Should we put this in the requestitem and pass the whole
          // thing along?
          response = peopleHandler.getPeople(peopleIds, sortOrder, filter,
              first, max, profileDetailFields, request.getToken());
          break;

        case FETCH_PERSON_APP_DATA :
          JSONArray jsonKeys = params.getJSONArray("keys");
          List<String> keys = new ArrayList<String>(jsonKeys.length());
          for (int i = 0; i < jsonKeys.length(); i++) {
            keys.add(jsonKeys.getString(i));
          }

          response = dataHandler.getPersonData(peopleIds, keys,
              request.getToken());
          break;

        case UPDATE_PERSON_APP_DATA:
          // We only support updating one person right now
          String id = peopleIds.get(0);

          String key = params.getString("key");
          String value = params.getString("value");

          response = dataHandler.updatePersonData(id, key, value,
              request.getToken());
          break;

        case FETCH_ACTIVITIES:
          response = activitiesHandler.getActivities(peopleIds,
              request.getToken());
          break;

        case CREATE_ACTIVITY:
          // We only support creating an activity for one person right now
          String personId = peopleIds.get(0);

          // TODO: We need to get the other fields from the json..
          // so json -> pojo
          Activity activity = new Activity("5", personId);
          activity.setTitle("Temporary title - we don't read json right now");
          response = activitiesHandler.createActivity(personId, activity,
              request.getToken());
          break;
      }

    } catch (JSONException e) {
      logger.log(Level.INFO, e.getMessage());
      response = new ResponseItem<Object>(ResponseError.BAD_REQUEST,
          "The json request had a bad format", new JSONObject());
    } catch (IllegalArgumentException e) {
      logger.log(Level.INFO, e.getMessage());
      response = new ResponseItem<Object>(ResponseError.BAD_REQUEST,
          "The json request had a bad idSpec", new JSONObject());
    }

    return response;
  }
}

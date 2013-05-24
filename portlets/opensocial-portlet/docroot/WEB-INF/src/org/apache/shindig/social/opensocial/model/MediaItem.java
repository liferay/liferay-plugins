/* @generated */
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
package org.apache.shindig.social.opensocial.model;

import org.apache.shindig.protocol.model.Exportablebean;
import org.apache.shindig.social.core.model.MediaItemImpl;

import com.google.inject.ImplementedBy;

/**
 * A container for the media item.
 */
@ImplementedBy(MediaItemImpl.class)
@Exportablebean
public interface MediaItem {

  /**
   * Fields for MediaItem.
   */
  public static enum Field {
    ALBUM_ID("albumId"),
    CREATED("created"),
    DESCRIPTION("description"),
    DURATION("duration"),
    FILE_SIZE("fileSize"),
    ID("id"),
    LANGUAGE("language"),
    LAST_UPDATED("lastUpdated"),
    LOCATION("location"),
    MIME_TYPE("mimeType"),
    NUM_COMMENTS("numComments"),
    NUM_VIEWS("numViews"),
    NUM_VOTES("numVotes"),
    RATING("rating"),
    START_TIME("startTime"),
    TAGGED_PEOPLE("taggedPeople"),
    TAGS("tags"),
    THUMBNAIL_URL("thumbnailUrl"),
    TITLE("title"),
    TYPE("type"),
    URL("url");

    /**
     * The field name that the instance represents.
     */
    private final String jsonString;

    /**
     * create a field base on the an element name.
     *
     * @param jsonString the name of the element
     */
    private Field(String jsonString) {
      this.jsonString = jsonString;
    }

    /**
     * @return a string representation of the enum.
     */
    @Override
    public String toString() {
      return this.jsonString;
    }
  }

  /**
   * An enumeration of potential media types.
   */
  public enum Type {
    AUDIO("audio"),
    IMAGE("image"),
    VIDEO("video");

    /**
     * The field type.
     */
    private final String jsonString;

    /**
     * Construct a field type based on the name.
     *
     * @param jsonString
     */
    private Type(String jsonString) {
      this.jsonString = jsonString;
    }

    /**
     * @return a string representation of the enum.
     */
    @Override
    public String toString() {
      return this.jsonString;
    }
  }

  /**
   * Get the mime type for this Media item.
   * @return the mime type.
   */
  String getMimeType();

  /**
   * Set the mimetype for this Media Item.
   * @param mimeType the mimeType
   */
  void setMimeType(String mimeType);

  /**
   * Get the Type of this media item, either audio, image or video.
   * @return the Type of this media item
   */
  Type getType();

  /**
   * Get the Type of this media item, either audio, image or video.
   * @param type the type of this media item
   */
  void setType(Type type);

  /**
   * Get a URL for the media item.
   * @return the url of the media item
   */
  String getUrl();

  /**
   * Set a URL for the media item.
   * @param url the media item URL
   */
  void setUrl(String url);

  /**
   * Get the thumbnail URL for the media item.
   * @return the thumbnail url of the MediaItem
   */
  String getThumbnailUrl();

  /**
   * Set a thumbnail URL for the media item.
   * @param url the thumbnail URL of the MediaItem
   */
  void setThumbnailUrl(String url);

  /**
   * Get the album which the media item belongs to.
   * @return the album id.
   */
  String getAlbumId();

  /**
   * Set the album id which the media item belongs to.
   * @param albumId the album id
   */
  void setAlbumId(String albumId);

  /**
   * Get the creation time
   * @return creation time associated with the media item in UTC.
   */
  String getCreated();

  /**
   * Set the creation time
   * @param created creation time associated with the media item in UTC.
   */
  void setCreated(String created);

  /**
   * Get the description of the media item
   * @return description
   */
  String getDescription();

  /**
   * Set the description of the media item
   * @param description
   */
  void setDescription(String description);

  /**
   * Get the playtime length in seconds of the MediaItem
   * @return playtime
   */
  String getDuration();

  /**
   * Set the playtime length in seconds of the MediaItem
   * @param duration
   */
  void setDuration(String duration);

  /**
   * Get the MediaItem's file size
   * @return fileSize
   */
  String getFileSize();

  /**
   * Set the number of bytes for the MediaItem
   * @param fileSize
   */
  void setFileSize(String fileSize);

  /**
   * Get the MediaItem's id 
   * @return id
   */
  String getId();

  /**
   * Set the MediaItem's id
   * @param id
   */
  void setId(String id);

  /**
   * Get the language associated with the media item in ISO 639-3 format
   * @return
   */
  String getLanguage();

  /**
   * Set the language associated with the media item in ISO 639-3 format
   * @param language
   */
  void setLanguage(String language);

  /**
   * Get the update time associated with the media item
   * @return lastUpdated
   */
  String getLastUpdated();

  /**
   * Set the update time associated with the media item 
   * @param lastUpdated
   */
  void setLastUpdated(String lastUpdated);

  /**
   * Get the location corresponding to the media item
   * @return location
   */
  Address getLocation();

  /**
   * Set the location corresponding to the media item
   * @param location
   */
  void setLocation(Address location);


  /**
   * Get the number of comments on the media item
   * @return numComments
   */
  String getNumComments();

  /**
   * Set the number of comments on the media item
   * @param numComments
   */
  void setNumComments(String numComments);

  /**
   * Get the number of views for the media item
   * @return numViews
   */
  String getNumViews();

  /**
   * Set the number of views for the media item
   * @param numViews
   */
  void setNumViews(String numViews);


  /**
   * Get the number of votes received for voting.
   * @return numVotes
   */
  String getNumVotes();

  /**
   * Set the number of votes received for voting.
   * @param numVotes
   */
  void setNumVotes(String numVotes);

  /**
   * Get the average rating of the media item on a scale of 0-10
   * @return rating
   */
  String getRating();

  /**
   * Set the average rating of the media item on a scale of 0-10
   * @param rating
   */
  void setRating(String rating);

  /**
   * Get the time when the content is available.
   * @return startTime
   */
  String getStartTime();

  /**
   * Set the the time when the content is available.
   * @param startTime
   */
  void setStartTime(String startTime);

  /**
   * Get people tagged in the media item.
   * @return taggedPeople
   */
  String getTaggedPeople();

  /**
   * Set people tagged in the media item.
   * @param taggedPeople
   */
  void setTaggedPeople(String taggedPeople);


  /**
   * Get tags associated with this media item.
   * @return tags
   */
  String getTags();

  /**
   * Set tags associated with this media item.
   * @param tags
   */
  void setTags(String tags);

  /**
   * Get the title for this media item
   * @return title
   */
  String getTitle();

  /**
   * Set the title for this media item
   * @param title
   */
  void setTitle(String title);
}

package com.liferay.portlet.atlassian.jira.model;

import java.io.Serializable;

public class Version implements Serializable {
    public static Version convert(String value) {
        int index = value.indexOf(",");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid issue type text: " +
                value);
        }
        return new Version(value.substring(0, index),
                           value.substring(index + 1, value.length()));
    }

    public Version(String versionId, String versionName) {
        _versionId = versionId;
        _versionName = versionName;
    }

    public String getText() {
        return _versionId + "," + _versionName;
    }


    public String getVersionName() {
        return _versionName;
    }

    public void setVersionName(String versionName) {
        this._versionName = versionName;

    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Version version = (Version) o;

        if (_versionId != null ? !_versionId.equals(version._versionId)
            : version._versionId != null) {
            return false;
        }
        if (_versionName != null ? !_versionName.equals(version._versionName)
            : version._versionName != null) {
            return false;
        }

        return true;
    }

    public String toString() {
        return "Version{" + "versionName='" + _versionName + '\''
            + ", versionId='" + _versionId + '\'' + '}';
    }

    public int hashCode() {
        int result;
        result = (_versionName != null ? _versionName.hashCode() : 0);
        result = 31 * result + (_versionId != null ? _versionId.hashCode() : 0);
        return result;
    }

    public String getVersionId() {
        return _versionId;
    }

    public void setVersionId(String versionId) {
        this._versionId = versionId;
    }

    private String _versionId;
    private String _versionName;
}

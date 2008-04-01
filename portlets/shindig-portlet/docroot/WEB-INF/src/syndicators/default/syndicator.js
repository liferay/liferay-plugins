/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Default syndicator configuration. Rather than replacing this
// file, you should create your own syndicator.js file and
// load it directly by modifying the value of web.xml.
// All configurations will automatically inherit values from this
// config, so you only need to provide configuration for items
// that you require explicit special casing for.

// Please namespace your attributes using the same conventions
// as you would for javascript objects, e.g. gadgets.features
// rather than "features".

// NOTE: Please _don't_ leave trailing commas because the php json parser
// errors out on this.

// Syndicator must be an array; this allows multiple syndicators
// to share configuration.
{"gadgets.syndicator" : ["default"],

// Set of regular expressions to validate the parent parameter. This is
// necessary to support situations where you want a single syndicator to support
// multiple possible host names (such as for localized domains, such as
// <language>.example.org. If left as null, the parent parameter will be
// ignored; otherwise, any requests that do not include a parent
// value matching this set will return a 404 error.
"gadgets.parent" : null,

// This config data will be passed down to javascript. Please
// configure your object using the feature name rather than
// the javascript name.

// Only configuration for required features will be used.
// See individual feature.xml files for configuration details.
"gadgets.features" : {
  "core.io" : {
  	// Note: /proxy is an open proxy. Be careful how you explose this!
    "proxyUrl" : "proxy?url=%url%",
    "jsonProxyUrl" : "proxy?output=js"
  },
  "views" : {
    "profile" : {
      "isOnlyVisible" : false,
      "aliases": ["DASHBOARD", "default"]
    },
    "canvas" : {
      "isOnlyVisible" : true,
      "aliases" : ["FULL_PAGE"]
    }
  },
  "rpc" : {
  	// Path to the relay file. Automatically appended to the parent
  	// parameter if it passes input validation and is not null.
    // This should never be on the same host in a production environment!
    // Only use this for TESTING!
    "parentRelayUrl" : "/shindig-portlet/files/container/rpc_relay.html",

    // If true, this will use the legacy ifpc wire format when making rpc
    // requests.
    "useLegacyProtocol" : false
  },
  // Skin defaults
  "skins" : {
    "properties" : {
         "BG_COLOR": "",
         "BG_IMAGE": "",
         "BG_POSITION": "",
         "BG_REPEAT": "",
         "FONT_COLOR": "",
         "ANCHOR_COLOR": ""
    }
  },
  "opensocial-0.7" : {
    // Path to fetch opensocial data from
    // Must be on the same domain as the gadget rendering server
    "path" : "/shindig-portlet/socialdata",
    "domain" : "shindig",
    "enableCaja" : true,
    "supportedFields" : {
       "person" : ["id", "name", "thumbnailUrl", "profileUrl"],
       "activity" : ["id", "title"]
    }
  }

}}

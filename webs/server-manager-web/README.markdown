# Server Manager User Guide

## Introduction

The Server Manager allows managing an application server running Liferay remotely. It follows a RESTful URL pattern, and specific functionality is documented below.

## Authentication

BASIC authentication is used for security, and you must authenticate before using any of the functionality of this plugin. The user must be a valid liferay Omni Admin.

## Request

Each url has a request type which is the type of the HTTP request (GET, POST, UPDATE, or DELETE), and arguments as specified. Square brackets '[]' denote optional arguments, and dollar signs '$' denote variable arguments. 

### Global Request Parameters

These parameters can be set on any GET request and are passed as GET parameters in the URL with ?name=value. If multiple values are possible they will be separated by a '|' and the first one will be the default value.

 - `format=json|raw` This specifies the format of the response. The default is a JSON response with an output-stream, error-stream, and success flag. If raw is set, however, the output-stream is returned as a string, without its JSON wrapping.


### Monitoring Methods

#### status

GET <http://localhost/server-manager-web/status>

 - **Description:**       Successful if the server is up and running, otherwise this request fails.
 - **Response:**          None. This operation will use the success field of the JSON response. The server is alive if the operation is successful, otherwise not.
 - **Supported Servers:** All (hypothetically)


### Plugin Methods

#### deploy

POST <http://localhost/server-manager-web/plugins[/$context]>

 - **Description:** Deploys the war file attached to the body of this POST request to _$context_ if specified. If _$context_ is not specified, the context will be based on the file name.
 - **Response:**    None
 - **Supported Servers:** All (hypothetically)


PUT <http://localhost/server-manager-web/plugins/$context>

 - **Description:** Updates the deployed _$context_ with the files from the delta war that is attached to the body of this PUT request.
 - **Response:**    None
 - **Supported Servers:** All (hypothetically)


#### list

GET <http://localhost/server-manager-web/plugins>

 - **Description:** Returns a list of contexts of all the installed plugins.
 - **Response:**    A JSON array of all the installed plugins. For example:

		[
			 "1-2-1-columns-layouttpl",
			 "beautiful-day-theme",
			 "hr-portlet","server-manager-web",
			 "sample-wrapper-hook",
			 "vldap-web"
		]
 - **Supported Servers:** All (hypothetically)

GET <http://localhost/server-manager-web/plugins/$context>

 - **Description:** Gives information about the plugin installed at context _$context_. This includes whether or not it is installed (true if this is a valid context), whether or not it is started, and the type of plugin, etc.
 - **Response:**    JSON containing the following information.

		{
			"installed": true,
			"started": true,
			"types": ["theme"]
		}
 - **Supported Servers:** All (hypothetically)


#### undeploy

DELETE <http://localhost/server-manager-web/$context>

 - **Description:** Undeploys _$context_.
 - **Response:**    None
 - **Supported Servers:** All (hypothetically)


### Server Methods

#### debug-port

GET <http://localhost/server-manager-web/server/debug-port>

 - **Description:**       Provides the debug port of the application server.
 - **Response:**          A number denoting the debug port of the application server.
 - **Supported Servers:** JBoss, Tomcat


#### log

GET <http://localhost/server-manager-web/server/log/output[/$offset]>

 - **Description:**       Returns Liferay's log since midnight starting at the optional _$offset_ of the file. If _$offset_ is not specified then we start at the beginning of the file. _$offset_ is a number. 
 - **Response:**          The log for Liferay as described above.
 - **Supported Servers:** JBoss, Tomcat (_Note: for JBoss this method gives the combined output of Liferay and the app server for everything after the server boots._)


GET <http://localhost/server-manager-web/server/log/error[/$offset]>

 - **Description:**       Returns the application server's log since midnight starting at the optional _$offset_ of the file. If _$offset_ is not specified then we start at the beginning of the file. _$offset_ is a number. 
 - **Response:**          The log for the application server as described above.
 - **Supported Servers:** JBoss, Tomcat (_Note: for JBoss this method gives the output of the boot process._)


## Response

The response for any request will be a JSON string containing three fields:

 - output-stream: This will contain the requested data, if any. For example it could contain the log or the debug port. The Response field in the above section will be placed into this field unless otherwise specified. If a response is not specified above, then this field will be empty.
 - error-stream: This will contain an error message or a stack trace if any error occurred during the request.
 - success: One of the following success codes for the operation.
    - 0 for success, everything finished with no problems
    - 1 something went wrong, the operation may have completed successfully anyway, but it may not have.

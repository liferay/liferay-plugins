/*

        Copyright 2006-2009 OpenAjax Alliance

        Licensed under the Apache License, Version 2.0 (the "License"); 
        you may not use this file except in compliance with the License. 
        You may obtain a copy of the License at
        
                http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software 
        distributed under the License is distributed on an "AS IS" BASIS, 
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
        See the License for the specific language governing permissions and 
        limitations under the License.
*/

/**
 * Create a new Inline Container.
 * @constructor
 * @extends OpenAjax.hub.Container
 *
 * InlineContainer implements the Container interface to provide a container
 * that places components within the same browser frame as the main mashup
 * application. As such, this container does not isolate client components into
 * secure sandboxes.
 * 
 * @param {OpenAjax.hub.ManagedHub} hub
 *    Managed Hub instance to which this Container belongs
 * @param {String} clientID
 *    A string ID that identifies a particular client of a Managed Hub. Unique
 *    within the context of the ManagedHub.
 * @param {Object} params  
 *    Parameters used to instantiate the InlineContainer.
 *    Once the constructor is called, the params object belongs exclusively to
 *    the InlineContainer. The caller MUST not modify it.
 *    The following are the pre-defined properties on params:
 * @param {Function} params.Container.onSecurityAlert
 *    Called when an attempted security breach is thwarted.  Function is defined
 *    as follows:  function(container, securityAlert)
 * @param {Function} [params.Container.onConnect]
 *    Called when the client connects to the Managed Hub.  Function is defined
 *    as follows:  function(container)
 * @param {Function} [params.Container.onDisconnect]
 *    Called when the client disconnects from the Managed Hub.  Function is
 *    defined as follows:  function(container)
 * @param {Object} [params.Container.scope]
 *    Whenever one of the Container's callback functions is called, references
 *    to "this" in the callback will refer to the scope object. If no scope is
 *    provided, default is window.
 * @param {Function} [params.Container.log]
 *    Optional logger function. Would be used to log to console.log or
 *    equivalent. 
 *
 * @throws {OpenAjax.hub.Error.BadParameters}   if required params are not
 *    present or null
 * @throws {OpenAjax.hub.Error.Duplicate}   if a Container with this clientID
 *    already exists in the given Managed Hub
 * @throws {OpenAjax.hub.Error.Disconnected}   if ManagedHub is not connected
 */
OpenAjax.hub.InlineContainer = function( hub, clientID, params )
{
    if ( ! hub || ! clientID || ! params ||
            ! params.Container || ! params.Container.onSecurityAlert ) {
        throw new Error(OpenAjax.hub.Error.BadParameters);
    }
    
    var cbScope = params.Container.scope || window;
    var connected = false;
    var subs = [];
    var subIndex = 0;
    var client = null;
    
    if ( params.Container.log ) {
        var log = function( msg ) {
            try {
                params.Container.log.call( cbScope, "InlineContainer::" + clientID + ": " + msg );
            } catch( e ) {
                OpenAjax.hub._debugger();
            }
        };
    } else {
        log = function() {};
    }
    
    this._init = function() {
        hub.addContainer( this );
    };

  /*** OpenAjax.hub.Container interface implementation ***/
    
    this.getHub = function() {
    	return hub;
    };
    
    this.sendToClient = function( topic, data, subscriptionID ) {
        if ( connected ) {
            var sub = subs[ subscriptionID ];
            try {
                sub.cb.call( sub.sc, topic, data, sub.d );
            } catch( e ) {
                OpenAjax.hub._debugger();
                client._log( "caught error from onData callback to HubClient.subscribe(): " + e.message );
            }
        }
    };
    
    this.remove = function() {
        if ( connected ) {
            finishDisconnect();
        }
    };
    
    this.isConnected = function() {
        return connected;
    };
    
    this.getClientID = function() {
        return clientID;
    };
    
    this.getPartnerOrigin = function() {
        if ( connected ) {
            return window.location.protocol + "//" + window.location.hostname;
        }
        return null;
    };
    
    this.getParameters = function() {
        return params;
    };
    
  /*** OpenAjax.hub.HubClient interface implementation ***/
    
    this.connect = function( hubClient, onComplete, scope ) {
        if ( connected ) {
            throw new Error( OpenAjax.hub.Error.Duplicate );
        }
        
        connected = true;
        client = hubClient;
        
        if ( params.Container.onConnect ) {
            try {
                params.Container.onConnect.call( cbScope, this );
            } catch( e ) {
                OpenAjax.hub._debugger();
                log( "caught error from onConnect callback to constructor: " + e.message );
            }
        }
        
        invokeOnComplete( onComplete, scope, hubClient, true );
    };
    
    this.disconnect = function( hubClient, onComplete, scope ) {
        if ( ! connected ) {
            throw new Error( OpenAjax.hub.Error.Disconnected );
        }
        
        finishDisconnect();
    
        if ( params.Container.onDisconnect ) {
            try {
                params.Container.onDisconnect.call( cbScope, this );
            } catch( e ) {
                OpenAjax.hub._debugger();
                log( "caught error from onDisconnect callback to constructor: " + e.message );
            }
        }
        
        invokeOnComplete( onComplete, scope, hubClient, true );
    };
    
  /*** OpenAjax.hub.Hub interface implementation ***/
    
    this.subscribe = function( topic, onData, scope, onComplete, subscriberData ) {
        assertConn();
        assertSubTopic( topic );
        if ( ! onData ) {
            throw new Error( OpenAjax.hub.Error.BadParameters );
        }
        
        var subID = "" + subIndex++;
        var success = false;
        var msg = null;
        try {
            var handle = hub.subscribeForClient( this, topic, subID );
            success = true;
        } catch( e ) {
            // failure
            subID = null;
            msg = e.message;
        }
        
        scope = scope || window;
        if ( success ) {
            subs[ subID ] = { h: handle, cb: onData, sc: scope, d: subscriberData };
        }
        
        invokeOnComplete( onComplete, scope, subID, success, msg );
        return subID;
    };
    
    this.publish = function( topic, data ) {
        assertConn();
        assertPubTopic( topic );
        hub.publishForClient( this, topic, data );
    };
    
    this.unsubscribe = function( subscriptionID, onComplete, scope ) {
        assertConn();
        if ( typeof subscriptionID === "undefined" || subscriptionID === null ) {
            throw new Error( OpenAjax.hub.Error.BadParameters );
        }
        var sub = subs[ subscriptionID ];
        if ( ! sub ) { 
            throw new Error( OpenAjax.hub.Error.NoSubscription );
        }    
        hub.unsubscribeForClient( this, sub.h );
        delete subs[ subscriptionID ];
        
        invokeOnComplete( onComplete, scope, subscriptionID, true );
    };
    
    this.getSubscriberData = function( subID ) {
        assertConn();
        return getSubscription( subID ).d;
    };
    
    this.getSubscriberScope = function( subID ) {
        assertConn();
        return getSubscription( subID ).sc;
    };
    
  /*** PRIVATE FUNCTIONS ***/
    
    function invokeOnComplete( func, scope, item, success, errorCode ) {
        if ( func ) { // onComplete is optional
            try {
                scope = scope || window;
                func.call( scope, item, success, errorCode );
            } catch( e ) {
                OpenAjax.hub._debugger();
                // invokeOnComplete is only called for client interfaces (Hub and HubClient)
                client._log( "caught error from onComplete callback: " + e.message );
            }
        }
    }
    
    function finishDisconnect() {
        for ( var subID in subs ) {
            hub.unsubscribeForClient( this, subs[subID].h );
        }
        subs = [];
        subIndex = 0;
        connected = false;
    }
    
    function assertConn() {
        if ( ! connected ) {
            throw new Error( OpenAjax.hub.Error.Disconnected );
        }
    }
    
    function assertPubTopic( topic ) {
        if ((topic == null) || (topic === "") || (topic.indexOf("*") != -1) ||
            (topic.indexOf("..") != -1) ||  (topic.charAt(0) == ".") ||
            (topic.charAt(topic.length-1) == "."))
        {
            throw new Error(OpenAjax.hub.Error.BadParameters);
        }
    }
    
    function assertSubTopic( topic ) {
        if ( ! topic ) {
            throw new Error(OpenAjax.hub.Error.BadParameters);
        }
        var path = topic.split(".");
        var len = path.length;
        for (var i = 0; i < len; i++) {
            var p = path[i];
            if ((p === "") ||
               ((p.indexOf("*") != -1) && (p != "*") && (p != "**"))) {
                throw new Error(OpenAjax.hub.Error.BadParameters);
            }
            if ((p == "**") && (i < len - 1)) {
                throw new Error(OpenAjax.hub.Error.BadParameters);
            }
        }
    }
    
    function getSubscription( subID ) {
        var sub = subs[ subID ];
        if ( sub ) {
            return sub;
        }
        throw new Error( OpenAjax.hub.Error.NoSubscription );
    }
    
    
    this._init();
};

////////////////////////////////////////////////////////////////////////////////

/**
 * Create a new InlineHubClient.
 * @constructor
 * @extends OpenAjax.hub.HubClient
 * 
 * @param {Object} params 
 *    Parameters used to instantiate the HubClient.
 *    Once the constructor is called, the params object belongs to the
 *    HubClient. The caller MUST not modify it.
 *    The following are the pre-defined properties on params:
 * @param {Function} params.HubClient.onSecurityAlert
 *     Called when an attempted security breach is thwarted
 * @param {Object} [params.HubClient.scope]
 *     Whenever one of the HubClient's callback functions is called,
 *     references to "this" in the callback will refer to the scope object.
 *     If not provided, the default is window.
 * @param {Function} [params.HubClient.log]
 *     Optional logger function. Would be used to log to console.log or
 *     equivalent. 
 * @param {OpenAjax.hub.InlineContainer} params.InlineHubClient.container
 *     Specifies the InlineContainer to which this HubClient will connect
 *  
 * @throws {OpenAjax.hub.Error.BadParameters} if any of the required
 *     parameters are missing
 */
OpenAjax.hub.InlineHubClient = function( params )
{
    if ( ! params || ! params.HubClient || ! params.HubClient.onSecurityAlert ||
            ! params.InlineHubClient || ! params.InlineHubClient.container ) {
        throw new Error(OpenAjax.hub.Error.BadParameters);
    }
    
    var container = params.InlineHubClient.container;
    var scope = params.HubClient.scope || window;
    
    if ( params.HubClient.log ) {
        var log = function( msg ) {
            try {
                params.HubClient.log.call( scope, "InlineHubClient::" + container.getClientID() + ": " + msg );
            } catch( e ) {
                OpenAjax.hub._debugger();
            }
        };
    } else {
        log = function() {};
    }
    this._log = log;

  /*** OpenAjax.hub.HubClient interface implementation ***/
    
    /**
     * Requests a connection to the ManagedHub, via the InlineContainer
     * associated with this InlineHubClient.
     * 
     * If the Container accepts the connection request, this HubClient's 
     * state is set to CONNECTED and the HubClient invokes the 
     * onComplete callback function.
     * 
     * If the Container refuses the connection request, the HubClient
     * invokes the onComplete callback function with an error code. 
     * The error code might, for example, indicate that the Container 
     * is being destroyed.
     * 
     * If the HubClient is already connected, calling connect will cause
     * the HubClient to immediately invoke the onComplete callback with
     * the error code OpenAjax.hub.Error.Duplicate.
     * 
     * @param {Function} [onComplete]
     *     Callback function to call when this operation completes.
     * @param {Object} [scope]  
     *     When the onComplete function is invoked, the JavaScript "this"
     *     keyword refers to this scope object.
     *     If no scope is provided, default is window.
     *    
     * In this implementation of InlineHubClient, this function operates 
     * SYNCHRONOUSLY, so the onComplete callback function is invoked before 
     * this connect function returns. Developers are cautioned that in  
     * IframeHubClient implementations, this is not the case.
     * 
     * A client application may call InlineHubClient.disconnect and then call
     * InlineHubClient.connect to reconnect to the Managed Hub.
     */
    this.connect = function( onComplete, scope ) {
        container.connect( this, onComplete, scope );
    };
    
    /**
     * Disconnect from the ManagedHub
     * 
     * Disconnect immediately:
     * 
     * 1. Sets the HubClient's state to DISCONNECTED.
     * 2. Causes the HubClient to send a Disconnect request to the 
     * 		associated Container. 
     * 3. Ensures that the client application will receive no more
     * 		onData or onComplete callbacks associated with this 
     * 		connection, except for the disconnect function's own
     * 		onComplete callback.
     * 4. Automatically destroys all of the HubClient's subscriptions.
     * 	
     * @param {Function} [onComplete]
     *     Callback function to call when this operation completes.
     * @param {Object} [scope]  
     *     When the onComplete function is invoked, the JavaScript "this"
     *     keyword refers to the scope object.
     *     If no scope is provided, default is window.
     *    
     * In this implementation of InlineHubClient, the disconnect function operates 
     * SYNCHRONOUSLY, so the onComplete callback function is invoked before 
     * this function returns. Developers are cautioned that in IframeHubClient 
     * implementations, this is not the case.   
     * 
     * A client application is allowed to call HubClient.disconnect and 
     * then call HubClient.connect in order to reconnect.
     */
    this.disconnect = function( onComplete, scope ) {
        container.disconnect( this, onComplete, scope );
    };
    
    this.getPartnerOrigin = function() {
        return container.getPartnerOrigin();
    };
    
    this.getClientID = function() {
        return container.getClientID();
    };
    
  /*** OpenAjax.hub.Hub interface implementation ***/
    
    /**
     * Subscribe to a topic.
     *
     * @param {String} topic
     *     A valid topic string. MAY include wildcards.
     * @param {Function} onData   
     *     Callback function that is invoked whenever an event is 
     *     published on the topic
     * @param {Object} [scope]
     *     When onData callback or onComplete callback is invoked,
     *     the JavaScript "this" keyword refers to this scope object.
     *     If no scope is provided, default is window.
     * @param {Function} [onComplete]
     *     Invoked to tell the client application whether the 
     *     subscribe operation succeeded or failed. 
     * @param {*} [subscriberData]
     *     Client application provides this data, which is handed
     *     back to the client application in the subscriberData
     *     parameter of the onData and onComplete callback functions.
     * 
     * @returns subscriptionID
     *     Identifier representing the subscription. This identifier is an 
     *     arbitrary ID string that is unique within this Hub instance
     * @type {String}
     * 
     * @throws {OpenAjax.hub.Error.Disconnected} if this Hub instance is not in CONNECTED state
     * @throws {OpenAjax.hub.Error.BadParameters} if the topic is invalid (e.g. contains an empty token)
     *
     * In this implementation of InlineHubClient, the subscribe function operates 
     * Thus, onComplete is invoked before this function returns. Developers are 
     * cautioned that in most implementations of HubClient, onComplete is invoked 
     * after this function returns.
     * 
     * If unsubscribe is called before subscribe completes, the subscription is 
     * immediately terminated, and onComplete is never invoked.
     */
    this.subscribe = function( topic, onData, scope, onComplete, subscriberData ) {
        return container.subscribe( topic, onData, scope, onComplete, subscriberData );
    };
    
    /**
     * Publish an event on 'topic' with the given data.
     *
     * @param {String} topic
     *     A valid topic string. MUST NOT include wildcards.
     * @param {*} data
     *     Valid publishable data. To be portable across different
     *     Container implementations, this value SHOULD be serializable
     *     as JSON.
     *     
     * @throws {OpenAjax.hub.Error.Disconnected} if this Hub instance 
     *     is not in CONNECTED state
     * 
     * In this implementation, publish operates SYNCHRONOUSLY. 
     * Data will be delivered to subscribers after this function returns.
     * In most implementations, publish operates synchronously, 
     * delivering its data to the clients before this function returns.
     */
    this.publish = function( topic, data ) {
        container.publish( topic, data );
    };
    
    /**
     * Unsubscribe from a subscription
     *
     * @param {String} subscriptionID
     *     A subscriptionID returned by InlineHubClient.prototype.subscribe()
     * @param {Function} [onComplete]
     *     Callback function invoked when unsubscribe completes
     * @param {Object} [scope]
     *     When onComplete callback function is invoked, the JavaScript "this"
     *     keyword refers to this scope object.
     *     
     * @throws {OpenAjax.hub.Error.NoSubscription} if no such subscription is found
     * 
     * To facilitate cleanup, it is possible to call unsubscribe even 
     * when the HubClient is in a DISCONNECTED state.
     * 
     * In this implementation of HubClient, this function operates SYNCHRONOUSLY. 
     * Thus, onComplete is invoked before this function returns. Developers are 
     * cautioned that in most implementations of HubClient, onComplete is invoked 
     * after this function returns.
     */
    this.unsubscribe = function( subscriptionID, onComplete, scope ) {
        container.unsubscribe( subscriptionID, onComplete, scope );
    };
    
    this.isConnected = function() {
        return container.isConnected();
    };
    
    this.getScope = function() {
        return scope;
    };
    
    this.getSubscriberData = function( subID ) {
        return container.getSubscriberData( subID );
    };
    
    this.getSubscriberScope = function( subID ) {
        return container.getSubscriberScope( subID );
    };
    
    /**
     * Returns the params object associated with this Hub instance.
     * Allows mix-in code to access parameters passed into constructor that created
     * this Hub instance.
     *
     * @returns params  the params object associated with this Hub instance
     * @type {Object}
     */
    this.getParameters = function() {
        return params;
    };
};
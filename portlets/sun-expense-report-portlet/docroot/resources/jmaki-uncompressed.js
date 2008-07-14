var _globalScope = window;

function Jmaki() {
    
    var _jmaki = this;    
    this.version = '1.0.1';
    this.debugGlue = false;
    this.verboseDebug = false;
    this.debug = false;
    var widgets = [];
    this.loaded = false;
    this.initialized = false;
    this.webRoot = "";
    this.resourcesRoot = "resources";
    this.extensions = [];
    this.inspectDepth = 2;
    var timers = [];
    
    var _doc = document;
    var _counter = 0;
    this.MSIE  = /MSIE/i.test(navigator.userAgent);
    
    
    /**  Map is a general map object for storing key value pairs
      * 
      *  @param mixin - default set of properties
      *
      */
    this.Map = function(mixin) {
 
        var map;
        if (typeof mixin == 'undefined') map = {};
        else map = mixin;
        
        /**
         * Get a list of the keys to check
         */
        this.keys = function() {
            var o = {};
            var _keys = [];
            
            for (var _i in map){
                // make sure we don't return prototype properties.
                if (typeof o[_i] == 'undefined') _keys.push(_i);
            }
            return _keys;
        };
        /**
         * Put stores the value in the table
         * @param key the index in the table where the value will be stored
         * @param value the value to be stored 
         */
        this.put = function(key,value) {
            map[key] = value;
        };
        
        /**
         * Return the value stored in the table
         * @param key the index of the value to retrieve
         */
        this.get = function(key) {
            return map[key];
        };
        
        /**
         * Remove the value from the table
         * @param key the index of the value to be removed
         */
        this.remove =  function(key) {
            delete map[key];
        };
        /**
         *  Clear the table
         */
        this.clear = function() {
            delete map;
            map = {};
        };
    };
    
    /**
     *  Generate a unqiue id
     */
    this.genId = function() {
        return "jmk_" + _counter++;    
    };
    
    /**
     * Utility function to see if a variable is defined.
     */
    function isDefined(_target) {
        return (typeof _target != "undefined");
    }    
    
    // default locale - fallback if all fails
    this.defaultLocale = "jmaki-en-us";
    
    // default locale for messages
    this.locale = "jmaki-en-us";
    // localized messages    
    
    this.defaultMessages = {
        "request_timeout" : "Request for {0} timed out.",
        "publish_function_not_found" : "Publish Error : function {0} not found on object {1}",
        "publish_object_not_found" : "Publish Error :  Object not found: {0}",
        "publish_match" : "<span style='color:green'>Publish Match :</span> : Topic: {0} listener {1}",
        "publish" : "<span style='color:red'>Publish </span> : Topic: {0} message {1}",
        "subscribe_handler_required" : "Subscribe Error : Handler required for subscriber {0}.",
        "subscribe_topic_required" : "Subscribe Error : topic or topicRegExp required for {0}.",
        "ajax_url_required" : "doAjax error: url required.",
        "ajax_error" : "jMaki.doAjax Error: {0}",        
        "ajax_request_open_error" : "doAjax error making request: {0}",
        "ajax_send_body_error" : "doAjax error sending body of request to {0}.",
        "ajax_server_error" : "doAjax error communicating with {0}. Server returned status code {1}.",
        "write_dynamic_script_error" : "Attempt to write a script that can not be dynamically load widget with  id {0}. Consider using the widget in an iframe.",
        "widget_constructor_not_found" : "Unable to find widget constructor for {0}.",
        "extension_constructor_not_found" : "Unable to find extension constructor for {0}.",
        "widget_instantiation_error" : "Unable to create an instance of {0}. Enable logging for more details.",
        "unknown" : "unknown",
        "widget_error" : "<span>Error loading {0} : id={1}<br>Script: {2} (line: {3}).<br>Message: {4}</span>",
        "jmaki_logger" : "jMaki Logger",
        "clear" : "Clear",
        "x_close" : "[X]",
        "clear_logger" : "Clear Logger",
        "hide_logger" : "Hide Logger",
        "more" : "[more]",
        "jmaki_version" : "jMaki Version : {0}",
        "unable_to_load_url" : "Unable to load URL {0}."   
    };
    
    // Localized messages 
    // where the first set is a set of languages
    this.messages = new this.Map();
    
    /**
     * Add a set of localzied messages
     *
     * @param locale - locale for the messages
     * @messages - object literal of messsages in key value pairs
     *
     */
    this.addMessages = function(locale, messages) {
        _jmaki.messages.put(locale, new _jmaki.Map(messages));
    };
    
    // This map is intialized with the default languages and messages
    _jmaki.addMessages(_jmaki.defaultLocale,_jmaki.defaultMessages);
    
    /**
     * Get a localized message for the given id.
     *
     * @param id - The message id
     * @param args - If provided these will be used to format the message
     *
     * @return null if the message could not be found
     *
     * Messages will be searched for jmaki.locale messages and then
     * the jmaki.defaultLocale messages.
     *
     */
    this.getMessage = function(id,args) {
        var message = null;
        if (_jmaki.messages.get(_jmaki.locale) &&
            _jmaki.messages.get(_jmaki.locale).get(id)) {
            message = _jmaki.messages.get(_jmaki.locale).get(id);
        // fallback
        } else if (_jmaki.messages.get(_jmaki.defaultLocale) &&
            _jmaki.messages.get(_jmaki.defaultLocale).get(id)) {
            message = _jmaki.messages.get(_jmaki.defaultLocale).get(id);
        }
        if (isDefined(args)) {
            return _jmaki.messageFormat(message,args);    
        }
        return message;        
    };
    
    this.messageFormat = function(message, args) {
        for (var i=0; isDefined(message) && isDefined(args) && i < args.length; i++) {
            var rex = new RegExp("\\{" + i + "\\}", "g");
            message =  message.replace(rex, args[i]);
        }
        return message;
    };
        
    this.attributes = new this.Map();	
    var topics = new this.Map();
 
    function getElement(id) {
        return _doc.getElementById(id);
    }
   
    /**
     *  Unsubscribe a listener
     *  @param listener 
     */
    this.unsubscribe = function(_lis) { 
        for (var _l=0; _jmaki.subs && _l < _jmaki.subs.length;_l++ ) {
            if (_jmaki.subs[_l].id  == _lis.id) {
                _jmaki.subs.splice(_l,1);
                break;
            }
        }
    };  
	
	function matchWildcard(pattern,topic) {
		
		var patpos = 0;
		var patlen = pattern.length;
		var strpos = 0;
		var strlen = topic.length;

		var i=0;
		var star = false;

		while (strpos+i<strlen) {
			if (patpos+i<patlen) {
				switch (pattern.charAt(patpos + i)) {
				case '?': {
					i++;
					continue;
				}
				case '*':
					star = true;
					strpos += i;
					patpos += i;
					do {
						++patpos;
						if (patpos == patlen)
							return true;
					} while (pattern.charAt(patpos) == '*');
					i=0;
					continue;
				}
				if (topic.charAt(strpos + i) != pattern.charAt(patpos + i)) {
					if (!star) return false;
					strpos++;
					i=0;
					continue;
				}
				i++;
			} else {
				if (!star) return false;
				strpos++;
				i=0;
			}
		}
		do {
			if (patpos + i == patlen) return true;
		} while(pattern.charAt(patpos + i++)=='*');
		return false;
	}
	
	
    /**
     *  Publish an event to a topic
     *  @param name of the topic to be published to
     *  @param args any object
     */
    this.publish = function(name, args, bubbleDown, bubbleUp) {
        if (!isDefined(name) || !isDefined(args)) return;  
        if (_jmaki.debugGlue) {
            _jmaki.log(jmaki.getMessage("publish",  [name ,_jmaki.inspect(args)]));
        }

        // check the glue for listeners
        if (_jmaki.subs){
            for (var _l=0; _l < _jmaki.subs.length;_l++ ) {
                var _listener = _jmaki.subs[_l];
                     if ((_listener.topic instanceof RegExp &&
_listener.topic.test(name)) 
                   || _listener.topic == name   
                   || (typeof _listener.topic.charAt == 'function' &&
matchWildcard(_listener.topic,name))
                   ) {     
 
                    // set the topic on payload
                    args.topic = name;
                    if (_jmaki.debugGlue) {
                    	var _vname = name;
	                if (_listener.topicString) _vname = _listener.topicString;   
                    	_jmaki.log(jmaki.getMessage("publish_match", [_vname, _listener]));                    
                    }
                    if (_listener.action == 'call' && _listener.target) {
                        // get the top level object                   
                        var _obj;
                        var myo = 'undefined';
                        if (_listener.target.functionName) {						
                            _obj = _jmaki.findObject(_listener.target.object);  									                           
                            // create an instance of the object if needed.
                            if (typeof _obj == 'function') {
                                myo = new _obj;
                            } else if (_obj) {
                                myo = _obj;
                            } else {
                              _jmaki.log(_jmaki.getMessage("publish_function_not_found",
                                   [_listener.target.functionName,_listener.target.object]));
                            }					
                            if (isDefined(myo) &&
                                typeof myo[_listener.target.functionName] == 'function'){
                                myo[_listener.target.functionName].call(_globalScope,args);
                            } else {
                                   _jmaki.log(_jmaki.getMessage("publish_object_not_found",
                                        [_listener.target.functionName,_listener.target.object]));
                            }                        
                        } else if (_listener.target.functionHandler) {
                            _listener.target.functionHandler.call(_globalScope,args);
                        }
                    }
                } else if (_jmaki.subs[_l].action == 'forward') {
                    var _topics = _jmaki.subs[_l].topics;
                    // now multiplex the event
                    for (var ti = 0; ti < _topics.length; ti++){
                        // don't cause a recursive loop if the topic is this one
                        if (_topics[ti] != name) {
                            _jmaki.publish(_topics[ti], args);
                        }
                    }
                }
            }
        }
        // publish to subframes with a global context appended
        var bd = true;
        if (isDefined(bubbleDown)) bd = bubbleDown;
      
        if ( bd && window.frames.length > 0){
            for (var i=0; i < window.frames.length; i++){
              if (window.frames[i].jmaki){
                  window.frames[i].jmaki.publish("/global" + name, args, true, false);
              }
            }
        }
        //  publish to parent frame if we are a sub-frame. This will prevent duplicate events
        if (window != window.top){
            var bu = true;
            if (isDefined(bubbleUp)) bu = bubbleUp;
              if (bu && window.parent.jmaki){
                  window.parent.jmaki.publish("/global" + name, args, false, true);
            }
        }        
    };

    /**
     * Load a set of libraries in order
     */
    this.addLibraries = function(_libs, _cb, _inprocess, _cleanup) {
        if (_libs.length <= 0) {
            if (typeof _cb == 'function') {
                _cb();
                return;
            }
        }
        if (!isDefined(_inprocess)) {
            _inprocess = new _jmaki.Map();
        }
        var _uuid = new Date().getMilliseconds();
        var _lib = _libs[_libs.length-1];
        var _s_uuid = "c_script_" + _libs.length + "_" + _uuid;
        var head = _doc.getElementsByTagName("head")[0];
        var e = _doc.createElement("script");
        e.start = _uuid;
        e.id =  _s_uuid;
        head.appendChild(e);
        var se = getElement(_s_uuid);
        _inprocess.put(_s_uuid,_lib);
        var loadHandler = function (_id) {
            var t = getElement(_id);
            if (t && t.timeoutHandler) {
               clearInterval(t.timeoutHandler.interval);
               delete t.timeoutHandler;
            }
            var _s = getElement(_id);
            // remove the script node
            if (_s  && !(isDefined(_cleanup) && _cleanup == false)) _s.parentNode.removeChild(_s);
            _inprocess.remove(_id);
            var _cbk = _cb;
            if (_libs.length-1 > 0) {
                _libs.pop();
                _jmaki.addLibraries(_libs, _cb,_inprocess, _cleanup);
             /**  rather than check length check for inprocess **/
            } 
            if (_inprocess.keys().length == 0) {
                if (isDefined(_cb)){
                    var timout = 0;
                    delete _inprocess;
                    setTimeout(function(){_cbk();}, 0);
                }
            }
        };
        if (_jmaki.MSIE) {
            se.onreadystatechange = function () {
                if (this.readyState == 'loaded') {
                    var _id = _s_uuid;
                    loadHandler(_id);
                }
            }; 
            getElement(_s_uuid).src = _lib;
        } else {   
            if (se.addEventListener) {
               // force a load using a timer if all else fails
               var loader = function(_id) {
                 var _c = 0;
                 var self = this;
                 this.interval = setInterval(function() {
                 if (_c>2){
                   clearInterval(self.interval);
                   loadHandler(_id);
                 } else {
                   _c++;
                 }
                   }, 250);
              };
              se.timeoutHandler = new loader(_s_uuid);
              se.addEventListener("load", function(){var _id = _s_uuid;
              loadHandler(_id)}, true);
            }
            setTimeout(function(){getElement(_s_uuid).src = _lib;}, 0);
        }
        se = null;
        head = null;
    };

    /**
     *  Get the XMLHttpRequest object
     *
     */
    this.getXHR = function () {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    };
    
    function handleAjaxError(_m, _r, args){
       if (args.onerror) {
             args.onerror(_m,_r);
           } else {
         _jmaki.log(_jmaki.getMessage("ajax_error", [_m]));
       } 
    }
    
    /**
    * Generalized XMLHttpRequest which can be used from evaluated code. Evaluated code is not allowed to make calls.
    * @param args is an object literal containing configuration parameters including method[get| post, get is default], body[bodycontent for a post], asynchronous[true is default]
    */

   this.doAjax= function(args) {
       if (typeof args == 'undefined' || !args.url) {
           _jmaki.log(_jmaki.getMessage("ajax_url_required"));
           return;
       }
      
       var _req =  this.getXHR();
      
       var method = "GET";
       var async = true;
       var callback;
       var _c = false;
       if (args.timeout) {
           setTimeout(function(){            
             if (_c == false) {
               _c = true;
               if (_req.abort) _req.abort();            
               handleAjaxError(_jmaki.getMessage("request_timeout", [args.url]), _req, args);
               return;
              }
           }, args.timeout);
       }
       
       if  (isDefined(args.asynchronous)) {
            async=args.asynchronous;
       }
       if (args.method) {
            method=args.method;
       }
       if (typeof args.callback == 'function') {
           callback = args.callback;
       }
       var body = null;
       if (args.body) {
           body = args.body;
       } else if (args.content) {
           body = "";
           for (var l in args.content) {
               body = body +  l + "=" + encodeURIComponent(args.content[l]) + "&";
           }
       }
       if (async == true && _c == false) {
       	   _req.onreadystatechange = function() {
           if (_req.readyState ==4 && _c == false) {         
               _c = true;
               if ((_req.status == 200 || _req.status ==0) && callback) {
                callback(_req);
               } else if (_req.status != 200){
                   _c = true;
                   handleAjaxError(_jmaki.getMessage("ajax_server_error", [args.url, _req.status]), _req, args);
               }
            return;
           }
       	 }
       }
       try {
          if (!_c)_req.open(method, args.url, async);
       } catch(e) {
         _c = true;
         handleAjaxError(_jmaki.getMessage("ajax_request_open_error", [args.url]),_req, args);
         return;
       }
       // add headers
       if (args.headers && args.headers.length > 0) {
           for (var _h=0;_h < args.headers.length; _h++) {
               _req.setRequestHeader(args.headers[_h].name, args.headers[_h].value);
           }
       }
       // customize the method
       if (args.method) {
            method=args.method;
            if (method.toLowerCase() == 'post') {
               if (!args.contentType) _req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            }
       }
       if (args.contentType) {
           _req.setRequestHeader("Content-Type", args.contentType);
       }
       try {
         if (_c == false) _req.send(body);         
       } catch(e) {
         _c = true;
         handleAjaxError(_jmaki.getMessage("ajax_send_body_error", [args.url]), _req, args);
         return;
       }       
       if (_c == false && async == false) {
           _c = true;
           if (_req.status ==200) {
                if (callback) callback(_req);
           } else {
               _c = true;
               handleAjaxError(_jmaki.getMessage("ajax_server_error", [args.url,_req.status]), _req, args);
           }
           return;
       }
    };
        
    /**
     *  Library name is added as a script element which will be loaded when the page is rendered
     *  @param lib library to add 
     */
    this.addLibrary = function(lib, cb) {
      var libs = [];
      libs.push(lib);
      return _jmaki.addLibraries(libs, cb);
    };
    
    /**
     * Register widget with jMaki 
     * @param widget Object respresenting the widget
     */
    this.addWidget = function(widget) {
        widgets.push(widget);
        if (this.loaded){this.loadWidget(widget);}
    };
 
     /**
     * Register widget with jMaki 
     * @param widget Object respresenting the widget
     */
    this.addExtension = function(ext) {
        _jmaki.extensions.push(ext);
    };
    
    /**
     * Bootstrap or load all registered widgets
     */
    this.bootstrapWidgets = function() {
        _jmaki.loaded = true;
        for (var l=0; l < widgets.length; l++) {
            _jmaki.loadWidget(widgets[l]);
        }
    };
    
    /**
     * Bootstrap or load all registered extensions
     */
    this.loadExtensions = function() {
        for (var l=0; l < _jmaki.extensions.length; l++) {
            _jmaki.loadExtension(_jmaki.extensions[l]);
        }
    };
  
   /**
     * Checks wheter a script has been loaded yet
     */
    this.writeScript = function(_s, _id) {
        if (_jmaki.loaded == true) {
            if (getElement(_id)) {
                getElement(_id).innerHTML = _jmaki.getMessage("write_dynamic_script_error", [_id]);
            }
        } else {
            _doc.write("<script src='" + _s + "'></script>");
        }
    };
   
    /**
     * Loads the style sheet by adding a link element to the DOM 
     * @param target name of style sheet to load 
     */
    this.loadStyle = function(target) {
        var styleElement = _doc.createElement("link");
        styleElement.type = "text/css";
        styleElement.rel="stylesheet";
        if (target[0] == '/') target = _jmaki.webRoot + target;
        styleElement.href = target;
        if (_doc.getElementsByTagName('head').length == 0) {
            var headN = _doc.createElement("head");
            _doc.documentElement.insertBefore(headN, _doc.documentElement.firstChild);
        }
        _doc.getElementsByTagName('head')[0].appendChild(styleElement);
    };
    
    /**
     * Replace style class
     * @param root root of the oldStyle classes
     * @param oldStyle name of class or classes to replace
     * @param targetStyle name of new class or classes to use 
     */
    this.replaceStyleClass = function (root, oldStyle, targetStyle) {
        var elements = this.getElementsByStyle(oldStyle,root);
        for (var i=0; i < elements.length; i++) {
            // Handle cases where there are multiple classnames
            if (elements[i].className.indexOf(' ') != -1) {
                var classNames = elements[i].className.split(' ');
                for (var ci in classNames) {
                    if (classNames[ci] == oldStyle) {
                        classNames[ci] = targetStyle;
                    }
                }
                // now reset the styles with the replaced values
                elements[i].className = classNames.join(' ');
            } else  if (elements[i].className == oldStyle) {
                elements[i].className = targetStyle;
            }
        }
    };
    
    /**
    * Find a set of child nodes that contain the className specified
    * @param className is the targetClassName you are looking for
    * @param root  An optional root node to start searching from. The entire document will be searched if not specfied.
    *
    */
    this.getElementsByStyle = function(className, root){
        var elements = [];
        if (isDefined(root)) {
            var rootNode = root;
            if (typeof root == 'string') {
                rootNode = getElement(root);
            }    
            elements = this.getAllChildren(rootNode, []);
        } else {
            elements = (_doc.all) ? _doc.all : _doc.getElementsByTagName("*");
        }
	var found = [];
	for (var i=0; i < elements.length; i++) {
	// Handle cases where there are multiple classnames
            if (elements[i].className.indexOf(' ') != -1) {
                var cn = elements[i].className.split(' ');
                for (var ci =0; ci < cn.length; ci++) {
                    if (cn[ci] == className) {
                        found.push(elements[i]);
                    }
                }
            } else  if (elements[i].className == className) {
                found.push(elements[i]);
            }
        }
        return found;
    };
    
    /**
     * Utility Function to get children
     * @param element for which to get the children
     */
    this.getAllChildren = function(target, children) {
        var _nc = target.childNodes;
        for (var l=0; _nc && l <  _nc.length; l++) {
            if (_nc[l].nodeType == 1) {
                children.push(_nc[l]);
                if (_nc[l].childNodes.length > 0) {
                    this.getAllChildren(_nc[l], children);
                }
            }
        }
        return children;
    };
    
    /**
     * Load extension
     * @param extension Object representing widget to load
     */
    this.loadExtension = function(_ext) {
        var targetName ="jmaki.extensions." + _ext.name + ".Extension";    
        var con = _jmaki.findObject(targetName);
        if (typeof con != "function") {
            _jmaki.log(_jmaki.getMessage("extension_constructor_not_found", [targetName]));
        } else {
          var ex = new con(_ext);
          if (ex.postLoad) ex.postLoad.call(_globalScope);
        }
    };
    
    
    /**
     * Load a widget
     * @param widget Object representing widget to load
     */
    this.loadWidget = function(_jmw) {
        // see if the widget has been defined.
        if (_jmaki.attributes.get(_jmw.uuid) != null) {
            return;
        }
        var targetName ="jmaki.widgets." + _jmw.name + ".Widget";     
        var con = _jmaki.findObject(targetName);       
        if (typeof con != "function") {
            logError(_jmaki.getMessage("widget_constructor_not_found", [targetName]), getElement(_jmw.uuid));
            return;
        }
        var wimpl;
        // bind the value using a @{foo.obj} notation       
        if ((typeof _jmw.value == 'string') && _jmw.value.indexOf("@{") == 0) {      
            var _vw = /[^@{].*[^}]/.exec(_jmw.value);
            _jmw.value = _jmaki.findObject(new String(_vw));
        }
        // do not wrap IE with exception handler
        // because we cant' get the right line number
        var _uuid = _jmw.uuid;      
        if (_jmaki.MSIE) {
            var _uuid = _jmw.uuid;
            var oldError = null;
            if (window.onerror) {
                oldError = window.onerror;
            }
            var eh = function(message, url, line) {
                var _puuid = _uuid;
                logWidgetError(targetName, _puuid,url, line, message, getElement(_puuid));
            };
            window.onerror = eh;
            wimpl = new con(_jmw);
            window.onerror = null;
            if (oldError) {
                window.onerror = oldError;
            }              
        } else if (typeof con == 'function'){
          try {
                wimpl = new con(_jmw);
           } catch (e){
                var line = _jmaki.getMessage("unknown");
                var description = null;
                if (e.lineNumber) line = e.lineNumber;
                if (e.message) description = e.message;
 
                if (_jmaki.debug) {
                    logWidgetError(targetName, _jmw.uuid,_jmw.script, line, description , getElement(_jmw.uuid));
                    return;
                }
            }
        }
        if (typeof wimpl == 'object') {
            _jmaki.attributes.put(_jmw.uuid, wimpl);           
            if (wimpl.postLoad) wimpl.postLoad.call(_globalScope);
            // map in any subscribe handlers.
            if (_jmw.subscribe && _jmw.subscribe.push) { //string also have length property
                for (var _wi = 0; _wi < _jmw.subscribe.length; _wi++) {
                    var _t = _jmw.subscribe[_wi].topic;
                    var _m = _jmw.subscribe[_wi].handler;
                    var _h = null;
                    if (typeof _m == 'string' && _m.indexOf("@{") == 0) {
                         var _hw = /[^@{].*[^}]/.exec(_m);
                        _h = _jmaki.findObject(new String(_hw));
                    } else if (wimpl[_m]) {
                        _h = wimpl[_m];
                    }       
                    if (_h != null) _jmaki.subscribe(_jmw.subscribe[_wi].topic,_h);
                }
            }
            _jmaki.publish("/jmaki/runtime/widget/loaded", { id : _jmw.uuid});
        } else { 
            logError(_jmaki.getMessage("widget_instantiation_error",[targetName]), getElement(_jmw.uuid ));
        }
    };
    
    function logWidgetError(name,uuid, url, line, _m, div) {
        var message= _jmaki.getMessage("widget_error", [name, uuid, url, line, _m]);
        logError(message, div);
    }
 
    function logError(message, div) {
        if (div == null || !isDefined(div.className)) {
            div = _doc.createElement("div");
            _doc.body.appendChild(div);
        }
        div.className = "";
        div.style.color = "red";
        div.innerHTML = message;
    }
    
    /**
     * An easy way to get a instance of a widget.
     * returns null if their is not a widget with the id.
     */
    this.getWidget = function(id) {
        return _jmaki.attributes.get(id);
    };
    
    /**
     * destroy all registered widgets under the target node
     * @param _root - The _root to start at. All widgets will be removed if not specified.
     */
    this.clearWidgets = function(_root) {

        if (!isDefined(_root)) {
            var _k = _jmaki.attributes.keys();
            for (var l=0; l < _k.length; l++) {
                _jmaki.removeWidget(_k[l]);            
            }
            _jmaki.loaded = false;
            widgets = [];
        } else {
           var _ws = _jmaki.getAllChildren(_root,[]);            
           for (var l=0; l < _ws.length; l++) {         
                if (_ws[l].id) _jmaki.removeWidget(_ws[l].id);          
            }
        }
    };
    
    this.removeWidget = function(_wid) {
        var _w = _jmaki.getWidget(_wid);   
        if (_w) {          
            if ( typeof _w.destroy == 'function') {
                _w.destroy();
            }
            var _p = getElement(_wid);         
            _p.parentNode.removeChild(_p);
        }
        _jmaki.attributes.remove(_wid);   
    };

    this.inspect = function(_o, _depth) {
        if (!isDefined(_depth)){
            _depth =0;
        } else  if (_depth >= _jmaki.inspectDepth) {
            return _o;
        } else {
            _depth++;
        }

        var _rs = [];
        if (!isDefined(_o))return 'undefined';
        if (_o instanceof Array) {
            for (var i=0; i < _o.length; i++) {
                _rs.push(i + " : " + _jmaki.inspect(_o[i],_depth));
            }
            return "[" +  _rs.join(" , ") + "]";     
        } else if (typeof _o == "string") {
           return "'" + _o + "'";
        } else if (typeof _o == "number" || 
            typeof _o == "boolean") {
           return _o;           
        } else if (typeof _o == "object") {
            for (var _oi in _o) {
                try {
                if (_oi != 'toString')_rs.push(_oi  + " : " + _jmaki.inspect(_o[_oi ], _depth));
                } catch(e){}
            }
            if (_rs.length >0) return "{" + _rs.join(" , ") + "}";
            else return "{}";
        } else return _o;
    };
    
    /*
     * Add a glue listener programatcially. following is an example.
     *
     *{topic : "/dojo/fisheye",action: "call", target: { object: "jmaki.dynamicfaces",functionName: "fishEyeValueUpdate"}}
     *   or 
     * @param l as topic and 
     * @param t as the target object path ending with a function 
     */
    this.subscribe = function(l, t) {    
        if (!isDefined(l)) return;
        // handle key word arguments
        var lis;
        if (typeof l == 'object' && !(l instanceof RegExp)) {          
            if (l.topic) l.topic = _jmaki.trim(l.topic);
            if (l.topicRegExp) l.topic = new RegExp(l.topicRegExp);
            lis = l;
        // function binding
        } else if (typeof t == 'string'){
          lis = {};     
          if (l.topicRegExp) lis.topic = new RegExp(l.topicRegExp);
          else lis.topic = l;
          lis.target = {};
          var _is = t.split('.');
          lis.action = "call";
          lis.target.functionName = _is.pop();
          lis.target.object = _is.join('.');
        // inline function
        } else if (typeof t == 'function') {
          lis = {};
          if (l.topicRegExp) lis.topic =  new RegExp(l.topicRegExp);
          else lis.topic = l;          
          lis.target = {};
          lis.action = "call";
          lis.target.functionHandler = t;
        } else {
          _jmaki.log(jmaki.getMessage("subscribe_handler_required", [l]));
        }
        if (isDefined(lis)){
            if (!isDefined(_jmaki.subs))_jmaki.subs = [];            
            if (!lis.id) lis.id = _jmaki.genId();
            if (lis.topic){
                lis.toString = function() { return _jmaki.inspect(this)};
                _jmaki.subs.push(lis);     
            } else {
                _jmaki.log(jmaki.getMessage("subscribe_topic_required", [l]));
                return null;
            }
            return lis;
        }
        return null;
    };
    
    this.trim = function(t) {
        return  t.replace(/^\s+|\s+$/g, "");
    };
        
    /*
     * @param _src is the source object
     * @param _par is the class to extend
     */
    this.extend = function(_src, _par) {
        _src.prototype = new _par();
        _src.prototype.constructor = _src;
        _src.superclass = _par.prototype;
        for (i in _src.prototype) {
            _src[i] = _src.prototype[i];
        }
    };
    
    this.hideLogger = function() {
      var ld = getElement("jmakiLogger");
      if (ld)ld.style.visibility = 'hidden';
    };
    
    this.clearLogger = function() {
      var b = getElement("jmakiLoggerContent");
      if (b) b.innerHTML = "";
    };
    
    this.log = function(text, level) {
     //   alert("logging t=" + text);
        // cached messages until after the page has been created
        if (!_jmaki.initialized) {          
            if (!_jmaki._messages) _jmaki._messages = [];
            _jmaki._messages.push({ text : text, level : level});
            return;
        }
        if (!_jmaki.debug ) return;
        var ld = getElement("jmakiLogger");
        var b = getElement("jmakiLoggerContent");        
        if (!ld){      	
            ld = _doc.createElement("div");
            ld.id = 'jmakiLogger';
            ld.style.border = "1px solid #000000";
            ld.style.fontSize = "12px";
            ld.style.position  = "absolute";
            ld.style.zIndex  = "999";
            ld.style.bottom = "0px";
            ld.style.background = "#FFFF00";
            ld.style.right ="0px";
            ld.style.width = "500px";
            ld.style.height = "200px";
            
            var tb = "<div  style='height: 14px; background : black; color : white; font-size : 10px'>" +
                     "<div style='float:left;width:450px;text-align:center'>" +
                     _jmaki.getMessage("jmaki_logger") + 
                     "</div><div style='right:0px,text-align:left'><a href='javascript:jmaki.clearLogger()' title='" +
                     _jmaki.getMessage("clear_logger") +
                     "' style='color:white;text-decoration:none'>[" +
                     _jmaki.getMessage("clear") +
                     "]</a> <a href='javascript:jmaki.hideLogger()' title='" +
                     _jmaki.getMessage("hide_logger") +
                      "' style='color:white;text-decoration:none'>" + 
                      _jmaki.getMessage("x_close") + 
                      "</a></div></div>";
            var tbE = _doc.createElement("div");
            tbE.innerHTML = tb;
            ld.appendChild(tbE);
            b = _doc.createElement("div");
            b.id ='jmakiLoggerContent';
            b.style.height = "186px";
            b.style.overflowY = "auto";
            ld.appendChild(b);
            if (_doc.body) {
               _doc.body.appendChild(ld);
            }

        }
        if (ld && _jmaki.loaded)ld.style.visibility = 'visible' ;
        var lm = _doc.createElement("div");
        lm.style.clear = "both";
        if (text && text.length > 125 && _jmaki.verboseDebug == false) {
            var lid = _jmaki.genId();           
            var tn = _doc.createElement("div");
            tn.innerHTML = "<div style='float:left;width:435px;height:12px;overflow:hidden'>" + text.substring(0,135) + "</div><div style='float:left'>...&nbsp;</div><a id='" + lid + "_href' href=\"javascript:jmaki.showLogMessage(\'" + lid +  "\')\" style='text-decoration: none'><span id='" + lid + "_link'>" + _jmaki.getMessage("more") + "</span></a>";
            var mn = _doc.createElement("div");
            mn.id = lid;
            mn.innerHTML = text;    
            mn.style.margin = "5px";
            mn.style.background = "#FF9900";
            mn.style.display = "none";
            lm.appendChild(tn);
            lm.appendChild(mn);
        } else lm.innerHTML =  text;    
        if (b)b.appendChild(lm);
    };
    
    this.showLogMessage = function(id) {
        var n = getElement(id);
        if (n && n.style){
            n.style.display = "block";
            var h = getElement(id + "_href");
            h.href = "javascript:jmaki.hideLogMessage('" + id + "')";
            var l = getElement(id + "_link");
            l.innerHTML = "&nbsp;" + _jmaki.getMessage("x_close");
        }   
    };

    this.hideLogMessage = function(id) {
        var n = getElement(id);
        if (n && n.style){
            n.style.display = "none";
            var h = getElement(id + "_href");
            h.href = "javascript:jmaki.showLogMessage('" + id + "')";
            var l = getElement(id + "_link");
            l.innerHTML = _jmaki.getMessage("more");            
        }      
    };   
    
    /**
     * Initialize jMaki by loading the config.json
     *  Write in the glue by loading dependencies and
     *  Register listeners.
     */
    this.initialize = function() {     
        if (!_jmaki.config)_jmaki.config = {};
    
        _jmaki.doAjax({ url : this.webRoot + this.resourcesRoot + "/config.json",
             asynchronous : false,
             timeout : 3000,
             onerror : function() { /* do nothing and continue*/},
             callback :  function(req) {
                if (req.responseText != '') {
                    var obj = eval('(' + req.responseText + ')');
                    if (obj.config) {
                        if (obj.config.theme)_jmaki.config.theme = obj.config.theme;
                        if (obj.config.logLevel) {
                            switch (obj.config.logLevel) {
                                case 'debug' : {
                                    _jmaki.debug = true;
                                    _jmaki.log(_jmaki.getMessage("jmaki_version",[_jmaki.version]));
                                    break;                                    
                                }
                                case 'all' : {
                                    _jmaki.debug = true;
                                    _jmaki.debugGlue = true;
                                    break;
                                }
                                case  'off' : {
                                    _jmaki.debug = false;
                                    break;
                                }
                            }
                        }                    
                        // write out the dependent libraries so we have access
                        if (obj.config.glue.timers) {  
                            _jmaki.addTimers(obj.config.glue.timers);
                        }
                        if (obj.config.gluelisteners){
                            for (var gl=0; gl < obj.config.glue.listeners.length;gl++) {
                                  _jmaki.subscribe (obj.config.glue.listeners[gl]); 
                            }
                        }
                    }
                }
        }
        });
        postInitialize();
    };
    
    /**
     * Create a namespace with the given string
     */
    this.namespace = function(_path, target) {
        // get the top level object
        var paths = _path.split('.');
        var _obj = _globalScope[paths[0]];
        if (!isDefined(_obj)) _globalScope[paths[0]] = _obj = {};
        for (var ii = 1; ii < paths.length; ii++) {
            if (isDefined(_obj[paths[ii]])) {
                _obj = _obj[paths[ii]];                                       
            } else {
                _obj[paths[ii]] = {};
                _obj = _obj[paths[ii]];
            }
        }
        // if object provided it becomes the last in the chain
        if (typeof target == 'object') {
            _obj = target;
        }
        return _obj;
    };
    
    this.findObject = function(_path) {
        var paths = _path.split('.');
        var found = false;		
        var _obj = _globalScope[paths[0]];
		if (_obj && paths.length == 1) found = true;	
        if (isDefined(_obj)){
            for (var ii =1; ii < paths.length; ii++) {
                var _lp = paths[ii];
                if (_lp.indexOf('()') != -1){                  
                  var _ns = _lp.split('()');
                  if (typeof _obj[_ns[0]] == 'function'){
                      var _fn = _obj[_ns[0]];              
                      return _fn.call(_globalScope);
                  }
                }     
                if (isDefined(_obj[_lp])) {
                    _obj = _obj[_lp];                                       
                    found = true;
                } else {
                    found = false;
                    break;
                }
            }
            if (found) {
                return _obj;
            }
        }
        return null;
    };
    
    this.Timer = function(args, isCall) {
        var _src = this;
        this.args = args;
        var _target;
        
        this.processTopic = function() {
            for (var ti = 0; ti < args.topics.length; ti++){
                _jmaki.publish(args.topics[ti], {topic: args.topics[ti],
                type:'timer',
                src:_src,
                timeout: args.to});
            }
        };
        
        this.processCall = function() {
            if (!_target) {
             var  _obj = _jmaki.findObject(args.on);
                if (typeof _obj == 'function'){
                    _target = new _obj();
                } else if (typeof _obj == 'object'){
                    _target = _obj;
                }
            }
            if ((_target && typeof _target == 'object')) {
              if(typeof _target[args.fn] == 'function') {
                _target[args.fn]({type:'timer', src:_src, timeout: args.to});
              }
            }
        };
        
        this.run = function() {
            if (isCall) {
                _src.processCall();
            } else {
                _src.processTopic();
            }
            _globalScope.setTimeout(_src.run,args.to);
        };
    };
    
    this.addTimer = function(_timer){
        var timers = [];
        timers.push(_timer);
        this.addTimers(timers);
    };
    
    this.addTimers = function(_timers){
        if (isDefined(_timers)){
            for (var _l=0; _l <_timers.length;_l++ ) {
                // create a wrapper and add the timer
                var _timer = _timers[_l];              
                if (_timer.action == 'call' &&
                isDefined(_timer.target) &&
                isDefined(_timer.target.object) &&
                isDefined(_timer.target.functionName) &&
                isDefined(_timer.timeout)) {
                    // create the timer
                    var args = {on: _timer.target.object,
                    fn: _timer.target.functionName,
                    to: _timer.timeout
                    };
                    var timer = new _jmaki.Timer(args,true);
                    timers.push(timer);
                    timer.run();
                } else if (_timers[_l].action == 'publish') {
                    var args = {topics: _timers[_l].topics,
                    to: _timer.timeout
                    };
                    var timer = new _jmaki.Timer(args,false);
                    timers.push(timer);
                    timer.run();
                }
            }            
        }
    };
    
    function postInitialize() {
        if (_jmaki.initialized) return;
        else _jmaki.initialized = true;
        // log any messages that might be queued up during pre-init
        if (_jmaki._messages) {
            for (var i=0; i < _jmaki._messages.length; i++) {
                var _m = _jmaki._messages[i];
                _jmaki.log(_m.text, _m.level);
            }
        }
        _jmaki.publish("/jmaki/runtime/intialized", {});
        _jmaki.loadExtensions();
        _jmaki.publish("/jmaki/runtime/extensionsLoaded", {});
        _jmaki.bootstrapWidgets();

        _jmaki.publish("/jmaki/runtime/widgetsLoaded", {});
        // load the theme       
        if ( _jmaki.config && _jmaki.config.theme) {
            var theme = _jmaki.config.theme;
            if (!/(^http)/i.test(theme)) theme = _jmaki.webRoot + theme;             
            _jmaki.loadStyle(theme);
        }
        _jmaki.publish("/jmaki/runtime/loadComplete", {});
    }
    /**
     *  All for a filter to be applied to a dataset
     *  @param input - An object you wish to filter
     *  @param a string representing the path to the object or
     *    a funciton reference to procress the input
     */
    this.filter = function(input, filter){
        if (typeof filter == 'string') {
            var h = _jmaki.findObject(filter);
            return h.call(_globalScope,input);
        } else if (typeof filter == 'function'){
            return filter.call(_globalScope, input);
        }
    };
   
    /**
     *  Find the postion of an Element
     *
     */ 
     this.getPosition = function(_e){
        var pX = 0;
        var pY = 0;
        if(_e.offsetParent) {
            while(true){
                pY += _e.offsetTop;
                pX += _e.offsetLeft;
                if(_e.offsetParent == null){
                    break;
                }
                _e = _e.offsetParent;
            }
        } else if(_e.y) {
                pY += _e.y;
                pX += _e.x;
        }
        return {x: pX, y: pY};
    };

  this.DContainer = function(args){    
        var _self = this;
        var uuid;
        var _container;
       
        if (typeof args.target == 'string') {
            uuid = args.target;
            _container = getElement(uuid);
        } else {
            uuid = args.target.id;
            _container = args.target;      
        }

        var oldWidth;
        this.url = null;
        var autoSizeH = false;
        var autoSizeW = false;
        
        if (args.autosize) {
            autoSizeH = true;
            autoSizeW = true;
        }
        
        // default sizes are all based on the width of the container   
        var VIEWPORT_WIDTH;
        var VIEWPORT_HEIGHT;
        
        this.loadURL = function(_url){
            if (_url.message) _url = _url.message;            
            if (typeof _url == 'string') { 
                _self.url = _url; 
            } else if (_url.url) {               
                _self.url = _url.url;
            } else if (_url.value) {
                _self.url = _url.value;
            }
            if (args.useIframe) {
                // wait for the iframe if it hasn't loaded
                if (!_self.iframe) {
                    var _t = setInterval(function() {
                        if (getElement(uuid + "_iframe")) {
                            clearInterval(_t);               
                            _self.iframe = getElement(uuid + "_iframe");
                            _self.iframe.src =  _self.url;
                            init();
                        }
                    }, 5);
              } else {
                _self.iframe.src = _self.url;
                }
            } else {                 
                _jmaki.injector.inject({url: _self.url, injectionPoint: _container});
            }
        };

        this.resize = function() {         
            if (autoSizeH || autoSizeW){
                if (!_container.parentNode) return;
                var pos = _jmaki.getPosition(_container);
                if (_container.parentNode.nodeName == "BODY") {
                    if (window.innerHeight){
                        VIEWPORT_HEIGHT = window.innerHeight - pos.y ;
                        VIEWPORT_WIDTH = window.innerWidth - 20;                 
                    } else {
                        var _tNode = _container.parentNode;
                        while(_tNode != null &&
                        (_tNode.clientHeight == 0 ||
                        !isDefined(_tNode.clientWidth))) {
                            _tNode = _tNode.parentNode;
                        }
                        if (_tNode == null) {
                            
                            VIEWPORT_WIDTH = 400;
                        } else {                     
                            VIEWPORT_WIDTH = _tNode.clientWidth -20;
                            VIEWPORT_HEIGHT = _tNode.clientHeight - pos.y;
                        }
                    }
                } else {                  
                    var _tNode = _container.parentNode;
                    while(_tNode != null &&
                    (_tNode.clientHeight == 0 ||
                    !isDefined(_tNode.clientWidth))) {
                        _tNode = _tNode.parentNode;
                    }
                    if (_tNode == null) {
                        VIEWPORT_WIDTH = 400;
                    } else {
                        VIEWPORT_WIDTH = _tNode.clientWidth;
                        VIEWPORT_HEIGHT = _tNode.clientHeight;
                    }
                }         
                if (autoSizeH) {                  
                    if (VIEWPORT_HEIGHT < 0) VIEWPORT_HEIGHT = 320;
                    _container.style.height = VIEWPORT_HEIGHT + "px";
                }
                if (autoSizeW) {
                    _container.style.width = VIEWPORT_WIDTH + "px";
                }
            } else {
                _container.style.width = VIEWPORT_WIDTH + "px";
                _container.style.height = VIEWPORT_HEIGHT + "px";          
            }
            if (VIEWPORT_HEIGHT < 0) {
                VIEWPORT_HEIGHT = 320;
            }
            if (VIEWPORT_WIDTH < 0) {
                VIEWPORT_WIDTH = 500;
            }
            
            if (args.useIframe) {         
                if (_self.iframe) {                  
                    _self.iframe.style.height = VIEWPORT_HEIGHT -2 + "px";
                    _self.iframe.style.width = VIEWPORT_WIDTH -2 + "px";
                }
            }
            // used for tracking with IE
            oldWidth = _doc.body.clientWidth;           
        };

        this.setContent = function(_c) {
            var _con;
            if (_c.message)_c = _c.message;
            if (_c.value) _con = _c.value;
            else _con = _c;
            if (!_self.iframe)_container.innerHTML = _con;
        }; 
        
        function init() {
            if (window.attachEvent) {
                window.attachEvent('onresize', layout);
            } else if (window.addEventListener) {
                window.addEventListener('resize', layout, true);
            }
            var _ot = _container;
            if (_self.iframe) {
                _ot = _self.iframe;           
            }                
            if (args.overflow) _ot.style.overflow = args.overflow;
            if (args.overflowX) _ot.style.overflowX = args.overflowX;
            if (args.overflowY) _ot.style.overflowY = args.overflowY;     
     
            if (args.startWidth) {
                VIEWPORT_WIDTH = Number(args.startWidth);
                _container.style.width = VIEWPORT_WIDTH + "px";
            } else {
                VIEWPORT_WIDTH = _container.clientWidth;
                autoSizeW = true;
            }
            
            if (args.startHeight) {
                VIEWPORT_HEIGHT = Number(args.startHeight);
            } else {
                VIEWPORT_HEIGHT = _container.clientHeight;
                autoSizeH = true;
            }
            if (VIEWPORT_HEIGHT <= 0) VIEWPORT_HEIGHT = 320;
            _container.style.height = VIEWPORT_HEIGHT + "px";
            if (args.useIFrame &&  _self.iframe) {
                _self.iframe.style.height = VIEWPORT_HEIGHT + "px";
            }
            _self.resize();
            if (args.url && !args.useIframe) {
                _self.loadURL(args.url);
            } else if (args.content && !_self.iframe) {            
                _container.innerHTML = args.content;
            }
        }
                
        var resizing = false;
        var lastSize = 0;
        
        function layout() {
            if (!_jmaki.MSIE) {
                _self.resize();
                return;
            }
            // special handling for ie resizing.
            // we wait for no change for a full second before resizing.
            if (oldWidth != _doc.body.clientWidth && !resizing) {
                if (!resizing) {
                    resizing = true;
                    setTimeout(layout,500);
                }
            } else if (resizing && _doc.body.clientWidth == lastSize) {
                resizing = false;
                _self.resize();
            } else if (resizing) {
                lastSize = _doc.body.clientWidth;
                setTimeout(layout, 500);
            }
        }
        
        if (args.useIframe && args.useIframe == true) {
            var srcString = "";
            if (args.url) srcString = "src='" + args.url + "'";
            var content = "";
            if (args.content) content = args.content;

            // use this technique as creating the iframe programmatically does not allow us to turn the border off
            var iframeTemplate = "<iframe id='" + uuid + "_iframe' " + srcString + " frameborder=0 scrolling=" + ((args.overflow == 'hidden') ? 'NO' : 'YES') + "></iframe>";
            _container.innerHTML = iframeTemplate;
            // wait for the iframe
            var _t = setInterval(function() {
                if (getElement(uuid + "_iframe")) {
                    clearInterval(_t);               
                    _self.iframe = getElement(uuid + "_iframe");
                    setTimeout(function(){init();},0);
                }
            }, 5);
        } else init();
    };
    
    this.destroy = function() {
        if (window.attachEvent) {
            window.dettachEvent('onresize', layout);
        } else if (window.addEventListener) {
            window.removeEventListener('resize', layout, true);
        } 
    }; 
   
    
  this.Injector = function() {
 
  var _uuid = new Date().getMilliseconds();
  var _injector = this;
  var _processing = false;

  var styles = [];
  
  var tasks = [];
  
  this.inject = function(task) {
   // make sure jmaki creates a list of libraries it can not load
    if (tasks.length == 0 && !_processing) {
        inject(task);
    } else {
        tasks.push(task);
    }
  };

  /**
   * 
   * Load template text aloing with an associated script
   * 
   * Argument p properties are as follows:
   *
   * url :              Not required but used if you want to get the template from
   *                    something other than the injection serlvet. For example if
   *                    you want to load content directly from a a JSP or HTML file.
   * 
   * p.injectionPoint:  Not required. This is the id of an element into. If this is
   *                    not specfied a div will be created under the root node of
   *                    the document and the template will be injected into it.
   *                    Content is injected by setting the innerHTML property
   *                    of an element to the template text.
   */
  function inject(task) {
      _processing = true;
      _jmaki.doAjax({
            method:"GET",
            url: task.url,
            asynchronous: false,
            callback: function(req){
                   getContent(req.responseText, task);               
               //if no parent is given append to the document root   
               var injectionPoint;
               if (typeof task.injectionPoint == 'string') {
                   injectionPoint = getElement(task.injectionPoint);
                   // wait for the injection point
                   if (!getElement(task.injectionPoint)) {
                       var _t = setInterval(function() {
                           if (getElement(task.injectionPoint)) {
                               clearInterval(_t);
                               injectionPoint = getElement(task.injectionPoint);
                               setTimeout(function(){processTask(injectionPoint,task);},0);                      
                           }
                       }, 25);
                   } else {
                       processTask(injectionPoint, task);             
                   }
                } else {
                    processTask(task.injectionPoint, task);
                }
         },
         onerror : function(){
            var ip = task.injectionPoint;
            if (typeof task.injectionPoint == 'string') {
                ip = getElement(task.injectionPoint);
            }
            _jmaki.clearWidgets(ip);
            ip.innerHTML = _jmaki.getMessage("unable_to_load_url", [task.url]);
            processNextTask();
         }

       });
  };
  
  function processTask(injectionPoint, task) {     
      _jmaki.clearWidgets(injectionPoint);
      var _id = "injector_" + _uuid;
      var data = task.content + "<div id='" + _id + "'></div>";
      injectionPoint.innerHTML = data;
      // wait for the content to be loaded
      var _t = setInterval(function() {
          if (getElement(_id)) {
              clearInterval(_t);
              try {
                  _injector.loadScripts(task,processNextTask);
              } catch (e) {
                  injectionPoint.innerHTML = "<span style='color:red'>" + e.message + "</span>";
              }
          }
      }, 25);
  }
  
  // pass in a reference to the task
  // start the next task
  function processNextTask() {
      if (tasks.length >0) {
          var _t = tasks.shift();
          inject(_t);
      };
      _processing = false;
  }
  

  /**
   * 
   * Load template text aloing with an associated script
   * 
   * Argument p properties are as follows:
   *
   * url :              Not required but used if you want to get the template from
   *                    something other than the injection serlvet. For example if
   *                    you want to load content directly from a a JSP, JSF call, PHP, or HTML file.
   */
  this.get = function (p) {
      var _data;
       _jmaki.doAjax({
            method:"GET",
            url: p.url,
            asynchronous: false,
            callback: function(req){
                _data = getContent(req.responseText);
            }
           }
           );
           return _data;
  };

  /**
   * If were returning an text document remove any script in the
   * the document and add it to the global scope using a time out.
   */
  function getContent(rawContent, _task) {
   
   _task.embeddedScripts = [];
   _task.embeddedStyles = [];
   _task.scriptReferences = [];
   _task.styleReferences = [];
  
    var _t = rawContent;
    var bodyText = "";

    // recursively go through and weed out the scripts

    var gscripts = _doc.getElementsByTagName("script");
    var gstyles = _doc.getElementsByTagName("link");
    while (_t.indexOf("<script") != -1) {
            var realStart = _t.indexOf("<script");
            var scriptSourceStart = _t.indexOf("src=", (realStart));
            var scriptElementEnd = _t.indexOf(">", realStart);
            var end = _t.indexOf("</script>", (realStart)) + "</script>".length;
            if (realStart != -1 && scriptSourceStart != -1) {
                var scriptSourceName;
                var scriptSourceLinkStart= scriptSourceStart + 5;
                var quoteType =  _t.substring(scriptSourceStart + 4, (scriptSourceStart +5));
                var scriptSourceLinkEnd= _t.indexOf("\"", (scriptSourceLinkStart + 1));
              	scriptSourceLinkEnd= _t.indexOf(quoteType, (scriptSourceLinkStart + 1));
                if (scriptSourceStart < scriptElementEnd) {
                    scriptSourceName = _t.substring(scriptSourceLinkStart, scriptSourceLinkEnd);
                    // prevent multiple inclusions of the same script
                    var exists = false;
                    for (var i = 0; i < gscripts.length; i++) {
                        if (typeof gscripts[i].src) {
                            if (gscripts[i].src == scriptSourceName) {
                                exists = true;
                                break;
                            }
                        }
                    }
                    if (!exists) {
                        _task.scriptReferences.push(scriptSourceName);
                    }
                }
            }
           // now remove the script body
           var scriptBodyStart =  scriptElementEnd + 1;
           var sBody = _t.substring(scriptBodyStart, end - "</script>".length);
           if (sBody.length > 0) {
              	_task.embeddedScripts.push(sBody);
           }
           //remove script
           _t = _t.substring(0, realStart) + _t.substring(end, _t.length);
           scriptSourceLinkEnd = -1;
      }
      while (_t.indexOf("<style") != -1) {
           var realStart = _t.indexOf("<style");
           var styleElementEnd = _t.indexOf(">", realStart);
           var end = _t.indexOf("</style>", (realStart)) ;
           var styleBodyStart =  styleElementEnd + 1;
           var sBody = _t.substring(styleBodyStart, end);
           if (sBody.length > 0) {
              _task.embeddedStyles.push(sBody);
           }
           //remove style
           _t = _t.substring(0, realStart) + _t.substring(end + "</style>".length, _t.length);
        }
        // get the links    
        while (_t.indexOf("<link") != -1) {
            var realStart = _t.indexOf("<link");
            var styleSourceStart = _t.indexOf("href=", (realStart));
            var styleElementEnd = _t.indexOf(">", realStart) +1;
            if (realStart != -1 && styleSourceStart != -1) {
                var styletSourceName;
                var styleSourceLinkStart= styleSourceStart + 6;
                var quoteType =  _t.substring(styleSourceStart + 5, (styleSourceStart + 6));           
                var styleSourceLinkEnd= _t.indexOf(quoteType, (styleSourceLinkStart + 1));
                if (styleSourceStart < styleElementEnd) {
                    styleSourceName = _t.substring(styleSourceLinkStart, styleSourceLinkEnd);
	              	var exists = false;
                        for (var i = 0; i < gstyles.length; i++) {
                            if (isDefined(gstyles[i].src)) {
                                if (gstyles[i].src == styleSourceName) {
                                    exists = true;	
                                }
                            }
                        }
		        if (!exists) {
		          	_task.styleReferences.push(styleSourceName);
	    	      }
                }
                //remove style
                _t = _t.substring(0, realStart) + _t.substring(styleElementEnd, _t.length);
            }
        }
        
        var head = _doc.getElementsByTagName("head")[0];
        
        // inject the links
        for(var loop = 0; _task.styleReferences && loop < _task.styleReferences.length; loop++) {
            var link = _doc.createElement("link");
            link.href = _task.styleReferences[loop];
            link.type = "text/css";
            link.rel = "stylesheet";
            head.appendChild(link);
        }
        
        var stylesElement;
        if (_task.embeddedStyles.length > 0) {
            stylesElement = _doc.createElement("style");
            stylesElement.type="text/css";
            var stylesText;
            for(var loop = 0; loop < _task.embeddedStyles.length; loop++) {
                stylesText = stylesText + _task.embeddedStyles[loop];
            }
            if (_doc.styleSheets[0].cssText) {
               _doc.styleSheets[0].cssText = _doc.styleSheets[0].cssText + stylesText;
            } else {
                stylesElement.appendChild(_doc.createTextNode(stylesText));
                head.appendChild(stylesElement);
            }
        }
        _task.content = _t;
      }
  
      this.loadScripts = function(task, initFunction) {    
          var _loadEmbeded = function() {
              // evaluate the embedded javascripts in the order they were added
              for(var loop = 0;task.embeddedScripts && loop < task.embeddedScripts.length; loop++) {
                  var script = task.embeddedScripts[loop];
                  // append to the script a method to call the scriptLoaderCallback
                  eval(script);
                  if (loop == (task.embeddedScripts.length -1)) {
                      if (isDefined(initFunction)) initFunction();
                      return;
                  }
              }
              if (task.embeddedScripts && task.embeddedScripts.length == 0 && isDefined(initFunction)) initFunction();
          };
          if (task.scriptReferences && task.scriptReferences.length > 0){
              // load the global scripts before loading the embeded scripts
              return _jmaki.addLibraries(task.scriptReferences.reverse(),_loadEmbeded, undefined, false);   
          } else {
              _loadEmbeded();
          }
          return true;
     };
  };
  this.injector = new this.Injector(); 
}

if (typeof jmaki == 'undefined') {
    var jmaki = new Jmaki();  
    jmaki.widgets = {};

    var oldLoad  = window.onload;
    
    /**
     * onload calls bootstrap function to initialize and load all registered widgets
     * override initial onload.
     */
    window.onload = function() {
        if (!jmaki.initialized) {
            jmaki.initialize();
        } else {
            jmaki.bootstrapWidgets();
            return;
        }
        if (typeof oldLoad  == 'function') {
            oldLoad();
        }
    }
}

/* Copyright 2007 You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at:
 http://developer.sun.com/berkeley_license.html
 $Id: component.js,v 1.0 2007/04/15 19:39:59 gmurray71 Exp $
*/
 // define the namespaces
jmaki.namespace("jmaki.widgets.jmaki.menu");

jmaki.widgets.jmaki.menu.Widget = function(wargs) {
	
    var container = document.getElementById(wargs.uuid);
    var topMenus = [];
    var navMenus = [];
    var menus = [];
    var hideTimer;
    var menu = [];
    var publish = "/jmaki/menu";

    // pull in the arguments
    if (wargs.publish) publish = wargs.publish;
    if (wargs.value) {
        menu = wargs.value.menu;
        rows = wargs.value.rows;
        init();
    } else if (wargs.service) {
            jmaki.doAjax({url: wargs.service, callback: function(req) {
        if (req.readyState == 4) {
            if (req.status == 200) {
              var data = eval('(' + req.responseText + ')');
              menu = data.menu;
              init();
          }
        }
      }});
    } else {
        menu = [
        {label: 'Some Label',
            menu: [
                {label:'Some Item', href:'http://www.sun.com'},
                {label:'Some Item 2', href:'http://www.jmaki.com'}
                ]},

        {label: 'Some Other Label',

            menu: [
                {label:'Some Other Item', href:'http://www.java.sun.com'},
                {label:'Some Other Item 2', action:{topic: '/mytopic', message: '2.jsp'}}
                ]}
        ];
        init();
    }

    function addStyle(style, nStyle){
       if (style.indexOf(nStyle) != -1) return style;
       if (style.length > 0) style += " ";
       return (style + nStyle);
    }

    function removeStyle(style, oStyle){
        if (style.indexOf(oStyle) == -1) return style;
        var styles = style.split(' ');
        var nStyle = "";
        for (var i = 0; i < styles.length; i++) {
            if (styles[i] != oStyle) nStyle += styles[i] + " ";
        }
        return nStyle;
    }

    function showMenu(e){
        hideMenus();
        var src = (typeof window.event == 'undefined') ? e.target : window.event.srcElement;
        var pos = getPosition(topMenus[src.id]);
        navMenus[src.id].style.top = pos.y + container.clientHeight - 2 + "px";
        navMenus[src.id].style.left = pos.x + "px";
        navMenus[src.id].style.display = "block";
    }

    function clone(t) {
       var obj = {};
       for (var i in t) {
            obj[i] = t[i];
       }
       return obj;
    }

    function processActions(_t, _pid, _type, _value) {
        if (_t) {
            var _topic = publish;
            var _m = {widgetId : wargs.uuid, type : _type, targetId : _pid};
            if (typeof _value != "undefined") _m.value = _value;
            var action = _t.action;
            if (!action) _topic = _topic + "/" + _type;
            if (action && action instanceof Array) {
              for (var _a=0; _a < action.length; _a++) {
                  var payload = clone(_m);
                  if (action[_a].topic) payload.topic = action[_a].topic;
                  else payload.topic = publish;
                  if (action[_a].message) payload.message = action[_a].message;
                  jmaki.publish(payload.topic,payload);
              }
            } else {
              if (action && action.topic) {
                  _topic = _m.topic = action.topic;
              }
              if (action && action.message) _m.message = action.message;               
              jmaki.publish(_topic,_m);
            }
        }
    } 


    function labelSelect(e) {
        hideMenus();
        var src = (typeof window.event == 'undefined') ? e.target : window.event.srcElement;
        var sp = src.id.split("_");
        var url = menu[sp[0]].url;
	if (typeof url == 'undefined') {
	    var href = menu[sp[0]].href;
	} else {
	    var href = url;
	    jmaki.log("jMaki menu: widget uses deprecated url property. Use href instead. ");
	}
        if (href) {
                window.location.href = href;
        }else if (menu[sp[0]].action ) {
            processActions(menu[sp[0]], menu[sp[0]].targetId);

        } 
    }

    function menuSelect(e){
        hideMenus();
        var src = (typeof window.event == 'undefined') ? e.target : window.event.srcElement;
        var sp = src.id.split("_");
        var url = menu[sp[0]].menu[sp[1]].url;
	if (typeof url == 'undefined') {
	    var href = menu[sp[0]].menu[sp[1]].href;
	} else {
	    var href = url;
	    jmaki.log("jMaki menu: widget uses deprecated url property. Use href instead. ");
	}
        if (href) {
                window.location.href = href;
        }else if (menu[sp[0]].menu[sp[1]].action ) {
            processActions(menu[sp[0]].menu[sp[1]], menu[sp[0]].menu[sp[1]].targetId);
        }
    }
    function hideMenus(){
        for (var _i=0; _i < menus.length;_i++) {
            menus[_i].style.display = "none";
           // topMenus[_i].className = removeStyle(topMenus[_i].className, "jmakiBackroundHover");
        }
    }

    function startHide() {
        hideTimer = setTimeout(hideMenus, 2000);
    }

    function stopHide() {
        if (hideTimer != null)  clearTimeout(hideTimer);
    }

    function getPosition(_e){
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
    }  


    function menuList(menuStyle) {
        var menuList = document.createElement("li");
        menuList.className = menuStyle + " jmakiBackround";
        menuList.onmouseout = startHide;
        menuList.onmousemove = stopHide;
	return menuList;

    }

    function menuElement(label, menuStyle, id) {
            var menuE = document.createElement("ul", menuStyle);
            menuE.className = menuStyle;// + " jmakiBackround";
            menuE.id = id;
                // need to support menus that are just menubar
            var tmi = document.createTextNode(label);
            menuE.appendChild(tmi);
	return menuE;

    }

    function init() {
        
        var selectedIndex = 0;
        
        if (wargs.args && wargs.args.topic) {
            publish = wargs.args.topic;
	    jmaki.log("topic name set to " + publish);
        } else if (wargs.publish) {
	    publish = wargs.publishes;
	    jmaki.log("topic name set to " + publish);
        }

        if (typeof wargs.args != 'undefined' && 
            typeof wargs.args.selectedIndex != 'undefined') {
            selectedIndex = Number(wargs.args.selectedIndex);
        }
        
        // since we add right to left reverse the ordering
        menu = menu.reverse();

        var endSpacer = document.createElement("li");
        endSpacer.className = "jmakiMenuTop jmakiMenuEndSpacer";
        container.appendChild(endSpacer);

        for (var i=0; i < menu.length; i++) {
	    var me = menuElement(menu[i].label, "jmakiMenuTop jmakiFont", i + '');
            if ( typeof menu[i].menu != 'undefined') {
                me.onmouseover = showMenu;
            } else {
                me.onclick = labelSelect;
            }
            container.appendChild(me);
            topMenus.push(me);

	    var ml = menuList("jmakiMenuContainer");
            navMenus.push(ml);
            container.appendChild(ml);
            menus.push(ml);
            if (menu[i].id) menu[i].targetId = menu[i].id;
                else menu[i].targetId = wargs.uuid+"_menu_"+i;

            if (i < menu.length -1) {
                var spacerDiv = document.createElement("li");
                spacerDiv.appendChild(document.createTextNode("|"));
                spacerDiv.className = "jmakiMenuSeparator jmakiFont";
                container.appendChild(spacerDiv);
            }
        }

        for(var oi=0; oi<menu.length; ++oi) {
            var mis = menu[oi].menu;
	    if ( typeof mis != 'undefined') { // not just a label
            for (var ii=0; ii < mis.length; ii++){
		var mi = menuElement(mis[ii].label, "jmakiMenuItem", oi + "_" + ii);  // was li item
                mi.onclick = menuSelect;
                mi.onmouseout = function(e){
                    var src = (typeof window.event == 'undefined') ? e.target : window.event.srcElement;
                    src.className = addStyle(src.className, "jmakiBackround");
                    src.className = removeStyle(src.className, "jmakiBackroundHover");
                };
                mi.onmousemove = function(e){
                    var src = (typeof window.event == 'undefined') ? e.target : window.event.srcElement;
                    src.className = removeStyle(src.className, "jmakiBackround");
                    src.className = addStyle(src.className, "jmakiBackroundHover");
                }
                navMenus[oi].appendChild(mi);

          if (mis[ii].id) mis[ii].targetId = mis[ii].id;
          else mis[ii].targetId = wargs.uuid+"_menu_"+ii;
            }
	  }
        }
    }
}

// define the namespaces
jmaki.namespace("jmaki.widgets.yahoo.dataTable");

/**
 * Wrapper by Greg Murray
 *
 * Find out more about the dataTable at: http://developer.yahoo.com/yui/datatable/
 */
jmaki.widgets.yahoo.dataTable.Widget = function(wargs) {

    var _widget = this;
        
    var container = document.getElementById(wargs.uuid);
    var filter = "jmaki.filters.tableModelFilter";
    var rowSingleSelect = true;

    var _model;
    var cm;
 
    var publish = "/yahoo/dataTable";
    var subscribe = ["/yahoo/dataTable", "/table"];
 
    var data;
    // yahoo normalized data
    var nData =[];
    var cols = [];
    var cold;
    var idMappings = {};
    var schema = []; 
    var counter = 0;

    var paginated = false;
    var rowsPerPage = 10;

    var showedModelWarning = false;
    
    function showModelDeprecation() {
        if (!showedModelWarning) {
             jmaki.log("Yahoo table widget uses the incorrect data format. Please see " +
                       "<a href='http://wiki.java.net/bin/view/Projects/jMakiTableDataModel'>" +
                       "http://wiki.java.net/bin/view/Projects/jMakiTableDataModel</a> for the proper format.");
             showedModelWarning = true;
        }   
    }
    function genId() {
        return wargs.uuid + "_nid_" + counter++;
    }       
    
    //read arguments
    if (wargs.args) {
        var args = wargs.args;
     
        if(args.columns) {
            cold = args.columns;
        }
        if (args.filter) {
            filter = args.filter;
        }
        if (typeof args.rowSingleSelect != 'undefined') {
            rowSingleSelect = args.rowSingleSelect;
        }
        if(typeof args.paginated != 'undefined') {
            //client-side pagination on/off switch
            paginated = args.paginated;
        }
        if(typeof args.rowsPerPage != 'undefined') {
            //client-side rows per page (only when pagination is on)
            rowsPerPage = args.rowsPerPage;
        }
    }
    
    if (wargs.publish ) {
	topic = wargs.publish;
     }
     
    if (wargs.subscribe){
        if (typeof wargs.subscribe == "string") {
            subscribe = [];
            subscribe.push(wargs.subscribe);         
        }
    }
    
    // sort function for the providing assending sort for adding items.
    function gSorter(a, b, desc) {
            var comp = YAHOO.util.Sort.compare;
            var myComp = comp(a.getData(cols[0].key), b.getData(cols[0].key), false);

            return myComp;
        }
    
    // normalize the data based on the schema
    this.addRows = function(_d,index) {
        var _rows;
        if (_d.message) {
            _d = _d.message;
        }
        if (_d.value) _rows = _d.value;
        else _rows = _d;
        if (typeof index == 'undefined' && _d.index) index = _d.index;
        var rs = _widget.dataTable.getRecordSet();
        for (var i=0; i < _rows.length; i++) {
            var row = {};
            // this code allows for matching to the columin id with row data              
            if (_rows[i][schema[0]]) {
                var r = _rows[i];
                for (var _i in r) {
                    if (typeof r[_i] == 'object' && r[_i].value) row[_i] = r[_i].value;
                    else  row[_i] = r[_i];
                }                
            } else {
                for (var ii=0; ii < _rows[i].length; ii++) {
                   var cell =   _rows[i][ii];
                   if (typeof cell.value != 'undefined') row[schema[ii]] = cell.value;
                   else row[schema[ii]] = cell;                    
                }
            }
            var rec = rs.addRecord(row);

    
            // add the record locator to the internal mappings
            var _lid;
            if (row.id) _lid = row.id;
            else  _lid = genId();

            if (idMappings[_lid]) {
                jmaki.log(wargs.uuid  + " : Warning. Attempt to add record to yahoo.dataTable with duplicate row id: " + _lid + ". Autogenerating new id.");
                while (idMappings[_lid]) _lid = genId();
            }
            idMappings[_lid] = rec;          
        }
        // sort everything
        if (_widget.columns && _widget.columns.keys[0]){
           var col = _widget.columns.keys[0];
           if (!col.sortOptions)col.sortOptions = {};
           col.sortOptions.sortFunction  = gSorter;
           _widget.dataTable.sortColumn(col);
           // reset the sorter
           col.sortOptions.sortFunction = null;
       }   
       _widget.dataTable.refreshView();


    }
    
    this.addRow = function(_d) {
        var row;
        var index;
        if (_d.message)_d = _d.message;
        if (_d.value) row = _d.value;
        else row = _d;
        if (_d.index) index = _d.index;        
        var _a = [];
         _a.push(row);    
        _widget.addRows(_a, index);
    }
    
    this.clear = function() {
      var rs = _widget.dataTable.getRecordSet();
      rs.reset();
      _widget.dataTable.refreshView(); 
      idMappings = {};   
    }
 
     this.removeRow = function(b) {
        var index;
        if (b.message)b = b.message;
        if (b.targetId) {
           index = b.targetId;
        } else {
            index = b;
        }
        if (typeof index != 'undefined'){
             var rs = _widget.dataTable.getRecordSet();      
             if (idMappings[index] && idMappings[index] != null) {
                 var rec = _widget.dataTable.getRecordIndex(idMappings[index]);
                 rs.deleteRecord(rec);
                 if (idMappings[index]) delete idMappings[index];
                 _widget.dataTable.refreshView();                 
             }
        }
    };

    this.updateRow = function(b, d) {
        var index;
        var data;
        if (d) data = d;
        if (b.message) {
            b = b.message;
        }
        if (b.value) {
            data = b.value;    
        }
        if (b.targetId) {
           index = b.targetId;
        } else {
            index = b;
        }
        if (typeof index != 'undefined' && data){
            var r = _widget.dataTable.getRecord(idMappings[index]);
            _widget.dataTable.getRecordSet().updateRecord(r, data);
            _widget.dataTable.refreshView();     
        }
    };
    

    this.select = function(b) {
        var index;
        if (b.message)b = b.message;
        if (b.targetId) {
           index = b.targetId;
        } else {
            index = b;
        }
        if (typeof index != 'undefined'){
            _widget.dataTable.unselectAllRows();
            _widget.dataTable.select(idMappings[index]);
            var r = _widget.dataTable.getRecord(idMappings[index]);
            var data = r.getData();
            jmaki.publish(publish + "/onSelect", {widgetId : wargs.uuid, topic : publish, type : "onSelect", targetId : index, value : data});
            
        }
    };
    

    function getRecordMapping(id) {
        for (var i in idMappings) {
            if (id == idMappings[i].getId()) return i;
        }
    }

    function doSubscribe(topic, handler) {
        var i = jmaki.subscribe(topic, handler);
        _widget.subs.push(i);     
    }
    
    this.destroy = function() {
        for (var i=0; _widget.subs && i < _widget.subs.length; i++) {
            jmaki.unsubscribe(_widget.subs[i]);
        }
    };   

    function init() {
        // create the columns and editors
        for (var i = 0 ; i < cold.length; i++){
            var col = {};
            var sortable = true;
            if (cold[i].sortable == false) sortable = cold[i].sortable;
            col.sortable = sortable;
            if (cold[i].id) {
               schema.push(cold[i].id);             
               schema[cold[i].id] = cold[i].id;
            } else {
                schema.push(cold[i].title);
                schema[cold[i].title] = cold[i].title;
            }
            // mix in everything but the renderer and editor
            for (var ii in cold[i]) {
                if (ii == 'editor') {
                    // create new editor with the mixins
                    col.editor = cold[i].editor;
                } else if (ii == 'renderer') {      
                    col.renderer = cold[i].renderer;
                } else if (ii == 'id') { 
                     col.key = cold[i].id;
                 } else if (ii == 'label') {
                     if (typeof cold[i].id == "undefined") {
                         col.key = cold[i].label;
                     }
                     col.label = cold[i].label;                                     
                } else if (ii == 'title') {
                     if (typeof cold[i].id == "undefined") {
                         col.key = cold[i].title;
                     }
                     col.text = cold[i].title;
                } else {
                    col[ii] = cold[i][ii];
                }
            }
            cols.push(col);
        }
        
        // normalize data for yahoo which wants key value pairs for every row item
        for (var i=0; i < data.length; i++) {
            var row = {};
            if (data[i][schema[0]]) {
                var r = data[i];
                for (var _i in r) {
                    if (typeof r[_i] == 'object' && r[_i].value) row[_i] = r[_i].value;
                    else  row[_i] = r[_i];
                }
            } else {
                for (var ii=0; ii < data[i].length; ii++) {
                    // this code allows for matching to the columin id with row data
                   var cell =  data[i][ii];
                   if (typeof cell.value != 'undefined') row[schema[ii]] = cell.value;
                   else row[schema[ii]] = cell;
                }
            }
            nData.push(row);
        }
    

        _widget.columns = new YAHOO.widget.ColumnSet(cols);
        var sortKey = cols[0].key;   
        
        var ds = new YAHOO.util.DataSource();

        ds.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        ds.responseSchema = {fields : schema};

       
        var onCellEdit = function(oArgs) {
            jmaki.publish(publish + "/onCellEdit", { widgetId : wargs.uuid, topic : publish, type : 'onCellEdit', oldValue : oArgs.oldData, newData : oArgs.newData});            
            jmaki.log("Cell \"" + oArgs.target.id +
            "\" was updated from \"" + oArgs.oldData + "\" to \"" +
            oArgs.newData + "\"", "info", this.toString());
        }
        
        //_widget.dataTable = new YAHOO.widget.DataTable(wargs.uuid,  _widget.columns, ds,{selectionMode : "single", caption:""});
        _widget.dataTable = new YAHOO.widget.DataTable(wargs.uuid, _widget.columns, ds,
        {   
            selectionMode : "single", 
            caption:"",
            paginated: paginated, // Enables built-in client-side pagination
            paginator:{ // Configurable options
                containers: null, // Create container DIVs dynamically
                currentPage: 1, // Show page 1
                dropdownOptions: [10,25,50], // Show these in the rows-per-page dropdown
                pageLinks: 0, // Show links to all pages
                rowsPerPage: rowsPerPage // Show up to n-rows per page
            }
        });
        
        _widget.dataTable.subscribe("cellClickEvent",_widget.dataTable.onEventEditCell);
        _widget.dataTable.subscribe("cellMouseoverEvent",_widget.dataTable.onEventHighlightRow);
        _widget.dataTable.subscribe("cellMouseoutEvent",_widget.dataTable.onEventUnhighlightRow);
        if (rowSingleSelect == true)_widget.dataTable.subscribe("cellClickEvent",_widget.dataTable.onEventSelectRow);
        if (rowSingleSelect == true)_widget.dataTable.subscribe("cellClickEvent", function(e) {
            var sids = _widget.dataTable.getSelectedRows();       
            var r = [];
            for (var l=0; l < sids.length; l++) {            
             var f = getRecordMapping( sids[l]);
             if (f)r.push(f);
           }
            var row = _widget.dataTable.getRecord(sids[0]);
            var data = row.getData();
           jmaki.publish(publish + "/onSelect", {widgetId : wargs.uuid, topic : publish, type : "onSelect", targetId : r[0], value : data});
        });
        _widget.dataTable.subscribe("cellEditEvent", onCellEdit);
        
        // track the subscribers so we can later remove them
        _widget.subs = [];

        for (var _i=0; _i < subscribe.length; _i++) {
            doSubscribe(subscribe[_i]  + "/clear", _widget.clear);
            doSubscribe(subscribe[_i]  + "/addRow", _widget.addRow);
            doSubscribe(subscribe[_i]  + "/addRows", _widget.addRows);
            doSubscribe(subscribe[_i]  + "/updateRow", _widget.updateRow);
            doSubscribe(subscribe[_i]  + "/removeRow", _widget.removeRow);
            doSubscribe(subscribe[_i]  + "/select", _widget.select);
        }
                
        _widget.addRows(nData);
        _widget.dataTable.refreshView();    
    }
    
    if (wargs.value) {
        // convert value if a jmakiRSS type
        if (wargs.value.dataType == 'jmakiRSS') {
           wargs.value = jmaki.filter(wargs.value, filter);
        }
	if (!wargs.value.rows) {
            showModelDeprecation();
	    return;
	}
        data = wargs.value;
        if ( wargs.value.columns) {
            cold =  wargs.value.columns;
        } 
	if (!wargs.value.rows) {
            showModelDeprecation();
            return;
        }
        if (wargs.value.rows) {
            data = wargs.value.rows
        } else {
            data = wargs.value;
        }
        init();
    } else if (wargs.service) {
        jmaki.doAjax(
           {url: wargs.service, 
                callback: function(req) {
                   if (req.responseText == "") {
                       container.innerHTML = "No data provided by: " + wargs.service;
                       return;
                    }
                    var _in = eval('(' + req.responseText + ')');
                   // convert value if a jmakiRSS type
                   if (_in.dataType == 'jmakiRSS') {
                       _in = jmaki.filter(_in, filter);
                    }
		    if (!_in.rows) {
            	    showModelDeprecation();
            	    return;
        	    }
                    if (_in.rows) {
                        data = _in.rows;
                    }  else {
                        data = _in;
                    }
                    if (!cold && _in.columns) {
                        cold = _in.columns;
                    }
                    init();
                },
                onerror : function (message) {
                    container.innerHTML = "Failed to load data: " + message;
                }
            }
        );
    } else {
          jmaki.doAjax(
           {url: wargs.widgetDir + "/widget.json", 
                callback: function(req) {
                   if (req.responseText == "") {
                       container.innerHTML = "No data provided by: " + wargs.service;
                       return;
                    }
                    var obj = eval('(' + req.responseText + ')');
                    var _in = obj.value.defaultValue;
                   // convert value if a jmakiRSS type
                   if (_in.dataType == 'jmakiRSS') {
                       _in = jmaki.filter(_in, filter);
                    }
                    if (_in.rows) {
                        data = _in.rows;
                    }  else {
                        data = _in;
                    }
                    if (!cold && _in.columns) {
                        cold = _in.columns;
                    }
                    init();
                },
                onerror : function (message) {
                    container.innerHTML = "Failed to load data: " + message;
                }
            }
        );
    }
}
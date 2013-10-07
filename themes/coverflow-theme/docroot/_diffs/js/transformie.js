jQuery(function() {

	if(!jQuery.browser.msie) return;

	if(Transformie.stylesheets)
		Transformie.parseStylesheets();

	if(Transformie.inlineCSS) {
		jQuery(Transformie.inlineCSS === true ? '*' : Transformie.inlineCSS).each(function() {
			if(Transformie.resolveCSSKey(this.style))
				Transformie.refreshMatrix(this, Transformie.resolveCSSKey(this.style));
		});
	}

	if(Transformie.trackChangesFor) {
		Transformie.bindChangeEvent(Transformie.trackChangesFor);
	}
	
});


var Transformie = {
	
	inlineCSS: '*',
	stylesheets: true,
	trackChangesFor: '*',
	centerOrigin: 'margin', //false, position
	
	toRadian: function(value) {
		if(value.indexOf("deg") != -1) {
			return parseInt(value,10) * (Math.PI * 2 / 360);
		} else if (value.indexOf("grad") != -1) {
			return parseInt(value,10) * (Math.PI/200);
		} else {
			return parseInt(value,10);
		}
	},
	
	bindChangeEvent: function(query) {
		jQuery(query).unbind('propertychange').bind('propertychange', function(e) {
			if(e.originalEvent.propertyName == 'style.webkitTransform' || e.originalEvent.propertyName == 'style.MozTransform' || e.originalEvent.propertyName == 'style.transform')
				Transformie.refreshMatrix(this, Transformie.resolveCSSKey(this.style));
		});
	},
	
	resolveCSSKey: function(style) {
		return style['-webkit-transform']
		|| 	style['webkit-transform'] 
		|| 	style['transform']
		|| 	style.webkitTransform
		||	style['-moz-transform']
		|| 	style['moz-transform'] 
		|| 	style.MozTransform
		|| 	style.mozTransform;
	},
	
	parseStylesheets: function() {	
		//Loop through all stylesheets and apply initial rules
		for (var i=0; i < document.styleSheets.length; i++) {
			for (var j=0; j < document.styleSheets[i].rules.length; j++) {
				if(Transformie.resolveCSSKey(document.styleSheets[i].rules[j].style))
					Transformie.refreshMatrix(document.styleSheets[i].rules[j].selectorText, Transformie.resolveCSSKey(document.styleSheets[i].rules[j].style));
			};
		};	
		
	},
	
	refreshMatrix: function(selector, ruleValue) {

		//TODO: Figure what to do with :hover
		if(selector.indexOf && selector.indexOf(':hover') != -1)
			return;
	
		//Split the webkit functions and loop through them
		var functions = ruleValue.match(/[A-z]+\([^\)]+/g) || [];
		var matrices = [];
		
		for (var k=0; k < functions.length; k++) {
		
			//Prepare the function name and its value
			var func = functions[k].split('(')[0],
				value = functions[k].split('(')[1];
		
			//Now we rotate through the functions and add it to our matrix
			switch(func) {
				case 'matrix': //Attention: Matrix in IE doesn't support e,f = tx, ty = translation
					var values = value.split(',');
					matrices.push($M([
						[values[0],	values[2],	0],
						[values[1],	values[3],	0],
						[0,					0,	1]
					]));
					break;
				case 'rotate':
					var a = Transformie.toRadian(value);
					matrices.push($M([
						[Math.cos(a),	-Math.sin(a),	0],
						[Math.sin(a),	Math.cos(a),	0],
						[0,				0,				1]
					]));
					break;
				case 'scale':
					matrices.push($M([
						[value,	0,		0],
						[0,		value,	0],
						[0,		0,		1]
					]));
					break;
				case 'scaleX':
					matrices.push($M([
						[value,	0,		0],
						[0,		1,		0],
						[0,		0,		1]
					]));
					break;
				case 'scaleY':
					matrices.push($M([
						[1,		0,		0],
						[0,		value,	0],
						[0,		0,		1]
					]));
					break;
				case 'skew':
					var a = Transformie.toRadian(value);
					matrices.push($M([
						[1,				0,	0],
						[Math.tan(a),	1,	0],
						[0,				0,	1]
					]));
				case 'skewX':
					var a = Transformie.toRadian(value);
					matrices.push($M([
						[1,		Math.tan(a),0],
						[0,		1,			0],
						[0,		0,			1]
					]));
					break;
				case 'skewY':
					var a = Transformie.toRadian(value);
					matrices.push($M([
						[1,				0,	0],
						[Math.tan(a),	1,	0],
						[0,				0,	1]
					]));
					break;
			};
			
		};
		
		if(!matrices.length)
			return;
		
		//Calculate the resulting matrix
		var matrix = matrices[0];
		for (var k=0; k < matrices.length; k++) {
			if(matrices[k+1]) matrix = matrices[k].x(matrices[k+1]);
		};

		//Finally, we apply the new matrix to our niftly matrix filter function
		jQuery(selector).each(function() {
		
			if(!this.filters["DXImageTransform.Microsoft.Matrix"]) {
				this.style.filter = (this.style.filter ? '' : ' ' ) + "progid:DXImageTransform.Microsoft.Matrix(sizingMethod='auto expand')";
				Transformie.bindChangeEvent(this);
			}

			this.filters["DXImageTransform.Microsoft.Matrix"].M11 = matrix.elements[0][0];
			this.filters["DXImageTransform.Microsoft.Matrix"].M12 = matrix.elements[0][1];
			this.filters["DXImageTransform.Microsoft.Matrix"].M21 = matrix.elements[1][0];
			this.filters["DXImageTransform.Microsoft.Matrix"].M22 = matrix.elements[1][1];
			
			if(Transformie.centerOrigin) { //TODO: Add computed borders here to clientWidth/height or find a better prop to look for
				this.style[Transformie.centerOrigin == 'margin' ? 'marginLeft' : 'left'] = -(this.offsetWidth/2) + (this.clientWidth/2) + "px";
				this.style[Transformie.centerOrigin == 'margin' ? 'marginTop' : 'top'] = -(this.offsetHeight/2) + (this.clientHeight/2) + "px";
			}
			
		});
		
	}	
};

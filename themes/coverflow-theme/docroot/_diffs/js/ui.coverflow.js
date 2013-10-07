/*
 * jQuery UI CoverFlow
   Re-written for jQueryUI 1.8.6/jQuery core 1.4.4+ by Addy Osmani with adjustments
   Maintenance updates for 1.8.9/jQuery core 1.5, 1.6.2 made.

   Original Component: Paul Bakaus for jQueryUI 1.7 
 */
(function($){

	function getPrefix( prop ){  
	        var prefixes = ['Moz','Webkit','Khtml','O','ms'],  
	            elem     = document.createElement('div'),  
	            upper      = prop.charAt(0).toUpperCase() + prop.slice(1),  
	            pref     = "",
				len 	 = 0;
	
	        for(len = prefixes.length; len--;){  
	            if((prefixes[len] + upper) in elem.style){  
	                pref = (prefixes[len]);  
	            }  
	        }  
	
	        if(prop in elem.style){  
	            pref = (prop);  
	        }  
			return pref;
	  }
	

	var vendorPrefix = getPrefix('transform');
	
	$.easing.easeOutQuint = function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	};

	$.widget("ui.coverflow", {
	
	   options: {
	        items: "> *",
			orientation: 'horizontal',
			item: 0,
			trigger: 'click',
			center: true, //If false, element's base position isn't touched in any way
			recenter: true //If false, the parent element's position doesn't get animated while items change
			
	  },
		
		_create: function() {
			
			var self = this, o = this.options;
			this.items = $(o.items, this.element);
			this.props = o.orientation == 'vertical' ? ['height', 'Height', 'top', 'Top'] : ['width', 'Width', 'left', 'Left'];
			//For < 1.8.2: this.items['outer'+this.props[1]](1);
			
			this.itemSize = 0.73 * this.items.innerWidth();
			this.itemWidth = this.items.width();
			this.itemHeight = this.items.height();
			this.duration = o.duration;
			this.current = o.item; //initial item
			

			//Bind click events on individual items
			this.items.bind(o.trigger, function() {
				self.select(this);
				
			});


			//Center the actual parent's left side within it's parent

			this.element.css(this.props[2],
				(o.recenter ? -this.current * this.itemSize/2 : 0)
				+ (o.center ? this.element.parent()[0]['offset'+this.props[1]]/2 - this.itemSize/2 : 0) //Center the items container
				- (o.center ? parseInt(this.element.css('padding'+this.props[3]),10) || 0 : 0) //Subtract the padding of the items container
			);

			//Jump to the first item
			this._refresh(1, 0, this.current);

		},
		
		select: function(item, noPropagation) {
		
			
			this.previous = this.current;
			this.current = !isNaN(parseInt(item,10)) ? parseInt(item,10) : this.items.index(item);
			

			//Don't animate when clicking on the same item
			if(this.previous == this.current) return false; 
			
			//Overwrite $.fx.step.coverflow everytime again with custom scoped values for this specific animation
			var self = this, to = Math.abs(self.previous-self.current) <=1 ? self.previous : self.current+(self.previous < self.current ? -1 : 1);
			$.fx.step.coverflow = function(fx) { self._refresh(fx.now, to, self.current); };
			
			// 1. Stop the previous animation
			// 2. Animate the parent's left/top property so the current item is in the center
			// 3. Use our custom coverflow animation which animates the item

			var animation = { coverflow: 1 };
		
		
			animation[this.props[2]] = (
				(this.options.recenter ? -this.current * this.itemSize/2 : 0)
				+ (this.options.center ? this.element.parent()[0]['offset'+this.props[1]]/2 - this.itemSize/2 : 0) //Center the items container
				- (this.options.center ? parseInt(this.element.css('padding'+this.props[3]),10) || 0 : 0) //Subtract the padding of the items container
			);
		
		
			
			//Trigger the 'select' event/callback
			if(!noPropagation) this._trigger('select', null, this._uiHash());
			
			this.element.stop().animate(animation, {
				duration: this.options.duration,
				easing: 'easeOutQuint'
			});
			
		},
		
		_refresh: function(state,from,to) {
		
	
			var self = this, offset = null;
	
			
			this.items.each(function(i) {
			
				
				var side = (i == to && from-to < 0 ) ||  i-to > 0 ? 'left' : 'right',
					mod = i == to ? (1-state) : ( i == from ? state : 1 ),
					before = (i > from && i != to),
					css = { zIndex: self.items.length + (side == "left" ? to-i : i-to) };
				    //css[($.browser.safari ? 'webkit' : ($.browser.opera ? 'O' : 'Moz'))+'Transform'] = 'matrix(1,'+(mod * (side == 'right' ? -0.2 : 0.2))+',0,1,0,0) scale('+(1+((1-mod)*0.3)) + ')'; 
	           
	
				if(vendorPrefix == 'ms' || vendorPrefix == ""){
		
					css["filter"] = "progid:DXImageTransform.Microsoft.Matrix(sizingMethod='auto expand', M11=1, M12=0, M21=" + (mod * (side == 'right' ? -0.2 : 0.2)) + ", M22=1";
					css[self.props[2]] = ( (-i * (self.itemSize/2)) + (side == 'right'? -self.itemSize/2 : self.itemSize/2) * mod );
		
					if(i == self.current){
					css.width = self.itemWidth * (1+((1-mod)*0.3));
					css.height = css.width * (self.itemHeight / self.itemWidth);
					css.top = -((css.height - self.itemHeight) / 3);

					css.left -= self.itemWidth/6 -50;
					}
					else{
					css.width = self.itemWidth;
					css.height = self.itemHeight;
					css.top = 0;

					if(side == "left"){
					css.left -= self.itemWidth/5 -50;
					}
		
					}//end if
		
		
				}else{
	
					css[vendorPrefix + 'Transform'] = 'matrix(1,'+(mod * (side == 'right' ? -0.2 : 0.2))+',0,1,0,0) scale('+(1+((1-mod)*0.3)) + ')'; 
					css[self.props[2]] = ( (-i * (self.itemSize/2)) + (side == 'right'? -self.itemSize/2 : self.itemSize/2) * mod );
		
				}
	

				$(this).css(css);


			});

			this.element.parent().scrollTop(0);
			
		},
		
		_uiHash: function() {
			return {
				item: this.items[this.current],
				value: this.current
			};
		}
		
	});

	
})(jQuery); 

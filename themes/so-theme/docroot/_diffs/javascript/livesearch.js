/*
 * jQuery Live Search Plugin
 * Version: 0.1
 * Date: 2008-08-04
 * Author: Eduardo Lundgren
 * Copyright: Copyright (c) 2008 Eduardo Lundgren under dual MIT/GPL license.
*/

; (function($) {

    $.fn.extend({
        liveSearch: function(options) {
            return this.each(function() {
                var opt = options = $.extend({},
                $.liveSearch.defaults, options || {});
                new $.liveSearch(this, opt);
            });
        }
    });


    $.liveSearch = function(element, options) {
        this.init(element, options);
    };

    $.extend($.liveSearch.prototype, {
        init: function(element, options) {
            var instance = this;

            this.options = options;
            this.timer = null;
            this.cache = null;
            this.element = jQuery(element);
            this.list = jQuery(this.options.list);
            this.delay = this.options.delay;
            this.filterList = jQuery(this.options.filter || this.list);

            if (this.filterList.length) {
                this.cache = this.filterList.map(this.options.data);

                this.element
                .keyup(function() {
                    var self = this;

                    if (instance.timer) {
                        clearTimeout(instance.timer);
                    }

                    instance.timer = setTimeout(function() {
                        instance.options.before.apply(instance);
                        instance.filter();
                        instance.options.after.apply(instance);
                    },
                    instance.delay);
                })
                .parents('form').submit(function() {
                    return false;
                });
            }

            return this;
        },

        filter: function() {
            var instance = this,
            results = [],
            rows = this.list;

            this.term = $.trim(this.element.val().toLowerCase()).match(/(\w|\s|[*])*/g).join("");

            if (!this.term) {
                rows.each(function() {
                    instance.options.show.apply(this);
                });
            } else {
                rows.each(function() {
                    instance.options.hide.apply(this);
                });

                this.cache.each(function(i, v) {
                    var regex = new RegExp(instance.term.replace("*", ""), "g");
                    if (regex.test(v) || instance.options.exclude.apply(rows[i])) {
                        results.push(i);
                    }
                });

                $.each(results,
                function() {
                    instance.options.show.apply(jQuery(rows[this]));
                });
            }
        }
    });

    $.extend($.liveSearch, {
        defaults: {
            delay: 250,
            show: function() {
                $(this).show();
            },
            hide: function() {
                $(this).hide();
            },
            data: function() {
                return $(this)[0].innerHTML.toLowerCase();
            },
            exclude: function() {
            	return false;
            },
            before: function() {},
            after: function() {}
        }
    });

})(jQuery);
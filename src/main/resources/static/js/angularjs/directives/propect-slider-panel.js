'use strict';

angular.module('rtag').directive('prospectsliderpanel', function() {
	var directive = {};

	directive.restrict = 'E';
	directive.templateUrl = '/views/prospect-slider-panel/index.php';
	directive.scope = {
		psp : "=name"
	}

	directive.compile = function(element, attributes) {
	}
	return directive;
});
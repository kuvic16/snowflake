/**
 * @author: Shaiful Islam <kuvic16@gmail.com>
 * @since version 1.0
 */
var app = angular.module('edms', ['ngRoute', 'ngResource', 'ngMaterial'], function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

app.directive('onErrorSrc', function ($rootScope) {
    return {
        link: function (scope, element, attrs) {
            element.bind('error', function () {
                if (attrs.src != attrs.onErrorSrc) {
                    console.log(attrs.onErrorSrc);
                    attrs.$set('src', $rootScope.baseUrl + attrs.onErrorSrc);
                }
            });
        }
    }
});

app.config(['$httpProvider', '$routeProvider', '$controllerProvider', function($httpProvider, $routeProvider, $controllerProvider,  $window) {
	app.registerCtrl = $controllerProvider.register;
}])
.run(function($rootScope, $window) {
    console.log("Apps is running...");
    $rootScope.baseUrl = "";
    $rootScope.blankImage = "/src/images/blank_avatar.png";
});

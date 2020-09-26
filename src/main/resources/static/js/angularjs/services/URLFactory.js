app.factory('URLResource', function ($resource, $rootScope) {
    var baseUrl = $rootScope.baseUrl;
    var resource = {};
    var resource = $resource(baseUrl,{},
            {
                'coreAreaList': { method: 'GET', url: baseUrl + '/corearea/list' },                
                'userCoreExpertiseSave': { method: 'POST', url: baseUrl + '/usersexpertieses/save?coreareaid=:core_area_id', isArray: true },
                'userCoreExpertiseList': { method: 'GET', url: baseUrl + '/usersexpertieses/list', isArray: true },
            }
    );
    return resource;
});

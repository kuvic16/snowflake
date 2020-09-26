app.service('UtilService', ['$rootScope',  function($rootScope){
        this.getParameterByName = function (sname) {
            var params = location.search.substr(location.search.indexOf("?") + 1);
            var sval = "";
            params = params.split("&");
            for (var i = 0; i < params.length; i++)
            {
                var temp = params[i].split("=");
                if ([temp[0]] == sname) {
                    sval = temp[1];
                }
            }
            return decodeURI(sval);
        };
        
        this.isUndefinedOrNull = function(val) {
	        return angular.isUndefined(val) || val === null || val === "" 
        };

        this.staff_search = {
            page: 0,
            gender: '',
            min_age: 18,
            max_age: 65,
            min_grade: 1,
            max_grade: 13,
            brac_experience: null,
            total_experience: null,
            core_expertieses: [],
            core_expertieses_names: [],
            unique_expertieses: [],
            unique_expertieses_names: [],
            posted_districts: [],
            lived_districts: [],
            lived_districts_names: [],
            project_program_departments: [],
            project_program_departments_names: [],
            educations: [],
            educations_names: [],
            languages: [],
        };
        
        
}]);
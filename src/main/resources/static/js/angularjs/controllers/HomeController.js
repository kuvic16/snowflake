app.controller('LanguageSelectionController', function ($scope, $rootScope, $location, $window, $http, URLResource) {

    /**
     * Variable for Language list
     * 
     * @var array
     * */
    $scope.languages = [];
    $scope.staffLanguages = [];
    
    
    /**
     * Retrive Language data init
     * 
     * @param 
     * 
     * @returns void
     * */
    $scope.getLanguageInitData = function () {
        $scope.showLoader()
        var successCallback = function (data) {
            $scope.languages = data.languageList;
            $scope.staffLanguages = data.staffLanguagesList;
            $scope.synchronizeWithStaffLanguages();
            
            $scope.hideLoader();
        }
        var errorCallback = function (data) {
            $scope.hideLoader();
        }
        URLResource.languageSelectionInitData(successCallback, errorCallback);
    };

    $scope.languageOnChangeListener = function (language, $event, plevel) {
        var elem = $event.currentTarget || $event.srcElement;
        //$("." + language.id).not(elem).prop('checked', false);

        language.beginner = false;
        language.intermediate = false;
        language.advanced = false;
        if (plevel == 'beginner' && $(elem).is(':checked')) language.beginner = true;
        if (plevel == 'intermediate' && $(elem).is(':checked')) language.intermediate = true;
        if (plevel == 'advanced' && $(elem).is(':checked')) language.advanced = true;

        //console.log($scope.languages);
    };

    /**
     * Synchronize Staff languages with Language List
     * 
     * @param 
     * 
     * @returns void
     * */
    $scope.synchronizeWithStaffLanguages = function () {
        $scope.languages.map(function (language) {
            language.beginner = false;
            language.intermediate = false;
            language.advanced = false;

            $scope.staffLanguages.forEach(function (staffLanguage) {
                if (language.id == staffLanguage.language_id) {
                    if (staffLanguage.level_of_proficiency == 1) language.beginner = true;
                    if (staffLanguage.level_of_proficiency == 2) language.intermediate = true;
                    if (staffLanguage.level_of_proficiency == 3) language.advanced = true;
                }
            });
            return language;
        });

    };

    $scope.save = function () {
        $scope.showLoader();

        var staff_languages = [];
        $scope.languages.forEach(function (language) {
            var staff_language = {};
            if (language.beginner) staff_language.level_of_proficiency = 1;
            if (language.intermediate) staff_language.level_of_proficiency = 2;
            if (language.advanced) staff_language.level_of_proficiency = 3;
            if (staff_language.level_of_proficiency > 0) {
                staff_language.language_id = language.id;
                staff_languages.push(staff_language);
            }
        });

        var successCallback = function (data) {
            console.log(data);
            $scope.hideLoader();
            //$scope.showSuccess("Your selection has been submitted successfully.");
            $window.location.href = "/Dashboard/StaffUniqueSkill";
        }
        var errorCallback = function (data) {
            console.log(data);
            $scope.hideLoader();
        }
        URLResource.saveStaffLanguages({ staff_languages: staff_languages }, successCallback, errorCallback);
    }

    $scope.showLoader = function () {
        $("#LanguageSelection").LoadingOverlay("show");
    }

    $scope.hideLoader = function () {
        $("#LanguageSelection").LoadingOverlay("hide");
    }

    /**
     * Show success message
     *
     * @param {string} $message
     *
     * @return void
     * */
    $scope.showSuccess = function ($message) {
        swal({
            title: "Thank you",
            text: $message,
            type: "success"
        },
            function () {
                $window.location.href = "/Dashboard/StaffUniqueSkill";
            });
    }

    $scope.backToPreviousPage = function () {
        $window.location.href = "/Dashboard/Staff";
    }

    /**
     * Call the method for the first time
     * */
    $scope.getLanguageInitData();
    

});













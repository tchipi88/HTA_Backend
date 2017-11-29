(function() {
    'use strict';
    angular
        .module('app')
        .factory('PatientSituation', PatientSituation);

    PatientSituation.$inject = ['$resource','DateUtils'];

    function PatientSituation ($resource,DateUtils) {
        var resourceUrl =  'api/patient-situations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
             'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                          data.dateSituation =DateUtils.convertLocalDateFromServer(data.dateSituation);

                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateSituation =DateUtils.convertLocalDateToServer(copy.dateSituation);

                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateSituation =DateUtils.convertLocalDateToServer(copy.dateSituation);

                    return angular.toJson(copy);
                }
            }
        });
    }
})();

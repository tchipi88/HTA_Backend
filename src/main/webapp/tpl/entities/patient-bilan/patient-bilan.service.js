(function() {
    'use strict';
    angular
        .module('app')
        .factory('PatientBilan', PatientBilan);

    PatientBilan.$inject = ['$resource','DateUtils'];

    function PatientBilan ($resource,DateUtils) {
        var resourceUrl =  'api/patient-bilans/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
             'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                          data.dateBilan =DateUtils.convertLocalDateFromServer(data.dateBilan);

                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateBilan =DateUtils.convertLocalDateToServer(copy.dateBilan);

                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateBilan =DateUtils.convertLocalDateToServer(copy.dateBilan);

                    return angular.toJson(copy);
                }
            }
        });
    }
})();

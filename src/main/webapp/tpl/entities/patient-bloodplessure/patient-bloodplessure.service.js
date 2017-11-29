(function() {
    'use strict';
    angular
        .module('app')
        .factory('PatientBloodplessure', PatientBloodplessure);

    PatientBloodplessure.$inject = ['$resource','DateUtils'];

    function PatientBloodplessure ($resource,DateUtils) {
        var resourceUrl =  'api/patient-bloodplessures/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
             'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                          data.dateReleve =DateUtils.convertLocalDateFromServer(data.dateReleve);

                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateReleve =DateUtils.convertLocalDateToServer(copy.dateReleve);

                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateReleve =DateUtils.convertLocalDateToServer(copy.dateReleve);

                    return angular.toJson(copy);
                }
            }
        });
    }
})();

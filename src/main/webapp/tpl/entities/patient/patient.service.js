(function() {
    'use strict';
    angular
        .module('app')
        .factory('Patient', Patient);

    Patient.$inject = ['$resource','DateUtils'];

    function Patient ($resource,DateUtils) {
        var resourceUrl =  'api/patients/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
             'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                          data.birthDay =DateUtils.convertLocalDateFromServer(data.birthDay);
 data.lastDateBloodPressureMesured =DateUtils.convertLocalDateFromServer(data.lastDateBloodPressureMesured);

                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.birthDay =DateUtils.convertLocalDateToServer(copy.birthDay);
 copy.lastDateBloodPressureMesured =DateUtils.convertLocalDateToServer(copy.lastDateBloodPressureMesured);

                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.birthDay =DateUtils.convertLocalDateToServer(copy.birthDay);
 copy.lastDateBloodPressureMesured =DateUtils.convertLocalDateToServer(copy.lastDateBloodPressureMesured);

                    return angular.toJson(copy);
                }
            }
        });
    }
})();

(function() {
    'use strict';
    angular
        .module('app')
        .factory('PatientConsultation', PatientConsultation);

    PatientConsultation.$inject = ['$resource','DateUtils'];

    function PatientConsultation ($resource,DateUtils) {
        var resourceUrl =  'api/patient-consultations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
             'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                          data.dateConsultation =DateUtils.convertLocalDateFromServer(data.dateConsultation);
 data.prochaineVisiteConsultation =DateUtils.convertLocalDateFromServer(data.prochaineVisiteConsultation);

                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateConsultation =DateUtils.convertLocalDateToServer(copy.dateConsultation);
 copy.prochaineVisiteConsultation =DateUtils.convertLocalDateToServer(copy.prochaineVisiteConsultation);

                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                     copy.dateConsultation =DateUtils.convertLocalDateToServer(copy.dateConsultation);
 copy.prochaineVisiteConsultation =DateUtils.convertLocalDateToServer(copy.prochaineVisiteConsultation);

                    return angular.toJson(copy);
                }
            }
        });
    }
})();

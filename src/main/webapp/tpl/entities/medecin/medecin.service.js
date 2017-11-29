(function() {
    'use strict';
    angular
        .module('app')
        .factory('Medecin', Medecin);

    Medecin.$inject = ['$resource','DateUtils'];

    function Medecin ($resource,DateUtils) {
        var resourceUrl =  'api/medecins/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

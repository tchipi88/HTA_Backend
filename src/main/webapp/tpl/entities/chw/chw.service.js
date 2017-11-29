(function() {
    'use strict';
    angular
        .module('app')
        .factory('Chw', Chw);

    Chw.$inject = ['$resource','DateUtils'];

    function Chw ($resource,DateUtils) {
        var resourceUrl =  'api/chws/:id';

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

(function() {
    'use strict';

    angular
        .module('app')
        .factory('PatientSearch', PatientSearch);

    PatientSearch.$inject = ['$resource'];

    function PatientSearch($resource) {
        var resourceUrl =  'api/_search/patients/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();

(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientSituationDeleteController',PatientSituationDeleteController);

    PatientSituationDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientSituation'];

    function PatientSituationDeleteController($uibModalInstance, entity, PatientSituation) {
        var vm = this;

        vm.patientSituation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientSituation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

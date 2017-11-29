(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientWeightDeleteController',PatientWeightDeleteController);

    PatientWeightDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientWeight'];

    function PatientWeightDeleteController($uibModalInstance, entity, PatientWeight) {
        var vm = this;

        vm.patientWeight = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientWeight.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

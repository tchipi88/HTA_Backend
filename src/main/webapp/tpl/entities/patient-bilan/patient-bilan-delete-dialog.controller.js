(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientBilanDeleteController',PatientBilanDeleteController);

    PatientBilanDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientBilan'];

    function PatientBilanDeleteController($uibModalInstance, entity, PatientBilan) {
        var vm = this;

        vm.patientBilan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientBilan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientBloodplessureDeleteController',PatientBloodplessureDeleteController);

    PatientBloodplessureDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientBloodplessure'];

    function PatientBloodplessureDeleteController($uibModalInstance, entity, PatientBloodplessure) {
        var vm = this;

        vm.patientBloodplessure = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientBloodplessure.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

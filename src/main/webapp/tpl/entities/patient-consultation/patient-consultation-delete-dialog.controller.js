(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientConsultationDeleteController',PatientConsultationDeleteController);

    PatientConsultationDeleteController.$inject = ['$uibModalInstance', 'entity', 'PatientConsultation'];

    function PatientConsultationDeleteController($uibModalInstance, entity, PatientConsultation) {
        var vm = this;

        vm.patientConsultation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PatientConsultation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

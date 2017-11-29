(function() {
    'use strict';

    angular
        .module('app')
        .controller('MedecinDeleteController',MedecinDeleteController);

    MedecinDeleteController.$inject = ['$uibModalInstance', 'entity', 'Medecin'];

    function MedecinDeleteController($uibModalInstance, entity, Medecin) {
        var vm = this;

        vm.medecin = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Medecin.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

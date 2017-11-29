(function() {
    'use strict';

    angular
        .module('app')
        .controller('ChwDeleteController',ChwDeleteController);

    ChwDeleteController.$inject = ['$uibModalInstance', 'entity', 'Chw'];

    function ChwDeleteController($uibModalInstance, entity, Chw) {
        var vm = this;

        vm.chw = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Chw.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

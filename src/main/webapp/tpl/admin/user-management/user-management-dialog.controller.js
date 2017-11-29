(function () {
    'use strict';

    angular
            .module('app')
            .controller('UserManagementDialogController', UserManagementDialogController);

    UserManagementDialogController.$inject = ['$stateParams', '$uibModalInstance', '$uibModal', 'entity', 'User'];

    function UserManagementDialogController($stateParams, $uibModalInstance, $uibModal, entity, User) {
        var vm = this;

        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.user = entity;

        

        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;




        function openCalendar(date) {
            vm.datePickerOpenStatus[date] = true;
        }




        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess(result) {
            vm.isSaving = false;
            $uibModalInstance.close(result);
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        function save() {
            vm.isSaving = true;
            if (vm.user.id !== null) {
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }
        ;

        vm.zoomColumn = function (lookupCtrl, lookupTemplate, fieldname, entity) {
            $uibModal.open({
                templateUrl: 'tpl/entities/' + lookupTemplate + '/' + lookupTemplate + '-dialog.html',
                controller: lookupCtrl + 'DialogController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                resolve: {
                    entity: function () {
                        return entity;
                    }
                }
            }).result.then(function (item) {
                vm.user[fieldname] = item;
            }, function () {

            });
        };
    }
})();

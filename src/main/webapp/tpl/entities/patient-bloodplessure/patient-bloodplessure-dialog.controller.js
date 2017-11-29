(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientBloodplessureDialogController', PatientBloodplessureDialogController);

    PatientBloodplessureDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$uibModal','DataUtils', 'entity', 'PatientBloodplessure','Patient'];

    function PatientBloodplessureDialogController ($timeout, $scope, $stateParams, $uibModalInstance,$uibModal, DataUtils, entity, PatientBloodplessure ,Patient) {
        var vm = this;

        vm.patientBloodplessure = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.patients = Patient.query();

      

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.patientBloodplessure.id !== null) {
                PatientBloodplessure.update(vm.patientBloodplessure, onSaveSuccess, onSaveError);
            } else {
                PatientBloodplessure.save(vm.patientBloodplessure, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tkbrApp:patientBloodplessureUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


         vm.datePickerOpenStatus.dateReleve = false;

        
         function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
         vm.setMimage = function ($file, fieldName) {
                if ($file && $file.$error === 'pattern') {
                    return;
                }
                if ($file) {
                    DataUtils.toBase64($file, function (base64Data) {
                        $scope.$apply(function () {
                            vm.patientBloodplessure[fieldName] = base64Data;
                            vm.patientBloodplessure[fieldName + 'ContentType'] = $file.type;
                        });
                    });
                }
            };
            
            vm.zoomColumn = function (lookupCtrl,lookupTemplate, fieldname, entity) {
                $uibModal.open({
                    templateUrl: 'tpl/entities/'+lookupTemplate+'/'+lookupTemplate+'-dialog.html',
                    controller: lookupCtrl+'DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return entity;
                        }
                    }
                }).result.then(function(item) {
                        vm.patientBloodplessure[fieldname] = item;
                }, function() {
                    
                });
            };

    }
})();

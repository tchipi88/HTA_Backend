(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientSituationDialogController', PatientSituationDialogController);

    PatientSituationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$uibModal','DataUtils', 'entity', 'PatientSituation','Patient'];

    function PatientSituationDialogController ($timeout, $scope, $stateParams, $uibModalInstance,$uibModal, DataUtils, entity, PatientSituation ,Patient) {
        var vm = this;

        vm.patientSituation = entity;
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
            if (vm.patientSituation.id !== null) {
                PatientSituation.update(vm.patientSituation, onSaveSuccess, onSaveError);
            } else {
                PatientSituation.save(vm.patientSituation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tkbrApp:patientSituationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


         vm.datePickerOpenStatus.dateSituation = false;

        
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
                            vm.patientSituation[fieldName] = base64Data;
                            vm.patientSituation[fieldName + 'ContentType'] = $file.type;
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
                        vm.patientSituation[fieldname] = item;
                }, function() {
                    
                });
            };

    }
})();

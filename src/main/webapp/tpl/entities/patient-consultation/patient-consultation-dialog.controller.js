(function() {
    'use strict';

    angular
        .module('app')
        .controller('PatientConsultationDialogController', PatientConsultationDialogController);

    PatientConsultationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$uibModal','DataUtils', 'entity', 'PatientConsultation','Medecin','Patient'];

    function PatientConsultationDialogController ($timeout, $scope, $stateParams, $uibModalInstance,$uibModal, DataUtils, entity, PatientConsultation ,Medecin,Patient) {
        var vm = this;

        vm.patientConsultation = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.patients = Patient.query();
vm.medecins = Medecin.query();

      

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.patientConsultation.id !== null) {
                PatientConsultation.update(vm.patientConsultation, onSaveSuccess, onSaveError);
            } else {
                PatientConsultation.save(vm.patientConsultation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tkbrApp:patientConsultationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


         vm.datePickerOpenStatus.dateConsultation = false;
 vm.datePickerOpenStatus.prochaineVisiteConsultation = false;

        
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
                            vm.patientConsultation[fieldName] = base64Data;
                            vm.patientConsultation[fieldName + 'ContentType'] = $file.type;
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
                        vm.patientConsultation[fieldname] = item;
                }, function() {
                    
                });
            };

    }
})();

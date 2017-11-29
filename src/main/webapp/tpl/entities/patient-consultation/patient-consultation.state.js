(function () {
'use strict';
        angular
        .module('app')
        .config(stateConfig);
        stateConfig.$inject = ['$stateProvider'];
        function stateConfig($stateProvider) {
        $stateProvider
                .state('patient-consultation', {
                parent: 'entity',
                        url: '/patient-consultation?page&sort&search',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        views: {
                        'content@app': {
                        templateUrl: 'tpl/entities/patient-consultation/patient-consultations.html',
                                controller: 'PatientConsultationController',
                                controllerAs: 'vm'  }
                        },
                        params: {
                        page: {
                        value: '1',
                                squash: true
                        },
                                sort: {
                                value: 'id,asc',
                                        squash: true
                                },
                                search: null
                        },
                        resolve: {
                        pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                        return {
                        page: PaginationUtil.parsePage($stateParams.page),
                                sort: $stateParams.sort,
                                predicate: PaginationUtil.parsePredicate($stateParams.sort),
                                ascending: PaginationUtil.parseAscending($stateParams.sort),
                                search: $stateParams.search
                        };
                        }]
                        }
                })
               
               
                .state('patient-consultation.new', {
                parent: 'patient-consultation',
                        url: '/new',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-consultation/patient-consultation-dialog.html',
                                controller: 'PatientConsultationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: function () {
                                return {

                                };
                                }
                                }
                        }).result.then(function () {
                        $state.go('patient-consultation', null, {reload: 'patient-consultation'});
                        }, function () {
                        $state.go('patient-consultation');
                        });
                        }]
                })
                .state('patient-consultation.edit', {
                parent: 'patient-consultation',
                        url: '/{id}/edit',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-consultation/patient-consultation-dialog.html',
                                controller: 'PatientConsultationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: ['PatientConsultation', function (PatientConsultation) {
                                return PatientConsultation.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-consultation', null, {reload: 'patient-consultation'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                })
                .state('patient-consultation.delete', {
                parent: 'patient-consultation',
                        url: '/{id}/delete',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-consultation/patient-consultation-delete-dialog.html',
                                controller: 'PatientConsultationDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                entity: ['PatientConsultation', function (PatientConsultation) {
                                return PatientConsultation.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-consultation', null, {reload: 'patient-consultation'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                });
        }

})();

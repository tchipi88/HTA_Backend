(function () {
'use strict';
        angular
        .module('app')
        .config(stateConfig);
        stateConfig.$inject = ['$stateProvider'];
        function stateConfig($stateProvider) {
        $stateProvider
                .state('patient-bloodplessure', {
                parent: 'entity',
                        url: '/patient-bloodplessure?page&sort&search',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        views: {
                        'content@app': {
                        templateUrl: 'tpl/entities/patient-bloodplessure/patient-bloodplessures.html',
                                controller: 'PatientBloodplessureController',
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
               
               
                .state('patient-bloodplessure.new', {
                parent: 'patient-bloodplessure',
                        url: '/new',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bloodplessure/patient-bloodplessure-dialog.html',
                                controller: 'PatientBloodplessureDialogController',
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
                        $state.go('patient-bloodplessure', null, {reload: 'patient-bloodplessure'});
                        }, function () {
                        $state.go('patient-bloodplessure');
                        });
                        }]
                })
                .state('patient-bloodplessure.edit', {
                parent: 'patient-bloodplessure',
                        url: '/{id}/edit',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bloodplessure/patient-bloodplessure-dialog.html',
                                controller: 'PatientBloodplessureDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: ['PatientBloodplessure', function (PatientBloodplessure) {
                                return PatientBloodplessure.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-bloodplessure', null, {reload: 'patient-bloodplessure'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                })
                .state('patient-bloodplessure.delete', {
                parent: 'patient-bloodplessure',
                        url: '/{id}/delete',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bloodplessure/patient-bloodplessure-delete-dialog.html',
                                controller: 'PatientBloodplessureDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                entity: ['PatientBloodplessure', function (PatientBloodplessure) {
                                return PatientBloodplessure.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-bloodplessure', null, {reload: 'patient-bloodplessure'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                });
        }

})();

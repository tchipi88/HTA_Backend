(function () {
'use strict';
        angular
        .module('app')
        .config(stateConfig);
        stateConfig.$inject = ['$stateProvider'];
        function stateConfig($stateProvider) {
        $stateProvider
                .state('patient-bilan', {
                parent: 'entity',
                        url: '/patient-bilan?page&sort&search',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        views: {
                        'content@app': {
                        templateUrl: 'tpl/entities/patient-bilan/patient-bilans.html',
                                controller: 'PatientBilanController',
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
               
               
                .state('patient-bilan.new', {
                parent: 'patient-bilan',
                        url: '/new',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bilan/patient-bilan-dialog.html',
                                controller: 'PatientBilanDialogController',
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
                        $state.go('patient-bilan', null, {reload: 'patient-bilan'});
                        }, function () {
                        $state.go('patient-bilan');
                        });
                        }]
                })
                .state('patient-bilan.edit', {
                parent: 'patient-bilan',
                        url: '/{id}/edit',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bilan/patient-bilan-dialog.html',
                                controller: 'PatientBilanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: ['PatientBilan', function (PatientBilan) {
                                return PatientBilan.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-bilan', null, {reload: 'patient-bilan'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                })
                .state('patient-bilan.delete', {
                parent: 'patient-bilan',
                        url: '/{id}/delete',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-bilan/patient-bilan-delete-dialog.html',
                                controller: 'PatientBilanDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                entity: ['PatientBilan', function (PatientBilan) {
                                return PatientBilan.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-bilan', null, {reload: 'patient-bilan'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                });
        }

})();

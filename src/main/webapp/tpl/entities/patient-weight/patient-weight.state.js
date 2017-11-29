(function () {
'use strict';
        angular
        .module('app')
        .config(stateConfig);
        stateConfig.$inject = ['$stateProvider'];
        function stateConfig($stateProvider) {
        $stateProvider
                .state('patient-weight', {
                parent: 'entity',
                        url: '/patient-weight?page&sort&search',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        views: {
                        'content@app': {
                        templateUrl: 'tpl/entities/patient-weight/patient-weights.html',
                                controller: 'PatientWeightController',
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
               
               
                .state('patient-weight.new', {
                parent: 'patient-weight',
                        url: '/new',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-weight/patient-weight-dialog.html',
                                controller: 'PatientWeightDialogController',
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
                        $state.go('patient-weight', null, {reload: 'patient-weight'});
                        }, function () {
                        $state.go('patient-weight');
                        });
                        }]
                })
                .state('patient-weight.edit', {
                parent: 'patient-weight',
                        url: '/{id}/edit',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-weight/patient-weight-dialog.html',
                                controller: 'PatientWeightDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: ['PatientWeight', function (PatientWeight) {
                                return PatientWeight.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-weight', null, {reload: 'patient-weight'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                })
                .state('patient-weight.delete', {
                parent: 'patient-weight',
                        url: '/{id}/delete',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-weight/patient-weight-delete-dialog.html',
                                controller: 'PatientWeightDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                entity: ['PatientWeight', function (PatientWeight) {
                                return PatientWeight.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-weight', null, {reload: 'patient-weight'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                });
        }

})();

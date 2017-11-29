(function () {
'use strict';
        angular
        .module('app')
        .config(stateConfig);
        stateConfig.$inject = ['$stateProvider'];
        function stateConfig($stateProvider) {
        $stateProvider
                .state('patient-situation', {
                parent: 'entity',
                        url: '/patient-situation?page&sort&search',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        views: {
                        'content@app': {
                        templateUrl: 'tpl/entities/patient-situation/patient-situations.html',
                                controller: 'PatientSituationController',
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
               
               
                .state('patient-situation.new', {
                parent: 'patient-situation',
                        url: '/new',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-situation/patient-situation-dialog.html',
                                controller: 'PatientSituationDialogController',
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
                        $state.go('patient-situation', null, {reload: 'patient-situation'});
                        }, function () {
                        $state.go('patient-situation');
                        });
                        }]
                })
                .state('patient-situation.edit', {
                parent: 'patient-situation',
                        url: '/{id}/edit',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-situation/patient-situation-dialog.html',
                                controller: 'PatientSituationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                entity: ['PatientSituation', function (PatientSituation) {
                                return PatientSituation.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-situation', null, {reload: 'patient-situation'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                })
                .state('patient-situation.delete', {
                parent: 'patient-situation',
                        url: '/{id}/delete',
                        data: {
                        authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                        $uibModal.open({
                        templateUrl: 'tpl/entities/patient-situation/patient-situation-delete-dialog.html',
                                controller: 'PatientSituationDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                entity: ['PatientSituation', function (PatientSituation) {
                                return PatientSituation.get({id: $stateParams.id}).$promise;
                                }]
                                }
                        }).result.then(function () {
                        $state.go('patient-situation', null, {reload: 'patient-situation'});
                        }, function () {
                        $state.go('^');
                        });
                        }]
                });
        }

})();

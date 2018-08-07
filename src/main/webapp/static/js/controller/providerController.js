'use strict';

angular.module('provider').controller('ProviderController', ['$scope','$http','ProviderDetailService', function($scope,$http,ProviderDetailService) {
	var vm = this;
	vm.providerDetails = {};
	vm.filter = {};
	vm.selectedFields = [];
	vm.submit = submit;
	vm.reset = reset;

	fetchAllDetails();

	function fetchAllDetails(){
		ProviderDetailService.fetchAllDetails()
		.then(
				function(p) {
					vm.providerDetails = p;
				},
				function(errResponse){
					console.error('Error while fetching Users');
				}
		);
	}




	function submit() {
		
		vm.selectedFields =  $('select').multipleSelect('getSelects');
		
        $http.get('/providers',{cache: true,params:{
		  'max_discharges':vm.filter.maxTotalDischarges,
		  'min_discharges':vm.filter.minTotalDischarges,
		  'max_average_covered_charges':vm.filter.maxAverageCoveredCharges,
		  'min_average_covered_charges':vm.filter.minAverageCoveredCharges,
		  'max_average_medicare_payments':vm.filter.maxAverageMedicarePayments,
		  'min_average_medicare_payments':vm.filter.minAverageMedicarePayments,
		  'state':vm.filter.state,
		  'fields':vm.selectedFields
		}})
            .then(
            function (response) {
            	vm.providerDetails = response.data;
            },
            function(errResponse){
                console.error('Error while fetching details');
            }
        );
    
		
	}

	function reset(){
		vm.selectedFields = [];
		vm.filter = {};
		$('select').multipleSelect('uncheckAll');
		fetchAllDetails();
	}

}]);

'use strict';

angular.module('provider').factory('ProviderDetailService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = '/providers';

    var factory = {
    	fetchAllDetails: fetchAllDetails,
    };

    return factory;

    function fetchAllDetails() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI,{cache: true})
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
        
}]);

var app = angular.module('app', [])

app.controller('tableController', ['$scope', '$http', function($scope, $http){
    $http.get("http://www.vishalkuo.com/phpGet.php")
    .success(function(response){
        $scope.names = response
    })
}])
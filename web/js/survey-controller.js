var app = angular.module('app', [])


app.controller('emailController', ['$scope', '$http', function($scope, $http){
    $scope.editingData = []
    maxID = 0
    $http.get("http://www.vishalkuo.com/emailList.php")
    .success(function(response){
        $scope.emails = response        
    })
}])
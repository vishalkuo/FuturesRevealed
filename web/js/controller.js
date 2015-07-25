var app = angular.module('app', [])


app.controller('tableController', ['$scope', '$http', function($scope, $http){
    $scope.editingData = []
    
    $http.get("http://www.vishalkuo.com/phpGet.php")
    .success(function(response){
        for (var i = 0; i < response.length; i++){
            $scope.editingData[response[i].id] = false
        }
        
        console.log($scope.editingData)
        $scope.names = response
        
    })
    
    $scope.modify = function(tableData){
        $scope.editingData[tableData.id] = true
    }
    
    $scope.update = function(tableData){
        $scope.editingData[tableData.id] = false
    }
}])
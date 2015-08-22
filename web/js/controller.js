var app = angular.module('app', [])


app.controller('tableController', ['$scope', '$http', function($scope, $http){
    $scope.editingData = []
    maxID = 0
    $http.get("http://www.vishalkuo.com/phpGet.php")
    .success(function(response){
        for (var i = 0; i < response.length; i++){
            response[i].newEntry = false;
            response[i].delete = false;
            maxID = (response[i].id > maxID)? response[i].id : maxID
        }
        $scope.names = response
    })
    
    $scope.modify = function(tableData){
        $scope.editingData[tableData.id] = true
    }
    
    $scope.update = function(tableData){
        $scope.editingData[tableData.id] = false
    }
    
    $scope.addSurvey= function(){
        var survey = {
            name: $scope.name,
            description: $scope.description,
            url: $scope.url,
            newEntry: true,
            id: ++maxID
        }    
        $scope.editingData[maxID] = false
        $scope.names.push(survey)
        
        $scope.name = ''
        $scope.description = ''
        $scope.url = ''
    }
    
    $scope.deleteSurvey = function(tableData){
        var index = -1
        for (var i = 0; i < $scope.names.length; i++){
            if ($scope.names[i].id === tableData.id){
                index = i
                $scope.names[i].delete = true
                break
            }
        }
        if (index === -1){
            alert("Oh no!")
        }
        
        $scope.names.splice(index, 1)
    }
    
    $scope.updateData = function(){
        $http.post('http://localhost/updateEmails.php', {'contents': $scope.names})
        .then(function(response){
            console.log(response)
        })
    }
}])
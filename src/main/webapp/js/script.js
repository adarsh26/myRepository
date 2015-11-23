var app = angular.module('myApp', ['ngRoute','ngSanitize']);
app.config(function($routeProvider){
    $routeProvider
        .when("/",{
            templateUrl:'navigation/login.html'
        })
        .when("/signup",{
            templateUrl:'navigation/signup.html'
        })
        .when("/dashboard",{
            templateUrl:'navigation/dashboard.html'
        })

});
app.controller('mainController', function ($scope, $http,$rootScope,$location,$rootScope)
{
    $rootScope.session="false";  //session variable

    ///////////////////////logout/////////////////////////
    $scope.logout= function()
    {
        $http.post("/logout").then(function(response){
            if(response.data=="ACCEPTED"){
                $rootScope.session="false";
                $location.path("/");
            }
        },function(){});
    }
    /////////////////////////////////////////////////////////
    /**********************Session Check****************************************/
    $http.post("/session").then(function(response){
       if(response.data.email!="Guest"){
           $rootScope.session= "true";}

    },function(error){});

    /**********************Session Check End****************************************/

/**********************************************************/
    //////////////////search////////////////////
    $scope.clients=null;
    $scope.search =function(data){
//        alert(data);
        var option={
            method:"POST",
            url:"/search",
            data: data
        };
        console.log(data);
        if(data!=null){
            console.log("inside if");
            $http.post("/search",data).then(function(response){
                console.log(response.data);
                $scope.clients=response.data.client;
                console.log($scope.clients);


            },function(error){});}
        else
        {
            $scope.clients=null;
        }

    }
    ///////////////////////////////////////////////////////////////////////


    if($rootScope.session=="true"){

        $scope.dashboard =function(){
            $location.path("/dashboard");
        }
    }


});


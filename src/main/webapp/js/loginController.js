/**
 * Created by ADARSH on 18-11-2015.
 */
app.controller('loginController',function($scope,$location,$http,$rootScope){

        $scope.user = {};
       if($rootScope.session=="true")
       {
           $location.path("/dashboard");
       }
        else {
           $scope.login = function (user) {
               var option = {
                   method: "POST",
                   url: "/login",
                   data: user
               };
               $http.post("/login", user).then(function (response) {
                   console.log(response);
                   if (response.statusText == 'Accepted') {
                       $rootScope.session="true";
                       $location.path("/dashboard");
                   }
                   else{
                       console.log("in else")
                       alert("Credentials Not Matched..");
                       $scope.user={};
                   }
               }, function (error) {
                   alert("Credentials Not Matched..");
                   $scope.user={};


               });


           }
       }




});

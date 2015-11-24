/**
 * Created by ADARSH on 18-11-2015.
 */
app.controller('signupController',function($scope,$http,$rootScope,$location){
 $scope.user={};
    $scope.register={};
    if($rootScope.session=="true") {

       $location.path("/dashboard");

    }
    else{

    $scope.checkusername = function(username){
        console.log(username);
        $http.post("/checkusername",username).then(function(response){
          if(response.data=="NOT_ACCEPTABLE") {
              alert("Username Not Available");
              $scope.register.email = "";
          }
            else{
  $scope.matchPassword = function(){

      if($scope.register.password!=$scope.register.confirmpassword)
      {
          alert("Password Not Matched");
          $scope.register.password="";
          $scope.register.confirmpassword="";
      }

  }
              $scope.Register = function (user) {
                  console.log(user);

                  console.log("Reached..");
                  var option={
                      method:"POST",
                      url:"/signup",
                      data:user
                  };
                  $http(option).then(function(response){
                      console.log(response);
                      if(response.data == "ACCEPTED")
                      {
                          alert("Successfully Registered!");
                          $location.path("/");
                          $scope.user={};
                      }

                  },function(error){});

              }
          }
        },function(error){});
    }
    }
});

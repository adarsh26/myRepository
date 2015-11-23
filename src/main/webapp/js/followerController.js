/**
 * Created by adarsh on 17/11/15.
 */
app.controller('followerController',function($scope,$http){

   console.log("in controller");
    $http.post("/alluser").then(function(response){
        Console.log(response);

    });
});

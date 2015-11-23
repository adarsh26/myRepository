/**
 * Created by ADARSH on 18-11-2015.
 */
app.controller('dashboardController',function($scope,$http,$location,$rootScope,$interval){
    $scope.name={};
    $scope.email={};
    $scope.imga=null;
    $scope.usertweets={};
    $scope.follower="";
    $scope.followin={};
    $scope.mobile=null;
    $scope.gen=null;
    $scope.page="/navigation/feeds.html";

        if($rootScope.session=='false'){
            console.log("session not exist");
            $location.path("/");

        }
      else{
            console.log("session  exist");
            ////////////////////////reload tweets data/////////


            ///////////////////////////////////////////////////
            /////////////////////user Data//////////////////
            $http.post("/dashboard").then(function(response){
                console.log(response);
               $scope.imga=response.data.imag;
                $scope.mobile=response.data.phone;
                $scope.gen=response.data.gender;
                $scope.name=response.data.name;
                $scope.email=response.data.mail;
            });
            //////////////////////////////////////////////////////////////
            /////////show emotion///////////

            $scope.showEmotion = function () {
                angular.element(document.querySelector( '#emotion-pic' )).css("visibility","visible");
            }
            $scope.hideEmotion = function () {
                angular.element(document.querySelector( '#emotion-pic' )).css("visibility","hidden");
            }
            ////////////////////////////////////add Emotion/////
            $scope.addEmotion = function(data){
         var content= angular.element(document.querySelector('#tweetcontent')).html() + "<img src='../images/emotions/"+data+"'>";
                 console.log(content)
                console.log(data);
                angular.element(document.querySelector('#tweetcontent')).html(content);
            }
            ///////////////////////////////////////////////////
            ///////////////////////follow function ///////////////////////
            $scope.follow = function(email){
                console.log("fired follow Request");
                $http.post("/follow",email).then(function(response){
                    $http.post("/following").then(function(response){
                        console.log(response);
                        $scope.following=response.data;
                        console.log("following:" +$scope.following);
                    });
                    $http.post("/alluser").then(function(response){
                        console.log(response);
                        $scope.alluser=response.data.alluser;
                        $http.post("/followcount").then(function(response){
                            console.log(response);
                            $scope.follower=response.data.followes;
                            $scope.followin=response.data.following;

                        });

                    });

                });
            }
            /////////////////////////////////////////////////////////////////////////////
            //////////////////////////////unfollow function////////////////////////////

            $scope.unfollow = function(email){
                        console.log("unfollow"+email);
                $http.post("/unfollow",email).then(function(response){

                    $http.post("/following").then(function(response){
                        console.log(response);
                        $scope.following=response.data;
                        console.log("following:" +$scope.following);
                    });
                    $http.post("/alluser").then(function(response){
                        console.log(response);
                        $scope.alluser=response.data.alluser;


                        $http.post("/followcount").then(function(response){
                            console.log(response);
                            $scope.follower=response.data.followes;
                            $scope.followin=response.data.following;

                        });
                    });

                });
            }
///////////////////////////////////////////////////////////////////////////
            $scope.following={}
   $http.post("/following").then(function(response){
       console.log(response);
       $scope.following=response.data;
       console.log("following:" +$scope.following);
   });

  ////////////////////////////Auto Refresh/////////////////////////////////////////////
            $interval(function(){
                $http.post("\gettweet").then(function(response){
                    console.log(response.data);
                    $scope.usertweets=response.data;
                },function(error){});
            },120000);

  ////////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////

            ////////////////////Tweets of followers & of own//////////////////////////////
            $http.post("\gettweet").then(function(response){
                console.log(response.data);
             $scope.usertweets=response.data;
            },function(error){});
            ////////////////////////////////////////////////////////////////////////////

            /////////////////////count function tells no of followers & following?//////
$http.post("/followcount").then(function(response){
    console.log(response);
    $scope.follower=response.data.followes;
    $scope.followin=response.data.following;

});
            //////////////////////////////////////////////////////////////////////////////
            $scope.alluser={};
            $scope.mytweets={};
            ///////////////////who to follow//////////////////////////////////////////////
            $http.post("/alluser").then(function(response){
                console.log(response);
                $scope.alluser=response.data.alluser;

            });
            //////////////////////////////////////////////////////////////////////////////\

            //////////////////////////////page to include/////////////////////////////////
            $scope.include= function(data){

                ///////////////load my tweets////////////////////////
                if(data=='mytweets.html'){
                    $http.post("/mytweets").then(function(response){
                        console.log(response);
                        $scope.mytweets=response.data;


                    },function(error){});

                }

           /////////////////////////////////////////////////////load
           //////////////////////////////////////////////

               $scope.page="/navigation/"+data;

            }

            ////////////////////////////////////tweet function/////////////////////////////////////////////////////////
            $scope.tweet= function() {
                $scope.msg = angular.element(document.querySelector('#tweetcontent')).html();
                angular.element(document.querySelector('#tweetcontent')).html("");
                $http.post("/tweeting", $scope.msg).then(function (response) {
                    //   $location.path("/dashboard");
                    $http.post("\gettweet").then(function(response){
                        console.log(response.data);
                        $scope.usertweets=response.data;
                    },function(error){});

                }, function (error) {
                });
            }

////////////////////////////////////////////////////////////
            ///////////////change Password/////

            $scope.changePassword= function(change)
            {
                if(change.newpass==change.repass){
                    $http.post("/changepassword",change).then(function(response){
                        console.log(response);
                        if(response.data=="ACCEPTED")
                        {
                            alert("Password Change Successfully!");
                            change=null;
                        }
                        else
                        {
                            alert("Wrong Password!");
                        }

                    },function(error){});
                }
                else{
                    alert("Password not match");
                }

            }
        }




});

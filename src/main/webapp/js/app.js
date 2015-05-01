angular.module('starter', [])
    
    .controller('MainCtrl', function($scope,$http){

        $scope.notConnected = true;
        $scope.source = "";
        $scope.id = "";
        $scope.text = "";
        $scope.subs = "";
        $scope.messages = [ ];
        $scope.newMessages = [ ];
        
        $scope.connect = function(){
            $scope.source = new EventSource(document.location.toString()+"api/ws/sub/"+$scope.id);
            $scope.source.onopen = function(event){
                console.log("opened");
            };
            $scope.source.onmessage = function(event) {
                console.log(event.data);
                var message = JSON.parse(event.data);
                console.log(message);
                $scope.newMessages.push(message);
                $scope.$apply();
            };
            $scope.source.onerror = function(event){
                console.log("error");
            };
            $scope.source.onclose = function(event){
                console.log("closed");
            };
            $scope.notConnected = false;
        };
        
        $scope.flush = function(){
            $scope.messages = $scope.messages.concat($scope.newMessages);
            $scope.newMessages = [ ];
        };
        
        $scope.send = function(){
            var tmp = {
                to: $scope.subs,
                who: $scope.id,
                text: $scope.text,
                date: new Date().getTime()
            };
            
            $http.post(document.location.toString()+"api/rest/post",tmp,{"Content-Type":"application/json"})
                .success(function(data){
                    console.log("Posted successfully");
                    $scope.text = "";
                })
                .error(function(data){
                    console.log("Kinda erroneous");
                });
        };
    });

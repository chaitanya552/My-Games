var app=angular.module("HangmanApp",[]);
app.controller("GameController",['$scope','$timeout',function($scope,$timeout){
var words=["california", "alaska","arizona","colorado","connecticut","delaware", "newyork","arizona","florida","illinois","massachusetts","nevada","texas","virginia"];

$scope.input={
	letter : ''
}
var selectRandomWord=function(){
	var index= Math.round(Math.random()*(words.length-1));
	return words[index];

}

var newGame = function(){
	$scope.incorrectLettersChosen=[];
	$scope.correctLettersChosen=[];
	$scope.guesses=10;
    $scope.displayWord='';

    selectedWord=selectRandomWord();
    console.log(selectedWord);
    var tempDisplayWord='';
    for (var i = 0; i < selectedWord.length; i++) {
    	tempDisplayWord+='*';
    }
    console.log(tempDisplayWord);
    $scope.displayWord=tempDisplayWord;
    
}

$scope.letterChosen = function() {
	console.log("working");
	// Check if $scope.input.letter is a single letter and an alphabet and not an already chosen letter.
		// Check if its correct.
		for(var i=0;i<$scope.correctLettersChosen.length;i++) {
			if($scope.correctLettersChosen[i].toLowerCase()==$scope.input.letter.toLowerCase()) {
				$scope.input.letter="";
				return;
			}
		}
		for(var i=0;i<$scope.incorrectLettersChosen.length;i++) {
			if($scope.incorrectLettersChosen[i].toLowerCase()==$scope.input.letter.toLowerCase()) {
				$scope.input.letter="";
				return;
			}
		}
		var correct=false;
		for(var i=0;i<selectedWord.length;i++) {
			if(selectedWord[i].toLowerCase()==$scope.input.letter.toLowerCase()) {
				$scope.displayWord=$scope.displayWord.slice(0,i)+$scope.input.letter.toLowerCase()+$scope.displayWord.slice(i+1);
				correct=true;

			}
		}
		if(correct) {
			$scope.correctLettersChosen.push($scope.input.letter.toLowerCase());

		}
		else
		{
			$scope.guesses=$scope.guesses-1;
			$scope.incorrectLettersChosen.push($scope.input.letter.toLowerCase());

		}
    $scope.input.letter="";
    if ($scope.guesses==0) {
    	alert("you lost! and the coorect word is: "+selectedWord);
    	$timeout(function(){
    		newGame();
    	},500);
    }
    if($scope.displayWord.indexOf("*")==-1)
    {
    	//that implies there are no stars in dispay word i.e all the letters are guessed

    	alert("Yayy You guessed it right! The word is : "+ selectedWord);
    	$timeout(function(){
    		newGame();
    	},500);
    }
}


newGame();





}])
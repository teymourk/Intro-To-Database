<?php
ini_set('display_errors', 'On');

$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

// if store and location arent emty then it instert in store or displays error message
if(!empty($_POST["Store"]) && !empty($_POST["Location"])) {

	if(!($stmt = $mysqli->prepare("INSERT INTO stores(name, location) VALUES (?,?)"))){
		
		echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
	}

	if(!($stmt->bind_param("ss",$_POST['Store'],$_POST['Location']))) {
		
		echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;		
	}	

	if(!$stmt->execute()){
		echo "Execute failed: "  . $stmt->errno . " " . $stmt->error;

	} else {

		echo "Added " . $_POST["Store"] . " to store lists";

	} 
	
}  else {

	echo "One or more fields are empty";
}

?>

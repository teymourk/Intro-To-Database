<?php

// Debugging turn on (Errors)
ini_set('display_errors', 'On');

// Conncet to server
$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

// if company name is empty send an error else do insert
if(empty($_POST["Company"])) 
{
  echo "One or more fields are empty!";

} else {

	// Insert Query
	if(!($stmt = $mysqli->prepare("INSERT INTO company(name) VALUES (?)"))){
		
		echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
	}

	// Bunding
	if(!($stmt->bind_param("s",$_POST['Company']))) {
		
		echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;		
	}	

	if(!$stmt->execute()){
		echo "Execute failed: "  . $stmt->errno . " " . $stmt->error;

	} else {

		echo "Added " . $_POST["Company"] . "to company lists.";
	}
}

?>


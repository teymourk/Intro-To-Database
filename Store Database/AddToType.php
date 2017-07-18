<?php
ini_set('display_errors', 'On');

$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

// if type is empty then error else insert into type
if(empty($_POST["Type"])) 
{
  echo "One or more fields are empty!";

} else {

	if(!($stmt = $mysqli->prepare("INSERT INTO type(type) VALUES (?)"))){
		
		echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
	}

	if(!($stmt->bind_param("s",$_POST['Type']))) {
		
		echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;		
	}	

	if(!$stmt->execute()){
		echo "Execute failed: "  . $stmt->errno . " " . $stmt->error;

	} else {

		echo "Added " . $_POST["Type"] . " to type list";
	}
}

?>


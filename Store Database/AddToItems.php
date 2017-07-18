<?php

// Debugging turn on (Errors)
ini_set('display_errors', 'On');

// if company name is empty send an error else do insert
$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

// Error if any of the field are empty
if(empty($_POST["Name"]) || empty($_POST["Price"])) 
{
  echo "One or more fields are empty!";

} else {

	// Insert Query
	if(!($stmt = $mysqli->prepare("INSERT INTO item(type_id, company_id, price, name) VALUES (?,?,?,?)"))){
		
		echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
	}

	if(!($stmt->bind_param("iids",$_POST['Type'],$_POST['Company'],$_POST['Price'],$_POST['Name']))) {
		
		echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;		
	}	

	if(!$stmt->execute()){
		echo "Execute failed: "  . $stmt->errno . " " . $stmt->error;

	} else {

		echo "Added " . $_POST["Name"] . "$" . $_POST["Price"] . " To Store List";
	}
}

?>


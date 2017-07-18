<?php
ini_set('display_errors', 'On');

$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

// If first option is to add to store list then it does insert
if($_POST["Update"] == 1) {

	// if store description for item isnt empty.
	if(!empty($_POST["Description"])) {

		//query to inster the item and store to the list
		if(!($stmt = $mysqli->prepare("INSERT item_stores(item_id, store_id, description) VALUES (?,?,?)"))) {
	
			echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
		}

		if(!($stmt->bind_param("iis",$_POST['Items'], $_POST['Store'], $_POST['Description']))){
		
			echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;
		}

		if(!$stmt->execute()){
			echo "Execute failed: "  . $stmt->errno . " " . $stmt->error; 
		
		} else {

			echo "Item Successfully Added To Store";
		}

	} else {

		echo "Description Cant Be Empty!!";
	}

// option for deleting if selected
} else  {

	// delete qury 
	if(!($stmt = $mysqli->prepare("DELETE FROM item_stores WHERE item_id = ? AND store_id = ?"))) {
		
		echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
	}

	if(!($stmt->bind_param("ii",$_POST['Items'], $_POST['Store']))){
		echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;
	}

	if(!$stmt->execute()){
		echo "Execute failed: "  . $stmt->errno . " " . $stmt->error;

	} else {

		echo "Deleted Item From Items List";
	}
}


?>
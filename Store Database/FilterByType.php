<?php
ini_set('display_errors', 'On');

$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
}

?>

<!DOCTYPE html PUBLIC>
<html>
<legend>Items Available</legend>
      <table cellspacing="14">
        <tr>
            <td><b>Name</b></td>
            <td><b>Prcice</b></td>
            <td><b>Description</b></td>
            <td><b>Stores</b></td>
        </tr>
<?php

//Query for filtering the items by the option chosen.
if(!($stmt = $mysqli->prepare("SELECT DISTINCT i.name, i.price, s.name, im.description FROM item i
                               INNER JOIN item_stores im ON im.item_id = i.item_id
                               INNER JOIN stores s ON s.store_id = im.store_id
                               WHERE i.type_id = ?"))) { 
		
	echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
}

if(!($stmt->bind_param("i",$_POST['FilterType']))){
	echo "Bind failed: "  . $stmt->errno . " " . $stmt->error;
}

if(!$stmt->execute()) {
  echo "Excution Failed: " . $mysqli->connect_errno . " " . $mysqli->connect_errno;
}

if(!$stmt->bind_result($name, $price, $storeName, $description)) {
  echo "Bind Failed" . $mysqli->connect_errno . " " . $mysqli->connect_errno;
}

// display all the items found based on the filter type.
while($stmt->fetch()){

    echo "<tr>\n<td>\n" . $name . "\n</td>\n<td>\n" . $price . "\n</td>\n<td>\n" . $description  . "\n</td>\n<td>\n". $storeName . "\n</td>\n<td>\n";
}
$stmt->close();
?>
    </table>  
		</fieldset>
	</form>
	</div>
    </body>
</html>

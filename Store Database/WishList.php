<?php
//Turn on error reporting
ini_set('display_errors', 'On');
//Connects to the database
$mysqli = new mysqli("oniddb.cws.oregonstate.edu","teymourk-db","AyJjIciZlo81sQbj","teymourk-db");
if($mysqli->connect_errno){
  echo "Connection error " . $mysqli->connect_errno . " " . $mysqli->connect_error;
  }
?>

<!DOCTYPE html PUBLIC>
<html>
  <head>
    <meta charset="UTF-8">
    <title>WishList</title>
    <link rel="stylesheet" href="wishList.css" type="text/css">
    </head>

    <!-- All the banners and details about how to use are located here. -->
    <body style="background-color: #F4F6F6;">
    <div class="banner">
    	<h1 class="header" style="color:blue";>Welcome To Items Database Creator!</h1>
      <p style="color:white";>------------------------------------------------------------------------------------------------------------------</p>
      <p style="color:white";> - This is a databse of bunch of items that shows what stores have the items in the table below. Items could be added or removed from this database. </p>
      <p style="color:white";> - To Add an Item that already exists in the database, just go to Update Store's Items and choose add and select which store you want this item to be and write a description for that item or else create your item first.</p> 
      <p style="color:white";> - If your store, type, company doesnt exist for your item, make sure to create them before persuing to adding it to the list.</p>
      <p style="color:white";> - If you choose to delete and Item for the database, make sure to choose the right item and what store that Item is already exists in.</p>
      <p style="color:white";> - Lastly, after every request make sure to refresh the page for changes to take effect. Thank you for using our service :)</p>
    </div>
    <div>

    <!-- THis is where all the divs for adding are inserted -->
    <form method="post" action="AddToItems.php">
		<fieldset class="nameField">
			<legend style="color:red;">Create an Item</legend>
        <p>Item Name: <input type="text" name="Name" /></p>
        <p>Item Price: <input type="text" name="Price" /></p>
        <p>Select Item Company From List <select name="Company">
        <?php
        if(!($stmt = $mysqli->prepare("SELECT company_id, name FROM company"))){
          echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
        }

        if(!$stmt->execute()){
          echo "Execute failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
        }
        if(!$stmt->bind_result($company_id, $name)){
          echo "Bind failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
        }
        while($stmt->fetch()){
            echo '<option value=" '. $company_id . ' "> ' . $name . '</option>\n';
        }
        $stmt->close();
        ?>
        </select><p>
        <p>Select Item Type From List <select name="Type">
        <?php
        if(!($stmt = $mysqli->prepare("SELECT type_id, type FROM type"))){
          echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
        }

        if(!$stmt->execute()){
          echo "Execute failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
        }
        if(!$stmt->bind_result($type_id, $item_type)){
          echo "Bind failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
        }
        while($stmt->fetch()){
            echo '<option value=" '. $type_id . ' "> ' . $item_type . '</option>\n';
        }
        $stmt->close();
        ?>
        </select><p>
        <p><input class="Create" type="submit" value="Create" /></p>
		</fieldset>
    </form>
        <form method="post" action="DeleteItem.php"> 
    <fieldset class="other_tables">
    <legend style="color:red;">Update Store's Items</legend>
    <select name="Update">Delete Or Add
    <option value=1>Add</option>
    <option value=2>Delete</option>
    </select>
    <p>Item List <select name="Items">
    <?php

    // Query for selectiong an item based on ID
    if(!($stmt = $mysqli->prepare("SELECT item_id, name FROM item"))){
      echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
    }

    if(!$stmt->execute()){
      echo "Execute failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
    }
    if(!$stmt->bind_result($item_id, $item_name)){
      echo "Bind failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
    }
    while($stmt->fetch()){
        echo '<option value=" '. $item_id . ' "> ' . $item_name . '</option>\n';
    }
    $stmt->close();
    ?>
    </select></p>
    <p>Store List <select name="Store">
    <?php

    // Query for selectiong an item based on sotre_ID
    if(!($stmt = $mysqli->prepare("SELECT store_id, name FROM stores"))){
      echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
    }

    if(!$stmt->execute()){
      echo "Execute failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
    }
    if(!$stmt->bind_result($store_id, $store_name)){
      echo "Bind failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
    }
    while($stmt->fetch()){
        echo '<option value=" '. $store_id . ' "> ' . $store_name . '</option>\n';
    }
    $stmt->close();
    ?>

    <!-- Div for adding or deleting items from store database -->
    </select></p>
    <b>Only When Adding.......!</b>
    <p>Item Description: <input type="text" name="Description" /></p>
    <input class="Create" type="submit" value="Add" /></p>
    </fieldset>
    </form>
    <form method="post" action="AddToStore.php">
    <fieldset class="other_tables">
    <legend style="color:red;">Add Store To Table</legend>
      <p>Store Name: <input type="text" name="Store" /></p>
      <p>Location: <input type="text" name="Location" /></p>
      <input class="Create" type="submit" value="Add" /></p>
    </fieldset>
    </form>
    
    <form method="post" action="AddToType.php"> 
    <fieldset class="other_tables">
    <legend style="color:red;">Add Type To Table</legend>
      <p>Type Name: <input type="text" name="Type" /></p>
      <input class="Create" type="submit" value="Add" /></p>
    </fieldset>
    </form>
    <form method="post" action="AddToCompany.php"> 
    <fieldset class="other_tables">
    <legend style="color:red;">Add Company To Table</legend>
      <p>Name: <input type="text" name="Company" /></p>
      <input class="Create" type="submit" value="Add" /></p>
    </fieldset>
    </form>
    <form method="post" action="FilterByType.php"> 
    <fieldset class="other_tables">
    <legend style="color:red;">Filter By Type</legend>
     <p>Pick Type<select name="FilterType">
          <?php

          // query to get item types 
          if(!($stmt = $mysqli->prepare("SELECT type_id, type FROM type"))){
            echo "Prepare failed: "  . $stmt->errno . " " . $stmt->error;
          }

          if(!$stmt->execute()){
            echo "Execute failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
          }
          if(!$stmt->bind_result($type_id, $item_type)){
            echo "Bind failed: "  . $mysqli->connect_errno . " " . $mysqli->connect_error;
          }
          while($stmt->fetch()){
              echo '<option value=" '. $type_id . ' "> ' . $item_type . '</option>\n';
          }
          $stmt->close();
          ?>
      </select><p>
      <input class="Create" type="submit" value="Search" /></p>
    </fieldset>
    </form>
		<fieldset class="ItemInfo">
    <legend style="color:red;">Items Available</legend>
      <table cellspacing="14">
        <tr style="color:blue";>
            <td><b>Name</b></td>
            <td><b>Prcice</b></td>
            <td><b>Description</b></td>
            <td><b>Company</b></td>
            <td><b>Type</b></td>
            <td><b>Stores</b></td>
            <td><b>Location</b></td>
        </tr>
<?php

// Query to fetch all the items details by inner joining between all the table
if(!($stms = $mysqli->prepare("SELECT i.name, i.price, s.name, im.description, c.name, s.location, t.type FROM item i
                               INNER JOIN item_stores im ON im.item_id = i.item_id
                               INNER JOIN stores s ON s.store_id = im.store_id
                               INNER JOIN company c ON c.company_id = i.company_id
                               INNER JOIN type t ON t.type_id = i.type_id"))) {

  echo "Prepare Failed " . $stms->errno . " " . $stms->error;
}

if(!$stms->execute()) {
  echo "Excution Failed: " . $mysqli->connect_errno . " " . $mysqli->connect_errno;
}

if(!$stms->bind_result($name, $price, $storeName, $description, $companyName, $location, $type)) {
  echo "Bind Failed" . $mysqli->connect_errno . " " . $mysqli->connect_errno;
}

while($stms->fetch()){
 echo "<tr>\n<td>\n" . $name . "\n</td>\n<td>\n" . "$" . floatval($price) . "\n</td>\n<td>\n". $description . "\n</td>\n<td>\n" . $companyName . "\n</td>\n<td>\n". $type . "\n</td>\n<td>\n". $storeName . "\n</td>\n<td>\n". $location ."\n</td>\n<td>\n";
}
$stms->close();
?>
    </table>  
		</fieldset>
	</form>
	</div>
    </body>
</html>
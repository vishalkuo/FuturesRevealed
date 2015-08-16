<?php
    require ('dbInfo.php');
    $newdb = "vishzlkf_accesstest";
    $var = json_decode(file_get_contents("php://input"));
    mysql_connect(localhost, $username, $password);
    @mysql_select_db($newdb) or die ("Unable to find databse");
    foreach($var as $obj){
       $isInsert = ($obj->contents->newEntry);
        $url = $obj->contents->url;
        $name = $obj->contents->name;
        $description = $obj->contents->description;
        $id = $obj->contents->id;
        $query ="";
        if ($isInsert){
            $query = "INSERT INTO survyes (id, name, url, description) VALUES ('$id', '$name', '$url', '$description')";
        } else {
            $query = "DELETE FROM survyes WHERE id = '$id'";
        }
        echo $query;
        mysql_query($query);        
    }
    mysql_close();
?>

<?php
    header('Content-Type: application/json');
    header('Access-Control-Allow-Origin: *');
    require ('dbInfo.php');
    $newdb = "vishzlkf_emails";
    $output;


    mysql_connect(localhost, $username, $password);
    @mysql_select_db($newdb) or die ("Unable to find databse");

    $query = ("SELECT * FROM emails");

    $r = mysql_query($query);

    while ($response = mysql_fetch_assoc($r)){
        $output[] = $response;
    }

    print (json_encode($output));

    mysql_close();
?>
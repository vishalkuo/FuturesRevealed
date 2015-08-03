<?php
    include_once 'dbInfo.php';
    header('Content-Type: application/json');
    echo $_POST;
    $message = json_decode(file_get_contents('php://input'), true);
    $contents = $message[0]['contents'];
    echo $contents;
?>

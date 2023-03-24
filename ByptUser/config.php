<?php
    $servername = "localhost";
    $username = "root";
    $password = "";

    //create connection
    $conn = new mysqli($servername, $username, $password);

    //Check connection
    if($conn->connect_error){
        echo "Connection failed"  . $conn->connect_error;
    }
    else{
        echo "connection established";
    }
?>
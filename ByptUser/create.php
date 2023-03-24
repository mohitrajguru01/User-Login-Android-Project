<?php
include "config.php";
$sql = "CREATE DATABASE IF NOT EXISTS ByptDb";
if($conn->query($sql) === TRUE){
    echo "DATABASE created successfully";
        //selecting database
    mysqli_select_db($conn, "ByptDb");
    //creating table
    $sql = "CREATE TABLE IF NOT EXISTS bypt_user(
        `user_id` INT AUTO_INCREMENT primary key,
        `username` VARCHAR(255), 
        `name` varchar(255),
        `email` varchar(255),
        `password` varchar(255),
        `image` varchar(255))";  
    if(mysqli_query($conn, $sql)){  
        echo "Table created successfully";
        
    }else{  
        echo "Could not create table: ". mysqli_error($conn);  
    }  
}else{
    echo "Error creating database";
}
?>
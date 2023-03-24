<?php
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"ByptDb");

$username = $_POST['username'];
$password= $_POST['password'];

$query="SELECT * from `bypt_user` where `username`='$username' and `password`='$password'";

$raw=mysqli_query($connection,$query);

if($result = mysqli_fetch_assoc($raw))
    print(json_encode($result));
else
    echo "false";
?>
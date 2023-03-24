<?php
include "config.php";
include "create.php";

$name = $_POST['name'];
$email = $_POST['email'];
$username = $_POST['username'];
$password= $_POST['password'];
$img_path = $_POST['img_path'];
$img_name=$_POST['img_name'];

$destination = "images/".$img_name;
file_put_contents($destination, base64_decode($img_path));

 $myquery = "INSERT INTO `bypt_user` (`user_id`, `username`, `name`, `email`, `password`, `image`) VALUES (NULL, '$username', '$name', '$email', '$password', '$img_name')";

$execute_myquery = mysqli_query($conn, $myquery);

if ($execute_myquery) {
   echo "Registration Successful";
}
else{
  echo "Registration Failed";
}
?>

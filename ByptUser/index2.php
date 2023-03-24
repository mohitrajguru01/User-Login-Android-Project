<?php
    $connection = mysqli_connect("localhost", "root", "");
    mysqli_select_db($connection, "myuser_db");

    if(isset($_GET['callApi'])){

        $name = $_POST['name'];
        $username = $_POST['username'];
        $email = $_POST['email'];
        $password = $_POST['password'];
        $img_name = $_POST['img_name'];

        if($_FILES['uploadImg']){
            // $img_name = $_FILES['uploadImg']['img_name'];
            $path = $_FILES['uploadImg']['img_path'];
            $destination = "images/".$img_name;
            move_uploaded_file($path, $destination);

            $query="INSERT INTO `myuser_table` (`id`, `name`, `username`, `email`, `password`, `image`)
			      VALUES (NULL, '$name', '$username', '$email', '$password', '$img_name')";
			$response=mysqli_query($connection,$query);
			
			if($response)
			     echo "true";
			else
			    echo "false";
        }
    }
?>
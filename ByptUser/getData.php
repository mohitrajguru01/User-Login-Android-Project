<?php

    $connection = mysqli_connect("localhost", "root", "");
    mysqli_select_db($connection, "ByptDb");

    $query = "SELECT * FROM `bypt_user`";

    $raw = mysqli_query($connection, $query);

    while ($res = mysqli_fetch_array($raw)) {
        $data[] = $res;
    }
    
    print(json_encode($data));

?>
<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $id  = $_GET['id'];
 
 require_once('database.php');
 
 $sql = "SELECT * FROM quickhop WHERE id='".$id."'";
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
 "Name"=>$res['Name'],
 "value"=>$res['value']
 
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }

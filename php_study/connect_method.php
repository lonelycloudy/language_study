<?php
/*php mysql 长连接 短连接
对俩者的链接速度很好奇,就测试了一下...
php5,mysql5.
表里有3000条数据,
短连接查询前1000条,连接断开10次数据库,进行查询;
长连接查询后1000条,连接1次数据库,进行10次查询;
大部分测试,结果类似下边, 短连接耗时117毫秒, 长连接 13 毫秒. 
 sort connection: 414.452 - 296.879: 117.573 millisecond  
 long connection: 427.635 - 414.489: 13.146 millisecond  
*/
//require("db_conn.php");  
$mysql_server_name="localhost"; //数据库服务器名称 "127.0.0.1"  
$mysql_username="aa"; // 连接数据库用户名  
$mysql_password="bb"; // 连接数据库密码  
$mysql_database="dbname"; // 数据库的名字  
$search_count=10;  
$search_number = 100;  
//开始,短链接  
$begin = microtime()*1000;  
for($i=0;$i<$search_count;$i++){  
	$dbConn=mysql_connect($mysql_server_name, $mysql_username,$mysql_password);  
	mysql_select_db($mysql_database, $dbConn);  
	$queryStr = "select * from `ausers` order by uid asc limit ".($i*$search_number)." ".$search_number;            
	$result = mysql_query($queryStr,$dbConn);  
	if(!result){  
		die(mysql_error());  
	}  
	$list = array();  
	while($row=mysql_fetch_array($result)){  
		$list[$row["uid"]] = $row;  
	}  
	mysql_free_result($result);  
	mysql_close($dbConn);  
}  
$end = microtime()*1000;  
echo "sort connection: ".$end." - ".$begin.": ".(($end-$begin))." millisecond<br/>";  
unset($list);  
//开始,长链接  
$begin = microtime()*1000;  
$dbConn=mysql_connect($mysql_server_name, $mysql_username,$mysql_password);  
mysql_select_db($mysql_database, $dbConn);  
for($i=0;$i<$search_count;$i++){  
	$queryStr = "select * from `ausers` order by uid desc limit ".($i*$search_number)." ".$search_number;           
	$result = mysql_query($queryStr,$dbConn);  
	if(!result){  
		die(mysql_error());  
	}  
	$list = array();  
	while($row=mysql_fetch_array($result)){  
		$list[$row["uid"]] = $row;  
	}  
	mysql_free_result($result);  
}  
mysql_close($dbConn);  
$end = microtime()*1000;  
echo "long connection: ".$end." - ".$begin.": ".(($end-$begin))." millisecond";  
/*
Local RedHat Test
sort connection: 507.199 - 410.364: 96.835 millisecond<br/>
long connection: 519.212 - 508.318: 10.894 millisecond
*/



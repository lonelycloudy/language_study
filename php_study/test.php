<?php
/*Step Zero 后台进程执行时间*/
function step_one(){
	set_time_limit(0);
	$args = $argv;
	/*Step One*/	
	$str = "Is your name O'reilly?";
	// 输出：Is your name O\'reilly?	
	//magic_quotes_sybase 
	echo addslashes($str);
}


/*Step-two
addslashes(), stripslashes(), get_magic_quotes_runtime(), and ini_get(). 
*/
function step_two(){
	echo get_magic_quotes_gpc();         // 1
	echo $_POST['lastname'];             // O\'reilly
	echo addslashes($_POST['lastname']); // O\\\'reilly

	if (!get_magic_quotes_gpc()) {
		$lastname = addslashes($_POST['lastname']);
	} else {
		$lastname = $_POST['lastname'];
	}

	echo $lastname; // O\'reilly
	$sql = "INSERT INTO lastnames (lastname) VALUES ('$lastname')";
}
echo 'hello,world';



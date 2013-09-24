<?php
/*函数引用返回*/
$g_a = 11;
$g_b = 12;

echo 'before';
echo "\n\r";
echo 'g_a:'.$g_a;
echo ',g_b:'.$g_b;
echo "\n\r";
function &test($a=null){ 
	$a=$a+1;
	return $a;
}
function &test1(&$a=null){ 
	$a=$a+1;
	return $a;
}
$g_a = test1($g_a);
$g_b = test1($g_b);
echo 'after';
echo "\n\r";
echo 'g_a:'.$g_a;
echo ',g_b:'.$g_b;
echo "\n\r";

/*类方法引用*/
class T_a{
	public $t_a;
	public function __construct($ta=null){ 
		$this->t_a = $ta;
	}
	public function _show(){ 
		
	}
}
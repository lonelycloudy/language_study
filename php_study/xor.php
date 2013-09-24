<?php
$a = 'm';
$b = 'V';
echo ($a^$b);
srand((double)microtime() * 1000000);
$ran= rand(0, 32000);
$encrypt_key = md5($ran);
echo $ran."\n\r";
print_r($encrypt_key);


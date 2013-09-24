<?php
$a = "http://tech.sina.com.cn/t/2013-08-27/01188677643.shtml";
$b = "http://tech.sina.com.cn/t/apple/2013-08-28/03238682257.shtml";
$c = "http://roll.tech.sina.com.cn/tele/gj/index.shtml";
$pattern = "/http:\/\/tech\.sina\.com\.cn\/t\/([0-9]{4})-([0-9]{2})-([0-9]{2})\/\d+\.shtml/";
if(preg_match_all($pattern,$a,$matches)){
	echo "A: ";
	print_r($matches);
}
if(preg_match_all($pattern,$b,$matches)){
	echo "B: ";
	print_r($matches);
}
if(preg_match_all($pattern,$c,$matches)){
	echo "C: ";
	print_r($matches);
}
exit;
$str = '{"channel":"cn.com.sina.tech","seedURL":"http:\/\/tech.sina.com.cn\/tele\/","urlPattern":["http:\/\/tech\\\.sina\\\.com\\\.cn\/t\/\\\w\/\\\d+-\\\d+-\\\d+\\\d+\\\.shtml","http:\/\/tech\\\.sina\\\.com\\\.cn\/t\/\\\d+-\\\d+-\\\d+\\\d+\\\.shtml"],"ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:artibody"}}';//define by php
$str = '{"channel":"cn.com.sina.tech","seedURL":"http://tech.sina.com.cn/tele/","urlPattern":["http://tech.sina.com.cn/t/\\\w/\\\d+-\\\d+-\\\d+\\\d+\\\.shtml","http://tech.sina.com.cn/t/\\\d+-\\\d+-\\\d+\\\d+\\\.shtml"],"ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:artibody"}}';//from java
$str = '{"channel":"cn.com.sina.tech","seedURL":"http://tech.sina.com.cn/tele/","urlPattern":["http://tech.sina.com.cn/t/\\w/\\d+-\\d+-\\d+\\d+\\.shtml","http://tech.sina.com.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml"],"ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:artibody"}}';//from java
$str = '{"channel":"cn.com.sina.tech","seedURL":"http://tech.sina.com.cn/tele/","listRule":"div.main_row","itemRule":"http://tech.sina.com.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml","urlPattern":["http://tech.sina.com.cn/t/\\w/\\d+-\\d+-\\d+\\d+\\.shtml","http://tech.sina.com.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml"],"singleRule":{"title":"h1#artibodyTitle","pubdate":"span#pub_date","content":"div#artibody"}}';
$str = str_replace('\\','\\\\',$str);
var_dump($str);
var_dump(json_decode($str,true));
exit;
echo "\n\r";
$t1 = array(
        "channel"=>"cn.com.sina.tech",
        "seedURL"=>"http://tech.sina.com.cn/tele/",
        "urlPattern"=>array("http://tech\\.sina\\.com\\.cn/t/\\w/\\d+-\\d+-\\d+\\d+\\.shtml",
                                "http://tech\\.sina\\.com\\.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml"
         ),
         "ruleList"=>array("title"=>"id:artibodyTitle","pubdate"=>"id:pub_date","content"=>"id:artibody")
);
print_r($t1);
echo "encode";
echo "\n\r";
$a = json_encode($t1);
var_dump($a);
echo "\n\r";
echo "decode";
//$a = '{"channel":"cn.com.sina.tech","seedURL":"http:\/\/tech.sina.com.cn\/tele\/","urlPattern":["http:\/\/tech\\.sina\\.com\\.cn\/t\/\\w\/\\d+-\\d+-\\d+\\d+\\.shtml","http:\/\/tech\\.sina\\.com\\.cn\/t\/\\d+-\\d+-\\d+\\d+\\.shtml"],"ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:artibody"}}';
$b = json_decode($a,true);
print_r($b);
exit;
/*
############php json 
F:\web_study\php_study>php json.php
string(314) "{"channel":"cn.com.sina.tech","seedURL":"http:\/\/tech.sina.com.cn\
/tele\/","urlPattern":["http:\/\/tech\\.sina\\.com\\.cn\/t\/\\w\/\\d+-\\d+-\\d+\
\d+\\.shtml","http:\/\/tech\\.sina\\.com\\.cn\/t\/\\d+-\\d+-\\d+\\d+\\.shtml"],"
ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:arti
body"}}"
array(4) {
  ["channel"]=>
  string(16) "cn.com.sina.tech"
  ["seedURL"]=>
  string(29) "http://tech.sina.com.cn/tele/"
  ["urlPattern"]=>
  array(2) {
    [0]=>
    string(53) "http://tech\.sina\.com\.cn/t/\w/\d+-\d+-\d+\d+\.shtml"
    [1]=>
    string(50) "http://tech\.sina\.com\.cn/t/\d+-\d+-\d+\d+\.shtml"
  }
  ["ruleList"]=>
  array(3) {
    ["title"]=>
    string(16) "id:artibodyTitle"
    ["pubdate"]=>
    string(11) "id:pub_date"
    ["content"]=>
    string(11) "id:artibody"
  }
}

Array
(
    [channel] => cn.com.sina.tech
    [seedURL] => http://tech.sina.com.cn/tele/
    [urlPattern] => Array
        (
            [0] => http://tech\.sina\.com\.cn/t/\w/\d+-\d+-\d+\d+\.shtml
            [1] => http://tech\.sina\.com\.cn/t/\d+-\d+-\d+\d+\.shtml
        )

    [ruleList] => Array
        (
            [title] => id:artibodyTitle
            [pubdate] => id:pub_date
            [content] => id:artibody
        )

)
encode
string(314) "{"channel":"cn.com.sina.tech","seedURL":"http:\/\/tech.sina.com.cn\
/tele\/","urlPattern":["http:\/\/tech\\.sina\\.com\\.cn\/t\/\\w\/\\d+-\\d+-\\d+\
\d+\\.shtml","http:\/\/tech\\.sina\\.com\\.cn\/t\/\\d+-\\d+-\\d+\\d+\\.shtml"],"
ruleList":{"title":"id:artibodyTitle","pubdate":"id:pub_date","content":"id:arti
body"}}"

decodeArray
(
    [channel] => cn.com.sina.tech
    [seedURL] => http://tech.sina.com.cn/tele/
    [urlPattern] => Array
        (
            [0] => http://tech\.sina\.com\.cn/t/\w/\d+-\d+-\d+\d+\.shtml
            [1] => http://tech\.sina\.com\.cn/t/\d+-\d+-\d+\d+\.shtml
        )

    [ruleList] => Array
        (
            [title] => id:artibodyTitle
            [pubdate] => id:pub_date
            [content] => id:artibody
        )

)
################java json
string(289) "{"channel":"cn.com.sina.tech","seedURL":"http://tech.sina.com.cn/te
le/","urlPattern":["http://tech.sina.com.cn/t/\\w/\\d+-\\d+-\\d+\\d+\\.shtml","h
ttp://tech.sina.com.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml"],"ruleList":{"title":"id:ar
tibodyTitle","pubdate":"id:pub_date","content":"id:artibody"}}"
array(4) {
  ["channel"]=>
  string(16) "cn.com.sina.tech"
  ["seedURL"]=>
  string(29) "http://tech.sina.com.cn/tele/"
  ["urlPattern"]=>
  array(2) {
    [0]=>
    string(50) "http://tech.sina.com.cn/t/\w/\d+-\d+-\d+\d+\.shtml"
    [1]=>
    string(47) "http://tech.sina.com.cn/t/\d+-\d+-\d+\d+\.shtml"
  }
  ["ruleList"]=>
  array(3) {
    ["title"]=>
    string(16) "id:artibodyTitle"
    ["pubdate"]=>
    string(11) "id:pub_date"
    ["content"]=>
    string(11) "id:artibody"
  }
}
######20130902######
F:\web_study\php_study>php json.php
string(386) "{"channel":"cn.com.sina.tech","seedURL":"http://tech.sina.com.cn/te
le/","listRule":"div.main_row","itemRule":"http://tech.sina.com.cn/t/\\d+-\\d+-\
\d+\\d+\\.shtml","urlPattern":["http://tech.sina.com.cn/t/\\w/\\d+-\\d+-\\d+\\d+
\\.shtml","http://tech.sina.com.cn/t/\\d+-\\d+-\\d+\\d+\\.shtml"],"singleRule":{
"title":"h1#artibodyTitle","pubdate":"span#pub_date","content":"div#artibody"}}"

array(6) {
  ["channel"]=>
  string(16) "cn.com.sina.tech"
  ["seedURL"]=>
  string(29) "http://tech.sina.com.cn/tele/"
  ["listRule"]=>
  string(12) "div.main_row"
  ["itemRule"]=>
  string(47) "http://tech.sina.com.cn/t/\d+-\d+-\d+\d+\.shtml"
  ["urlPattern"]=>
  array(2) {
    [0]=>
    string(50) "http://tech.sina.com.cn/t/\w/\d+-\d+-\d+\d+\.shtml"
    [1]=>
    string(47) "http://tech.sina.com.cn/t/\d+-\d+-\d+\d+\.shtml"
  }
  ["singleRule"]=>
  array(3) {
    ["title"]=>
    string(16) "h1#artibodyTitle"
    ["pubdate"]=>
    string(13) "span#pub_date"
    ["content"]=>
    string(12) "div#artibody"
  }
}
*/
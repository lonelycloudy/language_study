<?php

/**
    @过滤HTML标签 保留img
    @
    */
    function cleanhtml($str){
        $str = clearJs($str);
        $str = preg_replace('/　　/Uis', "", $str);
        $str = preg_replace('/<p.*>/Uis', "　　", $str);
        $str = preg_replace('/<\/p>/is', "\r\n\r\n", $str);
        $str = preg_replace('/<\/div>/is', "<\/div>\r\n", $str);
        $str = preg_replace('/<br \/>/is', "\r\n", $str);
        $str = preg_replace('/<br>/is', "\r\n", $str);
        $str = strip_tags($str, '<img>');
        $str = preg_replace('/(<img.*?)style=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)onclick=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)border=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)width=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)height=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)id=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)class=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)alt=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        $str = preg_replace('/(<img.*?)align=(["\'])?.*?(?(2)\2|\s)([^>]+>)/is','$1$3',$str);
        return $str;
    }

    /**
    @过滤JS
    */
    function clearJs($str){
        $pre = array(
          '@<script[^>]*?>.*?</script>@si',
          '@<style[^>]*?>.*?</style>@si',
          '@<!--.*?-->@si'
        );
        $str = preg_replace($pre,'',$str);
        return $str;
    }

/**
@相对路径转为绝对路径
@
@
*/
function relative_to_absolute($content, $feed_url) { 
  preg_match('/(http|https|ftp):\/\//', $feed_url, $protocol); 
  $server_url = preg_replace("/(http|https|ftp|news):\/\//", "", $feed_url); 
  $server_url = preg_replace("/\/.*/", "", $server_url); 

  if ($server_url == '') { 
    return $content; 
  } 

  if (isset($protocol[0])) { 
    $new_content = preg_replace('/href="\//', 'href="'.$protocol[0].$server_url.'/', $content); 
    $new_content = preg_replace('/src="\//', 'src="'.$protocol[0].$server_url.'/', $new_content); 
  } else { 
    $new_content = $content; 
  } 
  return $new_content; 
}
 
$string = "Happyniess Split words";
$pattern = "/(^.{0,10})(\W+.*$)/"; 
$replacement = "$1";
//echo preg_replace($pattern, $replacement, $string); // Returns "Don't"
$str = '<script type="text/javascript">alert("Hello,Filter!")</script><div id="test"><img src="http://i3.sinaimg.cn/IT/deco/2012/1018/content/zwy_tech_logo.gif"  height="30" alt="新浪科技" id="pagelogo_img"/><p> 测试内容查看</p><img class="myimg" width="80px" height="90px"  src="./img/a.jpg"/><img src="/img/cde.jpg"/><img src="/../img/def.jpg"/><a href="/a.php">ahref</a><br><br /><p>测试内容第二段落</p></div>';
$str = cleanhtml($str);
var_dump(relative_to_absolute($str,"http://tech.sina.com.cn/t/2012-04-28/08447036113.shtml"));

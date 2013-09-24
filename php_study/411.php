<?php
if(function_exists('session_start')){
   echo 'yes';
}else{
   echo 'no';
}
print_r(gd_info());

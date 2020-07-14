# Retrofit 2.0

## Results

![Results](https://raw.githubusercontent.com/Ruslan-Aliyev/Android-Retrofit2Get/master/Illustrations/1.png)

## Server:

http://ruslancode.net23.net/retrofit/2.0/get.php?test_name=test_one

http://ruslancode.net23.net/retrofit/2.0/get.php?test_name=test_two

```php
<?php
$res = array();
$res["test_one"] =  array('subject' => "test_1", 'info' => "test_info_1");
$res["test_two"] =  array('subject' => "test_2", 'info' => "test_info_2");
header('Content-Type: application/json');
echo json_encode($res[ $_GET["test_name"] ]);
?>
```

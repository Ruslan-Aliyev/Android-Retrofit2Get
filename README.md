# Retrofit 2.0

## Notable differences

### No more distinction in the adapter interface regarding synchronous and asynchronous requests:

#### Retrofit 2.0
```java
public interface Service {
    @GET("retrofit/{version}/get.php")
    Call<Model> get(@Path("version") String version, @Query("test_name") String test_name);
}
```

#### Retrofit 1.9
```java
public interface Service {
    @GET("/retrofit/{version}/get.php")
    public void getAsync(@Path("version") String version, @Query("test_name") String test_name, Callback<Model> response);

    @GET("/retrofit/{version}/get.php")
    public Model getSync(@Path("version") String version, @Query("test_name") String test_name);
}
```

### Becareful about constructing URLs

#### In 2.0, should be like this
```java
private static final String ROOT_URL = "http://ruslancode.net23.net/";
...
service = new Retrofit.Builder().baseUrl(ROOT_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build().create(Service.class);
```

```java
public interface Service {
    @GET("retrofit/{version}/get.php")
    Call<Model> get(@Path("version") String version, @Query("test_name") String test_name);
}
```

#### Instead of 1.9's
```java
private final String BASE_URL = "http://ruslancode.net23.net/";
...
restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
service = restAdapter.create(Service.class);
```

```java
public interface Service {
    @GET("/retrofit/{version}/get.php")
    public void getAsync(@Path("version") String version, @Query("test_name") String test_name, Callback<Model> response);

    @GET("/retrofit/{version}/get.php")
    public Model getSync(@Path("version") String version, @Query("test_name") String test_name);
}
```

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

# IndoorAtlasExample
Basic Android app to get location updates using IndoorAtlas API

View the video tutorial on [YouTube](https://www.youtube.com/watch?v=2EXkV4xL5rg)

[![Youtube](http://img.youtube.com/vi/2EXkV4xL5rg/1.jpg)](https://www.youtube.com/watch?v=2EXkV4xL5rg)

# Basic Usage

## 1. Get API key

Register at the [IndoorAtlas website](http://developer.indooratlas.com/signup). Create a new Application and you will get an API key and an API secret.

Add the API key and API secret in the AndroidManifest.xml file of your app.

```xml
<application>
  <meta-data
      android:name="com.indooratlas.android.sdk.API_KEY"
      android:value="YOUR KEY"/>
  <meta-data
      android:name="com.indooratlas.android.sdk.API_SECRET"
      android:value="YOUR SECRET"/>
</application>
```


## 2. Add Dependencies

To use IndoorAtlas API, you need to add dependencies in the *build.gradle* file of your application.

```
dependencies {
    compile 'com.indooratlas.android:indooratlas-android-sdk:2.0.2-beta@aar'
}
repositories{
    maven {
        url "http://indooratlas-ltd.bintray.com/mvn-public"
    }
}
```

## 3. Permissions

Add the following permissions to AndroidManifest.xml file of your app.

*Note: If you are targeting Android 6.0+ devices, you need to request permissions at runtime*

```xml
<manifest>
  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
  <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
</manifest>
```

## 4. Create a ``` IALocationListener ``` to receive location

A Location Listener listens to location updates sent by the ```IALocationManager ```.

```java
IALocationListener mLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            //Location updates will be received here  
            //iaLocation.getLatitude() and iaLocation.getLongitude() will get you lat, long coordinates
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }
    };
```

## 5. Create ``` IALocationManager ``` and request location updates

Create an object of ``` IALocationManager ```
```java
IALocationManager mLocationManager;

protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = IALocationManager.create(this); //create a IALocationManager
    }
```

Request Location Updates from the location manager. Specify the listener.
```java
protected void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), mLocationListener);
    }
```

When your app is paused, stop receiving location updates
```java
protected void onPause() {
        //Stop receiving location updates when app paused.
        mLocationManager.removeLocationUpdates(mLocationListener);
        super.onPause();
    }
```

Cleanup when your app is destroyed
```java
    protected void onDestroy() {
        mLocationManager.destroy();
        super.onDestroy();
    }
```

## and done..

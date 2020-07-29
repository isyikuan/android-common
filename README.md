# Android Common Library

## Download

Gradle:

```
implementation 'com.kimyi:android-common:0.2.2'
```



## Get started

```
class App : Application() {

    override fun onCreate() {
    	...
	AndroidCommon.init(this)
	...
    }
}
```


MyLibrarys
my first library which convert f-c

You can easily conver degree c to faranhit and vice versa

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.gagan1994:MyLibrarys:0.1.3'
	}

Covert Fahrenheit to celsius using 

        CustomThrmoMeter.ToDegreeCelsius(faranhit)

Convert CelsiusTo Fahrenheit using

        CustomThrmoMeter.ToFahrenheit(degree)

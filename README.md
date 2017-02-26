# InsecureStrorage
This Application is a demonstration of storing a string in plain text or in encrypted form inside shared preferences. 

<b>The insecure way</b> of stroing is storing sensitive information in plain text. For instance Credit Card details or a user account detials.

While the <b>secure way</b> of way of doing it by encrpyting (AES256 in this case) the string and then storing it in the shared preferences.At the same time the performance of the application must be kept in mind by running the encryption in a background thread instead of the main thread of the app.

The Activity Code is at : https://github.com/kaushikb1996/InsecureStrorage/blob/master/app/src/main/java/com/primeauth/insecurestrorage/MainActivity.java

<B>ScreenShot:</B>

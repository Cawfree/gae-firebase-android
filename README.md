# gae-firebase-android
A sample project showing how an Android App and the Google App Engine can communicate with a Firebase database.

## About
This project defines a coniguration for a `WebServlet` running on the Google App Engine, and a simple Android application, which both use Firebase to listen for push notifications generated on changes made to a database entry. 

## Instructions
In order to connect to your own Firebase Database, you'll have to supply your own private key for admin access (see: [Add Firebase to your app](https://firebase.google.com/docs/admin/setup)) and a package-compatible `googe-services.json` (see: [Maually add Firebase](https://firebase.google.com/docs/android/setup)). Additionally, you'll want to [change the default authorisation settings](https://firebase.google.com/docs/database/security/quickstart) to allow R/W access to the database. 

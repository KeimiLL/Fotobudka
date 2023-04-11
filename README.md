# Photo Booth
Photo Booth allows users to take a series of photos at specified intervals. Once a complete series of photos has been taken, the application sends the photos to a server application responsible for creating a set of photos ready for printing.

The server application operates within a local network and is available as a REST API. Photos are catalogued together with the output PDF file.

## Instructions
- Upon entering the homepage, the user is presented with a navigation panel (on the right) where they can navigate between configuration, camera, and theme selection. The main configuration panel is where the user can set four things: time to first photo in seconds (1 to 10), time between photos in seconds (1 to 10), number of photos (above 2), and language (Polish or English).
<p align="center">
<img src="/images/settings.png" alt="settings" width="70%"/>
</p>
<p align="center">
<img src="/images/settings-pl.png" alt="settings-pl" width="70%"/>
</p>

- On the next screen, the user can choose from 4 themes: noir (black/dark), birthday, vacation, and winter.
<p align="center">
<img src="/images/themes.png" alt="themes" width="70%"/>
</p>

- Upon entering the camera, the user is reminded that the app requires permissions to function properly.
<p align="center">
<img src="/images/permission.png" alt="permission" width="70%"/>
</p>
<p align="center">
<img src="/images/allow.png" alt="allow" width="70%"/>
</p>

- After granting permissions, the user sees a camera preview and can proceed to start taking photos by clicking the middle button. They will hear short sound signals indicating the countdown and a long, distinctive signal when a photo is taken.
<p align="center">
<img src="/images/prev.jpg" alt="prev" width="70%"/>
</p>

- In the top left corner, the user can see in real-time how many photos have been sent to the server.
<p align="center">
<img src="/images/sending.jpg" alt="sending" width="70%"/>
</p>

- After the process is complete, a link to a PDF file with a ready-to-print collage appears in the bottom left corner for the user.
<p align="center">
<img src="/images/end.jpg" alt="end" width="70%"/>
</p>

- Ready-to-print PDF file.
<p align="center">
<img src="/images/pdf.jpg" alt="pdf" width="30%"/>
</p>

## Tools used
- Kotlin - the main language for writing Android platform applications
- Jetpack Compose - a library supporting the creation of user interfaces in a more clear and efficient way
- Okhttp - a Java/Kotlin network library that makes it easy to create and send HTTP requests
- Gson - a Java library that allows for the conversion of Java objects to and from JSON format
- CameraX - a library for the Android platform that enables easy and efficient use of camera functions in applications
- Coroutines - functions that allow for the creation of asynchronous applications in the Kotlin language
- Coil - an Android library that allows for easy and efficient display of images in applications
- Dagger-Hilt - a Java and Android library that allows for simple and efficient dependency management in applications
- Node.js - a platform based on the JavaScript language that allows for the creation of server applications
- Express - a library for the Node.js platform that enables easy creation of web applications and APIs
- Multer - a library for the Node.js platform that allows for easy processing and transfer of files to the server
- Handlebars - a JavaScript library that allows for easy creation of HTML templates
- Puppeteer - a library for the Node.js platform, providing an interface allowing for control of the Chrome/Chromium browser through the DevTools protocol
- Ghostscript - a tool for manipulating and converting PDF and PostScript files

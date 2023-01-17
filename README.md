# GLARE - GL Android Rendering Engine
GLARE is a 3D rendering engine for Android devices supporting OpenGL ES 3 written in Java. It is completely written from scratch and does not use any external libraries. It currently displays a cube with a tile texture that can be easily swapped out in code. The current APK of the demo can be downloaded [here](https://github.com/david-palm/GLARE/releases/download/stable/glare_stable_v0.1.apk)!
<p align="center">
  <img src="https://user-images.githubusercontent.com/109848051/212898975-279f065b-6efa-46c4-825b-6b0994447420.png" height="500px">
</p>


## Rendering
GLARE allows the user to render 3D meshes as well as 2D UI elements:
- 3D objects have materials consisting of an albedo map or a color, a normal map, and a roughness map
- Point and ambient lights illuminate the scene using Blinn-Phong lighting
- 2D UI elements have materials that have either a color or a texture
- Currently does not support text rendering

## Event and layer system
- Custom event system wraps window and user input events and forwards them to the correct layer
- Layer system blocks user input if its already handled

## Inertia system
The inertia system allows the user to rotate the cube and zoom the camera but can easily be attached to any property of any object in code.
- Inertia system allows custom behavior after the user moved an object
- The length of the inertia as well as the form can be customized by using different mathematical functions
- Inertia can be added to 3D objects as well as UI elements or camera controls

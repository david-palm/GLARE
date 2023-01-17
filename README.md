# GLARE - GL Android Rendering Engine
GLARE is a 3D rendering engine for Android devices supporting on OpenGL ES 3 written in Java. It is completely written from scratch and does not use any external libraries. It is currently displaying a cube with a tile texture which can be easily swapped out in code.
## Rendering
GLARE allows the user to render 3D meshes as well as 2D UI elements:
- 3D objects have materials consisting of an albedo map or a color, a normal map and a roughness map
- Point and ambient lights illuminate the scene using Blinn-Phong lighting
- 2D UI have materials that have either a color or a texture
- Currently does not support text rendeering

## Event and layer system
- Custom event system wraps window and user input events and forwards them to the correct layer
- Layer system blocks user input if its already handled

## Inertia system
- Inertia system allows custom behaviour after the user moved an object
- The length of the inertia as well as the form can be customized by using different mathematical functions
- Inertia can be added to 3D objects as well as UI elements or camera controls

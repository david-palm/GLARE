#version 320 es
layout(location=0) in vec2 aPosition;
layout(location=1) in vec2 aTexCoord;

uniform mat4 uModel;

out vec2 fragmentPosition;

void main(){
    fragmentPosition = aPosition;
    gl_Position = uModel * vec4(aPosition, 0.0, 1.0);
}
#version 320 es
precision highp float;

uniform vec4 uColor;
// All corner radii sorted from left to right, top to bottom
uniform vec2 uRadii[4];

out vec4 fragColor;

void main(){
    fragColor = uColor;
}
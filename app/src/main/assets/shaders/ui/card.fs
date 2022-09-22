#version 320 es
precision highp float;

const float EPSILON = 0.02;

in vec2 fragmentPosition;
out vec4 fragColor;

uniform float uAspectRatio;
uniform vec4 uColor;
uniform mat4 uModel;
// All corner radii sorted from left to right, top to bottom. Radii are given in percentages
uniform vec2 uRoundedCorners[4];

void main(){
    fragColor = vec4(0.0, 0.0, 0.0, 0.0);

    // Calculating corner points
    vec2 corners[4];
    corners[0] = vec2(-1.0 + uRoundedCorners[0].x * uModel[1][1], 1.0 - uRoundedCorners[0].y * uModel[0][0] * uAspectRatio);
    corners[1] = vec2(1.0 - uRoundedCorners[1].x * uModel[1][1], 1.0 - uRoundedCorners[1].y * uModel[0][0] * uAspectRatio);
    corners[2] = vec2(-1.0 + uRoundedCorners[2].x * uModel[1][1], -1.0 + uRoundedCorners[2].y * uModel[0][0] * uAspectRatio);
    corners[3] = vec2(1.0 - uRoundedCorners[3].x * uModel[1][1], -1.0 + uRoundedCorners[3].y * uModel[0][0] * uAspectRatio);

    // Coloring everything except the corners
    if( fragmentPosition.x > corners[0].x && fragmentPosition.x < corners[1].x){
        fragColor = uColor;
    }
    if( fragmentPosition.y < corners[0].y && fragmentPosition.y > corners[2].y){
        fragColor = uColor;
    }

    // Calculating if fragment belongs to rounded corner
    for(int i = 0; i < 4; i++){
        // Transposing position into "corner space"
        vec2 positionsEllipseSpace = fragmentPosition - corners[i];
        float x = pow(positionsEllipseSpace.x, 2.0) / pow(uRoundedCorners[i].x * uModel[1][1], 2.0);
        float y = pow(positionsEllipseSpace.y, 2.0) / pow(uRoundedCorners[i].y * uModel[0][0] * uAspectRatio, 2.0);
        if(x +  y <= 1.0){
            fragColor = uColor;
        }
    }
}
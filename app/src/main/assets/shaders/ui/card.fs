#version 320 es
precision highp float;

const float EPSILON = 0.02;

in vec2 fragmentPosition;
out vec4 fragColor;

uniform mat4 uModel;
uniform vec4 uColor;
// All corner radii sorted from left to right, top to bottom. Radii are given in percentages
uniform vec2 uRoundCorners[4];

bool isClose(float a, float b){
    return (abs(a - b) < EPSILON);
}
void main(){
    fragColor = vec4(0.0, 0.0, 0.0, 0.0);

    // Calculating corner points. Radii are multiplied by 0.5 to convert percentage into coordinate
    vec2 corners[4];
    corners[0] = vec2(-1.0 + uRoundCorners[0].x, 1.0 - uRoundCorners[0].y);
    corners[1] = vec2(1.0 - uRoundCorners[1].x, 1.0 - uRoundCorners[1].y);
    corners[2] = vec2(-1.0 + uRoundCorners[2].x, -1.0 + uRoundCorners[2].y);
    corners[3] = vec2(1.0 - uRoundCorners[3].x, -1.0 + uRoundCorners[3].y);

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
        float x = pow(positionsEllipseSpace.x, 2.0) / pow(corners[i].x, 2.0);
        float y = pow(positionsEllipseSpace.y, 2.0) / pow(corners[i].y, 2.0);
        if(x +  y <= 1.0){
            fragColor = vec4(0.8, 0.8, 0.8, 1.0);
        }
    }
}
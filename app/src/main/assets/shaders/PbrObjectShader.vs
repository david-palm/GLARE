#version 320 es
layout(location=0) in vec3 aPosition;
layout(location=1) in vec2 aTexCoord;
layout(location=2) in vec3 aNormal;
layout(location=3) in vec3 aTangent;

out vec2 vTexCoords;
out vec3 vPosition;
out vec3 vNormal;
out vec3 lightPos;
out vec3 tangentLightPos;
out vec3 tangentViewPos;
out vec3 tangentFragPos;

uniform mat4 uModel;
uniform mat4 uView;
uniform mat4 uProjection;
uniform vec3 uLightPosition;
uniform vec3 uCameraPosition;

void main()
{
    vTexCoords = aTexCoords;

    vPosition = aPosition;
    vNormal = normalize(aNormal);
    vec3 vTangent = normalize(aTangent);
    vec3 vBitangent = normalize(cross(aNormal, aTangent));
    mat3 tbn = transpose(mat3(vTangent, vBitangent, vNormal));

    /*
    mat3 normalMatrix = mat3(transpose(inverse(uModel)));
    vPosition = vec3(uModel * vec4(aPosition, 1.0));
    vNormal = normalMatrix * aNormal;
    */


    lightPos = vec3(uModel * vec4(uLightPosition, 1.0));
    tangentLightPos = tbn * lightPos;
    tangentViewPos = tbn * (uCameraPosition);
    tangentFragPos = tbn * vPosition;
    gl_Position =  uProjection * uView * uModel * vec4(vPosition, 1.0);
}
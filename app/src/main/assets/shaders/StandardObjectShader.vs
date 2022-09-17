#version 320 es
layout(location=0) in vec3 aPosition;
layout(location=1) in vec2 aTexCoord;
layout(location=2) in vec3 aNormal;
layout(location=3) in vec3 aTangent;

out OUTSTRUCT{
    vec3 fragmentPosition;
    vec2 texCoord;
    vec3 tangentFragmentPosition;
    vec3 tangentLightPositions[1];
    vec3 tangentViewPosition;
} vOut;

uniform vec3 uLightPositions[1];

uniform mat4 uModel;
uniform mat4 uView;
uniform mat4 uProjection;

void main(){
    vOut.fragmentPosition = vec3(uModel * vec4(aPosition, 1.0));
    vOut.texCoord = aTexCoord;

    mat3 normalMatrix = mat3(transpose(inverse(uView * uModel)));
    vec3 tangent = normalize(normalMatrix * aTangent);
    vec3 normal = normalMatrix * aNormal;
    tangent = normalize(tangent - dot(tangent, normal) * normal);
    vec3 bitangent = cross(normal, tangent);
    mat3 tbn = transpose(mat3(tangent, bitangent, normal));

    vOut.tangentFragmentPosition = tbn * vOut.fragmentPosition;

    for(int i = 0; i < 1; i++){
        vOut.tangentLightPositions[i] = tbn * uLightPositions[i];
    }
    vec3 viewPosition = vec3(uView[3][0], uView[3][1], uView[3][2]);
    vOut.tangentViewPosition = tbn * viewPosition;

    gl_Position = uProjection * uView * uModel * vec4(aPosition, 1.0);
}
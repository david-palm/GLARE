#version 320 es
precision highp float;

out vec4 fragColor;

in OUTSTRUCT{
    vec3 fragmentPosition;
    vec2 texCoord;
    vec3 tangentFragmentPosition;
    vec3 tangentLightPositions[1];
    vec3 tangentViewPosition;
} vIn;

uniform vec4 uColor;
uniform sampler2D uAlbedo;
uniform sampler2D uNormal;
uniform sampler2D uRoughness;

uniform vec3 uLightPositons[1];
uniform vec3 uLightColors[1];
uniform vec3 uAmbientLight;

float calculateBlinnPhong(vec3 tangentLightPosition, vec3 normal, float exponent){
    // Calculating diffuse lighting
    vec3 lightDirection = normalize(tangentLightPosition - vIn.tangentFragmentPosition);
    float diffuse =  max(dot(lightDirection, normal), 0.0);

    // Calculating specular lighting
    vec3 viewDirection = normalize(vIn.tangentViewPosition - vIn.tangentFragmentPosition);
    vec3 reflectDirection = reflect(-lightDirection, normal);
    vec3 halfwayDirection = normalize(viewDirection + lightDirection);
    float specular = pow(max(dot(normal, halfwayDirection), 0.0), exponent) * texture(uAlbedo, vIn.texCoord).r;

    return diffuse + specular;
}

void main(){
    // Get values from textures
    vec3 color = texture(uAlbedo, vIn.texCoord).rgb;
    vec3 normal = texture(uNormal, vIn.texCoord).rgb;
    // Converting values to [-1, 1] range. Normal is already in tangent space
    normal = normalize(normal * 2.0 - 1.0);

    fragColor = vec4(color * calculateBlinnPhong(vIn.tangentLightPositions[0], normal, 32.0) * uLightColors[0], 1.0);
}

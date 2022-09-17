#version 320 es
precision highp float;
out vec4 fragColor;

in vec2 vTexCoords;
in vec3 vPosition;
in vec3 vNormal;
in vec3 lightPos;
in vec3 tangentLightPos;
in vec3 tangentViewPos;
in vec3 tangentFragPos;

uniform sampler2D uAlbedo;
uniform sampler2D uNormal;
uniform sampler2D uMetallic;
uniform sampler2D uRoughness;
uniform sampler2D uAO;

uniform vec3 uCameraPosition;

uniform vec3 uLightPosition;
uniform vec3 uLightColor;

uniform mat4 uModel;
uniform mat4 uView;


vec3 calcDiffuse(vec3 lightPosition, vec3 lightColor){
    vec3 l = normalize(tangentLightPos - tangentFragPos);
    vec3 n = texture(uNormal, vTexCoords).rgb;
    n = normalize(2.0 * n - 1.0);
    float intensity = max(dot(n, l), 0.0);
    intensity = intensity/length(lightPosition - vPosition);
    return intensity * lightColor;
}

vec3 calcSpecular(vec3 lightPosition, vec3 lightColor){
    vec3 l = normalize(lightPosition - vPosition);
    vec3 v = normalize(-uCameraPosition - vPosition);
    vec3 n = normalize(vNormal);
    vec3 r = reflect(-l, n);
    float intensity = pow(max(dot(v, r), 0.0), 32.0);
    intensity = intensity/length(lightPosition - vPosition);
    return lightColor * intensity;
}

vec3 calcBlinnPhong(vec3 lightPosition, vec3 lightColor){
    vec3 l = normalize(tangentLightPos- tangentFragPos);
    vec3 v = normalize(tangentViewPos - tangentFragPos);
    vec3 n = texture(uNormal, vTexCoords).rgb;
    n = normalize(2.0 * n - 1.0);
    vec3 h = normalize(l + v);
    float intensity = pow(max(dot(n, h), 0.0), 128.0);
    intensity = intensity/length(lightPosition - vPosition);
    return lightColor * intensity;
}

void main(){
    vec3 color = vec3(texture(uAlbedo, vTexCoords));
    vec3 ambient = 0.1 * uLightColor;
    vec3 diffuse = calcDiffuse(lightPos, uLightColor);
    vec3 spec = calcBlinnPhong(lightPos, uLightColor);
    spec = (1.0 - vec3(texture(uRoughness, vTexCoords)).r) * spec;
    color = (ambient + diffuse + spec) * color;
    fragColor = vec4(1.0, 1.0, 1.0, 1.0);
}
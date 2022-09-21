package com.example.opengl3renderer.ui;

import android.content.Context;

import com.example.opengl3renderer.layers.Layer;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.ui.object2d.card.Card;
import com.example.opengl3renderer.ui.object2d.card.CardMaterial;

import java.util.ArrayList;
import java.util.List;

public class UI {
    public ArrayList<Component> components;

    public UI(Context context){
        components = new ArrayList<Component>();
        CardMaterial material = new CardMaterial(context, new Vec4(0.8f, 0.8f, 0.8f, 0.2f));
        Card card = new Card(material, new Vec2(0.0f, -0.8f), new Vec2(1.0f, 0.2f), 0.0f, 0.5f);
        Component menu = new Component(card);
        components.add(menu);
    }

    public ArrayList getComponents(){
        return components;
    }

    public void addToLayerStack(List<Layer> layerStack){
        for(int i = 0; i < components.size(); i++){
            layerStack.add(components.get(i));
        }
    }

}

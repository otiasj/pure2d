/**
 * 
 */
package com.funzio.pure2D.particles.nova.vo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.funzio.pure2D.Scene;

/**
 * @author long
 */
public class ParticleVO {
    public int start_delay = 0;
    public int step_delay = Scene.DEFAULT_MSPF;
    public int duration = 0; // <= 0 is unlimited
    public int step_quantity = 1;

    // the layer
    public int layer = 0;

    // optional
    public List<String> sprite;
    public List<Integer> start_frame;

    public List<String> animator;
    public String blend_mode;

    public ParticleVO() {

    }

    public ParticleVO(final JSONObject json) throws JSONException {
        if (json.has("start_delay")) {
            start_delay = json.getInt("start_delay");
        }

        if (json.has("step_delay")) {
            step_delay = json.getInt("step_delay");
        }

        if (json.has("duration")) {
            duration = json.getInt("duration");
        }

        if (json.has("step_quantity")) {
            step_quantity = json.getInt("step_quantity");
        }

        if (json.has("layer")) {
            layer = json.getInt("layer");
        }

        // optional
        sprite = NovaVO.getListString(json.optJSONArray("sprite"));
        animator = NovaVO.getListString(json.optJSONArray("animator"));
        blend_mode = json.optString("blend_mode");

        start_frame = NovaVO.getListInteger(json.optJSONArray("start_frame"));
    }

}

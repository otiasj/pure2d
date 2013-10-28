/**
 * 
 */
package com.funzio.pure2D.ui.vo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.funzio.pure2D.gl.gl10.textures.TextureOptions;
import com.funzio.pure2D.particles.nova.vo.AnimatorVO;

/**
 * @author long.ngo
 */
public class UIConfigVO {

    public String cdn_url;
    public String cache_dir;
    public boolean texture_async = true;

    public ArrayList<FontVO> fonts;
    public ArrayList<AnimatorVO> animators;

    protected HashMap<String, AnimatorVO> mAnimatorMap;

    public UIConfigVO(final JSONObject json) throws JSONException {
        cdn_url = json.optString("cdn_url");
        fonts = getFonts(json.getJSONArray("fonts"));
        animators = getAnimators(json.optJSONArray("animators"));

        // mapping
        if (animators != null) {
            mAnimatorMap = new HashMap<String, AnimatorVO>();
            for (final AnimatorVO vo : animators) {
                if (vo != null) {
                    mAnimatorMap.put(vo.name, vo);
                }
            }
        }
    }

    /**
     * Apply a screen's scale factor to some certain numbers such as x, y, dx, dy. This is used when you scale the texture.
     * 
     * @param scale
     * @see TextureOptions
     */
    public void applyScale(final float scale) {

        // scale animators
        if (fonts != null) {
            for (final FontVO vo : fonts) {
                if (vo != null) {
                    vo.applyScale(scale);
                }
            }
        }

        // scale animators
        if (animators != null) {
            for (final AnimatorVO vo : animators) {
                if (vo != null) {
                    vo.applyScale(scale);
                }
            }
        }

    }

    protected static ArrayList<FontVO> getFonts(final JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }

        final ArrayList<FontVO> result = new ArrayList<FontVO>();
        final int size = array.length();
        for (int i = 0; i < size; i++) {
            FontVO vo = new FontVO(array.getJSONObject(i));
            result.add(vo);
        }

        return result;
    }

    protected static ArrayList<AnimatorVO> getAnimators(final JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }

        final ArrayList<AnimatorVO> result = new ArrayList<AnimatorVO>();
        final int size = array.length();
        for (int i = 0; i < size; i++) {
            result.add(AnimatorVO.create(array.getJSONObject(i)));
        }

        return result;
    }

    public TextureOptions getTextureOptions() {
        // TODO Auto-generated method stub
        return null;
    }

    public AnimatorVO getAnimatorVO(final String name) {
        return mAnimatorMap != null ? mAnimatorMap.get(name) : null;
    }
}
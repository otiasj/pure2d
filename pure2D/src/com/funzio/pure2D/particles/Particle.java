/**
 * 
 */
package com.funzio.pure2D.particles;

import com.funzio.pure2D.DisplayObject;
import com.funzio.pure2D.utils.Reusable;

/**
 * @author long
 */
public interface Particle extends DisplayObject, Reusable {

    public void finish();

    public boolean isFinished();

    public void setEmitter(ParticleEmitter emitter);

    public ParticleEmitter getEmitter();

    public void setListener(Listener listener);

    public Listener getListener();

    public interface Listener {
        public void onParticleFinish(Particle particle);
    }
}

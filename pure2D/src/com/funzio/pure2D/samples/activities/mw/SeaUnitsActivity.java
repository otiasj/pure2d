package com.funzio.pure2D.samples.activities.mw;

import javax.microedition.khronos.opengles.GL10;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.funzio.crimecity.game.model.CCMapDirection;
import com.funzio.crimecity.particles.ParticleAdapter;
import com.funzio.crimecity.particles.units.AircraftCarrier;
import com.funzio.crimecity.particles.units.Frigate;
import com.funzio.crimecity.particles.units.Submarine;
import com.funzio.crimecity.particles.units.Unit;
import com.funzio.pure2D.R;
import com.funzio.pure2D.Scene;
import com.funzio.pure2D.gl.GLColor;
import com.funzio.pure2D.samples.activities.StageActivity;

public class SeaUnitsActivity extends StageActivity {

    private int mDrags = 0;
    private Class<? extends Unit> mAttackerType = Frigate.class;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScene.setColor(new GLColor(0, 0.7f, 0, 1));
        // need to get the GL reference first
        mScene.setListener(new Scene.Listener() {

            @Override
            public void onSurfaceCreated(final GL10 gl) {
                ParticleAdapter.getInstance().setSurface(mStage);
                ParticleAdapter.getInstance().onSurfaceCreated(gl, null);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.stage_mw_sea_attacker;
    }

    private void addAttacker(final float x, final float y) {

        Unit attacker = null;
        CCMapDirection direction = CCMapDirection.SOUTHWEST;
        if (mAttackerType == Frigate.class) {
            attacker = new Frigate(direction);
        } else if (mAttackerType == Submarine.class) {
            attacker = new Submarine(direction);
        } else if (mAttackerType == AircraftCarrier.class) {
            attacker = new AircraftCarrier(direction, mScene);
        }

        // null check
        if (attacker != null) {
            attacker.setPosition(x, y);

            // add to scene
            mScene.addChild(attacker);
        }
    }

    @Override
    protected int getNumObjects() {
        return mScene.getNumGrandChildren();
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        final int action = event.getAction();
        boolean doOnDrag = false;
        if (action == MotionEvent.ACTION_MOVE) {
            mDrags++;
            doOnDrag = mDrags % 10 == 0;
        }

        if (action == MotionEvent.ACTION_DOWN || doOnDrag) {
            mStage.queueEvent(new Runnable() {
                @Override
                public void run() {
                    int len = event.getPointerCount();
                    for (int i = 0; i < len; i++) {
                        addAttacker(event.getX(i), mDisplaySize.y - event.getY(i));
                    }
                }
            });
        }

        return true;
    }

    public void onClickRadio(final View view) {
        switch (view.getId()) {
            case R.id.radio_frigate:
                mAttackerType = Frigate.class;
                break;

            case R.id.radio_submarine:
                mAttackerType = Submarine.class;
                break;

            case R.id.radio_aircraft_carrier:
                mAttackerType = AircraftCarrier.class;
                break;
        }
    }
}

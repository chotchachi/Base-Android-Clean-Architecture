package com.chotchachi.baseandroidcleanarchitecture.view.cardstackview.internal;

import android.view.animation.Interpolator;

import com.chotchachi.baseandroidcleanarchitecture.view.cardstackview.Direction;

public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}


package com.example.development.androidmsample.utils.behaviors;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.example.development.androidmsample.R;
import com.example.development.androidmsample.utils.Constants;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private int mAnimType;
    private boolean mIsAnimatingOut = false;
    private boolean isNestedScrollStoped=false;
    private String LOGTAG="=====";

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    public ScrollAwareFABBehavior(int animType) {
        super();
        mAnimType=animType;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        Log.d(LOGTAG,"layoutDependsOn");
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        Log.d(LOGTAG,"onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        Log.d(LOGTAG,"onStartNestedScroll");
        isNestedScrollStoped=false;
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d(LOGTAG,"onNestedScroll");
        if ( !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child);
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.d(LOGTAG,"onStopNestedScroll");
        if(mIsAnimatingOut){
            isNestedScrollStoped=true;
        }
        else if( child.getVisibility() != View.VISIBLE){
            animateIn(child);
        }
    }

    @Override
    public boolean blocksInteractionBelow(CoordinatorLayout parent, FloatingActionButton child) {
        Log.d(LOGTAG,"blocksInteractionBelow");
        return super.blocksInteractionBelow(parent, child);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(LOGTAG,"onNestedFling  Y="+velocityY+"   =consumed="+consumed);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
    private ViewPropertyAnimatorListener animListner=new ViewPropertyAnimatorListener() {
        public void onAnimationStart(View view) {
            ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
        }

    public void onAnimationCancel(View view) {
        ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
    }

    public void onAnimationEnd(View view) {
        ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
        view.setVisibility(View.GONE);
        if(isNestedScrollStoped&&view.getVisibility() != View.VISIBLE){
            animateIn((FloatingActionButton)view);
        }
    }
};
    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButton button) {
        Log.d(LOGTAG,"animateOut");
        if (Build.VERSION.SDK_INT >= 14) {
            if(mAnimType== Constants.SCALE) {
                ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
                        .setListener(animListner).start();
            }else if(mAnimType==Constants.TRANSLATE){
                ViewCompat.animate(button).translationY(100F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
                        .setListener(animListner).start();
            }
        } else {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_out);
            anim.setInterpolator(INTERPOLATOR);
            anim.setDuration(200L);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                }

                public void onAnimationEnd(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                    button.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                }
            });
            button.startAnimation(anim);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    private void animateIn(FloatingActionButton button) {
        Log.d(LOGTAG,"animateIn");
        button.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 14) {
            if(mAnimType==Constants.SCALE) {
                ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                        .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                        .start();
            }
            else if (mAnimType==Constants.TRANSLATE){
                ViewCompat.animate(button).translationY(0F).alpha(1.0F)
                        .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                        .start();
            }
        } else {
            if(mAnimType==Constants.SCALE) {
                Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_in);
                anim.setDuration(200L);
                anim.setInterpolator(INTERPOLATOR);
                button.startAnimation(anim);
            }
            else if (mAnimType==Constants.TRANSLATE){
                Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.snackbar_in);
                anim.setDuration(200L);
                anim.setInterpolator(INTERPOLATOR);
                button.startAnimation(anim);
            }

        }
    }
}
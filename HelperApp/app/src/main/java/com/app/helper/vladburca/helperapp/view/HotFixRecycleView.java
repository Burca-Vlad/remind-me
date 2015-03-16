package com.app.helper.vladburca.helperapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by vlad.burca on 1/22/2015.
 */
public class HotFixRecycleView extends RecyclerView {

    public HotFixRecycleView( Context context )
    {
        super( context );
    }

    public HotFixRecycleView( Context context, AttributeSet attrs )
    {
        super( context, attrs );
    }

    public HotFixRecycleView( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
    }

    @Override
    public void stopScroll()
    {
        try
        {
            super.stopScroll();
        }
        catch( NullPointerException exception )
        {
            /**
             *  The mLayout has been disposed of before the
             *  RecyclerView and this stops the application
             *  from crashing.
             */
        }
    }
}

package com.nicela.mafiaadmin.CardsActivity;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.nicela.mafiaadmin.MafiaGameSource.MafiaPlayer;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaUtills;
import com.nicela.mafiaadmin.R;

/**
 * Created by Vitaliy on 7/20/2015.
 */
class MafiaCard
{
    final static int CARD_TRANSPARENT = 180;

    private int mRole;
    private ImageView mImage;
    private boolean mIsClicked = false;
    private boolean mIsChosen = false;
    private CardsActivity mCardsActivity;

    public boolean isClicked()
    {
        return mIsClicked;
    }

    public void setRole(int mRole)
    {
        this.mRole = mRole;
    }

    public void setImage(ImageView mImage)
    {
        this.mImage = mImage;
    }

    public ImageView getImage()
    {
        return mImage;
    }

    public int getRole()
    {
        return mRole;
    }

    public MafiaCard(final int mRole, final ImageView mImage, CardsActivity mCardsActivity)
    {
        this.mRole = mRole;
        this.mImage = mImage;
        this.mCardsActivity = mCardsActivity;
        DOUBLE_TOUCH_DETECTOR.setOnDoubleTapListener(cardOnDoubleTouch);

        mImage.setOnClickListener(cardOnTouch);

        mImage.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(mIsClicked || !mIsChosen)
                    return false;
                return DOUBLE_TOUCH_DETECTOR.onTouchEvent(event);
            }
        });

    }

    private final View.OnClickListener cardOnTouch = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(mCardsActivity.isDoubleTaped())
            {
                mCardsActivity.getCurrentImage().setImageResource(R.drawable.card_shirt);
                mCardsActivity.setIsDoubleTaped(false);
                mCardsActivity.getCurrentImage().setImageAlpha(CARD_TRANSPARENT);
                mCardsActivity.getTextViewStatus().setText(mCardsActivity.getResources().getString(R.string.player)
                        + " " + (mCardsActivity.mNumberClicks + 1) + " " + mCardsActivity.getResources().getString(R.string.time_to_choose));
                clearCardsBackground();
            }
            else
            {
                if(mIsClicked)
                    return;
                //clear selection
                clearCardsBackground();
                //select new card
                mImage.setBackgroundResource(R.drawable.card_selection);
                mIsChosen = true;
            }
        }
    };

    public void setIsChosen(boolean mIsChosen)
    {
    }

    public boolean isChosen()
    {
        return mIsChosen;
    }

    public void setmIsChosen(boolean mIsChosen)
    {
        this.mIsChosen = mIsChosen;
    }

    private void clearCardsBackground()
    {

        for(int i = 0; i < mCardsActivity.getCards().size(); i++)
        {
            mCardsActivity.getCards().elementAt(i).mImage.setBackgroundResource(R.drawable.card_clear_background);
            mCardsActivity.getCards().elementAt(i).mIsChosen = false;
        }
    }

    private GestureDetector.SimpleOnGestureListener cardOnDoubleTouch = new GestureDetector.SimpleOnGestureListener()
    {
        @Override
        public boolean onDoubleTap(MotionEvent event)
        {
            switch (mRole)
            {
                case MafiaUtills.CIVILIAN:
                    mImage.setImageResource(R.drawable.card_civilian);
                    break;
                case MafiaUtills.COMMISSAR:
                    mImage.setImageResource(R.drawable.card_commissar);
                    break;
                case MafiaUtills.MAFIA:
                    mImage.setImageResource(R.drawable.card_mafia);
                    break;
                case MafiaUtills.MAFIA_BOSS:
                    mImage.setImageResource(R.drawable.card_mafia_boss);
                    break;
            }
            mCardsActivity.setCurrentImage(mImage);
            mCardsActivity.setIsDoubleTaped(true);
            mCardsActivity.getMafiaGame().addPlayer(new MafiaPlayer(mRole));
            mCardsActivity.mNumberClicks++;
            mIsClicked = true;
            return true;
        }
    };

    private final GestureDetector DOUBLE_TOUCH_DETECTOR = new GestureDetector(cardOnDoubleTouch);
}

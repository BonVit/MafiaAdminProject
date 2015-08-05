package com.nicela.mafiaadmin.CardsActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.nicela.mafiaadmin.ConfigurationUtills;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaGame;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaGameMode;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaUtills;
import com.nicela.mafiaadmin.R;

import java.util.Vector;

/**
 * Created by Vitaliy on 7/20/2015.
 */
public class CardsActivity extends Activity
{
    private TableLayout mTableLayout;
    private TextView mTextViewStatus;

    private Vector<MafiaCard> mMafiaCards;
    private MafiaGameMode mMafiaGameMode;
    private MafiaGame mMafiaGame;

    private boolean isDoubleTaped = false;
    int mNumberClicks = 0;

    //Getters and setters
    TextView getTextViewStatus()
    {
        return mTextViewStatus;
    }

    public ImageView getCurrentImage()
    {
        return currentImage;
    }

    public void setCurrentImage(ImageView currentImage)
    {
        this.currentImage = currentImage;
    }

    private ImageView currentImage;

    public MafiaGame getMafiaGame()
    {
        return mMafiaGame;
    }

    public CardsActivity()
    {
        mMafiaCards = new Vector<MafiaCard>();
        mMafiaGame = new MafiaGame(mMafiaGameMode);
    }

    public CardsActivity(MafiaGameMode mMafiaGameMode)
    {
        this.mMafiaGameMode = mMafiaGameMode;
        mMafiaCards = new Vector<MafiaCard>();
        mMafiaGame = new MafiaGame(mMafiaGameMode);
    }

    public boolean isDoubleTaped()
    {
        return isDoubleTaped;
    }

    public void setIsDoubleTaped(boolean isDoubleTaped)
    {
        this.isDoubleTaped = isDoubleTaped;
    }

    public Vector<MafiaCard> getCards()
    {
        return mMafiaCards;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        mTableLayout = (TableLayout) findViewById(R.id.tableLayout_cards);
        mTableLayout.setOnClickListener(onClick);
        mTextViewStatus = (TextView) findViewById(R.id.cardFragment_textView_status);
        mTextViewStatus.setOnClickListener(onClick);
        mTextViewStatus.setText(getResources().getString(R.string.player) + " " + (mNumberClicks + 1) + " " +
                getResources().getString(R.string.time_to_choose));

        mMafiaGameMode = new MafiaGameMode(MafiaUtills.CLASSIC_MODE);
        mMafiaGameMode.shuffleRoles();
        showCards();
    }

    private void showCards()
    {
        //Set up layout and cards
        LinearLayout.LayoutParams mLinearLayoutParams = new LinearLayout.
                LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams mCardsParams = new LinearLayout.
                LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        mCardsParams.weight = 1;
        mCardsParams.setMargins((int) getResources().getDimension(R.dimen.card_margins),
                (int) getResources().getDimension(R.dimen.card_margins),
                (int) getResources().getDimension(R.dimen.card_margins),
                (int) getResources().getDimension(R.dimen.card_margins));

        for(int i = 0; i < mMafiaGameMode.getRoles().length;)
        {
            LinearLayout mNewLinearLayout = new LinearLayout(mTableLayout.getContext());
            mNewLinearLayout.setLayoutParams(mLinearLayoutParams);
            mNewLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            mNewLinearLayout.setWeightSum(ConfigurationUtills.CARDS_IN_ROW);

            for(int j = 0; j < ConfigurationUtills.CARDS_IN_ROW; j++, i++)
            {
                //set up images
                final ImageView mNewImageView = new ImageView(mNewLinearLayout.getContext());
                mNewImageView.setLayoutParams(mCardsParams);
                mNewImageView.setImageResource(R.drawable.card_shirt);
                mNewImageView.setAdjustViewBounds(true);
                mNewImageView.setPadding((int) getResources().getDimension(R.dimen.card_padding),
                        (int) getResources().getDimension(R.dimen.card_padding),
                        (int) getResources().getDimension(R.dimen.card_padding),
                        (int) getResources().getDimension(R.dimen.card_padding));

                if(i >= mMafiaGameMode.getRoles().length)
                {
                    mNewImageView.setEnabled(false);
                    mNewImageView.setVisibility(View.INVISIBLE);
                }
                else
                    mMafiaCards.addElement(new MafiaCard(
                            mMafiaGameMode.getRoles()[i], mNewImageView, this));

                mNewLinearLayout.addView(mNewImageView);
            }

            mTableLayout.addView(mNewLinearLayout);
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(isDoubleTaped)
            {
                currentImage.setImageResource(R.drawable.card_shirt);
                currentImage.setImageAlpha(MafiaCard.CARD_TRANSPARENT);
            }
        }
    };
}

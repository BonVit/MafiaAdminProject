package com.nicela.mafiaadmin.Fragments.CardsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.nicela.mafiaadmin.ConfigurationUtills;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaGame;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaGameMode;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaPlayer;
import com.nicela.mafiaadmin.MafiaGameSource.MafiaUtills;
import com.nicela.mafiaadmin.R;

import java.util.Vector;

/**
 * Created by Vitaliy on 7/20/2015.
 */
public class CardsFragment extends Fragment
{
    private View mView;

    private TableLayout mTableLayout;
    private TextView mTextViewStatus;

    private Vector<MafiaCard> mMafiaCards;
    private MafiaGameMode mMafiaGameMode;
    private MafiaGame mMafiaGame;

    private boolean isDoubleTaped = false;

    private int mNumberChosenCard = 0;

    public int getNumberChosenCard()
    {
        return mNumberChosenCard;
    }

    public void setNumberChosenCard(int mNumberChosenCard)
    {
        this.mNumberChosenCard = mNumberChosenCard;
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

    public CardsFragment()
    {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_cards, container, false);
        mTableLayout = (TableLayout) mView.findViewById(R.id.tableLayout_cards);
        mTableLayout.setOnClickListener(onClick);
        mTextViewStatus = (TextView) mView.findViewById(R.id.cardFragment_textView_status);
        mTextViewStatus.setOnClickListener(onClick);

        mMafiaGameMode = new MafiaGameMode(MafiaUtills.CLASSIC_MODE);
        mMafiaGameMode.shuffleRoles();
        showCards();

        return mView;
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

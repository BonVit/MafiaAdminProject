package com.nicela.mafiaadmin.MafiaGameSource;

/**
 * Created by Vitaliy on 7/13/2015.
 */
public class MafiaGameMode
{
    //Number of players
    private int mPlayersNumber;

    //Roles
    private int[] mRoles;

    public int getPlayersNumber()
    {
        return mPlayersNumber;
    }

    public int[] getRoles()
    {
        return mRoles;
    }

    //Custom mode constructor
    public MafiaGameMode(int[] mRoles)
    {
        this.mPlayersNumber = mRoles.length;
        this.mRoles = mRoles;
    }

    //Classic game mode
    public MafiaGameMode(int mode)
    {
        switch (mode)
        {
            case MafiaUtills.CLASSIC_MODE:
                mRoles = new int[]
                        {MafiaUtills.CIVILIAN, MafiaUtills.CIVILIAN, MafiaUtills.CIVILIAN, MafiaUtills.CIVILIAN,
                                MafiaUtills.CIVILIAN, MafiaUtills.CIVILIAN, MafiaUtills.COMMISSAR, MafiaUtills.MAFIA,
                                MafiaUtills.MAFIA, MafiaUtills.MAFIA_BOSS};
                break;
        }
        mPlayersNumber = mRoles.length;
    }

    public void shuffleRoles()
    {

        for(int i = 0; i < mPlayersNumber; i++)
        {
            int rand = (int) (Math.random() * mPlayersNumber);
            int tmp = mRoles[i];
            mRoles[i] = mRoles[rand];
            mRoles[rand] = tmp;
        }
    }
}
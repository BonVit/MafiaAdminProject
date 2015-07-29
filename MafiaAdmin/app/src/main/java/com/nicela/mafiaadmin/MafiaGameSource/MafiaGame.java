package com.nicela.mafiaadmin.MafiaGameSource;

import java.util.Vector;

/**
 * Created by Vitaliy on 7/13/2015.
 */
public class MafiaGame
{
    //Game mode
    private MafiaGameMode mGameMode;

    //Players
    private Vector<MafiaPlayer> mMafiaPlayers;

    public MafiaGame(MafiaGameMode mode)
    {
        this.mGameMode = mode;
        this.mMafiaPlayers = new Vector<MafiaPlayer>();
    }

    public MafiaGame(int mode)
    {
        this.mGameMode = new MafiaGameMode(mode);
        this.mMafiaPlayers = new Vector<MafiaPlayer>();
    }

    public void addPlayer(MafiaPlayer mNewMafiaPlayer)
    {
        mMafiaPlayers.addElement(mNewMafiaPlayer);
    }

    public Vector<MafiaPlayer> getMafiaPlayers()
    {
        return mMafiaPlayers;
    }

    public MafiaGameMode getGameMode()
    {
        return mGameMode;
    }
}
package com.nicela.mafiaadmin.MafiaGameSource;

/**
 * Created by Vitaliy on 7/20/2015.
 */
public class MafiaPlayer
{
    private int mRole;
    private boolean mIsAlive;

    public int getRole()
    {
        return mRole;
    }

    public void setRole(int mRole)
    {
        this.mRole = mRole;
    }

    public boolean isAlive()
    {
        return mIsAlive;
    }

    public void setAlive(boolean mIsAlive)
    {
        this.mIsAlive = mIsAlive;
    }

    public MafiaPlayer(int mRole)
    {
        this.mRole = mRole;
        this.mIsAlive = true;
    }

    public MafiaPlayer(int mRole, boolean mIsAlive)
    {
        this.mRole = mRole;
        this.mIsAlive = mIsAlive;
    }
}

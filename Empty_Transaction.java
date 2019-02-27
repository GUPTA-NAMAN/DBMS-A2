import java.lang.Thread ;

class Empty_Transaction extends Thread
{
    protected  Locker locker ;
    protected int ID ;
    
    Empty_Transaction(int ID,Locker locker)
    {
        this.ID = ID ;
        this.locker = locker ;
    }
    
    protected synchronized boolean Locks(boolean IsLocking,int type,int[] p,int[] f)
    {
        if(IsLocking==true)
        {
        if ( type==1)               // for whole databasei
        {
           return locker.LockWhole(ID) ;
        }
        else if( type ==2 )
        {
            
           return locker.Lock_P(ID,p[0]) ;   // for passeneger only
        }
        else if(type ==3)
        {
           return locker.Lock_P_F(ID,p[0],f[0]) ;
        }
        else
        {
           return locker.Lock_P_F_F(ID,p[0],f[0],f[1])  ;
        }
        }
        else
        {
            if(type ==1)
            {
                return locker.UnLockWhole() ;
            }
            else if(type==2)
            {
                return locker.UnLock_P(p[0]) ;
            }
            else if(type==3)
            {
                return locker.UnLock_P_F(p[0],f[0]) ;
            }
            else
            {
                return locker.UnLock_P_F_F( p[0], f[0], f[1])   ;
            }
        }
        
    }
    
}

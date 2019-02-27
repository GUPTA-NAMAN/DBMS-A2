import java.lang.Thread ;

class Locker extends Thread
{
    DataBase db ;
    int lock_D ;
    int[] Lock_L_P ;
    int[] Lock_L_F ;
    int No_P_L , No_F_L ;

    
    Locker(int No_P,int No_F,DataBase db)
    {
        lock_D = 0 ;
        Lock_L_F=new int[No_F] ;
        No_P_L = 0 ;
        No_F_L = 0 ;
        Lock_L_P=new int[No_P] ;
        this.db= db ;
    }
    
    
    
    
    public int P_Get_BD(int ti,int p,int f)
    {
        if(lock_D==ti ||  Lock_L_P[p-1] == ti )
        {
            return db.Passenger_List[p-1].Fligth_List [f-1]  ;
        }
        else
        {
            System.out.println("e1") ;
            return -1 ;
        }
    }
    
    public boolean P_UpDate_BD(int ti,int p,int f,int value)
    {
        if(lock_D==ti ||   Lock_L_P[p-1]==ti)
        {
            db.Passenger_List[p-1].Fligth_List[f-1] = value ;
            return true ;
        }
        else
        {
          System.out.println("e2") ;
            return false  ;
        }
    }
    
    public int F_Get_BD(int ti,int f,int p)
    {
        if(lock_D==ti ||  Lock_L_F[f-1]==ti)
        {
            return db.Fligth_List[f-1].Passenger_List[p-1] ;
        }
        else
        {
            System.out.println("e3") ;
            return -1 ;
        }
    }
    
    public boolean F_UpDate_BD(int ti,int f,int p,int value)
    {
         if(lock_D==ti ||  Lock_L_F[f-1]==ti)
         {
             db.Fligth_List[f-1].Passenger_List[p-1] = value ;
            
             return true ;
         }
         else
         {
             System.out.println("e4") ;
             return false ;
         }
    }
    
    public int F_Get_NB(int ti,int f)
    {
        if(lock_D==ti || Lock_L_F[f-1]==ti)
        {
            return db.Fligth_List[f-1].No_Booking ;
        }
        else
        {
            System.out.println("e5") ;
           return -1 ;
        }
    }
    
    public boolean F_UpDate_NB(int ti,int f,int value)
    {
        if(lock_D==ti || Lock_L_F[f-1]==ti)
        {
            db.Fligth_List[f-1].No_Booking = value ;
            return true ;
        }
        else
        {
            System.out.println("e6") ;
            return false ;
        }
    }
    
    public int F_Get_C(int ti,int f)
    {
        if(lock_D==ti || Lock_L_F[f-1]==ti)
        {
            return db.Fligth_List[f-1].Capacity  ;
        }
        else
        {
            System.out.println("7") ;
            return -1 ;
        }
    }
    
    
    
    
    
   public boolean LockWhole(int ID)
    {
        
        if(No_F_L==0 && No_P_L==0 && lock_D==0)
        {
            lock_D = ID ;
            return true ;
        }
        else
        {
            return false ;
        }
    }
    
    public boolean UnLockWhole()
    {
        lock_D = 0 ;
        return true ;
    }
    
    public boolean Lock_P(int ID,int p)
    {
       if( lock_D==0 &&  Lock_L_P[p-1]  == 0 )
       {
           Lock_L_P[p-1] = ID ;
           No_P_L ++ ;
           return true ;
       }
        else
        {
            return false ;
        }
    }
    
    public boolean UnLock_P(int p)
    {
        Lock_L_P[p-1] = 0;
        No_P_L -- ;
        
        return true ;
    }
    
    public boolean Lock_P_F(int ID,int p,int f)
    {
        if(lock_D==0 && Lock_L_P[p-1]==0 && Lock_L_F[f-1]==0)
        {
            Lock_L_P[p-1] = ID ;
            Lock_L_F[f-1] = ID ;
            No_P_L ++ ;
            No_F_L ++ ;
            return true ;
        }
        else
        {
            return false ;
        }
    }
    
    public boolean UnLock_P_F(int p,int f)
    {
        Lock_L_F[f-1] = 0;
        No_F_L -- ;
        Lock_L_P[p-1] = 0 ;
        No_P_L --  ;
        return true ;
    }
    
    public boolean Lock_P_F_F(int ID,int p,int f1,int f2)
    {
        if( lock_D==0 && Lock_L_P[p-1]==0 && Lock_L_F[f1-1]==0 && Lock_L_F[f2-1]==0)
        {
            Lock_L_P[p-1] = Lock_L_F[f1-1] = Lock_L_F[f2-1] = ID ;
            No_F_L = No_F_L + 2 ;
            No_P_L ++ ;
            return true ;
        }
        else
        {
            return false ;
        }
    }
    
    public boolean UnLock_P_F_F(int p,int f1,int f2)
    {
        Lock_L_F[f1-1] = Lock_L_F[f2-1] = Lock_L_P[p-1] = 0 ;
        No_F_L = No_F_L -2 ;
        No_P_L -- ;
        return true ;
    }
    
}

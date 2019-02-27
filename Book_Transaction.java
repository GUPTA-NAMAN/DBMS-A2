
class Book_Transaction extends Empty_Transaction
{
    private int Passenger_ID ;
    private int Fligth_ID ;
    private  static int type = 3 ;
    Book_Transaction(int ID,Locker locker,int Passenger_ID,int Fligth_ID)
    {
        super(ID,locker) ;
        this.Passenger_ID = Passenger_ID ;
        this.Fligth_ID = Fligth_ID ;
    }
    
    @Override
    public void run()
    {
       
        int[] pi=new int[] { Passenger_ID } ;
        int[] fi=new int[] { Fligth_ID } ;
        
        while(true)
        {
            boolean CanProceed = false ;
            CanProceed = super.Locks(true,type,pi,fi) ;
            if(CanProceed==true)
            {
                
                break ;
            }
            else
            {
                Thread.yield() ;
            }
        }
        
        try
        {
            Thread.sleep(1) ;
        }
        catch(Exception e)
        {
            System.out.println(e) ;
        }
        
        if( locker.F_Get_NB(super.ID,Fligth_ID) < locker.F_Get_C(super.ID,Fligth_ID) && locker.F_Get_BD(super.ID,Fligth_ID,Passenger_ID) == 0 )
        {
            locker.F_UpDate_BD(super.ID,Fligth_ID,Passenger_ID,1) ;
            locker.P_UpDate_BD(super.ID,Passenger_ID,Fligth_ID,1) ;
            int nb = locker.F_Get_NB(super.ID,Fligth_ID) + 1 ;
            locker.F_UpDate_NB(super.ID,Fligth_ID,nb) ;
        }
        
        boolean HasUnlocked = super.Locks(false,type,pi,fi)  ;
     //   System.out.println("unlocking "+HasUnlocked+" "+pi[0]+"  "+fi[0])   ;
    }
}

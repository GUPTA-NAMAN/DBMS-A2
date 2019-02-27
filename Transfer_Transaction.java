class Transfer_Transaction extends Empty_Transaction
{
    private int Passenger_ID ;
    private int P_Fligth_ID ;
    private int L_Fligth_ID ;
    private static int type = 4 ;
    Transfer_Transaction(int ID,Locker locker,int Passenger_ID,int P_Fligth_ID,int L_Fligth_ID)
    {
        super(ID,locker) ;
        this.Passenger_ID = Passenger_ID ;
        this.P_Fligth_ID = P_Fligth_ID ;
        this.L_Fligth_ID = L_Fligth_ID ;
    }
                         
    @Override
    public void run()
    {
        int[] pi=new int[] { Passenger_ID } ;
        int[] fi=new int[] { P_Fligth_ID,L_Fligth_ID } ;
        
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
        

        if ( locker.F_Get_BD(super.ID,P_Fligth_ID,Passenger_ID) == 1  && locker.F_Get_NB(super.ID,L_Fligth_ID) < locker.F_Get_C(super.ID,L_Fligth_ID) )
        {
            locker.F_UpDate_BD(super.ID,P_Fligth_ID,Passenger_ID,0) ;
            locker.P_UpDate_BD(super.ID,Passenger_ID,P_Fligth_ID,0) ;
            int nb=locker.F_Get_NB(super.ID,P_Fligth_ID) - 1;
            locker.F_UpDate_NB(super.ID,P_Fligth_ID,nb)  ;
            
            
            
            locker.F_UpDate_BD(super.ID,L_Fligth_ID,Passenger_ID,1) ;
            locker.P_UpDate_BD(super.ID,Passenger_ID,L_Fligth_ID,1) ;
            int nb2 = locker.F_Get_NB(super.ID,L_Fligth_ID) + 1 ;
            locker.F_UpDate_NB(super.ID,L_Fligth_ID,nb2) ;
            
        }
        
      boolean HasUnLocked =   super.Locks(false,type,pi,fi) ;
        
    }
                         
                         
}

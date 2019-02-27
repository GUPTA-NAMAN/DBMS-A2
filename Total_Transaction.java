class Total_Transaction extends Empty_Transaction
{
    static int type = 1 ;
    
    Total_Transaction(int ID,Locker locker)
    {
        super(ID,locker) ;
    }
    
    @Override
    public void run()
    {
        int[] pi=null ;
        int[] fi=null ;
        
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
        
        
        int sum = 0 ;
        for(int i=0;i<10;++i)
        {
            sum = sum + locker.F_Get_NB(super.ID,i+1) ;
        }
        
     //   System.out.println("number of bookings "+sum)  ;
        
        boolean HasUnLock = super.Locks(false,type,pi,fi) ;
        
        
    }
}

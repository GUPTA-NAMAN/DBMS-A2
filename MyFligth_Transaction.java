class MyFligth_Transaction extends Empty_Transaction
{
    int Passenger_ID ;
    static int type= 2 ;
    MyFligth_Transaction(int ID,Locker locker,int Passenger_ID)
    {
        super(ID,locker) ;
        this.Passenger_ID = Passenger_ID ;
    }
                         
    @Override
    public void run()
    {
        int[] pi=new int[]{ Passenger_ID} ;
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
        
    //    System.out.println("Codes of fligts")  ;
        for(int i=0;i<10;++i)
        {
            int BookStatus = locker.P_Get_BD(super.ID,Passenger_ID,i+1)  ;
            if ( BookStatus == 1)
            {
                System.out.print((i+1)+"\t")  ;
            }
        }
    //    System.out.println() ;
        boolean HasUnLocked  = super.Locks(false,type,pi,fi)  ;
    }
}

import java.util.* ;
import java.lang.Math ;
import java.util.Scanner ;
import java.util.concurrent.TimeUnit;
import java.util.Scanner ;

class Main
{
    public static void main(String args[])
    {
        
        DataBase db=new DataBase(1000,10,110) ;
        Locker lock = new Locker(1000,10,db) ;

        Scanner sc=new Scanner(System.in) ;
        boolean Concurrent = false ;
        int initai =sc.nextInt() ;
        int gap =sc.nextInt() ;
        int times =sc.nextInt() ;
        
        boolean IsCon = false ;
        
        int[] input =new int[times];
        int[] output = new int[times] ;
        
        Empty_Transaction[] trans  ;
        for(int i=1;i<=times;initai=initai+gap,++i)
        {
            
           
            
            trans = new Empty_Transaction[initai] ;
            
            for(int j=0;j<initai;++j)
            {
                int choice = (int)(  Math.random()*3+1 ) ;
                int pi = (int) ( Math.random()*1000 + 1 ) ;
                int f1=(int) ( Math.random()*10 + 1 ) ;
                int f2= (int) ( Math.random()*10 + 1 ) ;
                if(choice==0)
                {
                    trans[j]=new Book_Transaction(j+1,lock,pi,f1) ;
                }
                else if(choice==1)
                {
                    trans[j]=new UnBook_Transaction(j+1,lock,pi,f1) ;
                }
                else if(choice==2)
                {
                    trans[j]=new MyFligth_Transaction(j+1,lock,pi) ;
                }
                else if(choice==3)
                {
                    trans[j]=new Transfer_Transaction(j+1,lock,pi,f1,f2)  ;
                }
                else
                {
                    trans[j] = new Total_Transaction(j+1,lock) ;
                }
            }
            
            long first = System.currentTimeMillis()  ;
            for(int j=0;j<initai;++j)
            {
                trans[j].start() ;
                if(!IsCon)
                {
                    try
                    {
                    trans[j].join() ;
                    }
                    catch(Exception e)
                    {
                        System.out.println(e)   ;
                    }
                }
            }
            long last = System.currentTimeMillis() ;
            
            
            if(IsCon)
            {
            for(int j=0;j<initai;++j)
            {
                try
                {
                    trans[j].join() ;
                }
                catch(Exception e)
                {
                    System.out.println(e) ;
                }
            }
            }
            
            input[i-1] = initai ;
            output[i-1] =(int) (last - first);
            
            
            
            if(i<times)
            {
                System.out.print(output[i-1]+" ")  ;
            }
            else
            {
                System.out.print(output[i-1])  ;
            }
            
        }
        System.out.println() ;
    }
}

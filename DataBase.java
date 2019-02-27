import java.util.ArrayList ;

class DataBase
{
    public int No_Passenger ;
    public int No_Fligth ;
    public int Limit_Capacity ;
    public Passenger_Data[]  Passenger_List ;
    public Fligth_Data[]    Fligth_List ;
    
    DataBase(int No_Passenger,int No_Fligth,int Limit_Capacity)
    {
        this.No_Passenger = No_Passenger ;
        this.No_Fligth = No_Fligth ;
        this.Limit_Capacity = Limit_Capacity ;
        Passenger_List = new Passenger_Data[No_Passenger] ;
        Fligth_List = new Fligth_Data[No_Fligth] ;
        for(int i=1;i<=this.No_Passenger;++i)
        {
            Passenger_List[i-1] = new Passenger_Data(i) ;
        }
        for(int i=1;i<=this.No_Fligth;++i)
        {
            Fligth_List[i-1] = new Fligth_Data(i,Limit_Capacity) ;
        }
    }
}

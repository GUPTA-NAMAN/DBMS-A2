import java.util.ArrayList ;;

class Fligth_Data
{
     int Fligth_ID ;
     int Capacity ;
     int No_Booking ;
     int[] Passenger_List ;
    
    Fligth_Data(int Fligth_ID , int Capacity)
    {
        this.Fligth_ID = Fligth_ID ;
        this.Capacity = Capacity ;
        No_Booking = 0 ;
        Passenger_List = new int[1000] ;
    }
}

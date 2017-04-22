/**
 * @author Sanjiv Karki
 */
 
import java.util.*;
import java.io.*;

/**
 * This garage class uses stack and queue to store car the parking lot and in the waiting line respectively.
 */
public class Garage
{
   /**
    * Initializes stack, queue and other variables.
    */
   private StackInt<String> stackCars;
   private Queue<String> queueCars;
   public static final int GARAGE_CAPACITY=7;
   public static final int WAITING_CAPACITY=5;
   private int parkedCarsSize=0;
   private int numberOfMovedCars=0;

   /**
    * An empty constructor to instantiate a stack and a queue.
    */
   public Garage()
   {
	   stackCars = new LinkedStack<String>();
	   queueCars= new LinkedList<String>();
   }
   
   /**
    * Contructor that takes the file name as a parameter, reads it and calls the corresponding method.
    * @param fileName the name of the file.
    */ 
   public Garage(String fileName)
   {
      stackCars = new LinkedStack<String>();
	   queueCars= new LinkedList<String>();
      String str;
      
      /**
       * This try block tries to open the file which is passed as parameter in the constructor.
       * If successful, this block executes otherwise control is passed to the catch block.
       */ 
      try
      {
         File inputFile = new File (fileName);
         Scanner in = new Scanner (inputFile);
         
         while(in.hasNext())
         {
            str = in.nextLine();
            if ((str.substring(0,1)).equals("a"))
            {
               arrival(str.substring(2));
            }
            else if((str.substring(0,1)).equals("d"))
            {
               departure(str.substring(2));
            }
         }
      }
      /**
       * This catch block executes if the file is not found. This block initializes an empty garage.
       */
      catch(FileNotFoundException e)
      {
         new Garage();
      }
    }
   
   /**
    *  This method stores the car arrived in the parking lot up to maximum of seven cars and in the 
    *  waiting line up to five cars in stack and queue respectively. If the car having same license plate 
    *  is arrived, it is not added in the stack and returns false
    *  @param license License plate as parameter.
    *  @return Returns a boolean.
    */
   public boolean arrival(String license)
   {
      StackInt <String> temp = new LinkedStack<String>();
      String parkedCar;
      
      /**
       * Checks if the stack is empty.
       */
      if(stackCars.empty())
      {
         stackCars.push(license);
         parkedCarsSize++;
         return true;
      }
      
      /**
       * Checks if the stack car is not empty and size of queue cars is less than waiting capacity.
       */
      else if(!stackCars.empty() && queueCars.size()<WAITING_CAPACITY)
      {
         /**
          * Checks if the number of parked cars is less than garage capacity.
          */
         if(parkedCarsSize<GARAGE_CAPACITY)
         {
            /**
             * Runs the loop until the stack is empty.
             */
            while(!stackCars.empty())
            {
               parkedCar = stackCars.pop();
               temp.push(parkedCar);
               
               /**
                * Compares the parked cars with the parameter passed.
                */
               if(parkedCar.equals(license))
               {
                  while (!temp.empty())
                  {
                     stackCars.push(temp.pop());
                  }
                  return false;
               }      
            }
            
            /**
             * Restores the values stored in temp stack to the original stack cars.
             */
            while(!temp.empty())
            {
               stackCars.push(temp.pop());
            }
            
            stackCars.push(license);
            parkedCarsSize++;
            return true;
         }
         else
         {
            queueCars.add(license);
            return true;
         }
      }
      else
      {
         return false;
      }
   }
      
   /**
    * This method takes the license number of the car as the parameter and returns the number of moved cars in the parking lot.
    * If the license number is not found, -1 is returned.
    * Also updates the parked car size.
    * @param license the license number of the car that needs to be departed.
    * @return Returns the number of moved cars in the parking lot. 
    */   
   public int departure(String license)
   {
      StackInt <String> temp = new LinkedStack<String>();
      String parkedCar;
      int helpingVariable=0;
      
      /**
       * Runs the loop until the stack car is empty.
       */
      while(!stackCars.empty())
      {
         parkedCar = stackCars.pop();
         temp.push(parkedCar);
         
         /**
          * Compares the parked cars with the parameter passed.
          */         
         if(parkedCar.equals(license))
         {
            temp.pop();
            parkedCarsSize=parkedCarsSize-1;
            while (!temp.empty())
            {
               stackCars.push(temp.pop());
            }
            if(!queueCars.isEmpty() && numberOfMovedCars<GARAGE_CAPACITY)
            {
               stackCars.push(queueCars.remove());
               parkedCarsSize++;
            }
            helpingVariable = numberOfMovedCars;
            numberOfMovedCars=0;
            return helpingVariable;
         }
         numberOfMovedCars++;
      }
      /**
       * Restores the values stored in temp stack to the original stack cars.
       */
      while(!temp.empty())
      {
         stackCars.push(temp.pop());
      }
      /**
       * If car is not found, -1 is returned.
       */
      return -1;         
   }
   
   /**
    *  Returns the number of car parked in the parking lot.
    *  @return parkedCarsSize the number of car parked in the parking lot.
    */
   public int numberParked()
   {
      return parkedCarsSize;
   }
   
   /**
    *  Returns the number of car waiting in the waiting line.
    *  @return Returns the number of car waiting in the waiting line.
    */ 
   public int numberWaiting()
   {
      return queueCars.size();
   }
   
   /**
    * This toString method returns the license plate number of the car stored in the parking lot and waiting line if any in the correct formatted order.
    * @return toString A formatted string that returns the license plate number of the car in the parking lot and in the waiting line.
    */ 
   public String toString()
   {
      StackInt <String> temp = new LinkedStack<String>();
      Queue <String> tempTwo  = new LinkedList<String>();
      String str;
      String str1= "";
      String str2= "";
      
      /**
       * Checks if the stack car is empty or not.
       */
      if(!stackCars.empty())
      {
         /**
          * Runs loop until the stack car is empty and concatenate value stored in the str to str1.
          */
         while(!stackCars.empty())
         {
            str = stackCars.pop();
            temp.push(str);
            str1 = str1 +" "+ str;
         }
         /**
          * Restores the values stored in tempOne stack to the original stack cars.
          */
         while (!temp.empty())
         {
            stackCars.push(temp.pop());
         }
         /**
          * Checks if the queue is empty or not.
          */
         if(!queueCars.isEmpty())
         {
            /**
             * Runs loop until the queue car is empty and concatenate the value stored in str to str2.
             */

            while(!queueCars.isEmpty())
            {
               str = queueCars.remove();
               tempTwo.add(str);
               str2 = str2 + " " + str;
            }
            /**
             * Restores the values stored in tempTwo queue to the original queue cars.
             */
            while(!tempTwo.isEmpty())
            {
               queueCars.add(tempTwo.remove());
            }
            return "The car(s) in the parking lot is/are:"+str1+"\n"+"The car(s) in the waiting line is/are:"+str2+"\n";
         }
         else
         {
            return "The car(s) in the parking lot is/are:" +str1+"\n"+"There is no car in the waiting line.\n";
         }
      }
      
      else
      {
         return "No cars in the parking lot.\n No cars in the waiting line.\n" ;
      } 
   }
}
  
   
   
   
public class MyClass {
    public static void main(String args[]) {
    
      // initial simulated coordinate string
      String scannerString  = "(+90,-180) (90D,-180) (-84.32,+123) (070,140.23) (+90.9.0,90) (SE,NW)";
      
      // split the input data by the whitespace to isolate the values
      String[] inputStr     = scannerString.split(" ");
      
      // init a string for holding the answered values
      String answer         = "";
      
        // iterate through the isolated values and test strings against criteria
        for (int i = 0; i < inputStr.length; i++)
        {
            answer += testStrings(inputStr[i]);
        }
        
        // print the results out
        System.out.println(answer.trim());
    }
    
    public static String testStrings(String inputStr)
    {
        /* Regular expression to test against the initial schema
           Each coordinate is valid if:
                                        It has an opening and closing parenthesis at beginning and end, respectively
                                        X and Y must be separated by a single comma with no whitespace
                                        X and Y may be preceded by a single sign + or -
                                        X >= -90 <= 90   Y >= -180 <= 180
                                        No letter characters are present
                                        No special characters besides a single decimal in the digit values
                                        X nor Y may begin with zeros
                                        
        */
        String reg = "[(]{1}[+-]{0,1}([1-9]+[0-9]+|[0-9]+[.]{1}[0-9]+)[,]{1}[+-]{0,1}([1-9]+[0-9]+|[0-9]+[.]{1}[0-9]+)[)]{1}";
        
        // init a string for holding the results
        String result = "";
      
        // validate the input string against the RE
        if (inputStr.matches(reg))
          {
              // the string devoid of parentheses
              String noBrackets = inputStr.replaceAll("[()]", "");
              
              // the parentheses-less string split by the comma to allow for testing of the digit values conditions
              String[] resArr   = noBrackets.split(",");
              
             // parsing the string digit values into float data type for logic
             float strNum0 = Float.parseFloat(resArr[0]);
             float strNum1 = Float.parseFloat(resArr[1]);
                  
             // validate the float values against the requirements
             // validate x
             if (strNum0 >= -90.0f && strNum0 <= 90.0f)
             {
                 // validate y
                 if (strNum1 >= -180.0f && strNum1 <= 180.0f)
                 {
                     // both tests valid, set the result string to valid
                     result = "Valid ";
                 }
                 else 
                 {  // first test pass, second fail, = failed test
                    // set the result string to invalid
                    result = "Invalid ";    
                 }
             }
              
          }
          else
          { 
              // initial RE conditions were not met, set the result string to invalid
              result = "Invalid ";
              
          }
          
          return result;
    }
}
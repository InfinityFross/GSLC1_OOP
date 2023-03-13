import java.util.Scanner; // Import the scanner class
public class CalculateAverageTemperature {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] temperatures = new double[7];
        System.out.print("WELCOME TO THE TEMPERATURE CALCULATOR\n");
        System.out.print("Please enter the temperature from day 1 until day 7 to get the average temperature for the week!\n");
        
        for (int i = 0; i < temperatures.length; i++) {
            System.out.print("Enter temperature for day " + (i+1) + " (in Celcius)" + ": ");
            temperatures[i] = scanner.nextDouble();
        }
        
        double average = calculateAverage(temperatures);
        System.out.println("The average temperature for the week is " + Math.round(average) + " degree Celcius ");
    }
    
    private static double calculateAverage(double[] temperatures) {
        double sum = 0;
        for (double temperature : temperatures) {
            sum += temperature;
        }
        return sum / temperatures.length;
    }
}

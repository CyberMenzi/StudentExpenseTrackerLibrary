
package tut.ac.za.exp;

/**
 *
 * @author Olama
 */
public class Expense {
    
    private String stuName;
    private String category;
    private double amount;
   

    public Expense(String stuName, String category, double amount) {
        this.stuName = stuName;
        this.category = category;
        this.amount = amount;
        
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" + "Name =" + stuName + ", category =" + category + ", amount =" + amount + '}';
    }

  

    
   
    
    
    
    
    
}

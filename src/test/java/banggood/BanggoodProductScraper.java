package banggood;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BanggoodProductScraper {
	
	public static void main(String[] args) {
    	

 	   // Disable notification
 		   ChromeOptions opt= new ChromeOptions();
 		   opt.addArguments("--disable-notifications"); 
		
     
     // Initialize WebDriver
     WebDriver driver = new ChromeDriver(opt);

     try {
         // Open the URL
   driver.get("https://www.banggood.in/Wholesale-RC-Car-ca-7008.html?cat_id=7008&page=1&direct=0&rec_uid=2566157916|1726302204&bid=81131&sort=1&sortType=desc");

		
         //Maximize the page
         driver.manage().window().maximize();
      
         //Handling cookies
         driver.findElement(By.xpath("//div[@class='cookie-pop-main']//button[@class='confirm'][normalize-space()='Allow and Close']")).click();
         
 		// Iterate over 8 pages
         for (int page = 1; page <= 8; page++) 
         {
            System.out.println("Scraping Page: " + page);

             // Get list of all product elements on the current page
             List<WebElement> products = driver.findElements(By.xpath("//ul[@class='goodlist']/li"));

             for (WebElement product : products) 
             {
             	   
             	// Extract product details

             	// Category
                 String category = "RC Car";

                 // Title
                 String title = product.findElement(By.xpath("//a[@class='title']")).getText();

                 // Price
                 String price = product.findElement(By.xpath("//span[@class='price']")).getText();

                 // Shipping Fee
                 String shippingFee;
                 try {
                     shippingFee = product.findElement(By.className("shipping-price")).getText();
                 } catch (Exception e) {
                     shippingFee = "Free Shipping or Not Listed";
                 }

                 // Image URL
                 String imageUrl = product.findElement(By.tagName("img")).getAttribute("src");

                 // Description
                 String description = title;
                 
                 // Print extracted data for each product
                 System.out.println("Category: " + category);
                 System.out.println("Title: " + title);
                 System.out.println("Price: " + price);
                 System.out.println("Shipping Fee: " + shippingFee);
                 System.out.println("Image URL: " + imageUrl);
                 System.out.println("Description: " + description);
                 System.out.println("---------------------------------");
             }

             // If not on the last page, navigate to the next page
            if (page < 8) {
                WebElement nextButton = driver.findElement(By.xpath("//i[@class='iconfont icon-Arrow-right next-page']"));
                 nextButton.click();     

                 
                 Thread.sleep(3000);
             }
         }

     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         // Close the driver after the scraping process
         driver.quit();
     }
 }


}

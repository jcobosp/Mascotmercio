package es.upm.dit.isst.mascotmercioapifront;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class Caso1ValorarEstablecimientoTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {

    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void caso1ValorarEstablecimiento() {
    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    driver.get("http://localhost:8083/index_NotLogged");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Registrarse")).click();
    driver.findElement(By.id("nombreUsuario")).click();
    driver.findElement(By.id("nombreUsuario")).sendKeys("jesus");
    driver.findElement(By.id("contraseña")).sendKeys("1a2b3c");
    driver.findElement(By.id("nombreCompleto")).sendKeys("Jesús Cobitos");
    driver.findElement(By.id("email")).sendKeys("jesus@cobos");
    driver.findElement(By.id("telefono")).sendKeys("1234");
    driver.findElement(By.id("ciudad")).sendKeys("Miami");
    driver.findElement(By.name("user_type")).click();
    {
      WebElement dropdown = driver.findElement(By.name("user_type"));
      dropdown.findElement(By.xpath("//option[. = 'Cliente']")).click();
    }
    executor.executeScript("window.scrollBy(0, 450);");
    WebElement descripcion = driverWait.until(ExpectedConditions.elementToBeClickable(By.id("descripcion")));
    descripcion.click();
    driver.findElement(By.id("descripcion")).sendKeys("Soy un crack :)");
    driver.findElement(By.cssSelector("button")).click();

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("jesus");
    driver.findElement(By.id("password")).sendKeys("1a2b3c");
    driver.findElement(By.id("botonInicioSesion")).click();

    driver.findElement(By.linkText("Establecimientos")).click();

    // driver.findElement(By.linkText("Más información")).click();
    driver.get("http://localhost:8083/detallesEstablecimiento_Cliente?id=4");

    {executor.executeScript("window.scrollBy(0, 2300);");}

    driver.findElement(By.id("titulo")).click();
    driver.findElement(By.id("titulo")).sendKeys("Me lo pasé genial!!!");
    driver.findElement(By.id("comentario")).click();
    driver.findElement(By.id("comentario")).sendKeys("En serio,\\n\\nGeniaaaaaal!!!");
    driver.findElement(By.cssSelector("label:nth-child(22)")).click();
    driver.findElement(By.cssSelector(".text-center:nth-child(6) > button")).click();
  }
}

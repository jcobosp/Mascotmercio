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
public class Caso2ReservarEstablecimientoClienteTest {
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
  public void caso2ReservarEstablecimientoCliente() {
    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    driver.get("http://localhost:8083/index_NotLogged");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Registrarse")).click();
    driver.findElement(By.id("nombreUsuario")).click();
    driver.findElement(By.id("nombreUsuario")).sendKeys("jesus");
    driver.findElement(By.id("contraseña")).sendKeys("1a2b3c");
    driver.findElement(By.id("nombreCompleto")).click();
    driver.findElement(By.id("nombreCompleto")).sendKeys("Jesús Cobitos");
    driver.findElement(By.id("email")).sendKeys("jesus@cobos");
    driver.findElement(By.id("telefono")).sendKeys("1234");
    driver.findElement(By.id("ciudad")).sendKeys("Miami");
    driver.findElement(By.name("user_type")).click();
    {
      WebElement dropdown = driver.findElement(By.name("user_type"));
      dropdown.findElement(By.xpath("//option[. = 'Cliente']")).click();
    }
    executor.executeScript("window.scrollBy(0, 575);");

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

    executor.executeScript("window.scrollBy(0, 450);");


    driver.findElement(By.id("nombreCliente")).click();
    driver.findElement(By.id("nombreCliente")).sendKeys("jesus");
    driver.findElement(By.id("telefono")).sendKeys("1234");
    driver.findElement(By.id("mascota")).click();
    {
      WebElement dropdown = driver.findElement(By.id("mascota"));
      dropdown.findElement(By.xpath("//option[. = 'Tortuga']")).click();
    }
    executor.executeScript("window.scrollBy(0, 600);");

    driver.findElement(By.id("asistentes")).click();
    driver.findElement(By.id("asistentes")).sendKeys("2");
    driver.findElement(By.id("fe")).click();
    driver.findElement(By.id("fe")).sendKeys("29/02/2025");
    driver.findElement(By.id("hora")).sendKeys("24:00");
    driver.findElement(By.id("solicitudes")).click();
    driver.findElement(By.id("solicitudes")).sendKeys("No");
    driver.findElement(By.cssSelector(".text-center:nth-child(7) > button")).click();
  }
}

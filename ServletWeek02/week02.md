# 2.Hafta

### Handle Request

1) Son kullanıcı servlet'i tetikleyen/çağıran bir url'e tıklar. (linke tıklıyor, form submit edildi vs.)

2) Servlet Container gelen isteğe karşılık iki tane obje oluşturur.
   * Http Servlet Request
   * Http Servlet Response objeleridir.

3) Servlet Container yeni bir thread oluşturur. (Thread oluşturulmasından/yönetilmesinden vs. container sorumludur.) service() metodunu çağırır.

 ```java
 Servlet
    service()

public void service() (ServletRequest req, ServletResponse rep) throws ServletException, IOException {

}
 ```
* Spring MVC'de DispatcherServlet -> FrontController'dır. Aynı mantığa göre(benzer) çalışıyor. 
* 2.adımda oluşturulan request ve response objelerini service metoduna parametre geçer.
   - Request objesi : Browserdan gelen bilgileri saklamak, kayda almak ve gerekli durumlarda kullanmak için oluşturulur.   
   - Response objesi : İstek tamamlandıktan sonra geriye dönüşte kullanılıyor. 

4) Gelen istek hangi tipte istek ise service metodunda ilgili metoda yönlendirme yapılır. 
   - get  -> doGet
   - post -> doPost'a yönlendirme yapılır. 
   jax-rs'teki @GET, @POST  yada Spring MVC'deki RequestMethod = GET / = POST gibi.  

5) İlgili metot çalışır. doGet/ doPost vs. İstek karşılanır. 

6) service metodu sonlanır. Thread sonlanır. İlgili request/response objeleri Garbage Collector tarafından temizlenmeye uygun hale gelir. 


### LifeCycle 

NOT : Tomcat Web-INF -> class folder'ı altında bizim .class dosyalarımız yer alır. 

1) Container, Servlet class dosyalarını bulur(web.xml dosyasından veya annotation'dan yararlanarak). Bulunan dosyalar loading işlemine tabi tutulur. 
   Servlet'ler:
   * public olmak zorundadır. 
   * public no-arg constructor olmak zorundadır.
   * Genel olarak servlet'lerde constructor tanımlanmaz. 
   * Constructor yerine init metodu tercih edilir. (initialize method)

   NOT : Servlet objesi server(örn. Tomcat) tarafından oluşturuluyor. Servlet'lerin main metodu yoktur. Tomcat default Singleton yapıda. Tek bir obje oluşur ve bu tek objeye              karşılık thread'ler oluşur.
   
2)  Servlet Initialize  
    Initialize mantığına birçok projede ihtiyaç duyulur. Proje ayağa kalkarken, istek karşılanmadan önce, ilgili servlet ilk defa çalışmadan önce vs. bir konfigürasyon yapmak       isteriz. Bu mantığa initialize mantığı denilebilir. (İlk değere hazırlama)
    
    Servlet'lerde bu mantık init metoduyla yapılmaktadır. 
    ```java
    public void init(ServletConfig config) throws ServletException {}
    
    public void init() throws ServletException {}
    
    ```
3) Bu noktaya kadar obje oluştu, initialize edildi. İstek artık karşılanabilir.(handle request)    
    * service() metodunu override etme. 
    * doGet() / doPost() vs. override edilecek.
    * Gelen her isteğe karşılık bir thread oluşur. 
    
4) doGet() / doPost() vs. metotları çalışır.   
 
    Kısaca;
    * Load HelloServlet.class
    * Instantiate HelloServlet
    * init() method running
    * service() method running
    * destroy()
 
    Lifecycle boyunca init() metodu bir kez çalışır. Kodda değişiklik olunca destroy aşamasına geçilir ve yeni bir obje oluşur. 
 
 ### ServletConfig
 
       javax.servlet.ServletConfig 
    
   ServletConfiguration olarak düşünülebilir. Interface'dir. 
     * getServletName(), getServletContext(), getInitParameter(), getInitParameterNames() metotları vardır. 
     * ServletConfig objesi her Servlet için bir tane oluşur. Özellikle kullanım amacı initialize-parameter'lardır. (init-param)
     * ServletConfig objesi ile web.xml dosyasında yer alan veya annotation ile belirtilmiş servlet için tanımlanmış init parameter bilgilerine erişim sağlayabiliriz. Bu                parametreler bir kez okunur. 
   
       //GenericServlet sınıfının metodu. HttpServlet'i extends ettiğimiz için config objesine sahibiz. 
       ServletConfig config= getServletConfig();
       
       // config üzerinden InitParameter'lara erişim sağlanabilmektedir.
       String name = config.getInitParameter("username");
       String pass = config.getInitParameter("password");
       
       String servletName = config.getSrevletName();
       
       ServletContext context = config. getServletContext();
       
       Enumeration<String> names = config.getInitParameterNames();
         
   **ServletConfig konfigürasyonunun xml ve annotation'dan yapılması**
   
   * Annotation ile: 
       ```java
       @WebServlet(urlPatterns="/configAnnotation", initParams = @WebInitParam(name="role", value="admin"))
       public class ConfigServletAnnotation extends HttpServlet{
       }  
       ```
   * XML ile:
      
         <servlet>
		       <servlet-name>MyConfigServlet</servlet-name>
		       <servlet-class>servletconfig.ConfigServlet</servlet-class>
           <!-- konfigurasyon yapılan sınıfın packagename.classname -->
		      <init-param>
			      <param-name>username</param-name>
		  	    <param-value>admin</param-value>
		      </init-param>

		      <init-param>
			      <param-name>password</param-name>
			      <param-value>123456</param-value>
		      </init-param>
	       </servlet>

         <servlet-mapping>
		       <servlet-name>MyConfigServlet</servlet-name>
		       <url-pattern>/config</url-pattern>
	       </servlet-mapping> 
       
   ### ServletContext
   
   ServletConfig -> Servlet'e ait init parametrelerine erişim sağladık. 
   ServletContext -> Proje için geçerli init parametreleri vardır. Context init parameter adı verilmektedir. Bunlar bütün Servlet'ler veya JSP'ler için geçerlidir.
   
   Servlet tanımının yapıldığı yerde context parametresi tanımlanabilir. 
   
    <init-param> -> <servlet> ... </servlet> 
   tag'leri arasında yer alıyor çünkü Servlet'e özgü.
  
   Context parametresi ise projeye özgü parametrelerdir. Dolayısıyla servlet tanımının dışında yer alır.Örneğin web.xml'de :
   
       <context-param>
			     <param-name>rootPath</param-name>
		  	   <param-value>/user/asli</param-value>
		   </context-param>
      
   şeklinde context-param tanımlaması yapılabilir. Birden fazla da tanımlanabilir.
   
   Bu tanımlanan context-param'ı kullanmak için:
   
       @WebServlet("/contextServlet")
       public class ContextServlet extends HttpServlet{
       
            @Override
	          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		      
                //GenericServlet
                ServletContext  context =  getServletContext();
                //ServletContext context2 = getServletConfig.getServletContext(); 
	
		            String pathInfo = context.getInitParameter("rootPath");
		           resp.getWriter().print(pathInfo);
	          }
       }  
   
   

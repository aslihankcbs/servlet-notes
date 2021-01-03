# 2.Hafta

**Handle Request**

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
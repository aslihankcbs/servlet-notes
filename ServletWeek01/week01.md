# 1.Hafta

**Servlet Nedir?**

Servletler bir Java sınıfıdır.Dinamik içerik üretme sorununa Java'nın getirdiği çözümdür.
* Bir Servlet HTTP kullanmak zorunda değil.
* Bir Servlet response olarak HTML döndürmek zorunda da değildir.

**Server(Sunucu) Nedir? Sorumlulukları Nelerdir?**

Server denilince aklımıza:
* Fiziksel makine (hardware)
* Bir application (software) gelir.

Server'in görevleri ise:
* Client (istemci)'in isteğini karşılamak -> client resource isteğinde bulunuyor. (handle request)
* Bir isteğe karşılık bir response oluşturmak, dönmek.

Resource -> html, pdf, png, js vs...

**Client Nedir?**

Son kullanıcı veya bir browser olabilir.
Browser'lar html sayfalarını yorumlar ve son kullanıcının anlayacağı şekilde render eder.

**HTTP (Hyper Text Transfer Protocol)**

Browser ile server arasındaki iletişim HTTP ile gerçekleştirilmektedir. (İletişim -> Kaynağın(resource) istenmesi, cevap(response) dönülmesi vb.)

**Static Web Pages/Static Resources**

Static web sayfaları -> her kullanıcı aynı sayfayı/sayfaları görür.HTML bizim için bir dinamiklik sağlamaz.(Dinamiklik: web service'e bağlanmak/veri tabanına bağlanmak vs.)

Bu noktada Java web dünyasında en temel/basit olarak Servlet yapıları kullanılmaktadır. Servlet'ler kullanarak bu dinamikliği sağlayabiliriz.

**Application Server**
Java dünyasında bir çok application server bulunmaktadır. Java Servlet Spesifikasyonu gibi tüm JavaEE (CDI, JAX-RS, EJB vb.) özelliklerini implement eden web sunucularıdır.

Application Server: Apache TomEE, Oracle Web Logic, Glassfish, JBoss ...
Web Server: Apache, Nginx, Lighttpd
Servler Container: Apache Tomcat, Jetty

Tomcat: Bir server olarak çalışmakta ve uygulamamızı publish etmektedir.

# Servlet Konteyner(Servlet Container/Servlet Engine)
* Java Servlet Spesifikasyonu'nun herhangi bir sürümünü implement eden web sunucularıdır.
* Servlet'lerin tüm kontrolü Servlet Konteyner tarafından yapılmaktadır.
* Servlet'lerin main metodu yoktur. 
* İstemciden gelen kaynak isteği bir statik kaynaksa bunu direkt olarak web server karşılar. Eğer gelen istek bir servlet'se veya JSP ise bunu Servlet Konteyner karşılamaktadır.   Yani gelen istek dinamik bir kaynaksa bunu Servlet Konteyner karşılar.
  Web Browser -> Web Server -> Web Container -> Servlet Container
  
**Servlet Konteyner neler sağlar?**
1) İletişim Desteği:
  * Servlet ile server arasındaki iletişimi sağlar. Bu sayede port dinle, stream oluştur, http protokolüne göre kodu yaz, socket aç vs. işlemlerine gerek duyulmaz.
2) Yaşam Döngüsü Yönetimi:
  * Servlet'lerin bütün yaşam döngüsü konteynerin sorumluluğundadır. Initialize edilmesi, objenin oluşturulması vs.
3) Multithread Desteği:
  * Her istek geldiğinde bir tane thread oluşturulur.
4) JSP Desteği:
  * Konteynerlar JSP dosyalarını translate eder.
  
Yani Tomcat'te yer alan Servlet Engine bunları sağlamaktadır.  

**Glassfish & Apache Tomcat Farkları Nelerdir?**
* Glassfish bir application server'dır. Ekstra özellik barındırır. Tomcat'ten daha fazla modüle destek verir. İçerisinde daha fazla Enterprise Edition modülüne destek barındırır. 
* Apache Tomcat sadece Servlet Container özelliği gösterir. EJB beanlerine, CDI beanlerine destek sağlamaz. Spring MVC çalıştırabilir.

**Konfigürasyon**

Java'da Servlet, JSF, JPA, Spring gibi yapıların konfigürasonları genel olarak 2 farklı yaklaşımla yapılmaktadır.
* XML Yaklaşımı
* Annotation Yaklaşımı

Annotation yaklaşımı biraz daha pratik bir yaklaşım olmakla beraber birbirlerine göre üstünlükleri vardır. 

  **XML Konfigürasyonu** 
  * web.xml -> Deployment Descriptor
  * Adı web.xml olmak zorundadır.
  * WEB-INF'te bulunmalıdır.
  * Her request sonrasında thread oluşuyor. Thread yönetimi konteyner altında!
  
  **Annotation ile Konfigürasyon**
  * İlgili sınıf üzerinde:
    ```java
    @WebServlet("/annotationServlet") 
    ```
    çoklu URL vermek için ise
     ```java
    @WebServlet(urlPatterns = {"/annotationServlet", "annotationServlet2"}) 
    ```

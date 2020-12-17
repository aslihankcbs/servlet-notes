# 1.Hafta

**Servlet Nedir?**

Servletler bir Java sınıfıdır.

**Server(Sunucu) Nedir? Sorumlulukları Nelerdir**

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

**HTTP (Hyper Text Transfer Protocol) **

Browser ile server arasındaki iletişim HTTP ile gerçekleştirilmektedir. (İletişim -> Kaynağın(resource) istenmesi, cevap(response) dönülmesi vb.)

**Static Web Pages/Static Resources**

Static web sayfaları -> her kullanıcı aynı sayfayı/sayfaları görür.HTML bizim için bir dinamiklik sağlamaz.(Dinamiklik web service'e bağlanmak/veri tabanına bağlanmak vs.)

Bu noktada Java web dünyasında en temel/basit olarak Servlet yapıları kullanılmaktadır. Servlet'ler kullanarak bu dinamikliği sağlayabiliriz.

**Application Server**

Java dünyasında birçok application server bulunmaktadır. Tomcat, Weblogic, Glassfish, Jetty...
Tomcat: Bir server olarak çalışmakta ve uygulamamızı publish etmektedir.

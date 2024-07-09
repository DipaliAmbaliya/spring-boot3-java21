# Spring boot with Java21

## Spring-boot Version : 3.3.1

### **Code**
- Native Images with GraalVM
  - To build the image, you can run the **spring-boot:build-image** goal with the native profile active: **mvn -Pnative spring-boot:build-image**
- Virtual Threads
  - **spring.threads.virtual.enabled** to enable virtual threads in your application.This setting works both for the embedded Tomcat and Jetty.
  - **Configuration beans**
    - **ApplicationTaskExecutor**, replaces the standard ApplicationTaskExecutor. In short, we want to override the default Executor so it starts a new virtual thread for each task. 
    - **ProtocolHandlerVirtualThreadExecutorCustomizer**, customizes the standard TomcatProtocolHandler in the same way.
- Using Records with Spring Data JPA
  - It enables us to use records with Spring Data JPA repositories
  - Automatic Mapping from Entity to Record
- Spring MVC and WebFlux URL Matching Changes
  - the trailing slash matching configuration option has been deprecated and its default value set to false. We can change the default with global Spring MVC configuration
  - **Configuration beans**
    - **PathMatchConfigurer**, override for Spring MVC and WebFlux URL Matching Changes
- Jakarta EE
  - Spring Boot 3 has migrated from Java JEE to Jakarta JEE APIs for almost all dependencies.Jakarta EE now uses jakarta packages rather than javax. 
- Image Banner Support Removed 
  - Support for image-based application banners has been removed. banner.gif, banner.jpg, and banner.png files are now ignored and should be replaced with a text-based banner.txt file.
- Spring Security 6.0
  - WebSecurityConfigurerAdapter deprecated
  - authorizeRequests() - deprecated. we should now use authorizeHttpRequests
  - requestMatchers replacing antMatchers, mvcMatchers & regexMatchers
  - **Configuration beans**
    - **SecurityFilterChain**,we should now take a more component-based approach and create a bean of type SecurityFilterChain.
- SSL bundle
  - **spring.ssl.bundle** create objects that provide access to the specified trust material
  - **Configuration beans**
    - inject the **SslBundles** instance into the Autowired constructor.SslBundles provides us access to all configured SSL Bundles. Therefore, we retrieve the demo bundle and create the context.
    - use the SSLContext instance to create a custom HttpClient and apply it to create a **SecureRestTemplateConfig** bean.
- Observability
    - Observability is the ability to observe the internal state of a running system from the outside. It consists of the three pillars logging, metrics and traces.
    - SpringBoot 3 offers us the ObservationRegistry interface that can be implemented to create observation which provides a single API for both metrics and traces.
    - **Configuration beans**
      - **ObservationRegistry**,to create observation which provides a single API for both metrics and traces.
      - **ObservationHandler**,This handler gets notified about the Observation‘s lifecycle events, and therefore provides callback methods.
      - **ObservationTextPublisher**, it registers ObservationHandler beans at the ObservationRegistry.
      - **ServerHttpObservationFilter**,For MVC there is a filter that we can use for HTTP server observations. When Actuator is part of our application, this filter is already registered and active. If not, we need to configure it
      - **ObservedAspect**,register the aspect implementation as a Spring-managed bean
- Web Flux Enhancements
  - New PartEvent API to stream multipart form uploads
  - JDK HttpClient integrated with WebClient.
  - **Configuration beans**
    - **WebFluxConfigurer**,for configure webflux to implement WebFluxConfigurer
    - **WebFluxSecurityConfig**, to enable security config for webflux
    - **WebClient**,supports synchronous HTTP access, but it required an additional dependency spring-boot-starter-webflux.
- Http Interfaces
    - The Spring Framework lets you define an HTTP service as a Java interface with annotated methods for HTTP exchanges. You can then generate a proxy that implements this interface and performs the exchanges. This helps to simplify HTTP remote access which often involves a facade that wraps the details of using the underlying HTTP client.
      Prior to Spring Boot 3 for use the RestTemplate or WebClient to construct call.In Spring Boot 3 you can declare an interface with the methods you would like to support.Next, create a proxy that will perform the declared HTTP exchanges.
    - **Configuration beans**
      - The **HttpServiceProxyFactory** is a factory to create a client proxy from an HTTP service interface. Use its HttpServiceProxyFactory.builder(client).build() method to get an instance of the proxy bean.
- Problem Details for HTTP APIs
    - A common requirement for REST services is to include details in the body of error responses. The Spring Framework supports the "Problem Details for HTTP APIs" specification
    - check ExceptionHandlerAdvice.java
- Spring Boot 3 With Docker Compose Application
  - **spring-boot-docker-compose** dependency spins up all the services in the docker-compose.yml file.
  - **spring.docker.compose.enabled** and **spring.docker.compose.enabled.file** add this in an application-{profile} properties or YAML file
- Miscellaneous 
  - ListCrudRepository 
  - PagingAndSortingRepository no longer extends CrudRepository 
  - Apache HTTP client a JdkClientHttpConnector will now be auto-configured 
  - The @SpringBootTest annotation can now use the main of any discovered @SpringBootConfiguration class if it’s available. This means that tests can now pick up any custom SpringApplication configuration performed by your main method.

## Java Version : 21

### **Code**
- Virtual Threads

### **TestCases**

- Virtual Threads
- Sequenced Collection
- StringTest
- FutureTest
- RecordTest
# Spring boot with Java21

### Spring-boot Version : 3.3.1

**Code**
- Native Images with GraalVM
  - To build the image, you can run the spring-boot:build-image goal with the native profile active:mvn -Pnative spring-boot:build-image
- Virtual Threads
  - **spring.threads.virtual.enabled** to enable virtual threads in your application.This setting works both for the embedded Tomcat and Jetty.
- Spring MVC and WebFlux URL Matching Changes
  - the trailing slash matching configuration option has been deprecated and its default value set to false. We can change the default with global Spring MVC configuration
- Jakarta EE
  - As well as dependency coordinate changes, Jakarta EE now uses jakarta packages rather than javax. Once you’ve update your dependencies you may find that import statements in your project need to be updated.
- Image Banner Support Removed 
  - Support for image-based application banners has been removed. banner.gif, banner.jpg, and banner.png files are now ignored and should be replaced with a text-based banner.txt file.
- Actuator Endpoints Sanitization
   - Since, the /env and /configprops endpoints can contains sensitive values, all values are always masked by default. This used to be case only for keys considered to be sensitive.can be configured using the properties management.endpoint.env.show-values or management.endpoint.configprops.show-values which can have the following values:
    NEVER - All values are sanitized (the default).
    ALWAYS - All values are present in the output (any user-defined sanitizing functions will still apply).
    WHEN_AUTHORIZED - Values are present in the output only if a user is authorized (any user-defined sanitizing functions will apply).
- Spring Security 6.0
  - WebSecurityConfigurerAdapter deprecated
  - .authorizeRequests() - deprecated
  - RequestMatchers
- SSL bundle
  - **spring.ssl.bundle** create objects that provide access to the specified trust material
- Prometheus Support
  - Spring Boot 3.3 includes support for the Prometheus Client 1.x.
- Zipkin
    - A new JDK HttpClient based Zipkin sender has been implemented. This sender only depends on the JDK and will be the default sender in Spring Boot 3.5.0, superseding the WebClient and RestTemplate sender implementations.
- Observability
    - Observability is the ability to observe the internal state of a running system from the outside. It consists of the three pillars logging, metrics and traces.
    - Beans of type ObservationPredicate, GlobalObservationConvention and ObservationHandler will be automatically registered on the ObservationRegistry. You can additionally register any number of ObservationRegistryCustomizer beans to further configure the registry.
- Http Interfaces
    - The Spring Framework lets you define an HTTP service as a Java interface with annotated methods for HTTP exchanges. You can then generate a proxy that implements this interface and performs the exchanges. This helps to simplify HTTP remote access which often involves a facade that wraps the details of using the underlying HTTP client.
      Prior to Spring Boot 3 for use the RestTemplate or WebClient to construct call.In Spring Boot 3 you can declare an interface with the methods you would like to support.Next, create a proxy that will perform the declared HTTP exchanges.
- Problem Details for HTTP APIs
    - A common requirement for REST services is to include details in the body of error responses. The Spring Framework supports the "Problem Details for HTTP APIs" specification
    - check ExceptionHandlerAdvice.java
- Miscellaneous 
  - ListCrudRepository 
  - PagingAndSortingRepository no longer extends CrudRepository 
  - Apache HTTP client a JdkClientHttpConnector will now be auto-configured 
  - The @SpringBootTest annotation can now use the main of any discovered @SpringBootConfiguration class if it’s available. This means that tests can now pick up any custom SpringApplication configuration performed by your main method.

### Java Version : 21

**Code**
- Virtual Threads
- UnmodifiableSet

**TestCases**

- Virtual Threads
- Sequenced Collection
- StringTest
- FutureTest
- RecordTest
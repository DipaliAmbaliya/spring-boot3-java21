# Spring boot with Java21

### Spring-boot Version : 3.3.1

**Code**
- Virtual Threads
  - **spring.threads.virtual.enabled** to enable virtual threads in your application.This setting works both for the embedded Tomcat and Jetty.
- SSL bundle
  - **spring.ssl.bundle** create objects that provide access to the specified trust material
- Spring MVC and WebFlux URL Matching Changes
  - the trailing slash matching configuration option has been deprecated and its default value set to false. We can change the default with global Spring MVC configuration
- Image Banner Support Removed 
  - Support for image-based application banners has been removed. banner.gif, banner.jpg, and banner.png files are now ignored and should be replaced with a text-based banner.txt file.
- Actuator Endpoints Sanitization
   - Since, the /env and /configprops endpoints can contains sensitive values, all values are always masked by default. This used to be case only for keys considered to be sensitive.can be configured using the properties management.endpoint.env.show-values or management.endpoint.configprops.show-values which can have the following values:
    NEVER - All values are sanitized (the default).
    ALWAYS - All values are present in the output (any user-defined sanitizing functions will still apply).
    WHEN_AUTHORIZED - Values are present in the output only if a user is authorized (any user-defined sanitizing functions will apply).
- Native Images with GraalVM
  - To build the image, you can run the spring-boot:build-image goal with the native profile active:mvn -Pnative spring-boot:build-image
- Zipkin
  - A new JDK HttpClient based Zipkin sender has been implemented. This sender only depends on the JDK and will be the default sender in Spring Boot 3.5.0, superseding the WebClient and RestTemplate sender implementations.
- Observability
  - Observability is the ability to observe the internal state of a running system from the outside. It consists of the three pillars logging, metrics and traces.
-Http Interfaces
  - The Spring Framework lets you define an HTTP service as a Java interface with annotated methods for HTTP exchanges. You can then generate a proxy that implements this interface and performs the exchanges. This helps to simplify HTTP remote access which often involves a facade that wraps the details of using the underlying HTTP client.
    Prior to Spring Boot 3 I would use the RestTemplate or WebClient to construct this call:
    `public List<Post> loadPosts() {
    ResponseEntity<List<Post>> exchange = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
    return exchange.getBody();
    }`
    In Spring Boot 3 you can declare an interface with the methods you would like to support.
    `public interface JsonPlaceholderService {
        @GetExchange("/posts")
        List<Post> loadPosts();
    }`
    Next, create a proxy that will perform the declared HTTP exchanges:

     `WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
     HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
     JsonPlaceholderService jps = factory.createClient(JsonPlaceholderService.class);`
- Problem Details for HTTP APIs
  - A common requirement for REST services is to include details in the body of error responses. The Spring Framework supports the "Problem Details for HTTP APIs" specification
  - check ExceptionHandlerAdvice.java
- Spring Security 6.0
  - WebSecurityConfigurerAdapter deprecated
  - .authorizeRequests() - deprecated
  - RequestMatchers
- Prometheus Support
  - In Spring Boot 3, Auto-Configuration for Prometheus Exemplars and Push Gateway can be configured
- Miscellaneous
    Auto-configuration for the new Elasticsearch Java Client has been introduced.
    Apache HTTP client a JdkClientHttpConnector will now be auto-configured
    The @SpringBootTest annotation can now use the main of any discovered @SpringBootConfiguration class if it’s available. This means that tests can now pick up any custom SpringApplication configuration performed by your main method.

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
<div align="center">



# PolygonMC
### Your Minecraft 1.21 server software

***

</div>

### PolygonMC is an extended version of the Minestom framework! It is intended to implement minor optimizations such as new events, the old extensions support and other things.

<br>

<div align="center">

## Features
***

</div>

### Command system rework ➔

#### A simple overhaul for a cleaner class structure

```java
public final class DemoCommandRegistry implements CommandRegistry {

    @RegisterCommand(alias = "demo")
    public void demoCommand(CommandInput input){
        input.getSender().sendMessage("DEMO");
    }

    @RegisterCommand(alias = "helloWorld")
    public void helloWorld(CommandInput input){
        input.getSender().sendMessage("Hello World!");
    }

}
```
```java
// ...
MinecraftServer.getCommandManager().registerCommandRegistry(new DemoCommandRegistry());
// ...
```
<div align="center">

#### We are working on even more features...

</div>

<div align="center">

## Installation
***

</div>

### Maven ➔
```xml
<repositories>
    <!-- ... -->
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependencies>
    <!-- ... -->
    <dependency>
        <groupId>com.github.aredblock</groupId>
        <artifactId>PolygonMC</artifactId>
        <version>VERSION</version>
        <exclusions>
            <exclusion>
                <groupId>org.jboss.shrinkwrap.resolver</groupId>
                <artifactId>shrinkwrap-resolver-depchain</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

### Gradle ➔
```kotlin
repositories {
    // ...
    mavenCentral()
    maven(url = "https://jitpack.io")
}
```

```kotlin
dependencies {
    //...
    implementation("com.github.aredblock:PolygonMC:VERSION")
}
```

<br>

> [!NOTE]
> You can get the latest version [here](https://jitpack.io/#aredblock/PolygonMC)


<br>

<div align="center">

#### From users for users ❤️

</div>
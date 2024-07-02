<div align="center">



# PolygonMC
### Your Minecraft 1.21 server software

***

</div>

### PolygonMC is an extended version of the Minestom framework! It is intended to implement minor optimizations such as new events, the Addons and other things.

<br>

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
> #### You can get the latest version [here](https://jitpack.io/#aredblock/PolygonMC). <br>
> If you use the PolygonMC runtime, make sure that your runtime has implemented a current version of the api to be able to execute new addons!

<div align="center">

## Features
***

</div>

- [Command system rework](#command-system-rework-)
- [Event system rework](#event-system-rework-)
- [Addons](#addons-)
- [AddonMessaging](#addonmessaging-)
- [Regions](#regions-)
- [Instance improvements](#instance-improvements-)
- [Schematics](#schematics-)
- [FakePlayer](#fakeplayer-)
***

### Command system rework ➔

#### A simple overhaul for a cleaner class structure.

```java
public final class DemoCommandRegistry implements CommandRegistry {

    @RegisterCommand(alias = "demo")
    public void demoCommand(CommandInput input){
        input.getSender().sendMessage("DEMO");
    }

    @RegisterCommand(alias = "helloWorld", aliases = { "hello" })
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
[> MainDemo.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/server/MainDemo.java)

<br>

### Event system rework ➔

#### A simple overhaul for a cleaner class structure.

```java
public final class DemoEventRegistry implements ListenerRegistry {

    @EventHandler
    //or
    @RegisterListener
    public void playerSpawnEvent(PlayerSpawnEvent event){
        if(event.isFirstSpawn()){
            event.getPlayer().sendMessage("Hello World!");
        }
    }

}
```

```java
// ...
MinecraftServer.getGlobalEventHandler().registerListenerRegistry(new DemoEventRegistry());
// ...
```
[> MainDemo.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/server/MainDemo.java)

<br>

### Addons ➔

#### The PolygonMC Addon API loads addons into the JVM during runtime and executes them.

```java
public final class DemoAddon implements Addon {

    @Override
    public void onInitialize() {
        MinecraftServer.LOGGER.info("DemoAddon initialized!");

        var addonManager = MinecraftServer.getAddonManager();
        addonManager.getAddonMessageManager().sendMessage("demoaddon", "HelloWorld");
        MinecraftServer.LOGGER.info(addonManager.getAddonMessageManager().popMessage("demoaddon"));
    }

    @Override
    public void onShutdown() {
        MinecraftServer.LOGGER.info("DemoAddon shutdown!");
    }
    
}
```
[> DemoAddon.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/addon/DemoAddon.java)

```json
{
  "name": "DemoAddon",
  "entrypoint": "de.aredblock.polygonmc.addon.DemoAddon"
}
```
[> addon.json](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/resources/addon.json)

<br>

### AddonMessaging ➔

#### With AddonMessaging it is possible to send simple messages in a Redis-like virtual channel. However, these are only available in the current JVM.

```java
// ...
MinecraftServer.getAddonManager().getAddonMessageManager().sendMessage("demoaddon", "HelloWorld");
MinecraftServer.LOGGER.info(addonManager.getAddonMessageManager().popMessage("demoaddon"));
// ...
```
[> DemoAddon.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/addon/DemoAddon.java)

<br>

### Regions ➔

#### Regions are a region between two positions. In regions, you can easily check whether a certain position is located within a region.

```java
// ...
var spawnRegion = new Region(new Pos(-10, 30, -10), new Pos(10, 60, 10));

if(spawnRegion.isInRegion(new Pos(0,42,0))){
    //This position is located in this region.
}
// ...
```
[> DemoAddon.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/addon/DemoAddon.java)

<br>

### Instance improvements ➔

#### This makes it possible to generate/load entire worlds within one line.
```java
// ...
MinecraftServer.getInstanceManager().generateFlat(Block.STONE);
// ...
MinecraftServer.getInstanceManager().generateFromFile(new File("worlds/world"));
// ...
```

[> DemoAddon.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/addon/DemoAddon.java)


<br>

### Schematics ➔

#### With the Schematic implementation in PolygonMC you can easily load WorldEdit schematics
```java
// ...
var schematic = new Schematic(new File("demo.schem"))
        .updateOption(SchematicOption.AIRPLACEMENT, false);
schematic.paste(player.getInstance(), player.getPosition());
// ...
```
[> DemoAddon.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/addon/DemoAddon.java)

<br>

### FakePlayer ➔

#### With the FakePlayer you can spawn "NPCs" in your world!
```java
// ...
FakePlayer.builder()
    .skin(player.getSkin())
    .instance(player.getInstance())
    .position(location)
    .build();
// ...
```
[> MainDemo.class](https://github.com/aredblock/PolygonMC/blob/master/polygonmc-demo/src/main/java/de/aredblock/polygonmc/server/MainDemo.java)

<br>

<div align="center">

#### We are working on even more features...

</div>

<br>

<div align="center">

#### From users for users ❤️

</div>
